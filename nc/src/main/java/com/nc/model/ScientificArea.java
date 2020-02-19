package com.nc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScientificArea implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2250352929537880971L;
	
	@Id
	@Column(unique=true,columnDefinition="VARCHAR(64)")
	private String id;
	
	private String name;
	
	@Override
	public boolean equals(Object obj) {
		if (this.id.equals(((ScientificArea)obj).getId())) return false;
		return true;
	}

	
	
}
