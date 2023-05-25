<template>
  <b-modal
    ref="hotPlace-modal"
    v-model="showHotPlaceModal"
    id="signUpModal"
    style="text-center"
    hide-footer
    @hide="hideModal"
  >
    <div class="modal-body">
      <div class="carousel-inner">
        <!-- 슬라이드 쇼 -->
        <div class="carousel-item active">
          <!--가로-->
          <img class="d-block w-100" :src="getImageSrc()" alt="First slide" />
          <!-- 사진에 글씨 쓰고 싶으면 아래 div -->
          <div class="carousel-caption d-none d-md-block"></div>
        </div>

        <!--     슬라이드 쇼 하고 싶으면 이렇게 추가 
        <div class="carousel-item">
          <img
            class="d-block w-100"
            src="https://images.pexels.com/photos/2355519/pexels-photo-2355519.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"
            alt="Second slide"
          />
        </div> -->

        <!-- / 슬라이드 쇼 끝 -->

        <!-- 왼쪽 오른쪽 화살표 버튼 -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <!-- <span>Previous</span> -->
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <!-- <span>Next</span> -->
        </a>
        <!-- / 화살표 버튼 끝 -->

        <!-- 인디케이터 -->
        <ul class="carousel-indicators">
          <li data-target="#demo" data-slide-to="0" class="active"></li>
          <!--0번부터시작-->
          <li data-target="#demo" data-slide-to="1"></li>
          <li data-target="#demo" data-slide-to="2"></li>
        </ul>
        <!-- 인디케이터 끝 -->
      </div>
    </div>
    <div
      class="modal-footer"
      style="justify-content: center; flex-direction: column"
    >
      <div class="inline-block">
        <b-card-text> {{ article.title }} </b-card-text>
      </div>
      <b-card-text>
        {{ article.content }}
      </b-card-text>
    </div>
    <div class="col-auto text-right">
      <button
        type="button"
        id="btn-scrap"
        class="btn btn-outline-primary mb-3"
        @click="scrapArticle"
      >
        스크랩
      </button>
      <button
        type="button"
        id="btn-register"
        class="btn btn-outline-primary mb-3"
        style="margin-left: 5px"
        v-if="userInfo && userInfo.userid === article.userid"
        @click="moveModifyArticle"
      >
        수정
      </button>
      <button
        type="reset"
        class="btn btn-outline-danger mb-3"
        style="margin-left: 5px"
        v-if="userInfo && userInfo.userid === article.userid"
        @click="deleteArticle"
      >
        삭제
      </button>
    </div>
  </b-modal>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "HotPlaceModal",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {},
  data() {
    return {
      article: [],
      showHotPlaceModal: false,
      articleno: null, // articleno를 data 속성으로 정의합니다.
      savefile: "",
      title: "",
    };
  },
  created() {
    this.$parent.$on("open-modal", this.handleOpenModal);
  },
  beforeDestroy() {
    this.$parent.$off("open-modal", this.handleOpenModal);
  },

  methods: {
    show() {
      this.showHotPlaceModal = true;
    },
    handleOpenModal(articleno) {
      this.articleno = articleno;
      this.getArticle(); // getArticle 메서드 호출 추가
      this.show();
    },

    scrapArticle() {
      console.log("articleno >> " + this.articleno);
      fetch("http://127.0.0.1:9018/hotplace/scrap", {
        method: "POST",
        headers: {
          "Content-Type": "application/multipart",
        },
        body: JSON.stringify(this.articleno, this.userInfo.userid),
      })
        .then((response) => {
          if (response.ok) {
            console.log("스크랩 성공");
            alert("스크랩 성공!");
            this.moveList();
          } else {
            console.log(this.articleno + "  " + this.userInfo.userid);
            console.log("실패");
            throw new Error("스크랩 되었습니다.");
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    },

    getArticle() {
      console.log(">>>>>>>>>>>" + this.articleno);
      fetch(`http://127.0.0.1:9018/hotplace/view/${this.articleno}`, {
        method: "GET",
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error("글 가져오기 실패");
          }
        })
        .then((data) => {
          this.article = data.article;
          console.log(this.article);
          //alert("핫플레이스 글 가져오기 성공");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 수정으로 이동
    moveModifyArticle() {
      this.$router.push({
        name: "HotPlaceModify",
        params: { article: this.article },
      });
    },

    // 삭제하기
    deleteArticle() {
      fetch(`http://127.0.0.1:9018/hotplace/delete/${this.articleno}`, {
        method: "DELETE",
        body: JSON.stringify({
          articleno: this.articleno,
        }),
      })
        .then((response) => {
          if (response.ok) {
            alert("글 삭제 성공");
            this.$router.go();
          } else {
            throw new Error("글 삭제 실패");
          }
        })
        .catch((error) => {
          this.message = error.message;
        });
    },

    hideModal() {
      console.log("하이드모달작동1");
      // this.$refs["hotPlace-modal"].hide();
    },
    hideHotPlaceModal() {
      this.showHotPlaceModal = false;
    },
    getImageSrc() {
      // 이미지 파일 경로를 반환합니다.
      return "http://127.0.0.1:9018/hotplace/image/" + this.article.savefile;
    },
  },
};
</script>

<style></style>
