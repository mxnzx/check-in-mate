package com.ssafy.enjoytrip.notice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeDto {

	private int articleNo;
	private String userId;
	private String userName;
	private String subject;
	private String content;
	private int hit;
	private String registerTime;


}
