package com.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.house.model.StarbucksDto;

@Mapper
public interface StarbucksMapper {

	List<StarbucksDto> searchAll();
}
