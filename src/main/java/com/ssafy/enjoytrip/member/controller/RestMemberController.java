package com.ssafy.enjoytrip.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import com.ssafy.enjoytrip.member.model.service.MemberServiceImpl;

@RestController
@RequestMapping("/user")
public class RestMemberController {

	private MemberService memberService;

	public RestMemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	// 회원가입 모달로 이동하기 
	@GetMapping("/join")
	public String join() {
		return "user/join-modal";
	}

	// 아이디 값 가져와서 기존 아이디와 비교해서 겹치는게 있는지 체크 
	@GetMapping("/{userid}")
	@ResponseBody
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		// logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		return cnt + "";
	}

	// 회원가입
	@PostMapping("/join")
	public String join(MemberDto memberDto, Model model) {
		// logger.debug("memberDto info : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			return "redirect:/user/login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return "error/error";
		}
	}

	// 로그인 창으로 이동 
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	// 로그인하기
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map,
			@RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session,
			HttpServletResponse response) {
		// logger.debug("login map : {}", map);
		try {
			MemberDto memberDto = memberService.loginMember(map);
			System.out.println(memberDto);
			if (memberDto != null) {
				session.setAttribute("userinfo", memberDto);

				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setPath("/board");
				cookie.setPath("/article");
				if ("ok".equals(saveid)) {
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}
	}

	// 로그아웃하기
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// 마이페이지로 이동
	@GetMapping("/mypage")
	public String mypage() {
		return "user/mypage";
	}
	
	// 회원탈퇴
	@PostMapping("/deleteMember")
	public String deleteMember(MemberDto memberDto) {
		String userId = memberDto.getUserId();
		System.out.println(userId);
		// logger.debug("memberDto info : {}", memberDto);
		try {
			memberService.deleteMember(userId);
			return "redirect:/user/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	// 회원정보 수정 ( 비밀번호 변경 ) 
	@PostMapping("/updateMember")
	public String updateMember(MemberDto memberDto) {
		String userId = memberDto.getUserId();
		String userPwd = memberDto.getUserPwd();
		System.out.println(memberDto + " 업데이트"  );
		try {
			memberService.updateMember(memberDto);
			return "redirect:/user/login";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
	
}
