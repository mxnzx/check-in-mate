<template>
  <!--중앙 content Start-->
  <div class="container" style="text-align: right">
    <div class="row" style="height: 100px"></div>
    <div class="row-4 m-1">
      <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
          <img
            src="@/assets/images/etc/no-profile.png"
            alt="profile"
            style="height: 100px"
          />
        </div>
        <div class="col-4"></div>
      </div>
      <div class="row m-3">
        <div class="col-4">
          <p style="padding-top: 8px">아이디</p>
        </div>
        <div class="col-6">
          <input
            class="form-mypage w-75"
            type="text"
            placeholder="아이디자리"
            readonly="readonly"
            id="id"
            name="userId"
            :value="userInfo.userid"
          />
        </div>
      </div>
      <div class="row m-3">
        <div class="col-4">
          <p style="padding-top: 8px">사용자 이름</p>
        </div>
        <div class="col-6">
          <input
            class="form-mypage w-75"
            type="text"
            placeholder="이름"
            readonly="readonly"
            id="username"
            name="username"
            :value="userInfo.username"
          />
        </div>
      </div>
      <div class="row m-3">
        <div class="col-4">
          <p style="padding-top: 8px">이메일</p>
        </div>
        <div class="col-6">
          <input
            class="form-mypage w-75"
            type="text"
            placeholder="이메일"
            readonly="readonly"
            id="email"
            name="email"
            :value="userInfo.email + '@ssafy.com'"
          />
        </div>
      </div>
      <div class="row m-3">
        <div class="col-4">
          <p style="padding-top: 8px">비밀번호</p>
        </div>
        <div class="col-6">
          <input
            class="form-mypage w-75"
            type="password"
            id="pw"
            name="userPwd"
            placeholder="변경하실 비밀번호를 입력하세요"
          />
        </div>
      </div>
      <div class="row m-3">
        <div class="col-4">
          <p style="padding-top: 8px">비밀번호 확인</p>
        </div>
        <div class="col-6">
          <input
            class="form-mypage w-75"
            type="password"
            id="pwCheck"
            name="pwCheck"
            placeholder="변경하신 비밀번호를 한번 더 입력하세요"
          />
        </div>
      </div>
      <div class="row m-3">
        <div class="col">
          <button
            id="myPageUpdate"
            type="button"
            class="btn btn-secondary"
            data-mdb-dismiss="modal"
          >
            수정
          </button>
        </div>
        <div class="col" style="text-align: left">
          <button
            id="delete-btn"
            type="button"
            class="btn btn-danger"
            data-bs-dismiss="modal"
          >
            회원탈퇴
          </button>
        </div>
      </div>
    </div>

    <div class="row" style="height: 100px"></div>
  </div>

  <!--중앙 content End-->
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style></style>
