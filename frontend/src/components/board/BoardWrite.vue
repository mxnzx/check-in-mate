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
            id="pwdcheck_join"
            name="pwdcheck"
            class="form-control"
            type="text"
            placeholder="제목을 입력하세요"
            v-model="userPwd"
          />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">만남유형</div>
        <div class="col">
          <b-select
            class="form-select"
            id="emaildomain_join"
            name="emailDomain"
            v-model="emailDomain"
          >
            <option value="ssafy.com">식사</option>
            <option value="google.com">관광</option>
            <option value="kakao.com">관람</option>
          </b-select>
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">장소</div>
        <div class="col">
          <input
            id="userid_join"
            name="userId"
            class="form-control"
            type="text"
            placeholder="장소를 입력하세요"
            v-model="userName"
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
          />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">인원수</div>
        <div class="col">
          <input
            id="pwdcheck_join"
            name="pwdcheck"
            class="form-control"
            type="text"
            placeholder="원하는 인원수를 작성해주세요"
            v-model="userPwd"
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
            v-model="emailId"
          />
        </div>
      </div>
    </div>
    <div class="modal-footer">
      <b-button
        id="btn-join"
        type="button"
        variant="primary"
        @click="checkValue"
        >글작성</b-button
      >
      <b-button type="button" variant="danger" @click="hideModal"
        >취소</b-button
      >
    </div>
  </b-modal>
</template>

<script>
export default {
  name: "BoardWrite",
  data() {
    return {
      showBoardWriteModal: false,
      userId: "",
      subject: "",
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
        userId: this.userId,
        subject: this.subject,
        content: this.content,
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
            console.log(this.userId);
            console.log(this.subject);
            console.log(this.content);
            this.$router.push("list");
          } else {
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
