package com.ssafy.enjoytrip.member.model.dao;


import java.sql.SQLException;
import java.util.List;

import com.ssafy.enjoytrip.member.model.MemberDto;


//회원 정보 DAO
public interface MemberDao {

	// 회원가입
	boolean joinMember(MemberDto memberDto) throws SQLException;

	// 로그인
	MemberDto loginMember(String userId, String userPwd) throws SQLException;

	// 회원아이디 리스트 불러오기
	List<String> listMember() throws SQLException;

	// 회원 정보 수정
	boolean updateMember(MemberDto memberDto) throws SQLException;

	// 회원 정보 삭제(회원탈퇴)
	boolean deleteMember(String id) throws SQLException;

}
