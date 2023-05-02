package com.ssafy.enjoytrip.board.model;

public class CommentDto {

	private int commentNo;
	private int articleNo;
	private String userId;
	private String parentId;
	private String comment;
	private String registerTime;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	@Override
	public String toString() {
		return "CommentDto [commentNo=" + commentNo + ", articleNo=" + articleNo + ", userId=" + userId + ", comment="
				+ comment + ", registerTime=" + registerTime + "]";
	}
	
	
}
