package com.ssafy.enjoytrip.hotplace.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HotplaceScrapDto {
	private String userid;
	private int articleno;
	private List<HotplaceFileDto> savefile;
	private String title;
}
