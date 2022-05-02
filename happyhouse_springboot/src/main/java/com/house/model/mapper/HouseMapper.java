package com.house.model.mapper;

import java.util.List;
import java.util.Map;

import com.house.model.HouseDealInfoDto;
import com.house.model.HouseInfoDto;
import com.house.model.SimpleHouseInfoDto;

public interface HouseMapper {
	
	List<HouseDealInfoDto> search(Map<String, String> map);
	List<SimpleHouseInfoDto> listDong(String dong);
	List<SimpleHouseInfoDto> apartNameSearch(String aptName);
	HouseDealInfoDto dealInfoSearch(String no);
	HouseInfoDto houseInfoSearch(int aptCode);

}
