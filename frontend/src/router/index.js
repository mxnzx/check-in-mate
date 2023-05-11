import Vue from "vue";
import VueRouter from "vue-router";
import AppBoard from "@/views/AppBoard";
import BoardList from "@/components/board/BoardList";
import BoardWrite from "@/components/board/BoardWrite";
import BoardView from "@/components/board/BoardView";
import AppAttraction from "@/views/AppAttraction";
// import { component } from "vue/types/umd";

Vue.use(VueRouter);

const routes = [
  {
    path: "/board/api",
    name: "AppBoard",
    component: AppBoard,
    redirect: "/board/api/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: BoardList,
      },
      {
        path: "write",
        name: "boardWrite",
        component: BoardWrite,
      },
      {
        path: "view/:articleNo",
        name: "boardView",
        component: BoardView,
      },
    ],
  },

  // {
  //   path: "/board/list",
  //   name: "boardlist",
  //   component: AppBoard,
  // },
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
