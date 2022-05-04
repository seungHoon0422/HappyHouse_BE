package com.house.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.model.UserDto;
import com.house.model.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	private InterestMapper interestMapper; 
	
	@Override
	public void regist(String userid, int aptCode) throws Exception {
		interestMapper.regist(userid, aptCode); 
	}

}
