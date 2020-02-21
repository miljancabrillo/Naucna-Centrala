import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/registration-components/registration/registration.component';
import { RegistrationService } from './services/RegistrationService';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RegistrationFormComponent } from './components/registration-components/registration-form/registration-form.component';
import { ScientificAreaService } from './services/ScientificAreaService';
import { FormsModule } from '@angular/forms';
import { HashFormComponent } from './components/registration-components/hash-form/hash-form.component';
import { SuccessMessageComponent } from './components/registration-components/success-message/success-message.component';
import { AuthService } from './services/AuthService';
import { AuthInterceptor } from './http-interceptor/AuthInterceptor';
import { TasksAndProccessesComponent } from './components/tasks-and-proccesses/tasks-and-proccesses.component';
import { TaskService } from './services/TaskService';
import { MagazineFormComponent } from './components/create-magazine-components/magazine-form/magazine-form.component';
import { AddReviewersAndEditorsComponent } from './components/create-magazine-components/add-reviewers-and-editors/add-reviewers-and-editors.component';
import { ReviewMagazineDataAdminComponent } from './components/create-magazine-components/review-magazine-data-admin/review-magazine-data-admin.component';
import { EditMagazineDataComponent } from './components/create-magazine-components/edit-magazine-data/edit-magazine-data.component';
import { SuccessMessageMagazineComponent } from './components/create-magazine-components/success-message-magazine/success-message-magazine.component';
import { ApproveReviewerComponent } from './components/registration-components/approve-reviewer/approve-reviewer.component';
import { GenericFormComponent } from './components/generic-form/generic-form.component';
import { SearchPanelComponent } from './components/web-shop-components/search-panel/search-panel.component';
import { ShopingCartComponent } from './components/web-shop-components/shoping-cart/shoping-cart.component';
import { WebShopComponent } from './components/web-shop-components/web-shop/web-shop.component';
import { SearchService } from './services/SearchService';
import { ShoppingCartService } from './services/ShoppingCartService';

import { GooglePlaceModule } from "ngx-google-places-autocomplete";


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
    ApproveReviewerComponent,
    GenericFormComponent,
    SearchPanelComponent,
    ShopingCartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    GooglePlaceModule
  ],
  providers: [
    RegistrationService,
    ScientificAreaService,
    AuthService,
    TaskService,
    SearchService,
    ShoppingCartService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
