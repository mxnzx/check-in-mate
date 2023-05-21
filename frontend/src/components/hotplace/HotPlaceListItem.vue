<template>
  <div>
    <b-card
      :title="article.title"
      :img-src="getImageSrc()"
      img-alt="Image"
      img-top
      @click="hotplaceModal"
      style="cursor: pointer"
      :style="{ width: '300px' }"
    >
      <b-card-text>
        {{ article.content }}
      </b-card-text>
      <template #footer>
        <small class="text-muted">{{ article.registertime }}</small>
      </template>
    </b-card>
    <br />
    <hot-place-modal ref="HotPlaceModal"></hot-place-modal>
  </div>
</template>

<script>
import HotPlaceModal from "@/components/hotplace/HotPlaceModal.vue";

export default {
  name: "HotPlaceListItem",
  components: {
    HotPlaceModal,
  },
  computed: {
    // imageSrc() {
    //   // 이미지 파일의 상대 경로를 반환합니다.
    //   console.log(
    //     "../../../../backend/src/main/webapp/upload/hotplace/0520" +
    //       this.article.savefile
    //   );
    //   return require("http://localhost:9018/hotplace/image/b4e39aa1-84a4-435b-a499-a508140f04c9.png");
    // },
  },
  props: {
    article: Object,
  },
  methods: {
    hotplaceModal() {
      this.$emit("open-modal", this.article.articleno);
      console.log("모달 articleno >>>>>>>>>>" + this.article.articleno);
    },
    getImageSrc() {
      // 이미지 파일 경로를 반환합니다.
      return "http://localhost:9018/hotplace/image/" + this.article.savefile;
    },
  },
};
</script>

<style></style>
