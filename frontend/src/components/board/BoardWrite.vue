<template>
  <b-modal
    ref="boardwrite-modal"
    v-model="showBoardWriteModal"
    id="signUpModal"
    title="동행 글쓰기"
    hide-footer
  >
    <div class="modal-body">
      <div class="row mb-1 text-center">
        <div class="col-4">제목</div>
        <div class="col">
          <input
            id="join_mate_title"
            name="join_mate_title"
            class="form-control"
            type="text"
            placeholder="제목을 입력하세요"
            v-model="join_mate_title"
          />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">만남유형</div>
        <div class="col">
          <b-select
            class="form-select"
            id="join_mate_category"
            name="join_mate_category"
            v-model="join_mate_category"
          >
            <option value="식사">식사</option>
            <option value="관광">관광</option>
            <option value="관람">관람</option>
          </b-select>
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">장소</div>
        <div class="col">
          <input
            id="join_mate_place"
            name="join_mate_place"
            class="form-control"
            type="text"
            placeholder="장소를 입력하세요"
            v-model="join_mate_place"
          />
        </div>
      </div>
      <div id="idcheck-result"></div>
      <div class="row"></div>
      <div class="row mb-1 text-center">
        <div class="col-4">날짜/시간</div>
        <div class="col">
          <input
            type="date"
            id="date"
            class="form-control"
            max="2033-05-16"
            min="2013-05-16"
            value="2023-05-16"
            v-model="join_mate_date"
          />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">인원수</div>
        <div class="col">
          <input
            id="join_mate_people_num"
            name="join_mate_people_num"
            class="form-control"
            type="text"
            placeholder="원하는 인원수를 작성해주세요"
            v-model="join_mate_people_num"
          />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">기타</div>
        <div class="col">
          <input
            id="emailid_join"
            name="emailId"
            class="form-control"
            type="text"
            placeholder="기타 내용을 작성해주세요"
            v-model="join_mate_content"
          />
        </div>
      </div>
    </div>
    <div class="modal-footer">
      <b-button
        id="btn-join"
        type="button"
        variant="primary"
        @click="registArticle"
        >글작성</b-button
      >
      <b-button type="button" variant="danger" @click="hideModal"
        >취소</b-button
      >
    </div>
  </b-modal>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "BoardWrite",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      showBoardWriteModal: false,
      userId: "",
      title: "",
      category: "",
      place: "",
      date: "",
      peopleNum: "",
      content: "",
    };
  },
  methods: {
    show() {
      this.showBoardWriteModal = true;
    },
    hideModal() {
      this.$refs["boardwrite-modal"].hide();
    },
    registArticle() {
      let obj = {
        userId: this.userInfo.userid,
        title: this.join_mate_title,
        category: this.join_mate_category,
        place: this.join_mate_place,
        date: this.join_mate_date,
        peopleNum: this.join_mate_people_num,
        content: this.join_mate_content,
      };
      console.log("write userid >> " + this.userId);
      fetch("http://localhost:9018/board/api/write", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(obj),
      })
        .then((response) => {
          if (response.ok) {
            console.log("작성성공");
            console.log(obj);
            //this.$router.push("list");
            this.hideModal();
            this.$router.go();
          } else {
            console.log(obj);
            console.log("실패");
            throw new Error("글 등록 실패");
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    },

    moveList() {
      this.$router.push("list");
    },
  },
};
</script>

<style></style>
