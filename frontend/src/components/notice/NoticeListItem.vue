<template>
  <tr class="text-center">
    <td>{{ article.articleNo }}</td>
    <td>
      <router-link :to="`view/${article.articleNo}`" class="board-subject">{{
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
  name: "NoticeListItem",
  props: {
    article: Object,
  },
};
</script>

<style></style>
