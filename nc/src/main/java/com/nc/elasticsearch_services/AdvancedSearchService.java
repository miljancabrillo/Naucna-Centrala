package com.nc.elasticsearch_services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.document_handlers.PDFHandler;
import com.nc.dto.FormDTO;
import com.nc.dto.FormFieldDTO;
import com.nc.model.Article;
import com.nc.model.Coauthor;
import com.nc.model.UserDetails;
import com.nc.repository.ArticleRepository;
import com.nc.repository.UserDetailsRepository;

@Service
public class AdvancedSearchService {

	@Autowired
	RestHighLevelClient client;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	UserDetailsRepository udRepository;
	
	@Autowired
	PDFHandler pdfHandler;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@SuppressWarnings("unchecked")
	public FormDTO geoFilter(String taskId, FormDTO formDTO) throws IOException {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		String authorUsername = (String) runtimeService.getVariable(task.getProcessInstanceId(), "AuthorId");
		UserDetails author = udRepository.findUserDetailsByUsername(authorUsername);
		
		ArrayList<Coauthor> coauthors = (ArrayList<Coauthor>)  runtimeService.getVariable(task.getProcessInstanceId(), "coauthors");
		
		HashSet<String> results = doGeoQuery(author.getLat(), author.getLon());
		for(Coauthor co : coauthors) {
			results.retainAll(doGeoQuery(co.getLat(), co.getLon()));
		}
	
		HashMap<String, String> filteredReviewers = new HashMap<>();
		
		FormFieldDTO field = formDTO.getFieldById("selectedReviewers");
		for(HashMap.Entry<String,String> entry : field.getSelectValues().entrySet()){
			if(results.contains(entry.getKey())) {
				filteredReviewers.put(entry.getKey(), entry.getValue());
			}
		}
		field.setSelectValues(filteredReviewers);
		return formDTO;
	}
	
	private HashSet<String> doGeoQuery(float lat, float lon) throws IOException{
		
		BoolQueryBuilder qb = new BoolQueryBuilder();
		qb.must(QueryBuilders.matchAllQuery());
		qb.mustNot(QueryBuilders.geoDistanceQuery("location").distance("100km").point(lon,lat));
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		sourceBuilder.query(qb);
		
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.source(sourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		Set<String> searchResults = new HashSet<String>();
		
		for(SearchHit hit : searchResponse.getHits()) {
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			searchResults.add(sourceAsMap.get("username").toString());
		}
		return (HashSet<String>) searchResults;
		
	}
	
	public FormDTO moreFilter(String taskId, FormDTO formDTO) throws IOException {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		String fileName = (String) runtimeService.getVariable(task.getProcessInstanceId(), "fileName");
		File file = new File("pdf/" + fileName);
		String content = pdfHandler.getText(file);
		
		MoreLikeThisQueryBuilder qb = QueryBuilders.moreLikeThisQuery(new String[] {"content"}, new String[]{content},null);
		qb.analyzer("serbian").minDocFreq(1).maxQueryTerms(120).minimumShouldMatch("70%");
		
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		sourceBuilder.query(qb);
		
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.source(sourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		HashSet<String> results = new HashSet<>();
			
		System.out.println(searchResponse.toString());
		
		for(SearchHit hit : searchResponse.getHits()) {
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			Article article = articleRepository.findOneById(Long.parseLong(sourceAsMap.get("id").toString()));
			for(UserDetails ud : article.getReviewers()) {
				results.add(ud.getUsername());
			}
		}
		
		HashMap<String, String> filteredReviewers = new HashMap<>();
		
		FormFieldDTO field = formDTO.getFieldById("selectedReviewers");
		for(HashMap.Entry<String,String> entry : field.getSelectValues().entrySet()){
			if(results.contains(entry.getKey())) {
				filteredReviewers.put(entry.getKey(), entry.getValue());
			}
		}
		field.setSelectValues(filteredReviewers);
		return formDTO;
	}
}
