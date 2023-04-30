package com.ssafy.enjoytrip.member.model.service;


import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.dao.MemberDao;
import com.ssafy.enjoytrip.member.model.dao.MemberDaoImpl;



public class MemberServiceImpl implements MemberService {
	
	private static MemberService memberService = new MemberServiceImpl();
	private MemberDao memberDao;
	
	private MemberServiceImpl() {
		memberDao = MemberDaoImpl.getMemberDao();
	}
	
	public static MemberService getMemberService() {
		return memberService;
	}

	@Override
	public boolean joinMember(MemberDto memberDto) throws Exception {
		memberDto.setUserPwd(encryptPassword(memberDto.getUserPwd()));
		System.out.println(memberDto.getUserPwd());
		System.out.println("MemberServiceImpl joinmember 실행");
		return memberDao.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws Exception {
		System.out.println(userPwd);
		MemberDto user = memberDao.loginMember(userId, userPwd);
		return user;
	}

	@Override
	public List<String> listMember() throws Exception {
		return memberDao.listMember();
	}

	@Override
	public boolean idCheck(String userid) throws Exception {
		List<String> selectedUserId = listMember();
		for (String s : selectedUserId) {
			if (userid.equals(s)) return false;
		}
		return true;
	}

	@Override
	public boolean updateMember(MemberDto memberDto) throws Exception {
		memberDto.setUserPwd(encryptPassword(memberDto.getUserPwd()));
		return memberDao.updateMember(memberDto);
	}

	@Override
	public boolean deleteMember(String id) throws Exception {
		return memberDao.deleteMember(id);
	}

	@Override
	public String encryptPassword(String pwd) {
		String salt = BCrypt.gensalt();
		String hashedPwd = BCrypt.hashpw(pwd, salt);
		return hashedPwd;
	}

}
