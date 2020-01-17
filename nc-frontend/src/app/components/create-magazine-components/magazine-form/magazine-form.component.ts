import { Component, OnInit } from '@angular/core';
import { Magazine } from 'src/app/model/Magazine';
import { ScientificAreaService } from 'src/app/services/ScientificAreaService';
import { TaskService } from 'src/app/services/TaskService';
import { ScientificArea } from 'src/app/model/ScientificArea';
import * as $ from 'jquery';
import { ProcessVariable } from 'src/app/model/ProcessVariable';


@Component({
  selector: 'app-magazine-form',
  templateUrl: './magazine-form.component.html',
  styleUrls: ['./magazine-form.component.css']
})
export class MagazineFormComponent implements OnInit {

  magazine : Magazine;
  errorMessage : string = "AAAAAAAAAAAAAAA";
  scientificAreas : ScientificArea[];

  constructor(private scientificAreaService : ScientificAreaService, private taskService : TaskService) { 
    this.magazine = new Magazine();
    this.scientificAreaService.getScientificAreas().subscribe(
      data => {
        this.scientificAreas = data;
      }
    )    
  }

  ngOnInit() {
  }

  completeClick(){

    var error = false;
    var errorMessage : string; 


    $(".required-field").each(function(){
      if($(this).val() == ""){
        error = true;
        errorMessage="Field " +  $(this).attr("id") + " is required"!
      }
    });

    if(!error){
      this.errorMessage = "";
    }else{
      this.errorMessage = errorMessage;
      return;
    }
    
    if($("#scinetificAreas").val().length == 0){
      this.errorMessage = "Select at least one scinetific area!";
      return;
    }else{
      this.errorMessage = "";
    }

    this.magazine;

    this.taskService.setProcessVariable(new ProcessVariable("magazineData", this.magazine)).subscribe(
      data => {
        this.taskService.completeTask().subscribe(
          data =>{
            this.taskService.setCurrentTaskId("none")
          }
        )
      }
    )
  }

}
