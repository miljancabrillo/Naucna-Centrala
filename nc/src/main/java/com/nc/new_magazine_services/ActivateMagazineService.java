package com.nc.new_magazine_services;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.MagazineDTO;
import com.nc.model.Magazine;
import com.nc.repository.MagazineRepository;
import com.nc.repository.ScientificAreaRepository;
import com.nc.repository.UserDetailsRepository;

@Service
public class ActivateMagazineService implements JavaDelegate {

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
		
		magazine.setName(mdto.getName());
		magazine.setOpenAccess(mdto.isOpenAccess());
		magazine.setISSN(mdto.getISSN());
		magazine.setActive(true);
		
		magazineRepository.save(magazine);
	
	}

}
