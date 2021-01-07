package com.jtp.example.mysqlTextBoard;

import java.util.Scanner;

import com.jtp.example.mysqlTextBoard.controller.ArticleController;

public class App {
	public void run() {
		Scanner sc = Container.scanner;

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("article list")) {
				ArticleController articlecontroller = new ArticleController();
				articlecontroller.showList();
			} else if (cmd.equals("system exit")) {
				System.out.println("== 시스템 종료 ==");
				break;
			}
		}
	}
}