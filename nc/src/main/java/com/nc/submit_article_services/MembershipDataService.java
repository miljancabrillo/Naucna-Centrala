package com.nc.submit_article_services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Membership;
import com.nc.repository.MembershipRepository;
import com.nc.repository.UserDetailsRepository;

@Service
public class MembershipDataService implements JavaDelegate {
	
	@Autowired 
	MembershipRepository memRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String username = execution.getVariable("AuthorId").toString();
		long magazineId = Long.parseLong(execution.getVariable("selectedMagazineId").toString());
		
		Membership mem = memRepository.findByUsernameAndMagazineId(username, magazineId);
		
		if(mem == null || mem.getStatus().equals("expired")) {
			execution.setVariable("hasMembership", false);
		}else {
			execution.setVariable("hasMembership", true);
		}
		execution.setVariable("membershipPaymentStatus2", "");
	}

}
