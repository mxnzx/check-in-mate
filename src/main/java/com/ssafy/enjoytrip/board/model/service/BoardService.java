package com.ssafy.enjoytrip.board.model.service;


import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.CommentDto;
import com.ssafy.enjoytrip.util.PageNavigation;


public interface BoardService {

	// 여행정보 공유 글작성
	void writeArticle(BoardDto boardDto) throws Exception;
	// 여행정보 공유 글 목록 출력
	List<BoardDto> listArticle(Map<String, String> map) throws Exception;
	List<BoardDto> sortListArticle(Map<String, String> map) throws Exception;
	// 페이지 네이게이션
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	// 여행정보 공유 글 내용 가져오기
	BoardDto getArticle(int articleNo) throws Exception;
	// 조회수
	void updateHit(int articleNo) throws Exception;
	
	// 여행정보 공유 글 수정하기
	void modifyArticle(BoardDto boardDto) throws Exception;
	// 여행정보 공유 글 삭제하기
	void deleteArticle(int articleNo, String path) throws Exception;
	// 미구현
	void deleteArticleAll(String id) throws Exception;
	
	// 댓글 작성
	void writeComment(CommentDto commentDto);
	// 댓글 목록 출력
	List<CommentDto> listComment(int article_no);
	// 댓글 삭제
	void deleteComment(int commentNo);
	
}
