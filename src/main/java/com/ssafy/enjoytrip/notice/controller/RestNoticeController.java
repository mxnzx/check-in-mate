package com.ssafy.enjoytrip.notice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.notice.model.NoticeDto;
import com.ssafy.enjoytrip.notice.model.service.NoticeService;

@RestController
@RequestMapping("/notice/api")
public class RestNoticeController {

	@Qualifier("NoticeServiceImpl")
	private NoticeService noticeService;

	@Autowired
	public RestNoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	// 공지사항 리스트 출력
	@GetMapping("/list")
	public List<NoticeDto> list(@RequestParam Map<String, String> map) throws Exception {
		return noticeService.listArticle(map);
	}
	
	// 공지사항 글쓰기
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ResponseEntity<NoticeDto> write(NoticeDto noticeDto) throws Exception{
		noticeService.writeArticle(noticeDto);
		return ResponseEntity.ok(noticeDto);
	}
	
	// 공지사항 글보기 ( articleNo로 비교 ) 
	// REST로 구현완료
	@RequestMapping(value = "/view/{articleNo}", method = RequestMethod.GET)
	public ResponseEntity<String> view(@PathVariable("articleNo") int articleNo, @RequestParam Map<String, String> map, Model model)
			throws Exception {
		noticeService.getArticle(articleNo);
		noticeService.updateHit(articleNo);
		return ResponseEntity.ok(articleNo + "번 글 보기 ");
	}	
	
	// 공지사항 글 수정하기
	// REST로 구현완료
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public ResponseEntity<String> modify(NoticeDto noticeDto, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		noticeService.modifyArticle(noticeDto);
		redirectAttributes.addAttribute("pgno", map.get("pgno"));
		redirectAttributes.addAttribute("key", map.get("key"));
		redirectAttributes.addAttribute("word", map.get("word"));
		return ResponseEntity.ok("수정완료");
	}
	// 공지사항 글 삭제하기
	// REST로 구현완료
	@RequestMapping(value = "/delete/{articleNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("articleNo") int articleNo, @RequestParam Map<String, String> map,
			RedirectAttributes redirectAttributes) throws Exception {
		noticeService.deleteArticle(articleNo);
		return ResponseEntity.ok("삭제완료");
	}

	
}
