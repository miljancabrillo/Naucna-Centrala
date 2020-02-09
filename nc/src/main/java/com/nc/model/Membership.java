package com.nc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Membership {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private UserDetails user;
	
	@ManyToOne
	private Magazine magazine;
	
	private Date validUntil;
	
	private String status;
	
}
