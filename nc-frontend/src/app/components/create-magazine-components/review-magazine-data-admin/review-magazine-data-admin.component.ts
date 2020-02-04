import { Component, OnInit } from '@angular/core';
import { Magazine } from 'src/app/model/Magazine';
import { ScientificArea } from 'src/app/model/ScientificArea';
import { ScientificAreaService } from 'src/app/services/ScientificAreaService';
import { TaskService } from 'src/app/services/TaskService';
import { ProcessVariable } from 'src/app/model/ProcessVariable';

@Component({
  selector: 'app-review-magazine-data-admin',
  templateUrl: './review-magazine-data-admin.component.html',
  styleUrls: ['./review-magazine-data-admin.component.css']
})
export class ReviewMagazineDataAdminComponent implements OnInit {

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

  resendClick(){

    if(this.magazine.adminMessage == null ){
      this.errorMessage = "Fill comment field!";
      return;
    }else{
      this.errorMessage = "";
    }

    this.magazine;

    this.taskService.setProcessVariable(new ProcessVariable("magazineStatus", new Object("declined"))).subscribe(
      data =>{
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
    )

    
  }

  activateClick(){
    this.taskService.setProcessVariable(new ProcessVariable("magazineStatus", new Object("accepted"))).subscribe(
      data =>{
        this.taskService.completeTask().subscribe(
          data =>{
              this.taskService.setCurrentTaskId("none")
            }
        );
      }
    )
  }

}
