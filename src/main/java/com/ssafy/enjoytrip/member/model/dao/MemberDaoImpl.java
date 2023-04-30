package com.ssafy.enjoytrip.member.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.util.DBUtil;



public class MemberDaoImpl implements MemberDao {
	
	private static MemberDao memberDao = new MemberDaoImpl();
	private DBUtil dbUtil;
	
	private MemberDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}
	
	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public boolean joinMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
        PreparedStatement pstmt = null;
        int rs = 0;
        System.out.println("MemberDaoImpl joinMember 실행");
        try {
        	System.out.println("MemberDaoImpl joinMember 정상실행");
            conn = dbUtil.getConnection();
            StringBuilder joinMember = new StringBuilder();
            joinMember.append("insert into member \n");
            joinMember.append("(user_id, user_name, user_password, email_id, email_domain, join_date) \n");
            joinMember.append("values(?, ?, ?, ?, ?, ?) \n");
            pstmt = conn.prepareStatement(joinMember.toString());
            pstmt.setString(1, memberDto.getUserId());
            pstmt.setString(2, memberDto.getUserName());
            pstmt.setString(3, memberDto.getUserPwd());
            pstmt.setString(4, memberDto.getEmailId());
            pstmt.setString(5, memberDto.getEmailDomain());
            pstmt.setString(6, memberDto.getJoinDate());
            System.out.println("DaoImpl" + "  " + memberDto.toString());
            rs = pstmt.executeUpdate();
            System.out.println("db에 넣었음");
        } finally {
            dbUtil.close(pstmt, conn);
        }
        return rs == 0; // 변경된 row 수 반영 0이면 없는 것
    
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		MemberDto memberDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select user_id, user_name, user_password \n");
			loginMember.append("from member \n");
			loginMember.append("where user_id = ? \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(userPwd, rs.getNString("user_password"))) {
					memberDto = new MemberDto();
					memberDto.setUserId(rs.getString("user_id"));
					memberDto.setUserName(rs.getString("user_name"));
					memberDto.setUserPwd(rs.getString("user_password"));
				}
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return memberDto;
	}

	@Override
	public List<String> listMember() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> selectedUserId = new ArrayList<>();
		try {
			conn = dbUtil.getConnection();
			StringBuilder loginMember = new StringBuilder();
			loginMember.append("select user_id \n");
			loginMember.append("from member \n");
			pstmt = conn.prepareStatement(loginMember.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				selectedUserId.add(rs.getString(1));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return selectedUserId;
	}

	@Override
	public boolean updateMember(MemberDto memberDto) throws SQLException {
		Connection conn = null;
        PreparedStatement pstmt = null;
        int rs = 0;

        try {
            conn = dbUtil.getConnection();
            StringBuilder joinMember = new StringBuilder();
            joinMember.append("update member set user_password = ? where user_id = ? \n");
            pstmt = conn.prepareStatement(joinMember.toString());
            pstmt.setString(1, memberDto.getUserPwd());
            pstmt.setString(2, memberDto.getUserId());

            rs = pstmt.executeUpdate();

        } finally {
            dbUtil.close(pstmt, conn);
        }
        return rs == 0; // 변경된 row 수 반영 0이면 없는 것
	}

	@Override
	public boolean deleteMember(String id) throws SQLException {
		Connection conn = null;
        PreparedStatement pstmt = null;
        int rs = 0;
        
        try {
        	conn = dbUtil.getConnection();
        	String sql = "delete from member where user_id = ? \n";
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1, id);
        	rs = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs == 0;
	}

}
