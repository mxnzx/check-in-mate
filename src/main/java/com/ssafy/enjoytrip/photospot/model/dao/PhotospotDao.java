package com.ssafy.enjoytrip.photospot.model.dao;


import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.photospot.model.PhotospotDto;



//포토스팟(핫플레이스) DAO
public interface PhotospotDao {
	// 포토스팟 게시글 작성
	void write(PhotospotDto photospotDto) throws SQLException;

	// 포토스팟 게시글 리스트 가져오기
	List<PhotospotDto> list() throws SQLException;

	// 포토스팟 게시글 수정
	void modify(PhotospotDto photospotDto) throws SQLException;

	// 포토스팟 게시글 삭제
	void delete(int photospotNo) throws SQLException;

	// 조회수 갱신
	void updateHit(int photospotNo) throws SQLException;

	// 특정 포토스팟 게시글 가져오기
//	BoardDto getPhotospot(int photospotNo) throws SQLException;

	// 게시글에 업로드한 주소의 지도 열기 
	void openMap(int photospotNo) throws SQLException;

}
