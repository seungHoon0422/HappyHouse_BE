package com.house.model.service;

import java.util.Map;

import com.house.model.UserDto;

public interface UserService  {

	/**회원 가입
	 * @return */
	void registerMember(UserDto userDto) throws Exception;
	/**로그인*/
	UserDto login(Map<String, String> map) throws Exception;
	/**아이디로 회원 정보 조회 */
	UserDto info(String userid) throws Exception;
	/**회원 정보 삭제 ( 회원 탈퇴 ) */
	void delete(UserDto user) throws Exception;
	/**회원 정보 수정 (아이디는 그대로) */
	void update(UserDto user) throws Exception;
	/**아이디 중복검사*/
	int idCheck(String checkId) throws Exception;
	/**비밀번호 찾기*/
	String findpass(UserDto userDto) throws Exception;
	/**아이디 찾기*/
	String findid(UserDto userDto) throws Exception;
	/**비밀번호 바꾸기*/
	void changepass(UserDto user) throws Exception;

}
