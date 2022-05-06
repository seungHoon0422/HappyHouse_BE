package com.house.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
	
	/**관심 목록 오름차순
	 * @throws Exception */
	@GetMapping("/interestup")
	public ResponseEntity<?> interestup(HttpSession session) throws Exception {
		System.out.println("hi");
		UserDto user = (UserDto)session.getAttribute("userinfo"); // 로그인 되어있는 사람의 정보 
		String userid = user.getUserid();
		List<HashMap> list= interestService.likelist(userid);
		Collections.sort(list, new Comparator<HashMap>() {

			@Override
			public int compare(HashMap o1, HashMap o2) {
				int deal1=Integer.parseInt(String.valueOf(o1.get("count(*)")));
				int deal2=Integer.parseInt(String.valueOf(o2.get("count(*)")));
				return deal2-deal1;
				
			}
		});
		System.out.println(list.toString()); 
		if(list !=null && !list.isEmpty()) {
			return new ResponseEntity<List<HashMap>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
			
	}
	/**관심 목록 내림차순
	 * @throws Exception */
	@GetMapping("/interestdown")
	public ResponseEntity<?> interestdown(HttpSession session) throws Exception {
		System.out.println("hi");
		UserDto user = (UserDto)session.getAttribute("userinfo"); // 로그인 되어있는 사람의 정보 
		String userid = user.getUserid();
		List<HashMap> list= interestService.likelist(userid);
		Collections.sort(list, new Comparator<HashMap>() {

			@Override
			public int compare(HashMap o1, HashMap o2) {
				int deal1=Integer.parseInt(String.valueOf(o1.get("count(*)")));
				int deal2=Integer.parseInt(String.valueOf(o2.get("count(*)")));
				return deal1-deal2;
				
			}
		});
		
		if(list !=null && !list.isEmpty()) {
			return new ResponseEntity<List<HashMap>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
			
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
		//System.out.println(aptName);
		List<HashMap> list = interestService.list(aptName);
		System.out.println(list.toString());
		if(list !=null && !list.isEmpty()) {
			return new ResponseEntity<List<HashMap>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**오름차순 정렬 */
	@GetMapping("/up/{aptName}")
	@ResponseBody
	public ResponseEntity<?> up(@PathVariable("aptName") String aptName) throws Exception{
		List<HashMap> list = interestService.list(aptName);
		System.out.println(list.toString());
		Collections.sort(list, new Comparator<HashMap>() {

			@Override
			public int compare(HashMap o1, HashMap o2) {
				String deal1=(String)o1.get("dealAmount");
				System.out.println(deal1);
				String deal2= (String)o2.get("dealAmount");
				// 둘의 길이가 같으면 
				if(deal1.length() > deal2.length()) 
					return -1 ;
				else if(deal2.length() > deal1.length()) return 1 ;
				else return deal2.compareTo(deal1);
				
			}
		});
		
		System.out.println(list.toString());

		if(list !=null && !list.isEmpty()) {
			return new ResponseEntity<List<HashMap>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**내림차순 정렬 */
	@GetMapping("/down/{aptName}")
	@ResponseBody
	public ResponseEntity<?> down(@PathVariable("aptName") String aptName) throws Exception{
		List<HashMap> list = interestService.list(aptName);
		System.out.println(list.toString());
		Collections.sort(list, new Comparator<HashMap>() {

			@Override
			public int compare(HashMap o1, HashMap o2) {
				String deal1=(String)o1.get("dealAmount");
				System.out.println(deal1);
				String deal2= (String)o2.get("dealAmount");
				// 둘의 길이가 같으면 
				if(deal1.length() > deal2.length()) 
					return 1;
				else if(deal2.length() > deal1.length()) return -1 ;
				else return deal1.compareTo(deal2);
				
			}
		});
		
		System.out.println(list.toString());

		if(list !=null && !list.isEmpty()) {
			return new ResponseEntity<List<HashMap>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

}
