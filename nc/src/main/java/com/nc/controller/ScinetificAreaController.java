package com.nc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nc.model.ScientificArea;
import com.nc.repository.ScientificAreaRepository;

@RestController
public class ScinetificAreaController {

	@Autowired
	ScientificAreaRepository saRepository;
	
	@GetMapping(value="/scinetificAreas")
	public ResponseEntity<List<ScientificArea>> getAllScinetificAreas(){
		return  new ResponseEntity<>(saRepository.findAll(), HttpStatus.OK);
	}
	
}
