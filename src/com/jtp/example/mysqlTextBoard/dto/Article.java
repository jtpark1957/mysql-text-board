package com.jtp.example.mysqlTextBoard.dto;

public class Article {
	public int id;
	public String title;
	public String regDate;
	public String updateDate;
	public String body;
	public int memberId;
	public int boardId ;
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
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + "]";
	}
}
