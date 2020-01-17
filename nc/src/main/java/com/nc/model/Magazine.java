package com.nc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Magazine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private long ISSN;
	@ManyToMany
	private List<ScientificArea> scientificAreas;
	private boolean openAccess;
	@OneToOne
	private UserDetails cheifEditor;
	@Lob
	private HashMap<ScientificArea, UserDetails> scientificAreaEditorMap = new HashMap<>();
	@ManyToMany
	private List<UserDetails> reviewers = new ArrayList<UserDetails>();
	private boolean active = false;
	
	public void AddReviewer(UserDetails reviewer) {
		this.reviewers.add(reviewer);
	}
	
	public void addScientificAreaEditorPair(ScientificArea area, UserDetails editor) {
		this.scientificAreaEditorMap.put(area, editor);
	}
	
}
