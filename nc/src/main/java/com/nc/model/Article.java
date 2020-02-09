package com.nc.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	
	@Lob
	private ArrayList<Coauthor> coauthors;
	
	private String keyWords;
	
	private String articleAbstract;

	private String scientificArea;
	
	private String pdfFileName;
	
}