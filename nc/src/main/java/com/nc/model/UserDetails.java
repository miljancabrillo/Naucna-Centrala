package com.nc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.nc.dto.RegistrationDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8082450395082557967L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//id camunda usera
	private String username;
	private String name;
	private String surname;
	private String city;
	private String country;
	private String title;
	private String email;
	private boolean isAssignedAsEditor = false;
	
	@ManyToMany
	private List<ScientificArea> scientificAreas;
	
	private String password;
	
	public UserDetails(RegistrationDTO rdto) {
		this.username = rdto.getUsername();
		this.name = rdto.getName();
		this.surname = rdto.getSurname();
		this.city = rdto.getCity();
		this.country = rdto.getCountry();
		this.title = rdto.getTitle();
		this.email = rdto.getEmail();
		this.scientificAreas = rdto.getScientificAreas();
	}

	@Override
	public boolean equals(Object obj) {
		if(this.id != ((UserDetails)obj).getId()) return false;
		return true;
	}
	
	public boolean isReviewerOf(ArrayList<ScientificArea> scientificAreas) {
		for (ScientificArea scientificArea : scientificAreas) {
			if(this.scientificAreas.contains(scientificArea)) return true;
		}
		return false;
	}
	
}
