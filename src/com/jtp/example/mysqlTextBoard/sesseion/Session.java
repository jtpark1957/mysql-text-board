package com.jtp.example.mysqlTextBoard.sesseion;

public class Session {

	public static int loginedMemberId;

	public boolean isLogined() {
		return loginedMemberId > 0;
	}

	public int getLoginedMemberId() {
		return loginedMemberId;
	}

	public void logout() {
		loginedMemberId = 0;

	}

}
