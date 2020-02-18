package com.nc.elasticsearch_services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.lucene.queryparser.flexible.core.builders.QueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;
import com.nc.dto.SearchFieldDTO;
import com.nc.elasticsearch_model.ArticleIndexingUnit;
import com.nc.elasticsearch_repository.ArticleEsRepository;
import com.nc.repository.ArticleRepository;

@Service
public class ArticleSearchService {

	@Autowired 
	ElasticsearchTemplate esTemplate;
	
	@Autowired
	ArticleEsRepository articleEsRepostiory;
	
	@Autowired
	ArticleRepository articleDbRepository;
	
	@PostConstruct
	public void postConstruct() {
		esTemplate.putMapping(ArticleIndexingUnit.class);
	}

	public List<String> search(ArrayList<SearchFieldDTO> searchFields) {
		
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
		
		SearchQuery searchQuery = new NativeSearchQuery(qb);
		
		return esTemplate.queryForIds(searchQuery);
	}
}
