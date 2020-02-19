package com.nc.elasticsearch_services;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
	}
	
	
	
}
