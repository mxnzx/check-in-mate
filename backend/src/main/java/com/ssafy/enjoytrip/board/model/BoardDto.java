package com.ssafy.enjoytrip.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDto {
	private int articleNo;
	private String userId;
	private String userName;
	private String title;
	private String content;
	private int hit;
	private String registerTime;
	private String category;
	private String place;
	private String date;
	private int peopleNum;
	private int commentCnt;
}
