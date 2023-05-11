package com.ssafy.enjoytrip.member.model.service;


import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.member.model.MemberDto;


public interface MemberService {

	// 아이디 체크
	int idCheck(String userId) throws Exception;
	// 회원가입
	void joinMember(MemberDto memberDto) throws Exception;
	// 로그인
	MemberDto loginMember(Map<String, String> map) throws Exception;
	
	/* Admin */
	// 회원 출력
	List<MemberDto> listMember(Map<String, Object> map) throws Exception;
	// 회원 가져오기
	MemberDto getMember(String userId) throws Exception;
	// 회원정보 수정
	void updateMember(MemberDto memberDto) throws Exception;
	// 회원정보 삭제
	void deleteMember(String userid) throws Exception;
	// 미구현
	String encryptPassword(String pwd);
}
