package com.house.model.mapper;

import java.util.List;
import java.util.Map;

import com.house.model.HouseDealInfoDto;

public interface HouseMapper {
	
	List<HouseDealInfoDto> search(Map<String, String> map);

}
