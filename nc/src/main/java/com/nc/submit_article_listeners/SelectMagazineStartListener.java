package com.nc.submit_article_listeners;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Magazine;
import com.nc.repository.MagazineRepository;

@Service
public class SelectMagazineStartListener implements TaskListener {

	@Autowired
	MagazineRepository magazineRepository;
	
	@Override
	public void notify(DelegateTask delegateTask) {
		
		HashMap<String, String> magaiznesMap = new HashMap<>();
		
		List<Magazine> magazines = magazineRepository.findAll();
		
		for (Magazine magazine : magazines) {
			magaiznesMap.put(Long.toString(magazine.getId()), magazine.getName());
		}
	
		delegateTask.getExecution().setVariable("magazinesHashMap", magaiznesMap);
	}

}
