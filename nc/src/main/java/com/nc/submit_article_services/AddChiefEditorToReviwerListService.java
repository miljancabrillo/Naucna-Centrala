package com.nc.submit_article_services;

import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.repository.UserDetailsRepository;

@Service
public class AddChiefEditorToReviwerListService implements JavaDelegate {

	@Autowired
	UserDetailsRepository udRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		String editorUsername = execution.getVariable("chiefEditorId").toString();
		
		ArrayList<String> selectedReviwers = new ArrayList<>();
		selectedReviwers.add(editorUsername);
		
		execution.setVariable("selectedReviewers", selectedReviwers);

	}

}
