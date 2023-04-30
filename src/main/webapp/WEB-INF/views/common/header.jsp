<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value=" ${pageContext.request.contextPath}" />
<!-- 아이디 저장 쿠키 가져오기 -->
<c:if test=" ${cookie.ssafy_id.value ne null}">
	<c:set var="idck" value=" checked" />
	<c:set var="saveid" value=" ${cookie.ssafy_id.value}" />
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="${root}/img/favicon.ico" />
<link rel="stylesheet" href="${root}/assets/css/index.css" />
<link rel="stylesheet" href="${root}/assets/css/app.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous">
	
</script>
<script type="text/javascript" src="${root}/js/key.js"></script>
<!-- <script src="./resources/js/jquery-3.5.1.min.js"></script>
    <script src="./resources/js/bootstrap.bundle.min.js"></script> -->
<title>Enjoy Trip</title>
</head>
<body>
	<div id="wrapper"
		style="height: auto; min-height: 100%; padding-bottom: 40px;">
		<div class="container-fluid">
			<!-- 상단 Header Start  -->
			<header>
				<nav class="navbar navbar-expand-lg my-2">
					<div class="container-fluid">
						<a class="navbar-brand fs-3" href="${root}/index.jsp"
							id="enjoyTrip"> Enjoy Trip</a>
						<button class="navbar-toggler" type="button"
							data-bs-toggle="collapse"
							data-bs-target="#navbarSupportedContent"
							aria-controls="navbarSupportedContent" aria-expanded="false"
							aria-label="Toggle navigation">
							<span class="navbar-toggler-icon"></span>
						</button>
						<div class="collapse navbar-collapse" id="navbarSupportedContent">
							<ul class="navbar-nav me-auto mb-2 mb-lg-0">
								<li class="nav-item"><a class="nav-link"
									aria-current="page" href="${root}/navigator/searchTrip">지역별여행지</a>
									<!-- KakaoMap API Page --></li>
								<li class="nav-item"><a class="nav-link" href="">나의여행계획</a></li>
								<li class="nav-item"><a class="nav-link" href="">핫플자랑하기</a></li>
								<li class="nav-item"><a class="nav-link" href="${root}/board/list?pgno=1&key=&word=">여행정보공유</a></li>
							</ul>
							<ul class="navbar-nav mb-2 mb-lg-0 m-2">
								<%@ include file="confirm.jsp"%>
								<li class="nav-item"><a class="nav-link" href="${root}/article?action=notice&pgno=1&key=&word=">공지사항</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false"> 회원관리 </a>
									<ul class="dropdown-menu">
										<c:if test="${empty userinfo}">
											<li><a class="dropdown-item" href="#"
												data-bs-toggle="modal" data-bs-target="#signUpModal">회원가입</a></li>
											<li><a class="dropdown-item" id="login-dropdown"
												href="#" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a></li>
										</c:if>
										<c:if test="${not empty userinfo}">
											<li><a class="dropdown-item" id="logout=dropdown"
												href="${root}/user?action=logout">로그아웃</a></li>
										</c:if>
										<c:if test="${not empty userinfo}">
											<li><a class="dropdown-item"
												href="${root}/user/mypage.jsp">마이페이지</a></li>
										</c:if>
									</ul></li>
							</ul>
						</div>
					</div>
				</nav>
			</header>