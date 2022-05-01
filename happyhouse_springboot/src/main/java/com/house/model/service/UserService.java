package com.house.model.service;

import java.util.Map;

import com.house.model.UserDto;

public interface UserService  {

	/**회원 가입*/
	void registerMember(UserDto userDto) throws Exception;
	/**로그인*/
	UserDto login(Map<String, String> map) throws Exception;
	/**아이디로 회원 정보 조회 */
	UserDto info(UserDto user) throws Exception;
	/**회원 정보 삭제 ( 회원 탈퇴 ) */
	void delete(UserDto user) throws Exception;

}
