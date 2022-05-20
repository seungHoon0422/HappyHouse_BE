package com.house.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.house.model.HouseDealInfoDto;
import com.house.model.HouseInfoDto;
import com.house.model.HouseListVo;
import com.house.model.SimpleHouseInfoDto;
@Mapper
public interface HouseMapper {
	
	List<HouseDealInfoDto> search(Map<String, String> map);
	List<SimpleHouseInfoDto> listDong(String dong);
	List<SimpleHouseInfoDto> apartNameSearch(String aptName);
	HouseDealInfoDto dealInfoSearch(String no);
	HouseInfoDto houseInfoSearch(int aptCode);
	List<HouseInfoDto> searchHouseByDong(String dongcode);
	void registDealInfo(HouseDealInfoDto houseDealInfoDto);
	List<HouseListVo> searchByGugunName(String name);

}
