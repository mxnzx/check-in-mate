<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="form-login" method="POST" action="">
	<input type="hidden" name="action" value="login" />
	<div class="modal" id="loginModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Login</h5>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row">
							<div class="form-check mb-3 float-end">
								<input class="form-check-input" type="checkbox" value="ok"
									id="saveid" name="saveid" ${idck} /> <label
									class="form-check-label" for="saveid"> 아이디저장 </label>
							</div>
						</div>
						<div class="row mb-1 text-center">
							<div class="col-3">아이디</div>
							<div class="col">
								<input type="text" placeholder="id" class="form-control"
									id="userid" name="userid" value="${saveid}" />
							</div>

						</div>
						<div class="row mb-1 text-center">
							<div class="col-3">비밀번호</div>
							<div class="col">
								<input type="password" placeholder="pw" class="form-control"
									id="userpwd" name="userpwd">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-mdb-dismiss="modal">id/pw 찾기</button>
					<button type="button" id="btn-login" class="btn btn-primary"
						data-mdb-dismiss="modal">로그인</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
</form>
<script>
	//로그인 로직
	document.querySelector("#btn-login").addEventListener("click", function() {
		if (!document.querySelector("#userid").value) {
			alert("아이디 입력!!");
			return;
		} else if (!document.querySelector("#userpwd").value) {
			alert("비밀번호 입력!!");
			return;
		} else {
			let form = document.querySelector("#form-login");
			form.setAttribute("action", "${root}/user/login");
			console.log("login");
			form.submit();
		}
	});
</script>