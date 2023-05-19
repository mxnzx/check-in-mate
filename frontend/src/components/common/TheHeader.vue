<template>
  <div class="header">
    <b-navbar toggleable="lg" type="light">
      <b-navbar-brand href="/" class="" id="enjoyTrip">Enjoy Trip</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item
            ><router-link to="/attraction" class=""
              >지역별여행지</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"><router-link to="/myplan" class=""
              >나의여행계획</router-link
            ></b-nav-item>
          <b-nav-item href="#"
            ><router-link to="/hotplace" class="" href=""
              >핫플자랑하기</router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link to="/board/api" class=""
              >여행정보공유</router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">
          <b-navbar-nav>
            <b-nav-item href="#" style="display: flex; align-items: center">
              <router-link to="/notice/api" class="" style="margin: auto">
                공지사항
              </router-link>
            </b-nav-item>
          </b-navbar-nav>

          <!-- 로그인 후에 띄울거 -->
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <b-nav-item-dropdown style="display: flex" right>
              <template #button-content>
                <img
                  src="@/assets/images/etc/no-profile.png"
                  class=""
                  alt="top1"
                  style="width: 30px"
                />
              </template>
              <b-nav-item class="align-self-center">
                <router-link
                  :to="{ name: 'mypage' }"
                  class="link align-self-center"
                  >마이페이지</router-link
                >
              </b-nav-item>
              <b-nav-item
                class="align-self-center link"
                @click.prevent="onClickLogout"
                >로그아웃</b-nav-item
              >
            </b-nav-item-dropdown>

            <!-- 사용자 이름 출력 -->
            <b-nav-item class="align-self-center">
              {{ userInfo.username }}({{ userInfo.userid }})
            </b-nav-item>
          </b-navbar-nav>

          <!-- 로그인 전에 띄울거 -->
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item-dropdown text="회원관리" right>
              <template #button-content>
                <span>회원관리</span>
              </template>
              <b-dropdown-item @click="openJoinModal">회원가입</b-dropdown-item>
              <b-dropdown-item @click="openLoginModal">로그인</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>

          <join-modal ref="JoinModal"></join-modal>
          <login-modal ref="LoginModal"></login-modal>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import JoinModal from "@/components/user/JoinModal.vue";
import LoginModal from "@/components/user/LoginModal.vue";

const memberStore = "memberStore";

export default {
  components: {
    JoinModal,
    LoginModal,
  },

  name: "TheHeaderNavbar",
  data() {
    return {};
  },

  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/index") this.$router.push({ name: "main" });
    },
    openJoinModal() {
      this.$refs.JoinModal.show();
    },
    openLoginModal() {
      this.$refs.LoginModal.show();
    },
  },
};
</script>

<style scoped>
#logo {
  width: 120px;
}

.link {
  text-decoration: none;
}

.dropdown-toggle {
  display: flex !important;
}
.dropdown-toggle::after {
  display: flex;
  margin-top: 10px;
}
.nav-link {
  display: flex;
}
</style>
