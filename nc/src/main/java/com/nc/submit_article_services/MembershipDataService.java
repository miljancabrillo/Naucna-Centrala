package com.nc.submit_article_services;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Membership;
import com.nc.repository.MembershipRepository;

@Service
public class MembershipDataService implements JavaDelegate {
	
	@Autowired 
	MembershipRepository memRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String username = execution.getVariable("AuthorId").toString();
		long magazineId = Long.parseLong(execution.getVariable("selectedMagazineId").toString());
		
		List<Membership> mem = memRepository.findByUsernameAndMagazineId(username, magazineId);
		
		if(mem == null) {
			execution.setVariable("hasMembership", false);
			return;
		}
		String status = "expired";
		for (Membership membership : mem) {
			if(membership.getStatus().equals("paid")) status = "paid";
		}
		
		if(status.equals("expired")) {
			execution.setVariable("hasMembership", false);
		}else {
			execution.setVariable("hasMembership", true);
		}
		execution.setVariable("membershipPaymentStatus2", "");
	}

}
