package com.nc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6243221980836471937L;

	@Id
	@GeneratedValue
	private long id;
	
	@JsonIgnore
	@ManyToOne
	UserDetails reviewer;
	
	private String review;
	private String status;
	private String commentForEditor;
	
	private String fileName;
	
	private String authorReply;
	private boolean forEditor;
	
	
}
