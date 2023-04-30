package com.ssafy.enjoytrip.member.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import com.ssafy.enjoytrip.member.model.service.MemberServiceImpl;



@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
	
	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getParameter("action");
		String path = "";
		
		try {
			if("mvjoin".equals(action)) {
				path = "/user/join.jsp";
				redirect(request, response, path);
			} else if("idcheck".equals(action)) {
				int cnt = idcheck(request, response);	//0, 1
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				out.print(cnt);
			} else if("join".equals(action)) {
				path = join(request, response);
				redirect(request, response, path);
			} else if("mvlogin".equals(action)) {
				path = "/user/login.jsp";
				redirect(request, response, path);
			} else if("login".equals(action)) {
				path = login(request, response);
				forward(request, response, path);
			} else if("logout".equals(action)) {
				path = logout(request, response);
				redirect(request, response, path);
			} else if("userUpdate".equals(action)) {
				path = userUpdate(request, response);
				redirect(request, response, path);
			} else if ("delete".equals(action)) {
				//flag가 null이 아닌 경우는 boardController에서 다시 포워딩 한 것임.
				if (request.getAttribute("flag") == null) {
					request.setAttribute("flag", "true");
					forward(request, response, "/article?action=deleteArticleAll");
				}
				path = delete(request, response);
				forward(request, response, path);
			} else {
				redirect(request, response, path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			doGet(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
	
	private int idcheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (memberService.idCheck(request.getParameter("userid"))) {
			return 1;
		}
		
		return 0;
	}
	
	private String join(HttpServletRequest request, HttpServletResponse response) {
		
		
		MemberDto memberDto = new MemberDto();
		LocalDateTime now = LocalDateTime.now();
        String fnow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
		memberDto.setUserId(request.getParameter("user-join"));
		memberDto.setUserName(request.getParameter("username"));
		memberDto.setUserPwd(request.getParameter("userpwd-join"));
		memberDto.setEmailId(request.getParameter("emailid"));
		memberDto.setEmailDomain(request.getParameter("emaildomain"));
		memberDto.setJoinDate(fnow);
		
		System.out.println(memberDto);
		
		try {
			memberService.joinMember(memberDto);
			return "/user?action=mvlogin";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "/user/login.jsp";
		}
	}
	
	private String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("userId : " + request.getParameter("userid-login"));
		System.out.println("pw : " + request.getParameter("userpwd-login"));
		String userId = request.getParameter("userid-login");
		String userPwd = request.getParameter("userpwd-login");
		MemberDto memberDto = memberService.loginMember(userId, userPwd);
		if(memberDto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userinfo", memberDto);
			
			String idsave = request.getParameter("saveid");
			if("ok".equals(idsave)) { 
				Cookie cookie = new Cookie("ssafy_id", userId);
				cookie.setPath(request.getContextPath());
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40); 
				response.addCookie(cookie);
			} else { 
				Cookie cookies[] = request.getCookies();
				if(cookies != null) {
					for(Cookie cookie : cookies) {
						if("ssafy_id".equals(cookie.getName())) {
							cookie.setMaxAge(0);
							response.addCookie(cookie);
							break;
						}
					}
				}
			}
			request.setAttribute("success-login", "success");
			return "/index.jsp";
		} else {
			request.setAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다. 다시 입력해주세요. ");
			return "/user/login.jsp";
		}
	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
		session.invalidate();
		return "";
	}
	
	private String userUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDto member = new MemberDto();
		member.setUserId(request.getParameter("id"));
		member.setUserPwd(request.getParameter("pw"));
		
		memberService.updateMember(member);
		return "/index.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		memberService.deleteMember(request.getParameter("id"));
		return "/user?action=logout";
	}
}
