import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/services/SearchService';
import { Article } from 'src/app/model/Article';
import { ShoppingCartService } from 'src/app/services/ShoppingCartService';

@Component({
  selector: 'app-web-shop',
  templateUrl: './web-shop.component.html',
  styleUrls: ['./web-shop.component.css']
})
export class WebShopComponent implements OnInit {

  articles : Article[];
  showSearch : boolean = true;
  searchResults : Article[];
  constructor(private searchService : SearchService, private cartService : ShoppingCartService) { 
    setInterval(()=>{
      if(this.showSearch != searchService.showSearch){
        this.searchResults = searchService.searchResults;
        if(this.searchResults != null){
          for(let article of this.searchResults){
            article.addedToCart = false;
          }
        }
      }
      this.showSearch = searchService.showSearch;
    }, 200)
  }

  ngOnInit() {
  }
  

  addToCart(article : Article){
    article.addedToCart = true;
    this.cartService.addArticleToCart(article);
  }

  backToSearh(){
    this.searchService.showSearch = true;
  }
  
  linkClick(value){
    var win = window.open();
    win.document.body.style.margin = "0px 0px 0px 0px"
    win.document.body.innerHTML = '<iframe src="' + value  + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>';
  }

}
