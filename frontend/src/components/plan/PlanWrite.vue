<!-- 
    하나의 큰 포스팅 쓰기 페이지이다.
    row1
        [지역명]제목
    row2
        PlanWriteByDate컴포넌트가 하나씩 붙는다
    row3
        '날짜추가하기' 버튼 (row3 위로 컴포넌트가 붙는다) 
 -->

<template>
    <plan-write-by-date></plan-write-by-date>
</template>

<script>
import PlanWriteByDate from "./PlanWriteByDate.vue";

export default {
    name: "PlanWrite",
    components: {
        PlanWriteByDate,
    },
    data() {
        return {
            articleByDate: [],
        }
    }


}
</script>

<style>

</style>