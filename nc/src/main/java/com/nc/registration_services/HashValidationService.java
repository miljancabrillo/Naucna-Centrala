package com.nc.registration_services;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.HashDataDTO;

@SuppressWarnings("unchecked")
@Service
public class HashValidationService implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = (HashMap<String, Object>) execution.getVariable("hashData");
		ObjectMapper mapper = new ObjectMapper();
		HashDataDTO hashData = mapper.convertValue(map, HashDataDTO.class);
		String hashValue = (String) execution.getVariable("hashValue");
		
		if(hashData.getHash().equals(hashValue)) {
			execution.setVariable("hashStatus", "valid");

		}else {
			hashData.setErrorMessage("Invalid hash");
			execution.setVariable("hashData", mapper.convertValue(hashData, HashMap.class));
			execution.setVariable("hashStatus", "invalid");

		}
	}

}
