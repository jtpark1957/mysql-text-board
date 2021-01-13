package com.jtp.example.mysqlTextBoard.service;

import java.util.List;

import com.jtp.example.mysqlTextBoard.dao.ArticleDao;
import com.jtp.example.mysqlTextBoard.dto.Article;
import com.jtp.example.mysqlTextBoard.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;
		
	public ArticleService() {
		articleDao = new ArticleDao();
	}
	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		return articleDao.getArticles();
		
	}
	public Article getArticle(int id) {
		// TODO Auto-generated method stub
		return articleDao.getArticle(id);
	}
	public int delete(int id) {
		return articleDao.delete(id);
	}
	public int write(int boardId, int memberId, String title, String body) {
		// TODO Auto-generated method stub
		return articleDao.add(boardId, memberId, title, body);
	}
	public int modify(int id, String title, String body) {
		// TODO Auto-generated method stub
		return articleDao.modify(id,title,body);
	}
	public List<Article> getForPrintArticles(int boardId) {
		return articleDao.getForPrintArticles(boardId);
	}
	public Board getBoardByCode(String boardCode) {
		// TODO Auto-generated method stub
		return articleDao.getBoardByCode(boardCode);
	}
	public boolean isMakeBoardAvailableName(String name) {
		Board board = articleDao.getBoardByName(name);

		return board == null;
	}

	public boolean isMakeBoardAvailableCode(String code) {
		Board board = articleDao.getBoardByCode(code);

		return board == null;
	}

	public int makeBoard(String code, String name) {
		return articleDao.makeBoard(code, name);
	}
}
