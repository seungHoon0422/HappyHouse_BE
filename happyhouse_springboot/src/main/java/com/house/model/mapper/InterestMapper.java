package com.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.house.model.InterestDto;
import com.house.model.UserDto;

@Mapper
public interface InterestMapper {

	void regist(InterestDto interestDto) throws Exception;
	
	List<Integer> interest(String userid) throws Exception;


}
