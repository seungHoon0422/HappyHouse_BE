package com.house.model.service;

import java.util.List;

import com.house.model.StarbucksDto;

public interface StarbucksService {
	public List<StarbucksDto> searchStarbucksByDong(String dongcode);
	public List<StarbucksDto> searchAll();
}
