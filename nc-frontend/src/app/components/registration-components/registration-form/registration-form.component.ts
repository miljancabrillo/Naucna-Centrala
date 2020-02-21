import { Component, OnInit, ComponentRef } from '@angular/core';
import { RegistrationData } from 'src/app/model/RegistrationData';
import { ScientificAreaService } from 'src/app/services/ScientificAreaService';
import { HttpClient } from '@angular/common/http';
import * as $ from 'jquery';
import { ScientificArea } from 'src/app/model/ScientificArea';
import { RegistrationService } from 'src/app/services/RegistrationService';
import { ProcessVariable } from 'src/app/model/ProcessVariable';






@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  registrationData = new RegistrationData();
  scientificAreas : ScientificArea[];
  ref : ComponentRef<any>;

  constructor(private http : HttpClient, private scientificAreaService : ScientificAreaService, private registrationService : RegistrationService) {
    
    this.scientificAreaService.getScientificAreas().subscribe(
      data => {
        this.scientificAreas = data;
      }
    )    
   }

  ngOnInit() {
    this.registrationService.claimRegistrationTask().subscribe(
      data => {
        console.log(data);
        this.registrationService.getProcessVariable("registrationData").subscribe(
          data =>{
            if(data){
              this.registrationData = data;
              var ids = new Array();
              //na multiselectu da oznaci prethodno odabrane
              for(let area of this.registrationData.scientificAreas){
                ids.push(area.id);
              }
              this.registrationData.scientificAreas = [];
              for(let area of this.scientificAreas){
                for(let id of ids){
                  if(area.id == id) this.registrationData.scientificAreas.push(area);
                }
              }
            }
          }
        );
      }
    )
  }

  completeClick(){

    var error = false;
    var errorMessage = "";

    $(".required-field").each(function(){
      if($(this).attr("id") == "email"){
        //.vali
      }
      if($(this).val() == ""){
        error = true;
        errorMessage="Field " +  $(this).attr("id") + " is required"!
      }
    });

    if(error){
      this.registrationData.errorMessage = errorMessage;
      return;
    }else{
      this.registrationData.errorMessage = "";
    }
    
    if($("#scinetificAreas").val().length == 0){
      this.registrationData.errorMessage = "Select at least one scinetific area!";
      return;
    }else{
      this.registrationData.errorMessage = "";
    }

     //preuzimanje koordinata lokacije
     let address = $("#autocomplete").val().replace(' ','+');
     this.http.get<any>("https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&key=AIzaSyDnihJyw_34z5S1KZXp90pfTGAqhFszNJk").subscribe(
       data => {
         this.registrationData.lon = data.results[0].geometry.location.lng;
         this.registrationData.lat = data.results[0].geometry.location.lat;
         this.registrationService.setProcessVariable(new ProcessVariable("registrationData", this.registrationData)).subscribe(
          data => {
            this.registrationService.completeRegistrationTask().subscribe(
              data =>{
                this.registrationService.setCurrentTaskId("none")
              }
            )
            console.log(this.registrationData);
          }
        )
       },
       eror =>{
         
       }
     )

    
  }

  showHideClick(){
    if($("#showHideBtn").text()=="Show"){
      $("#showHideBtn").text("Hide");
      $("#password").attr("type","text");
    }else{
      $("#showHideBtn").text("Show");
      $("#password").attr("type","password")
    }
  }

  
 



}
