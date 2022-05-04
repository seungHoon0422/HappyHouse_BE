package com.house.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.house.model.InterestDto;
import com.house.model.UserDto;
import com.house.model.service.InterestService;

/***
 * 관심정보 처리 컨트롤러*/
@Controller
@RequestMapping("/interest")
public class InterestController {

	
	
	@Autowired
	InterestService interestService;
	
	
	
	
	private ResponseEntity<?> regist(@RequestBody String aptCode, HttpSession session) throws Exception{
		System.out.println(aptCode);
		UserDto user = (UserDto)session.getAttribute("userinfo"); // 로그인 되어있는 사람의 정보 
		System.out.println(user);
		interestService.regist(user, aptCode);
		// 1. session에 있는 userid받아서 interestService.regist(userid, code) 실행
		// 2. return 0 : 등록 성공(aptCode 존재 x ), 1이상 : 이미 등록된 아파트 , 
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
}
