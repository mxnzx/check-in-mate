<template>
  <div id="app">
    <the-header></the-header>
    <router-view></router-view>
  </div>
</template>

<script>
import TheHeader from "./components/common/TheHeader.vue";

export default {
  components: {
    TheHeader,
  },
};
</script>

<style>
@import "@/assets/css/styles.css";

.backgroud {
  background-image: url("https://user-images.githubusercontent.com/77240765/235085316-343b04d2-037f-46ab-b2f2-bda4048c0484.jpeg");
  width: 100%;
  height: 100%;
}
#app {
  position: relative;
  height: 100%;
}
</style>
