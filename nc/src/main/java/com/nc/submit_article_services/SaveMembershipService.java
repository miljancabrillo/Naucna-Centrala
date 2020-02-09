package com.nc.submit_article_services;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.Membership;
import com.nc.repository.MembershipRepository;

@Service
public class SaveMembershipService implements JavaDelegate {

	@Autowired
	MembershipRepository memRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub

		long memId = Long.parseLong(execution.getVariable("memId").toString());
		Membership mem = memRepository.findOneById(memId);
		String status = execution.getVariable("membershipPaymentStatus").toString();
		mem.setStatus(status);
		memRepository.save(mem);
		
	}

}
