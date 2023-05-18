<!-- 
    목록 뽑아내는 컴포넌트이다
    row1
        MY TRIP PLAN
    row2
        검색창
    row3
        col1 게시물1
        col2 게시물2
        col3 게시물3
 -->

<template>
    <div>
        <plan-list-item></plan-list-item>
    </div>

</template>

<script>
import PlanListItem from "./PlanListItem.vue";

export default {
    name: "PlanList",
    components: {
        PlanListItem,
    },
    data() {
        return {
            articles:[]
        }
    }
}
</script>

<style>

</style>