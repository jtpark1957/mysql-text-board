package com.jtp.example.mysqlTextBoard.service;


import com.jtp.example.mysqlTextBoard.dao.MemberDao;
import com.jtp.example.mysqlTextBoard.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	public MemberService() {
		memberDao = new MemberDao();
	}
	
	public int join(String loginId, String loginPw, String name) {
		// TODO Auto-generated method stub
		return memberDao.add(loginId,loginPw,name);
	}

	public Member getMemberById(int memberId) {
		// TODO Auto-generated method stub
		return memberDao.getMemberById(memberId);
	}

	public Member getMemberByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return memberDao.getMemberByLoginId(loginId);
	}
}
