<template>
  <div>
    <div class="mb-3">
      <!-- v-b-toggle, href, b-collapse의 id 값을 다 맞춘다 -->
      <a v-b-toggle="'plan-write-idx' + dayIdx" :href="'plan-write-idx' + dayIdx" @click.prevent>DAY {{ dayIdx }}</a>
    </div>

    <b-collapse :id="'plan-write-idx' + dayIdx">
      <b-card title="">
        <!-- 제목 시작 -->
        <div class="row justify-content-center">
          <input type="text" class="form-control" id="title" name="title" style="border: none"
            placeholder="날짜별 테마명을 입력하세요" v-model="title" />
        </div>
        <!-- 제목 끝 -->

        <hr class="my-3" />

        <div class="row mb-3">
          <!-- 날짜별 내용 입력 시작 -->
          <div class="col-6">
            <div class="row">
              <textarea class="form-control mn-2 mb-4" id="content" name="content" rows="7" style="border: none"
                placeholder="내용을 입력하세요" v-model="content"></textarea>
            </div>
          </div>
          <!-- 날짜별 내용 입력 끝 -->

          <!-- 리스트의 add 버튼을 누르면 여기로 데이터 가지고 온다  -->
          <div class="col-6 pickmyplace-box">
            <div class="p-1 pt-2">내가 선택한 여행지</div>
            <hr style="margin-top: 5px">
            <div v-for="(place, idx) in myPickPlace[0]" :key="idx">
              <div class="row">
                <div class="col-1">{{ idx + 1 }}</div>
                <div class="col-6 px-0">{{ place.place_name }}</div>
                <div class="col-5">
                  <input style="width: 100%;" :value="place.startTime" @input="updateStartTime(idx, $event.target.value)"
                    type="time" placeholder="시작시간을 입력하세요" :id="'startTime-' + idx">
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- 완료하기 버튼: 누르면 토글이 올라간다 -->
        <div class="d-flex justify-content-center">
          <b-button size="sm" class="m-1" pill @click="pushToPlanWrite">작성완료</b-button>
          <b-button size="sm" class="m-1" pill @click="toggleUp">수정</b-button>
        </div>
      </b-card>
    </b-collapse>
  </div>
</template>

<script>
import { eventBus } from "@/main";
import { mapMutations } from "vuex";

export default {
  props: ['dayIdx'],  //day1,2,3,4...
  components: {

  },
  data() {
    return {
      title: "",
      content: "",
      myPickPlace: [],

    }
  },
  mounted() {
    eventBus.$on('add-list-items-update-by-date', (pickList) => {
      this.myPickPlace.push(pickList);
    });
  },
  methods: {
    //vuex를 통해 상태관리하는 코드
    ...mapMutations(['updateDateByDay']),
    pushToPlanWrite() {
      //데이터를 객체로 만들어서 push할거다
      const data = {
        dayIdx: this.dayIdx,
        title: this.title,
        content: this.content,
        myPickPlace: this.myPickPlace[0]
      };
      //this.updateDataByDay(data); // Vuex store로 데이터 전달
      this.$store.commit('updateDataByDay', data);  //위에코드 안먹음
      console.log(data);
      console.log(this.$store.state);
      //토글닫자
      console.log("토글닫히는 로직 구현하자");
    },

    updateStartTime(index, value) {
      this.myPickPlace[0][index].startTime = value;
    },

    toggleUp() {
      console.log("토글이 닫힌다");
    }
  }

}
</script>

<style>
.by-day-text {
  font-size: large;
}

.pickmyplace-box {
  background-color: rgba(229, 232, 228, 0.262);
}
</style>
