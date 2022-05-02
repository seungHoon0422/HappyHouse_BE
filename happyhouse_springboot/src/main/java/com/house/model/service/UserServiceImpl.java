package com.house.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.model.UserDto;
import com.house.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void registerMember(UserDto userDto) throws Exception {
		userMapper.registerMember(userDto); 
	}

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return userMapper.login(map);
	}

	@Override
	public UserDto info(UserDto user) throws Exception {
		return userMapper.info(user); 
	}

	@Override
	public void delete(UserDto user) throws Exception {
		userMapper.delete(user);
		
	}

	@Override
	public void update(UserDto user) throws Exception {
		userMapper.update(user);
		
	}

}
