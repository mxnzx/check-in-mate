<template>
  <div class="row justify-content-center">
    <!-- 공지사항 제목 및 검색 폼 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-2 text-center">
        <span class="bg-light">공지사항</span>
      </h2>
      <div class="col-md-5 offset-4">
        <form class="d-flex" id="form-search" @submit.prevent="search">
          <input type="text" v-model="searchKeyword" placeholder="검색어..." />
          <button
            id="btn-search"
            class="btn btn-dark"
            type="button"
            @click="search"
          >
            검색
          </button>
        </form>
      </div>
    </div>
    <!-- 글 목록 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div
        class="row"
        style="width: 100%; padding: 1vw; justify-content: space-between"
      >
        <div class="col-md-8" style="display: flex; flex-direction: row">
          <img src="@/assets/images/etc/notice_icon.png" style="width: 30px" />
          <span>공지사항</span>
        </div>
        <a
          @click="moveWrite"
          style="cursor: pointer"
          v-if="userInfo && userInfo.userid === 'admin'"
          >글쓰기</a
        >
      </div>

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
          <!-- v-if빼서 무조건 페이징 버튼 출력 -->
          <notice-list-item
            v-for="article in pagedArticles"
            :key="article.articleNo"
            :article="article"
          ></notice-list-item>
        </tbody>
      </table>

      <!-- 페이징 컴포넌트 -->
      <notice-pagination
        :current-page="currentPage"
        :total-pages="totalPages"
        @page-change="handlePageChange"
      ></notice-pagination>
    </div>
  </div>
</template>

<script>
import NoticeListItem from "./NoticeListItem.vue";
import NoticePagination from "./NoticePagination.vue";

import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "NoticeList",
  components: {
    NoticeListItem,
    NoticePagination,
  },

  data() {
    return {
      articles: [], // 전체 글 목록 데이터
      displayedArticles: [], // 현재 페이지에 해당하는 글 목록 데이터
      currentPage: 1, // 현재 페이지 번호
      pageSize: 5, // 페이지 당 글 개수
      searchKeyword: "", // 검색 키워드
      userId: "",
    };
  },
  created() {
    this.fetchArticles();
  },
  // eslint-disable-next-line no-dupe-keys, vue/no-dupe-keys
  computed: {
    ...mapState(memberStore, ["userInfo"]),
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

  methods: {
    fetchArticles() {
      fetch("http://127.0.0.1:9018/notice/api/list")
        .then((response) => response.json())
        .then((data) => {
          this.articles = data;
          this.allArticles = data;
          this.displayedArticles = data; // 초기에 전체 글 목록을 표시
          console.log("articles fetcharticles >>>>>>>" + this.articles);
          console.log(
            "displayedArticles fetcharticles >>>>>>>" + this.displayedArticles
          );
        });
    },
    moveWrite() {
      this.$router.push("write");
    },
    search() {
      if (this.searchKeyword.trim() === "") {
        // 검색 키워드가 비어있는 경우, 전체 글 목록을 보여줍니다.
        this.displayedArticles = this.allArticles;
        this.currentPage = 1; // 페이지 초기화
      } else {
        console.log(this.searchKeyword);
        console.log(this.allArticles);
        // 검색 키워드가 있는 경우, 제목에 해당 검색어가 포함된 글을 필터링합니다.
        const filteredArticles = this.allArticles.filter((article) => {
          return (
            article.subject && article.subject.includes(this.searchKeyword)
          );
        });
        console.log(filteredArticles);
        this.displayedArticles = filteredArticles;
        this.currentPage = 1; // 페이지 초기화
      }
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
