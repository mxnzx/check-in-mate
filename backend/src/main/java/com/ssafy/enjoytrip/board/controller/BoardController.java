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

	private String uploadPath;
	private BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	// 여행정보 공유 리스트 출력 
	@GetMapping("/list")
	public ModelAndView list(@RequestParam Map<String, String> map) throws Exception {
		//logger.debug("list parameter pgno : {}", map.get("pgno"));
		ModelAndView mav = new ModelAndView();
		List<BoardDto> list = boardService.listArticle(map);
		System.out.println(list);
		MemberDto memberDto = new MemberDto();
		mav.addObject(memberDto);
		PageNavigation pageNavigation = boardService.makePageNavigation(map);
		mav.addObject("boards", list);
		mav.addObject("navigation", pageNavigation);
		mav.addObject("pgno", map.get("pgno"));
		mav.addObject("key", map.get("key"));
		mav.addObject("word", map.get("word"));
		mav.setViewName("board/board");
		return mav;
	}
	// 글쓰기 페이지로 이동 
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> map, Model model ,HttpServletRequest request, HttpServletResponse response) {
		//logger.debug("write call parameter {}", map);
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if(memberDto != null) {			
			model.addAttribute("pgno", map.get("pgno"));
			model.addAttribute("key", map.get("key"));
			model.addAttribute("word", map.get("word"));
			return "board/write";
		}
		else {
			request.setAttribute("msg", "로그인 후 이용이 가능합니다. ");
			return "user/login";
		}
	}
	
	// 여행정보 공유 글쓰기 
	@PostMapping("/write")
	public String write(BoardDto boardDto, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		//logger.debug("write boardDto : {}", boardDto);
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		System.out.println(">>>>>>>" + memberDto);
		boardDto.setUserId(memberDto.getUserId());
		System.out.println("boardDto >>>  "+boardDto);
		System.out.println("MemberDto >>>  "+memberDto);

// 파일 업로드 다운로드 부분 주석처리 (미구현)
//		FileUpload 관련 설정.
		//logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
		
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
	// 여행정보 공유 글보기 ( articleNo로 비교 ) 
	@GetMapping("/view")
	public String view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		//logger.debug("view articleNo : {}", articleNo);
		BoardDto boardDto = boardService.getArticle(articleNo);
		boardService.updateHit(articleNo);
		model.addAttribute("board", boardDto);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "board/view";
	}
	
	// 여행정보 공유 글 수정하기로 이동
	@GetMapping("/modify")
	public String modify(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		//logger.debug("modify articleNo : {}", articleNo);
		BoardDto boardDto = boardService.getArticle(articleNo);
		model.addAttribute("board", boardDto);
		model.addAttribute("pgno", map.get("pgno"));
		model.addAttribute("key", map.get("key"));
		model.addAttribute("word", map.get("word"));
		return "board/modify";
	}
	
	// 여행정보 공유 글 수정하기
	@PostMapping("/modify")
	public String modify(BoardDto boardDto, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		//logger.debug("modify boardDto : {}", boardDto);
		boardService.modifyArticle(boardDto);
		System.out.println(boardDto);
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
		return "redirect:/board/list";
	}
	
	// 여행정보 공유 글 삭제하기
	@GetMapping("/delete")
	public String delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		//logger.debug("delete articleNo : {}", articleNo);
		boardService.deleteArticle(articleNo);
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
		return "redirect:/board/list";
	}

}
