import { Component, OnInit } from '@angular/core';
import { ScientificAreaService } from 'src/app/services/ScientificAreaService';
import { TaskService } from 'src/app/services/TaskService';
import { Magazine } from 'src/app/model/Magazine';
import { User } from 'src/app/model/User';
import { UsersService } from 'src/app/services/UsersService';
import { ProcessVariable } from 'src/app/model/ProcessVariable';
import { ScientificAreaEditorPair } from 'src/app/model/ScientificAreaEditorPair';

@Component({
  selector: 'app-add-reviewers-and-editors',
  templateUrl: './add-reviewers-and-editors.component.html',
  styleUrls: ['./add-reviewers-and-editors.component.css']
})
export class AddReviewersAndEditorsComponent implements OnInit {

  magazine : Magazine = new Magazine();
  editors : User[];
  reviewers : User[];
  selectedEditors : User[];
  errorMessage : string = "";


  constructor(private scientificAreaService : ScientificAreaService, private taskService : TaskService, private usersService : UsersService) {
    taskService.getProcessVariable("magazineData").subscribe(
      data => {
        this.magazine = data;
        this.selectedEditors = new Array(this.magazine.scientificAreas.length);
        this.selectedEditors
      }
    )
    usersService.getEditors().subscribe(
      data =>{
        this.editors = data;
      }
    )

    usersService.getReviewers().subscribe(
      data =>{
        this.reviewers = data;
      }
    )
   }

  ngOnInit() {
  }

  completeClick(){
   
    if(this.magazine.reviewers.length < 2){
      this.errorMessage = "Select at least two reviewers!"
      return;
    }else{
      this.errorMessage = "Select at least two reviewers!"
    }

    this.magazine.scientificAreaEditorList = new Array();

    for(var scientificArea of this.magazine.scientificAreas){
      let index = this.magazine.scientificAreas.indexOf(scientificArea);
      if(this.selectedEditors[index] != null){
        var pair = new ScientificAreaEditorPair();
        pair.areaId  = scientificArea.id;
        pair.username = this.selectedEditors[index].username;
        this.magazine.scientificAreaEditorList.push(pair);
      }
    }

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
