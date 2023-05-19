package com.ssafy.enjoytrip.plan.model.dao;


import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.plan.model.PlanDto;



//나의여행계획 DAO
public interface PlanArticleDao {
	// 나의여행계획 게시글 작성
	void write(PlanDto planDto) throws SQLException;

	// 나의여행계획 게시글 리스트 가져오기
	List<PlanDto> list() throws SQLException;

	// 나의여행계획 게시글 수정
	void modify(PlanDto planDto) throws SQLException;

	// 나의여행계획 게시글 삭제
	void delete(int planAritcleNo) throws SQLException;

	// 조회수 갱신
	void updateHit(int planAritcleNo) throws SQLException;

	// 특정 나의여행계획 게시글 가져오기
//	BoardDto getPhotospot(int planAritcleNo) throws SQLException;
	
	// 검색어가 여행지명에 포함된 여행지 목록 가져오기
	List<AttrInfoDto> getAttraction(String keyword) throws SQLException;
	
	// 선택한 여행지를 나의 경로에 추가하기 
	void writeAttr(AttrInfoDto attrInfoDto) throws SQLException;
	
	
	// 선택한 여행지 목록에서 특정 여행지 삭제하기
	void deleteAttr(int contentId) throws SQLException;
	
	// 선택한 여행지 목록 초기화하기(전체 삭제)
	void deleteAttrAll() throws SQLException;

}
