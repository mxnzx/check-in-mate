package com.ssafy.enjoytrip.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.service.MemberService;

@RestController
@RequestMapping("/user/api")
public class RestMemberController {

	private MemberService memberService;

	public RestMemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	// 회원가입
	@PostMapping(value = "/join", headers = "content-type=application/json")
	public ResponseEntity<Map<String, Object>> join(@RequestBody MemberDto memberDto) {
		//logger.debug("memberDto info : {}", memberDto);
		Map<String, Object> responseData = new HashMap<>();
		try {
			memberService.joinMember(memberDto);
			// return "redirect:/user/login";
			responseData.put("result", memberDto);
			responseData.put("message", "회원가입 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
	        responseData.put("result", "error");
	        responseData.put("message", "회원 가입 중 문제 발생!!!");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
		}
		return ResponseEntity.ok(responseData);
	}

	// 회원탈퇴
	@DeleteMapping("/deleteMember")
	public ResponseEntity<Map<String, Object>> deleteMember(@RequestBody MemberDto memberDto) {
		Map<String, Object> responseData = new HashMap<>();
		String userId = memberDto.getUserId();
		System.out.println(userId);
		// logger.debug("memberDto info : {}", memberDto);
		try {
			memberService.deleteMember(userId);
			// return "redirect:/user/login";
			responseData.put("message", "회원탈퇴 성공!!");
		} catch (Exception e) {
			e.printStackTrace();
			responseData.put("result", "error");
			responseData.put("message", "회원탈퇴 실패!!");
			
		}
		return ResponseEntity.ok(responseData);
	}

	// 회원정보 수정 ( 비밀번호 변경 )
	@PutMapping("/updateMember")
	public ResponseEntity<Map<String, Object>> updateMember(@RequestBody MemberDto memberDto) {
		Map<String, Object> responseData = new HashMap<>();
		String userId = memberDto.getUserId();
		String userPwd = memberDto.getUserPwd();
		System.out.println(memberDto + " 업데이트");
		try {
			memberService.updateMember(memberDto);
			// return "redirect:/user/login";
			responseData.put("result", memberDto);
			responseData.put("message", "회원정보 수정 성공!!!");
		    
		} catch (Exception e) {
			e.printStackTrace();
			// return "error/error";
			responseData.put("result", "error");
			responseData.put("message", "회원정보 수정 실패!!");
		}
		return ResponseEntity.ok(responseData);

	}

	// 아이디 값 가져와서 기존 아이디와 비교해서 겹치는게 있는지 체크
	@GetMapping("/{userid}")
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		// logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		return cnt + "";
	}

}
