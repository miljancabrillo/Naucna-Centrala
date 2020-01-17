package com.nc.registration_services;

import java.util.HashMap;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nc.dto.RegistrationDTO;
import com.nc.model.UserDetails;
import com.nc.repository.UserDetailsRepository;

@Service
public class RegisterUserService implements JavaDelegate {

	@Autowired
	UserDetailsRepository udRepository;
	
	@Autowired
	IdentityService identityService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		HashMap<String, Object> map = (HashMap<String, Object>) execution.getVariable("registrationData");
		ObjectMapper mapper = new ObjectMapper();
		RegistrationDTO rdto = mapper.convertValue(map, RegistrationDTO.class);
		
		UserDetails userDetails = new UserDetails(rdto);
		userDetails.setPassword(encoder.encode(rdto.getPassword()));
		udRepository.save(userDetails);
						
		User user = identityService.newUser(rdto.getUsername());
		user.setEmail(rdto.getEmail());
		user.setFirstName(rdto.getName());
		user.setLastName(rdto.getSurname());
		user.setPassword(rdto.getPassword());
		identityService.saveUser(user);
		
	}

}
