<template>
  <div class="row justify-content-center">
    <!-- 여행정보 공유 게시판 제목 시작 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-2 text-center">
        <span class="bg-light">동행 구하기</span>
      </h2>
      <!-- 검색하기 시작 -->
      <div class="col-md-7 offset-3">
        <!-- <select
            name="sort_list"
            id="sort_list"
            class="form-select form-select-sm ms-5 me-1 w-50"
            aria-label="검색조건"
          >
            <option selected>게시글 정렬</option>
            <option value="notice">최신 순</option>
            <option value="sort_hit">조회수 순</option>
          </select> -->
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
            <option value="title">제목</option>
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
            <button id="btn-search" class="btn btn-dark" type="button">
              검색
            </button>
          </div>
        </form>
      </div>
      <!-- 검색하기 끝 -->
    </div>
    <!-- 여행정보 공유 게시판 제목 끝 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <!-- 글쓰기 앵커 시작 -->
        <div class="col-md-12" style="text-align: end">
          <a @click="moveWrite" style="cursor: pointer"> 글쓰기 </a>
        </div>
        <!-- 글쓰기 앵커 끝 -->
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
          <board-list-item
            v-for="article in articles"
            :key="article.articleNo"
            :article="article"
          ></board-list-item>
        </tbody>
      </table>
      <!-- 리스트 테이블 끝-->
      <board-write ref="BoardWrite"></board-write>
    </div>
  </div>
</template>

<script>
import BoardListItem from "./BoardListItem.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
export default {
  name: "BoardList",
  components: {
    BoardListItem,
    BoardWrite,
  },
  data() {
    return {
      articles: [],
    };
  },
  // 리스트 가져오기
  created() {
    fetch("http://localhost:9018/board/api/list")
      .then((response) => response.json())
      .then((data) => {
        console.log("respone >>" + this.response);
        console.log("data>> " + data);
        this.articles = data;
        console.log("list data" + data);
      });
  },
  // 글쓰기로 이동
  methods: {
    moveWrite() {
      this.$refs.BoardWrite.show();
    },
  },
};
</script>

<style></style>
