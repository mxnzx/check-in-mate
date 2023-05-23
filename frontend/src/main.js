import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

export const eventBus = new Vue();

Vue.config.productionTip = false;
Vue.use(BootstrapVue);
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");

// main.js 또는 Vue 인스턴스를 생성하는 곳에서 아래 코드를 추가합니다.
window.addEventListener("beforeunload", () => {
  // 페이지가 언로드되기 전에 데이터 초기화
  store.commit("resetDataByDay");
});