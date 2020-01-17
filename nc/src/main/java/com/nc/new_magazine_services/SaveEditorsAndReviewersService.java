package com.nc.new_magazine_services;

import java.util.ArrayList;
import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.MagazineDTO;
import com.nc.dto.ScientificAreaEditoPairDTO;
import com.nc.dto.UserDetailsDTO;
import com.nc.model.Magazine;
import com.nc.model.UserDetails;
import com.nc.repository.MagazineRepository;
import com.nc.repository.ScientificAreaRepository;
import com.nc.repository.UserDetailsRepository;

@Service
public class SaveEditorsAndReviewersService implements JavaDelegate {

	@Autowired
	MagazineRepository magazineRepository;
	
	@Autowired
	UserDetailsRepository udRepository;
	
	@Autowired
	ScientificAreaRepository saRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = (HashMap<String, Object>) execution.getVariable("magazineData");
		ObjectMapper mapper = new ObjectMapper();
		MagazineDTO mdto = mapper.convertValue(map, MagazineDTO.class);
		
		long magazineId = (long) execution.getVariable("newMagazineId");
		
		Magazine magazine = magazineRepository.findOneById(magazineId);
		
		magazine.setReviewers(new ArrayList<UserDetails>());
		magazine.setScientificAreaEditorMap(new HashMap<>());
		
		for(UserDetailsDTO reviewerDTO : mdto.getReviewers()) {
			UserDetails reviewer = udRepository.findUserDetailsByUsername(reviewerDTO.getUsername());
			magazine.AddReviewer(reviewer);
		}
		
		for(ScientificAreaEditoPairDTO pair : mdto.getScientificAreaEditorList()) {
			magazine.addScientificAreaEditorPair(saRepository.findOneById(pair.getAreaId()), udRepository.findUserDetailsByUsername(pair.getUsername()));
		}
		
		magazineRepository.save(magazine);
		
	}

}
