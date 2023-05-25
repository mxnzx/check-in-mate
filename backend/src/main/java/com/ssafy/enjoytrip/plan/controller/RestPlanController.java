package com.ssafy.enjoytrip.plan.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ssafy.enjoytrip.hotplace.model.HotplaceFileDto;
import com.ssafy.enjoytrip.plan.model.PlanDto;
import com.ssafy.enjoytrip.plan.model.PlanFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.enjoytrip.plan.model.service.PlanService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;


@RestController
@RequestMapping("/plan")
@CrossOrigin(origins = {"*"})
public class RestPlanController {

    @Autowired
    ServletContext servletContext;

    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.imgPath}")
    private String uploadImgPath;

    @Autowired
    private PlanService planService;

    @Autowired
    public RestPlanController(PlanService planBoardService) {
        super();
        this.planService = planService;
    }

    // 게시글 보기
    @GetMapping("list")
    public ResponseEntity<Map<String, Object>> list() {
        return null;
    }

    //게시글 상세보기
    @GetMapping("view/{articleNo}")
    public ResponseEntity<Map<String, Object>> view(@PathVariable("articleNo") int articleNo) throws Exception {
        PlanDto planDto = null;
        System.out.println("나의여행계획 컨트롤러 들어옴 >>> articleNo"+ articleNo);

        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            planDto = planService.viewPlanArticle(articleNo);
            //planService.updateHit(articleNo);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("planArticle", planDto);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "공지사항 조회 실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    // 이미지 가져오기
    @GetMapping("image/{filename:.+}")
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

    // 게시글 작성 : step1) 이미지저장후, 저장된 src를 가져온다
    @PostMapping("write/image")
    public ResponseEntity<String> uploadFile(
            @RequestParam("mainImage") MultipartFile[] files) throws Exception {

        try {
            // FileUpload 관련 설정
            if (files != null && files.length > 0 && !files[0].isEmpty()) {
                String realPath = servletContext.getRealPath("/upload/plan");
                String today = new SimpleDateFormat("yyMMdd").format(new Date());
                String saveFolder = uploadPath;
                System.out.println("savefolder : >>>>>>>>>>>>>>>" + saveFolder);
                File folder = new File(saveFolder);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                List<PlanFileDto> fileInfos = new ArrayList<>();
                for (MultipartFile mfile : files) {
                    PlanFileDto fileInfoDto = new PlanFileDto();
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
                System.out.println("controller   >>>>>>>>>" + fileInfos);  //======> 파일 잘 들어감

                return ResponseEntity.ok(fileInfos.get(0).getSavefile());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    //게시물 등록
    @PostMapping("write/article")
    public ResponseEntity<String> write(@RequestBody PlanDto planDto) throws Exception {

        System.out.println("컨트롤러에 dto 들어옴>>>>>>>>>>>>"+planDto);
        planService.write(planDto);
        return ResponseEntity.ok("Plan saved successfully.");

    }


    // 게시글 삭제
    @DeleteMapping("delete/{articleno}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("articleno") int articleno, RedirectAttributes redirectAttributes) {
        System.out.println("delete controller >>>>>>>>>>>>>>>>" + articleno);
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            //planBoardService.deleteArticle(articleno, uploadPath);
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

//    // 게시글 수정 -> 구현안할듯
//    @PostMapping("/modify")
//    public ResponseEntity<Map<String, Object>> modify() {
//        return null;
//    }


//    // 검색어가 여행지명에 포함된 여행지 목록 가져오기
//    @GetMapping
//    public ResponseEntity<Map<String, Object>> getAttraction() {
//        return null;
//    }

}

