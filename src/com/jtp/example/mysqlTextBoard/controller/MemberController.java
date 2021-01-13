package com.jtp.example.mysqlTextBoard.controller;

import java.util.Scanner;

import com.jtp.example.mysqlTextBoard.Container;
import com.jtp.example.mysqlTextBoard.dto.Member;
import com.jtp.example.mysqlTextBoard.service.MemberService;
import com.jtp.example.mysqlTextBoard.sesseion.Session;

public class MemberController extends Controller {
	private MemberService memberService;

	public MemberController() {
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("member join")) {
			doJoin(cmd);
		} else if (cmd.startsWith("member login")) {
			doLogin(cmd);
		} else if (cmd.startsWith("member info")) {
			showInfo(cmd);
		} else if (cmd.startsWith("member logout")) {
			doLogout(cmd);
		}

	}

	private void doLogout(String cmd) {
		System.out.println("== 로그아웃 ==");

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		System.out.println("로그아웃 되었습니다.");
		Container.session.logout();
	}
	

	private void showInfo(String cmd) {
		System.out.println("== 회원확인 ==");
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		int loginedMemberId = Container.session.getLoginedMemberId();
		Member member = memberService.getMemberById(loginedMemberId);
		System.out.printf("번호 : %d\n", member.id);
		System.out.printf("가입날자 : %s\n", member.regDate);
		System.out.printf("로그인아이디 : %s\n", member.loginId);
		System.out.printf("이름 : %s\n", member.name);
	}

	private void doLogin(String cmd) {
		System.out.println("== 로그인 ==");
		if (Container.session.isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		Scanner sc = Container.scanner;

		System.out.printf("로그인아이디 : ");
		String loginId = sc.nextLine().trim();

		if (loginId.length() == 0) {
			System.out.println("로그인아이디를 입력해주세요.");
			return;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("존재하지 않는 회원입니다.");
			return;
		}

		System.out.printf("로그인비밀번호 : ");
		String loginPw = sc.nextLine().trim();

		if (loginPw.length() == 0) {
			System.out.println("로그인비밀번호를 입력해주세요.");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호가 일치하지 않습니다.");
			return;
		}

		Session.loginedMemberId = member.id;

		System.out.printf("%s님 환영합니다.\n", member.name);
	}

	private void doJoin(String cmd) {
		System.out.println("== 회원가입 ==");
		if (Container.session.isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		Scanner sc = Container.scanner;

		System.out.printf("로그인아이디 : ");
		String loginId = sc.nextLine().trim();

		if (loginId.length() == 0) {
			System.out.println("로그인아이디를 입력해주세요.");
			return;
		}

		System.out.printf("로그인비밀번호 : ");
		String loginPw = sc.nextLine().trim();

		if (loginPw.length() == 0) {
			System.out.println("로그인비밀번호를 입력해주세요.");
			return;
		}

		System.out.printf("로그인비밀번호확인 : ");
		String loginPwConfirm = sc.nextLine().trim();

		if (loginPwConfirm.length() == 0) {
			System.out.println("로그인비밀번호확인을 입력해주세요.");
			return;
		}

		if (loginPw.equals(loginPwConfirm) == false) {
			System.out.println("로그인비밀번호가 일치하지 않습니다.");
			return;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine().trim();

		if (name.length() == 0) {
			System.out.println("이름을 입력해주세요.");
			return;
		}

		int id = memberService.join(loginId, loginPw, name);

		System.out.printf("%d번 회원이 생성되었습니다.\n", id);
	}

}
