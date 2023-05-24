<template></template>

<script>
import MyScrapListItem from "./MyScrapListItem.vue";
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "HotPlaceList",
  components: { MyScrapListItem },
  data() {
    return {
      articles: [],
    };
  },

  created() {
    fetch("http://127.0.0.1:9018/hotplace/list")
      .then((response) => response.json())
      .then((data) => {
        console.log("response >>" + this.response);
        console.log("data >>>" + data);
        this.articles = data;
        console.log(this.articles);
        console.log("list data" + data);
      });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    openModal(article) {
      // article 데이터를 모달 컴포넌트로 전달
      this.$refs.HotPlaceModal.show(article);
    },
    HotPlaceModal() {
      this.$refs.HotPlaceModal.show();
    },
  },
  mounted() {
    this.articles.push({});
  },
};
</script>

<style></style>
