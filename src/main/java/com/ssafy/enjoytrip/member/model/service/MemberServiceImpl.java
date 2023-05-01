package com.ssafy.enjoytrip.member.model.service;


import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.dao.MemberDao;
import com.ssafy.enjoytrip.member.model.dao.MemberDaoImpl;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	
	private MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
//		return sqlSession.getMapper(MemberMapper.class).idCheck(userId);
		return memberMapper.idCheck(userId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
//		sqlSession.getMapper(MemberMapper.class).joinMember(memberDto);
		memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(Map<String, String> map) throws Exception {
//		return sqlSession.getMapper(MemberMapper.class).loginMember(map);
		return memberMapper.loginMember(map);
	}
	
	/* ADMIN */
	@Override
	public List<MemberDto> listMember(Map<String, Object> map) throws Exception {
		return memberMapper.listMember(map);
	}

	@Override
	public MemberDto getMember(String userId) throws Exception {
		return memberMapper.getMember(userId);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		memberMapper.updateMember(memberDto);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		memberMapper.deleteMember(userId);		
	}

	@Override
	public String encryptPassword(String pwd) {
		String salt = BCrypt.gensalt();
		String hashedPwd = BCrypt.hashpw(pwd, salt);
		return hashedPwd;
	}

}
