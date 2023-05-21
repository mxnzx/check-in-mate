package com.ssafy.enjoytrip.hotplace.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceFileDto;


@Mapper
public interface HotplaceMapper {

	// 글작성
	void writeArticle(HotplaceDto hotplaceDto) throws SQLException;
	
	// 글 목록 출력
	List<HotplaceDto> listArticle(Map<String, Object> param) throws SQLException;
	
	// 글 내용 가져오기 
	HotplaceDto getArticle(int articleno) throws SQLException;
	
	// 글 수정하기
	void modifyArticle(HotplaceDto hotplaceDto) throws SQLException;
	
	// 글 삭제하기
	void deleteArticle(int articleno) throws SQLException;
	
	// 파일 등록
	void registerFile(HotplaceDto hotplaceDto) throws Exception;
	
 	// 파일 삭제
	void deleteFile(int articleno) throws Exception;
	
	// 파일 목록
	List<HotplaceFileDto> fileInfoList(int articleno) throws Exception;
	
}
