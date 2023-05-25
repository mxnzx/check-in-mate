<template>
  <div>
    <b-card
      :title="article.title"
      :img-src="getImageSrc()"
      img-alt="Image"
      img-top
      style="cursor: pointer"
      :style="{ width: '300px' }"
    >
    </b-card>
    <br />
    <!-- <hot-place-modal ref="HotPlaceModal"></hot-place-modal> -->
  </div>
</template>

<script>
export default {
  name: "MyScrapListItem",
  components: {},
  data() {
    return {
      articles: [],
    };
  },
  props: {
    article: Object,
  },
  methods: {
    getImageSrc() {
      // 이미지 파일 경로를 반환합니다.
      console.log(this.article);
      return (
        "http://127.0.0.1:9018/hotplace/image/" +
        this.article.savefile[0].savefile
      );
    },
  },
};
</script>

<style></style>
