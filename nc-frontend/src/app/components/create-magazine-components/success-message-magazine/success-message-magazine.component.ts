import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/services/TaskService';

@Component({
  selector: 'app-success-message-magazine',
  templateUrl: './success-message-magazine.component.html',
  styleUrls: ['./success-message-magazine.component.css']
})
export class SuccessMessageMagazineComponent implements OnInit {

  
  constructor(private taskSerice : TaskService) { }

  ngOnInit() {
  }

  completeClick(){
    this.taskSerice.completeTask().subscribe(
      data =>{
        this.taskSerice.setCurrentTaskId("none");
      }
    )
  }

}
