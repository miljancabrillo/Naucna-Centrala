import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/services/TaskService';
import { Form } from 'src/app/model/Form';
import { FormField } from 'src/app/model/FormFiled';

@Component({
  selector: 'app-generic-form',
  templateUrl: './generic-form.component.html',
  styleUrls: ['./generic-form.component.css']
})
export class GenericFormComponent implements OnInit {

  form : Form = new Form();
  errorMessage : string = "";

  constructor(private taskService : TaskService) { 
    taskService.getForm().subscribe(
      data => {
        this.form = data;
        console.log(this.form);
      }
    )
  }

  ngOnInit() {
  }

  fileChoserListener(files: FileList, filed : FormField){
    let fileToUpload = files.item(0); 
    filed.fileName = files.item(0).name;

    let fileReader = new FileReader();

    fileReader.onload = (e) => {
      filed.value = fileReader.result;
      console.log(fileReader.result);
    }

    fileReader.readAsDataURL(files.item(0))
  }

  completeClick(){
    console.log(this.form);
    this.taskService.postForm(this.form).subscribe(
      success =>{
        this.taskService.completeTask().subscribe(
          data =>{
            this.taskService.setCurrentTaskId("none");
          }  
        )
      },
      error =>{
        this.errorMessage = error.error;
      }
    )
  }

  linkClick(value){
    var win = window.open();
    win.document.body.style.margin = "0px 0px 0px 0px"
    win.document.body.innerHTML = '<iframe src="' + value  + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>';
 }

}
