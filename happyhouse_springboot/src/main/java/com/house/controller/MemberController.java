package com.house.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.house.model.InterestDto;
import com.house.model.UserDto;
import com.house.model.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


/***
 * 회원 정보 처리하는 컨트롤러 */
@Controller
@RequestMapping("/user")
public class MemberController {
	
	@Autowired
	private UserService userService;
	
		
	/**레벨 구분 페이지이동*/
	@GetMapping("/userregister")
	public String userregister() {
		return "user/level";
	}
	
	
	/**레벨 선택 후 회원가입 페이지 이동*/
	@GetMapping("/mvregister")
	public String mvregister(@RequestParam("level") int level, Model model) {
		model.addAttribute("level", level);
		return "user/join";
	}
	
	/**회원정보 입력 후 회원가입 버튼
	 * @throws Exception */
	@PostMapping("/register")
	public String register(UserDto userDto) throws Exception {
		userService.registerMember(userDto);
		return "user/login";
	}
	
	/**아이디 중복검사*/
	@GetMapping("/idcheck")
	public @ResponseBody String idCheck(@RequestParam("ckid") String checkId) throws Exception {
		int idCount = userService.idCheck(checkId);
		System.out.println(idCount);
		JSONObject json = new JSONObject();
		json.put("idcount", idCount);
		return json.toString();
	}
	
	/**로그인 페이지 이동*/
	@GetMapping("/mvlogin")
	public String mvlogin() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
			HttpServletResponse response) throws Exception{
		UserDto userDto = userService.login(map);
		if(userDto != null) { // 로그인 성공 
			session.setAttribute("userinfo", userDto); // userinfo : 이름, 아이디  , 레벨 ( 중개인 / 사용자 ) 
			Cookie cookie = new Cookie("save_id" , map.get("userid"));
			cookie.setPath("/");
			if ("saveok".equals(map.get("idsave"))) {
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
			} else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			System.out.println(userDto.toString());
			return "redirect:/";
		}else { // 로그인 실패 
			model.addAttribute("msg", "아이디 또는 비밀번호를 확인해주세요."); 
			return "user/login";
		}
	}
	
	@GetMapping("/logout") 
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	/**회원정보 페이지 이동
	 * @throws Exception */
	@GetMapping("/info")
	public String info(HttpSession session, Model model) throws Exception {
		UserDto user = (UserDto) session.getAttribute("userinfo"); 
		if(user != null ) System.out.println(user.toString());
		UserDto userDto = userService.info(user); // 사용자의 정보 담겨있는 userDto 
		model.addAttribute("userlist", userDto);  
		return "user/info";
		
	}
	
	/**회원 정보 수정 
	 * @throws Exception */
	@PostMapping("/update")
	public String update(HttpSession session, @ModelAttribute UserDto user, Model model) throws Exception {
		System.out.println(user.toString());
		userService.update(user);
		
		return "redirect:/user/info"; 
	}
	
	
	/**회원 탈퇴*/
	@DeleteMapping("/delete")
	public String delete(HttpSession session) throws Exception{
		UserDto user = (UserDto) session.getAttribute("userinfo");
		session.invalidate(); //  세션 만료 시키고 
		userService.delete(user);  // 회원 탈퇴 

	
		return "redirect:/";
	}
	
	/**관심 목록 조회
	 * @throws Exception */
	@GetMapping("/interest")
	public String interest(HttpSession session, Model model) throws Exception {
		UserDto user = (UserDto)session.getAttribute("userinfo"); // 로그인 되어있는 사람의 정보 
		List<InterestDto> list= userService.interest(user);
		model.addAttribute("apartlist", list); 
		return "user/interest"; 
			
	}
}	
