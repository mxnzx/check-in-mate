<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<div class="container">
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				<mark class="basic">글쓰기</mark>
			</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<form id="form-register" method="POST" action="">
				<input type="hidden" name="action" value="write">
				<div class="mb-3">
					<label for="subject" class="form-label">제목 : </label> <input
						type="text" class="form-control" id="subject" name="subject"
						placeholder="제목..." />
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용 : </label>
					<textarea class="form-control" id="content" name="content" rows="7"></textarea>
				</div>
				<div class="col-auto text-center">
					<button type="button" id="btn-register"
						class="btn btn-outline-primary mb-3">글작성</button>
					<button type="reset" class="btn btn-outline-danger mb-3">초기화</button>
				</div>
			</form>
		</div>
	</div>
</div>
</div>
<script>
	document.querySelector("#btn-register").addEventListener("click",
			function() {
				if (!document.querySelector("#subject").value) {
					alert("제목 입력!!");
					return;
				} else if (!document.querySelector("#content").value) {
					alert("내용 입력!!");
					return;
				} else {
					let form = document.querySelector("#form-register");
					form.setAttribute("action", "${root}/article");
					form.submit();
				}
			});
</script>
</div>
<footer>
	<div class="container-fluid px-3 mt-5 mb-3">
		<div class="row">
			<div class="col-sm-1">
				<img src="${root}/img/logo/ssafy-logo-black.b301994e.png" alt=""
					class="img-link">
			</div>
			<div class="col-sm-11">
				<div class="row">
					<div class="col-6">
						<img src="${root}/img/logo/ssafy-logo-small.bd603755.png" alt=""
							class="img-link col"> <img
							src="${root}/img/logo/tour-api-logo.ebcb1140.png" alt=""
							class="img-link col"> <img
							src="${root}/img/logo/visit-korea-logo.858325ef.png" alt=""
							class="img-link col"> <img
							src="${root}/img/logo/mcst-logo.86700cdd.png" alt=""
							class="img-link col">
					</div>
					<div class="col-6" style="text-align: end; font-size: smaller;">
						<div>대표 : Hi SSAM 주소: 212, Teheran-ro, Gangnam-gu, Seoul,
							Republic of Korea</div>
						<div>대표번호: 1588-3357 사업자번호 : 123-4567-987</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</footer>
</body>
</html>
<%--<%@ include file="/common/footer.jsp"%> --%>
