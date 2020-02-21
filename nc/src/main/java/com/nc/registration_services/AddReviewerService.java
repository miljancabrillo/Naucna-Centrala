package com.nc.registration_services;

import java.io.IOException;
import java.util.HashMap;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.RegistrationDTO;
import com.nc.elasticsearch_model.ReviewerIndexingUnit;

@Service
public class AddReviewerService implements JavaDelegate {

	@Autowired
	IdentityService identityService;
	
	@Autowired
	RestHighLevelClient client;
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		boolean reviewerRequestSatus = (boolean) execution.getVariable("reviewerRequestSatus");
		
		HashMap<String, Object> map = (HashMap<String, Object>) execution.getVariable("registrationData");
		ObjectMapper mapper = new ObjectMapper();
		RegistrationDTO rdto = mapper.convertValue(map, RegistrationDTO.class);
		
		if(reviewerRequestSatus) {
			identityService.createMembership(rdto.getUsername(), "reviewer");	
			
			ReviewerIndexingUnit riu = new ReviewerIndexingUnit(rdto.getUsername(), rdto.getLat(), rdto.getLon());
			
			IndexRequest indexRequest = new IndexRequest("reviewers_index");
			indexRequest.id(riu.getUsername());
			
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

}
