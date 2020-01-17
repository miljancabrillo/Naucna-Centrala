package com.nc.controller;

import java.util.ArrayList;
import java.util.List;


import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nc.dto.ProcessVariableDTO;
import com.nc.dto.TaskDTO;

@RestController
@RequestMapping("/registration")
@SuppressWarnings("rawtypes")
public class RegistrationController {
	@Autowired
	IdentityService identityService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@GetMapping(produces = "text/plain")
    public String startRegistrationProcess() {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("registration");	
        return pi.getId();
    }
	
	@GetMapping(path = "/tasksByProcess/{processInstanceId}", produces = "application/json")
    public ResponseEntity<List<TaskDTO>> getTasksByPorcess(@PathVariable String processInstanceId) {
		
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskDTO> dtos = new ArrayList<TaskDTO>();
		for (Task task : tasks) {
			TaskDTO t = new TaskDTO(task.getId(), task.getName(), task.getAssignee(), task.getTaskDefinitionKey());
			dtos.add(t);
		}
		
        return new ResponseEntity<List<TaskDTO>>(dtos,  HttpStatus.OK);
    }
	
	@GetMapping(path = "/unclaimedTasksByProcess/{processInstanceId}", produces = "application/json")
    public ResponseEntity<List<TaskDTO>> getUnclaimedTasksByPorcess(@PathVariable String processInstanceId) {
		
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskDTO> dtos = new ArrayList<TaskDTO>();
		for (Task task : tasks) {
			if(task.getAssignee() != null) continue;
			TaskDTO t = new TaskDTO(task.getId(), task.getName(), task.getAssignee(), task.getTaskDefinitionKey());
			dtos.add(t);
		}
		
        return new ResponseEntity<List<TaskDTO>>(dtos,  HttpStatus.OK);
    }
	
	@PostMapping(path = "/setProcessVariable/{taskId}", produces = "application/json")
    public  ResponseEntity setProcessVariable(@RequestBody ProcessVariableDTO processVariableDTO, @PathVariable String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		
		runtimeService.setVariable(processInstanceId, processVariableDTO.getName(), processVariableDTO.getData());

        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@GetMapping(path = "/getProcessVariable/{taskId}/{variableName}", produces = "application/json")
    public  ResponseEntity getProcessVariable(@PathVariable String taskId, @PathVariable String variableName) {
		
		if(variableName.equals("hashValue")) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		
		Object variable = runtimeService.getVariable(processInstanceId, variableName);

        return new ResponseEntity<>(variable,HttpStatus.OK);
    }
	
	@PostMapping(path = "/claimRegistrationTask/{taskId}")
    public @ResponseBody ResponseEntity claim(@PathVariable String taskId) {
		taskService.claim(taskId, "guest");
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/completeRegistationTask/{taskId}")
    public @ResponseBody ResponseEntity complete(@PathVariable String taskId) {
		taskService.complete(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
