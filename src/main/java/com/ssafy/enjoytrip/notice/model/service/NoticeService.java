package com.ssafy.enjoytrip.notice.model.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.notice.model.NoticeDto;
import com.ssafy.enjoytrip.util.PageNavigation;



public interface NoticeService {

	// 공지 작성
	void writeArticle(NoticeDto noticeDto) throws Exception;
	// 공지 목록 출력
	List<NoticeDto> listArticle(Map<String, String> map) throws Exception;
	// 미구현
	List<NoticeDto> sortListArticle(Map<String, String> map) throws Exception;
	// 페이지네이게이션
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	// 공지 내용 가져오기
	NoticeDto getArticle(int articleNo) throws Exception;
	// 조회수
	void updateHit(int articleNo) throws Exception;
	
	// 공지 수정하기
	void modifyArticle(NoticeDto noticeDto) throws Exception;
	// 공지 삭제하기
	void deleteArticle(int articleNo) throws Exception;
	// 미구현
	void deleteArticleAll(String id) throws Exception;
}
