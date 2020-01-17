import { Component, OnInit, OnDestroy } from '@angular/core';
import { Task } from 'src/app/model/Task';
import { RegistrationService } from 'src/app/services/RegistrationService';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit, OnDestroy {
 
  tasks : Task[];
  currentTask = "";
  intervalIdOne;
  intervalIdTwo;

  constructor(private router : Router, private route: ActivatedRoute, private registrationService : RegistrationService) { 
    this.registrationService.startRegistrationProcess().subscribe(
      data => {
        this.registrationService.setRegistrationProcesId(data);
        this.registrationService.getTasksOfRegistrationProcess().subscribe(
          data =>{
            this.tasks = data;
          }
        )
       }
    )
    this.intervalIdOne = setInterval(()=>{
      this.registrationService.getUnclaimedTasksOfRegistrationProcess().subscribe(
        data => {
          this.tasks = data;
         }
      )
    }, 1000);
    this.intervalIdTwo = setInterval(()=>{
     this.currentTask = this.registrationService.getCurrentTaskId();
    }, 500);
  }

  ngOnInit() {
  }

  ngOnDestroy(): void {
    clearInterval(this.intervalIdOne);
    clearInterval(this.intervalIdTwo);
  }

  openTask(taskType : string, taskId : string){
    this.registrationService.setCurrentTaskId(taskId);
    this.router.navigate([taskType], {relativeTo: this.route});
  }

}
