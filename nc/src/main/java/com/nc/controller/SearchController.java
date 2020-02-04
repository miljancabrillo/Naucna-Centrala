package com.nc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nc.dto.ArticleDTO;

@RestController
public class SearchController {

	@GetMapping("/testArticles")
	public ResponseEntity<List<ArticleDTO>> getTestArticles(){
		ArrayList<String> authors = new ArrayList<String>();
		authors.add("Marko Markovic");
		authors.add("Jako Jankovic");
		authors.add("Jovan Jovanovic");
		
		ArticleDTO articleOne = new ArticleDTO(1, "First Article", "Nat Geo", authors, (float) 12.6, 1, false);
		ArticleDTO articleTwo = new ArticleDTO(2, "Second Article", "Nat Geo", authors, (float) 14.6, 1, false);
		ArticleDTO articleThree = new ArticleDTO(3, "Third Article", "Nat Geo", authors, (float) 124.6, 1, false);
		ArticleDTO articleFour = new ArticleDTO(4, "Fourth Article", "Nat Geo", authors, (float) 12214.6, 1, false);
		ArticleDTO articleFive = new ArticleDTO(5, "Fifth Article", "Nat Geo", authors, (float) 1222.6, 1, false);
		
		ArrayList<ArticleDTO> articles = new ArrayList<>();
		articles.add(articleOne);
		articles.add(articleTwo);
		articles.add(articleThree);
		articles.add(articleFour);
		articles.add(articleFive);

		return new ResponseEntity<List<ArticleDTO>>(articles, HttpStatus.OK);
	}
	
}
