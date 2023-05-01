package com.ssafy.enjoytrip.notice.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.notice.model.NoticeDto;
import com.ssafy.enjoytrip.util.PageNavigation;

@Mapper
public interface NoticeMapper {

	void writeArticle(NoticeDto noticeDto) throws Exception;
	void registerFile(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listArticle(Map<String, Object> map) throws Exception;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	NoticeDto getArticle(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	
	void modifyArticle(NoticeDto noticeDto) throws Exception;
	void deleteFile(int articleNo) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	
}
