package com.ssafy.enjoytrip.attr.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.attr.model.GugunDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;
import com.ssafy.enjoytrip.attr.model.mapper.AttrInfoMapper;

@Service("AttrInfoServiceImpl")
public class AttrInfoServiceImpl implements AttrInfoService {
	
	@Autowired
	private AttrInfoMapper attrInfoDao;

	@Autowired
	private SqlSession sqlSession;
	
	public AttrInfoServiceImpl(AttrInfoMapper attrInfoDao) {
		super();
		this.attrInfoDao = attrInfoDao;
	}

	@Override
	public List<SidoDto> sidoList() throws Exception {
		return attrInfoDao.sidoList();
	}

	@Override
	public List<GugunDto> gugunList(String sidoCode) throws Exception {
		return attrInfoDao.gugunList(sidoCode);
	}

	@Override
	public List<AttrInfoDto> attrList(String sidoCode, String gugunCode, String contentTypeId) throws Exception {
		return attrInfoDao.attrList(sidoCode, gugunCode, contentTypeId);
	}

	@Override
	public Map<String, Object> attrDescription(String contentId) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("contentId", contentId);
		Map<String, Object> resultMap = sqlSession.selectOne("attrDescription", paramMap);
		return resultMap;
	}
}
