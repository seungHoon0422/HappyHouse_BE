package com.house.model.service;

import com.house.model.UserDto;

public interface InterestService {

	/**관심목록에 등록 */
	void regist(String userid, int aptCode) throws Exception;

}
