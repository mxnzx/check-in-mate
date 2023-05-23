<template>
  <div class="container">
    <div class="row justify-content-center">
      <!-- 글보기 제목 시작 -->
      <!-- <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <span class="bg-light">글보기</span>
        </h2>
      </div> -->
      <!-- 글보기 제목 끝 -->
      <!-- 글번호, 제목 시작 -->
      <div class="col-lg-8 col-md-10 col-sm-12">
        <div class="row my-2">
          <!-- <div class="col-1">
            <h2 class="text-secondary">{{ article.articleNo }}.</h2>
          </div> -->
          <div class="col-11">
            <h2 class="text-secondary">{{ article.title }}</h2>
          </div>
        </div>
        <!-- 글번호, 제목 끝 -->
        <!-- 아이디, 등록시간, 댓글, 조회수 시작 -->
        <div class="row">
          <div class="col-md-8">
            <div class="clearfix align-items-center">
              <img
                class="avatar me-md-2 float-md-start bg-light p-2"
                src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
              />
              <span class="fw-bold" style="padding-right: 20px">{{
                article.userId
              }}</span>
              <span class="text-secondary fw-light"
                >{{ article.registerTime }} 조회 : {{ article.hit }}</span
              >
            </div>
          </div>
          <div class="col-md-4 align-self-center text-right">
            댓글 : {{ article.commentCnt }}
          </div>
        </div>
        <!-- 아이디, 등록시간, 댓글, 조회수 끝 -->
        <!-- 목록 , 수정 , 삭제 시작 -->
        <div style="text-align: right">
          <a style="padding-right: 10px; cursor: pointer" @click="moveList"
            >목록</a
          >
          <a
            style="padding-right: 10px; cursor: pointer"
            @click="moveModifyArticle"
            >수정</a
          >
          <a @click="deleteArticle" style="cursor: pointer">삭제</a>
        </div>
        <!-- 목록 , 수정 , 삭제 끝 -->
        <!-- 내용 시작 -->
        <hr class="my-3" />
        <div class="text-secondary">만남유형 : {{ article.category }}</div>
        <div class="text-secondary">일시 : {{ article.date }}</div>
        <div class="text-secondary">장소 : {{ article.place }}</div>
        <div class="text-secondary">모집 인원수 : {{ article.peopleNum }}</div>
        <div class="text-secondary">세부사항 : {{ article.content }}</div>
        <hr class="my-3" />
        <!-- 내용 끝 -->
        <!-- 댓글 시작 -->
        <section class="mt-5">
          <div class="card bg-light">
            <div class="card-body">
              <!-- 댓글 목록 -->
              <div class="ul_comment">
                <div v-for="comment in comments" :key="comment.commentNo">
                  <div class="d-flex mb-3">
                    <div class="flex-shrink-0" style="padding-right: 5px">
                      <img
                        class="rounded-circle"
                        src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                        alt="..."
                      />
                    </div>

                    <div class="fw-bold" style="padding-right: 20px">
                      {{ comment.userId }}
                    </div>
                    <div class="flex-grow-1">
                      {{ comment.content }}
                    </div>
                    <div>
                      {{ comment.registerTime }}
                      <a
                        class="deleteComment"
                        href="#1"
                        @click="deleteComment(comment.commentNo)"
                        style="color: red"
                      >
                        삭제
                      </a>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 댓글 등록 -->
              <br />
              <!-- <input
                type="text"
                class="form-control"
                id="userId"
                name="userId"
                placeholder="아이디입력"
                v-model="userId"
              /><br /> -->
              <div class="d-flex justify-content-between">
                <textarea
                  class="form-control col-10"
                  rows="3"
                  placeholder="댓글입력"
                  v-model="content"
                ></textarea>
                <div
                  class="commentBtn col-2"
                  style="
                    display: flex;
                    flex-direction: column;
                    justify-content: center;
                    align-items: center;
                  "
                >
                  <a
                    class=""
                    id="commentRegBtn"
                    @click="wrtieComment"
                    style="cursor: pointer"
                  >
                    등록
                  </a>
                </div>
              </div>
            </div>
          </div>
        </section>
        <br />
        <!-- 댓글 끝  -->
        <!-- 버튼 3개 시작 -->
        <!-- <div style="text-align: right">
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
        </div> -->
        <!-- 버튼 3개 끝 -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "BoardView",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      articleNo: "",
      article: [],
      comments: [],
      content: "",
      userId: "",
      title: "",
      commentCnt: "",
      category: "",
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
      fetch(
        `http://192.168.208.83:9018/board/api/view/${this.$route.params.articleNo}`,
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
      fetch(`http://192.168.208.83:9018/comment/${this.$route.params.articleNo}`, {
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
        userId: this.userInfo.userid,
        content: this.content,
      };
      fetch(`http://192.168.208.83:9018/comment`, {
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
            console.log();
            throw new Error("댓글 작성 실패");
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    },

    // 댓글 삭제
    deleteComment(commentNo) {
      fetch(
        `http://192.168.208.83:9018/comment/${this.article.articleNo}/${commentNo}`,
        {
          method: "DELETE",
          body: JSON.stringify({
            articleNo: this.articleNo,
            commentNo: this.commentNo,
          }),
        }
      )
        .then((response) => {
          if (response.ok) {
            this.$router.go();
            console.log("댓글 삭제 성공");
            console.log(commentNo);
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
      fetch(
        `http://192.168.208.83:9018/board/api/delete/${this.$route.params.articleNo}`,
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
