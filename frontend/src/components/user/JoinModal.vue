<template>
  <b-modal
    ref="join-modal"
    v-model="showJoinModal"
    id="signUpModal"
    title="Join"
    hide-footer
  >
    <div class="modal-body">
      <div class="row mb-1 text-center">
        <div class="col-4">이름</div>
        <div class="col">
          <input
            id="username_join"
            name="userName"
            class="form-control"
            type="text"
            placeholder="이름"
            v-model="joinMemberData.userid"
          />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">아이디</div>
        <div class="col">
          <input
            id="userid_join"
            name="userId"
            class="form-control"
            type="text"
            placeholder="아이디"
            v-model="joinMemberData.username"
          />
        </div>
      </div>
      <div id="idcheck-result"></div>
      <div class="row"></div>
      <div class="row mb-1 text-center">
        <div class="col-4">비밀번호</div>
        <div class="col">
          <input
            id="userpwd_join"
            name="userPwd"
            class="form-control"
            type="password"
            placeholder="비밀번호"
            v-model="joinMemberData.userpwd"
          />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">비밀번호확인</div>
        <div class="col">
          <input
            id="pwdcheck_join"
            name="pwdcheck"
            class="form-control"
            type="password"
            placeholder="비밀번호확인"
          />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-4">이메일</div>
        <div class="col-3">
          <input
            id="emailid_join"
            name="emailId"
            class="form-control"
            type="text"
            placeholder="이메일"
            v-model="joinMemberData.emailid"
          />
        </div>
        <div class="col-1">
          <span
            class="input-group-text"
            style="background-color: white; border: none; padding-left: 2.5px"
            >@</span
          >
        </div>
        <div class="col">
          <b-select
            class="form-select"
            id="emaildomain_join"
            name="emailDomain"
            v-model="joinMemberData.emaildomain"
          >
            <option value="ssafy.com">SSAFY</option>
            <option value="google.com">GOOGLE</option>
            <option value="kakao.com">KAKAO</option>
            <option value="naver.com">NAVER</option>
          </b-select>
        </div>
      </div>
    </div>
    <div class="modal-footer">
      <b-button
        id="btn-join"
        type="button"
        variant="primary"
        @click="checkValue"
        >회원가입</b-button
      >
      <b-button type="button" variant="danger" @click="hideModal"
        >취소</b-button
      >
    </div>
  </b-modal>
</template>

<script>
import axios from "axios";

export default {
  name: "JoinModel",
  data() {
    return {
      showJoinModal: false,
      joinMemberData: {
        userid: "",
        username: "",
        userpwd: "",
        emailid: "",
        emaildomain: "",
      },
      isUseId: "",
    };
  },
  methods: {
    show() {
      this.showJoinModal = true;
    },
    hideModal() {
      this.$refs["join-modal"].hide();
    },
    //비어있는 값이 없다면 회원가입 ㄱㄱ
    checkValue() {
      if (!document.querySelector("#username_join").value) {
        alert("이름 입력!!");
        return;
      } else if (!document.querySelector("#userid_join").value) {
        alert("아이디 입력!!");
        return;
      } else if (!document.querySelector("#userpwd_join").value) {
        alert("비밀번호 입력!!");
        return;
      } else if (
        document.querySelector("#userpwd_join").value !=
        document.querySelector("#pwdcheck_join").value
      ) {
        alert("비밀번호 확인!!");
        return;
      }
      // else if (!this.isUseId) {
      //   alert("사용할 수 없는 아이디 입니다.");
      //   return;
      // }
      else {
        //비어있는 값이 없다면 joinMember 호출
        this.joinMember();
        // let form = document.querySelector("#form-join");
        // form.setAttribute("action", "${root}/user/api/join");
        // form.submit();
      }
    },
    joinMember() {
      console.log("회원가입 시도");
      console.log(this.joinMemberData);
      axios
        .post("http://localhost:9018/user/join", this.joinMemberData)
        .then(this.closeJoinModal());
    },
    closeJoinModal() {
      this.$refs["join-modal"].hide();
    },
  },
};
</script>

<style></style>
