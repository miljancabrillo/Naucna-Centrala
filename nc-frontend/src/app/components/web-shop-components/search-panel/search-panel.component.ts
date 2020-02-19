import { Component, OnInit } from '@angular/core';
import { SearchField } from 'src/app/model/SearchField';
import { SearchService } from 'src/app/services/SearchService';

@Component({
  selector: 'app-search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.css']
})
export class SearchPanelComponent implements OnInit {

  searchFields : SearchField[] = new Array();

  constructor(private searchService : SearchService) { 
    this.searchFields.push(new SearchField());
  }

  ngOnInit() {
  }

  add(){
    this.searchFields.push(new SearchField());
  }

  remove(field : SearchField){
    this.searchFields.splice(this.searchFields.indexOf(field),1);
  }

  search(){
    this.searchService.search(this.searchFields).subscribe(
      data =>{
        this.searchService.searchResults = data;
        this.searchService.showSearch = false;
      }
    )
  }

}
