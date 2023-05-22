<template>
  <div class="map_wrap">
    <div id="map" style="height:100%;position:relative;overflow:hidden;"></div>
    <div id="menu_wrap" class="bg_white">
      <div class="option">
        <div>
          <form @submit.prevent="searchPlaces">
            키워드 : <input type="text" v-model="keyword" id="keyword" size="15">
            <button type="submit">검색하기</button>
          </form>
        </div>
      </div>
      <hr>
      <ul id="placesList">
      </ul>
      <div id="pagination"></div>
    </div>
  </div>
</template>

<script>
import { eventBus } from '@/main.js';

export default {
  name: "KakaoMap",
  data() {
    return {
      //map 객체를 설정한다
      map: null,
      markers: [],
      ps: null,
      keyword: "이태원 맛집",
      infowindow: null,
      searchedData: null,

    };
  },
  mounted() {
    //카카오 지도 api 불러오기
    if (!window.kakao || !window.kakao.maps) {
      const kakaoMap = document.createElement("script");
      kakaoMap.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=846de1180d8d9b6e255d19db1db819b3&libraries=services,drawing";
      kakaoMap.addEventListener("load", () => {
        window.kakao.maps.load(this.initMap);
      });
      document.head.appendChild(kakaoMap);
    } else {
      this.initMap();
    }


  },

  methods: {

    //맵 출력하기(처음 초기화된 지도)
    initMap() {
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
          center: new window.kakao.maps.LatLng(37.564343, 126.947613), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      //지도를 생성한다
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);
      ////////////////////////여기까지는 됐었다 ////////////////////////

      // 장소 검색 객체를 생성합니다
      this.ps = new window.kakao.maps.services.Places();


      // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
      this.infowindow = new window.kakao.maps.InfoWindow({ zIndex: 1 });
      // 키워드로 장소를 검색합니다
      this.searchPlaces();
    },

    // 키워드 검색을 요청하는 함수입니다
    searchPlaces() {

      var keyword = document.getElementById('keyword').value;
      this.keyword = keyword;

      if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
      }

      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      this.ps.keywordSearch(keyword, this.placesSearchCB);
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === window.kakao.maps.services.Status.OK) {
        console.log("정상적으로 검색이 완료됨>>>>>>" + this.keyword);
        //console.log(data);
        this.searchedData = data;   //==========> 이 데이터를 마커안에 집어넣어야함
        //console.log(this.searchedData);


        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        this.displayPlaces(data);

        // 페이지 번호를 표출합니다
        this.displayPagination(pagination);

      } else if (status === this.kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

      } else if (status === this.kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

      }
    },

    // 검색 결과 목록과 마커를 표출하는 함수입니다     ===============> 여기 파라미터값이 데이터값이다. 우리는 인포윈도우에 이 값을 뿌리고 거기에 이 데이터를 전부 담아야함(title담는것처럼)
    displayPlaces(places) {

      var listEl = document.getElementById('placesList'),
        menuEl = document.getElementById('menu_wrap'),
        fragment = document.createDocumentFragment(),
        bounds = new window.kakao.maps.LatLngBounds();
      //listStr = '';
      //console.log(menuEl + " " + listStr);

      // 검색 결과 목록에 추가된 항목들을 제거합니다
      this.removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      for (var i = 0; i < places.length; i++) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new window.kakao.maps.LatLng(places[i].y, places[i].x),
          marker = this.addMarker(placePosition, i),
          itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        //this: vue컴포넌트
        this.addEventListeners.call(this, marker, itemEl, places[i]);  //======> 이게 인포윈도우에 객체 뿌리는 거임. 각각 뿌린다. 마커를 가지ㄱ

        fragment.appendChild(itemEl);
      }

      // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.map.setBounds(bounds);

    },

    // 마커와 검색결과 항목에 이벤트 리스너를 추가하는 함수
    addEventListeners(marker, itemEl, place) {
      window.kakao.maps.event.addListener(marker, 'mouseover', () => {
        this.displayInfowindow(marker, place);

        //인포윈도우에 add버튼 누르면 처리할 이벤트 생성
        document.getElementById("infowindow-add-button").addEventListener("click", () => { this.addPickList(place) });

      });

      // window.kakao.maps.event.addListener(marker, 'mouseout', ()=>{
      //   this.infowindow.close();
      // });

      itemEl.onmouseover = () => {
        this.displayInfowindow(marker, place);
      };

      // itemEl.onmouseout = () => {
      //   this.infowindow.close();
      // };
    },

    // 검색결과 항목을 Element로 반환하는 함수입니다
    getListItem(index, places) {

      var el = document.createElement('li'),
        itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' +
          '<div class="info">' +
          '   <h5>' + places.place_name + '</h5>';

      if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
          '   <span class="jibun gray">' + places.address_name + '</span>';
      } else {
        itemStr += '    <span>' + places.address_name + '</span>';
      }

      itemStr += '  <span class="tel">' + places.phone + '</span>' +
        '</div>';

      el.innerHTML = itemStr;
      el.className = 'item';

      //console.log(el);

      return el;
    },
    /////////////////여기 title 삭제함
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, idx) {
      var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new window.kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new window.kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new window.kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new window.kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new window.kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker);  // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {

      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }


      this.markers = [];
    },
    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    displayPagination(pagination) {
      var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = 'on';
        } else {
          el.onclick = (function (i) {
            return function () {
              pagination.gotoPage(i);
            }
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },

    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명과 버튼을 표시합니다
    // 여기에 숨겨서 저장해놓자
    displayInfowindow(marker, place) {
      var placeData = JSON.stringify(place);  // place 객체를 JSON 형식의 문자열로 변환

      //이제 인포윈도우에 현재 선택한 장소의 모든 데이터가 담겨있다.
      //접근 방식은 
      //var divElement = document.getElementById('place-info');
      //var placeData = JSON.parse(divElement.dataset.place);
      //console.log(placeData);  // 숨겨진 place 객체에 접근하여 사용할 수 있음

      var content = '<div id="place-info" style="padding: 5px; z-index: 1;" data-place=\'' + placeData + '\'>' +
        '<div>' + place.place_name + '</div>' +
        '<button id="infowindow-add-button"> add </button>' +
        '</div>';

      this.infowindow.setContent(content);
      this.infowindow.open(this.map, marker);
      //현재 title을 가지고 갈 수 있다
    },

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    },

    //pickList에 픽한 상호명을 담는다
    addPickList(place) {
      //planpicklist 컴포넌트에 title을 전달한다
      // 'pick-item-update' 이벤트 발행
      console.log(place);
      eventBus.$emit('pick-item-update', place);
    },
  },

}
</script>
<style>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  font-size: 12px;
}

