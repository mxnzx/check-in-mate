<template>
  <div class="row justify-content-center">
    <!-- 핫플레이스 제목 시작 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-2 text-center">
        <span class="bg-light">My Scrap</span>
      </h2>
    </div>
    <!-- 핫플레이스 제목 끝 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <!-- 글쓰기 앵커 시작 -->

        <!-- 글쓰기 앵커 끝 -->
      </div>
      <!-- 사진 리스트 시작-->
      <div>
        <b-card-group deck style="justify-content: center">
          <my-scrap-list-item
            v-for="article in articles"
            :key="article.articleno"
            :article="article"
          >
          </my-scrap-list-item>
        </b-card-group>
      </div>
      <br />
      <!-- 사진 리스트 끝-->
      <!-- <hot-place-modal ref="HotPlaceModal"></hot-place-modal> -->
    </div>
  </div>
</template>

<script>
import MyScrapListItem from "./MyScrapListItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "MyScrap",
  components: { MyScrapListItem },
  data() {
    return {
      articles: [],
      userId: "",
    };
  },

  mounted() {
    console.log("스크랩 유저아이디" + this.userInfo.userid);
    this.userId = this.userInfo.userid;
    console.log(this.userId);
    fetch(`http://127.0.0.1:9018/hotplace/scrap/${this.userId}`)
      .then((response) => response.json())
      .then((data) => {
        console.log("response >>" + this.response);
        console.log("data >>>" + data);
        this.articles = data;
        console.log(this.articles);
        console.log("list data" + data);
      });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  // methods: {
  // },
  // mounted() {
  //   this.articles.push({});
  // },
};
</script>

<style></style>
