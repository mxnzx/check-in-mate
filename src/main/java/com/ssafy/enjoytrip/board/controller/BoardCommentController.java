package com.ssafy.enjoytrip.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("{articleNo}")
	public List<CommentDto> listComment(@PathVariable int articleNo) {
		return boardService.listComment(articleNo);
	}
	
	@DeleteMapping("{articleNo}/{commentNo}")
	public List<CommentDto> deleteComment(@PathVariable int articleNo, @PathVariable int commentNo) {
		boardService.deleteComment(commentNo);
		return boardService.listComment(articleNo);
	}
	@PostMapping
	public List<CommentDto> writeComment(@RequestBody CommentDto commentDto) {
		boardService.writeComment(commentDto);
		return boardService.listComment(commentDto.getArticleNo());
	}
}
