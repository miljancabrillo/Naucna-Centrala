package com.nc.new_magazine_services;

import java.util.HashMap;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.MagazineDTO;
import com.nc.model.Magazine;
import com.nc.model.UserDetails;
import com.nc.repository.MagazineRepository;
import com.nc.repository.UserDetailsRepository;

@Service
public class AssignChiefEditorService implements JavaDelegate {

	@Autowired
	UserDetailsRepository udRepository;
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = (HashMap<String, Object>) execution.getVariable("magazineData");
		ObjectMapper mapper = new ObjectMapper();
		MagazineDTO mdto = mapper.convertValue(map, MagazineDTO.class);
		
		Magazine magazine = new Magazine();
		magazine.setName(mdto.getName());
		magazine.setISSN(mdto.getISSN());
		magazine.setOpenAccess(mdto.isOpenAccess());
		magazine.setScientificAreas(mdto.getScientificAreas());
		
		UserDetails chiefEditor = udRepository.findUserDetailsByUsername((String) execution.getVariable("initiatorId"));
		chiefEditor.setAssignedAsEditor(true);
		
		magazine.setCheifEditor(chiefEditor);
		
		udRepository.save(chiefEditor);
		
		magazine = magazineRepository.save(magazine);
		
		execution.setVariable("newMagazineId", magazine.getId());
	}

}
