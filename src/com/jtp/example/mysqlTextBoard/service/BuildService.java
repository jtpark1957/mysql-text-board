package com.jtp.example.mysqlTextBoard.service;

import java.util.List;

import com.jtp.example.mysqlTextBoard.Container;
import com.jtp.example.mysqlTextBoard.dto.Article;
import com.jtp.example.mysqlTextBoard.dto.Board;
import com.jtp.example.mysqlTextBoard.util.Util;

public class BuildService {

	private ArticleService articleService;
	private MemberService memberService;

	public BuildService() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void buildSite() {
		System.out.println("site/article 폴더 생성");
		Util.rmdir("site");
		Util.mkdirs("site");
		Util.copy("site_template/app.css", "site/app.css");
		buildIndexPage();
		buildArticleDetailPages();
		buildArticleListPages();

		
	}

	private void buildArticleListPages() {
		List<Board> boards = articleService.getForPrintBoards();

		String bodyTemplate = Util.getFileContents("site_template/article_list.html");
		String foot = Util.getFileContents("site_template/foot.html");
		for (Board board : boards) {
			StringBuilder sb = new StringBuilder();

			sb.append(getHeadHtml("article_list_" + board.code));

			String fileName = "article_list_" + board.code + "_1.html";

			List<Article> articles = articleService.getForPrintArticles(board.id);

			StringBuilder mainContent = new StringBuilder();

			for (Article article : articles) {
				String link = "article_detail_" + article.id + ".html";

				mainContent.append("<div>");
				mainContent.append("<div class=\"article-list__cell-id\">" + article.id + "</div>");
				mainContent.append("<div class=\"article-list__cell-reg-date\">" + article.regDate + "</div>");
				mainContent.append("<div class=\"article-list__cell-writer\">" + article.extra__writer + "</div>");
				mainContent.append("<div class=\"article-list__cell-title\">");

				mainContent.append("<a href=\"" + link + "\" class=\"hover-underline\">" + article.title + "</a>");

				mainContent.append("</div>");
				mainContent.append("</div>");
			}

			String body = bodyTemplate.replace("${article-list__main-content}", mainContent.toString());

			sb.append(body);
			sb.append(foot);

			String filePath = "site/" + fileName;

			Util.writeFile(filePath, sb.toString());
			System.out.println(filePath + " 생성");
		}
	}

	private void buildIndexPage() {
		StringBuilder sb = new StringBuilder();

		String head = getHeadHtml("index");
		String foot = Util.getFileContents("site_template/foot.html");

		String mainHtml = Util.getFileContents("site_template/index.html");

		sb.append(head);
		sb.append(mainHtml);
		sb.append(foot);

		String filePath = "site/index.html";
		Util.writeFile(filePath, sb.toString());
		System.out.println(filePath + " 생성");
	}

	private void buildArticleDetailPages() {
		List<Article> articles = articleService.getArticles();
		String head = getHeadHtml("article_detail");
		String foot = Util.getFileContents("site_template/foot.html");
		for (Article article : articles) {
			StringBuilder sb = new StringBuilder();

			sb.append(head);

			sb.append("번호 : " + article.id + "<br>");
			sb.append("작성날짜 : " + article.regDate + "<br>");
			sb.append("갱신날짜 : " + article.updateDate + "<br>");
			sb.append("제목 : " + article.title + "<br>");
			sb.append("내용 : " + article.body + "<br>");
			sb.append("<a href=\"article_detail_" + (article.id - 1) + ".html\">이전글</a><br>");
			sb.append("<a href=\"article_detail_" + (article.id + 1) + ".html\">다음글</a><br>");

			sb.append("</div>");

			sb.append(foot);

			String fileName = "article_detail_" + article.id + ".html";

			String filePath = "site/" + fileName;

			Util.writeFile(filePath, sb.toString());

			System.out.println(filePath + " 생성");
		}
	}

	private String getHeadHtml(String pageName) {
		String head = Util.getFileContents("site_template/head.html");

		StringBuilder boardMenuContentHtml = new StringBuilder();
		List<Board> forPrintBoards = articleService.getForPrintBoards();

		for (Board board : forPrintBoards) {
			boardMenuContentHtml.append("<li>");

			String link = "article_list_" + board.code + "_1.html";

			boardMenuContentHtml.append("<a href=\"" + link + "\" class=\"block\">");

			boardMenuContentHtml.append(getTitleBarContentByPageName("article_list_" + board.code));

			boardMenuContentHtml.append("</a>");

			boardMenuContentHtml.append("</li>");
		}

		head = head.replace("${menu-bar__menu-1__board-menu-content}", boardMenuContentHtml.toString());
		String titleBarContentHtml = getTitleBarContentByPageName(pageName);

		head = head.replace("${title-bar__content}", titleBarContentHtml);
		return head;
	}
	private String getTitleBarContentByPageName(String pageName) {
		if (pageName.equals("index")) {
			return "<i class=\"fas fa-home\"></i> <span>HOME</span>";
		} else if (pageName.equals("article_detail")) {
			return "<i class=\"fas fa-file-alt\"></i> <span>ARTICLE DETAIL</span>";
		} else if (pageName.startsWith("article_list_free")) {
			return "<i class=\"fab fa-free-code-camp\"></i> <span>FREE LIST</span>";
		} else if (pageName.startsWith("article_list_notice")) {
			return "<i class=\"fas fa-flag\"></i> <span>NOTICE LIST</span>";
		} else if (pageName.startsWith("article_list_")) {
			return "<i class=\"fas fa-clipboard-list\"></i> <span>" + pageName.replace("article_list_","</span>");
		}
		

		return "";
	}
}