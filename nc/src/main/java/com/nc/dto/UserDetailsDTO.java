package com.nc.dto;

import com.nc.model.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

	private String username;
	private String name;
	private String surname;
	
	public UserDetailsDTO(UserDetails user) {
		this.name = user.getName();
		this.surname = user.getSurname();
		this.username = user.getUsername();
	}
	
}
