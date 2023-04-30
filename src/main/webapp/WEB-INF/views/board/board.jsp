<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<!-- 게시판 보드 자리 -->
<div class="row justify-content-center">
	<div class="col-lg-8 col-md-10 col-sm-12">
		<h2 class="my-3 py-3 shadow-sm bg-light text-center">
			<mark class="basic">여행정보공유 게시판</mark>
		</h2>
	</div>
	<div class="col-lg-8 col-md-10 col-sm-12">
		<div class="row align-self-center mb-2">
			<div class="col-md-2 text-start">
				<button type="button" id="btn-mv-register"
					class="btn btn-outline-primary btn-sm">글쓰기</button>
			</div>
			<div class="col-md-7 offset-3">
				<select name="sort_list" id="sort_list"
					class="form-select form-select-sm ms-5 me-1 w-50" aria-label="검색조건">
					<option selected>게시글 정렬</option>
					<option value="notice">최신 순</option>
					<option value="sort_hit">조회수 순</option>
				</select>
				<form class="d-flex" id="form-search" action="">
					<input type="hidden" name="action" value="notice" /> <input
						type="hidden" name="pgno" value="1" /> <select name="key"
						id="key" class="form-select form-select-sm ms-5 me-1 w-50"
						aria-label="검색조건">
						<option selected>검색조건</option>
						<option value="article_no">글번호</option>
						<option value="subject">제목</option>
						<option value="user_id">작성자</option>
					</select>
					<div class="input-group input-group-sm">
						<input type="text" name="word" id="word" class="form-control"
							placeholder="검색어..." />
						<button id="btn-search" class="btn btn-dark" type="button">검색</button>
					</div>
				</form>
			</div>
		</div>
		<table class="table table-hover">
			<thead>
				<tr class="text-center">
					<th scope="col">글번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
					<th scope="col">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boards}">
					<tr class="text-center">
						<th scope="row">${board.articleNo}</th>
						<td class="text-start"><a href="#"
							class="board-title link-dark" data-no="${board.articleNo}"
							style="text-decoration: none"> ${board.subject} </a></td>
						<td>${board.userId}</td>
						<td>${board.hit}</td>
						<td>${board.registerTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="row">${navigation.navigator}</div>
</div>
<!-- <form id="form-param" method="get" action="">
	<input type="hidden" id="p-action" name="action" value=""> <input
		type="hidden" id="p-pgno" name="pgno" value=""> <input
		type="hidden" id="p-key" name="key" value=""> <input
		type="hidden" id="p-word" name="word" value="">
</form> -->

<form id="form-param" method="get" action="">
	<input type="hidden" name="pgno" id="pgno" value="${pgno}"> <input
		type="hidden" name="key" value="${key}"> <input type="hidden"
		name="word" value="${word}">
</form>
<form id="form-no-param" method="get" action="${root}/board/view">
	<input type="hidden" name="pgno" value="${pgno}"> <input
		type="hidden" name="key" value="${key}"> <input type="hidden"
		name="word" value="${word}"> <input type="hidden"
		id="articleno" name="articleno" value="">
</form>
</div>
<script>
	let titles = document.querySelectorAll(".board-title");
	titles.forEach(function(title) {
		title.addEventListener("click", function() {
			console.log(this.getAttribute("data-no"));
			location.href = "${root}/board?action=view&articleno="
					+ this.getAttribute("data-no");
		});
	});

	document.querySelector("#btn-mv-register").addEventListener("click",
			function() {
				let form = document.querySelector("#form-param");
				form.setAttribute("action", "${root}/board/write");
				form.submit();
			});

	document.querySelector("#btn-search").addEventListener("click", function() {
		let form = document.querySelector("#form-search");
		form.setAttribute("action", "${root}/board");
		form.submit();
	});
	document.querySelector("#sort_list").addEventListener("change", function() {
		let option;
		option = this.options[this.selectedIndex];
		option = option.value;
		console.log(option);
		location.href = "${root}/board?action=" + option;
	});

	let pages = document.querySelectorAll(".page-link");
	pages.forEach(function(page) {
		page.addEventListener("click", function() {
			console.log(this.parentNode.getAttribute("data-pg"));
			document.querySelector("#p-action").value = "notice";
			document.querySelector("#p-pgno").value = this.parentNode
					.getAttribute("data-pg");
			document.querySelector("#p-key").value = "${param.key}";
			document.querySelector("#p-word").value = "${param.word}";
			document.querySelector("#form-param").submit();
		});
	});
	let msg = '${msg}';
	if (msg != "") {
		alert(msg);
	};
</script>
</div>
<!-- 하단 Footer -->
<%@ include file="../common/footer.jsp"%>
<!-- 로그인 모달창 -->
<%@ include file="../common/login-modal.jsp"%>
<!--회원가입 모달-->
<%@ include file="../common/join-modal.jsp"%>

