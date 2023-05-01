package com.ssafy.enjoytrip.notice.controller;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.notice.model.NoticeDto;
import com.ssafy.enjoytrip.notice.model.service.NoticeService;
import com.ssafy.enjoytrip.notice.model.service.NoticeServiceImpl;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.ParameterCheck;


@Controller
@RequestMapping("/notice")
public class NoticeController extends HttpServlet {

	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	// 공지사항 리스트 출력 
	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		//logger.debug("list parameter pgno : {}", map.get("pgno"));
		ModelAndView mav = new ModelAndView();
		List<NoticeDto> list = noticeService.listArticle(map);
		System.out.println(list);
		MemberDto memberDto = new MemberDto();
		mav.addObject(memberDto);
		PageNavigation pageNavigation = noticeService.makePageNavigation(map);
		mav.addObject("notices", list);
		mav.addObject("navigation", pageNavigation);
		mav.addObject("pgno", map.get("pgno"));
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("notice/notice");
		return mav;
	}
	
	// 공지 글쓰기 페이지로 이동  , 임시로 로그인 했을때만 출력하게 해놓음 (미완성)
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> map, Model model ,HttpServletRequest request, HttpServletResponse response) {
		//logger.debug("write call parameter {}", map);
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {			
			model.addAttribute("pgno", map.get("pgno"));
			model.addAttribute("key", map.get("key"));
			model.addAttribute("word", map.get("word"));
			return "notice/write";
		}
		else {
			request.setAttribute("msg", "로그인 후 이용이 가능합니다. ");
			return "user/login";
		}
	}
}
