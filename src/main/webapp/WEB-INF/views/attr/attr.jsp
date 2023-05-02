<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<!--중앙 content Start-->
	<div class="container">
		<div class="row">
			<div class="col-5" style="height: 600px;">
				<div class="row">
					<div class="alert alert-primary text-center fw-bold" role="alert">
						전국 관광지 정보</div>
					<!-- 관광지 검색 start -->			
					<form id="form-search" method="GET" class="d-flex" onsubmit="return false;" role="search">
					
					<select id="search-sido" class="form-select me-2">
						<option value="0" selected>지역 선택</option>
					</select> 
					
					<select id="search-gugun" class="form-select me-2">
						<option value="0" selected>구군 선택</option>
					</select>
					
					<select id="search-content-id" class="form-select me-2">
						<option value="0" selected>관광지 유형</option>
						<option value="12">관광지</option>
						<option value="14">문화시설</option>
						<option value="15">축제공연행사</option>
						<option value="25">여행코스</option>
						<option value="28">레포츠</option>
						<option value="32">숙박</option>
						<option value="38">쇼핑</option>
						<option value="39">음식점</option>
					</select>
					
					<!-- <input type="submit" value="button" class="btn btn-outline-success" id = "search-btn" /> -->
					<button id="search-btn" class="btn btn-outline-success" type="button">검색</button>
						
				</form>
				</div>
				<div class="row">
					<div class="row container" style="overflow: auto; height: 450px;">
						<table class="table table-striped align-middle"
							style="display: none">
							<thead>
								<tr>
									<th>대표이미지</th>
									<th>관광지명</th>
									<th>주소</th>
								</tr>
							</thead>
							<tbody id="trip-list"></tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-7">
				<div class="map container">
					<div id="map" style="height: 600px"></div>
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=846de1180d8d9b6e255d19db1db819b3&libraries=services,clusterer,drawing">					
					</script>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<!--중앙 content End-->

<script type="text/javascript" src="${root}/assets/js/map.js" />




<!-- 로그인 모달창 -->
<%@ include file="../user/login-modal.jsp"%>
<!--회원가입 모달-->
<%@ include file="../user/join-modal.jsp"%>
<!-- 하단 Footer -->
<%@ include file="../common/footer.jsp"%>

