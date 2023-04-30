package com.ssafy.enjoytrip.notice.model.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.notice.model.NoticeDto;
import com.ssafy.enjoytrip.util.PageNavigation;



public interface NoticeService {

	void writeArticle(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listArticle(Map<String, String> map) throws Exception;
	List<NoticeDto> sortListArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	NoticeDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(NoticeDto noticeDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	void deleteArticleAll(String id) throws Exception;
}
