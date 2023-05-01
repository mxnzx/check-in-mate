<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				<mark class="basic">글수정</mark>
			</h2>
		</div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <form id="form-modify" method="POST" action="">
          	<input type="hidden" name="pgno" value="${pgno}">
		    <input type="hidden" name="key" value="${key}">
		   	<input type="hidden" name="word" value="${word}">
            <input type="hidden" name="articleNo" value="${board.articleNo}">
            <div class="mb-3">
              <label for="subject" class="form-label">제목 : </label>
              <input type="text" class="form-control" id="subject" name="subject" value="${board.subject}" />
            </div>
            <div class="mb-3">
              <label for="content" class="form-label">내용 : </label>
              <textarea class="form-control" id="content" name="content" rows="7">${board.content}</textarea>
            </div>
            <div class="col-auto text-center">
              <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3">
                글수정
              </button>
              <button type="button" id="btn-list" class="btn btn-outline-danger mb-3">
                목록으로이동...
              </button>
            </div>
          </form>
		</div>
	</div>
</div>
<script>
	document.querySelector("#btn-modify").addEventListener("click", function() {
		if (!document.querySelector("#subject").value) {
			alert("제목 입력!!");
			return;
		} else if (!document.querySelector("#content").value) {
			alert("내용 입력!!");
			return;
		} else {
			let form = document.querySelector("#form-modify");
			form.setAttribute("action", "${root}/board/modify");
			form.submit();
		}
	});
	document.querySelector("#btn-list").addEventListener("click", function() {
		location.href = "${root}/board/list";
	});
</script>
</div>
<%@ include file="../common/footer.jsp"%>
