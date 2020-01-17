import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/AuthService';
import * as $ from 'jquery';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  userLogged : boolean = false;
  username : string;
  password : string;
  errorMessage : string;

  constructor(private router : Router, private route: ActivatedRoute, private authService : AuthService) { 
    this.userLogged = authService.isUserLogged();
    setInterval(() => {
      this.userLogged = authService.isUserLogged();
    }, 500);
    this.onNavClick('webShop');
  }

  ngOnInit() {
  }

  loginClick(){
    this.authService.login(this.username, this.password).subscribe(
      success => {
        if(!success) {
          this.errorMessage = "Wrong username or password";
        }else{
          this.errorMessage = "";
          this.router.navigate(["/home/webShop"]);
        }
      }
    );
  }
  
  logoutClick(){
    this.authService.logOutUser();
    this.router.navigate(["/home/webShop"]);
  }

  onNavClick(selector : string){
    $(".nav-link").removeClass('active');
    $("#"+selector).addClass('active');
    this.router.navigate([selector], {relativeTo: this.route});
  }

  registration(){
    this.router.navigate(["registration"]);
  }

}
