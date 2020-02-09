package com.nc.submit_article_listeners;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class InputPaymentDataCreateListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub

		String url = delegateTask.getExecution().getVariable("url").toString();
		url = url + delegateTask.getId();
		delegateTask.getExecution().setVariable("url", url);
		
	}

}
