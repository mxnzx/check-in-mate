package com.ssafy.enjoytrip.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.board.model.CommentDto;
import com.ssafy.enjoytrip.board.model.service.BoardService;

@RestController
@RequestMapping("/comment")
public class BoardCommentController {

	private BoardService boardService;

	public BoardCommentController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}
	// 댓글 목록 출력 ( articleNo의 댓글을 출력 )
	@GetMapping("{articleNo}")
	public List<CommentDto> listComment(@PathVariable int articleNo) {
		return boardService.listComment(articleNo);
	}
	
	// 댓글 삭제하기 ( commentNo 비교 )
	@DeleteMapping("{articleNo}/{commentNo}")
	public List<CommentDto> deleteComment(@PathVariable int articleNo, @PathVariable int commentNo) throws Exception {
		boardService.deleteComment(commentNo);
		boardService.downCommentCnt(articleNo);
		return boardService.listComment(articleNo);
	}
	
	// 댓글쓰기
	@PostMapping
	public List<CommentDto> writeComment(@RequestBody CommentDto commentDto) throws Exception {
		boardService.writeComment(commentDto);
		boardService.upCommentCnt(commentDto.getArticleNo());
		return boardService.listComment(commentDto.getArticleNo());
	}
	
//	// 댓글쓰기
//	@PostMapping
//	public List<CommentDto> writeComment(@RequestParam("commentNo") int commentNo , @RequestParam("articleNo") int articleNo, @RequestParam("userId") String userId, @RequestParam("parentId") String parentId, @RequestParam("content") String content, @RequestParam("registerTime") String registerTime) {
//		boardService.writeComment(commentDto);
//		boardService.upCommentCnt(articleNo);
//		return boardService.listComment(commentDto.getArticleNo());
//	}
}
