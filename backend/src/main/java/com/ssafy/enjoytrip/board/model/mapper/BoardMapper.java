package com.ssafy.enjoytrip.board.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.CommentDto;

@Mapper
public interface BoardMapper {

	// 글작성
	void writeArticle(BoardDto boardDto) throws SQLException;

	// 미구현
	void registerFile(BoardDto boardDto) throws Exception;

	// 글목록보기
	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;

	// 총 글 개수 가져오기
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	// 글내용 가져오기
	BoardDto getArticle(int articleNo) throws SQLException;

	// 조회수 
	void updateHit(int articleNo) throws SQLException;
	
	// 댓글 개수 더하기
	void upCommentCnt(int articleNo) throws SQLException;

	// 댓글 개수 빼기
	void downCommentCnt(int articleNo) throws SQLException;

	// 글 수정
	void modifyArticle(BoardDto boardDto) throws SQLException;

	// 미구현
	void deleteFile(int articleNo) throws Exception;

	// 글 삭제
	void deleteArticle(int articleNo) throws SQLException;

	//List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
	
	// 댓글작성
	void insertComment(CommentDto commentdto);
	
	// 댓글 목록 출력
	List<CommentDto> listComment(int article_no);
	
	// 댓글 삭제
	void deleteComment(int commentNo);

}