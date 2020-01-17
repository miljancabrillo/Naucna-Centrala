import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ScientificArea } from '../model/ScientificArea';

@Injectable({
    providedIn: 'root',
})
export class ScientificAreaService{

    constructor(private http : HttpClient){

    }

    getScientificAreas(){
        return this.http.get<ScientificArea[]>('/api/scinetificAreas');
    }

}