package com.nc.submit_article_listeners;

import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import com.nc.model.Coauthor;

@Service
public class InputCoauthorDataCompleteListener implements TaskListener{

	@SuppressWarnings("unchecked")
	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		Coauthor coauthor = new Coauthor();
		coauthor.setName(delegateTask.getExecution().getVariable("coauthorName").toString());
		coauthor.setSurname(delegateTask.getExecution().getVariable("coauthorSurname").toString());
		coauthor.setCity(delegateTask.getExecution().getVariable("coauthorCity").toString());
		coauthor.setCountry(delegateTask.getExecution().getVariable("coauthorCountry").toString());	
		coauthor.setEmail(delegateTask.getExecution().getVariable("coauthorEmail").toString());
		
		ArrayList<Coauthor> coauthors = (ArrayList<Coauthor>) delegateTask.getExecution().getVariable("coauthors");
		
		coauthors.add(coauthor);
		
		delegateTask.getExecution().setVariable("coauthors", coauthors);

	}

}
