import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/User';

@Injectable({
    providedIn: 'root',
})
export class UsersService{

    private currentTaskId : string;

    constructor(private http : HttpClient){}

    getEditors(){
        return this.http.get<User[]>("/api/users/editors");
    }

    getReviewers(){
       return this.http.post<User[]>("/api/users/reviewers",null);
    }
}