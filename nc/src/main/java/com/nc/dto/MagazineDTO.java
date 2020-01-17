package com.nc.dto;

import java.util.List;

import com.nc.model.ScientificArea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MagazineDTO {

	private String name;
	private long ISSN;
	private List<ScientificArea> scientificAreas;
	private boolean openAccess;
	private List<ScientificAreaEditoPairDTO> scientificAreaEditorList;
	private List<UserDetailsDTO> reviewers;
	private String adminMessage;
	private boolean reviewed;
	
}
