package com.java.dto;

public class Member extends Dto{
	public String loginId;
	public String loginPw;
	public String name;

	public Member(int id, String loginId, String loginPw, String regDate, String name) {
		this.id=id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.regDate = regDate;
		this.name = name;
	}
}