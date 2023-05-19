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
        <div class="col-4" style="background-color: antiquewhite;">
          col1
        </div>
        <div class="col-4" style="background-color: antiquewhite;">
          col2
        </div>
        <div class="col-4" style="background-color: antiquewhite;">
          col3
        </div>
      </div>
    </div>
    <!-- 원하는 여행지 선택 목록 끝 -->

    <!-- 날짜별 내용 입력 시작 -->
    <div class="">
      <textarea class=" row form-control m-2 mb-4" id="content" name="content" rows="7" style="border: none" placeholder="내용을 입력하세요"
        v-model="content"></textarea>
    </div>
    <!-- 날짜별 내용 입력 끝 -->
  </div>
</template>

<script>
export default {

}
</script>

<style>
  .by-day-text{
    font-size:large;
  }
</style>