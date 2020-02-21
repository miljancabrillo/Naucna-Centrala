package com.nc.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nc.dto.ArticleDTO;
import com.nc.dto.FormDTO;
import com.nc.dto.SearchFieldDTO;
import com.nc.elasticsearch_services.AdvancedSearchService;
import com.nc.elasticsearch_services.ArticleSearchService;

@RestController
public class SearchController {
	
	@Autowired
	ArticleSearchService searchService;
	
	@Autowired
	AdvancedSearchService advancedSearchService;
	
	@PostMapping("/search")
	public ArrayList<ArticleDTO> search(@RequestBody ArrayList<SearchFieldDTO> searchFields) throws IOException{
		return searchService.search(searchFields);
	}
	
	@PostMapping("/filterGeo/{taskId}")
	public FormDTO filterGeo(@PathVariable("taskId") String taskId, @RequestBody FormDTO formDTO) throws IOException{
		return advancedSearchService.geoFilter(taskId, formDTO);
	}
	
	@PostMapping("/filterMore/{taskId}")
	public FormDTO filterMore(@PathVariable("taskId") String taskId, @RequestBody FormDTO formDTO) throws IOException{
		return advancedSearchService.moreFilter(taskId, formDTO);
	}
}
