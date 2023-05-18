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
          <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
            @click="hotplaceModal"
            style="cursor: pointer"
          >
            <b-card-text>
              This is a wider card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </b-card-text>
            <template #footer>
              <small class="text-muted">Last updated 3 mins ago</small>
            </template>
          </b-card>

          <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
          >
            <b-card-text>
              This card has supporting text below as a natural lead-in to
              additional content.
            </b-card-text>
            <template #footer>
              <small class="text-muted">Last updated 3 mins ago</small>
            </template>
          </b-card>

          <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
          >
            <b-card-text>
              This is a wider card with supporting text below as a natural
              lead-in to additional content. This card has even longer content
              than the first to show that equal height action.
            </b-card-text>
            <template #footer>
              <small class="text-muted">Last updated 3 mins ago</small>
            </template>
          </b-card>
        </b-card-group>
      </div>
      <br />
      <div>
        <b-card-group deck>
          <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
          >
            <b-card-text>
              This is a wider card with supporting text below as a natural
              lead-in to additional content. This content is a little bit
              longer.
            </b-card-text>
            <template #footer>
              <small class="text-muted">Last updated 3 mins ago</small>
            </template>
          </b-card>

          <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
          >
            <b-card-text>
              This card has supporting text below as a natural lead-in to
              additional content.
            </b-card-text>
            <template #footer>
              <small class="text-muted">Last updated 3 mins ago</small>
            </template>
          </b-card>

          <b-card
            title="Title"
            img-src="https://picsum.photos/300/300/?image=41"
            img-alt="Image"
            img-top
          >
            <b-card-text>
              This is a wider card with supporting text below as a natural
              lead-in to additional content. This card has even longer content
              than the first to show that equal height action.
            </b-card-text>
            <template #footer>
              <small class="text-muted">Last updated 3 mins ago</small>
            </template>
          </b-card>
        </b-card-group>
      </div>
      <!-- 사진 리스트 끝-->
      <hot-place-modal ref="HotPlaceModal"></hot-place-modal>
    </div>
  </div>
</template>

<script>
import HotPlaceModal from "@/components/hotplace/HotPlaceModal.vue";
export default {
  components: { HotPlaceModal },
  methods: {
    hotplaceModal() {
      this.$refs.HotPlaceModal.show();
    },
    moveWrite() {
      this.$router.push("hotplace/write");
    },
  },
};
</script>

<style></style>
