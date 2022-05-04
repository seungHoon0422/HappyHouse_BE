package com.house.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.house.model.UserDto;

@Mapper
public interface InterestMapper {

	void regist(String userid, int aptCode) throws Exception;


}
