package com.nc.submit_article_services;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Magazine;
import com.nc.model.UserDetails;
import com.nc.repository.MagazineRepository;

@Service
public class FindPotentialReviewersService implements JavaDelegate {

	@Autowired
	MagazineRepository magazineRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
	
		long magazineId = Long.parseLong(execution.getVariable("selectedMagazineId").toString());
		Magazine magazine = magazineRepository.findOneById(magazineId);
		
		HashMap<String, String> reviewersMap = new HashMap<>();
	
		for (UserDetails reviewer : magazine.getReviewers()) {
			reviewersMap.put(reviewer.getUsername(), reviewer.getName() + " " + reviewer.getSurname());
		}
		
		execution.setVariable("potentialReviewers", reviewersMap);
		
	}

}
