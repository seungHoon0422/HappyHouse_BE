package com.house.model.service;

import java.util.List;
import java.util.Map;

import com.house.model.HouseDealInfoDto;

public interface HouseService {

	public List<HouseDealInfoDto> search(Map<String, String> map);
}
