import { NgModule } from '@angular/core';
import { Routes, RouterModule, Route } from '@angular/router';
import { RegistrationComponent } from './components/registration/registration.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import { HashFormComponent } from './components/hash-form/hash-form.component';
import { SuccessMessageComponent } from './components/success-message/success-message.component';
import { WebShopComponent } from './components/web-shop/web-shop.component';
import { TasksAndProccessesComponent } from './components/tasks-and-proccesses/tasks-and-proccesses.component';
import { MagazineFormComponent } from './components/create-magazine-components/magazine-form/magazine-form.component';
import { AddReviewersAndEditorsComponent } from './components/create-magazine-components/add-reviewers-and-editors/add-reviewers-and-editors.component';
import { ReviewMagazineDataAdminComponent } from './components/create-magazine-components/review-magazine-data-admin/review-magazine-data-admin.component';
import { SuccessMessageMagazineComponent } from './components/create-magazine-components/success-message-magazine/success-message-magazine.component';
import { EditMagazineDataComponent } from './components/create-magazine-components/edit-magazine-data/edit-magazine-data.component';

const routes: Route []= [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'registration',
   component: RegistrationComponent,
   children : [
    {path: '',  redirectTo: 'registration', pathMatch: 'full' },
    {path: 'registrationForm', component: RegistrationFormComponent},
    {path: 'hashForm', component: HashFormComponent},
    {path: 'successMessage', component: SuccessMessageComponent},
    {path: '**',  redirectTo: 'registration'}]},
  {path: 'home',
   component: HomeComponent,
   children : [
    {path: '',  redirectTo: 'home', pathMatch: 'full' },
    {path: 'webShop', component: WebShopComponent},
    {path: 'tasks',
     component: TasksAndProccessesComponent, 
    children : [
      {path: '',  redirectTo: 'tasks', pathMatch: 'full' },
      {path: 'inputMagazineData', component: MagazineFormComponent},
      {path: 'addEditorsAndReviewers', component: AddReviewersAndEditorsComponent},
      {path: 'reviewMagazineAdmin', component: ReviewMagazineDataAdminComponent},
      {path: "editMagazineData", component: EditMagazineDataComponent},
      {path: 'successMessage', component: SuccessMessageMagazineComponent},
      {path: '**',  redirectTo: 'tasks'}
    ]
    },
    {path: '**',  redirectTo: 'home'}],
  },
  {path: '**',  redirectTo: '/home' }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }