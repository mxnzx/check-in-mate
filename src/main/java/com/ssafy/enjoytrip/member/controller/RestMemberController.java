package com.ssafy.enjoytrip.member.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<MemberDto> join(@RequestBody MemberDto memberDto) {
		// logger.debug("memberDto info : {}", memberDto);
		try {
			memberService.joinMember(memberDto);
			//return "redirect:/user/login";
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			//model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			//return "error/error";
			//return ResponseEntity.
			//일단 둘다 이렇게 보낸다
			return ResponseEntity.ok(memberDto);
		}
	}
	
	// 회원탈퇴
	@DeleteMapping("/deleteMember")
	public ResponseEntity<MemberDto> deleteMember(@RequestBody MemberDto memberDto) {
		String userId = memberDto.getUserId();
		System.out.println(userId);
		// logger.debug("memberDto info : {}", memberDto);
		try {
			memberService.deleteMember(userId);
			//return "redirect:/user/login";
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			//return "error/error";
			return ResponseEntity.ok(memberDto);
		}
	}
	
	// 회원정보 수정 ( 비밀번호 변경 ) 
	@PutMapping("/updateMember")
	public ResponseEntity<MemberDto> updateMember(@RequestBody MemberDto memberDto) {
		String userId = memberDto.getUserId();
		String userPwd = memberDto.getUserPwd();
		System.out.println(memberDto + " 업데이트"  );
		try {
			memberService.updateMember(memberDto);
			//return "redirect:/user/login";
			return ResponseEntity.ok(memberDto);
		} catch (Exception e) {
			e.printStackTrace();
			//return "error/error";
			return ResponseEntity.ok(memberDto);
		}
		
	}
	
}
