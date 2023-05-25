<template>
  <div class="l-l-container">
    <div class="plan-day-title">
      <div class="plan-day-title-item plan-day-day">Day {{ day.dayIdx }}</div>
      <div class="plan-day-title-item" >{{ day.title }}</div>
    </div>
    <div class="plan-day-map-area">
      <div class="day-map">

        <!-- 지도를 표시할 div 입니다 -->
        <div :id="`map-${this.index}`" style="width:600px;height:170px;"></div>





      </div>
    </div>
    <div class="day-content-all">
      <div class="place-list">
        <div v-for="(place, index) in myPickPlaces" :key="index" class="place-list-item">
          <div class="place-list-item-idx">{{ index + 1 }}</div>
          <div><a :href="place.place_url" class="board-subject">{{ place.place_name }}</a></div>
        </div>
      </div>
      <div class="day-content-introduce">
        <h5>여행 소개</h5>
        <div>{{ day.content }}</div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  props: {
    day: {
      type: Object,

    },
    index: {
      type: String,
    }
  },
  data() {
    return {
      myPickPlaces: [],
      positions: [],
      map: null,
      markers:[],

    }
  },
  components: {

  },
  mounted() {
    console.log(this.day);
    this.myPickPlaces = this.day.myPickPlace;
    console.log(this.myPickPlaces);
    console.log(this.index);


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
  },
  methods: {
    //처음 초기화되어있는 지도를 표시한다
    initMap() {
      const mapContainer = document.getElementById("map-" + this.index), // 지도를 표시할 div
        mapOption = {
          center: new window.kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);

      //여기다가 내가 가진 attr만큼 for문 돌리면서 마커 생성한다
      console.log(this.myPickPlaces.length);  //현재 일수에 선택한 마커 수
      for (var i = 0; i < this.myPickPlaces.length; i++) {
        this.positions.push({
          title: this.myPickPlaces[i].place_name,
          latlng: new window.kakao.maps.LatLng(this.myPickPlaces[i].y, this.myPickPlaces[i].x)
        });
        console.log(this.positions[i]);
        
      }
    },
    
  }


}
</script>

<style>
.l-l-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  flex-direction: column;
}

.plan-day-title {
  display: flex;

}

.plan-day-map-area {}

.plan-day-title-item {
  margin-right: 2vmin;
  display: flex;
  align-items: flex-end;
}

.plan-day-day {
  font-size: 3vmin;

}

.day-content-all {
  display: flex;
  justify-content: space-around;
}

.place-list {
  display: flex;
  flex-direction: column;
}

.place-list-item {
  display: flex;
}

.place-list-item-idx {
  margin-right: 2vmin;
}

.day-content-introduce {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.day-map {
  width: 600px;
  height: 170px;
}
</style>