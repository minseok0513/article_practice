package com.java.controller;

import java.util.List;
import java.util.Scanner;

import com.java.dto.Member;
import com.java.util.Util;

public class MemberController {
	private List<Member> members;
	private Scanner sc;
	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
	}
	int lastMemberId = 0;
	
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
}
