<template>
  <b-tabs content-class="mt-3">
    <b-tab title="아이디찾기" active>
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
                style="
                  background-color: white;
                  border: none;
                  padding-left: 2.5px;
                "
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
            <b-button
              id="btn-join"
              type="button"
              variant="primary"
              @click="findId"
              >아이디찾기</b-button
            >
          </p>
        </div>
        <label>찾은 아이디 : {{ this.userid }}</label>
      </div></b-tab
    >

    <!-- 비밀번호 찾기 스타트 -->
    <b-tab title="비밀번호 찾기">
      <section>
        <div class="form-container">
          <h2>비밀번호 찾기</h2>
          <div class="form-group">
            <input
              name="from_id"
              placeholder="아이디를 입력해주세요"
              type="text"
              required
              v-model="userid"
            />
          </div>
          <div class="form-group">
            <input
              name="from_name"
              placeholder="이름을 입력해주세요"
              type="text"
              v-model="username"
              required
            />
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
                style="
                  background-color: white;
                  border: none;
                  padding-left: 2.5px;
                "
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
          <div class="form-group">
            <button
              class="btnclass"
              type="btn"
              name="send"
              @click="findPassword"
            >
              비밀번호 찾기
            </button>
          </div>
        </div>
      </section></b-tab
    >
  </b-tabs>
</template>

<script>
import emailjs from "emailjs-com";
export default {
  data() {
    return {
      userId: "",
      userName: "",
      emailId: "",
      emailDomain: "",
      userPwd: "",
    };
  },
  methods: {
    // 아이디 찾기
    findId() {
      fetch(
        `http://127.0.0.1:9018/user/find/${this.emailid}/${this.emaildomain}`,
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

    // 비밀번호 찾기
    findPassword() {
      console.log(
        this.userid +
          " " +
          this.username +
          " " +
          this.emailid +
          "  " +
          this.emaildomain
      );
      fetch(
        `http://localhost:9018/user/find/${this.userid}/${this.username}/${this.emailid}/${this.emaildomain}`,
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
          this.userPwd = data.findpassword.userpwd;
          console.log(this.userPwd);
          console.log(this.username);
          this.sendEmail(this.username, this.userPwd);
        })
        .catch((error) => {
          console.error(error);
        });
    },

    // 이메일 보내기
    sendEmail(username, userPwd) {
      const emailParams = {
        username: username,
        userPwd: userPwd,
      };
      console.log("email username: " + username);
      console.log("email userPwd: " + userPwd);
      emailjs
        .send(
          "service_gjbj94f",
          "template_zfsu1rn",
          emailParams,
          "4bqA46x4Eeb0JW3IY"
        )
        .then(
          (result) => {
            console.log("이메일 전송 성공", result.status, result.text);
          },
          (error) => {
            console.log("이메일 전송 실패", error);
          }
        );
    },
  },
};
</script>
