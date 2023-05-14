<template>
  <mdb-modal :show="register" @close="register = false">
      <mdb-modal-header class="text-center">
        <mdb-modal-title tag="h4" bold class="w-100">Sign up</mdb-modal-title>
      </mdb-modal-header>
      <mdb-modal-body class="mx-3 grey-text">
        <mdb-input label="Your name" icon="user" class="mb-5" />
        <mdb-input label="Your email" icon="envelope" type="email" class="mb-5" />
        <mdb-input label="Your password" icon="lock" type="password" />
      </mdb-modal-body>
      <mdb-modal-footer center>
        <mdb-btn @click.native="register = false" color="deep-orange">Sign Up</mdb-btn>
      </mdb-modal-footer>
    </mdb-modal>
  <!-- <form id="form-join" method="POST" action="">
    <div class="modal" id="signUpModal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">회원 가입</h5>
          </div>
          <div class="modal-body">
            <div class="row mb-1 text-center">
              <div class="col-3">이름</div>
              <div class="col">
                <input id="username_join" name="userName" class="form-control" type="text" placeholder="이름"
                  v-model="userId" />
              </div>
            </div>
            <div class="row mb-1 text-center">
              <div class="col-3">아이디</div>
              <div class="col">
                <input id="userid_join" name="userId" class="form-control" type="text" placeholder="아이디"
                  v-model="userName" />
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
                <input id="emailid_join" name="emailId" class="form-control" type="text" placeholder="이메일"
                  v-model="emailId" />
              </div>
              <div class="col-1">
                <span class="input-group-text" style="background-color:white; border:none; padding-left:2px">@</span>
              </div>
              <div class="col">
                <select class="form-select" id="emaildomain_join" name="emailDomain">
                  <option value="ssafy.com">SSAFY</option>
                  <option value="google.com">GOOGLE</option>
                  <option value="kakao.com">KAKAO</option>
                  <option value="naver.com">NAVER</option>
                </select>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button id="btn-join" type="button" class="btn btn-primary" data-mdb-dismiss="modal"
              @click="checkValue">회원가입</button>

            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
          </div>
        </div>
      </div>
    </div>
  </form> -->
</template>

<script>
import axios from "axios";

export default {
  name: "JoinModel",
  data() {
    return {
      userId: "",
      userName: "",
      userPwd: "",
      emailId: "",
      userPwd: "",
      emailDomain: "",
    }
  },
  methods: {
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
      } else if (!isUseId) {
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
      axios
        .post("http://127.0.0.1:9018/user/api/join", this.data)
        .then(() => this.$router.push("@"));
    }


  }



};
</script>

<style></style>
