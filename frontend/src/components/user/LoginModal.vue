<template>
  <b-modal ref="login-modal" v-model="showLoginModal" id="signUpModal" title="Login" hide-footer>
    <div class="modal-body">
      <div class="container-fluid">
        <div class="row">
          <div class="form-check mb-3 float-end">
            <input class="form-check-input" type="checkbox" value="ok" id="saveid" name="saveid" /> <label
              class="form-check-label" for="saveid"> 아이디저장 </label>
          </div>
        </div>
        <div class="row mb-1 text-center">
          <div class="col-3">아이디</div>
          <div class="col">
            <input type="text" placeholder="id" class="form-control" id="userid" name="userid" />
          </div>

        </div>
        <div class="row mb-1 text-center">
          <div class="col-3">비밀번호</div>
          <div class="col">
            <input type="password" placeholder="pw" class="form-control" id="userpwd" name="userpwd">
          </div>
        </div>
      </div>
    </div>
    <div class="modal-footer">
      <b-button type="button" @click="searchIdPw">id/pw 찾기</b-button>
      <b-button type="button" id="btn-login" variant="outline-primary" @click="loginMember">로그인</b-button>
      <b-button type="button" variant="danger" @click="hideModal">취소</b-button>



    </div>
  </b-modal>
</template>

<script>
//import axios from "axios";

export default {
  data() {
    return {
      showLoginModal: false,

    }

  },
  methods: {
    show() {
      this.showLoginModal = true;
    },
    hideModal() {
      this.$refs['login-modal'].hide()
    },
    loginMember() {
      if (!document.querySelector("#userid").value) {
        alert("아이디 입력!!");
        return;
      } else if (!document.querySelector("#userpwd").value) {
        alert("비밀번호 입력!!");
        return;
      } else {
        // let form = document.querySelector("#form-login");
        // form.setAttribute("action", "${root}/user/login");
        console.log("login");
        // form.submit();
      }
    },
    searchIdPw() {
      //아이디 비번 찾기 로직
      
    }

  },


};
</script>

<style></style>
