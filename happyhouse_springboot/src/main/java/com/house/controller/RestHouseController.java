package com.house.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.house.model.HouseDealInfoDto;
import com.house.model.HouseInfoDto;
import com.house.model.HouseListVo;
import com.house.model.RegionDto;
import com.house.model.SimpleHouseInfoDto;
import com.house.model.service.HouseService;
import com.house.model.service.RegionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/resthouse")
@CrossOrigin("*")
@Api("House Controller v1")
public class RestHouseController {

	private static final Logger logger = LoggerFactory.getLogger(RestHouseController.class);

	@Autowired
	RegionService regionService;	
	@Autowired
	HouseService houseService;

	/** 동코드로 아파트 정보 검색 */
	@ApiOperation(value = "아파트 정보(행정동 코드)", notes = "아파트 정보 리스트를 리턴합니다.")
	@GetMapping("/houseInfoByDongCode/{dongcode}")
	public ResponseEntity<?> getHouseInfoByDongCode(@PathVariable("dongcode") String dongcode){
		
		logger.info("/houseInfoByDongCode/"+dongcode+ ", getHouseInfoByDongCode()");
		List<HouseInfoDto> houseinfos = houseService.searchHouseByDong(dongcode);
		 return new ResponseEntity<List<HouseInfoDto>>(houseinfos, HttpStatus.OK);
	}

	/** 아파트 코드로 아파트 정보 검색 */
	@ApiOperation(value = "아파트 정보(아파트 코드)", notes = "아파트 코드에 해당하는 정보를 리턴합니다.")
	@GetMapping("/houseInfoByApartCode/{apartcode}")
	public ResponseEntity<?> getHouseInfoByAparCode(@PathVariable("apartcode") int apartcode){
		
		logger.info("/houseInfoByApartCode/"+ apartcode + ", getHouseInfoByAparCode()");
		HouseInfoDto houseinfo = houseService.houseInfoSearch(apartcode);
		 return new ResponseEntity<HouseInfoDto>(houseinfo, HttpStatus.OK);
	}

	
	/** 동코드로 매매정보 검색 */
	@ApiOperation(value = "매매 정보(행정동 코드)", notes = "매매 정보 리스트를 리턴합니다.")
	@GetMapping("/dealInfoByDongCode/{dongcode}")
	public ResponseEntity<?> getDealInfoByDongCode(@PathVariable("dongcode") String dongcode){
		
		logger.info("/dealInfoByDongCode/"+dongcode+ ", getDealInfoByDongCode()");
		List<SimpleHouseInfoDto> dealinfos = houseService.listDong(dongcode);
		 return new ResponseEntity<List<SimpleHouseInfoDto>>(dealinfos, HttpStatus.OK);
	}
	
	/** 아파트 이름으로 매매정보 검색 */
	@ApiOperation(value = "매매 정보(아파트 이름)", notes = "매매 정보 리스트를 리턴합니다.")
	@GetMapping("/dealInfoByApartName/{aptname}")
	public ResponseEntity<?> getDealInfoByApartName(@PathVariable("aptname") String name){
		
		logger.info("/dealInfoByApartName/"+name+ ", getDealInfoByApartName()");
		List<SimpleHouseInfoDto> dealinfos = houseService.apartNameSearch(name);
		 return new ResponseEntity<List<SimpleHouseInfoDto>>(dealinfos, HttpStatus.OK);
	}
	/** 매매번호로 상세정보 검색 */
	@ApiOperation(value = "매매 상세정보(거래번호)", notes = "매매 상세 정보를 리턴합니다.")
	@GetMapping("/dealInfoByDealNo/{no}")
	public ResponseEntity<?> getDealInfoByDealNo(@PathVariable("no") String no){
		
		logger.info("/dealInfoByDealNo/"+ no + ", getDealInfoByDealNo()");
		HouseDealInfoDto houseDealInfoDto = houseService.dealInfoSearch(no);
		 return new ResponseEntity<HouseDealInfoDto>(houseDealInfoDto, HttpStatus.OK);
	}
	
	/** 매매정보 등록 */
	@ApiOperation(value = "매매 정보 등록", notes = "매매 정보를 등록합니다.")
	@PostMapping("/registDealInfo")
	public ResponseEntity<?> registDealInfo(@RequestBody HouseDealInfoDto houseDealInfoDto){
		houseService.registDealInfo(houseDealInfoDto);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/filterSearch")
	private ResponseEntity<?> searchByGugunName(@RequestBody Map<String, String> param, Model model) {
	
		String gugunName = regionService.getGugunName(param.get("gugunCode"));
		gugunName = gugunName.split(" ")[1];
		System.out.println("gugunName : "+gugunName);
		List<HouseListVo> houseListVo = houseService.searchByGugunName(param, gugunName);
		return new ResponseEntity<List<HouseListVo>>(houseListVo, HttpStatus.OK);
	}

	
}
