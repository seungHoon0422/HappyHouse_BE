package com.house.model.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.house.model.DongCodeDto;
import com.house.model.HouseDealInfoDto;
import com.house.model.HouseListVo;
import com.house.model.InterestDto;
import com.house.model.UserDto;

@Mapper
public interface InterestMapper {

	void regist(InterestDto interestDto) throws Exception;
	
	List<String> interest(String userid) throws Exception;

	void delete(String aptName, String userid) throws Exception;

	List<HashMap> list(String aptName) throws Exception;

	List<HashMap> likelist(String userid) throws Exception;

	DongCodeDto sido(String dongCode) throws Exception;


}
