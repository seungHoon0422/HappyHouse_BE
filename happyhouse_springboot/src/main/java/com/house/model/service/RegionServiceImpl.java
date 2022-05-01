package com.house.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.model.AddressDto;
import com.house.model.RegionDto;
import com.house.model.mapper.RegionMapper;

@Service
public class RegionServiceImpl implements RegionService{

	
	@Autowired
	RegionMapper regionMapper;
	
	@Override
	public List<RegionDto> getSido() {
		return regionMapper.getSido();
	}

	@Override
	public List<RegionDto> getGugun() {
		return regionMapper.getGugun();
	}

	@Override
	public List<RegionDto> getDong() {
		return regionMapper.getDong();
	}

	@Override
	public AddressDto getAddress(String code) {
		return regionMapper.getAddress(code);
		
	}

	
	
}
