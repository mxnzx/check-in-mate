package com.ssafy.attr.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.attr.model.AttrInfoDto;
import com.ssafy.attr.model.SidoDto;

//지역별 여행지 DAO
public interface AttrInfoDao {
	// 시도 정보 가져오기
	List<SidoDto> getCities() throws SQLException;

	// 구군 정보 가져오기
	List<AttrInfoDto> getAttrInfo(int sido, int gugun, int type) throws SQLException;

}
