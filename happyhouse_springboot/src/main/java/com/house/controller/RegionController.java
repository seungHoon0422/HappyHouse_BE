package com.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.model.RegionDto;
import com.house.model.service.RegionService;

@RestController
@RequestMapping("/region")
@CrossOrigin("*")
public class RegionController {

	@Autowired
	RegionService regionService;	
	
	
	@GetMapping(value="/sido")
	public ResponseEntity<?> getSido(){
		List<RegionDto> list = regionService.getSido();
		 return new ResponseEntity<List<RegionDto>>(list, HttpStatus.OK);
	}


	
	
	
}
