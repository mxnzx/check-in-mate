package com.ssafy.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;

//자유게시판 DAO
public interface BoardDao {

	// 자유게시판 글 작성
	void writeArticle(BoardDto boardDto) throws SQLException;

	// 자유게시판 글 목록 가져오기
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;

	// 자유게시판 글의 총 개수 가져오기
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	// 자유게시판 글 상세보기
	BoardDto getArticle(int articleNo) throws SQLException;

	// 조회수 업데이트
	void updateHit(int articleNo) throws SQLException;

	// 자유게시판 글 수정하기
	void modifyArticle(BoardDto boardDto) throws SQLException;

	// 자유게시판 글 삭제하기
	void deleteArticle(int articleNo) throws SQLException;

	// 자유게시판 글 전체 삭제하기
	void deleteArticleAll(String id) throws SQLException;
}
