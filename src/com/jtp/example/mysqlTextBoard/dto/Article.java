package com.jtp.example.mysqlTextBoard.dto;

import java.util.Map;

public class Article {
	public int id;
	public String title;
	public String regDate;
	public String updateDate;
	public String body;
	public int memberId;
	public int boardId ;
	public Article() {
		
	}
	public Article(int id, String regDate, String updateDate, String title, String body, int memberId,
			int boardId) {
		this.id = id;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.boardId = boardId;
		// TODO Auto-generated constructor stub
	}
	public Article(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regDate = (String) articleMap.get("regDate");
		this.updateDate = (String) articleMap.get("updateDate");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		this.memberId = (int) articleMap.get("memberId");
		this.boardId = (int) articleMap.get("boardId");
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + "]";
	}
}
