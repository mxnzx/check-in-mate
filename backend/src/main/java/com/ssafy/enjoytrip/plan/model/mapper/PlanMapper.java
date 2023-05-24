package com.ssafy.enjoytrip.plan.model.mapper;


import com.ssafy.enjoytrip.plan.model.DataByDayDto;
import com.ssafy.enjoytrip.plan.model.MyPickPlaceDto;
import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.PlanFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//나의여행계획 DAO
@Mapper
public interface PlanMapper {

	// 나의여행계획 게시글 작성
	int writeArticle(PlanDto planDto) throws SQLException;

	// 나의여행계획 게시글 중 일수별 테이블 작성
	int writeArticleByDay(DataByDayDto dataByDayDto) throws SQLException;

	//나의 여행계획 게시글 중 일수별 중 여행지 순서 테이블 작성
	int writeArticleByDayBySeq(MyPickPlaceDto myPickPlaceDto) throws SQLException;

	// 나의여행계획 게시글 리스트 가져오기
	List<PlanDto> list(Map<String, Object> param) throws SQLException;


	// 나의여행계획 게시글 삭제
	void delete(int planAritcleNo) throws SQLException;

	// 특정 나의여행계획 게시글 가져오기
	PlanDto getArticle(int planAritcleNo) throws SQLException;

    // 파일 등록
    void registerFile(List<PlanFileDto> fileInfos) throws Exception;

//	// 나의여행계획 게시글 수정
//	void modify(PlanDto planDto) throws SQLException;
//	// 조회수 갱신
//	void updateHit(int planAritcleNo) throws SQLException;
//	// 검색어가 여행지명에 포함된 여행지 목록 가져오기
//	List<PlanDto> getAttraction(String keyword) throws SQLException;



}
