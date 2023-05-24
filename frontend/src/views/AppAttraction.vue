<template>
  <div>
    <attraction-map-vue></attraction-map-vue>
    <the-footer></the-footer>
  </div>
</template>

<script>
import AttractionMapVue from "@/components/attr/AttractionMap.vue";
import TheFooter from "@/components/common/TheFooter.vue";
export default {
  name: "AppAttraction",
  components: {
    AttractionMapVue,
    TheFooter,
  },
};
</script>

<style></style>
