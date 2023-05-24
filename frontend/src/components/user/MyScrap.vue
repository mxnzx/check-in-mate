<template>
  <div class="row justify-content-center">
    <!-- 핫플레이스 제목 시작 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <h2 class="my-3 py-2 text-center">
        <span class="bg-light">HOT PLACE</span>
      </h2>
    </div>
    <!-- 핫플레이스 제목 끝 -->
    <div class="col-lg-8 col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <!-- 글쓰기 앵커 시작 -->
        <div class="col-md-12" style="text-align: end">
          <a @click="moveWrite" style="cursor: pointer" v-if="userInfo">
            등록하기
          </a>
        </div>
        <!-- 글쓰기 앵커 끝 -->
      </div>
      <!-- 사진 리스트 시작-->
      <div>
        <b-card-group deck style="justify-content: center">
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
export default {};
</script>

<style></style>
