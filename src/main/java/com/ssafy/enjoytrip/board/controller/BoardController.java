package com.ssafy.enjoytrip.board.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.service.BoardService;
import com.ssafy.enjoytrip.board.model.service.BoardServiceImpl;
import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.ParameterCheck;
import com.ssafy.enjoytrip.util.SizeConstant;


@Controller
@RequestMapping("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int pgno;
	private String key;
	private String word;
	private String queryStrig;

	private BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	
	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		//logger.debug("list parameter pgno : {}", map.get("pgno"));
		ModelAndView mav = new ModelAndView();
		List<BoardDto> list = boardService.listArticle(map);
		PageNavigation pageNavigation = boardService.makePageNavigation(map);
		mav.addObject("boards", list);
		mav.addObject("navigation", pageNavigation);
		mav.addObject("pgno", map.get("pgno"));
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("board/board");
		return mav;
	}
	
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> map, Model model) {
		//logger.debug("write call parameter {}", map);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "board/write";
	}
	
	// 아이디 관련 아직 아이디를 못가져와서 임시 주석처리 
	@PostMapping("/write")
	public String write(BoardDto boardDto, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		//logger.debug("write boardDto : {}", boardDto);
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		System.out.println(memberDto.getUserId());
		boardDto.setUserId(memberDto.getUserId());

//		FileUpload 관련 설정.
		//logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
		
// 파일 업로드 다운로드 부분 주석처리
//		if (!files[0].isEmpty()) {
////			String realPath = servletContext.getRealPath(UPLOAD_PATH);
////			String realPath = servletContext.getRealPath("/resources/img");
//			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			String saveFolder = uploadPath + File.separator + today;
//			logger.debug("저장 폴더 : {}", saveFolder);
//			File folder = new File(saveFolder);
//			if (!folder.exists())
//				folder.mkdirs();
//			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
//			for (MultipartFile mfile : files) {
//				FileInfoDto fileInfoDto = new FileInfoDto();
//				String originalFileName = mfile.getOriginalFilename();
//				if (!originalFileName.isEmpty()) {
//					String saveFileName = UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					fileInfoDto.setSaveFolder(today);
//					fileInfoDto.setOriginalFile(originalFileName);
//					fileInfoDto.setSaveFile(saveFileName);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
//					mfile.transferTo(new File(folder, saveFileName));
//				}
//				fileInfos.add(fileInfoDto);
//			}
//			boardDto.setFileInfos(fileInfos);
//		}

		boardService.writeArticle(boardDto);
		redirectAttributes.addAttribute("pgno", "1");
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		return "redirect:/board/list";
	}	
	
	@GetMapping("/view")
	public String view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		//logger.debug("view articleNo : {}", articleNo);
		BoardDto boardDto = boardService.getArticle(articleNo);
		boardService.updateHit(articleNo);
		model.addAttribute("boards", boardDto);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "board/view";
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getParameter("action");
//
//		pgno = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
//		key = ParameterCheck.nullToBlank(request.getParameter("key"));
//		word = ParameterCheck.nullToBlank(request.getParameter("word"));
//		queryStrig = "?pgno=" + pgno + "&key=" + key + "&word=" + URLEncoder.encode(word, "utf-8");
//
//		String path = "";
//		if ("board".equals(action)) {
//			path = list(request, response);
//			forward(request, response, path);
//		} else if ("sort_hit".equals(action)) {
//			path = sortHit(request, response);
//			forward(request, response, path);
//		} else if ("view".equals(action)) {
//			path = view(request, response);
//			forward(request, response, path);
//		} else if ("mvwrite".equals(action)) {
//			path = mvwrite(request, response);
//			forward(request, response, path);
//		} else if ("write".equals(action)) {
//			path = write(request, response);
//			redirect(request, response, path);
//		} else if ("mvmodify".equals(action)) {
//			path = mvModify(request, response);
//			forward(request, response, path);
//		} else if ("modify".equals(action)) {
//			path = modify(request, response);
//			forward(request, response, path);
//		} else if ("delete".equals(action)) {
//			path = delete(request, response);
//			redirect(request, response, path);
//		} else if ("deleteArticleAll".equals(action)) {
//			path = deleteArticleAll(request, response);
//			forward(request, response, path);
//		} else {
//			redirect(request, response, path);
//		}
//	}
//
//
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		doGet(request, response);
//	}
//
//	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
//		dispatcher.forward(request, response);
//	}
//
//	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
//		response.sendRedirect(request.getContextPath() + path);
//	}
//
//	private String sortHit(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		try {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("pgno", pgno + "");
//			map.put("key", key);
//			map.put("word", word);
//
//			//
//			List<BoardDto> list = boardService.sortListArticle(map);
//			request.setAttribute("boards", list);
//			
//
//			PageNavigation pageNavigation = boardService.makePageNavigation(map);
//			request.setAttribute("navigation", pageNavigation);
//
//			return "/board/board.jsp" + queryStrig;
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글목록 출력 중 문제 발생!!!");
//			return "/error/error.jsp";
//		}
//	}
//	
//	private String list(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		try {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("pgno", pgno + "");
//			map.put("key", key);
//			map.put("word", word);
//
//			//
//			List<BoardDto> list = boardService.listArticle(map);
//			request.setAttribute("boards", list);
//			
//
//			PageNavigation pageNavigation = boardService.makePageNavigation(map);
//			request.setAttribute("navigation", pageNavigation);
//
//			return "/board/board.jsp" + queryStrig;
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글목록 출력 중 문제 발생!!!");
//			return "/error/error.jsp";
//		}
//	}
//
//	private String view(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			int articleNo = Integer.parseInt(request.getParameter("articleno"));
//			try {
//				BoardDto boardDto = boardService.getArticle(articleNo);
//				boardService.updateHit(articleNo);
//				request.setAttribute("board", boardDto);
//
//				return "/board/view.jsp";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글내용 출력 중 문제 발생!!!");
//				return "/error/error.jsp";
//			}
//		}  else {
//			request.setAttribute("msg", "로그인 후 이용이 가능합니다. ");
//			return "/board?action=board";
//		}
//	}
//
//	private String mvwrite(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			return "/board/write.jsp";
//		} else {
//			request.setAttribute("msg", "로그인 후 이용이 가능합니다. ");
//			return "/board?action=board";
//		}
//	}
//	
//	private String write(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			BoardDto boardDto = new BoardDto();
//			boardDto.setUserId(memberDto.getUserId());
//			boardDto.setSubject(request.getParameter("subject"));
//			boardDto.setContent(request.getParameter("content"));
//			try {
//				boardService.writeArticle(boardDto);
//				return "/board?action=board";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글작성 중 문제 발생!!!");
//				return "/error/error.jsp";
//			}
//		} else
//			return "/user/login.jsp";
//	}
//
//	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
//		// TODO : 수정하고자하는 글의 글번호를 얻는다.
//		// TODO : 글번호에 해당하는 글정보를 얻고 글정보를 가지고 modify.jsp로 이동.
//		try {
//			HttpSession session = request.getSession();
//			MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//			if (memberDto != null) {
//				int articleNo = Integer.parseInt(request.getParameter("articleno"));
//				BoardDto boardDto = boardService.getArticle(articleNo);
//				request.setAttribute("board", boardDto);
//
//				return "/board/modify.jsp";
//			} else
//				return "/user/login.jsp";
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.setAttribute("msg", "글내용 얻는 중 문제 발생!!!");
//			return "/error/error.jsp";
//		}
//	}
//
//	private String modify(HttpServletRequest request, HttpServletResponse response) {
//		// TODO : 수정 할 글정보를 얻고 BoardDto에 set.
//		// TODO : boardDto를 파라미터로 service의 modifyArticle() 호출.
//		// TODO : 글수정 완료 후 view.jsp로 이동.(이후의 프로세스를 생각해 보세요.)
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			BoardDto boardDto = new BoardDto();
//			boardDto.setArticleNo(Integer.parseInt(request.getParameter("articleno")));
//			boardDto.setSubject(request.getParameter("subject"));
//			boardDto.setContent(request.getParameter("content"));
//
//			try {
//				boardService.modifyArticle(boardDto);
//				return "/board?action=board&pgno=1&key=&word=";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글수정 중 문제 발생!!!");
//				return "/error/error.jsp";
//			}
//
//		} else
//			return "/user/login.jsp";
//	}
//
//	private String delete(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			int articleNo = Integer.parseInt(request.getParameter("articleno"));
//
//			try {
//				boardService.deleteArticle(articleNo);
//				return "/board?action=board&pgno=1&key=&word=";
//			} catch (Exception e) {
//				e.printStackTrace();
//				request.setAttribute("msg", "글삭제 중 문제 발생!!!");
//				return "/error/error.jsp";
//			}
//
//		} else
//			return "/user/login.jsp";
//	}
//
//	private String deleteArticleAll(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			boardService.deleteArticleAll(request.getParameter("id"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "/user?action=delete";
//	}

}
