<template>
  <div class="l-container">
    <div class="plan-main-title">
      <h2 class="my-3 py-2 text-center">{{ plan.mainTitle }}</h2>
    </div>
    <div class="plan-etc">
      <div class="plan-etc-item"> 조회수 {{ plan.hit }}</div>
      <div class="plan-etc-item">스크랩 {{  }}</div>
      <div class="plan-etc-item">{{ plan.userid }}</div>
      <div class="plan-etc-item">{{ plan.registerTime }}</div>
    </div>
    <div class="plan-image set-center-content">
      <div class="plan-image-item">
        <img :src="'http://localhost:9018/plan/image/' + plan.img" alt="대표사진" class="plan-image-item">
      </div>
    </div>
    <div class="plan-main-content set-center-content">
      <div>{{ plan.mainContent }}</div>
    </div>
    <!-- 데이별 컴포넌트 들고오기 -->
    <div v-for="(day, index) in this.planByDay" :key="index" class="set-center-content">
      <plan-view-by-day :day="day"></plan-view-by-day>
    </div>
    <hr>
    <div class="set-center-content">
      <button>돌아가기</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import PlanViewByDay from "./PlanViewByDay.vue";

export default {
  components: {
    PlanViewByDay,
  },
  data() {
    return {
      planArticleNo: "3",
      plan: [],
      planByDay: [],
      

    }
  },
  created() {
    this.getPlanArticle();

  },
  mounted() {

  },

  methods: {
    getPlanArticle() {  
      axios
        .get(`http://127.0.0.1:9018/plan/view/3`)
        .then((response) => {
          this.plan = response.data.planArticle;
          console.log(this.plan);
          this.planByDay = this.plan.dataByDay;
          console.log(this.planByDay);
        })
        .catch((error) => {
          console.error(error);
        });
    },

  },


}
</script>

<style scoped>
.l-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  flex-direction: column;

}

.plan-title {}

.plan-etc {
  display: flex;
  justify-content: center;
}

.plan-image {
  height: 300px;
  align-items: center;
}

.plan-image-item {
  background-color: antiquewhite;
  width: 600px;
  height: 100%;
}

.set-center-content {
  display: flex;
  justify-content: center;
}

.plan-etc-item {
  margin-right: 2vmin;
}

.plan-main-content {
  margin: 2vmin;
}
button {
  width: 100px;
  margin: 3vmin;
}

</style>