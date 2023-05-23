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
            v-model="userpwd"
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
            @click="updateMember"
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
            @click="deleteMember"
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
  data() {
    return {
      userpwd: "",
      userid: "",
    };
  },

  created() {},
  methods: {
    deleteMember() {
      const userId = this.userInfo.userid; // 사용자의 userid 값 가져오기

      fetch(`http://192.168.208.83:9018/user/deleteMember/${userId}`, {
        method: "DELETE",
        body: JSON.stringify({
          userid: userId,
        }),
      })
        .then((response) => {
          if (response.ok) {
            console.log("회원 삭제 성공");
            this.$store.commit(`${memberStore}/SET_USER_INFO`, null);
            this.$router.push("/");
          } else {
            throw new Error("회원 삭제 실패");
          }
        })
        .catch((error) => {
          this.message = error.message;
        });
    },
    updateMember() {
      const modifiedData = {
        userpwd: this.userpwd,
        userid: this.userInfo.userid,
      };

      fetch(`http://192.168.208.83:9018/user/updateMember`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(modifiedData),
      })
        .then((response) => {
          if (response.ok) {
            alert("회원정보 수정 성공");
            this.$router.push("/");
          } else {
            throw new Error("회원정보 수정 실패");
          }
        })
        .catch((error) => {
          this.message = error.message;
        });
    },
  },
};
</script>

<style></style>
