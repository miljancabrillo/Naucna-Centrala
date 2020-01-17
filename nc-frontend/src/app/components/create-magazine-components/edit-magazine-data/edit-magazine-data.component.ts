import { Component, OnInit } from '@angular/core';
import { ScientificArea } from 'src/app/model/ScientificArea';
import { Magazine } from 'src/app/model/Magazine';
import { ScientificAreaService } from 'src/app/services/ScientificAreaService';
import { TaskService } from 'src/app/services/TaskService';
import { ProcessVariable } from 'src/app/model/ProcessVariable';
import * as $ from 'jquery';


@Component({
  selector: 'app-edit-magazine-data',
  templateUrl: './edit-magazine-data.component.html',
  styleUrls: ['./edit-magazine-data.component.css']
})
export class EditMagazineDataComponent implements OnInit {

  magazine : Magazine;
  scientificAreas : ScientificArea[];
  errorMessage : string = "";

  constructor(private scientificAreaService : ScientificAreaService, private taskService : TaskService) { 
    this.magazine = new Magazine();
    this.scientificAreaService.getScientificAreas().subscribe(
      data => {
        this.scientificAreas = data;
      }
    )    
    taskService.getProcessVariable("magazineData").subscribe(
      data =>{
        this.magazine = data;
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
