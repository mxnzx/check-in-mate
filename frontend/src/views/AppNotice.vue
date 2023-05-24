<template>
  <div>
    <router-view></router-view>
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheFooter from "@/components/common/TheFooter.vue";
export default {
  components: {
    TheFooter,
  },
};
</script>

<style></style>
