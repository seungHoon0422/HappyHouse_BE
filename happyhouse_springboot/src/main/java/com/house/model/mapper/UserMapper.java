package com.house.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.house.model.UserDto;

@Mapper
public interface UserMapper {

	void registerMember(UserDto userDto) throws Exception;

	UserDto login(Map<String, String> map) throws Exception;

	UserDto info(String userid) throws Exception;

	void delete(String userid) throws Exception;

	void update(UserDto user) throws Exception;

	int idCheck(String checkId) throws Exception;

	String findpass(UserDto userDto) throws Exception;
	
	String findid(UserDto userDto) throws Exception;

	void changepass(UserDto user) throws Exception;

	
}
