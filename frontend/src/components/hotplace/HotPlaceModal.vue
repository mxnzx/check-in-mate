<template>
  <b-modal
    ref="hotPlace-modal"
    v-model="showHotPlaceModal"
    id="signUpModal"
    style="text-center"
    hide-footer
  >
    <div class="modal-body">
      <div class="carousel-inner">
        <!-- 슬라이드 쇼 -->
        <div class="carousel-item active">
          <!--가로-->
          <img
            class="d-block w-100"
            src="https://images.pexels.com/photos/213399/pexels-photo-213399.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
            alt="First slide"
          />
          <div class="carousel-caption d-none d-md-block">
            <h5>TEST</h5>
            <p>testtesttest</p>
          </div>
        </div>
        <div class="carousel-item">
          <img
            class="d-block w-100"
            src="https://images.pexels.com/photos/2355519/pexels-photo-2355519.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260"
            alt="Second slide"
          />
        </div>
        <div class="carousel-item">
          <img
            class="d-block w-100"
            src="https://images.pexels.com/photos/2544554/pexels-photo-2544554.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"
            alt="Third slide"
          />
        </div>

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
    <div class="modal-footer" style="justify-content: center">
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
        id="btn-register"
        class="btn btn-outline-primary mb-3"
        @click="registArticle"
      >
        수정
      </button>
      <button
        type="reset"
        class="btn btn-outline-danger mb-3"
        style="margin-left: 5px"
        @click="moveList"
      >
        삭제
      </button>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: "HotPlaceModal",
  props: {},
  data() {
    return {
      article: [],
      showHotPlaceModal: false,
      articleno: null, // articleno를 data 속성으로 정의합니다.
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
    getArticle() {
      console.log(">>>>>>>>>>>" + this.articleno);
      fetch(`http://localhost:9018/hotplace/view/${this.articleno}`, {
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

    // hideModal() {
    //   this.$refs["hotPlace-modal"].hide();
    // },
  },
};
</script>

<style></style>
