package com.nc.submit_article_listeners;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class SelectOneReviwerComplete implements TaskListener {

	@Override
	public void notify(DelegateTask execution) {
		// TODO Auto-generated method stub

		String reviewerId = execution.getVariable("reviewerId").toString();
		String reviewerId2 = execution.getVariable("reviewerId2").toString();
		
		reviewerId = reviewerId2;
		
		execution.setVariable("reviewerId", reviewerId);
		execution.removeVariable("reviewerId2");

		
	}

}
