package com.jtp.example.mysqlTextBoard;

import java.util.Scanner;

import com.jtp.example.mysqlTextBoard.controller.ArticleController;
import com.jtp.example.mysqlTextBoard.controller.Controller;
import com.jtp.example.mysqlTextBoard.controller.MemberController;
import com.jtp.example.mysqlTextBoard.service.ArticleService;
import com.jtp.example.mysqlTextBoard.service.MemberService;
import com.jtp.example.mysqlTextBoard.sesseion.Session;

public class Container {

	public static Scanner scanner;
	public static MemberService memberService;
	public static ArticleService articleService;
	
	public static Session session;
	public static Controller articleController;
	public static Controller memberController;

	static {
		scanner = new Scanner(System.in);
		memberService = new MemberService();
		articleService = new ArticleService();
		
		session = new Session();
		articleController = new ArticleController();
		memberController = new MemberController();
	}
}
