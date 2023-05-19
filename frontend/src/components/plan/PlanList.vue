<!-- 
    목록 뽑아내는 컴포넌트이다
    row1
        MY TRIP PLAN
    row2
        검색창
    row3
        col1 게시물1
        col2 게시물2
        col3 게시물3
-->

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
          <a @click="planWrite" style="cursor: pointer"> 등록하기 </a>
        </div>
        <!-- 글쓰기 앵커 끝 -->
      </div>
      
      <div>
        <plan-list-item ref="PlanListItem"></plan-list-item>
      </div>
      <br />
    </div>
  </div>
</template>

<script>
import PlanListItem from "./PlanListItem.vue";

export default {
  name: "PlanList",
  components: {
    PlanListItem,
  },
  data() {
    return {
      articles: []
    }
  }
}
</script>

<style>
.myplan-title {
}
</style>