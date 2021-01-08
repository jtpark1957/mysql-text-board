package com.jtp.example.mysqlTextBoard.service;

import java.util.List;

import com.jtp.example.mysqlTextBoard.dao.ArticleDao;
import com.jtp.example.mysqlTextBoard.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;
		
	public ArticleService() {
		articleDao = new ArticleDao();
	}
	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		return articleDao.getArticles();
		
	}
 
}
