package com.ssafy.enjoytrip.attr.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;

public interface AttrInfoService {
	// 시도 정보 가져오기
	List<SidoDto> getCities() throws Exception;

	// 구군 정보 가져오기
	List<AttrInfoDto> getAttrInfo(int sido, int gugun, int type) throws Exception;


}
