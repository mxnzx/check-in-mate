<template>
  <div class="container">
    <div class="row justify-content-center">
      <!-- 공지사항 글쓰기 제목 시작 -->
      <!-- <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="basic">글쓰기</mark>
        </h2>
      </div> -->
      <!-- 여행정보 공유 게시판 글쓰기 제목 끝 -->
      <div class="col-lg-8 col-md-10 col-sm-12">
        <input type="hidden" name="action" value="write" />
        <!-- 작성자입력 시작 -->
        <div class="mb-3" style="display: none">
          <label for="userId" class="form-label">작성자 </label>
          <input
            type="text"
            class="form-control"
            id="userId"
            name="userId"
            placeholder="아이디입력"
          />
        </div>
        <!-- 작성자입력 끝 -->
        <!-- 제목입력 시작 -->
        <div class="mb-3">
          <label for="subject" class="form-label"></label>
          <input
            type="text"
            class="form-control"
            id="subject"
            name="subject"
            style="border: none"
            placeholder="제목입력"
            v-model="subject"
          />
        </div>
        <!-- 제목입력 끝 -->
        <hr class="my-3" />
        <!-- 내용입력 시작 -->
        <div class="mb-3">
          <textarea
            class="form-control"
            id="content"
            name="content"
            rows="7"
            style="border: none"
            placeholder="내용입력"
            v-model="content"
          ></textarea>
        </div>
        <!-- 내용입력 끝 -->
        <!-- 글작성, 목록 버튼 시작 -->
        <div class="col-auto text-right">
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
            style="margin-left: 5px"
            @click="moveList"
          >
            목록
          </button>
        </div>
        <!-- 글작성, 목록 버튼 끝 -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "NoticeWrite",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      userId: "",
      subject: "",
      content: "",
    };
  },
  methods: {
    // 글쓰기
    registArticle() {
      // const userId = this.userInfo.userid;

      let obj = {
        userId: this.userInfo.userid,
        subject: this.subject,
        content: this.content,
      };
      console.log("write userid >> " + this.userInfo.userid);
      fetch("http://192.168.208.83:9018/notice/api/write", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(obj),
      })
        .then((response) => {
          if (response.ok) {
            console.log("작성성공");
            console.log(obj);
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
    // 리스트로 이동
    moveList() {
      this.$router.push("list");
    },
  },
};
</script>

<style></style>
