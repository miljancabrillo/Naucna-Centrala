import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from 'src/app/services/ShoppingCartService';
import { Article  } from 'src/app/model/Article';
import { PaymentRequest } from 'src/app/model/PaymentRequest';


@Component({
  selector: 'app-shoping-cart',
  templateUrl: './shoping-cart.component.html',
  styleUrls: ['./shoping-cart.component.css']
})
export class ShopingCartComponent implements OnInit {

  articles : Article[] = new Array();
  total : number = 0;

  constructor(private cartService : ShoppingCartService) {
    setInterval(() => {
      this.articles = this.cartService.getArticles();
      this.total = 0;
      for(let article of this.articles){
        this.total += article.price;
      }
    }, 300)
   }

  ngOnInit() {
  }

  removeFromCart(article : Article){
    article.addedToCart = false;
    this.cartService.removeArticleFromCart(article);
  }

  checkOut(){
    if(this.articles.length == 0) return;
    let paymentRequest = new PaymentRequest();
    paymentRequest.currency = "USD";
    paymentRequest.price = this.total;
    paymentRequest.sellerId = 1;

    this.cartService.checkOut(paymentRequest).subscribe(
      data =>{
        console.log(data)
        window.open(data,"_blank");
      }
    )

  }

  testSub(){
    this.cartService.testSubscription().subscribe(
      data =>{
        console.log(data)
        window.open(data,"_blank");
      }
    )
  }

  testReg(){
    this.cartService.tesRegistration().subscribe(
      data =>{
        console.log(data)
        window.open(data,"_blank");
      }
    )
  }
}
