package com.ssafy.enjoytrip.attr.controller;


/*
 *   네비게이션 항목 중 "지역별여행지" 페이지에서 이벤트 처리를 위한 Controller
 *   tripDataController는 들어온 요청에 대한 Query 처리를 수행하며 그 결과를 반환합니다.
 *   
 *   class:
 *   	1 	  city:  도시 정보(서울, 대전, 대구... 등)를 담는다.
 *      2 attrInfo:  SelectBox에서 선택된 값에 대한 Query 결과 (선택된 지역의 관광지 정보)를 담는다.
 * 
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.enjoytrip.attr.model.AttrInfoDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;
import com.ssafy.enjoytrip.attr.model.dao.AttrInfoDaoImpl;


@Controller
@RequestMapping("/navigator")
public class AttrInfoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AttrInfoDaoImpl attrInfoDaoImpl;
    
//    
//    public void init() throws ServletException 
//    {
//    	attrInfoDaoImpl = attrInfoDaoImpl.getTripService();
//    }

    @GetMapping("searchTrip")
    public String searchTrip() {
    	return "redirect:/attr/attr";
    }
    
//    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//    {
//        String action = request.getParameter("action");
//        String path = "";
//        
//        // // "지역별여행지" 페이지 호출 시
//        if ("serchTrip".equals(action)) 
//        { 	
//            System.out.println("도시 정보 가져오기 이벤트 발생.");
//            path = getInfo(request, response);
//            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//            dispatcher.forward(request, response);
//        } 
//        
//        // 검색 항목 지정 후 "검색" 버튼 클릭 이벤트 발생 시
//        else if ("showmap".equals(action)) 
//        {
//            path = showMap(request, response);
//            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//            dispatcher.forward(request, response);
//        }
//    }
//
//
//    // 선택된 정보를 바탕으로 관광지 정보를 DB에서 수집한다.
//    private String showMap(HttpServletRequest request, HttpServletResponse response) 
//    {
//        int sido = Integer.parseInt(request.getParameter("sido"));
//        int gugun = Integer.parseInt(request.getParameter("gugun"));
//        int type = Integer.parseInt(request.getParameter("type"));
//        try {
//            List < AttrInfoDto > tripInfo = attrInfoDaoImpl.getAttrInfo(sido, gugun, type);
//            request.setAttribute("attrinfo", tripInfo);
//            return "/attr/attr.jsp";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "/attr/attr.jsp";
//        }
//    }
//    
//    
//    // "지역별 여행지" 네비게이션 이벤트 발생 시 SelectBox 내의 도시 정보를 가져오기 위한 기초 자료를 DB에서 가져온다.
//    private String getInfo(HttpServletRequest request, HttpServletResponse response) 
//    {
//        HttpSession session = request.getSession();
//        try {
//            List < SidoDto > citylist = attrInfoDaoImpl.getCities();
//            session.setAttribute("cities", citylist);  //도시 정보를 세션에 저장한다.
//            return "/map/map.jsp";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "/map/map.jsp";
//        }
//    }
}