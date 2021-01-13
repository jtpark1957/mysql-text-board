package com.jtp.example.mysqlTextBoard;

import java.util.Scanner;

import com.jtp.example.mysqlTextBoard.controller.ArticleController;
import com.jtp.example.mysqlTextBoard.controller.MemberController;
import com.sbs.example.mysqlutil.MysqlUtil;

public class App {
	public void run() {
		Scanner sc = Container.scanner;

		ArticleController articlecontroller = new ArticleController();
		MemberController membercontroller = new MemberController();
		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			MysqlUtil.setDBInfo("127.0.0.1", "jttpp", "123412", "textBoard");
			boolean needToExit = false;
			if(cmd.startsWith("article")) {
				articlecontroller.doCommand(cmd);
			} else if(cmd.startsWith("member")) {
				membercontroller.doCommand(cmd);
			} else if (cmd.equals("system exit")) {
			
				System.out.println("== 시스템 종료 ==");
				needToExit = true;
			}
			MysqlUtil.closeConnection();
			if(needToExit) {
				break;
			}
			
		}
	}
}