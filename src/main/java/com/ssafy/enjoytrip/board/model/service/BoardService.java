package com.ssafy.enjoytrip.board.model.service;


import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.util.PageNavigation;


public interface BoardService {

	void writeArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
	List<BoardDto> sortListArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	BoardDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(BoardDto boardDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	void deleteArticleAll(String id) throws Exception;
	
}
