<template>
  <b-container class="bv-example-row mt-3 text-center">
    <router-view></router-view>
    <the-footer></the-footer>
  </b-container>
</template>

<script>
import TheFooter from "@/components/common/TheFooter.vue";
export default {
  name: "AppUser",
  components: {
    TheFooter,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
