<template>
  <b-modal v-model="showJoinModal" id="signUpModal" title="Join">
    <div class="modal-body">
      <div class="row mb-1 text-center">
        <div class="col-3">이름</div>
        <div class="col">
          <input id="username_join" name="userName" class="form-control" type="text" placeholder="이름" v-model="userId" />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-3">아이디</div>
        <div class="col">
          <input id="userid_join" name="userId" class="form-control" type="text" placeholder="아이디" v-model="userName" />
        </div>
      </div>
      <div id="idcheck-result"></div>
      <div class="row"></div>
      <div class="row mb-1 text-center">
        <div class="col-3">비밀번호</div>
        <div class="col">
          <input id="userpwd_join" name="userPwd" class="form-control" type="password" placeholder="비밀번호"
            v-model="userPwd" />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-3">비밀번호확인</div>
        <div class="col">
          <input id="pwdcheck_join" name="pwdcheck" class="form-control" type="text" placeholder="비밀번호확인"
            v-model="userPwd" />
        </div>
      </div>
      <div class="row mb-1 text-center">
        <div class="col-3">이메일</div>
        <div class="col-3">
          <input id="emailid_join" name="emailId" class="form-control" type="text" placeholder="이메일" v-model="emailId" />
        </div>
        <div class="col-1">
          <span class="input-group-text" style="background-color:white; border:none; padding-left:2px">@</span>
        </div>
        <div class="col">
          <select class="form-select" id="emaildomain_join" name="emailDomain" v-model="emailDomain">
            <option value="ssafy.com">SSAFY</option>
            <option value="google.com">GOOGLE</option>
            <option value="kakao.com">KAKAO</option>
            <option value="naver.com">NAVER</option>
          </select>
        </div>
      </div>
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

      userId: "",
      userName: "",
      userPwd: "",
      emailId: "",
      emailDomain: "",
      isUseId: "",
    }
  },
  methods: {
    show() {
      this.showJoinModal = true;

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
      } else if (document.querySelector("#userpwd_join").value != document.querySelector("#pwdcheck_join").value) {
        alert("비밀번호 확인!!");
        return;
      } else if (!this.isUseId) {
        alert("사용할 수 없는 아이디 입니다.");
        return;
      } else {
        //비어있는 값이 없다면 joinMember 호출
        this.joinMember();
        // let form = document.querySelector("#form-join");
        // form.setAttribute("action", "${root}/user/api/join");
        // form.submit();

      }
    },
    joinMember() {
      console.log("회원가입 시도");
      axios
        .post("http://127.0.0.1:9018/user/api/join", this.data)
        //.then(() => this.$router.push("@"));
    }
  }
};
</script>

<style></style>
