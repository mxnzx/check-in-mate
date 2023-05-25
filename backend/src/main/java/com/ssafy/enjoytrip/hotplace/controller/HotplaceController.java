package com.ssafy.enjoytrip.hotplace.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.enjoytrip.hotplace.model.HotplaceDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceFileDto;
import com.ssafy.enjoytrip.hotplace.model.HotplaceScrapDto;
import com.ssafy.enjoytrip.hotplace.model.service.HotplaceService;

@RestController
@RequestMapping("hotplace")
@CrossOrigin(origins = { "*" })
public class HotplaceController {

	private final Logger logger = LoggerFactory.getLogger(HotplaceController.class);

	@Autowired
	ServletContext servletContext;
	
	@Value("${file.path}")
	private String uploadPath;

	@Value("${file.imgPath}")
	private String uploadImgPath;

	@Qualifier("HotplaceServiceImpl")
	private HotplaceService hotplaceService;

	@Autowired
	public HotplaceController(HotplaceService hotplaceService) {
		super();
		this.hotplaceService = hotplaceService;
	}

	// 핫플레이스 글 목록 출력
	@GetMapping("/list")
	public List<HotplaceDto> list(@RequestParam Map<String, String> map) throws Exception {
		System.out.println("list controller >>>" + map);
		return hotplaceService.listArticle(map);
	}

	// 핫플레이스 글 쓰기
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> write(@RequestParam("userid") String userid,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("upfile") MultipartFile[] files) {

		ResponseEntity<Map<String, Object>> resEntity;
		try {
			// FileUpload 관련 설정
			if (files != null && files.length > 0 && !files[0].isEmpty()) {
				String realPath = servletContext.getRealPath("/upload/hotplace");
				String today = new SimpleDateFormat("yyMMdd").format(new Date());
//				String saveFolder = uploadPath + File.separator + today;
				String saveFolder = uploadPath;
				System.out.println("savefolder : >>>>>>>>>>>>>>>"  + saveFolder);
				File folder = new File(saveFolder);
				if (!folder.exists()) {
					folder.mkdirs();
				}

				List<HotplaceFileDto> fileInfos = new ArrayList<>();
				for (MultipartFile mfile : files) {
					HotplaceFileDto fileInfoDto = new HotplaceFileDto();
					String originalFileName = mfile.getOriginalFilename();
					if (!originalFileName.isEmpty()) {
						String saveFileName = UUID.randomUUID().toString()
								+ originalFileName.substring(originalFileName.lastIndexOf('.'));
						fileInfoDto.setSavefolder(today);
						fileInfoDto.setOriginalfile(originalFileName);
						fileInfoDto.setSavefile(saveFileName);
						mfile.transferTo(new File(folder, saveFileName));
					}
					fileInfos.add(fileInfoDto);
				}
				System.out.println("controller>>>>>>>>>" + fileInfos);
				// hotplaceDto.setFileInfos(fileInfos);

				HotplaceDto hotplaceDto = new HotplaceDto();
				hotplaceDto.setUserid(userid);
				hotplaceDto.setTitle(title);
				hotplaceDto.setContent(content);
				hotplaceDto.setFileInfos(fileInfos);
				hotplaceService.writeArticle(hotplaceDto);

			}

			Map<String, Object> map = new HashMap<>();
			map.put("resmsg", "입력 성공");
			map.put("resValue", null);
			resEntity = new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("resmsg", "입력 실패");
			resEntity = new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return resEntity;
	}
	
    // 이미지 가져오기
    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        // 이미지 파일이 저장된 경로
        String imagePath = uploadPath + File.separator + filename;
        
        // 이미지 파일을 Resource 타입으로 로드
        Resource imageResource = new UrlResource("file:" + imagePath);
        
        // 이미지 파일의 MIME 타입을 가져옴
        String contentType = servletContext.getMimeType(imageResource.getFile().getAbsolutePath());
        
        // 이미지 파일을 Response에 포함하여 반환
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(imageResource);
    }
	
	// 파일 , 게시글 삭제 
	@RequestMapping(value = "/delete/{articleno}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> delete(@PathVariable("articleno") int articleno,
			RedirectAttributes redirectAttributes) throws Exception {
//		//logger.debug("delete articleNo : {}", articleNo);
//		hotplaceService.DeleteArticle(articleno, uploadPath);
//		return ResponseEntity.ok("삭제완료");
		System.out.println("delete controller >>>>>>>>>>>>>>>>" + articleno);
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			hotplaceService.deleteArticle(articleno, uploadPath);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제 성공");
			map.put("resValue", null);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	// 핫플레이스 글보기
	@RequestMapping(value = "/view/{articleno}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> view(@PathVariable("articleno") int articleno) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;
		HotplaceDto hotplaceDto = null;
		try {
			hotplaceDto = hotplaceService.getArticle(articleno);
			hotplaceService.updateHit(articleno);
			System.out.println("view article >>>>>>>>>>"  + hotplaceDto);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회 성공");
			map.put("article", hotplaceDto);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}

		return resEntity;
	}
	

	// 핫플레이스 글 수정하기 ( 삭제 후 입력 ) 
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> modify(@PathVariable("articleno") int articleno , @RequestParam("userid") String userid,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("upfile") MultipartFile[] files) throws Exception {
		ResponseEntity<Map<String, Object>> resEntity = null;
		HotplaceDto hotplaceDto = new HotplaceDto();
		try {
			hotplaceDto.setUserid(userid);
			hotplaceDto.setTitle(title);
			hotplaceDto.setContent(content);
			hotplaceService.deleteArticle(articleno, uploadPath);
			hotplaceService.writeArticle(hotplaceDto);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정 성공");
			map.put("article", hotplaceDto);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	// 스크랩
	@RequestMapping(value = "/scrap", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> scrap(@RequestParam("articleno") int articleno, @RequestParam("userid") String userid) {
	    ResponseEntity<Map<String, Object>> resEntity = null;
	    HotplaceScrapDto hotplaceScrapDto = new HotplaceScrapDto();
	    System.out.println(userid + " " + articleno);
	    try {
	        hotplaceScrapDto.setUserid(userid);
	        hotplaceScrapDto.setArticleno(articleno); 
	        System.out.println("여기까지 실행");
	        hotplaceService.scrap(articleno, userid); //
	        System.out.println(userid + " " + articleno);
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("resmsg", "스크랩 성공");
	        map.put("article", hotplaceScrapDto);
	        resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    } catch (Exception e) {
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("resmsg", "스크랩 실패");
	        resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	    }
	    return resEntity;
	}
	
	// 스크랩 가져오기
	@GetMapping("/scrap/{userid}")
	public ResponseEntity<Map<String, Object>> getScrap(@PathVariable("userid") String userid){
		ResponseEntity<Map<String, Object>> resEntity = null;
		HotplaceScrapDto hotplaceScrapDto = null;
		try {
			hotplaceScrapDto = hotplaceService.getScrap(userid);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "스크랩 가져오기 성공");
			map.put("getScrap", hotplaceScrapDto);
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch(Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			e.printStackTrace();
			map.put("resmsg", "스크랩 가져오기 실패");
			resEntity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		return resEntity;		
	}
}
