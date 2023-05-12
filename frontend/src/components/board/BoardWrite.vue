<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="basic">글쓰기</mark>
        </h2>
      </div>
      <div class="col-lg-8 col-md-10 col-sm-12">
        <form id="form-register" method="POST" action="">
          <input type="hidden" name="action" value="write" />
          <div class="mb-3">
            <label for="userId" class="form-label">작성자 </label>
            <input
              type="text"
              class="form-control"
              id="userId"
              name="userId"
              placeholder="아이디입력"
              v-model="userId"
            />
          </div>
          <div class="mb-3">
            <label for="subject" class="form-label">제목 : </label>
            <input
              type="text"
              class="form-control"
              id="subject"
              name="subject"
              placeholder="제목입력"
              v-model="subject"
            />
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">내용 : </label>
            <textarea
              class="form-control"
              id="content"
              name="content"
              rows="7"
              placeholder="내용입력"
              v-model="content"
            ></textarea>
          </div>
          <div class="col-auto text-center">
            <button
              type="button"
              id="btn-register"
              class="btn btn-outline-primary mb-3"
              @click="registArticle"
            >
              글작성
            </button>
            <button
              type="reset"
              class="btn btn-outline-danger mb-3"
              @click="moveList"
            >
              목록
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardWrite",
  data() {
    return {
      userId: "",
      subject: "",
      content: "",
    };
  },
  methods: {
    registArticle() {
      let obj = {
        userId: this.userId,
        subject: this.subject,
        content: this.content,
      };
      console.log("write userid >> " + this.userId);
      fetch("http://localhost:9018/board/api/write", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(obj),
      })
        .then((response) => {
          if (response.ok) {
            console.log("작성성공");
            console.log(this.userId);
            console.log(this.subject);
            console.log(this.content);
            this.$router.push("list");
          } else {
            console.log("실패");
            throw new Error("글 등록 실패");
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    },

    moveList() {
      this.$router.push("list");
    },
  },
};
</script>

<style></style>
