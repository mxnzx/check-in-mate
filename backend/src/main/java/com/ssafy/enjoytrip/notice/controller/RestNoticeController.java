package com.ssafy.enjoytrip.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.notice.model.NoticeDto;
import com.ssafy.enjoytrip.notice.model.service.NoticeService;

@RestController
@RequestMapping("/notice/api")
@CrossOrigin(origins = { "*" })
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
	public ResponseEntity<Map<String, Object>> write(@RequestBody NoticeDto noticeDto) throws Exception{
//		noticeService.writeArticle(noticeDto);
//		return ResponseEntity.ok(noticeDto);
		
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			noticeService.writeArticle(noticeDto);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "입력 성공");
			map.put("resValue", null);
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch(Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "입력 실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	// 공지사항 글보기 ( articleNo로 비교 ) 
	// REST로 구현완료
	@RequestMapping(value = "/view/{articleNo}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> view(@PathVariable("articleNo") int articleNo)
			throws Exception {
//		noticeService.getArticle(articleNo);
//		noticeService.updateHit(articleNo);
//		return ResponseEntity.ok(articleNo + "번 글 보기 ");
		ResponseEntity<Map<String, Object>> resEntity = null;
		NoticeDto noticeDto = null;
		System.out.println("공지사항 글보기 controller");
		try {
			noticeDto = noticeService.getArticle(articleNo);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "공지사항 조회 성공");
			map.put("noticeArticle", noticeDto);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "공지사항 조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}	
	
	// 공지사항 글 수정하기
	// REST로 구현완료
	@RequestMapping(value = "/modify", method = RequestMethod.PUT)
	public ResponseEntity<String> modify(@RequestBody NoticeDto noticeDto, @RequestParam Map<String, String> map,
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
