package com.house.model.service;

import java.util.List;

import com.house.model.InterestDto;
import com.house.model.UserDto;

public interface InterestService {

	/**관심목록에 등록 */
	void regist(InterestDto interestDto) throws Exception;
	
	/**회원의 관심 목록 조회 
	 * @return */
	List<Integer> interest(String userid) throws Exception;

}
