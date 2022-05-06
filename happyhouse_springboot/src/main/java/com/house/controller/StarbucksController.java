package com.house.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.model.RegionDto;
import com.house.model.StarbucksDto;
import com.house.model.service.RegionService;
import com.house.model.service.StarbucksService;

@RestController
@RequestMapping("/starbucks")
@CrossOrigin("*")
public class StarbucksController {

	
	@Autowired
	RegionService regionService;
	
	@Autowired
	StarbucksService starbucksService;
	
	@GetMapping("/search/{dongcode}")
	public ResponseEntity<?> searchStarbucksByDong(@PathVariable("dongcode") String dongcode){
		System.out.println("dongcode : "+dongcode);
		String dongname = regionService.getDongName(dongcode);
		List<StarbucksDto> list = starbucksService.searchAll(); 
		List<StarbucksDto> response = new ArrayList<StarbucksDto>();
		// 동이름이 같은 매장만 저장
		for (StarbucksDto dto : list) {
			if(dto.getAddress().contains(dongname)) response.add(dto);
		}
		
		for (StarbucksDto starbucksDto : response) {
			System.out.println(starbucksDto.getName() + " " + starbucksDto.getAddress());
		}
		return new ResponseEntity<List<StarbucksDto>>(response, HttpStatus.OK);
	}
	
}
