<template>
  <b-modal v-model="showAttractionDetailModal" id="signUpModal" :title="attractionData.title" :title-class="'my-custom-title'" hide-footer>
    <div class="modal-body" style="padding: 1.5rem; padding-top: 5px;">
      <!-- <div class="row mb-1 d-flex justify-content-center modal-title mr-0" style="margin-right: 0px;margin-left: 0px;">
        {{ attractionData.title }}
      </div> -->
      <div class="row mb-3 text-center">
        <img :src="attractionData.imgUrl" alt="이미지 없음" class="modal-image">
      </div>

      
      <div class="row mb-1">
        {{ attractionData.attractionOverview }}
      </div>
    </div>
    <div class="modal-footer" style="align:center">
      <b-button id="btn-join" type="button" variant="primary" @click="checkValue">동행모으기</b-button>
      <b-button type="button" variant="danger" @click="hideModal">동행찾기</b-button>
    </div>
  </b-modal>
</template>

<script>
import axios from "axios";
export default {
  name: "AttractionDetailModal",
  data() {
    return {
      showAttractionDetailModal: false,
      selectContentId: null,
      attractionData: {
        title: null,
        attractionOverview: null,
        imgUrl: null,
      }
    }
  },
  methods: {
    show(selectContentId) {
      this.selectContentId = selectContentId;
      this.showAttractionDetailModal = true;
      this.getAttractionDescription();
    },
    getAttractionDescription() {
      axios
        .get(`http://127.0.0.1:9018/navigator/attrDescription?contentId=${this.selectContentId}`)
        .then((response) => {
          this.attractionData.title = response.data.title;
          this.attractionData.attractionOverview = response.data.overview;
          this.attractionData.imgUrl = response.data.first_image;
        })
    },
  }
}

</script>

<style>
.modal-image {
  max-width: 100%;
  max-height: 100%;
}

.modal-title {
  font-weight:bolder;
  font-size:xx-large;
}

.my-custom-title {
  padding-bottom: 5px;
}
</style>
