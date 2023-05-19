package com.ssafy.enjoytrip.plan.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.plan.model.service.PlanService;

@RestController
@RequestMapping("/plan")
public class RestPlanController {

    private PlanService planBoardService;

    public RestPlanController(PlanService planBoardService) {
        super();
        this.planBoardService = planBoardService;
    }

    // 게시글 보기
    @GetMapping
    public ResponseEntity<Map<String, Object>> list() {
        return null;
    }

    //게시글 상세보기
    @GetMapping
    public ResponseEntity<Map<String, Object>> detail() {
        return null;
    }

    // 게시글 작성
    @PostMapping
    public ResponseEntity<Map<String, Object>> write() {
        return null;
    }

    // 게시글 수정
    @PutMapping
    public ResponseEntity<Map<String, Object>> modify() {
        return null;
    }

    // 게시글 삭제
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> delete() {
        return null;
    }

    // 검색어가 여행지명에 포함된 여행지 목록 가져오기
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAttraction() {
        return null;
    }

}