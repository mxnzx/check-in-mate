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
					<form id="form-search" method="POST" class="d-flex" onsubmit="return false;" role="search">
					
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

<script>
let areaUrl = "/navigator/searchSido";
window.onload = init();

//창이 뜨면 시도 목록 가져온다
function init() {
    fetchAllAreas(areaUrl);
}
//시도 목록 가져와서 옵션값으로 추가한다
function fetchAllAreas(areaUrl) {
    fetch(areaUrl, { method: "GET" })
  .then((response) => response.json())
  .then((data) => makeOptionforSido(data));
}


//바뀐 시도 값을 가지고 구군 목록을 가져온다.
document.getElementById("search-sido").addEventListener("change", function() {
  const selectedSido = this.options[this.selectedIndex].value;	//sido-code
  fetchGugunList(selectedSido); // 함수 실행하기
});

function fetchGugunList(selectedSido) {
  console.log(selectedSido);
  const gugunUrl = "/navigator/searchGugun?sidoCode="+selectedSido;
  fetch(gugunUrl, { method: "GET" })
  .then((response) => response.json())
  .then((data) => makeOptionforGugun(data));
}



/* let areaUrl =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=" +
  serviceKey +
  "&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json"; */

// fetch(areaUrl, { method: "GET" }).then(function (response) { return response.json() }).then(function (data) { makeOption(data); });

function makeOptionforSido(data) {
  let areas = data;
  console.log(areas);
  let sel = document.getElementById("search-sido");
  areas.forEach((area) => {
    let opt = document.createElement("option");
    opt.setAttribute("value", area.sidoCode);
    opt.appendChild(document.createTextNode(area.sidoName));
    sel.appendChild(opt);  
  });
}

function makeOptionforGugun(data) {
	  let areas = data;
	  console.log(areas);
	  let sel = document.getElementById("search-gugun");
	  areas.forEach((gugun) => {
	    let opt = document.createElement("option");
	    opt.setAttribute("value", gugun.gugunCode);
	    opt.appendChild(document.createTextNode(gugun.gugunName));
	    sel.appendChild(opt);  
	  });
}
// 검색 버튼을 누르면..
// 지역, 유형, 검색어 얻기.
// 위 데이터를 가지고 공공데이터에 요청.
// 받은 데이터를 이용하여 화면 구성.
document.getElementById("search-btn").addEventListener("click", () => {
  let searchUrl = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A`;

  let areaCode = document.getElementById("search-area").value;
  let contentTypeId = document.getElementById("search-content-id").value;
  let keyword = document.getElementById("search-keyword").value;

  if (parseInt(areaCode)) searchUrl += `&areaCode=${areaCode}`;
  if (parseInt(contentTypeId)) searchUrl += `&contentTypeId=${contentTypeId}`;
  if (!keyword) {
    alert("검색어 입력 필수!!!");
    return;
  } else searchUrl += `&keyword=${keyword}`;

  fetch(searchUrl)
    .then((response) => response.json())
    .then((data) => makeList(data));
});

var positions; // marker 배열.
function makeList(data) {
  document.querySelector("table").setAttribute("style", "display: ;");
  let trips = data.response.body.items.item;
  let tripList = ``;
  //배열에 지역명 저장
  positions = [];
  trips.forEach((area) => {
    tripList += `
    <tr onclick="moveCenter(${area.mapy}, ${area.mapx});">
      <td><img src="${area.firstimage}" width="100px" height="60px"></td>
      <td>${area.title}</td>
      <td>${area.addr1} ${area.addr2}</td>
    </tr>
  `;

    let markerInfo = {
      content: `<div class="container-fluid bg-light">
                  <p>${area.title}</p>
                </div>
                <div class="container-fluid row">
                  <div class="col-3 attraction-first-card" style="background-image: url(${area.firstimage}); height: 3rem">
                  </div>
                  <div class="col">
                    <p>${area.addr1}</p>
                  </div>
                </div>
                `,
      latlng: new kakao.maps.LatLng(area.mapy, area.mapx),
    };
    positions.push(markerInfo);
    console.log(positions);
  });
  document.getElementById("trip-list").innerHTML = tripList;
  displayMarker();
}

// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 5, // 지도의 확대 레벨
  };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

function displayMarker() {
  // 마커 이미지의 이미지 주소입니다
  var imageSrc =
    "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

  for (var i = 0; i < positions.length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35);

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: positions[i].latlng, // 마커를 표시할 위치
      title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });

    // 마커에 표시할 인포윈도우를 생성합니다
    var infoWindow = new kakao.maps.InfoWindow({
      content: positions[i].content,
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    kakao.maps.event.addListener(
      marker,
      "mouseover",
      makeOverListener(map, marker, infoWindow)
    );
    kakao.maps.event.addListener(
      marker,
      "mouseout",
      makeOutListener(infoWindow)
    );
  }

  // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
  function makeOverListener(map, marker, infoWindow) {
    return function () {
      infoWindow.open(map, marker);
    };
  }

  // 인포윈도우를 닫는 클로저를 만드는 함수입니다
  function makeOutListener(infoWindow) {
    return function () {
      infoWindow.close();
    };
  }

  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(positions[0].latlng);
}

function moveCenter(lat, lng) {
  map.setCenter(new kakao.maps.LatLng(lat, lng));
}



</script>

<!-- 하단 Footer Start  -->
<%@ include file="../common/footer.jsp"%>
<!-- 하단 Footer End  -->
<!-- 로그인 모달창 -->
<%@ include file="../common/login-modal.jsp"%>
<!--회원가입 모달-->
<%@ include file="../common/join-modal.jsp"%>


</body>

</html>
