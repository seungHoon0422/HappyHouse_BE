package com.house.model.service;

import java.util.List;

import com.house.model.AddressDto;
import com.house.model.DetailVo;
import com.house.model.HouseInfoDto;
import com.house.model.RegionDto;

public interface RegionService {

	List<RegionDto> getSido();
	List<RegionDto> getGugun();
	List<RegionDto> getDong();
	HouseInfoDto getAddress(String code);
	DetailVo getDetail(String no);
	String getDongName(String dongcode);
	String getGugunName(String gugunCode);
	List<HouseInfoDto> getApt(String dongCode);

}
