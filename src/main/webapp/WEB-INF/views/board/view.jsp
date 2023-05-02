<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<c:if test="${board eq null}">
	<script>
		alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
		location.href = "${root}/board/list";
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
				<h2 class="text-secondary px-5">${board.articleNo}.
					${board.subject}</h2>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="clearfix align-content-center">
						<img class="avatar me-2 float-md-start bg-light p-2"
							src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
						<p>
							<span class="fw-bold">${board.userId}</span> <br /> <span
								class="text-secondary fw-light"> ${board.registerTime} 조회
								: ${board.hit} </span>
						</p>
					</div>
				</div>
				<div class="col-md-4 align-self-center text-end">댓글 : 17</div>
				<div class="divider mb-3"></div>
				<div class="text-secondary">${board.content}</div>
				<div class="divider mt-3 mb-3"></div>
				<div class="d-flex justify-flex-end" style="    flex-direction: column;">
					<div class="row">

						<div>
							<ul id="commentUL"></ul>
						</div>
						<div>${userInfo.userId}</div>
						<div>
							<textarea id="content" rows="5" style="width: 100%;" placeholder="댓글 입력"></textarea>
						</div>
						<div  style="text-align: right;">
							<button id="commentRegBtn" class = "btn btn-outline-primary mb-3" >댓글 등록</button>
						</div>

					</div>
					<div style="text-align: right;">
						<button type="button" id="btn-list"
							class="btn btn-outline-primary mb-3">글목록</button>
							<c:if test="${userinfo.userId eq board.userId}">
						<button type="button" id="btn-mv-modify"
							class="btn btn-outline-success mb-3 ms-1">글수정</button>
						<button type="button" id="btn-delete"
							class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
					</c:if>
					</div> 

				</div>
				<form id="form-no-param" method="get" action="${root}/board">
					<input type="hidden" id="npgno" name="pgno" value="${pgno}">
					<input type="hidden" id="nkey" name="key" value="${key}"> <input
						type="hidden" id="nword" name="word" value="${word}"> <input
						type="hidden" id="articleno" name="articleno"
						value="${board.articleNo}">
				</form>
				<form id="form-param" method="get" action="">
					<input type="hidden" id="pgno" name="pgno" value="${pgno}">
					<input type="hidden" id="key" name="key" value="${key}"> <input
						type="hidden" id="word" name="word" value="${word}">
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
    	  	form.setAttribute("action", "${root}/board/list");
          	form.submit();
      	});
    	
    	// 자신의 아이디와 같을 때만 수정 , 삭제 가능 
    	if(`${userinfo.userId}`===`${board.userId}`){
		document.querySelector("#btn-mv-modify").addEventListener("click", function () {
	    	let form = document.querySelector("#form-no-param");
	   		form.setAttribute("action", "${root}/board/modify");
	    	form.submit();
	  	});
		document.querySelector("#btn-delete").addEventListener("click", function () {
			if(confirm("정말 삭제하시겠습니까?")) {
				let form = document.querySelector("#form-no-param");
	      	  	form.setAttribute("action", "${root}/board/delete");
	          	form.submit();
			}
		});
    	}
		
	    /*-------------------------------------------------------------------------	   
	    	댓글 스크립트	    
	    ------------------------------------------------------------------------- */
		let commentRegBtn = document.getElementById("commentRegBtn");
	    commentRegBtn.addEventListener('click', function () {
	    	let body = {
				"articleNo": "${board.articleNo}",
				"userId": "${userinfo.userId}", 
				"comment": document.getElementById("content").value
	    	};
	    	console.log(body);
	    	let config = {
	    	          method: "POST",
	    	          headers: {
	    	            "Content-Type": "application/json",
	    	          },
	    	          body: JSON.stringify(body),
	    	        };
	    	        fetch(`${root}/comment`, config)
	    	          .then((response) => response.json())
	    	          .then((data) => {
	    	        	  console.dir(data)
	    	        	  makeCommentList(data)
	    	        	 });
	    });
	    
	    function makeCommentList(list) {
	    	let html = "";
	    	for (let i = 0; i < list.length; i++){
	    		let comment = list[i];
	    		html += `
	    			<li>
	    				<ul>작성자 : \${comment.userId}</ul>
	    				<ul>내용 : \${comment.comment}</ul>
	    				<ul>등록시간 : \${comment.registerTime}</ul>
	    				<ul><a href="#1" onclick="commentDel(\${comment.commentNo})">삭제</a></ul>
	    				<br>
	    			</li>
	    		`;
	    	}
	    	document.getElementById("commentUL").innerHTML = html;
	    }
	    
	    function commentDel(commentNo) {
	    	 let config = {
	    	            method: "DELETE",
	    	            headers: {
	    	              "Content-Type": "application/json",
	    	            },
	    	          };
	    	          fetch(`${root}/comment/${board.articleNo}/\${commentNo}`, config) /*자바스크립트 변수라서 \붙혀서 el태그아니라고 표현해줌, 다른건 공유영역 */
	    	            .then((response) => response.json())
	    	            .then((data) => makeCommentList(data));
	    }
	    fetch(`${root}/comment/${board.articleNo}`) /*자바스크립트 변수라서 \붙혀서 el태그아니라고 표현해줌, 다른건 공유영역 */
	     	.then((response) => response.json())
	     	.then((data) => makeCommentList(data));
    </script>
</div>
<!-- 로그인 모달창 -->
<%@ include file="../user/login-modal.jsp"%>
<!--회원가입 모달-->
<%@ include file="../user/join-modal.jsp"%>
<%@ include file="../common/footer.jsp"%>