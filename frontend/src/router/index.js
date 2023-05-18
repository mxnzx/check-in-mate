import Vue from "vue";
import VueRouter from "vue-router";
// views 등록
import AppBoard from "@/views/AppBoard";
import AppAttraction from "@/views/AppAttraction";
import AppNotice from "@/views/AppNotice";
import AppMain from "@/views/AppMain";
// import AppHotPlace from "@/views/AppHotPlace";

// 여행정보 공유
import BoardList from "@/components/board/BoardList";
import BoardWrite from "@/components/board/BoardWrite";
import BoardView from "@/components/board/BoardView";
import BoardModify from "@/components/board/BoardModify";

// 공지사항
import NoticeList from "@/components/notice/NoticeList";
import NoticeWrite from "@/components/notice/NoticeWrite";
import NoticeView from "@/components/notice/NoticeView";
import NoticeModify from "@/components/notice/NoticeModify";

// 핫플 자랑하기
// import HotPlaceList from "@/components/hotplace/HotPlaceList";
//import HotPlaceModal from "@/components/hotplace/HotPlaceModal";
// import { component } from "vue/types/umd";

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
    redirect: "/index",
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () =>
          import(
            /* webpackChunkName: "user" */ "@/components/user/UserRegister"
          ),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "user" */ "@/components/user/MyPage"),
      },
    ],
  },

  {
    path: "/hotplace",
    component: () => import("@/components/hotplace/HotPlaceList"),
  },
  {
    path: "/hotplaceModal",
    component: () => import("@/components/hotplace/HotPlaceModal"),
  },
  {
    path: "/hotplace/write",
    component: () => import("@/components/hotplace/HotPlaceWrite"),
  },
  {
    path: "/",
    name: "TheIndex",
    redirect: "/index",
  },
  {
    path: "/index",
    component: () => import("@/components/TheIndex.vue"),
  },
  {
    path: "/mypage",
    component: () => import("@/components/user/MyPage.vue"),
  },
  {
    path: "/login",
    component: () => import("@/components/user/LoginPage.vue"),
  },
  // 여행정보 공유
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
      {
        path: "modify/:articleNo",
        name: "boardModify",
        component: BoardModify,
      },
    ],
  },

  {
    path: "/comment",
    name: "AppBoardComment",
    component: AppBoard,
  },

  // 지역별 여행지
  {
    path: "/attraction",
    name: "AppAttraction",
    component: AppAttraction,
  },

  // 공지사항
  {
    path: "/notice/api",
    name: "AppNotice",
    component: AppNotice,
    redirect: "/notice/api/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: NoticeList,
      },
      {
        path: "write",
        name: "noticeWrite",
        component: NoticeWrite,
      },
      {
        path: "view/:articleNo",
        name: "noticeView",
        component: NoticeView,
      },
      {
        path: "modify/:articleNo",
        name: "noticeModify",
        component: NoticeModify,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
