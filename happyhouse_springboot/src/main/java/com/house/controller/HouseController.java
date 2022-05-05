package com.house.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.house.model.HouseDealInfoDto;
import com.house.model.HouseInfoDto;
import com.house.model.SimpleHouseInfoDto;
import com.house.model.service.HouseService;
/***
 * 아파트 처리 컨트롤러*/
@Controller
@RequestMapping("/house")
public class HouseController {

//	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);

	
	@Autowired
	HouseService houseService;
	
	@GetMapping("/register")
	private String register() {
		System.out.println("register controller");
		return "house/register";
	}
	
	@PostMapping("/register")
	private String registDealInfo(HouseDealInfoDto houseDealInfoDto) {
		
		System.out.println("regist house deal info controller");
		System.out.println("aptCode : "+ houseDealInfoDto.getAptCode());
		System.out.println("deal Amount : "+ houseDealInfoDto.getDealAmount());
		houseService.registDealInfo(houseDealInfoDto);
		return "house/search";
	}

	@GetMapping("/search")
	private String search(@RequestParam Map<String, String> select, Model model) {
		
//		List<HouseDealInfoDto> dealList = houseService.search(select);
//		model.addAllAttributes(dealList);
		System.out.println("search controller");
		return "house/search";
	}

	@GetMapping("/search/{dongcode}")
	@ResponseBody
	private ResponseEntity<?> search(@PathVariable String dongcode, Model model) {
		
		System.out.println("search house information by dongcode");
		List<HouseInfoDto> list = houseService.searchHouseByDong(dongcode);
		System.out.println("house info size : "+list.size());
		for (HouseInfoDto houseInfoDto : list) {
			System.out.println( houseInfoDto.getAptCode() + " "+ houseInfoDto.getAptName() + " " + houseInfoDto.getDongName());
		}
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}

	
//	@PostMapping("/register")
//	public String register(@RequestParam Map<String, String> select, Model model) throws Exception {
//		
//		List<HouseDealInfoDto> houseDealList = houseService.search(select);
//		for (HouseDealInfoDto info : houseDealList) {
//			model.addAttribute(info);
//		}
//
//		Set<String> keySet = select.keySet();
//		System.out.println("<request parameters>");
//		for (String key : keySet) {
//			System.out.println(key+" : "+select.get(key));
//		}
//		
//		return "/house/search";
//	}

	// 입력된 dong code에 해당하는 모들 매물정보를 보여준다.
	// aptName(아파트 이름), area(면적), dealAmount(거래가격), buildYear(건축년도), 거래년도(년,월,일)
	@PostMapping("/list")
	public String listDong(@RequestParam String dong, Model model) {
		
		System.out.println("list dong method start, code : "+dong);
		List<SimpleHouseInfoDto> houseInfoList = houseService.listDong(dong);
		System.out.println("dong code search return size : " + houseInfoList.size());
		model.addAttribute("infos", houseInfoList);
		
		return "/house/list";
		
	}
	
	@PostMapping("/apartlist")
	public String apartNameSearch(@RequestParam String aptName, Model model) {
		
		System.out.println("apart Name Search method start, apart name : "+ aptName);
		List<SimpleHouseInfoDto> houseInfoList = houseService.apartNameSearch(aptName);
		System.out.println("apart name search return size : "+houseInfoList.size());
		model.addAttribute("infos", houseInfoList);
		
		return "/house/list";
		
	}

	
	
	
}
