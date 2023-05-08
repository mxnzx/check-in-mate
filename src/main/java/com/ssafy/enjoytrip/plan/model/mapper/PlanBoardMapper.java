package com.ssafy.enjoytrip.plan.model.mapper;


import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.plan.model.PlanBoardDto;



//나의여행계획 Mapper
@Mapper
public interface PlanBoardMapper {
	// 나의여행계획 게시글 작성
	void write(PlanBoardDto planArticleDto) throws SQLException;

	// 나의여행계획 게시글 리스트 가져오기
	List<PlanBoardDto> list() throws SQLException;

	// 나의여행계획 게시글 수정
	void modify(PlanBoardDto planArticleDto) throws SQLException;

	// 나의여행계획 게시글 삭제
	void delete(int planAritcleNo) throws SQLException;

	// 조회수 갱신
	void updateHit(int planAritcleNo) throws SQLException;

	// 특정 나의여행계획 게시글 가져오기
	PlanBoardDto detail(int planAritcleNo) throws SQLException;
	
	// 검색어가 여행지명에 포함된 여행지 목록 가져오기
	List<AttrInfoDto> getAttraction(String keyword) throws SQLException;
	

}
