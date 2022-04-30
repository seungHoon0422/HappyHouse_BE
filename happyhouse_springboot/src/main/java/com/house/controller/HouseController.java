package com.house.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.house.model.HouseDealInfoDto;
import com.house.model.service.HouseService;
/***
 * 아파트 처리 컨트롤러*/
@Controller
@RequestMapping("/house")
public class HouseController {

//	private static final Logger logger = LoggerFactory.getLogger(GuestBookController.class);

	
	@Autowired
	HouseService houseService;
	
	@GetMapping("/register")
	private String register() {
		System.out.println("register controller");
		return "house/register";
	}
	
	@GetMapping("/search")
	private String search(@RequestParam Map<String, String> select, Model model) {
		
		List<HouseDealInfoDto> dealList = houseService.search(select);
		model.addAllAttributes(dealList);
		
		System.out.println("search controller");
		return "house/search";
		
	}

	@PostMapping("/register")
	public String register(@RequestParam Map<String, String> select, Model model) throws Exception {
		
		List<HouseDealInfoDto> houseDealList = houseService.search(select);
		for (HouseDealInfoDto info : houseDealList) {
			model.addAttribute(info);
		}

		Set<String> keySet = select.keySet();
		System.out.println("<request parameters>");
		for (String key : keySet) {
			System.out.println(key+" : "+select.get(key));
		}
		
		return "/house/list";
	}


	
	
}
