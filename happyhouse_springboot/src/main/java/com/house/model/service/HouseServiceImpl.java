package com.house.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.model.FilterDto;
import com.house.model.HouseDealInfoDto;
import com.house.model.HouseInfoDto;
import com.house.model.HouseListVo;
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

	@Override
	public void registDealInfo(HouseDealInfoDto houseDealInfoDto) {
		
		houseMapper.registDealInfo(houseDealInfoDto);
	}
	@Override
	public List<HouseListVo> searchByGugunName(FilterDto filterDto, String name) {
		List<HouseListVo> houseListVos = houseMapper.searchByGugunName(name);
		
		double area = Integer.parseInt(filterDto.getArea());
		int floor = Integer.parseInt(filterDto.getFloor());
		int dealAmount = Integer.parseInt(filterDto.getDealAmount());
		String inputName = filterDto.getInputName();
		System.out.println(area+" "+ floor+" "+ dealAmount);
		
		List<HouseListVo> result = new ArrayList<HouseListVo>();

		for (HouseListVo vo : houseListVos) {
			if(vo.getArea() == null || vo.getFloor() == null || vo.getDealAmount() == null) continue;
			Double a = Double.parseDouble(vo.getArea());
			int f = Integer.parseInt(vo.getFloor());
			int d = Integer.parseInt(vo.getDealAmount().trim().replace(",", ""));
			String i = vo.getAptName();
			
			if(area == 1 && !(a<20)) continue;
			else if(area == 2&& !(20<= a  && a< 30)) continue;
			else if(area == 3 &&!(30<= a && a< 40)) continue;
			else if(area == 4 && !(a>=40)) continue;
			
			if(floor == 1 && !(f<5)) continue;
			else if(floor == 2 && !(5<= f  && f< 10)) continue;
			else if(floor == 3 && !(f>=10)) continue;
			
			if(dealAmount == 1 && !(d<50000)) continue;
			else if(dealAmount == 2 && !(50000<= d)) continue;
			else if(dealAmount == 3 && !(d>=100000)) continue;
			
			if(i.contains(inputName)) result.add(vo);
		}
		
		return result;
	}

}
