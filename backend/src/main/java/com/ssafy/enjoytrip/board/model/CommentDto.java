package com.ssafy.enjoytrip.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto {

	private int commentNo;
	private int articleNo;
	private String userId;
	private String parentId;
	private String content;
	private String registerTime;	
	
}
