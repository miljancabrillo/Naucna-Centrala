import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Task } from '../model/Task';
import { ProcessVariable } from '../model/ProcessVariable';

@Injectable({
    providedIn: 'root',
})
export class RegistrationService{

    private registrationProcesId : string;
    private currentTaskId : string;

    constructor(private http : HttpClient){}

    setRegistrationProcesId(id : string){
        this.registrationProcesId = id;
    }

    getRegistrationProcesId(){
        return this.registrationProcesId;
    }

    setCurrentTaskId(id : string){
        this.currentTaskId= id;
    }

    getCurrentTaskId(){
        return this.currentTaskId;
    }

    startRegistrationProcess(){
        return this.http.get('api/registration', {responseType: 'text'});
    }

    getTasksOfRegistrationProcess(){
        return this.http.get<Task[]>('api/registration/tasksByProcess/'+this.registrationProcesId)
    }

    getUnclaimedTasksOfRegistrationProcess(){
        return this.http.get<Task[]>('api/registration/unclaimedTasksByProcess/'+this.registrationProcesId)
    }

    setProcessVariable(processVariable : ProcessVariable){
        return this.http.post('api/registration/setProcessVariable/'+this.currentTaskId, processVariable);
    }

    getProcessVariable(variable : String){
        return this.http.get<any>('api/registration/getProcessVariable/'+this.currentTaskId + '/' + variable);
    }

    claimRegistrationTask(){
        return this.http.post('api/registration/claimRegistrationTask/'+this.currentTaskId, null)
    }

    completeRegistrationTask(){
        return this.http.post('api/registration/completeRegistationTask/'+this.currentTaskId, null)
    }
}