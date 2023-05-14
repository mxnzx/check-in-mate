<template>
  <div class="header">
    <b-navbar toggleable="lg" type="light">
      <b-navbar-brand href="/" class="" id="enjoyTrip">Enjoy Trip</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item><router-link to="/attraction" class="">지역별여행지</router-link></b-nav-item>
          <b-nav-item href="#"><a class="" href="">나의여행계획</a></b-nav-item>
          <b-nav-item href="#"><a class="" href="">핫플자랑하기</a></b-nav-item>
          <b-nav-item href="#"><router-link to="/board/api" class="">여행정보공유</router-link></b-nav-item>

        </b-navbar-nav>


        <b-navbar-nav class="ml-auto">

          <b-navbar-nav>
            <b-nav-item href="#"><router-link to="/notice/api" class="">공지사항</router-link></b-nav-item>
          </b-navbar-nav>


          <b-nav-item-dropdown text="회원관리" right>
            <template #button-content>
              <span>회원관리</span>
            </template>
            <b-dropdown-item @click="openJoinModal">회원가입</b-dropdown-item>
            <b-dropdown-item @click="openLoginModal">로그인</b-dropdown-item>
          </b-nav-item-dropdown>

          <join-modal ref="JoinModal"></join-modal>
          <login-modal ref="LoginModal"></login-modal>

        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import JoinModal from "@/components/user/JoinModal.vue";
import LoginModal from "@/components/user/LoginModal.vue";

export default {

  components: {
    JoinModal,
    LoginModal,
  },

  data() {
    return {
      
    };
  },
  methods: {
    openJoinModal() {
      this.$refs.JoinModal.show(); 
    },
    openLoginModal() {
      this.$refs.LoginModal.show(); 
    }
  }
}
</script>

<style></style>
