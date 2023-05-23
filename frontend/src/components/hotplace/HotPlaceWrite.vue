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
        <!-- <div class="mb-3">
          <label for="userId" class="form-label">작성자 </label>
          <input
            type="text"
            class="form-control"
            id="userId"
            name="userId"
            placeholder="아이디입력"
            v-model="userId"
          />
        </div> -->
        <!-- 작성자입력 끝 -->
        <!-- 제목입력 시작 -->
        <div class="mb-3">
          <label for="title" class="form-label"></label>
          <input
            type="text"
            class="form-control"
            id="title"
            name="title"
            style="border: none"
            placeholder="제목입력"
            v-model="title"
            required
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
            required
          ></textarea>
        </div>
        <div class="mb-3">
          <input
            type="file"
            class="form-control border"
            id="upfile"
            name="upfile"
            multiple="multiple"
            ref="fileInput"
            required
          />
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
  name: "HotplaceWrite",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      userid: "",
      title: "",
      content: "",
    };
  },
  methods: {
    registArticle() {
      let obj = {
        userid: this.userInfo.userid,
        title: this.title,
        content: this.content,
      };
      // 파일 입력 요소 가져오기
      const fileInput = this.$refs.fileInput;
      // FormData 객체 생성
      const formData = new FormData();

      // 파일이 선택되었을 때 FormData에 추가
      if (fileInput.files.length > 0) {
        for (let i = 0; i < fileInput.files.length; i++) {
          const file = fileInput.files[i];
          formData.append("upfile", file);
        }
      }
      // 나머지 데이터도 FormData에 추가
      for (let key in obj) {
        formData.append(key, obj[key]);
      }
      console.log(formData);
      fetch(`http://127.0.0.1:9018/hotplace/write`, {
        method: "POST",
        body: formData,
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push("/hotplace");
          } else {
            throw new Error("핫플 등록 실패");
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    },

    // 목록으로 이동
    moveList() {
      this.$router.push("/hotplace");
    },
  },
};
</script>

<style></style>
