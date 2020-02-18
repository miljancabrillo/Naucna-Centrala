package com.nc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchFieldDTO {

	private String operator;
	private String field;
	private String searchType;
	private String searchText;
	
}
