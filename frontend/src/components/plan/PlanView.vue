<template>
  <div class="l-container">
    <div class="plan-main-title">
      <h2 class="my-3 py-2 text-center">제목입니다</h2>
    </div>
    <div class="plan-etc">
      <div>조회수</div>
      <div>스크랩</div>
      <div>작성자명</div>
      <div>등록날짜</div>
    </div>
    <div class="plan-image set-center-content">
      <div class="plan-image-item">
        이미지넣을곳
      </div>
    </div>
    <div class="plan-main-content set-center-content">
      <div>글내용입니다</div>
    </div>
    <!-- 데이별 컴포넌트 들고오기 -->

      
      
  </div>
</template>

<script>
import axios from "axios";
export default {
  components: {
    
  },
  data() {
    return {
      planArticleNo: "",
      planArticle:[],

    }
  },
  created() {
    this.getPlanArticle();

  },
  mounted() {

  },

  methods: {
    getPlanArticle() {
      axios
        .get(`http://127.0.0.1:9018/plan/view/3`)
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error("글 가져오기 실패");
          }
        })
        .then((data) => {
          this.planArticle = data.planArticle;
          console.log(this.planArticle);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    
  },


}
</script>

<style>
.l-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  flex-direction: column;

}
.plan-title {

}

.plan-etc {
  display: flex;
  justify-content: center;
}

.plan-image {
  height: 300px;
  align-items: center;
}

.plan-image-item {
  background-color: antiquewhite;
  width:600px;
  height: 100%;
}

.set-center-content {
  display: flex;
  justify-content: center;
}
</style>