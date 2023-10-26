package com.java.dto;

public class Article extends Dto {
	public int memberId; // 7
	public String title;
	public String body;
	public int hit;
	
	public Article(int id, String regDate, int memberId, String title, String body) {
		this(id, regDate, memberId, title, body, 0);
	}

	public Article(int id, String regDate, int memberId, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}
	
	public void increaseHit() {
		hit++;
	}
}