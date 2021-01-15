package com.jtp.example.mysqlTextBoard.controller;

import java.util.List;
import java.util.Scanner;

import com.jtp.example.mysqlTextBoard.Container;
import com.jtp.example.mysqlTextBoard.dto.Article;
import com.jtp.example.mysqlTextBoard.dto.Board;
import com.jtp.example.mysqlTextBoard.dto.Member;
import com.jtp.example.mysqlTextBoard.service.ArticleService;
import com.jtp.example.mysqlTextBoard.service.BuildService;
import com.jtp.example.mysqlTextBoard.service.MemberService;
import com.jtp.example.mysqlTextBoard.util.Util;

public class BuildController extends Controller {
	private BuildService buildService;
	private MemberService memberService;
	private ArticleService articleService;

	public BuildController() {
		articleService = Container.articleService;
		buildService = Container.buildService;
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("build site")) {
			doBuildSite(cmd);
		} 
	}

	private void doBuildSite(String cmd) {
		System.out.println("== 사이트 생성 ==");

		buildService.buildSite();
	}
}