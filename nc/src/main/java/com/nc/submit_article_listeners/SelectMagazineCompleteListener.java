package com.nc.submit_article_listeners;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Magazine;
import com.nc.repository.MagazineRepository;

@Service
public class SelectMagazineCompleteListener implements TaskListener {
	
	@Autowired
	MagazineRepository magazineRepository;

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		long selectedMagazineId = Long.parseLong(delegateTask.getExecution().getVariable("selectedMagazineId").toString());
		Magazine magazine = magazineRepository.findOneById(selectedMagazineId);
		delegateTask.getExecution().setVariable("isOpenAccess", magazine.isOpenAccess());
		
	}

}
