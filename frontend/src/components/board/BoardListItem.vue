<template>
  <tr>
    <td>{{ article.articleNo }}</td>
    <td>
      <router-link :to="`view/${article.articleNo}`">{{
        article.subject
      }}</router-link>
    </td>
    <td>{{ article.userName }}</td>
    <td>{{ article.hit }}</td>
    <td>{{ article.registerTime }}</td>
  </tr>
</template>

<script>
export default {
  name: "BoardListItem",
  props: {
    article: Object,
  },
};
</script>

<style></style>
