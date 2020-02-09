import { NgModule } from '@angular/core';
import { Routes, RouterModule, Route } from '@angular/router';
import { RegistrationComponent } from './components/registration-components/registration/registration.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationFormComponent } from './components/registration-components/registration-form/registration-form.component';
import { HashFormComponent } from './components/registration-components/hash-form/hash-form.component';
import { SuccessMessageComponent } from './components/registration-components/success-message/success-message.component';
import { TasksAndProccessesComponent } from './components/tasks-and-proccesses/tasks-and-proccesses.component';
import { MagazineFormComponent } from './components/create-magazine-components/magazine-form/magazine-form.component';
import { AddReviewersAndEditorsComponent } from './components/create-magazine-components/add-reviewers-and-editors/add-reviewers-and-editors.component';
import { ReviewMagazineDataAdminComponent } from './components/create-magazine-components/review-magazine-data-admin/review-magazine-data-admin.component';
import { SuccessMessageMagazineComponent } from './components/create-magazine-components/success-message-magazine/success-message-magazine.component';
import { EditMagazineDataComponent } from './components/create-magazine-components/edit-magazine-data/edit-magazine-data.component';
import { ApproveReviewerComponent } from './components/registration-components/approve-reviewer/approve-reviewer.component';
import { GenericFormComponent } from './components/generic-form/generic-form.component';
import { WebShopComponent } from './components/web-shop-components/web-shop/web-shop.component';

const routes: Route []= [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'registration',
   component: RegistrationComponent,
   children : [
    {path: '',  redirectTo: 'registration', pathMatch: 'full' },
    {path: 'registrationForm', component: RegistrationFormComponent},
    {path: 'hashForm', component: HashFormComponent},
    {path: 'successMessage', component: SuccessMessageComponent},
    {path: 'Task_1db80lw', component: ApproveReviewerComponent},
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
      {path: 'Task_1db80lw', component: ApproveReviewerComponent},
      {path: 'selectMagazine', component: GenericFormComponent},
      {path: 'inputArticleInformation', component: GenericFormComponent},
      {path: 'inputCoauthorsData', component: GenericFormComponent},
      {path: 'reviewTitleAbstractAndMainConceptsOfArticle', component: GenericFormComponent},
      {path: 'reviewFormattingOfPDFFile', component: GenericFormComponent},
      {path: 'commentFormattingMistakes', component: GenericFormComponent},
      {path: 'reviewEditorCommmentAndUploadCorrectedPDF', component: GenericFormComponent},
      {path: 'selectReviewer', component: GenericFormComponent},
      {path: 'setReviewDeadline', component: GenericFormComponent},
      {path: 'reviewArticleAndSubmitRecensions', component: GenericFormComponent},
      {path: 'selectOneReviewer', component: GenericFormComponent},
      {path: 'reviewRecensions', component: GenericFormComponent},
      {path: 'recensionsMinorCorrection', component: GenericFormComponent},
      {path: 'reviewEditedArticle', component: GenericFormComponent},
      {path: 'majorCorrection', component: GenericFormComponent},
      {path: 'Task_1ha7bnj', component: GenericFormComponent},

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
