package com.ssafy.enjoytrip.notice.model.service;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.notice.model.NoticeDto;
import com.ssafy.enjoytrip.notice.model.dao.NoticeDao;
import com.ssafy.enjoytrip.notice.model.dao.NoticeDaoImpl;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;



public class NoticeServiceImpl implements NoticeService{

	
	private static NoticeService noticeService = new NoticeServiceImpl();
	private NoticeDao noticeDao;
	
	private NoticeServiceImpl() {
		noticeDao = NoticeDaoImpl.getNoticeDao();
	}
	
	public static NoticeService getNoticeService() {
		return noticeService;
	}

	@Override
	public void writeArticle(NoticeDto noticeDto) throws Exception {
		noticeDao.writeArticle(noticeDto);
		
	}

	@Override
	public List<NoticeDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		String sort = "";
//		if("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int pgno = Integer.parseInt(map.get("pgno"));
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		
		List<NoticeDto> list = noticeDao.listArticle(param);		//noticeDao가 list를 리턴함
		Collections.sort(list, new HitComparator().reversed());	

		
		return  list;
	}

	@Override
	public List<NoticeDto> sortListArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		String sort = "";
//		if("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int pgno = Integer.parseInt(map.get("pgno"));
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		
		//===============================================================

		List<NoticeDto> list = noticeDao.listArticle(param);		//noticeDao가 list를 리턴함
		return  list;
	}
	
	public class HitComparator implements Comparator<NoticeDto> {

		@Override
		public int compare(NoticeDto o1, NoticeDto o2) {
			if ( o1.getHit() > o2.getHit()) {
				return 1;
			} else if ( o1.getHit() < o2.getHit()) {
				return -1;
			}
			return 0;
		}
	
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
//		if ("userid".equals(key))
//			key = "user_id";
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		int totalCount = noticeDao.getTotalArticleCount(param);
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
	public NoticeDto getArticle(int articleNo) throws Exception {
		return noticeDao.getArticle(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		noticeDao.updateHit(articleNo);
		
	}

	@Override
	public void modifyArticle(NoticeDto noticeDto) throws Exception {
		noticeDao.modifyArticle(noticeDto);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		noticeDao.deleteArticle(articleNo);
	}

	@Override
	public void deleteArticleAll(String id) throws Exception {
		noticeDao.deleteArticleAll(id);
	}
	
	
}
