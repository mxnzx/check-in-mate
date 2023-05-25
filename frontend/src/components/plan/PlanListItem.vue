<template>
  <div>
    <b-card no-body class="overflow-hidden" style="max-width: 540px">
      <b-row no-gutters>
        <b-col md="6">
          <b-card-img
            :src="getImageSrc()"
            alt="Image"
            class="rounded-0"
            @click="moveView"
          ></b-card-img>
        </b-col>
        <b-col md="6">
          <b-card-body :title="article.mainTitle">
            <b-card-text>
              {{ article.mainContent }}
            </b-card-text>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>
    <br/>
  </div>
</template>

<script>
export default {
  props: {
    article: Object,
  },  
  methods: {
    showPlanView() {
      console.log("글 상세보기 띄워라");
    },
    getImageSrc() {
      // 이미지 파일 경로를 반환합니다.
      console.log(this.article.img);
      return "http://127.0.0.1:9018/plan/image/" + this.article.img;
    },
    moveView() {
      console.log(this.article.articleNo);
      this.$router.push("/myplan/view/" + this.article.articleNo)
    },
  },
};
</script>

<style></style>
