import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { RegistrationService } from './services/RegistrationService';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { ScientificAreaService } from './services/ScientificAreaService';
import { FormsModule } from '@angular/forms';
import { HashFormComponent } from './components/hash-form/hash-form.component';
import { SuccessMessageComponent } from './components/success-message/success-message.component';
import { AuthService } from './services/AuthService';
import { AuthInterceptor } from './http-interceptor/AuthInterceptor';
import { WebShopComponent } from './components/web-shop/web-shop.component';
import { TasksAndProccessesComponent } from './components/tasks-and-proccesses/tasks-and-proccesses.component';
import { TaskService } from './services/TaskService';
import { MagazineFormComponent } from './components/create-magazine-components/magazine-form/magazine-form.component';
import { AddReviewersAndEditorsComponent } from './components/create-magazine-components/add-reviewers-and-editors/add-reviewers-and-editors.component';
import { ReviewMagazineDataAdminComponent } from './components/create-magazine-components/review-magazine-data-admin/review-magazine-data-admin.component';
import { EditMagazineDataComponent } from './components/create-magazine-components/edit-magazine-data/edit-magazine-data.component';
import { SuccessMessageMagazineComponent } from './components/create-magazine-components/success-message-magazine/success-message-magazine.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegistrationComponent,
    RegistrationFormComponent,
    HashFormComponent,
    SuccessMessageComponent,
    WebShopComponent,
    TasksAndProccessesComponent,
    MagazineFormComponent,
    AddReviewersAndEditorsComponent,
    ReviewMagazineDataAdminComponent,
    EditMagazineDataComponent,
    SuccessMessageMagazineComponent,
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    RegistrationService,
    ScientificAreaService,
    AuthService,
    TaskService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
