package com.house.model;

public class FilterDto {
	private String gugunCode;
	private String inputName;
	private String area;
	private String floor;
	private String dealAmount;
	public FilterDto(String gugunCode, String inputName, String area, String floor, String dealAmount) {
		super();
		this.gugunCode = gugunCode;
		this.inputName = inputName;
		this.area = area;
		this.floor = floor;
		this.dealAmount = dealAmount;
	}
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	
	
}
