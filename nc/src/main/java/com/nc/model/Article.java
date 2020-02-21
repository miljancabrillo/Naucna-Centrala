package com.nc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	@Lob
	private ArrayList<Coauthor> coauthors;
	
	private String keyWords;
	
	private String articleAbstract;

	private String scientificArea;
	
	private String pdfFileName;
	
	@ManyToOne
	private Magazine magazine;
	
	@ManyToOne
	private UserDetails author;
	
	@ManyToMany
	private List<UserDetails> reviewers;
	
}
