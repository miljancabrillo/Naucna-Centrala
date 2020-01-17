package com.nc.controller;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nc.dto.UserDetailsDTO;
import com.nc.model.ScientificArea;
import com.nc.model.UserDetails;
import com.nc.repository.UserDetailsRepository;

@RestController
@RequestMapping("/users")
@SuppressWarnings("unchecked")
public class UsersController {

	@Autowired
	UserDetailsRepository udRepository;
	
	@Autowired
	IdentityService identityService;
	
	@GetMapping("/editors")
	public ResponseEntity<List<UserDetailsDTO>> getEditors(){

		List<UserDetails> editors = udRepository.findByNameContainingIgnoreCase("edi");
		List<UserDetailsDTO> editorsDTO = new ArrayList<>();
		
		for (UserDetails editor : editors) {
			editorsDTO.add(new UserDetailsDTO(editor));
		}
		
		return new ResponseEntity<>(editorsDTO, HttpStatus.OK);

	}
	
	@PostMapping("/reviewers")
	public ResponseEntity<List<UserDetailsDTO>> getReviewers(){
				
		List<UserDetails> reviewers = udRepository.findByNameContainingIgnoreCase("re");
		List<UserDetailsDTO> reviewersDTO = new ArrayList<>();
		
		for (UserDetails reviewer : reviewers) {
			reviewersDTO.add(new UserDetailsDTO(reviewer));
		}
		
		return new ResponseEntity<>(reviewersDTO, HttpStatus.OK);
		
	}
	
}
