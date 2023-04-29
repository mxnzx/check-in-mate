package com.ssafy.notice.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.notice.model.NoticeDto;

// 공지사항 DAO
public interface NoticeDao {

	// 공지사항 글 작성
	void writeArticle(NoticeDto noticeDto) throws SQLException;

	// 공지사항 글 목록 가져오기
	List<NoticeDto> listArticle(Map<String, Object> param) throws SQLException;

	// 공지사항 글의 총 개수 가져오기
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	// 공지사항 글 상세보기
	NoticeDto getArticle(int articleNo) throws SQLException;

	// 조회수 업데이트
	void updateHit(int articleNo) throws SQLException;

	// 공지사항 글 수정하기
	void modifyArticle(NoticeDto noticeDto) throws SQLException;

	// 공지사항 글 삭제하기
	void deleteArticle(int articleNo) throws SQLException;

	// 공지사항 글 전체 삭제하기
	void deleteArticleAll(String id) throws SQLException;
}
