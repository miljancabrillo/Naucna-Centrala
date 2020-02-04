import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from 'src/app/services/RegistrationService';

@Component({
  selector: 'app-success-message',
  templateUrl: './success-message.component.html',
  styleUrls: ['./success-message.component.css']
})
export class SuccessMessageComponent implements OnInit {

  constructor(private router : Router, private registrationService : RegistrationService) {
    this.registrationService.claimRegistrationTask().subscribe(
      data => {
        this.registrationService.completeRegistrationTask().subscribe(
          data =>{
            console.log(data);
          }
        )
      }
    )
   }

  ngOnInit() {
  }

  homeClick(){
    this.router.navigate(["home"]);

  }

}
