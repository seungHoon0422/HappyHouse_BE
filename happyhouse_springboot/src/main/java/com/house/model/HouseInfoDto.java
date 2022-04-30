package com.house.model;

public class HouseInfoDto {
	
	
	private int aptCode;
	
	private String aptName;//아파트명
	private String dongCode; //법정동 코드
	private String dongName; //동
    private int buildYear; //지어진년도
    private String jibun;//지번
    private String lat;  //위도
    private String lng;   //경도
    private String img;   //사진
    
    public HouseInfoDto() {
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

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	@Override
	public String toString() {
		return "HouseInfoDto [aptName=" + aptName + ", buildYear=" + buildYear + ", jibun=" + jibun + ", lat=" + lat
				+ ", lng=" + lng + ", img=" + img + ", dongName=" + dongName + ", dongCode=" + dongCode + "]";
	}
}
