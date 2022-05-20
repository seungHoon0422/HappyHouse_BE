package com.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.house.model.AddressDto;
import com.house.model.DetailVo;
import com.house.model.HouseInfoDto;
import com.house.model.RegionDto;


@Mapper
public interface RegionMapper {
	List<RegionDto> getSido();
	List<RegionDto> getGugun();
	List<RegionDto> getDong();
	HouseInfoDto getAddress(String code);
	DetailVo getDetail(String no);
	String getDongName(String dongcode);
	String getGugunName(String gugunCode);

}
