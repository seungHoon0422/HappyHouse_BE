package com.house.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.house.model.InterestDto;
import com.house.model.UserDto;
import com.house.model.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


/***
 * 회원 정보 처리하는 컨트롤러 */
@RestController
@RequestMapping("/restuser")
@CrossOrigin("*")
public class RestMemberController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam Map<String, String> map) throws Exception {
		System.out.println(map.get("userid"));
		System.out.println("hi");
		UserDto userDto = userService.login(map);
		
		if(userDto != null) {
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
			}
		System.out.println("로그인실패");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
	@PostMapping("/regist")
	public String regist(UserDto userDto) throws Exception{
		System.out.println(userDto.toString());
		userService.registerMember(userDto);
		return "success"; 
	}
	
	//회원정보 얻어오기 
	@GetMapping("user")
	public ResponseEntity<?> info(UserDto userDto) throws Exception{
		UserDto user = userService.info(userDto); 
		System.out.println("회원정보 불러올게요 : "+user.toString());
		if(user !=null) {
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**회원 정보 수정 
	 * @throws Exception */
	@PostMapping("/update")
	public ResponseEntity<?> update(UserDto userDto) throws Exception {
		userService.update(userDto);
		
		return new ResponseEntity<String>("update", HttpStatus.OK);
		
	}
	
}	 
