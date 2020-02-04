import { Component, OnInit } from '@angular/core';
import { RegistrationData } from 'src/app/model/RegistrationData';
import { RegistrationService } from 'src/app/services/RegistrationService';
import { ProcessVariable } from 'src/app/model/ProcessVariable';
import { TaskService } from 'src/app/services/TaskService';

@Component({
  selector: 'app-approve-reviewer',
  templateUrl: './approve-reviewer.component.html',
  styleUrls: ['./approve-reviewer.component.css']
})
export class ApproveReviewerComponent implements OnInit {

  registrationData = new RegistrationData();
  reviewerRequestSatus : boolean = false;

  constructor(private taskService : TaskService) { 
        this.taskService.getProcessVariable("registrationData").subscribe(
          data =>{
              this.registrationData = data;
            }
      );
  }

  ngOnInit() {

  }
  
  completeClick(){
    this.taskService.setProcessVariable(new ProcessVariable("reviewerRequestSatus",new Object(this.reviewerRequestSatus))).subscribe(
      data => {
        this.taskService.completeTask().subscribe(
          data =>{
            this.taskService.setCurrentTaskId("none")
          }
        )
        console.log(this.registrationData);
      }
    )
  }
}
