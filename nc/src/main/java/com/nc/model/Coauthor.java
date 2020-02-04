package com.nc.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coauthor implements Serializable{

	
	private static final long serialVersionUID = 2474821520434628106L;
	
	private String name;
	private String surname;
	private String city;
	private String country;
	private String email;
	
}
