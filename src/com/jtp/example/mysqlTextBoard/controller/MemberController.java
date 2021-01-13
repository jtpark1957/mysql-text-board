package com.jtp.example.mysqlTextBoard.controller;

import java.util.Scanner;

import com.jtp.example.mysqlTextBoard.Container;
import com.jtp.example.mysqlTextBoard.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;
	
	public MemberController() {
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if(cmd.startsWith("member join")) {
			doJoin(cmd);
		}
		
	}

	private void doJoin(String cmd) {
		System.out.println("== 회원가입 ==");

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
