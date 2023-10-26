package com.java.controller;

import com.java.dto.Member;

public abstract class Controller {
	public static Member loginedMember;
	
	public static boolean isLogined() {
		return loginedMember != null;
	}
	
	public abstract void makeTestData();
	public abstract void doAction(String command, String actionMethodName);
}