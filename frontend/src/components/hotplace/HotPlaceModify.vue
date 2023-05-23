<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <input type="hidden" name="action" value="write" />
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
        <div class="mb-3">
          <input
            type="file"
            class="form-control border"
            id="upfile"
            name="upfile"
            multiple="multiple"
            ref="fileInput"
          />
        </div>

        <!-- 내용입력 끝 -->
        <!-- 글작성, 목록 버튼 시작 -->
        <div class="col-auto text-right">
          <button
            type="button"
            id="btn-register"
            class="btn btn-outline-primary mb-3"
            @click="modifyArticle"
          >
            글수정
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
  props: {
    article: {
      type: Object,
      required: true,
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      userid: "",
      title: "",
      content: "",
      articleno: "",
    };
  },
  created() {
    this.title = this.article.title; // article 프롭스의 title 값을 data 속성에 대입
    this.content = this.article.content; // article 프롭스의 content 값을 data 속성에 대입
    this.articleno = this.article.articleno;
    console.log("hotplace modidfy title >>>>>>>> " + this.title);
    console.log("hotplace modidfy content >>>>>>>>" + this.content);
    console.log("hotplace modidfy articleno >>>>>>>>" + this.articleno);
  },
  methods: {
    modifyArticle() {
      this.deleteArticle();
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
            console.log(obj);
            this.$router.push("/hotplace");
          } else {
            throw new Error("핫플 수정 실패");
          }
        })
        .catch((error) => {
          alert(error.message);
        });
    },
    // 삭제하기
    deleteArticle() {
      fetch(`http://127.0.0.1:9018/hotplace/delete/${this.articleno}`, {
        method: "DELETE",
        body: JSON.stringify({
          articleno: this.articleno,
        }),
      })
        .then((response) => {
          if (response.ok) {
            alert("글 삭제 성공");
          } else {
            throw new Error("글 삭제 실패");
          }
        })
        .catch((error) => {
          this.message = error.message;
        });
    },
    moveList() {
      this.$router.push("/hotplace");
    },
  },
};
</script>

<style></style>
