package com.jtp.example.mysqlTextBoard.dto;

public class Article {
	public int id;
	public String title;
	public String regDate;
	public String updateDate;
	public String body;
	public int memberId;
	public int boardId ;
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + "]";
	}
}
