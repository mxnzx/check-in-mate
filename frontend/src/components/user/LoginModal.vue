<template>
  <b-modal
    ref="login-modal"
    v-model="showLoginModal"
    id="signUpModal"
    title="Login"
    hide-footer
  >
    <div class="modal-body">
      <div class="container-fluid">
        <div class="row mb-1 text-center">
          <div class="col-3">아이디</div>
          <div class="col">
            <input
              type="text"
              placeholder="id"
              class="form-control"
              id="userid"
              name="userid"
              required
              v-model="user.userid"
              @keyup.enter="confirm"
            />
          </div>
        </div>
        <div class="row mb-1 text-center">
          <div class="col-3">비밀번호</div>
          <div class="col">
            <input
              type="password"
              placeholder="pw"
              class="form-control"
              id="userpwd"
              name="userpwd"
              required
              v-model="user.userpwd"
              @keyup.enter="confirm"
            />
          </div>
        </div>
        <div class="row">
          <div class="form-check mb-6 float-end">
            <input
              class="form-check-input"
              type="checkbox"
              value="ok"
              id="saveid"
              name="saveid"
            />
            <label class="form-check-label" for="saveid"> 아이디저장 </label>
          </div>
        </div>
      </div>
    </div>
    <div class="modal-footer">
      <b-button type="button" @click="searchIdPw">id/pw 찾기</b-button>
      <b-button type="button" id="btn-login" variant="primary" @click="confirm"
        >로그인</b-button
      >
      <b-button type="button" variant="danger" @click="hideModal"
        >취소</b-button
      >
    </div>
  </b-modal>
</template>

<script>
//import axios from "axios";
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";
export default {
  data() {
    return {
      showLoginModal: false,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      //   console.log(this.user);
      await this.userConfirm(this.user);
      console.log(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        if (this.$route.path != "/index") this.$router.push({ name: "main" });
        this.hideModal();
      } else {
        alert("아이디 비밀번호를 다시 확인하세요 ");
        this.hideModal();
      }
    },
    show() {
      this.showLoginModal = true;
    },
    hideModal() {
      this.$refs["login-modal"].hide();
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
      this.hideModal();
      this.$router.push("/user/findidpassword");
    },
  },
};
</script>

<style></style>
