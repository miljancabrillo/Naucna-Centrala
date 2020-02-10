package com.nc.submit_article_services;

import java.util.Collection;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.model.bpmn.instance.ExtensionElements;
import org.camunda.bpm.model.bpmn.instance.FlowElement;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperties;
import org.camunda.bpm.model.bpmn.instance.camunda.CamundaProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nc.model.UserDetails;
import com.nc.repository.UserDetailsRepository;
import com.nc.services.MailService;

@Service
public class GenericMailService implements JavaDelegate {

	@Autowired
	MailService mailService;
	
	@Autowired
	UserDetailsRepository udRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		FlowElement element = execution.getBpmnModelElementInstance();
		ExtensionElements extensionElements = element.getExtensionElements();
	
		Collection<CamundaProperty> properties = extensionElements .getElementsQuery()
			      .filterByType(CamundaProperties.class)
			      .singleResult()
			      .getCamundaProperties();
		
		String reciverId = "";
		String content = "";
		String subject = "";
		
		for (CamundaProperty property : properties) {
			if(property.getCamundaName().equals("reciverId")) {
				reciverId = property.getCamundaValue();
			}
			if(property.getCamundaName().equals("content")) {
				content = property.getCamundaValue();
			}
			if(property.getCamundaName().equals("subject")) {
				subject = property.getCamundaValue();
			}
		}
		
		UserDetails user = udRepository.findUserDetailsByUsername(execution.getVariable(reciverId).toString());
		//mailService.sendMail(content, user.getEmail(), subject);
	}
}
