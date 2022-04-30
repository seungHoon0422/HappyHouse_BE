package com.house.model;

public class UserDto {
	private String userid;
	private String userpass;
	private String email;
	private String username;
	private String phone;
	private int level; // 사용자면 0, 중개인이면 1 
	
	public UserDto() {
	
	}

	public UserDto(String userid, String userpass, String email, String username, String phone, int level) {
		super();
		this.userid = userid;
		this.userpass = userpass;
		this.email = email;
		this.username = username;
		this.phone = phone;
		this.level = level;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	
<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'master' of https://github.com/seungHoon0422/house.git
