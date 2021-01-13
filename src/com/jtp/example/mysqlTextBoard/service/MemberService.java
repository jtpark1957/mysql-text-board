package com.jtp.example.mysqlTextBoard.service;

import com.jtp.example.mysqlTextBoard.dao.MemberDao;

public class MemberService {
	private MemberDao memberDao;
	public MemberService() {
		memberDao = new MemberDao();
	}
	public int join(String loginId, String loginPw, String name) {
		// TODO Auto-generated method stub
		return memberDao.add(loginId,loginPw,name);
	}
}
