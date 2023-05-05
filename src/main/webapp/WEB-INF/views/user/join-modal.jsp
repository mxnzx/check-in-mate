<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="form-join" method="POST" action="">
	<div class="modal" id="signUpModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">회원 가입</h5>
				</div>
				<div class="modal-body">
					<div class="row mb-1 text-center">
						<div class="col-3">이름</div>
						<div class="col">
							<input id="username_join" name="userName" class="form-control"
								type="text" placeholder="이름"></input>
						</div>
					</div>
					<div class="row mb-1 text-center">
						<div class="col-3">아이디</div>
						<div class="col">
							<input id="userid_join" name="userId" class="form-control"
								type="text" placeholder="아이디"></input>
						</div>
					</div>
					<div id="idcheck-result"></div>
					<div class="row"></div>
					<div class="row mb-1 text-center">
						<div class="col-3">비밀번호</div>
						<div class="col">
							<input id="userpwd_join" name="userPwd" class="form-control"
								type="password" placeholder="비밀번호"></input>
						</div>
					</div>
					<div class="row mb-1 text-center">
						<div class="col-3">비밀번호확인</div>
						<div class="col">
							<input id="pwdcheck_join" name="pwdcheck" class="form-control"
								type="text" placeholder="비밀번호확인"></input>
						</div>
					</div>
					<div class="row mb-1 text-center">
						<div class="col-3">이메일</div>
						<div class="col-3">
							<input id="emailid_join" name="emailId" class="form-control"
								type="text" placeholder="이메일"></input>
						</div>
						<div class="col-1">
							<span class="input-group-text" style="background-color:white; border:none; padding-left:2px">@</span>
						</div>
						<div class="col">
							<select class="form-select" id="emaildomain_join" name="emailDomain">
								<option value="ssafy.com">SSAFY</option>
								<option value="google.com">GOOGLE</option>
								<option value="kakao.com">KAKAO</option>
								<option value="naver.com">NAVER</option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="btn-join" type="button" class="btn btn-primary"
						data-mdb-dismiss="modal">회원가입</button>

					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
	</div>
</form>
<script>
	//회원가입 로직
  	let isUseId = false;
    	document.querySelector("#userid_join").addEventListener("keyup", function () {
	 		let user = this.value;
	   	 	let resultDiv = document.querySelector("#idcheck-result");
	   	 	if(user.length < 6 || user.length > 16) {
	   		 	resultDiv.setAttribute("class", "mb-3 text-dark");
	   		 	resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다.";
	   		 	isUseId1 = false;
	   	 	} else {
	   	 		//아이디 중복 체크
	   		 	fetch("${root}/user/api/" + user)
	 	   		.then(response => response.text())
	 	   		.then(data => {
	 		 		if(data == 0) {
	 		   			resultDiv.setAttribute("class", "mb-3 text-primary");
	 	       			resultDiv.textContent = user + "는 사용할 수 있습니다.";
	 	       			isUseId = true;
	 		 		} else {
	 		   			resultDiv.setAttribute("class", "mb-3 text-danger");
	  		       		resultDiv.textContent = user + "는 사용할 수 없습니다.";
	  		     		isUseId = false;
	 		 		}
	   		   });
	   	 	}
	     });
     
 	document.querySelector("#btn-join").addEventListener("click", function () {
 		if (!document.querySelector("#username_join").value) {
           alert("이름 입력!!");
           return;
         } else if (!document.querySelector("#userid_join").value) {
           alert("아이디 입력!!");
           return;
         } else if (!document.querySelector("#userpwd_join").value) {
           alert("비밀번호 입력!!");
           return;
         } else if (document.querySelector("#userpwd_join").value != document.querySelector("#pwdcheck_join").value) {
           alert("비밀번호 확인!!");
           return;
         } else if (!isUseId) {
        	 alert("사용할 수 없는 아이디 입니다.");
        	 return;
         } else {
           let form = document.querySelector("#form-join");
           form.setAttribute("action", "${root}/user/api/join");
           form.submit();
           
         }
 	});
  </script>