.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}

.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}

#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}

.bg_white {
  background: #fff;
}

#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5F5F5F;
  margin: 3px 0;
}

#menu_wrap .option {
  text-align: center;
}

#menu_wrap .option p {
  margin: 10px 0;
}

#menu_wrap .option button {
  margin-left: 5px;
}

#placesList li {
  list-style: none;
}

#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}

#placesList .item span {
  display: block;
  margin-top: 4px;
}

#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

#placesList .item .info {
  padding: 10px 0 10px 55px;
}

#placesList .info .gray {
  color: #8a8a8a;
}

#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;
}

#placesList .info .tel {
  color: #009900;
}

#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;
}

#placesList .item .marker_1 {
  background-position: 0 -10px;
}

#placesList .item .marker_2 {
  background-position: 0 -56px;
}

#placesList .item .marker_3 {
  background-position: 0 -102px
}

#placesList .item .marker_4 {
  background-position: 0 -148px;
}

#placesList .item .marker_5 {
  background-position: 0 -194px;
}

#placesList .item .marker_6 {
  background-position: 0 -240px;
}

#placesList .item .marker_7 {
  background-position: 0 -286px;
}

#placesList .item .marker_8 {
  background-position: 0 -332px;
}

#placesList .item .marker_9 {
  background-position: 0 -378px;
}

#placesList .item .marker_10 {
  background-position: 0 -423px;
}

#placesList .item .marker_11 {
  background-position: 0 -470px;
}

#placesList .item .marker_12 {
  background-position: 0 -516px;
}

#placesList .item .marker_13 {
  background-position: 0 -562px;
}

#placesList .item .marker_14 {
  background-position: 0 -608px;
}

#placesList .item .marker_15 {
  background-position: 0 -654px;
}

#pagination {
  margin: 10px auto;
  text-align: center;
}

#pagination a {
  display: inline-block;
  margin-right: 10px;
}

#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
</style>