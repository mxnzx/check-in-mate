<!-- 
    날짜 하나 추가될때마다 이 컴포넌트를 가져다 붙인다
    row1
        col1 : 원하는 지역명 검색창 + 리스트
        col2 : 지도
        col3 : 추가해놓은 여행지 목록
    row2
        각 날짜별마다 content
-->
<template>
  <div>
    <!-- 원하는 여행지 선택 목록 시작 -->
    <div class="container">
      <div class="row">
        <plan-pick-list></plan-pick-list>
      </div>
    </div>
    <!-- 원하는 여행지 선택 목록 끝 -->

    <!-- 날짜별 내용 입력 시작 -->
    <div class="row justify-content-center">
        <input type="text" class="form-control" id="subject" name="subject" style="border: none"
          placeholder="날짜별 테마명을 입력하세요" v-model="subject" />
    </div>
    <div class="row">
      <textarea class="form-control mn-2 mb-4" id="content" name="content" rows="7" style="border: none"
        placeholder="내용을 입력하세요" v-model="content"></textarea>
    </div>
    <!-- 날짜별 내용 입력 끝 -->
  </div>
</template>

<script>
import PlanPickList from "./PlanPickList.vue";

export default {
  components: {
    PlanPickList,
    
  },
  data() {
    return {
      "subject": "",
      "content": "",  

    }
  }

}
</script>

<style>
.by-day-text {
  font-size: large;
}
</style>