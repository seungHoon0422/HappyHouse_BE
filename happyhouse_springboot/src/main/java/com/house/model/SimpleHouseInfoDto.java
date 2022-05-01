package com.house.model;

public class SimpleHouseInfoDto {

	
	// foreign key
	private int aptCode;
	private String aptName;//아파트명
    private int buildYear; //지어진년도
	private String dealAmount; //거래가격
    private int dealYear; // 년(조회)
    private int dealMonth;// 월(조회)
    private int dealDay;  // 일(결과)
    private String area;  // 면적
    
    
    

    
    public SimpleHouseInfoDto(int aptCode, String aptName, int buildYear, String dealAmount, int dealYear,
			int dealMonth, int dealDay, String area) {
		super();
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.buildYear = buildYear;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
	}
    
    
    

	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}

    
    
    
}
