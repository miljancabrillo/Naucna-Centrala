import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from '../model/Article';
import { SearchField } from '../model/SearchField';

@Injectable({
    providedIn: 'root',
})
export class SearchService{

    public showSearch = true;

    public searchResults : Article[];

    constructor(private http : HttpClient){}


    search(searchFields : SearchField[]){
        return this.http.post<Article[]>("/api/search", searchFields);
    }

}