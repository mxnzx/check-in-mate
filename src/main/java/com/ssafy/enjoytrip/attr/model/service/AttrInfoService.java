package com.ssafy.enjoytrip.attr.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.attr.model.GugunDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;

public interface AttrInfoService {

	List<SidoDto> sidoList() throws Exception;
	List<GugunDto> gugunList(String sidoCode) throws Exception;
	List<AttrInfoDto> attrList() throws Exception;
	

}
