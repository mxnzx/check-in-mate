<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<!--중앙 content Start-->
	<div class="container" style="text-align: right;">
		<div class="row" style="height: 100px;"></div>
		<div class="row-4 m-1">
			<form id="myPage-form" action="${root}/user" method="POST">
				<input id="input-hidden" type="hidden" name="action"
					value="userUpdate">
				<div class="row text-center">
					<div class="col-4"></div>
					<div class="col-4">
						<img src="../img/etc/no-profile.png" alt="profile"
							style="height: 100px">
					</div>
					<div class="col-4"></div>
				</div>
				<div class="row m-3">
					<div class="col-4">
						<p style="padding-top: 8px;">아이디</p>
					</div>
					<div class="col-6">
						<input class="form-control w-75" type="text" placeholder="아이디자리"
							readonly="readonly" id="id" name="id"
							value="${ sessionScope.userinfo.userId }">
					</div>
				</div>
				<div class="row m-3">
					<div class="col-4">
						<p style="padding-top: 8px;">비밀번호</p>
					</div>
					<div class="col-6">
						<input class="form-control w-75" type="password" id="pw" name="pw"
							placeholder="변경하실 비밀번호를 입력하세요">
					</div>
				</div>
				<div class="row m-3">
					<div class="col-4">
						<p style="padding-top: 8px;">비밀번호 확인</p>
					</div>
					<div class="col-6">
						<input class="form-control w-75" type="password" id="pwCheck"
							name="pwCheck" placeholder="변경하신 비밀번호를 한번 더 입력하세요">
					</div>
				</div>
				<div class="row m-3">
					<div class="col">
						<button id="myPageUpdate" type="button" class="btn btn-secondary"
							data-mdb-dismiss="modal">수정</button>
					</div>
					<div class="col" style="text-align: left;">
						<button id="delete-btn" type="button" class="btn btn-danger"
							data-bs-dismiss="modal">회원탈퇴</button>
					</div>
				</div>
		</div>
		</form>
		<script type="text/javascript">
				document.querySelector("#myPageUpdate").addEventListener("click", function() {
					if (!document.querySelector("#pw").value) {
						alert("비밀번호를 입력해주세요.");
						return;
					}
					if (!document.querySelector("#pwCheck").value) {
						alert("비밀번호를 한번 더 입력해주세요.");
						return;
					}
					
					if (document.querySelector("#pw").value != document.querySelector("#pwCheck").value) {
						alert("비밀번호를 정확하게 입력해주세요.");
						return;
					}
					alert("비밀번호가 변경되었습니다.");
					document.querySelector("#myPage-form").submit();
				});
				
				document.querySelector("#delete-btn").addEventListener("click", function() {
						document.querySelector("#input-hidden").setAttribute("value", "delete");
						document.querySelector("#myPage-form").submit();
				});
			</script>

		<div class="row" style="height: 100px;"></div>
	</div>
</div>
</div>
<!--중앙 content End-->

<!-- 하단 Footer Start  -->
<%@ include file="/common/footer.jsp"%>
<!-- 하단 Footer End  -->
	<!-- 로그인 모달창 -->
	<%@ include file="/common/login-modal.jsp" %>

	<!--회원가입 모달-->
	<%@ include file="/common/join-modal.jsp" %>

</body>

</html>
