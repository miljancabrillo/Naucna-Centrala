import { Component, OnInit } from '@angular/core';
import { HashData } from 'src/app/model/HashData';
import { RegistrationService } from 'src/app/services/RegistrationService';
import { ProcessVariable } from 'src/app/model/ProcessVariable';

@Component({
  selector: 'app-hash-form',
  templateUrl: './hash-form.component.html',
  styleUrls: ['./hash-form.component.css']
})
export class HashFormComponent implements OnInit {

  hashData : HashData = new HashData();

  constructor(private registrationService : RegistrationService) { }

  ngOnInit() {
    this.registrationService.claimRegistrationTask().subscribe(
      data => {
        this.registrationService.getProcessVariable("hashData").subscribe(
          data =>{
            if(data){
              this.hashData = data;
            }else{
              this.hashData = new HashData();
            }
          }
        );
      }
    )
  }

  completeClick(){
    
    if(this.hashData.hash == ""){
      this.hashData.errorMessage = "Type in hash value!"
      return;
    }

    this.registrationService.setProcessVariable(new ProcessVariable("hashData", this.hashData)).subscribe(
      data => {
        this.registrationService.completeRegistrationTask().subscribe(
          data =>{
            this.registrationService.setCurrentTaskId("none")
          }
        )
      }
    )
  }
}
