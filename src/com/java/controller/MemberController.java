package com.java.controller;

import java.util.List;
import java.util.Scanner;

import com.java.dto.Article;
import com.java.dto.Member;
import com.java.util.Util;

public class MemberController {
	private List<Member> members;
	private Scanner sc;
	boolean isLogined =false;
	Member loginedMember = null;
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}

	int lastMemberId = 3;
	public void doLogin() {
		if(loginedMember != null) {
			System.out.println("이미 로그인되어있습니다.");
			return;
		}
		System.out.printf("아이디 입력 : ");
		String loginId = sc.nextLine();
		System.out.printf("비밀번호 입력 : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("존재하지않는 회원입니다.");
			return;
		}
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}
		isLogined = true;
		loginedMember = member;
		System.out.printf("로그인 성공! %s회원님 환영합니다.", loginedMember.name);

	}
	public void doJoin() {
		int id = lastMemberId + 1;
		String regDate = Util.getNowDateStr();
		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;
		String name = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (loginId.length() == 0) {
				System.out.println("아이디 입력해라");
				continue;
			} else if (isJoinableLoginId(loginId) == false) {
				System.out.println("이미 쓰는 아이디야");
				continue;
			}

			break;
		}

		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			if (loginPw.length() == 0) {
				System.out.println("비밀번호 입력해라");
				continue;
			}
			while (true) {
				System.out.printf("로그인 비밀번호 확인 : ");
				loginPwConfirm = sc.nextLine();

				if (loginPwConfirm.length() == 0) {
					System.out.println("비밀번호 확인 입력해라");
					continue;
				}
				break;
			}

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호 확인해");
				continue;
			}
			break;
		}

		while (true) {
			System.out.printf("이름 : ");
			name = sc.nextLine();

			if (name.length() == 0) {
				System.out.println("이름 입력해라");
				continue;
			}
			break;
		}

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%d번 회원이 가입되었습니다.\n", id);
		lastMemberId++;
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}
		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}
	public void makeTestData() {
		System.out.println("테스트를 위한 회원데이터 3개 생성 완료");
		members.add(new Member(1, Util.getNowDateStr(),"test1", "test1", "회원1"));
		members.add(new Member(2, Util.getNowDateStr(),"test2", "test2", "회원2"));
		members.add(new Member(3, Util.getNowDateStr(),"test3", "test3", "회원3"));
	}
}
