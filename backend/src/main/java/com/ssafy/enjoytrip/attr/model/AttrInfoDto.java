package com.ssafy.enjoytrip.attr.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 관광지 정보 객체
@Getter
@Setter
@ToString
public class AttrInfoDto {
	private int contentId;
	private int contentTypeId;
	private String title;
	private String address1;
	private String address2;
	private String zipCode;
	private String tel;
	private String imgUrl1;
	private String imgUrl2;
	private int readCount;
	private int sidoCode;	
	private int gugunCode;
	private double latitude;
	private double longitude;
	private String mLevel;
}
