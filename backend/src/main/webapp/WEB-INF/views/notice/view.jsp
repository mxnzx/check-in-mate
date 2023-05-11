<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
	<c:if test="${notice eq null}">
		<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/notice/list";
		</script>
	</c:if>

	<div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="basic">글보기</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary px-5">${notice.articleNo}. ${notice.subject}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">${notice.userId}</span> <br />
                  <span class="text-secondary fw-light"> ${notice.registerTime} 조회 : ${notice.hit} </span>
                </p>
              </div>
            </div>
            <div class="col-md-4 align-self-center text-end">댓글 : 17</div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              ${notice.content}
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                글목록
              </button>
              <c:if test="${userinfo.userId eq 'admin'}">
              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
                글수정
              </button>
              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
                글삭제
              </button>
              </c:if>
            <form id="form-no-param" method="get" action="${root}/notice">
				<input type="hidden" id="npgno" name="pgno" value="${pgno}">
				<input type="hidden" id="nkey" name="key" value="${key}">
				<input type="hidden" id="nword" name="word" value="${word}">
				<input type="hidden" id="articleno" name="articleno" value="${notice.articleNo}">
			</form>
			<form id="form-param" method="get" action="">
      			<input type="hidden" id="pgno" name="pgno" value="${pgno}">
      			<input type="hidden" id="key" name="key" value="${key}">
      			<input type="hidden" id="word" name="word" value="${word}">
    		</form>              
            </div>
          </div>
        </div>
      </div>
    </div>
    </div>
    <script>
	document.querySelector("#btn-list").addEventListener("click", function () {
	  	let form = document.querySelector("#form-param");
	  	form.setAttribute("action", "${root}/notice/list");
      	form.submit();
  	});
	document.querySelector("#btn-mv-modify").addEventListener("click", function () {
    	let form = document.querySelector("#form-no-param");
   		form.setAttribute("action", "${root}/notice/modify");
    	form.submit();
  	});
	document.querySelector("#btn-delete").addEventListener("click", function () {
		if(confirm("정말 삭제하시겠습니까?")) {
			let form = document.querySelector("#form-no-param");
      	  	form.setAttribute("action", "${root}/notice/delete");
          	form.submit();
		}
	});
    </script>
    </div>
    <!-- 로그인 모달창 -->
<%@ include file="../user/login-modal.jsp"%>
<!--회원가입 모달-->
<%@ include file="../user/join-modal.jsp"%>
<%@ include file="../common/footer.jsp" %>