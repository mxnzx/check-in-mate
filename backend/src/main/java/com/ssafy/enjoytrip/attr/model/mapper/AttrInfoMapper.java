package com.ssafy.enjoytrip.attr.model.mapper;


import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.attr.model.AttrDescriptionDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.attr.model.GugunDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;


//지역별 여행지Mapper
@Mapper
public interface AttrInfoMapper {

	List<SidoDto> sidoList() throws SQLException;
	List<GugunDto> gugunList(String sidoCode) throws SQLException;
	List<AttrInfoDto> attrList(String sidoCode, String gugunCode, String contentTypeId) throws SQLException;
	AttrDescriptionDto attrDescription(String contentId) throws SQLException;
}
