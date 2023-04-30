package com.ssafy.enjoytrip.attr.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;
import com.ssafy.enjoytrip.attr.model.dao.AttrInfoDao;

@Service("AttrInfoServiceImpl")
public class AttrInfoServiceImpl implements AttrInfoService {
	
	@Autowired
	private AttrInfoDao attrInfoDao;
	
	public AttrInfoServiceImpl(AttrInfoDao attrInfoDao) {
		super();
		this.attrInfoDao = attrInfoDao;
	}

	@Override
	public List<SidoDto> getCities() throws Exception {
		return attrInfoDao.getCities();
	}

	@Override
	public List<AttrInfoDto> getAttrInfo(int sido, int gugun, int type) throws Exception {
		return null;
	}

}
