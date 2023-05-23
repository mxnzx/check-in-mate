<template>
  <div style="text-align: center">
    <button @click="previousPage" :disabled="currentPage === 1">이전</button>
    <button
      v-for="pageNumber in pageNumbers"
      :key="pageNumber"
      @click="goToPage(pageNumber)"
      :class="{ active: pageNumber === currentPage }"
    >
      {{ pageNumber }}
    </button>
    <button @click="nextPage" :disabled="currentPage === totalPages">
      다음
    </button>
  </div>
</template>

<script>
export default {
  props: {
    currentPage: {
      type: Number,
      required: true,
    },
    totalPages: {
      type: Number,
      required: true,
    },
  },
  computed: {
    pageNumbers() {
      return Array.from({ length: this.totalPages }, (_, index) => index + 1);
    },
  },
  methods: {
    previousPage() {
      if (this.currentPage > 1) {
        this.$emit("page-change", this.currentPage - 1);
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.$emit("page-change", this.currentPage + 1);
      }
    },
    goToPage(pageNumber) {
      console.log(pageNumber);
      if (pageNumber >= 1 && pageNumber <= this.totalPages) {
        this.$emit("page-change", pageNumber);
      }
    },
  },
};
</script>

<style>
button.active {
  background-color: black;
  color: white;
}
</style>
