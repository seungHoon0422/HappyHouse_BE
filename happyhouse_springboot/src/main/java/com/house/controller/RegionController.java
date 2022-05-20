package com.house.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.house.model.AddressDto;
import com.house.model.DetailVo;
import com.house.model.HouseDealInfoDto;
import com.house.model.HouseInfoDto;
import com.house.model.RegionDto;
import com.house.model.service.HouseService;
import com.house.model.service.RegionService;

@RestController
@RequestMapping("/region")
@CrossOrigin("*")
public class RegionController {

	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

	
	@Autowired
	RegionService regionService;	
	@Autowired
	HouseService houseService;
	
	@GetMapping(value="/sido")
	public ResponseEntity<?> getSido(){
		
		logger.info("/reigon/sido");
		List<RegionDto> list = regionService.getSido();
		 return new ResponseEntity<List<RegionDto>>(list, HttpStatus.OK);
	}

	
	@GetMapping(value="gugun/{regcode}")
	public ResponseEntity<?> getGugun(@PathVariable("regcode") String regcode){
		
		logger.info("/reigon/gugun/"+regcode);
		List<RegionDto> list = regionService.getGugun();
		List<RegionDto> guguns = new ArrayList<RegionDto>();
		String gugunCode = regcode.substring(0,2);

		for (RegionDto gugun : list) {
			if(gugun.getCode().substring(0, 2).equals(gugunCode))
				guguns.add(gugun);
		}

		return new ResponseEntity<List<RegionDto>>(guguns, HttpStatus.OK);
	}

	@GetMapping(value="dong/{regcode}")
	public ResponseEntity<?> getDong(@PathVariable("regcode") String regcode){
		
		logger.info("/reigon/dong/"+regcode);
		List<RegionDto> list = regionService.getDong();
		List<RegionDto> dongs = new ArrayList<RegionDto>();
		String gugunCode = regcode.substring(0,5);

		for (RegionDto dong : list) {
			if(dong.getCode().substring(0, 5).equals(gugunCode))
				dongs.add(dong);
		}

		return new ResponseEntity<List<RegionDto>>(dongs, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/address/{code}")
	public ResponseEntity<?> getAddress(@PathVariable("code") String code){
		logger.info("/region/address/"+code);
		HouseInfoDto houseInfo = regionService.getAddress(code);		
		return new ResponseEntity<HouseInfoDto>(houseInfo, HttpStatus.OK);
	}
	
	@GetMapping(value="/detail/{no}")
	public ResponseEntity<?> getDetail(@PathVariable("no") String no){
		logger.info("region/detail/"+no);
		HouseDealInfoDto dealInfo = houseService.dealInfoSearch(no);
		HouseInfoDto houseInfo = houseService.houseInfoSearch(dealInfo.getAptCode());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deal", dealInfo);
		map.put("house", houseInfo);
		
		DetailVo detailVo = new DetailVo(dealInfo, houseInfo);
		System.out.println(dealInfo.getNo());
		System.out.println(houseInfo.getAptName());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	

}
