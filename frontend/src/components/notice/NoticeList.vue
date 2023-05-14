<template>
  <div class="row justify-content-center">
    <!-- 공지사항 제목 시작 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-3 shadow-sm bg-light text-center">
        <span class="bg-light">공지사항</span>
      </h2>
    </div>
    <!-- 공지사항 제목 끝 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <!-- 글쓰기 버튼 시작 -->
        <div class="col-md-2 text-start">
          <button
            type="button"
            id="btn-mv-register"
            class="btn btn-outline-primary btn-sm"
            @click="moveWrite"
          >
            글쓰기
          </button>
        </div>
        <!-- 글쓰기 버튼 끝 -->
        <!-- 검색하기 시작 -->
        <!-- <div class="col-md-7 offset-3">
          <select
            name="sort_list"
            id="sort_list"
            class="form-select form-select-sm ms-5 me-1 w-50"
            aria-label="검색조건"
          >
            <option selected>게시글 정렬</option>
            <option value="notice">최신 순</option>
            <option value="sort_hit">조회수 순</option>
          </select>
          <form class="d-flex" id="form-search" action="">
            <input type="hidden" name="action" value="notice" />
            <input type="hidden" name="pgno" value="1" />
            <select
              name="key"
              id="key"
              class="form-select form-select-sm ms-5 me-1 w-50"
              aria-label="검색조건"
            >
              <option selected>검색조건</option>
              <option value="article_no">글번호</option>
              <option value="subject">제목</option>
              <option value="user_id">작성자</option>
            </select>
            <div class="input-group input-group-sm">
              <input
                type="text"
                name="word"
                id="word"
                class="form-control"
                placeholder="검색어..."
              />
              <button id="btn-search" class="btn btn-dark" type="button">검색</button>
            </div>
          </form>
        </div> -->
        <!-- 검색하기 끝 -->
      </div>
      <!-- 리스트 테이블 시작-->
      <table class="table table-hover" id="article-list">
        <thead>
          <tr class="text-center">
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody>
          <notice-list-item
            v-for="article in articles"
            :key="article.articleNo"
            :article="article"
          ></notice-list-item>
        </tbody>
      </table>
      <!-- 리스트 테이블 끝-->
    </div>
  </div>
</template>

<script>
import NoticeListItem from "./NoticeListItem.vue";
export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
  },
  data() {
    return {
      articles: [],
    };
  },
  // 글목록 얻어오기
  created() {
    fetch("http://localhost:9018/notice/api/list")
      .then((response) => response.json())
      .then((data) => {
        console.log("respone >>" + this.response);
        console.log("data>> " + data);
        this.articles = data;
        console.log("list data" + data);
      });
  },
  methods: {
    // 글쓰기로 이동
    moveWrite() {
      this.$router.push("write");
    },
  },
};
</script>

<style></style>
