package com.house.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 관심정보 처리 컨트롤러*/
@Controller
@RequestMapping("/interest")
public class InterestController {

	@PutMapping("/regist/{code}")
	@ResponseBody
	private ResponseEntity<?> regist(@PathVariable("code") String code){
		
		// 1. session에 있는 userid받아서 interestService.regist(userid, code) 실행
		// 2. return 0 : 등록 실패(Exception), return 1 : 정상 등록, return 2 : 이미 등록된 아파트
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
}
