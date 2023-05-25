<template>
  <div class="row justify-content-center">
    <!-- 여행정보 공유 게시판 제목 시작 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-2 text-center">
        <span class="bg-light">동행 모집</span>
      </h2>
      <!-- 검색하기 시작 -->
      <div class="col-md-7 offset-3">
        <form class="d-flex" id="form-search" action="">
          <input type="hidden" name="action" value="notice" />
          <input type="hidden" name="pgno" value="1" />
          <select
            name="key"
            id="key"
            class="form-select form-select-sm ms-5 me-1 w-50"
            aria-label="검색조건"
            v-model="searchKey"
          >
            <option value="">검색조건</option>
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
              v-model="searchKeyword"
            />
            <button
              id="btn-search"
              class="btn btn-dark"
              type="button"
              @click="search"
            >
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
          <a @click="moveWrite" style="cursor: pointer" v-if="userInfo">
            글쓰기
          </a>
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
            v-for="article in pagedArticles"
            :key="article.articleNo"
            :article="article"
          ></board-list-item>
        </tbody>
      </table>
      <!-- 리스트 테이블 끝-->
      <board-pagination
        :current-page="currentPage"
        :total-pages="totalPages"
        @page-change="handlePageChange"
      ></board-pagination>
      <board-write ref="BoardWrite"></board-write>
    </div>
  </div>
</template>

<script>
import BoardListItem from "./BoardListItem.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardPagination from "./BoardPagination.vue";
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
    BoardWrite,
    BoardPagination,
  },
  data() {
    return {
      articles: [],
      displayedArticles: [],
      currentPage: 1, // 현재 페이지 번호
      pageSize: 5, // 페이지 당 글 개수
      searchKeyword: "", // 검색 키워드
      searchKey: "",
    };
  },

  // eslint-disable-next-line no-dupe-keys, vue/no-dupe-keys
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),

    // 현재 페이지에 해당하는 글 목록 데이터 계산
    pagedArticles() {
      const startIdx = (this.currentPage - 1) * this.pageSize;
      const endIdx = startIdx + this.pageSize;
      //this.displayedArticles = this.displayedArticles.slice(startIdx, endIdx);
      return this.displayedArticles.slice(startIdx, endIdx);
    },
    // 전체 페이지 수 계산
    totalPages() {
      const pageCount = Math.ceil(
        this.displayedArticles.length / this.pageSize
      );
      return Math.max(pageCount, 1); // 게시글이 10개 미만일 때 최소값 1로 설정
    },

    // 현재 페이지의 시작 인덱스 계산
    startIndex() {
      return (this.currentPage - 1) * this.pageSize;
    },

    // 현재 페이지의 끝 인덱스 계산
    endIndex() {
      return this.currentPage * this.pageSize;
    },

    // 페이지 번호 목록 계산
    pageNumbers() {
      const pageCount = Math.ceil(
        this.displayedArticles.length / this.pageSize
      );
      return Array.from({ length: pageCount }, (_, index) => index + 1);
    },
  },

  // 리스트 가져오기
  // created() {
  //   fetch("http://127.0.0.1:9018/board/api/list")
  //     .then((response) => response.json())
  //     .then((data) => {
  //       console.log("respone >>" + this.response);
  //       console.log("data>> " + data);
  //       this.articles = data;
  //       this.allArticles = data;
  //       this.displayedArticles = data;
  //       console.log("list data" + data);
  //       console.log("articles fetcharticles >>>>>>>" + this.articles);
  //       console.log(
  //         "displayedArticles fetcharticles >>>>>>>" + this.displayedArticles
  //       );
  //     });
  //   console.log("동행찾기 데이터 : >>> " + this.$route.query.searchKeyword);
  //   const searchMateword = this.$route.query.searchKeyword;
  //   if (searchMateword) {
  //     this.searchMate();
  //   }
  // },
  async created() {
    try {
      const response = await fetch("http://127.0.0.1:9018/board/api/list");
      const data = await response.json();
      this.articles = data;
      this.allArticles = data;
      this.displayedArticles = data;
      console.log("list data", data);
      console.log("articles fetcharticles >>>>>>>", this.articles);
      console.log(
        "displayedArticles fetcharticles >>>>>>>",
        this.displayedArticles
      );

      const searchMateword = this.$route.query.searchKeyword;
      if (searchMateword) {
        this.searchMate();
      }
    } catch (error) {
      console.error("Error occurred while fetching data:", error);
    }
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),

    // 동행찾기
    searchMate() {
      console.log("searchMate 함수 실행");
      console.log("mate allArticles" + this.allArticles);
      let filteredArticles;
      filteredArticles = this.allArticles.filter((article) => {
        return (
          article.title &&
          article.title.includes(this.$route.query.searchKeyword)
        );
      });
      this.displayedArticles = filteredArticles;
      console.log(" searchMate data" + this.displayedArticles);
    },

    // 검색
    search() {
      console.log("검색버튼 클릭");
      if (this.searchKeyword.trim() === "") {
        // 검색 키워드가 비어있는 경우, 전체 글 목록을 보여줌
        this.displayedArticles = this.allArticles;
        this.currentPage = 1; // 페이지 초기화
      } else {
        // 검색 키워드가 있는 경우, 검색 조건에 따라 필터링
        let filteredArticles;
        console.log("검색 this.allArticles >>" + this.allArticles);
        switch (this.searchKey) {
          case "article_no":
            filteredArticles = this.allArticles.filter((article) => {
              return article.articleNo.toString().includes(this.searchKeyword);
            });
            break;
          case "title":
            filteredArticles = this.allArticles.filter((article) => {
              return (
                article.title && article.title.includes(this.searchKeyword)
              );
            });
            break;
          case "user_id":
            filteredArticles = this.allArticles.filter((article) => {
              return (
                article.userId && article.userId.includes(this.searchKeyword)
              );
            });
            break;
          default:
            filteredArticles = this.allArticles;
            break;
        }
        this.displayedArticles = filteredArticles;
        this.currentPage = 1; // 페이지 초기화
      }
    },
    // 글쓰기로 이동
    moveWrite() {
      this.$refs.BoardWrite.show();
    },
    handlePageChange(page) {
      //console.log(page);
      console.log(this.pageSize);
      this.currentPage = page;
    },
  },
};
</script>

<style></style>
