package com.house.model;

public class RegionDto {

	private String code;
	private String name;
	
	
	public RegionDto() {
	}
	
	public RegionDto(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
