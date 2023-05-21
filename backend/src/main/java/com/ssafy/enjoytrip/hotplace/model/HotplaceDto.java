package com.ssafy.enjoytrip.hotplace.model;

import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HotplaceDto {
	private int articleno;
	private String title;
	private int hit;
	private String content;
	private String registertime;
	private String userid;
	private String imgbig;
	private String imgsmall;
	private List<HotplaceFileDto> fileInfos;
	private String savefile;
	private String savefolder;

}
