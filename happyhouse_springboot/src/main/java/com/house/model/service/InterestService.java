package com.house.model.service;

import java.util.HashMap;
import java.util.List;

import com.house.model.HouseDealInfoDto;
import com.house.model.HouseListVo;
import com.house.model.InterestDto;
import com.house.model.UserDto;

public interface InterestService {

	/**관심목록에 등록 */
	void regist(InterestDto interestDto) throws Exception;
	
	/**회원의 관심 목록 조회 
	 * @return */
	List<String> interest(String userid) throws Exception;

	/**해당 아파트 삭제 
	 * @param userid */
	void delete(String aptName, String userid) throws Exception;
	
	/**아파트 이름에 대한 모든 매매 목록 불러오기*/
	List<HashMap> list(String aptName) throws Exception;

}
