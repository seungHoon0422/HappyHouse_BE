package com.house.model.service;

import java.util.List;
import java.util.Map;

import com.house.model.HouseDealInfoDto;
import com.house.model.SimpleHouseInfoDto;

public interface HouseService {

	public List<HouseDealInfoDto> search(Map<String, String> map);
	public List<SimpleHouseInfoDto> listDong(String dong);
	public List<SimpleHouseInfoDto> apartNameSearch(String aptName);
}
