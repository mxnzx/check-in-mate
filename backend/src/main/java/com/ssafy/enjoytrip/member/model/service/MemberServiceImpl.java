package com.ssafy.enjoytrip.member.model.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>"  +memberDto.getUserid() + " " + memberDto.getUserpwd());
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		System.out.println("dfdfdfd" + sqlSession.getMapper(MemberMapper.class).login(memberDto));
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}
	
	
	
	private MemberMapper memberMapper;

	public MemberServiceImpl(MemberMapper memberMapper) {
		super();
		this.memberMapper = memberMapper;
	}
//
//	// 아이디 체크
//	@Override
//	public int idCheck(String userId) throws Exception {
////		return sqlSession.getMapper(MemberMapper.class).idCheck(userId);
//		return memberMapper.idCheck(userId);
//	}
//
	// 회원가입
	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
//		sqlSession.getMapper(MemberMapper.class).joinMember(memberDto);
		System.out.println("memberserviceimpl joinmember 실행");
		memberMapper.joinMember(memberDto);
	}
//
//	// 로그인
//	@Override
//	public MemberDto loginMember(Map<String, String> map) throws Exception {
////		return sqlSession.getMapper(MemberMapper.class).loginMember(map);
//		return memberMapper.loginMember(map);
//	}
//	
//	// 회원출력
//	/* ADMIN */
//	@Override
//	public List<MemberDto> listMember(Map<String, Object> map) throws Exception {
//		return memberMapper.listMember(map);
//	}
//
//	// 회원 정보 가져오기
//	@Override
//	public MemberDto getMember(String userId) throws Exception {
//		return memberMapper.getMember(userId);
//	}
//
	// 회원정보 수정
	@Override
	public void updateMember(MemberDto memberDto) throws Exception {
		memberMapper.updateMember(memberDto);
	}

	// 회원탈퇴
	@Override
	public void deleteMember(String userid) throws Exception {
		memberMapper.deleteMember(userid);		
	}
//
//	@Override
//	public String encryptPassword(String pwd) {
//		String salt = BCrypt.gensalt();
//		String hashedPwd = BCrypt.hashpw(pwd, salt);
//		return hashedPwd;
//	}

	// 아이디 찾기
	@Override
	public MemberDto findId(String emailid, String emaildomain) throws Exception {
//		System.out.println("service findid >>" + sqlSession.getMapper(MemberMapper.class).findId(emailid, emaildomain));
//		return sqlSession.getMapper(MemberMapper.class).findId(emailid, emaildomain);
		return memberMapper.findId(emailid, emaildomain);
	}

}
