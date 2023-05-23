<template>
  <div class="row justify-content-center">
    <!-- 여행정보 공유 게시판 제목 시작 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-2 text-center">
        <span class="bg-light">HOT PLACE</span>
      </h2>
    </div>
    <!-- 여행정보 공유 게시판 제목 끝 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <!-- 글쓰기 앵커 시작 -->
        <div class="col-md-12" style="text-align: end">
          <a @click="moveWrite" style="cursor: pointer"> 등록하기 </a>
        </div>
        <!-- 글쓰기 앵커 끝 -->
      </div>
      <!-- 사진 리스트 시작-->
      <div>
        <b-card-group deck>
          <HotPlaceListItem
            v-for="article in articles"
            :key="article.articleno"
            :article="article"
            @open-modal="openModal"
          >
          </HotPlaceListItem>
        </b-card-group>
      </div>
      <br />
      <!-- 사진 리스트 끝-->
      <!-- <hot-place-modal ref="HotPlaceModal"></hot-place-modal> -->
    </div>
  </div>
</template>

<script>
// import HotPlaceModal from "@/components/hotplace/HotPlaceModal.vue";
import HotPlaceListItem from "./HotPlaceListItem.vue";
export default {
  name: "HotPlaceList",
  components: { HotPlaceListItem },
  data() {
    return {
      articles: [],
    };
  },

  created() {
    fetch("http://192.168.208.83:9018/hotplace/list")
      .then((response) => response.json())
      .then((data) => {
        console.log("response >>" + this.response);
        console.log("data >>>" + data);
        this.articles = data;
        console.log(this.articles);
        console.log("list data" + data);
      });
  },
  methods: {
    openModal(article) {
      // article 데이터를 모달 컴포넌트로 전달
      this.$refs.HotPlaceModal.show(article);
    },
    HotPlaceModal() {
      this.$refs.HotPlaceModal.show();
    },
    moveWrite() {
      this.$router.push("hotplace/write");
    },
  },
  mounted() {
    this.articles.push({});
  },
};
</script>

<style></style>
