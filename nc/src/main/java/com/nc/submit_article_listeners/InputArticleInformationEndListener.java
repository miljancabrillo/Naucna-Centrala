package com.nc.submit_article_listeners;

import java.util.ArrayList;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

import com.nc.model.Coauthor;

@Service
public class InputArticleInformationEndListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution delegateExecution) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Coauthor> coauthors = new ArrayList<>();
		delegateExecution.setVariable("coauthors", coauthors);
		
	}

}
