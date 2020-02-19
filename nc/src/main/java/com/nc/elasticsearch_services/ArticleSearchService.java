package com.nc.elasticsearch_services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.dto.ArticleDTO;
import com.nc.dto.SearchFieldDTO;
import com.nc.model.Article;
import com.nc.repository.ArticleRepository;

@Service
public class ArticleSearchService {
	
	@Autowired
	ArticleRepository articleDbRepository;
	
	@Autowired
	RestHighLevelClient client;
	
	public ArrayList<ArticleDTO> search(ArrayList<SearchFieldDTO> searchFields) throws IOException {
		
	
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder(); 
		//kreiranje highlghta 
		HighlightBuilder highlightBuilder = new HighlightBuilder(); 
		HighlightBuilder.Field highlightContent = new HighlightBuilder.Field("content"); 
		highlightContent.highlighterType("unified");  
		highlightBuilder.field(highlightContent);
		
		sourceBuilder.highlighter(highlightBuilder);
		
		sourceBuilder.query(getQueryBuilder(searchFields));
		
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.source(sourceBuilder);
		  
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		
		ArrayList<ArticleDTO> retVal = new ArrayList<>();
		
		//kreiranje liste rezultata
		for (SearchHit hit : searchResponse.getHits()) {
			
			
			long articleId = Long.parseLong(hit.getId());
			Article article = articleDbRepository.findOneById(articleId);
			ArticleDTO articleDTO = new ArticleDTO(article);

			Map<String, Object> sourceAsMap = hit.getSourceAsMap();
			articleDTO.setSceintificArea((String) sourceAsMap.get("scientificArea"));
			
			String highlightStirng = "";
			
			Map<String, HighlightField> highlightFields = hit.getHighlightFields();
		    HighlightField highlight = highlightFields.get("content"); 
		    if(highlight == null) {
		    	String documentContent = (String) sourceAsMap.get("content");
		    	highlightStirng = documentContent.substring(0, 200) + "...";
		    }else {
		    	Text[] fragments = highlight.fragments();  
			    for(Text textFragment : fragments) {
			    	highlightStirng += textFragment.toString() + "... ";
			    }
		    }   
		    
		    articleDTO.setContentHighlight(highlightStirng);
		    retVal.add(articleDTO);
		}	
		return retVal;
	}
	
	private BoolQueryBuilder getQueryBuilder(ArrayList<SearchFieldDTO> searchFields) {
		BoolQueryBuilder qb = new BoolQueryBuilder();
		for(SearchFieldDTO searchField : searchFields) {
			if(searchField.getOperator().equals("must")) {
				if(searchField.getSearchType().equals("regular")) {
					qb.must(QueryBuilders.matchQuery(searchField.getField(), searchField.getSearchText()));
				}else {
					qb.must(QueryBuilders.matchPhraseQuery(searchField.getField(), searchField.getSearchText()));
				}
			}else if(searchField.getOperator().equals("mustnot")){
				if(searchField.getSearchType().equals("regular")) {
					qb.mustNot(QueryBuilders.matchQuery(searchField.getField(), searchField.getSearchText()));
				}else {
					qb.mustNot(QueryBuilders.matchPhraseQuery(searchField.getField(), searchField.getSearchText()));
				}
			}else {
				if(searchField.getSearchType().equals("regular")) {
					qb.should(QueryBuilders.matchQuery(searchField.getField(), searchField.getSearchText()));
				}else {
					qb.should(QueryBuilders.matchPhraseQuery(searchField.getField(), searchField.getSearchText()));
				}
			}
		}
		return qb;
	}
}
