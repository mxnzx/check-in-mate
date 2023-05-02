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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.enjoytrip.attr.model.GugunDto;
import com.ssafy.enjoytrip.attr.model.SidoDto;
import com.ssafy.enjoytrip.attr.model.service.AttrInfoService;


@RestController
@RequestMapping("/navigator")
public class AttrInfoController {

	@Autowired
    private AttrInfoService attrInfoService;
    
    //DI를 위함
    public AttrInfoController(AttrInfoService attrInfoService) {
		super();
		this.attrInfoService = attrInfoService;
		
	}

    //1. 페이지로 들어오면 시도 코드를 가지고 온다
    
    //3. 시도&구군&컨텐츠 값이 모두 들어와있고, 검색 버튼을 누르면 해당 3개의 값이 맞는 attraction_info를 가지고 리스트로 출력한다
    @GetMapping("searchSido")
    public List<SidoDto> searchSido() {
    		List<SidoDto> sidos;
			try {
				sidos = attrInfoService.sidoList();
				return sidos;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return null;
    }
    
  //2. 시도 목록을 선택하면 sido_code가 일치하는 구군 목록을 가지고 온다
    @GetMapping("searchGugun")
    public List<GugunDto> searchGugun(@RequestParam("sidoCode") String sidoCode) {
    	System.out.println(">>>>>>"+sidoCode);
    		List<GugunDto> guguns;
			try {
				
				guguns = attrInfoService.gugunList(sidoCode);
				System.out.println(guguns);
				return guguns;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return null;
    }
    
    
    
    
    
    

}