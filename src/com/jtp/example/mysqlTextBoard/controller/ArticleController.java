package com.jtp.example.mysqlTextBoard.controller;

import java.util.List;
import java.util.Scanner;

import com.jtp.example.mysqlTextBoard.Container;
import com.jtp.example.mysqlTextBoard.dto.Article;
import com.jtp.example.mysqlTextBoard.dto.Board;
import com.jtp.example.mysqlTextBoard.dto.Member;
import com.jtp.example.mysqlTextBoard.service.ArticleService;
import com.jtp.example.mysqlTextBoard.service.MemberService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
	}

	public void doCommand(String cmd) {
		if (cmd.startsWith("article createboard")) {
			doCreateBoard(cmd);
		} else if (cmd.startsWith("article selectboard")) {
			doSelectBoard(cmd);
		} else if (cmd.startsWith("article list")) {
			showList();
		} else if (cmd.startsWith("article detail")) {
			showDeatil(cmd);
		} else if (cmd.startsWith("article delete")) {
			doDelete(cmd);
		} else if (cmd.startsWith("article write")) {
			doWrite(cmd);
		} else if (cmd.startsWith("article modify")) {
			doModify(cmd);
		}
	}

	private void showList() {
		String boardCode = Container.session.getCurrentBoardCode();
		Board board = articleService.getBoardByCode(boardCode);
		System.out.printf("== %s 게시물 리스트 ==\n", board.name);

		List<Article> articles = articleService.getForPrintArticles(board.id);

		System.out.println("번호 / 작성 / 수정 / 작성자 / 제목");

		for (Article article : articles) {

			String writer = article.extra__writer;

			System.out.printf("%d / %s / %s / %s / %s\n", article.id, article.regDate, article.updateDate, writer,
					article.title);
		}
		// TODO Auto-generated method stub

	}

	private void showDeatil(String cmd) {
		System.out.println("== 게시물 디테 ==");
		if (cmd.split("article")[1].equals(" detail")) {
			return;
		}
		int input = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(input);
		if (article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return;
		}
		Member member = memberService.getMemberById(article.memberId);
		String writer = member.name;

		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("수정날짜 : %s\n", article.updateDate);
		System.out.printf("작성자 : %s\n", writer);
		System.out.printf("제목 : %s\n", article.title);
		System.out.printf("내용 : %s\n", article.body);

	}

	private void doDelete(String cmd) {
		System.out.println("== 게시물 삭제 ==");
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}
		if (cmd.split("article")[1].equals(" delete")) {
			return;
		}
		int input = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(input);
		if (article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return;
		}
		if (article.memberId != Container.session.getLoginedMemberId()) {
			System.out.println("권한이 없습니다.");
			return;
		}

		articleService.delete(input);
		System.out.printf("%d번 게시물을 삭제하였습니다.\n", input);

	}

	private void doWrite(String cmd) {
		System.out.println("== 게시물 작성 ==");
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		Scanner sc = Container.scanner;

		System.out.printf("제목 : ");
		String title = sc.nextLine();

		System.out.printf("내용 : ");
		String body = sc.nextLine();

		int memberId = Container.session.getLoginedMemberId(); // 임시 1
		Board board = articleService.getBoardByCode(Container.session.getCurrentBoardCode());
		int boardId = board.id; // 임시 1

		int id = articleService.write(boardId, memberId, title, body);

		System.out.printf("%d번 게시물을 생성하였습니다.\n", id);

	}

	private void doModify(String cmd) {
		System.out.println("== 게시물 수 ==");
		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		if (cmd.split("article")[1].equals(" modify")) {
			return;
		}
		int input = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(input);

		if (article == null) {
			System.out.println("존재하지 않는 게시물 입니다.");
			return;
		}
		if (article.memberId != Container.session.getLoginedMemberId()) {
			System.out.println("권한이 없습니다.");
			return;
		}

		Member member = memberService.getMemberById(article.memberId);
		String writer = member.name;

		System.out.println("== 게시물 수 ==");
		System.out.printf("번호 : %d\n", article.id);
		System.out.printf("작성날짜 : %s\n", article.regDate);
		System.out.printf("작성자 : %s\n", writer);

		Scanner sc = Container.scanner;

		System.out.printf("제목 : ");
		String title = sc.nextLine();

		System.out.printf("내용 : ");
		String body = sc.nextLine();

		articleService.modify(input, title, body);

		System.out.printf("%d번 게시물을 수정하였습니다.\n", input);

	}

	private void doCreateBoard(String cmd) {
		System.out.println("== 게시판 작성 ==");

		if (Container.session.isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
		}

		Member member = memberService.getMemberById(Container.session.getLoginedMemberId());

		if (member.isAdmin() == false) {
			System.out.println("관리자만 게시판을 생성할 수 있습니다.");
			return;
		}

		Scanner sc = Container.scanner;

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		if (articleService.isMakeBoardAvailableName(name) == false) {
			System.out.println("해당 이름은 이미 사용중입니다.");
			return;
		}

		System.out.printf("코드 : ");
		String code = sc.nextLine();

		if (articleService.isMakeBoardAvailableCode(code) == false) {
			System.out.println("해당 코드는 이미 사용중입니다.");
			return;
		}

		int id = articleService.makeBoard(code, name);

		System.out.printf("%d번 게시판을 생성하였습니다.\n", id);
	}

	private void doSelectBoard(String cmd) {
		System.out.println("== 게시판 선택 ==");

		System.out.println("= 게시판 목록 =");
		System.out.println("번호 / 생성날짜 / 코드 / 이름 / 게시물 수");

		List<Board> boards = articleService.getForPrintBoards();

		for (Board board : boards) {
			int articlesCount = articleService.getArticlesCount(board.id);
			System.out.printf("%d / %s / %s / %s / %d\n", board.id, board.regDate, board.code, board.name,
					articlesCount);
		}

		System.out.printf("게시판 코드 : ");
		String inputedBoardCode = Container.scanner.nextLine().trim();

		Board board = articleService.getBoardByCode(inputedBoardCode);

		if (board == null) {
			System.out.println("코드를 잘 못 입력하였습니다.");
			return;
		}

		Container.session.setCurrentBoardCode(board.code);

		System.out.printf("%s 게시판으로 변경합니다.\n", board.name);
	}

}
