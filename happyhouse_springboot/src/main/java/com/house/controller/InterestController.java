package com.house.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.house.model.HouseDealInfoDto;
import com.house.model.HouseListVo;
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
	
	/**관심 지역 등록*/
	@PostMapping("/regist")
	@ResponseBody
	private ResponseEntity<?> regist(@RequestBody InterestDto interestDto, HttpSession session) throws Exception{
//		System.out.println(aptCode);
		UserDto user = (UserDto)session.getAttribute("userinfo"); // 로그인 되어있는 사람의 정보 
		String userid = user.getUserid(); 
		interestDto.setUserid(userid);
			interestService.regist(interestDto);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
	
	}
	
	/**관심 목록 조회
	 * @throws Exception */
	@GetMapping("/interest")
	public ResponseEntity<?> interest(HttpSession session) throws Exception {
		System.out.println("hi");
		UserDto user = (UserDto)session.getAttribute("userinfo"); // 로그인 되어있는 사람의 정보 
		String userid = user.getUserid();
		List<String> list= interestService.interest(userid);
		System.out.println(list.toString()); 
		if(list !=null && !list.isEmpty()) {
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	
//		model.addAttribute("apartlist", list);  // 관심등록한 아파트의 이름
//		return "user/interest"; 
			
	}
	
	/**관심 목록 삭제*/
	@DeleteMapping("/interest/{aptName}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("aptName") String aptName , HttpSession session) throws Exception{
		System.out.println(aptName);
		UserDto user = (UserDto)session.getAttribute("userinfo"); // 로그인 되어있는 사람의 정보 
		String userid = user.getUserid();
		interestService.delete(aptName , userid);
		List<String> list= interestService.interest(userid);
		System.out.println(list.toString()); 
		if(list !=null && !list.isEmpty()) {
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	/**클릭한 아파트에 대한 상세정보*/
	@GetMapping("/list/{aptName}")
	@ResponseBody
	public ResponseEntity<?> list(@PathVariable("aptName") String aptName) throws Exception{
		System.out.println(aptName);
		List<HouseDealInfoDto> list = interestService.list(aptName);
		System.out.println(list.toString());
		if(list !=null && !list.isEmpty()) {
			return new ResponseEntity<List<HouseDealInfoDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
