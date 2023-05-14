<template>
  <div class="container">
    <div class="row justify-content-center">
      <!-- 글보기 제목 시작 -->
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <span class="bg-light">글보기</span>
        </h2>
      </div>
      <!-- 글보기 제목 끝 -->
      <!-- 글번호, 제목 시작 -->
      <div class="col-lg-8 col-md-10 col-sm-12">
        <div class="row my-2">
          <div class="col-1">
            <h2 class="text-secondary">{{ article.articleNo }}.</h2>
          </div>
          <div class="col-11">
            <h2 class="text-secondary">{{ article.subject }}</h2>
          </div>
        </div>
        <!-- 글번호, 제목 끝 -->
        <div class="row">
          <!-- 아이디, 등록시간, 조회수 시작 -->
          <div class="col-md-8">
            <div class="clearfix align-content-center">
              <img
                class="avatar me-2 float-md-start bg-light p-2"
                src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
              />
              <p>
                <span class="fw-bold">{{ article.userId }}</span> <br />
                <span class="text-secondary fw-light">
                  {{ article.registerTime }} 조회 : {{ article.hit }}
                </span>
              </p>
            </div>
          </div>
          <div class="col-md-4 align-self-center text-right">댓글 : 17</div>
          <!-- 아이디, 등록시간, 댓글, 조회수 끝 -->
          <!-- 내용 시작 -->
        </div>
        <hr class="my-3" />
        <div class="text-secondary">{{ article.content }}</div>
        <hr class="my-3" />
        <!-- 내용 끝 -->
        <!-- 버튼 3개 시작 -->
        <div style="text-align: right">
          <button
            type="button"
            id="btn-list"
            class="btn btn-outline-primary mb-3"
            @click="moveList"
          >
            글목록
          </button>
          <button
            type="button"
            id="btn-mv-modify"
            class="btn btn-outline-success mb-3 ms-1"
            @click="moveModifyArticle"
          >
            글수정
          </button>
          <button
            type="button"
            id="btn-delete"
            class="btn btn-outline-danger mb-3 ms-1"
            @click="deleteArticle"
          >
            글삭제
          </button>
          <!-- 버튼 3개 끝 -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "NoticeView",

  data() {
    return {
      articleNo: "",
      article: [],
    };
  },
  created() {
    // 글 가져오기 실행
    this.getArticle();
  },
  methods: {
    // 글 가져오기
    getArticle() {
      fetch(`http://localhost:9018/notice/api/view/${this.$route.params.articleNo}`, {
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
          this.article = data.noticeArticle;
          console.log(this.article);
          //alert("글 가져오기 성공");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 목록으로 이동
    moveList() {
      this.$router.push("/notice/api/list");
    },
    // 수정으로 이동
    moveModifyArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { articleNo: this.article.articleNo },
      });
    },
    // 삭제하기
    deleteArticle() {
      fetch(`http://localhost:9018/notice/api/delete/${this.$route.params.articleNo}`, {
        method: "DELETE",
        body: JSON.stringify({
          articleNo: this.articleNo,
        }),
      })
        .then((response) => {
          if (response.ok) {
            alert("글 삭제 성공");
            this.moveList();
          } else {
            throw new Error("글 삭제 실패");
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
