<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="basic">글보기</mark>
        </h2>
      </div>
      <div class="col-lg-8 col-md-10 col-sm-12">
        <div class="row my-12" style="display: inline">
          <h2 class="text-secondary px-3" style="display: inline" text-align="center">
            {{ article.articleNo }}.
          </h2>
          <h2 class="text-secondary px-1" style="display: inline">
            {{ article.subject }}
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
            <!-- 댓글시작 -->
            <section class="mb-5">
              <div class="card bg-light">
                <div class="card-body">
                  <!-- 댓글 목록 -->
                  <ul class="ul_comment" v-for="comment in comments" :key="comment.commentNo">
                    <div class="d-flex mb-4">
                      <div class="flex-shrink-0">
                        <img
                          class="rounded-circle"
                          src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                          alt="..."
                        />
                      </div>
                      <div class="ms-3">
                        <div class="fw-bold">{{ comment.userId }}</div>
                        {{ comment.comment }}
                        <div>
                          {{ comment.registerTime }}
                          <a
                            class="deleteComment"
                            href="#1"
                            @click="deleteComment(comment.commentNo)"
                            style="color: red"
                            >삭제</a
                          >
                        </div>
                      </div>
                    </div>
                  </ul>
                  <!-- 댓글 등록 -->
                  <form class="mb-4">
                    <br />
                    <input
                      type="text"
                      class="form-control"
                      id="userId"
                      name="userId"
                      placeholder="아이디입력"
                      v-model="userId"
                    /><br />
                    <textarea class="form-control" rows="3" placeholder="댓글입력"></textarea>
                  </form>
                  <div class="commentBtn text-end">
                    <button
                      id="commentRegBtn"
                      class="btn btn-outline-primary mb-1"
                      @click="wrtieComment"
                    >
                      댓글 등록
                    </button>
                  </div>
                </div>
              </div>
            </section>
            <!-- 댓글 끝  -->
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
  name: "BoardView",

  data() {
    return {
      articleNo: "",
      article: [],
      comments: [],
      comment: "",
      userId: "",
    };
  },
  created() {
    this.getArticle();
    this.getComment();
  },
  methods: {
    getArticle() {
      // 비동기
      // TODO : 글번호에 해당하는 글정보 얻기.
      fetch(`http://localhost:9018/board/api/view/${this.$route.params.articleNo}`, {
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
          console.log(this.$route.params.articleNo);
          //alert("글 가져오기 성공");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 댓글 목록 가져오기
    getComment() {
      fetch(`http://localhost:9018/comment/${this.$route.params.articleNo}`, {
        method: "GET",
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error("댓글 가져오기 실패");
          }
        })
        .then((data) => {
          this.comments = data;
          console.log(data);
          console.log(this.comments);
          console.log("댓글 가져오기 " + data);
          console.log("댓글 가져오기 성공");
        })
        .catch((error) => {
          console.error(error);
        });
    },
    // 댓글 쓰기
    wrtieComment() {
      let obj = {
        articleNo: this.article.articleNo,
        userId: this.article.userId,
        comment: this.comment,
      };
      fetch(`http://localhost:9018/comment`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(obj),
      })
        .then((response) => {
          if (response.ok) {
            this.$router.go();
          } else {
            throw new Error("댓글 작성 실패");
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    },

    // 댓글 삭제
    deleteComment(commentNo) {
      fetch(`http://localhost:9018/comment/${this.article.articleNo}/${commentNo}`, {
        method: "DELETE",
        body: JSON.stringify({
          articleNo: this.articleNo,
          commentNo: this.commentNo,
        }),
      })
        .then((response) => {
          if (response.ok) {
            this.$router.go();
            console.log("댓글 삭제 성공");
          } else {
            throw new Error("댓글 삭제 실패");
          }
        })
        .catch((error) => {
          this.message = error.message;
        });
    },
    //목록으로 이동
    moveList() {
      this.$router.push("/board/api/list");
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleNo: this.article.articleNo },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      fetch(`http://localhost:9018/board/api/delete/${this.$route.params.articleNo}`, {
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
