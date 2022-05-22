package com.house.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.house.model.InterestDto;
import com.house.model.UserDto;
import com.house.model.service.JwtService;
import com.house.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/***

회원 정보 처리하는 컨트롤러 */
@RestController
@RequestMapping("/restuser")
@CrossOrigin("*")
public class RestMemberController {

    @Autowired 
    private JwtService jwtService;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam Map<String, String> map, HttpSession session) throws Exception {
        System.out.println(map.get("userid"));
        System.out.println(map.get("userpass"));
//        System.out.println("hi");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null; 
        UserDto userDto = userService.login(map);
        try {
            if(userDto != null ) {
                String token = jwtService.create("userid", userDto.getUserid(), "access-token"); // key, data, subject
                resultMap.put("access-token", token);
                resultMap.put("username", userDto.getUsername());
                resultMap.put("message", "success");
                status = HttpStatus.ACCEPTED;
            }else {
                resultMap.put("message", "fail");
                status = HttpStatus.ACCEPTED;
            }
        }catch(Exception e) {
            System.out.println("로그인 실패 ");
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            
        }
        return new ResponseEntity<Map<String,Object>>(resultMap, status);
        
    }
    
    @ApiOperation(value="회원인증 ", notes ="해당 회원정보의 token 반환 ")
    @GetMapping("/info/{userid}")
    public ResponseEntity<Map<String,Object>> getInfo(@PathVariable("userid") String userid, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if(jwtService.isUsable(request.getHeader("access-token"))) { //요청헤더에 담겨온 토큰 
            System.out.println("사용가능한 토큰 !! ");
            try {
                UserDto user = userService.info(userid);
                resultMap.put("userInfo", user);
                System.out.println(user);
                resultMap.put("message", "success");
            }catch(Exception e) {
                resultMap.put("message", e.getMessage()); 
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }else {
            System.out.println("사용 불가능한 토큰 : 해당 로그인 정보가 없거나 사용 시간 만료 ");
            resultMap.put("message", "fail");
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
        
    
    
    @PostMapping("/regist")
    public ResponseEntity<?> regist(UserDto userDto){
        System.out.println(userDto.toString());
        try{
            userService.registerMember(userDto);
            return new ResponseEntity<String>("환영합니다.", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //회원정보 얻어오기 
    @GetMapping("user")
    public ResponseEntity<?> info(UserDto userDto) throws Exception{
        String userid = userDto.getUserid();
        UserDto user = userService.info(userid); 
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

/**비밀번호 찾기 
 * @throws Exception */
@GetMapping("/findpass/{userid}/{username}")
public ResponseEntity<?> findpass(@PathVariable("userid") String userid, @PathVariable("username") String username) throws Exception{
    UserDto user = new UserDto();
    user.setUserid(userid);
    user.setUsername(username);
    System.out.println("비밀번호찾기  : "+username + " "+userid);
    String userpass = userService.findpass(user);
    System.out.println(userpass);
        if(userpass != null) {
        return new ResponseEntity<String>(userpass, HttpStatus.OK);
    
        } else {
        return new ResponseEntity<String>("아이디랑 비밀번호를 확인해주세요!! ", HttpStatus.NO_CONTENT);
        }
    
    
}

/**아이디 찾기 */
@GetMapping("/findid/{username}/{email}")
public ResponseEntity<?> findid(@PathVariable("username") String username, @PathVariable("email") String email) throws Exception{
    UserDto user = new UserDto();
    user.setUsername(username);
    user.setEmail(email);
    System.out.println("아이디 찾기 : "+username + " "+email);
    String userid = userService.findid(user);
    
    System.out.println(userid);
    if(userid != null) {
    return new ResponseEntity<String>(userid, HttpStatus.OK);

    } else {
    return new ResponseEntity<String>("이름과 이메일 정보를 확인해주세요!! ", HttpStatus.NO_CONTENT);
    }

    
}
}     

