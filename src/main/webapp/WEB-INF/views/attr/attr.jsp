<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>



<!-- 공공데이터 포털 접근 Encoding key: 선택한 도시 정보에 대한 구군 정보를 가져오기 위한 목적으로 활용 -->
<c:set var="serviceKey"
	value="32LhEcAt92tVnlXIsGrJ6fSn2BqC1XtzUqxU432qhzk3Lu8%2BU5TEwxJTzLsAW7xmu3JCvM3CfPxBYO7nu82Pqg%3D%3D" />

<!--중앙 content Start-->
<div class="container">
	<div class="row">
		<div class="col-5" style="height: 600px;">
			<div class="row">
				<div class="alert alert-primary text-center fw-bold" role="alert">
					전국 관광지 정보</div>
				<!-- 관광지 검색 start -->
				<form id="form-search" method="POST" class="d-flex"
					onsubmit="return false;" role="search">
					<select id="search-city" class="form-select me-2">
						<option value="0" selected>검색 할 지역 선택</option>
						<c:forEach var="city" items="${cities}">
							<option value="${city.sidoCode}">${city.sidoName}</option>
						</c:forEach>
					</select> <select id="search-gugun" class="form-select me-2">
						<option value="0" selected>구군 항목</option>
					</select> <select id="search-content-id" class="form-select me-2">
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
					<button id="btn-search" class="btn btn-outline-success"
						type="button">검색</button>
				</form>
			</div>
			<!-- 관광지 검색 Select Form End -->
			<div class="row">
				<div class="row container" style="overflow: auto; height: 450px;">
					<table class="table table-striped align-middle">
						<thead>
							<tr>
								<th>대표이미지</th>
								<th>관광지명</th>
								<th>주소</th>
								<!-- 위도 경도 삭제 -->
							</tr>
						</thead>
						<tbody id="trip-list"></tbody>
						<!-- DB에서 가져온 관광지 정보는 attrinfo에 담겨져 있으므로 이를 foreach로 꺼내와 출력 -->
						<c:forEach var="attr" items="${attrinfo}">
							<tr onclick="moveCenter(${attr.latitude}, ${attr.longitude});">
								<td><img src="${attr.imgUrl}" width="100px"></td>
								<td>${attr.title}</td>
								<td>${attr.address}</td>
								<!-- 위도 경도 삭제 -->
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<!-- kakaoMap API  start  -->
		<div class="col-7">
			<div class="map container">
				<div id="map" style="height: 600px"></div>
				<!-- 	KakaoAPI 활용을 위한 appKey Setting.
		        			★ root 서버 주소 세팅 필요. (127.0.0.1:8088, 8081, 8080 설정? 한 상태로 마지막 확인
		        	 -->
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8820d3277f771b41fa361424002a1649">
							</script>

				<!-- 카카오맵 API 초기 화면 보여주기  -->
				<script>
					        	// 카카오지도
					            var mapContainer = document.getElementById("map"), // 지도를 표시할 div
					              mapOption = {
					                center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
					                level: 5, // 지도의 확대 레벨
					              };
					
					            // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
					            var map = new kakao.maps.Map(mapContainer, mapOption);
		        			</script>
				<!-- 시, 구군 정보 토글에 불러오기 -->
				<script>
									//시 정보 선택 시 발생하는 구군 항목 업데이트
								document.querySelector("#search-city").addEventListener("change", function () {
								  	   let seleted_code = this[this.selectedIndex].value;
								  	   if (seleted_code) {
								  	       getRegionDetail(seleted_code);
								  	   }
								  });
							
								// "도시" 정보를 선택하면 이벤트로 발생하는 "지역구" 정보 가져오기
								function getRegionDetail(data) {
									let Region_Code_T10_2 = "https://apis.data.go.kr/B551011/KorService1/areaCode1?"
							          	+ "serviceKey=${serviceKey}"
							          	+ "&numOfRows=100"
							          	+ "&MobileOS=ETC"
							          	+ "&MobileApp=AppTest"
							          	+ "&_type=json"
							          	+ "&areaCode=" + data;
							
							      	fetch(Region_Code_T10_2).then((response) => response.json())
							          .then((text) => {
							              let RDetails = text.response.body.items.item;
							              let TagSet = "";
							              let Select_div = document.querySelector("#search-gugun");
							
							              if (RDetails) {
							                  RDetails.forEach(function (city) {
							                      let Rcode = city.code;
							                      let Rname = city.name;
							
							                      TagSet += "<option value=" + Rcode + ">" + Rname + "</option>";
							                  });
							              }
							              Select_div.innerHTML = TagSet;
							          });
							  	}
							  	  
							  
							    // 관광지 정보 가져오기 이벤트 ("검색" 버튼 이벤트) --> DB select 조건에 필요한 매개변수 담아서 던짐.
							    document.getElementById("btn-search").addEventListener("click", () => {
							    	//window.alert("검색 버튼 클릭됨");
							    	//document.querySelector("table").setAttribute("style", "display: ;");
							    	let sidoCode = document.querySelector("#search-city").value;
							    	let gugunCode = document.querySelector("#search-gugun").value;
							    	let typeCode = document.querySelector("#search-content-id").value;
							    	
							    	//console.log(sidoCode, gugunCode, typeCode);
							    	//console.log("${root}/navigator?action=showmap&sido="+sidoCode+"&gugun="+gugunCode+"&type="+typeCode)
							    	
							      	let form = document.querySelector("#form-search");
							        form.setAttribute("action", "${root}/navigator?action=showmap&sido="+sidoCode+"&gugun="+gugunCode+"&type="+typeCode);
							        form.submit();
							    });
							    	
							    function moveCenter(lat, lng) {
							      map.setCenter(new kakao.maps.LatLng(lat, lng));
							    }
							</script>


				<!-- 검색 이벤트가 발생해서 DB에서 관광지 정보를 가져왔다면 session의 attrinfo에 값이 담겨진다. 그 때 출력. -->
				<c:if test="${not empty attrinfo}">
					<script>
	        		// 마커 이미지 경로
	        	    var Simg = "./assets/img/maker/Simg.png";  // 쇼핑
	        		var Cimg = "./assets/img/maker/Cimg.png";  // 문화
	        		var Eimg = "./assets/img/maker/Eimg.png";  // 행사
	        		var Fimg = "./assets/img/maker/Fimg.png";  // 음식 
	        		var Pimg = "./assets/img/maker/Pimg.png";  // 숙박
	        		var Rimg = "./assets/img/maker/Rimg.png";  // 레포츠
	        		var Timg = "./assets/img/maker/Timg.png";  // 여행
				        			var pos = new kakao.maps.LatLng(${attrinfo[0].latitude}, ${attrinfo[0].longitude});
				        			map.setCenter(pos);
		        				</script>

					<c:forEach var="position" items="${attrinfo}">
						<script>
				        				var imageSize = new kakao.maps.Size(24,35);
				        				var imageSrc = '';
				        				if (${position.contentId} == 12) {     //관광지(여행)
				        					imageSrc = Timg;
				        				}
				        				else if (${position.contentId} == 14){ //문화
				        					imageSrc = Cimg;
				        				}
				        				else if (${position.contentId} == 15){ //축제공연행사
				        					imageSrc = Eimg;
				        				}
				        				else if (${position.contentId} == 28){ //레포츠
				        					imageSrc = Rimg;
				        				}
				        				else if (${position.contentId} == 32){ //숙박
				        					imageSrc = Pimg;
				        				}
				        				else if (${position.contentId} == 38){ //쇼핑
				        					imageSrc = Simg;
				        				}
				        				else if (${position.contentId} == 39){ //음식점
				        					imageSrc = Fimg;
				        				}
				        				else {
				        					imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
				        				}
				        				
				        				var makerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
				        				
				        				//마커 생성
				        				var marker = new kakao.maps.Marker({
									        map: map, // 마커를 표시할 지도
									        position: new kakao.maps.LatLng(${position.latitude}, ${position.longitude}), // 마커를 표시할 위치
									        title : "${position.title}", // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
									        image : makerImage // 마커 이미지 
									    });
		        			</script>
					</c:forEach>
				</c:if>
			</div>
			<!-- kakaoMap API  end -->

		</div>
	</div>
</div>
</div>
<!--중앙 content End-->
<!-- 하단 Footer -->
<%@ include file="../common/footer.jsp"%>



<!-- 하단 Footer End  -->
<!-- 로그인 모달창 -->
<%@ include file="../common/login-modal.jsp"%>
<!--회원가입 모달-->
<%@ include file="../common/join-modal.jsp"%>

