import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from '../model/Article';
import { SearchField } from '../model/SearchField';

@Injectable({
    providedIn: 'root',
})
export class SearchService{

    constructor(private http : HttpClient){}

    getTestArticles(){
        return this.http.get<Article[]>("/api/testArticles");
    }

    search(searchFields : SearchField[]){
        return this.http.post("/api/search", searchFields);
    }

}