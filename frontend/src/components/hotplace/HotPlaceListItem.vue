<template>
  <div>
    <b-card
      :title="article.title"
      img-src="https://picsum.photos/300/300/?image=41"
      img-alt="Image"
      img-top
      @click="hotplaceModal"
      style="cursor: pointer"
    >
      <b-card-text>
        {{ article.content }}
      </b-card-text>
      <template #footer>
        <small class="text-muted">Last updated 3 mins ago</small>
      </template>
    </b-card>
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
  props: {
    article: Object,
  },
  methods: {
    hotplaceModal() {
      this.$emit("open-modal", this.article.articleno);
      console.log("모달 articleno >>>>>>>>>>" + this.article.articleno);
    },
  },
};
</script>

<style></style>
