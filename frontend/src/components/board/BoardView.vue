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
            <!-- ${article.articleNo}. ${board.subject} -->
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
              <!-- 댓글시작 -->
              <div>
                <!-- <ul id="commentUL"></ul> -->
              </div>

              <div>
                <li v-for="comment in comments" :key="comment.commentNo">
                  <ul>댓글번호 : {{comment.commentNo}} </ul>
                  <ul>작성자 : {{comment.userId}}</ul>
                  <ul>내용 : {{comment.comment}}</ul>
                  <ul>등록시간 : {{comment.registerTime}}</ul>
                  <ul><a href="#1" @click="deleteComment(comment.commentNo)">삭제</a></ul>
                  <br>
                </li>
              </div>
              <div class="mb-3">
                <label for="userId" class="form-label">댓글 작성자 입력 </label>
                <input
                type="text"
                class="form-control"
                id="userId"
                name="userId"
                placeholder="아이디입력"
                v-model="userId"
                />
              </div>              
              <div>
                <textarea
                  id="content"
                  rows="5"
                  style="width: 100%"
                  placeholder="댓글 입력"
                  v-model="comment"
                ></textarea>
              </div>
              <div style="text-align: right">
                <button id="commentRegBtn" class="btn btn-outline-primary mb-3" @click="wrtieComment">
                  댓글 등록
                </button>
              </div>
            </div>
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
  <!-- <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleNo}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userId}</div><div>${article.registerTime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div class="view">{{ article.content }}</div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container> -->
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
      fetch(
        `http://localhost:9018/board/api/view/${this.$route.params.articleNo}`,
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
    getComment(){
      fetch(
        `http://localhost:9018/comment/${this.$route.params.articleNo}`,{
          method: "GET",
        }
      )
      .then((response) =>{
        if(response.ok){
          return response.json();
        }
        else{
          throw new Error("댓글 가져오기 실패");
        }
      })
      .then((data) =>{
        this.comments = data;
        console.log(data);
        console.log(this.comments);
        console.log("댓글 가져오기 "  + data);
        console.log("댓글 가져오기 성공");
      })
      .catch((error) =>{
        console.error(error);
      });
    },
    // 댓글 쓰기
    wrtieComment(){
      let obj = {
        articleNo: this.article.articleNo,
        userId: this.article.userId,
        comment: this.comment,
      }
      fetch(
        `http://localhost:9018/comment`,{
          method: "POST",
          headers:{
            "Content-Type": "application/json",
          },
          body: JSON.stringify(obj),
        }
      )
      .then((response) =>{
        if(response.ok){
          this.$router.go();
        }
        else{
          throw new Error("댓글 작성 실패");
        }
      })
      .catch((error) =>{
        alert(error.message);
      })
    },

    // 댓글 삭제
  deleteComment(commentNo){
    fetch(
      `http://localhost:9018/comment/${this.article.articleNo}/${commentNo}`,
      {
      method: "DELETE",
      body: JSON.stringify({
        articleNo: this.articleNo,
        commentNo: this.commentNo,
      }),
      }
    )
    .then((response) =>{
      if(response.ok){
        this.$router.go();
        console.log("댓글 삭제 성공");
      }
      else{
        throw new Error("댓글 삭제 실패");
      }
    })
    .catch((error) =>{
      this.message = error.message;
    })
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
        `http://localhost:9018/board/api/delete/${this.$route.params.articleNo}`,
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
