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

		List<User> editors = (List<User>) identityService.createUserQuery().memberOfGroup("editor").list();
		
		ArrayList<String> usernames = new ArrayList<>();
		
		for (User editor : editors) {
			usernames.add(editor.getId());
		}
		
		List<UserDetails> editorsDetails = udRepository.findByUsernames(usernames);
		List<UserDetailsDTO> editorsDTO = new ArrayList<>();
		
		for (UserDetails editor : editorsDetails) {
			editorsDTO.add(new UserDetailsDTO(editor));
		}
		
		return new ResponseEntity<>(editorsDTO, HttpStatus.OK);

	}
	
	@PostMapping("/reviewers")
	public ResponseEntity<List<UserDetailsDTO>> getReviewers(){
				
		List<User> reviewers = (List<User>) identityService.createUserQuery().memberOfGroup("reviewer").list();
		
		ArrayList<String> usernames = new ArrayList<>();
		
		for (User reviewer : reviewers) {
			usernames.add(reviewer.getId());
		}
		
		List<UserDetails> reviewersDetails = udRepository.findByUsernames(usernames);
		List<UserDetailsDTO> reviewersDTO = new ArrayList<>();
		
		for (UserDetails reviewer : reviewersDetails) {
			reviewersDTO.add(new UserDetailsDTO(reviewer));
		}
		
		return new ResponseEntity<>(reviewersDTO, HttpStatus.OK);

		
	}
	
}
