package com.ssafy.enjoytrip.attr.model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;
import com.ssafy.enjoytrip.util.DBUtil;



public class AttrInfoDaoImpl {
	 private DBUtil dbUtil;
	    private static AttrInfoDaoImpl tripController = new AttrInfoDaoImpl();
	    
	    // 생성자 호출 시 DB 연결 객체를 받아온다.
	    private AttrInfoDaoImpl() {
	        dbUtil = DBUtil.getInstance();
	    }
	    
	    // Controller를 받은 곳에서 명령을 전달한다 --> 받은 명령을 Controller를 통해 처리한다.
	    public static AttrInfoDaoImpl getTripService() {
	        return tripController;
	    }
	    
	    
	    /*
	     *   도시 정보 가져오기 (화면의 도시정보 Select Box에 담겨진다).
	     *   Connection : DB 객체에 연결해 SQL문을 적용시키는 객체
	     *   PreparedStatement : 미리 정의된 SQL문 (문자열로 작성된 SQL)에 매개변수를 삽입(? 위치)해 명령 수행
	     *   ResultSet : 행(row) 단위로 select한 결과를 가져와서 활용하는 객체
	     * 
	     */ 
	    public List<SidoDto> getCities() throws SQLException {
	        List < SidoDto > list = new ArrayList<>();   
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try 
	        {
	        	conn = dbUtil.getConnection();
	            StringBuilder sql = new StringBuilder();
	            sql.append("select * from sido");
	            pstmt = conn.prepareStatement(sql.toString());
	            rs = pstmt.executeQuery();
	            
	            while (rs.next()) //ResultSet로 가져온 데이터를 하나씩 추출 후 활용
	            {
	            	SidoDto city = new SidoDto();
	                city.setSidoCode(rs.getInt("sido_code"));
	                city.setSidoName(rs.getString("sido_name"));
	                list.add(city);
	            }
	        } 
	        finally
	        {
	            dbUtil.close(rs, pstmt, conn);  // 사용 완료 후 자원 반환
	        }
	        return list;
	    }
	    
	    
	    // 구군 정보 가져오기
	    public List<AttrInfoDto> getAttrInfo(int sido, int gugun, int type)throws SQLException {
	        List < AttrInfoDto > list = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            conn = dbUtil.getConnection();
	            StringBuilder sql = new StringBuilder();
	            sql.append("select sido_code, gugun_code, content_type_id, \n");
	            sql.append("title, addr1, first_image, latitude, longitude \n");
	            sql.append("from attraction_info \n");
	            sql.append("where sido_code=" + sido + " and gugun_code=" + gugun + " and content_type_id=" + type);
	            pstmt = conn.prepareStatement(sql.toString());
	            rs = pstmt.executeQuery();
	            
	            //ResultSet로 가져온 데이터를 하나씩 추출 후 활용
	            while (rs.next()) {
	            	AttrInfoDto Info = new AttrInfoDto();
	                Info.setSidoCode(rs.getInt("sido_code"));
	                Info.setGugunCode(rs.getInt("gugun_code"));
	                Info.setContentId(rs.getInt("content_type_id"));
	                Info.setTitle(rs.getString("title"));
	                Info.setAddress(rs.getString("addr1"));
	                Info.setImgUrl(rs.getString("first_image"));
	                Info.setLatitude(rs.getDouble("latitude"));
	                Info.setLongitude(rs.getDouble("longitude"));
	                System.out.println(Info);
	                list.add(Info);
	            }
	        } finally {
	            dbUtil.close(rs, pstmt, conn);
	        }
	        return list;
	    }
}
