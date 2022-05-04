package com.house.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.model.InterestDto;
import com.house.model.UserDto;
import com.house.model.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	private InterestMapper interestMapper; 
	
	@Override
	public void regist(InterestDto interestDto) throws Exception {
		interestMapper.regist(interestDto); 
	}

	@Override
	public List<Integer> interest(String userid) throws Exception {
		return interestMapper.interest(userid);
	}
}
