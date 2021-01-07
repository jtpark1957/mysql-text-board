package com.jtp.example.mysqlTextBoard.controller;

import java.util.List;

import com.jtp.example.mysqlTextBoard.dto.Article;
import com.jtp.example.mysqlTextBoard.service.ArticleService;

public class ArticleController {
	private ArticleService articleService;
	
	public ArticleController() {
		articleService = new ArticleService();
	}
	public void showList() {
		System.out.println("== 게시물 리스트 ==");
		List<Article> articles = articleService.getArticles();
		System.out.println(articles);
		// TODO Auto-generated method stub
		
	}

}
