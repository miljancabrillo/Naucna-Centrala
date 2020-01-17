package com.nc.controller;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nc.dto.UserDetailsDTO;
import com.nc.model.UserDetails;
import com.nc.repository.UserDetailsRepository;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserDetailsRepository udRepository;
	
	@Autowired
	IdentityService identityService;
	
	@GetMapping("/editors")
	public ResponseEntity<List<UserDetailsDTO>> getEditors(){
		List<UserDetails> users = udRepository.findAll();
		List<UserDetailsDTO> list = new ArrayList<>();

		for(UserDetails user : users) {
			list.add(new UserDetailsDTO(user));
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
	
	@GetMapping("/reviewers")
	public ResponseEntity<List<UserDetailsDTO>> getReviewers(){
		
		List<UserDetails> users = udRepository.findAll();
		List<UserDetailsDTO> list = new ArrayList<>();

		for(UserDetails user : users) {
			list.add(new UserDetailsDTO(user));
		}
		
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> tesst(){
	 return new ResponseEntity<>(new BCryptPasswordEncoder().encode("admin"), HttpStatus.OK);
		
	}
	
}
