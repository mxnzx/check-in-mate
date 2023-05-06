package com.ssafy.enjoytrip.photospot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PhotospotDto {
	private int photospotNo;
	private String userId;
	private String title;
	private String content;
	private String imgSrc;
	private String Addr;
	private int hit;
	private String registerTime;
	private int sidoCode;
	private int gugunCode;
	private int contentTypeId;

}
