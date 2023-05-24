package com.ssafy.enjoytrip.plan.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.PlanFileDto;


public interface PlanService {
    // 파일 등록
    void registerFile(List<PlanFileDto> fileInfos) throws Exception;

    // 나의여행계획 게시글 작성
    void write(PlanDto planDto) throws SQLException, Exception;

    // 나의여행계획 게시글 리스트 가져오기
    List<PlanDto> list(Map<String, String> map) throws SQLException;

    // 나의여행계획 게시글 삭제
    void delete(int planAritcleNo, String path) throws SQLException;

    // 특정 나의여행계획 게시글 가져오기
    PlanDto getArticle(int planAritcleNo) throws SQLException;

//    // 조회수 갱신
//    void updateHit(int planAritcleNo) throws SQLException;

//    // 나의여행계획 게시글 수정
//    void modify(PlanDto planArticleDto) throws SQLException;

//    // 검색어가 여행지명에 포함된 여행지 목록 가져오기
//    List<AttrInfoDto> getAttraction(String keyword) throws SQLException;

}
