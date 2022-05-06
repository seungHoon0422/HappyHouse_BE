package com.house.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.house.model.StarbucksDto;
import com.house.model.mapper.StarbucksMapper;

@Service
public class StarbucksServiceImpl implements StarbucksService {

	
	@Autowired
	RegionService regionService;
	@Autowired
	StarbucksMapper starbucksMapper;
	
	@Override
	public List<StarbucksDto> searchAll() {
		return starbucksMapper.searchAll();
	}
	
	@Override
	public List<StarbucksDto> searchStarbucksByDong(String dongcode) {
		
		return null;
	}

	
	// 두 좌표 사이의 거리를 구하는 함수
    // dsitance(첫번쨰 좌표의 위도, 첫번째 좌표의 경도, 두번째 좌표의 위도, 두번째 좌표의 경도)
    private static double distance(double lat1, double lon1, double lat2, double lon2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist; //단위 meter
    }
    
    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }


}
