<template>
  <div class="l-l-container">
    <div class="plan-day-title">
      <div class="plan-day-title-item plan-day-day">Day {{ day.dayIdx }}</div>
      <div class="plan-day-title-item">{{ day.title }}</div>
    </div>
    <div class="plan-day-map-area">
      <div class="day-map">
        지도 넣을자리
      </div>
    </div>
    <div class="day-content-all">
      <div class="place-list">
        <div v-for="(place, index) in myPickPlaces" :key="index" class="place-list-item">
          <div class="place-list-item-idx">{{ index+1 }}</div>
          <div>{{ place.place_name }}</div>
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
      
    }
  },
  data() {
    return {
      myPickPlaces:[],

    }
  },
  components: {
    
  },
  mounted() {
    console.log(this.day);
    this.myPickPlaces = this.day.myPickPlace;
    console.log(this.myPickPlaces);
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

.plan-day-map-area {

}

.plan-day-title-item{
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
  display:flex;
  flex-direction: column;
  align-items: center;
}

.day-map {
  background-color: antiquewhite;
  width: 600px;
  height: 170px;
}
</style>