import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Article } from '../model/Article';
import { PaymentRequest } from '../model/PaymentRequest';

@Injectable({
    providedIn: 'root',
})
export class ShoppingCartService{

articles : Article[] = new Array();

constructor(private http : HttpClient){}

addArticleToCart(article : Article){
    this.articles.push(article);
}

removeArticleFromCart(article : Article){
    this.articles.splice(this.articles.indexOf(article),1);
}

getArticles(){
    return this.articles;
}

checkOut(paymentRequest : PaymentRequest){
   return this.http.post("/api/testPayment",paymentRequest, {responseType: 'text'});
}

testSubscription(){
    return this.http.get("/api/testSubscription", {responseType: 'text'});
 }
 
 tesRegistration(){
    return this.http.get("/api/testRegistration", {responseType: 'text'});
 }

}