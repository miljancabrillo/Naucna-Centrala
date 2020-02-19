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
      this.showSearch = searchService.showSearch;
      this.searchResults = searchService.searchResults;
    }, 200)
  }

  ngOnInit() {
  }

  addToCart(article : Article){
    article.addedToCart = true;
    this.cartService.addArticleToCart(article);
  }
}
