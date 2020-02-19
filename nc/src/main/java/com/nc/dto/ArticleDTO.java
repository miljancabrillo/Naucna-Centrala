package com.nc.dto;

import com.nc.model.Article;
import com.nc.model.Coauthor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

	private long id;
	private String title;
	private String magazineName;
	private String sceintificArea;
	private String authors;
	private String contentHighlight;
	private float price;
	private boolean bought;
	
	public ArticleDTO(Article article) {
		this.id = article.getId();
		this.title = article.getTitle();
		this.magazineName = article.getMagazine().getName();
		this.price = (float) 10.5;
		this.bought = article.getMagazine().isOpenAccess();
		this.authors = article.getAuthor().getName() + " " + article.getAuthor().getSurname() +", ";
		for(Coauthor co : article.getCoauthors()) {
			if(article.getCoauthors().indexOf(co) == (article.getCoauthors().size() - 1))	this.authors += co.getName() + " " + co.getSurname();
			else this.authors += co.getName() + " " + co.getSurname() +", ";
		}	
	}
	
}
