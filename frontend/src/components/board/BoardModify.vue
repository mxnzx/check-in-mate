<template>
  <div class="row justify-content-center">
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-3 shadow-sm bg-light text-center">
        <mark class="basic">글수정</mark>
      </h2>
    </div>
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div class="mb-3">
        <label for="subject" class="form-label">제목 : </label>
        <input
          type="text"
          class="form-control"
          id="subject"
          name="subject"
          v-model="article.subject"
        />
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용 : </label>
        <textarea
          class="form-control"
          id="content"
          name="content"
          rows="7"
          v-model="article.content"
        ></textarea>
      </div>
      <div class="col-auto text-center">
        <button
          type="button"
          id="btn-modify"
          class="btn btn-outline-primary mb-3"
          @click="modifyArticle"
        >
          글수정
        </button>
        <button
          type="button"
          id="btn-list"
          class="btn btn-outline-danger mb-3"
          @click="moveList"
        >
          목록으로이동...
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardModify",
  data() {
    return {
      articleNo: "",
      article: [],
    };
  },
  created() {
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
        alert("글 가져오기 성공");
      });
  },
  methods: {
    modifyArticle() {
      const modifiedData = {
        // 수정할 데이터 필드와 값 설정
        articleNo: this.article.articleNo,
        subject: this.article.subject,
        content: this.article.content,
      };
      console.log("moidfy articleNo :  " + this.article.articleNo);
      console.log("moidfy subject :  " + this.article.subject);
      console.log("moidfy content :  " + this.article.content);
      fetch(`http://localhost:9018/board/api/modify`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json", // 요청 본문의 타입을 JSON으로 설정
        },
        body: JSON.stringify(modifiedData), // JSON 형식으로 데이터를 문자열로 변환하여 요청 본문에 포함
      })
        .then((response) => {
          if (response.ok) {
            console.log(response);
            alert("글 수정 성공");
            this.$router.push("/board/api/list");
          } else {
            throw new Error("글 수정 실패");
          }
        })
        .catch((error) => {
          this.message = error.message;
        });
    },
    moveList() {
      this.$router.push("/board/api/list");
    },
  },
};
</script>

<style></style>
