package com.house.model;

public class AddressDto {

	private String dongName;
	private String jibun;
	
	
	
	public AddressDto(String dongName, String jibun) {
		this.dongName = dongName;
		this.jibun = jibun;
	}
	
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	
	
}
