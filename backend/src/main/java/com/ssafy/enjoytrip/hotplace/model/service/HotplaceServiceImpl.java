package com.ssafy.enjoytrip.hotplace.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.mapper.BoardMapper;
import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceFileDto;
import com.ssafy.enjoytrip.hotplace.model.mapper.HotplaceMapper;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service("HotplaceServiceImpl")
public class HotplaceServiceImpl implements HotplaceService{
	
	private HotplaceMapper hotplaceMapper;
	
	public HotplaceServiceImpl(HotplaceMapper hotplaceMapper) {
		super();
		this.hotplaceMapper = hotplaceMapper;
	}

	@Override
	public void writeArticle(HotplaceDto hotplaceDto) throws Exception {
		System.out.println("serviceImpl >>>>>>>>>>>>" + hotplaceDto);
		hotplaceMapper.writeArticle(hotplaceDto);
		List<HotplaceFileDto> fileInfos = hotplaceDto.getFileInfos();
		System.out.println("serviceImpl >>>>>>>>>>>>" + fileInfos);
		if(fileInfos != null && !fileInfos.isEmpty()) {
			hotplaceMapper.registerFile(hotplaceDto);
		}
	}

	@Override
	public List<HotplaceDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
		System.out.println(map);
		System.out.println(param);
//		if("userid".equals(key))
//			key = "user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);
		return hotplaceMapper.listArticle(param);
	}

	@Override
	public HotplaceDto getArticle(int articleno) throws Exception {
		return hotplaceMapper.getArticle(articleno);
	}

	@Override
	public void modifyArticle(HotplaceDto hotplaceDto) throws Exception {
		hotplaceMapper.modifyArticle(hotplaceDto);
	}

	@Override
	public void deleteArticle(int articleno, String path) throws Exception {
		System.out.println("delete serviceimpl >>>>>>>>>>>" + articleno);
		List<HotplaceFileDto> fileList = hotplaceMapper.fileInfoList(articleno);
		System.out.println(fileList);
		hotplaceMapper.deleteArticle(articleno);
		//hotplaceMapper.deleteFile(articleno);
		for (HotplaceFileDto hotplaceFileDto : fileList) {
			File file = new File(path + File.separator + hotplaceFileDto.getSavefolder() + File.separator + hotplaceFileDto.getSavefile());
			file.delete();
		}
		
	}

}
