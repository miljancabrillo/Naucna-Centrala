import { Component, OnInit, OnDestroy } from '@angular/core';
import { Task } from 'src/app/model/Task';
import { TaskService } from 'src/app/services/TaskService';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-tasks-and-proccesses',
  templateUrl: './tasks-and-proccesses.component.html',
  styleUrls: ['./tasks-and-proccesses.component.css']
})
export class TasksAndProccessesComponent implements OnInit, OnDestroy {
  
  tasks : Task[];
  intervalOne;
  intervalTwo;
  currentTask : string;

  constructor(private router : Router, private route: ActivatedRoute, private taskService : TaskService) { 
    this.intervalOne = setInterval(()=>{
      taskService.getTasksByUser().subscribe(
        data => {
          this.tasks = data;
        }
      )
    }, 1000);
    this.intervalTwo = setInterval(()=>{
      this.currentTask = this.taskService.getCurrentTaskId();
     }, 500);
  }

  ngOnInit() {
  }

  startProccess(processInstanceKey : string){
    this.taskService.startProcess(processInstanceKey).subscribe(
      data => {
        
      }
    );
  }

  openTask(taskKey : string, taskId : string){
    this.taskService.setCurrentTaskId(taskId);
    this.router.navigate([taskKey], {relativeTo: this.route});
  }

  ngOnDestroy(): void {
    clearInterval(this.intervalOne);
    clearInterval(this.intervalTwo);
  }

}
