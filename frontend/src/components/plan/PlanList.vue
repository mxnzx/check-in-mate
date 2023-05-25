<template>
  <div class="row justify-content-center">
    <!-- 여행정보 공유 게시판 제목 시작 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-2 text-center">
        <span class="">MY TRIP PLAN</span>
      </h2>
    </div>
    <!-- 여행정보 공유 게시판 제목 끝 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <!-- 글쓰기 앵커 시작 -->
        <div class="col-md-12" style="text-align: end">
          <router-link
            to="/myplan/write"
            style="cursor: pointer"
            v-if="userInfo" class="board-subject"
            >등록하기</router-link
          >
        </div>
        <!-- 글쓰기 앵커 끝 -->
      </div>

      <div>
        <b-card-group deck style="justify-content: center">
          <plan-list-item
            v-for="article in articles"
            :key="article.articleNo"
            :article="article"
          >
          </plan-list-item>
        </b-card-group>
      </div>
      <br />
    </div>
  </div>
</template>

<script>
import PlanListItem from "./PlanListItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "PlanList",
  components: {
    PlanListItem,
  },
  data() {
    return {
      articles: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    fetch("http://127.0.0.1:9018/plan/list")
      .then((response) => response.json())
      .then((data) => {
        this.articles = data;
      });
  },
};
</script>

<style>
.myplan-title {
}
</style>
