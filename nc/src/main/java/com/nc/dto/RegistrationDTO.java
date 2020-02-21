package com.nc.dto;

import java.util.ArrayList;

import com.nc.model.ScientificArea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
	
	private String name;
	private String surname;
	private String city;
	private String country;
	private String title;
	private String email;
	private ArrayList<ScientificArea> scientificAreas;
	private String username;
	private String password;
	private boolean reviewerCandidate;
	private String errorMessage;
	private float lon;
	private float lat;
	
}
