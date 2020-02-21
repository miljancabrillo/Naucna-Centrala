package com.nc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.server.PathParam;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.FormFieldValidationConstraint;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;


import com.nc.dto.FormDTO;
import com.nc.dto.FormFieldDTO;
import com.nc.model.Review;
import com.nc.model.UserDetails;
import com.nc.repository.UserDetailsRepository;

import Decoder.BASE64Decoder;

@RestController
public class FormsController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	UserDetailsRepository udRepository;

	@SuppressWarnings("unchecked")
	@GetMapping("/form/{taskId}")
	public ResponseEntity<FormDTO> getFormFileds(@PathVariable("taskId") String taskId) throws IOException{
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskFormData tfd = formService.getTaskFormData(taskId);
		List<FormField> formFields = tfd.getFormFields();
		ArrayList<FormFieldDTO> formFieldsDTO = new ArrayList<>();
		
		for(FormField field : formFields) {
			FormFieldDTO fieldDTO = new FormFieldDTO(field);
			
			//pronalazak tipa polja
			String type = "";
			if(field.getProperties().get("type") != null) {
				type = field.getProperties().get("type");
			}else {
				type = field.getTypeName();
			}
			fieldDTO.setType(type);
			
		
			//ubacivanje enum vrijednosti 
			if(type.equals("enum") || type.equals("multiselect")) {
				if(field.getProperties().get("selectValues") != null) {
					HashMap<String, String> selectValues = (HashMap<String, String>) runtimeService.getVariable(task.getProcessInstanceId(), field.getProperties().get("selectValues"));
					fieldDTO.setSelectValues(selectValues);
				}else{
					HashMap<String, String> selectValues = parseSelectValues(field.getType().getInformation("values").toString());
					fieldDTO.setSelectValues(selectValues);
				}
				
			}
			
			//ako treba prikazati vrijednost u polju
			if(field.getProperties().containsKey("fillValue")) {
				fieldDTO.setValue(runtimeService.getVariable(task.getProcessInstanceId(), field.getId()).toString());
			}
			
			//ako treba da je polje readonly
			if(field.getProperties().containsKey("readonly")) {
				fieldDTO.setReadonly(true);
			}
			
			//ako je tip polja link za download pdfa
			if(type.equals  ("downloadBtn")) {
				fieldDTO.setLinkText(field.getProperties().get("downloadBtnDescription"));
				String fileName = (String) runtimeService.getVariable(task.getProcessInstanceId(), "fileName");
				
				File file = new File("pdf/"+fileName);
				byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
				String startString = "data:application/pdf;base64,";
				String dataStrign = new String(encoded, StandardCharsets.US_ASCII);
				fieldDTO.setValue(startString + StringUtils.remove(dataStrign, "data/application/pdf/base64/"));	
			}
			
			if(type.equals("reviews")) {
				ArrayList<Review> reviews = (ArrayList<Review>) runtimeService.getVariable(task.getProcessInstanceId(), field.getProperties().get("reviewsList"));
				fieldDTO.setReviews(reviews);
			}
			
			if(type.equals("link")) {
				fieldDTO.setLinkText(field.getProperties().get("linkText"));
				fieldDTO.setLinkUrl(runtimeService.getVariable(task.getProcessInstanceId(), field.getProperties().get("linkValue")).toString());
			}
			
			formFieldsDTO.add(fieldDTO);
		}
		
		FormDTO formDTO = new FormDTO();
		formDTO.setFields(formFieldsDTO);
		formDTO.setTaskId(taskId);
		
		return new ResponseEntity<>(formDTO, HttpStatus.OK);
	}
	
	@PostMapping("/form/{taskId}")
	public ResponseEntity<String> postFormFileds(@PathVariable("taskId") String taskId, @RequestBody FormDTO formDTO) throws IOException{

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskFormData tfd = formService.getTaskFormData(taskId);
		List<FormField> formFields = tfd.getFormFields();
		
		//provjera ispravnosti polja
		for (FormField field : formFields) {
			FormFieldDTO fieldDTO = formDTO.getFieldById(field.getId());

			//validacija po constraintovima
			List<FormFieldValidationConstraint> constraints = field.getValidationConstraints();
			for (FormFieldValidationConstraint constraint : constraints) {
				if(constraint.getName().equals("required") && !fieldDTO.getType().equals("multiselect")) {
					if(fieldDTO.getValue() == null || fieldDTO.getValue().equals("")) {
						return new ResponseEntity<>(fieldDTO.getLabel() + " field is mandatory!",HttpStatus.BAD_REQUEST);
					}
				}else if(constraint.getName().equals("required") && fieldDTO.getType().equals("multiselect")) {
					if(fieldDTO.getMultiselectValues() == null)	return new ResponseEntity<>(fieldDTO.getLabel() + " field is mandatory!",HttpStatus.BAD_REQUEST);
				}
			}
			
			//validacija po tipu
			if(fieldDTO.getType().equals("long")){
				try {
					Long.parseLong(fieldDTO.getValue());
				} catch (Exception e) {
					// TODO: handle exception
					return new ResponseEntity<>(fieldDTO.getLabel() + " field must be number!",HttpStatus.BAD_REQUEST);
				}
			}
			
			if(fieldDTO.getType().equals("multiselect")){
				int selectConstraint = Integer.parseInt(field.getProperties().get("minSelected"));
				if(selectConstraint > fieldDTO.getMultiselectValues().size()) return new ResponseEntity<>("Select at least " + selectConstraint + " elements in multiselect!"
						,HttpStatus.BAD_REQUEST);				
			}
			
			if(fieldDTO.getType().endsWith("reviews")) {
				if(fieldDTO.getReviews().get(0).isForEditor() == true) continue;
				for(Review review : fieldDTO.getReviews()) {
					if(review.getAuthorReply() == null || review.getAuthorReply().equals("")) return new ResponseEntity<>("Reply to all reviews!",HttpStatus.BAD_REQUEST);				
				}

			}
			
			if(fieldDTO.getType().equals("email")) {
				if(!isMailValid(fieldDTO.getValue())) return new ResponseEntity<>("Invalid email format!",HttpStatus.BAD_REQUEST);				
			}
			
			if(fieldDTO.getType().equals("duration")) {
				if(!isDurationValid(fieldDTO.getValue())) return new ResponseEntity<>("Invalid duration format!",HttpStatus.BAD_REQUEST);				
			}
			
		}
		
		//ako su sva polja uredu ubacujem ih u varijable ako je fajl samo ime ubacim
		for (FormField field : formFields) {
			FormFieldDTO fieldDTO = formDTO.getFieldById(field.getId());
			
			if(fieldDTO.getType().equals("file")) {
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] decodedBytes = decoder.decodeBuffer(fieldDTO.getValue());

				File file = new File("pdf/" + fieldDTO.getFileName());;
				FileOutputStream fop = new FileOutputStream(file);

				fop.write(decodedBytes);
				fop.flush();
				fop.close();
				
				runtimeService.setVariable(task.getProcessInstanceId(), "fileName", fieldDTO.getFileName());
			}else if(fieldDTO.getType().equals("multiselect")) {
				runtimeService.setVariable(task.getProcessInstanceId(), fieldDTO.getId(), fieldDTO.getMultiselectValues());
			}else if(fieldDTO.getType().equals("reviews")) {
				if(fieldDTO.getReviews().get(0).isForEditor() == true) continue;
				runtimeService.setVariable(task.getProcessInstanceId(), "reviewsForAuthor", fieldDTO.getReviews());
				fieldDTO.changeReviewsToEditors();
				runtimeService.setVariable(task.getProcessInstanceId(), "reviews", fieldDTO.getReviews());				
			}else if(fieldDTO.getType().equals("location")) {
				runtimeService.setVariable(task.getProcessInstanceId(), "lon", fieldDTO.getLon());
				runtimeService.setVariable(task.getProcessInstanceId(), "lat", fieldDTO.getLat());	
			} else if(!fieldDTO.getType().equals("downloadBtn") && !fieldDTO.getType().equals("link") && !fieldDTO.getType().equals("filter") && !fieldDTO.getType().equals("filterGeo") && !fieldDTO.getType().equals("filterMore")) {
				runtimeService.setVariable(task.getProcessInstanceId(), fieldDTO.getId(), fieldDTO.getValue());
			}
		}
		
		return new ResponseEntity<>("",HttpStatus.OK);
	}
	
	@SuppressWarnings("deprecation")
	private HashMap<String, String> parseSelectValues(String values){
		
		HashMap<String, String> map = new HashMap<>();
		
		values = StringUtils.getNestedString(values, "{", "}");
		String[] parts = StringUtils.splitByWholeSeparator(values, ", ");
		
		for (String part : parts) {
			String[] partsTwo = StringUtils.split(part, "=");
			map.put(partsTwo[0], partsTwo[1]);
		}
		
		return map;
	}
	
	@GetMapping("/doSubscription")
	public void subscription(@PathParam("taskId") String taskId, @PathParam("status") String status) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		runtimeService.setVariable(task.getProcessInstanceId(), "membershipPaymentStatus", status);
		taskService.complete(taskId);
	}
	
	@PostMapping("/form/filterReviewers/{taskId}")
	public ResponseEntity<FormDTO> filterReviewers(@PathVariable("taskId") String taskId, @RequestBody FormDTO formDTO){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String scinetificAreaId = runtimeService.getVariable(task.getProcessInstanceId(), "scientificArea").toString();
		
		HashMap<String, String> filteredReviewers = new HashMap<>();
		
		FormFieldDTO field = formDTO.getFieldById("selectedReviewers");
		for(HashMap.Entry<String,String> entry : field.getSelectValues().entrySet()){
			UserDetails reviewer = udRepository.findUserDetailsByUsername(entry.getKey());
			if(reviewer.isReviewerOf(scinetificAreaId)) {
				filteredReviewers.put(entry.getKey(), entry.getValue());
			}
		}
		
		field.setSelectValues(filteredReviewers);

		return new ResponseEntity<>(formDTO, HttpStatus.OK);
	}
	
	private boolean isMailValid(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
		 
	}
	
	private boolean isDurationValid(String duration) {
		String regex = "^P(?!$)(\\d+Y)?(\\d+M)?(\\d+W)?(\\d+D)?(T(?=\\d)(\\d+H)?(\\d+M)?(\\d+S)?)?$";
		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(duration);
		return matcher.matches();
		 
	}

}
