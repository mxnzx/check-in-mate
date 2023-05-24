<template>
  <nav class="navbar" style="background-color: transparent">
    <div class="row container-fluid">
      <!-- 여기부터 2개줄 합치기 -->
      <div
        class="row"
        style="align-items: center; width: 100%; justify-content: space-between"
      >
        <!-- 로고 시작 -->
        <div class="" style="width: 8vmin"></div>
        <div class="">
          <b-navbar-brand href="/">
            <img
              src="@/assets/images/etc/checkinmate.png"
              alt="MainLogo"
              width="100px;"
            />
          </b-navbar-brand>
        </div>
        <!-- 얘까지 로고 -->
        <b-navbar-nav
          class="row"
          style="flex-direction: row; justify-content: right"
        >
          <!-- 공지아이콘시작 -->
          <b-navbar-nav>
            <b-nav-item href="#" style="display: flex; align-items: center">
              <router-link to="/notice/api" class="" style="margin: auto">
                <img
                  src="@/assets/images/etc/gongzi.png"
                  class=""
                  alt="top1"
                  style="width: 40px"
                />
              </router-link>
            </b-nav-item>
          </b-navbar-nav>
          <!-- 공지 아이콘 끝 -->

          <!-- 로그인 후에 띄울거 -->
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <b-nav-item-dropdown style="display: flex" right>
              <template #button-content>
                <img
                  src="@/assets/images/etc/loginsaram.png"
                  class=""
                  alt="top1"
                  style="width: 30px"
                />
              </template>
              <b-nav-item class="align-self-center">
                Signed in as {{ userInfo.userid }}
              </b-nav-item>
              <b-nav-item class="align-self-center">
                <router-link :to="{ name: 'mypage' }" class="align-self-center"
                  >My page</router-link
                >
              </b-nav-item>
              <b-nav-item
                class="align-self-center link"
                @click.prevent="onClickLogout"
                >Sign out</b-nav-item
              >
            </b-nav-item-dropdown>

            <!-- 사용자 이름 출력 -->
            <!-- <b-nav-item class="align-self-center">
              {{ userInfo.username }}({{ userInfo.userid }})
            </b-nav-item> -->
          </b-navbar-nav>

          <!-- 로그인 전에 띄울거 -->
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item-dropdown text="회원관리" right>
              <template #button-content>
                <img
                  src="@/assets/images/etc/user.png"
                  class=""
                  alt="top1"
                  style="width: 30px"
                />
              </template>
              <b-dropdown-item @click="openJoinModal">회원가입</b-dropdown-item>
              <b-dropdown-item @click="openLoginModal">로그인</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>

          <join-modal ref="JoinModal"></join-modal>
          <login-modal ref="LoginModal"></login-modal>
        </b-navbar-nav>
      </div>
      <!-- 여기까지 로고랑 아이콘  -->
      <div class="">
        <div class="row">
          <ul class="nav justify-content-end">
            <b-nav-item class="nav-item">
              <!-- <a class="nav-link active" aria-current="page" href="#">Active</a> -->
              <router-link to="/attraction" class="nav-link active"
                >지역별여행지</router-link
              >
            </b-nav-item>
            <b-nav-item class="nav-item">
              <router-link to="/myplan" class="nav-link active"
                >나의여행계획</router-link
              >
            </b-nav-item>
            <b-nav-item class="nav-item">
              <router-link to="/hotplace" class="nav-link active"
                >핫플자랑하기</router-link
              >
            </b-nav-item>
            <b-nav-item class="nav-item">
              <router-link to="/board/api" class="nav-link active"
                >동행모집</router-link
              >
            </b-nav-item>
          </ul>
        </div>
      </div>
    </div>
  </nav>
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
.container-fluid {
  flex-direction: column;
}
.navbar {
  background-color: transparent;
  z-index: 99;
}
</style>
