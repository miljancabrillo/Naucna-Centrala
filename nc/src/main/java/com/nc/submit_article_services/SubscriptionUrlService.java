package com.nc.submit_article_services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Magazine;
import com.nc.model.Membership;
import com.nc.model.UserDetails;
import com.nc.repository.MagazineRepository;
import com.nc.repository.MembershipRepository;
import com.nc.repository.UserDetailsRepository;

@Service
public class SubscriptionUrlService implements JavaDelegate {

	@Autowired
	UserDetailsRepository udRepository;
	
	@Autowired
	MembershipRepository memRepository;
	
	@Autowired
	MagazineRepository magRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String username = execution.getVariable("InitiatorId").toString();
		long magazineId = Long.parseLong(execution.getVariable("magazineId").toString());
		
		UserDetails user = udRepository.findUserDetailsByUsername(username);
		Magazine mag = magRepository.findOneById(magazineId);
		
		Membership mem = new Membership();
		
		mem.setUser(user);
		mem.setMagazine(mag);
		mem.setStatus("created");
		
		mem = memRepository.save(mem);
		
		execution.setVariable("memId", mem.getId());
		
		String url = "http://localhost:8080/subscription.html?taskId=";
		
		execution.setVariable("url", url);
	}

}
