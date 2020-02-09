import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ProcessVariable } from '../model/ProcessVariable';
import { Task } from '../model/Task';
import { Form } from '../model/Form';

@Injectable({
    providedIn: 'root',
})
export class TaskService{

    private currentTaskId : string;

    constructor(private http : HttpClient){}

    setCurrentTaskId(id : string){
        this.currentTaskId= id;
    }

    getCurrentTaskId(){
        return this.currentTaskId;
    }

    startProcess(proccessInstanceKey : string){
        return this.http.get('api/tp/startProccess/' + proccessInstanceKey);
    }

    getTasksByUser(){
        return this.http.get<Task[]>('api/tp/tasksByUser')
    }


    setProcessVariable(processVariable : ProcessVariable){
        return this.http.post('api/tp/setProcessVariable/' + this.currentTaskId, processVariable);
    }

    setStringProcessVariable(variableName: string, processVariable : string){
        return this.http.post('api/tp/setProcessVariable/' + this.currentTaskId, processVariable);
    }

    getProcessVariable(variable : String){
        return this.http.get<any>('api/tp/getProcessVariable/' + this.currentTaskId + '/' + variable);
    }

    getForm(){
        return this.http.get<Form>('api/form/' + this.currentTaskId);
    }

    postForm(form : Form){
        return this.http.post('api/form/' + this.currentTaskId, form);
    }

    filterForm(form : Form){
        return this.http.post<Form>('api/form/filterReviewers/' + this.currentTaskId, form);
    }

    completeTask(){
        return this.http.post('api/tp/completeTask/' + this.currentTaskId, null)
    }
}