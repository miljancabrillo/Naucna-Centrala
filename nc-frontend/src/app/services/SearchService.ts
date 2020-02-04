import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from '../model/Article';

@Injectable({
    providedIn: 'root',
})
export class SearchService{

    constructor(private http : HttpClient){}

    getTestArticles(){
        return this.http.get<Article[]>("/api/testArticles");
    }

}