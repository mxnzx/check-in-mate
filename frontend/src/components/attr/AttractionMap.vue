```
<template>
  <div class="container">
    <div class="row">
      <div class="col-5" style="height: 600px">
        <div class="row">
          <div class="alert alert-primary text-center fw-bold" role="alert">
            전국 관광지 정보
          </div>
          <!-- 관광지 검색 start -->
          <form
            id="form-search"
            method="GET"
            class="d-flex"
            onsubmit="return false;"
            role="search"
          >
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
            <button
              id="search-btn"
              class="btn btn-outline-success"
              type="button"
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
export default {
  data() {
    return {
      map: null,
      areaUrl: "/navigator/searchSido",
    };
  },
  methods: {
    //창이 뜨면 시도 목록 가져온다
    init() {
      this.fetchAllAreas(this.areaUrl);
    },
    fetchAllAreas(areaUrl) {
      fetch(areaUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => this.makeOptionforSido(data));
    },
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
    //시도 불러오기
    this.init();

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
  },
};
</script>

<style></style>
```