package com.nc.elasticsearch_model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewerIndexingUnit {

	private String username;
	private ArrayList<Float> location = new ArrayList<>();
	
	public ReviewerIndexingUnit(String username, float lat, float lon) {
		this.username = username;
		this.location.add(lat);
		this.location.add(lon);
	}
	
}
