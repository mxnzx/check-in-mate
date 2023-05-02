package com.ssafy.enjoytrip.board.model.service;


import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.BoardDto;
import com.ssafy.enjoytrip.board.model.CommentDto;
import com.ssafy.enjoytrip.board.model.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.dao.BoardDaoImpl;
import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;


@Service("BoardServiceImpl")
public class BoardServiceImpl implements BoardService {

	
	private BoardMapper boardMapper;

	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}
	
	@Override
	public void writeArticle(BoardDto boardDto) throws Exception {
		System.out.println("글입력 전 dto : " + boardDto);
		boardMapper.writeArticle(boardDto);
		System.out.println("글입력 후 dto : " + boardDto);
		// 파일부분 주석
//		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
//		if (fileInfos != null && !fileInfos.isEmpty()) {
//			boardMapper.registerFile(boardDto);
//		}		
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		System.out.println(map);
		if("userid".equals(key))
			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return boardMapper.listArticle(param);
	}

	@Override
	public List<BoardDto> sortListArticle(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = boardMapper.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardMapper.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		boardMapper.updateHit(articleNo);
		
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		boardMapper.modifyArticle(boardDto);
		
	}

	@Override
	public void deleteArticle(int articleNo, String path) throws Exception {
		//List<FileInfoDto> fileList = boardMapper.fileInfoList(articleNo);
		//boardMapper.deleteFile(articleNo);
		boardMapper.deleteArticle(articleNo);
//		for(FileInfoDto fileInfoDto : fileList) {
//			File file = new File(path + File.separator + fileInfoDto.getSaveFolder() + File.separator + fileInfoDto.getSaveFile());
//			file.delete();
//		}
		
	}

	@Override
	public void deleteArticleAll(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeComment(CommentDto commentDto) {
		boardMapper.insertComment(commentDto);
	}

	@Override
	public List<CommentDto> listComment(int article_no) {
		return boardMapper.listComment(article_no);
	}

	@Override
	public void deleteComment(int commentNo) {
		boardMapper.deleteComment(commentNo);
	}
	
//	private BoardDao boardDao;
//	
//	@Autowired
//	public BoardServiceImpl(BoardDao boardDao) {
//		super();
//		this.boardDao = boardDao;
//	}
//	
//	@Override
//	public void writeArticle(BoardDto boardDto) throws Exception {
//		boardDao.writeArticle(boardDto);
//	}
//
//
//	public List<BoardDto> sortListArticle(Map<String, String> map) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		String sort = "";
////		if("userid".equals(key))
////			key = "user_id";
//		param.put("key", key.isEmpty() ? "" : key);
//		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
//		
//		List<BoardDto> list = boardDao.listArticle(param);		//boardDao가 list를 리턴함
//		Collections.sort(list, new HitComparator().reversed());	
//
//		
//		return  list;
//	}
//	@Override
//	public List<BoardDto> listArticle(Map<String, String> map) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		String sort = "";
////		if("userid".equals(key))
////			key = "user_id";
//		param.put("key", key.isEmpty() ? "" : key);
//		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int pgno = Integer.parseInt(map.get("pgno"));
//		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
//		
//		//===============================================================
//
//		List<BoardDto> list = boardDao.listArticle(param);		//boardDao가 list를 리턴함
//		return  list;
//	}
//	public class HitComparator implements Comparator<BoardDto> {
//
//		@Override
//		public int compare(BoardDto o1, BoardDto o2) {
//			if ( o1.getHit() > o2.getHit()) {
//				return 1;
//			} else if ( o1.getHit() < o2.getHit()) {
//				return -1;
//			}
//			return 0;
//		}
//	
//	}
//	// =================================================================
//	
//	@Override
//	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
//		PageNavigation pageNavigation = new PageNavigation();
//
//		int naviSize = SizeConstant.NAVIGATION_SIZE;
//		int sizePerPage = SizeConstant.LIST_SIZE;
//		int currentPage = Integer.parseInt(map.get("pgno"));
//
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
////		if ("userid".equals(key))
////			key = "user_id";
//		param.put("key", key.isEmpty() ? "" : key);
//		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
//		int totalCount = boardDao.getTotalArticleCount(param);
//		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//
//		return pageNavigation;
//	}
//
//	@Override
//	public BoardDto getArticle(int articleNo) throws Exception {
//		return boardDao.getArticle(articleNo);
//	}
//
//	@Override
//	public void updateHit(int articleNo) throws Exception {
//		boardDao.updateHit(articleNo);
//	}
//
//	@Override
//	public void modifyArticle(BoardDto boardDto) throws Exception {
//		// TODO : BoardDaoImpl의 modifyArticle 호출
//		boardDao.modifyArticle(boardDto);
//	}
//
//	@Override
//	public void deleteArticle(int articleNo) throws Exception {
//		// TODO : BoardDaoImpl의 deleteArticle 호출
//		boardDao.deleteArticle(articleNo);
//	}
//
//	@Override
//	public void deleteArticleAll(String id) throws Exception {
//		// TODO BoardDaoImpl의 deleteArticleAll 호출
//		boardDao.deleteArticleAll(id);
//		
//	}

}
