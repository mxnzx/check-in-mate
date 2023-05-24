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

<style>
.container {
  position: relative;
}
.footer {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
}
</style>
