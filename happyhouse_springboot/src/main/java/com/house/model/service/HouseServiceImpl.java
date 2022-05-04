package com.house.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.model.HouseDealInfoDto;
import com.house.model.HouseInfoDto;
import com.house.model.SimpleHouseInfoDto;
import com.house.model.mapper.HouseMapper;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	HouseMapper houseMapper;
	
	@Override
	public List<HouseDealInfoDto> search(Map<String, String> map) {
		// TODO Auto-generated method stub
		return houseMapper.search(map);
	}

	@Override
	public List<SimpleHouseInfoDto> listDong(String dong) {
		return houseMapper.listDong(dong);
	}

	@Override
	public List<SimpleHouseInfoDto> apartNameSearch(String aptName) {
		return houseMapper.apartNameSearch(aptName);
	}

	@Override
	public HouseDealInfoDto dealInfoSearch(String no) {
		return houseMapper.dealInfoSearch(no);
	}

	@Override
	public HouseInfoDto houseInfoSearch(int aptCode) {
		return houseMapper.houseInfoSearch(aptCode);
	}

	@Override
	public List<HouseInfoDto> searchHouseByDong(String dongcode) {
		return houseMapper.searchHouseByDong(dongcode);
	}

}
