<template>
  <div class="w3-content w3-container w3-margin-top">
    <div class="w3-container w3-card-4">
      <div class="w3-center w3-large w3-margin-top">
        <h2>아이디 찾기</h2>
        <br />
      </div>
      <div class="row" style="justify-content: center">
        <p>
          <input
            class="form-control"
            type="text"
            id="emailid"
            name="emailid"
            placeholder="이메일을 입력해주세요."
            required
            v-model="emailid"
          />
        </p>
        <div class="col-1">
          <span
            class="input-group-text"
            style="background-color: white; border: none; padding-left: 2.5px"
            >@</span
          >
        </div>
        <div>
          <b-select
            class="form-select"
            id="emaildomain_join"
            name="emailDomain"
            v-model="emaildomain"
          >
            <option value="ssafy.com">SSAFY</option>
            <option value="google.com">GOOGLE</option>
            <option value="kakao.com">KAKAO</option>
            <option value="naver.com">NAVER</option>
          </b-select>
        </div>
      </div>
      <p class="w3-center">
        <b-button id="btn-join" type="button" variant="primary" @click="findId"
          >아이디찾기</b-button
        >
      </p>
    </div>
    <label>찾은 아이디 : {{ this.userid }}</label>
  </div>
</template>

<script>
export default {
  data() {
    return {
      emailId: "",
      emailDomain: "",
      userid: "",
    };
  },
  methods: {
    findId() {
      fetch(
        `http://192.168.208.83:9018/user/find/${this.emailid}/${this.emaildomain}`,
        {
          method: "GET",
        }
      )
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error("글 가져오기 실패");
          }
        })
        .then((data) => {
          this.userid = data.findid.userid;
          console.log(this.userid);
          //console.log(data);
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>
