<template>
  <div class="container">
    <div class="row">
      <div class="col-5" style="height: 600px">
        <div class="row">
          <div class="alert alert-primary text-center fw-bold" role="alert">
            전국 관광지 정보
          </div>
          <form
            id="form-search"
            method="GET"
            class="d-flex"
            onsubmit="return false;"
            role="search"
          >
            <select id="search-sido" class="form-select me-2" @change="readytoGugunList">
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

            
            <button
              id="search-btn"
              class="btn btn-outline-success"
              type="button"
              @click="getAttractionList"
            >
              검색
            </button>
          </form>
        </div>
        <div class="row">
          <div class="row container" style="overflow: auto; height: 450px">
            <table
              class="table table-striped align-middle"
              style="display: none"
            >
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
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//import axios from "axios";

export default {
  data() {
    return {
      map: null,
      areaUrl: "http://127.0.0.1:9018/navigator/searchSido",
      positions: null,
      marker: null,
    };
  },
  methods: {
    //창이 뜨면 시도 목록 가져온다
    init() {
      this.fetchAllAreas(this.areaUrl);
    },

    //시도 목록 가져와서 옵션값으로 추가한다
    fetchAllAreas(areaUrl) {
      fetch(areaUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => this.makeOptionforSido(data));
    },

    //시도 옵션태그를 추가한다
    makeOptionforSido(data) {
      let areas = data;
      console.log(areas);
      let sel = document.getElementById("search-sido");
      areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.sidoCode);
        opt.appendChild(document.createTextNode(area.sidoName));
        sel.appendChild(opt);
      });
    },

    //바뀐 시도 값을 가지고 구군 목록을 가져온다.
    readytoGugunList() {
      const gugunSelect = document.getElementById("search-gugun");
      gugunSelect.innerHTML = ""; // 옵션태그 모두 삭제
      const selectedSido = document.getElementById("search-sido").value;    //선택한 sido-code 가져오기
        // 새로운 option 추가
      const defaultOpt = document.createElement("option");
      defaultOpt.value = "0";
      defaultOpt.text = "구군 선택";
      gugunSelect.add(defaultOpt);
      this.fetchGugunList(selectedSido); // 함수 실행하기
    },

    //구군 목록 가져와서 옵션값으로 추가한다
    fetchGugunList(selectedSido) {
      console.log(selectedSido);
      const gugunUrl = "http://127.0.0.1:9018/navigator/searchGugun?sidoCode=" + selectedSido;
      console.log("gugunUrl");
      fetch(gugunUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => this.makeOptionforGugun(data));
    },

    //구군 옵션태그를 추가한다
    makeOptionforGugun(data) {
      let areas = data;
      console.log(areas);
      let sel = document.getElementById("search-gugun");
      areas.forEach((gugun) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", gugun.gugunCode);
        opt.appendChild(document.createTextNode(gugun.gugunName));
        sel.appendChild(opt);
      });
    },

    //세 항목을 가지고 여행지 리스트를 가져온다 
  getAttractionList() {
  let searchUrl = "http://127.0.0.1:9018/navigator/attrList";
  let sidoCode = document.getElementById("search-sido").value;
  let gugunCode = document.getElementById("search-gugun").value;
  let contentTypeId = document.getElementById("search-content-id").value;
  
  //패치할 url 생성
  if (parseInt(sidoCode)) searchUrl += "?sidoCode=" + sidoCode;
  if (parseInt(gugunCode)) searchUrl += "&gugunCode=" + gugunCode;
  if (parseInt(contentTypeId)) searchUrl += "&contentTypeId=" + contentTypeId;
  
  fetch(searchUrl)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      this.makeAttrList(data)
      });
    
  },

  //여행지리스트를 출력한다
  makeAttrList(data) {
    document.querySelector("table").setAttribute("style", "display: ;");
    let trips = data;
    let tripList = ``;
    this.positions = [];    //지역명 저장할 배열

    //리스트를 나열할 표에 출력할 여행지리스트를 붙인다
    trips.forEach((area) => {
      console.log(area);  //찍힘
      //area의 위도, 경도를 td의 data로 넣어준다(동적 템플릿에서 이벤트처리가 불가하기 때문임)
      tripList += `
      <tr>
        <td data-latitude="${area.latitude}" data-longitude="${area.longitude}"><img src=${area.imgUrl1} width="100px" height="60px"></td>
        <td data-latitude="${area.latitude}" data-longitude="${area.longitude}">${area.title}</td>
        <td data-latitude="${area.latitude}" data-longitude="${area.longitude}">${area.address1} ${area.address2}</td>
      </tr>
    `;

    //마커 생성 
    let markerInfo = {
      content: `<div class="container-fluid bg-light">
                  <p>${area.title}</p>
                </div>
                <div class="container-fluid row">
                  <div class="col-3 attraction-first-card" style="background-image: url(${area.imgUrl1}); height: 3rem">
                  </div>
                  <div class="col">
                    <p>${area.address1}</p>
                  </div>
                </div>
                `,
      latlng: new window.kakao.maps.LatLng(area.latitude, area.longitude),
    };
    this.positions.push(markerInfo);
    console.log(this.positions);
  });
  document.getElementById("trip-list").innerHTML = tripList;
  this.displayMarker();
  },

  //마커를 지도에 표시하는 카카오지도 api 코드(그대로 가져왔다)
  displayMarker() {
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    for (var i = 0; i < this.positions.length; i++) {
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new window.kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var marker = new window.kakao.maps.Marker({
        map: this.map, // 마커를 표시할 지도
        position: this.positions[i].latlng, // 마커를 표시할 위치
        title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });

      // 마커에 표시할 인포윈도우를 생성합니다
      var infoWindow = new window.kakao.maps.InfoWindow({
        content: this.positions[i].content,
      });

      // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
      // 이벤트 리스너로는 클로저를 만들어 등록합니다
      // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
      window.kakao.maps.event.addListener(
        marker,
        "mouseover",
        makeOverListener(this.map, marker, infoWindow)
      );
      window.kakao.maps.event.addListener(
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
    this.map.setCenter(this.positions[0].latlng);
    },

    handleClick(event) {
      // 클릭한 요소의 데이터 가져오기
      console.log(event);
      const latitude = event.target.dataset.latitude;
      const longitude = event.target.dataset.longitude;
      console.log(latitude + " " + longitude);
      // 처리할 로직 수행
      this.moveCenter(latitude, longitude);
    },
    
    //리스트 항목을 클릭하면 위도와 경도에 맞춰 가운데로 위치를 불러온다
    moveCenter(lat, lng) {
      this.map.setCenter(new window.kakao.maps.LatLng(lat, lng));
  },

  //처음 초기화되어있는 지도를 표시한다
  initMap() {
      const mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new window.kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);
    },
},


  mounted() {
    //화면이 뜨면 시도 불러오기
    this.init();

    //카카오 지도 api 불러오기
    if (!window.kakao || !window.kakao.maps) {
      const kakaoMap = document.createElement("script");
      kakaoMap.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=846de1180d8d9b6e255d19db1db819b3&libraries=services,clusterer,drawing";
      kakaoMap.addEventListener("load", () => {
        window.kakao.maps.load(this.initMap);
      });
      document.head.appendChild(kakaoMap);
    } else {
      this.initMap();
    }

    // 이벤트 위임 설정(여행지 리스트가 뜬 후에 리스트 항목을 눌렀을 때 moveCenter를 실행)
    document.getElementById("trip-list").addEventListener("click", this.handleClick);
  },
};
</script>

<style></style>
