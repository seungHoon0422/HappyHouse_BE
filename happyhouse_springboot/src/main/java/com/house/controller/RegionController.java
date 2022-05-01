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
import com.house.model.service.RegionService;

@RestController
@RequestMapping("/region")
@CrossOrigin("*")
public class RegionController {

	@Autowired
	RegionService regionService;	
	
	
	@GetMapping(value="/sido")
	public ResponseEntity<?> getSido(){
		System.out.println("/reigon/sido");
		List<RegionDto> list = regionService.getSido();
		 return new ResponseEntity<List<RegionDto>>(list, HttpStatus.OK);
	}

	@GetMapping(value="gugun/{regcode}")
	public ResponseEntity<?> getGugun(@PathVariable("regcode") String regcode){
		
		System.out.println("/reigon/"+regcode);
		List<RegionDto> list = regionService.getGugun();
		List<RegionDto> guguns = new ArrayList<RegionDto>();
		String gugunCode = regcode.substring(0,2);
		System.out.println(list.size());
		for (RegionDto gugun : list) {
			if(gugun.getCode().substring(0, 2).equals(gugunCode))
				guguns.add(gugun);
		}
		System.out.println("list size : "+guguns.size());
		return new ResponseEntity<List<RegionDto>>(guguns, HttpStatus.OK);
	}

	@GetMapping(value="dong/{regcode}")
	public ResponseEntity<?> getDong(@PathVariable("regcode") String regcode){
		
		System.out.println("/reigon/"+regcode);
		List<RegionDto> list = regionService.getDong();
		List<RegionDto> dongs = new ArrayList<RegionDto>();
		String gugunCode = regcode.substring(0,5);

		for (RegionDto dong : list) {
			if(dong.getCode().substring(0, 5).equals(gugunCode))
				dongs.add(dong);
		}

		return new ResponseEntity<List<RegionDto>>(dongs, HttpStatus.OK);
	}
	
	
	
}