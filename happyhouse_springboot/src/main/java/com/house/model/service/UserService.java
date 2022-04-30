package com.house.model.service;

import java.util.Map;

import com.house.model.UserDto;

public interface UserService  {

	/**회원 가입*/
	void registerMember(UserDto userDto) throws Exception;

	UserDto login(Map<String, String> map) throws Exception;

}
