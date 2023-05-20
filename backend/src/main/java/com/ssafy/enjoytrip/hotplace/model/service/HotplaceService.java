package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;

public interface HotplaceService {

	// 글작성
	void writeArticle(HotplaceDto hotplaceDto) throws Exception;
	
	// 글 목록 출력
	List<HotplaceDto> listArticle(Map<String, String> map) throws Exception;
	
	// 글 내용 가져오기 
	HotplaceDto getArticle(int articleno) throws Exception;
	
	// 글 수정하기
	void modifyArticle(HotplaceDto hotplaceDto) throws Exception;
	
	// 글 삭제하기
	void deleteArticle(int articleno, String path) throws Exception;
	
}
