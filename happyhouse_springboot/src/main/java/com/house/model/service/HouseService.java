package com.house.model.service;

import java.util.List;
import java.util.Map;

import com.house.model.FilterDto;
import com.house.model.HouseDealInfoDto;
import com.house.model.HouseInfoDto;
import com.house.model.HouseListVo;
import com.house.model.SimpleHouseInfoDto;

public interface HouseService {

	public List<HouseDealInfoDto> search(Map<String, String> map);
	public List<SimpleHouseInfoDto> listDong(String dong);
	public List<SimpleHouseInfoDto> apartNameSearch(String aptName);
	public HouseDealInfoDto dealInfoSearch(String no);
	public HouseInfoDto houseInfoSearch(int aptCode);
	public List<HouseInfoDto> searchHouseByDong(String dongcode);
	public void registDealInfo(HouseDealInfoDto houseDealInfoDto);
	public List<HouseListVo> searchByGugunName(FilterDto filterDto, String name);
	public List<HouseListVo> searchByApartName(String apartname);
	public void sellHouse(HouseDealInfoDto house);
	
	
}
