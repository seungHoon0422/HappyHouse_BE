package com.house.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.model.DongCodeDto;
import com.house.model.HouseDealInfoDto;
import com.house.model.HouseListVo;
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
	public List<String> interest(String userid) throws Exception {
		return interestMapper.interest(userid);
	}

	@Override
	public void delete(String aptName, String userid) throws Exception {
		interestMapper.delete(aptName, userid);
		
	}

	@Override
	public List<HashMap> list(String aptName) throws Exception {
		return interestMapper.list(aptName);
	}

	@Override
	public List<HashMap> likelist(String userid) throws Exception {
		return interestMapper.likelist(userid);
	}

	@Override
	public DongCodeDto sido(String dongCode) throws Exception {
		return interestMapper.sido(dongCode);
	}

	@Override
	public int already(InterestDto interest) throws Exception {
		return interestMapper.already(interest);
	
	}
}
