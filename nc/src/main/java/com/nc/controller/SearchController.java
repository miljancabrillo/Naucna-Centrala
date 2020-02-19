package com.nc.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nc.dto.ArticleDTO;
import com.nc.dto.SearchFieldDTO;
import com.nc.elasticsearch_services.ArticleSearchService;

@RestController
public class SearchController {
	
	@Autowired
	ArticleSearchService searchService;
	
	@PostMapping("/search")
	public ArrayList<ArticleDTO> search(@RequestBody ArrayList<SearchFieldDTO> searchFields) throws IOException{
		return searchService.search(searchFields);
	}
}
