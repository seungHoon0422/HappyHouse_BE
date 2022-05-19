package com.house.model.service;

import java.util.Map;

public interface JwtService {
	<T> String create (String key, T data, String subject);
	Map<String , String > get(String key); 
	String getUserId();
	boolean isUsable(String jwt);
}
