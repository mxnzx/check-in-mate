package com.ssafy.enjoytrip.attr.model.dao;


import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.attr.model.GugunDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;


//지역별 여행지 DAO
@Mapper
public interface AttrInfoDao {

	List<SidoDto> sidoList() throws SQLException;
	List<GugunDto> gugunList(String sidoCode) throws SQLException;
	List<AttrInfoDto> attrList() throws SQLException;

}
