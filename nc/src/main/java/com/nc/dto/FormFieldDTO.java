package com.nc.dto;

import java.util.ArrayList;
import java.util.HashMap;

import org.camunda.bpm.engine.form.FormField;

import com.nc.model.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormFieldDTO {

	private String id;
	private String type;
	private String label;
	private String value;
	private HashMap<String, String> selectValues;
	private String fileName;
	private boolean readonly = false;
	private String linkText;
	private ArrayList<String> multiselectValues;
	private ArrayList<Review> reviews;
	private String linkUrl;
	private float lon;
	private float lat;
	
	
	public FormFieldDTO(FormField field) {
		
		this.id = field.getId();
		this.type = field.getTypeName();
		this.label = field.getLabel();
				
	}
	
	public void changeReviewsToEditors() {
		for (Review review : reviews) {
			review.setForEditor(true);
		}
	}

}
