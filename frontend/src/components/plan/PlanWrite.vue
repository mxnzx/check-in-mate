<template>
  <div class="container">
    <div class="row justify-content-center">
      <!-- 여행정보 공유 게시판 글쓰기 제목 끝 -->
      <div class="col-lg-11 col-md-11 col-sm-12">
        <input type="hidden" name="action" value="write" />
        <!-- 제목입력 시작 -->
        <div class="mb-3">
          <div class="plan-input-title-wrapper">
            <input type="text" class="form-control plan-input-title" id="subject" name="subject" style="border: none"
              placeholder="[지역명] 제목을 입력하세요" v-model="subject" />
          </div>
        </div>
        <!-- 제목입력 끝 -->

        <hr class="my-3" />

        <!-- 내용입력 시작 -->
        <div>
          <div>
            <div class="row mb-3">
              <plan-map class="col-9"></plan-map>
              <plan-pick-list class="col-3 p-0"></plan-pick-list>

            </div>
          </div>
          <!-- <div class="col-12 d-flex justify-content-center by-day-text mb-2">
            DAY 1
          </div> -->
          <plan-write-by-date id="plan-by-day-01" :dayIdx="1"></plan-write-by-date>
        </div>
        <div v-for="(date, index) in dates" :key="index" >
          <div class="col-12 d-flex justify-content-center by-day-text mb-2">
            <!-- DAY {{ index + 2 }} -->
          </div>
          <plan-write-by-date :id="'plan-by-day-' + index + 2" :dayIdx="index+2"></plan-write-by-date>
        </div>
        <!-- 내용입력 끝 -->

        <!-- 날짜 추가 버튼 -->
        <div class="d-flex justify-content-center mt-5">
          <b-button size="sm" pill @click="addDate">날짜 추가하기</b-button>
        </div>


        <!-- 글작성, 목록 버튼 시작 -->
        <div class="col-auto text-right">
          <b-button type="button" id="btn-register" class="btn mb-3" @click="registArticle" variant="light">
            글작성
          </b-button>
          <b-button type="reset" class="btn mb-3" style="margin-left: 5px" @click="moveList" variant="light">
            목록
          </b-button>
        </div>
        <!-- 글작성, 목록 버튼 끝 -->
      </div>
    </div>
  </div>
</template>

<script>
import PlanWriteByDate from "./PlanWriteByDate.vue";
import PlanMap from "./PlanMap.vue";
import PlanPickList from "./PlanPickList.vue";
import { mapState } from "vuex";


export default {
  name: "PlanWrite",
  components: {
    PlanWriteByDate,
    PlanMap,
    PlanPickList,
  },
  data() {
    return {
      dates: [],  //추가된 날짜 컴포넌트들을 저장하는 배열
      dataByDay:[],
    }
  },
  methods: {
    addDate() {
      this.dates.push({});  //새 날짜 컴포넌트를 배열에 추가
    },
    
  },
  computed: {
    ...mapState(['dataByDay']), //날짜별로 입력완료가 되었을 때 store에 저장하기 위함.
  }
}
</script>

<style>
.plan-input-title::placeholder {
  font-size: x-large;
}

.plan-input-title-wrapper input {
  font-size: x-large;
}
</style>