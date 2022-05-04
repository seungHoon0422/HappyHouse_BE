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
	private InterestService interestService;

	@GetMapping("/search")	
	@ResponseBody
	private ResponseEntity<?> searchById(HttpSession session){
		
		UserDto attribute = (UserDto) session.getAttribute("userinfo");
		String userid = attribute.getUserid();
//		List<InterestDto> list = interestService.searchById(userid);
//		return new ResponseEntity<List<InterestDto>>(list, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.OK); // 임시 return
	}

	
	@GetMapping("/search/{aptCode}")
	@ResponseBody
	private ResponseEntity<?> searchByCode(@PathVariable int aptCode, HttpSession session){
		System.out.println("code : "+ aptCode);
		
		UserDto attribute = (UserDto) session.getAttribute("userinfo");
		String userid = attribute.getUserid();
//		List<InterestDto> list = interestService.searchByCode(aptCode);
//		for (InterestDto interestDto : list) {
//			if(interestDto.getUserid().equals(userid)) {
//				return new ResponseEntity<Integer>(1, HttpStatus.OK);
//			}
//		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}
	
	
	@PostMapping("/regist")
	@ResponseBody
	private ResponseEntity<?> regist(@RequestBody InterestDto interestDto, HttpSession session) throws Exception{
//		System.out.println(aptCode);
		UserDto user = (UserDto)session.getAttribute("userinfo"); // 로그인 되어있는 사람의 정보 
		String userid = user.getUserid(); 
		interestDto.setUserid(userid);
		interestService.regist(interestDto);

		// 1. session에 있는 userid받아서 interestService.regist(userid, code) 실행
		// 2. return 0 : 등록 성공(aptCode 존재 x ), 1이상 : 이미 등록된 아파트 , 
		return new ResponseEntity<Integer>(1, HttpStatus.OK);
	}
	
}
