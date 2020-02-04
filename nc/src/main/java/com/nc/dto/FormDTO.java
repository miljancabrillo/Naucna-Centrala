package com.nc.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormDTO {

	private String taskId;
	private ArrayList<FormFieldDTO> fields;
	private String variableName;
	
	public FormFieldDTO getFieldById(String id) {
		for (FormFieldDTO field : fields) {
			if(field.getId().equals(id)) return field;
		}
		return null;
	}
	
}
