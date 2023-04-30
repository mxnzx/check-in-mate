package com.ssafy.enjoytrip.member.model.service;


import java.util.List;

import com.ssafy.enjoytrip.member.model.MemberDto;


public interface MemberService {

	boolean joinMember(MemberDto memberDto) throws Exception;
	MemberDto loginMember(String userId, String userPwd) throws Exception;
	List<String> listMember() throws Exception;
	boolean idCheck(String userid) throws Exception;
	boolean updateMember(MemberDto memberDto) throws Exception;
	boolean deleteMember(String id) throws Exception;
	String encryptPassword(String pwd);
}
