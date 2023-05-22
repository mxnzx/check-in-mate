<template>
  <div>
    <div class="container p-0" style=" width:100%; height: 100%; background-color: antiquewhite; display: flex; flex-direction: column;">
      <div class="plan-pick-list-text">추가한 여행지 목록</div>
      <!-- 추가하는 리스트 구역 -->
      <div v-for="(item, index) in pickList" :key="index" class="row pick-list-item" draggable="true" @dragstart="dragStart($event, index)">
        <div class="col-1 p-0">{{ index + 1 }}</div>
        <div class="col-8 p-0">{{ item.place_name }}</div>
        <div class="col-1 p-0" @click="deletePickList(index)" style="cursor: pointer">x</div>
      </div>
      <!-- 담기와 삭제 버튼 -->
      <div style="margin-top: auto;">
        <div class="row m-2">
          <div class="col">
            <b-button size="sm" variant="light">add</b-button>
          </div>
          <div class="col">
            <b-button size="sm" variant="light">delete</b-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import { eventBus } from "@/main";

export default {
  data() {
    return {
      title: "",
      pickList: [],   // 추가한 여행지 목록을 배열로 관리 => 모든 정보가 다 들어있음
    }
  },

  mounted() {
    eventBus.$off('pick-item-update'); // 기존에 등록된 이벤트 리스너 제거
    eventBus.$on('pick-item-update', (place) => {
      this.pickList.push(place); // 새로운 항목을 배열에 추가
    });
  },

  computed: {
    computedTitle() {
      return this.title;
    }
  },


  methods: {
    deletePickList(index) {
      this.pickList.splice(index, 1); // 선택한 항목을 배열에서 제거
    },
    dragStart(event, index) {
      event.dataTransfer.setData('text/plain', index.toString());
    }
  }
}
</script>

<style>
.plan-pick-list-text {
  font-size: small;
  text-align: center;
}
</style>