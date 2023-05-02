package com.ssafy.enjoytrip.notice.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.notice.model.NoticeDto;
import com.ssafy.enjoytrip.util.PageNavigation;

@Mapper
public interface NoticeMapper {

	// 공지 작성
	void writeArticle(NoticeDto noticeDto) throws Exception;
	// 미구현
	void registerFile(NoticeDto noticeDto) throws Exception;
	// 공지 글목록 가져오기
	List<NoticeDto> listArticle(Map<String, Object> map) throws Exception;
	// 공지 총 글개수 가져오기
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	// 공지 내용 가져오기
	NoticeDto getArticle(int articleNo) throws Exception;
	// 조회수
	void updateHit(int articleNo) throws Exception;
	
	// 공지 수정
	void modifyArticle(NoticeDto noticeDto) throws Exception;
	// 미구현
	void deleteFile(int articleNo) throws Exception;
	// 공지 삭제
	void deleteArticle(int articleNo) throws Exception;
	
}
