package com.ssafy.enjoytrip.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.ssafy.enjoytrip.member.model.MemberDto;

@Mapper
public interface MemberMapper {

//	// 아이디 중복검사
//	int idCheck(String userId) throws SQLException;
	// 회원가입
	void joinMember(MemberDto memberDto) throws SQLException;
//	// 로그인
//	MemberDto loginMember(Map<String, String> map) throws SQLException;
//	
//	/* Admin */
//	List<MemberDto> listMember(Map<String, Object> map) throws SQLException;
//	MemberDto getMember(String userId) throws SQLException;
	// 회원정보 수정
	void updateMember(MemberDto memberDto) throws SQLException;
	// 회원탈퇴
	void deleteMember(String userid) throws SQLException;
	
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
