package com.ssafy.enjoytrip.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.member.model.MemberDto;
import com.ssafy.enjoytrip.member.model.service.JwtServiceImpl;
import com.ssafy.enjoytrip.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
@CrossOrigin
public class RestMemberController {

	public static final Logger logger = LoggerFactory.getLogger(RestMemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserid());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserid());// key, data
				memberService.saveRefreshToken(memberDto.getUserid(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserid()))) {
				String accessToken = jwtService.createAccessToken("userid", memberDto.getUserid());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}


//	private MemberService memberService;
//
//	public RestMemberController(MemberService memberService) {
//		super();
//		this.memberService = memberService;
//	}
//
//	// 회원가입
//	@PostMapping(value = "/join", headers = "content-type=application/json")
//	public ResponseEntity<Map<String, Object>> join(@RequestBody MemberDto memberDto) {
//		//logger.debug("memberDto info : {}", memberDto);
//		Map<String, Object> responseData = new HashMap<>();
//		try {
//			memberService.joinMember(memberDto);
//			// return "redirect:/user/login";
//			responseData.put("result", memberDto);
//			responseData.put("message", "회원가입 성공!!");
//		} catch (Exception e) {
//			e.printStackTrace();
//	        responseData.put("result", "error");
//	        responseData.put("message", "회원 가입 중 문제 발생!!!");
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
//		}
//		return ResponseEntity.ok(responseData);
//	}
//
//	// 회원탈퇴
//	@DeleteMapping("/deleteMember")
//	public ResponseEntity<Map<String, Object>> deleteMember(@RequestBody MemberDto memberDto) {
//		Map<String, Object> responseData = new HashMap<>();
//		String userId = memberDto.getUserId();
//		System.out.println(userId);
//		// logger.debug("memberDto info : {}", memberDto);
//		try {
//			memberService.deleteMember(userId);
//			// return "redirect:/user/login";
//			responseData.put("message", "회원탈퇴 성공!!");
//		} catch (Exception e) {
//			e.printStackTrace();
//			responseData.put("result", "error");
//			responseData.put("message", "회원탈퇴 실패!!");
//			
//		}
//		return ResponseEntity.ok(responseData);
//	}
//
//	// 회원정보 수정 ( 비밀번호 변경 )
//	@PutMapping("/updateMember")
//	public ResponseEntity<Map<String, Object>> updateMember(@RequestBody MemberDto memberDto) {
//		Map<String, Object> responseData = new HashMap<>();
//		String userId = memberDto.getUserId();
//		String userPwd = memberDto.getUserPwd();
//		System.out.println(memberDto + " 업데이트");
//		try {
//			memberService.updateMember(memberDto);
//			// return "redirect:/user/login";
//			responseData.put("result", memberDto);
//			responseData.put("message", "회원정보 수정 성공!!!");
//		    
//		} catch (Exception e) {
//			e.printStackTrace();
//			// return "error/error";
//			responseData.put("result", "error");
//			responseData.put("message", "회원정보 수정 실패!!");
//		}
//		return ResponseEntity.ok(responseData);
//
//	}
//
//	// 아이디 값 가져와서 기존 아이디와 비교해서 겹치는게 있는지 체크
//	@GetMapping("/{userid}")
//	public String idCheck(@PathVariable("userid") String userId) throws Exception {
//		// logger.debug("idCheck userid : {}", userId);
//		int cnt = memberService.idCheck(userId);
//		return cnt + "";
//	}

