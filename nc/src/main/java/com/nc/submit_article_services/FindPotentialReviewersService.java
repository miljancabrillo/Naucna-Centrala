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
		
		String selectedAreaId = execution.getVariable("scientificArea").toString();
		boolean areaReveiwerFound = false;
		
		HashMap<String, String> reviewersMap = new HashMap<>();
	
		for (UserDetails reviewer : magazine.getReviewers()) {
			if(reviewer.isReviewerOf(selectedAreaId)) areaReveiwerFound = true;
			reviewersMap.put(reviewer.getUsername(), reviewer.getName() + " " + reviewer.getSurname());
		}
		
		if(areaReveiwerFound) {
			execution.setVariable("potentialReviewers", reviewersMap);
			execution.setVariable("reviewersFound", true);
		}else {
			execution.setVariable("reviewersFound", false);
		}
		
	}

}
