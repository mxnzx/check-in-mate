package com.ssafy.enjoytrip.member.model.service;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.member.model.MemberDto;


public interface MemberService {

//	// 아이디 체크
//	int idCheck(String userId) throws Exception;
	// 회원가입
	void joinMember(MemberDto memberDto) throws Exception;

//	// 로그인
//	MemberDto loginMember(Map<String, String> map) throws Exception;
//	
//	/* Admin */
//	// 회원 출력
//	List<MemberDto> listMember(Map<String, Object> map) throws Exception;
//	// 회원 가져오기
	// 회원정보 수정
	void updateMember(MemberDto memberDto) throws Exception;
	// 회원정보 삭제
	void deleteMember(String userid) throws Exception;
//	// 미구현
//	String encryptPassword(String pwd);
	
	// 아이디 찾기 
	public MemberDto findId(String emailid, String emaildomain) throws Exception;
	
	// 비밀번호 찾기 
	public MemberDto findPassword(String userid, String username, String emailid, String emaildomain) throws SQLException;
	
	// 스크랩 가져오기
	public void getScrap(String userid) throws SQLException;
	
	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;

}
