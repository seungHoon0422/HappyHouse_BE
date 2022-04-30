package com.house.model.mapper;

import java.util.List;

import com.house.model.RegionDto;

public interface RegionMapper {
	List<RegionDto> getSido();
	List<RegionDto> getGugun();
	List<RegionDto> getDong();

}
