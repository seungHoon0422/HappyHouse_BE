package com.house.model;

public class HouseDealInfoDto{
	
	private int no;
	private int aptCode; // 아파트 코드
	private String dealAmount; //거래가격
    private int dealYear; // 년(조회)
    private int dealMonth;// 월(조회)
    private int dealDay;  // 일(결과)
    private String area;  // 면적
    private String floor;  //층
    private String type;   //종류
    private String rentMoney;//대출
    
    
    
    public HouseDealInfoDto() {
	}

    

	public HouseDealInfoDto(int no, int aptCode, String dealAmount, int dealYear, int dealMonth, int dealDay,
			String area, String floor, String type, String rentMoney) {
		super();
		this.no = no;
		this.aptCode = aptCode;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.type = type;
		this.rentMoney = rentMoney;
	}



	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public int getAptCode() {
		return aptCode;
	}



	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
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



	public String getFloor() {
		return floor;
	}



	public void setFloor(String floor) {
		this.floor = floor;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getRentMoney() {
		return rentMoney;
	}



	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}



	@Override
	public String toString() {
		return "HouseDealInfoDto [no=" + no + ", aptCode=" + aptCode + ", dealAmount=" + dealAmount + ", dealYear="
				+ dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor
				+ ", type=" + type + ", rentMoney=" + rentMoney + "]";
	}

	
}
