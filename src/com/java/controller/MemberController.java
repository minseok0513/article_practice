package com.java.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java.dao.MemberDao;
import com.java.dto.Article;
import com.java.dto.Member;
import com.java.util.Util;

public class MemberController extends Controller {
	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;
	private MemberDao memberDao;

	public MemberController(Scanner sc) {
		this.sc = sc;
		memberDao = new MemberDao();
		members = memberDao.members;
	}

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private void doLogout() {
		if (isLogined() == false) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}

		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	private void doLogin() {
		if (isLogined()) {
			System.out.println("이미 로그인 되어 있습니다.");
			return;
		}

		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비번 : ");
		String loginPw = sc.nextLine();

		// 입력받은 아이디에 해당하는 회원이 존재하는지
		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다.");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}

		loginedMember = member;
		System.out.printf("로그인 성공! %s님 환영합니다^^!\n", loginedMember.name);
	}

	private void doJoin() {
		int id = memberDao.getNewId() + 1;
		String regDate = Util.getNowDateStr();

		String loginId = null;
		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}

			break;
		}

		while (true) {
			System.out.printf("로그인 비번 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비번확인 : ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}

			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		memberDao.add(member);

		System.out.printf("%d번 회원이 생성되었습니다. 환영합니다^^\n", id);
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

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
		memberDao.add(new Member(memberDao.getNewId(), Util.getNowDateStr(), "admin", "admin", "관리자"));
		memberDao.add(new Member(memberDao.getNewId(), Util.getNowDateStr(), "user1", "user1", "유저1"));
		memberDao.add(new Member(memberDao.getNewId(), Util.getNowDateStr(), "user2", "user2", "유저2"));
	}
}