package com.nc.registration_services;

import java.util.HashMap;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.RegistrationDTO;

@Service
public class AddReviewerService implements JavaDelegate {

	@Autowired
	IdentityService identityService;
	
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
		}
	}

}
