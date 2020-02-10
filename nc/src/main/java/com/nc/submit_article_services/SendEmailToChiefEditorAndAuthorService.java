package com.nc.submit_article_services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.UserDetails;
import com.nc.repository.MagazineRepository;
import com.nc.repository.UserDetailsRepository;
import com.nc.services.MailService;

@Service
public class SendEmailToChiefEditorAndAuthorService implements JavaDelegate {

	@Autowired
	MailService mailService;
	
	@Autowired
	MagazineRepository magazineRepository;
	
	@Autowired
	UserDetailsRepository udRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String authorUsername = (String) execution.getVariable("AuthorId");
		long magazineId = Long.parseLong(execution.getVariable("selectedMagazineId").toString());
		
		UserDetails editor = magazineRepository.findOneById(magazineId).getCheifEditor();
		UserDetails author = udRepository.findUserDetailsByUsername(authorUsername);
		
		execution.setVariable("chiefEditorId", editor.getUsername());
		//mailService.sendMail("You have new atricle submitet to you magazine!", editor.getEmail(),"Article submit");
		//mailService.sendMail("Your article has been submited succesfully!", author.getEmail(), "Article submit");
		
	}

}
