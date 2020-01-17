package com.nc.registration_services;

import java.util.HashMap;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.RegistrationDTO;

@SuppressWarnings("unchecked")
@Service
public class DataValidationService implements JavaDelegate{
	
	@Autowired
	IdentityService identityService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		//preuzimanje procesne varijable
		HashMap<String, Object> map = (HashMap<String, Object>) execution.getVariable("registrationData");
		ObjectMapper mapper = new ObjectMapper();
		RegistrationDTO rdto = mapper.convertValue(map, RegistrationDTO.class);
		
		String dataStatus = "valid";
		
		if(rdto.getName().equals("")) {
			dataStatus = "invalid";
			rdto.setErrorMessage("Field name is required!");
		}
		if(rdto.getSurname().equals("")) {
			dataStatus = "invalid";
			rdto.setErrorMessage("Field surname is required!");
		}
		
		if(rdto.getUsername().equals("")) {
			dataStatus = "invalid";
			rdto.setErrorMessage("Field username is required!");
		}else {
			User user = identityService.createUserQuery().userId(rdto.getUsername()).singleResult();
			if(user != null) {
				dataStatus = "invalid";
				rdto.setErrorMessage("Select another username!");
			}
		}
		
		if(rdto.getPassword().equals("")) {
			dataStatus = "invalid";
			rdto.setErrorMessage("Field password is required!");
		}
		if(rdto.getCity().equals("")) {
			dataStatus = "invalid";
			rdto.setErrorMessage("Field city is required!");
		}
		if(rdto.getCountry().equals("")) {
			dataStatus = "invalid";
			rdto.setErrorMessage("Field country is required!");
		}
		if(rdto.getEmail().equals("")) {
			dataStatus = "invalid";
			rdto.setErrorMessage("Field email is required!");
		}
		if(rdto.getScientificAreas().size() == 0) {
			dataStatus = "invalid";
			rdto.setErrorMessage("Select at least one scientific area!");
		}
		
		//postavljanje varijabli
		execution.setVariable("registrationData", mapper.convertValue(rdto, HashMap.class));
		execution.setVariable("dataStatus", dataStatus);
		execution.setVariable("reviewer", rdto.isReviewerCandidate());
	}

}
