<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="basic">글보기</mark>
        </h2>
      </div>
      <div class="col-lg-8 col-md-10 col-sm-12">
        <div class="row my-2">
          <h2 class="text-secondary px-5">
            {{ article.articleNo }} {{ article.subject }}
          </h2>
        </div>
        <div class="row">
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
          <div class="col-md-4 align-self-center text-end">댓글 : 17</div>
          <div class="divider mb-3"></div>
          <div class="text-secondary">{{ article.content }}</div>
          <div class="divider mt-3 mb-3"></div>
          <div class="d-flex justify-flex-end" style="flex-direction: column">
            <div class="row">
            </div>
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
              
            </div>
          </div>
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
    this.getArticle();
  },
  methods: {
    getArticle() {
      // 비동기
      // TODO : 글번호에 해당하는 글정보 얻기.
      fetch(
        `http://localhost:9018/notice/api/view/${this.$route.params.articleNo}`,
        {
          method: "GET",
        }
      )
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
          alert("글 가져오기 성공");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    moveList() {
      this.$router.push("/notice/api/list");
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { articleNo: this.article.articleNo },
      });
    },
    deleteArticle() {
      fetch(
        `http://localhost:9018/notice/api/delete/${this.$route.params.articleNo}`,
        {
          method: "DELETE",
          body: JSON.stringify({
            articleNo: this.articleNo,
          }),
        }
      )
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
