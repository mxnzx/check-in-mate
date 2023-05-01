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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.board.model.BoardDto;
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
	
	//  공지 글쓰기 
	@PostMapping("/write")
	public String write(NoticeDto noticeDto, HttpSession session,
			RedirectAttributes redirectAttributes) throws Exception {
		//logger.debug("write boardDto : {}", boardDto);
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		System.out.println(">>>>>>>" + memberDto);
		noticeDto.setUserId(memberDto.getUserId());
		//System.out.println("boardDto >>>  "+boardDto);
		//System.out.println("MemberDto >>>  "+memberDto);

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

		noticeService.writeArticle(noticeDto);
		redirectAttributes.addAttribute("pgno", "1");
		redirectAttributes.addAttribute("key", "");
		redirectAttributes.addAttribute("word", "");
		return "redirect:/notice/list";
	}	
}
