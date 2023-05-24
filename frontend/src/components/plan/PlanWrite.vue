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
              placeholder="[지역명] 제목을 입력하세요" v-model="plan.mainTitle" />
          </div>
        </div>
        <!-- 제목입력 끝 -->

        <hr class="my-3" />

        <!-- 내용입력 시작 -->
        <div>
          <div>
            <div class="row">
              <textarea class="form-control mn-2 mb-4" id="plan-write-main-content" name="plan-write-main-content"
                rows="5" style="border: none" placeholder="여행 컨셉이나 테마 등 자유롭게 소개해주세요. "
                v-model="plan.mainContent"></textarea>
            </div>
            <div class="row mb-3">
              <div class="col-2 pr-0">여행 대표 사진</div>
              <div class="col-10">
                <input type="file" class="form-control border" id="plan-upfile" name="plan-upfile" multiple="multiple"
                  ref="planFileInput" />
              </div>
            </div>
            <div class="row mb-3">
              <plan-map class="col-9"></plan-map>
              <plan-pick-list class="col-3 p-0"></plan-pick-list>
            </div>
          </div>
          <!-- <div class="col-12 d-flex justify-content-center by-day-text mb-2">
            DAY 1
          </div> -->
          <plan-write-by-date
            id="plan-by-day-01"
            :dayIdx="1"
          ></plan-write-by-date>
        </div>
        <div v-for="(date, index) in dates" :key="index">
          <div class="col-12 d-flex justify-content-center by-day-text mb-2">
            <!-- DAY {{ index + 2 }} -->
          </div>
          <plan-write-by-date
            :id="'plan-by-day-' + index + 2"
            :dayIdx="index + 2"
          ></plan-write-by-date>
        </div>
        <!-- 내용입력 끝 -->

        <!-- 날짜 추가 버튼 -->
        <div class="d-flex justify-content-center mt-5">
          <b-button size="sm" pill @click="addDate">날짜 추가하기</b-button>
        </div>

        <!-- 글작성, 목록 버튼 시작 -->
        <div class="col-auto text-right">
          <b-button
            type="button"
            id="btn-register"
            class="btn mb-3"
            @click="registArticle"
            variant="light"
          >
            글작성
          </b-button>
          <b-button
            type="reset"
            class="btn mb-3"
            style="margin-left: 5px"
            @click="moveList"
            variant="light"
          >
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
//import axios from "axios";
import { sendImg, addPlan } from "@/api/plan";

const memberStore = "memberStore";

export default {
  name: "PlanWrite",
  components: {
    PlanWriteByDate,
    PlanMap,
    PlanPickList,
  },
  data() {
    return {
      dates: [], //추가된 날짜 컴포넌트들을 저장하는 배열
      dataByDay: [],
      plan: {
        userid: "",
        mainTitle: "",
        mainContent: "",
        dataByDay: [],
        img: "",
      }
    }
  },
  created() {
    this.plan.userid = this.userInfo.userid
  },
  methods: {
    addDate() {
      this.dates.push({}); //새 날짜 컴포넌트를 배열에 추가
    },

    excludeObserver(arr) {
      // 배열의 복사본을 생성하여 __ob__ 속성을 제외합니다.
      return arr.map(item => {
        const { __ob__, ...rest } = item;
        delete rest.__ob__;
        console.log(__ob__);
        return rest;
      });
    },

    registArticle() {
      //파일 업로드 코드
      const mainImage = this.$refs.planFileInput;     //파일 입력요소 가져오기
      console.log(this.$store.state.dataByDay);       //observer

      this.plan.dataByDay = this.excludeObserver(this.$store.state.dataByDay);


      console.log(this.plan.dataByDay);

      console.log();

      sendImg(mainImage,
        ({ data }) => {
          this.plan.img = data;
          console.log(this.plan);

          // 여기에 this.plan 세팅하기
          addPlan(this.plan,
            () => {
              //갔다오면 dataByDay.store reset 해야함

              this.$router.push("/myplan")
            },
            (error) => console.log(error));
        },
        console.log("errorr")
      );
    },
    completeWritePlan() {
      // 글 등록이 완료되면 데이터 초기화
      // planList로 라우팅
      this.$router.push("/myplan");
    }

  },
  computed: {
    ...mapState(['dataByDay']), //날짜별로 입력완료가 되었을 때 store에 저장하기 위함.
    ...mapState(memberStore, ["userInfo"]),
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
