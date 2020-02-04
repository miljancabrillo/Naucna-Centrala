package com.nc.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {

	private long id;
	private String title;
	private String magazineName;
	private ArrayList<String> authors;
	private float price;
	private long sellerKpId;
	private boolean bought;
	
}
