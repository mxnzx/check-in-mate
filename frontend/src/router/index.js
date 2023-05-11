import Vue from "vue";
import VueRouter from "vue-router";
import AppBoard from "@/views/AppBoard";
// import BoardList from "@/components/board/BoardList";
import AppAttraction from "@/views/AppAttraction";

Vue.use(VueRouter);

const routes = [
  {
    path: "/board",
    name: "boardlist",
    component: AppBoard,
  },
  {
    path: "/attraction",
    name: "AppAttraction",
    component: AppAttraction,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
