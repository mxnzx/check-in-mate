<template>
  <div class="regist">
    <h1 class="underline">SSAFY 글 상세보기</h1>
    <div class="regist_form">
      <label> 글번호</label>
      <div class="view">{{ article.articleNo }}</div>
      <label> 글제목</label>
      <div class="view">{{ article.subject }}</div>
      <label> 작성자</label>
      <div class="view">{{ article.userId }}</div>
      <label> 조회수</label>
      <div class="view">{{ article.hit }}</div>
      <label> 작성시간</label>
      <div class="view">{{ article.registerTime }}</div>
      <label> 내용</label>
      <div class="view">{{ article.content }}</div>

      <div style="padding-top: 15px">
        <router-link :to="'/modify/' + article.articleNo" class="btn"
          >수정</router-link
        >
        <router-link :to="`/delete/${article.articleNo}`" class="btn"
          >삭제</router-link
        >
        <!-- <router-link :to="`/view/${article.articleno}`"> -->
        <router-link to="/list" class="btn">목록</router-link>
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
      article: {},
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
          this.article = data;
          console.log(data.article);
          console.log(this.$route.params.articleNo);
          alert("글 가져오기 성공");
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style></style>
