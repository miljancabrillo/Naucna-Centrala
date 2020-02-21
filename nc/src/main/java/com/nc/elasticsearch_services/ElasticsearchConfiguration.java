package com.nc.elasticsearch_services;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.elasticsearch_model.ReviewerIndexingUnit;

@Configuration
public class ElasticsearchConfiguration {

	@Autowired
	RestHighLevelClient client;
	
	@PostConstruct
	public void postConstruct() throws IOException {
		GetIndexRequest getIndexrequest = new GetIndexRequest("articles_index"); 
		
		if(!client.indices().exists(getIndexrequest, RequestOptions.DEFAULT)) {
			CreateIndexRequest createIndexRequest = new CreateIndexRequest("articles_index"); 
			client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
			
			 PutMappingRequest mappingRequest = new PutMappingRequest("articles_index");
			 
			 XContentBuilder builder = XContentFactory.jsonBuilder();
			 builder.startObject();
			 {
			     builder.startObject("properties");
			     {
			         builder.startObject("coauthors");
			         {
			             builder.field("type", "text");
			             builder.field("store", "true");
			             builder.field("analyzer", "serbian");
			         }
			         builder.endObject();
			         
			         builder.startObject("content");
			         {
			             builder.field("type", "text");
			             builder.field("store", "true");
			             builder.field("analyzer", "serbian");
			         }
			         builder.endObject();
			         
			         builder.startObject("id");
			         {
			             builder.field("type", "keyword");
			         }
			         builder.endObject();
			         
			         builder.startObject("keyWords");
			         {
			             builder.field("type", "text");
			             builder.field("store", "true");
			             builder.field("analyzer", "serbian");
			         }
			         builder.endObject();
			         
			         builder.startObject("magazineName");
			         {
			             builder.field("type", "text");
			             builder.field("store", "true");
			             builder.field("analyzer", "serbian");
			         }
			         builder.endObject();
			         
			         builder.startObject("scientificArea");
			         {
			             builder.field("type", "text");
			             builder.field("store", "true");
			             builder.field("analyzer", "serbian");
			         }
			         builder.endObject();
			         
			         builder.startObject("title");
			         {
			             builder.field("type", "text");
			             builder.field("store", "true");
			             builder.field("analyzer", "serbian");
			         }
			         builder.endObject();
			     }
			     builder.endObject();
			 }
			 builder.endObject();
			 mappingRequest.source(builder); 
			 client.indices().putMapping(mappingRequest, RequestOptions.DEFAULT);
		}
		
		GetIndexRequest getIndexrequestTwo = new GetIndexRequest("reviewers_index"); 
		
		if(!client.indices().exists(getIndexrequestTwo, RequestOptions.DEFAULT)) {
			CreateIndexRequest createIndexRequest = new CreateIndexRequest("reviewers_index"); 
			client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
			
			 PutMappingRequest mappingRequest = new PutMappingRequest("reviewers_index");
			 
			 XContentBuilder builder = XContentFactory.jsonBuilder();
			 builder.startObject();
			 {
			     builder.startObject("properties");
			     {
			            
			         builder.startObject("username");
			         {
			             builder.field("type", "keyword");
			         }
			         builder.endObject();
			            
			         builder.startObject("location");
			         {
			             builder.field("type", "geo_point");
			         }
			         builder.endObject();
			     }
			     builder.endObject();
			 }
			 builder.endObject();
			 mappingRequest.source(builder); 
			 client.indices().putMapping(mappingRequest, RequestOptions.DEFAULT);
			 
			 addToReviewersIndex(new ReviewerIndexingUnit("rev1", (float)45.267136, (float)19.833549));
			 addToReviewersIndex(new ReviewerIndexingUnit("rev2", (float)40.7128, (float)-74.0060));
			 addToReviewersIndex(new ReviewerIndexingUnit("rev3", (float)44.7866, (float)20.4489));
			 addToReviewersIndex(new ReviewerIndexingUnit("rev4", (float)48.2082, (float)16.3738));
			 addToReviewersIndex(new ReviewerIndexingUnit("rev5", (float)45.7733, (float)19.1151));
			 addToReviewersIndex(new ReviewerIndexingUnit("rev6", (float)55.7558, (float)37.6173));


		}
	}	
	
	private void addToReviewersIndex(ReviewerIndexingUnit riu) {
		IndexRequest indexRequest = new IndexRequest("reviewers_index");
		indexRequest.id(riu.getUsername());
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			indexRequest.source(mapper.writeValueAsString(riu), XContentType.JSON);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			client.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
