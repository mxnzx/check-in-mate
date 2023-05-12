<template>
  <nav class="navbar navbar-expand-lg my-2">
    <div class="container-fluid">
      <a class="navbar-brand fs-3" href="/" id="enjoyTrip"> Enjoy Trip</a>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/attraction">지역별여행지</router-link>
            </a>
          </li>
          <li class="nav-item"><a class="nav-link" href="">나의여행계획</a></li>
          <li class="nav-item"><a class="nav-link" href="">핫플자랑하기</a></li>
          <li class="nav-item">
            <a class="nav-link">
              <router-link to="/board/api">여행정보공유</router-link>
            </a>
          </li>
        </ul>
        <ul class="navbar-nav mb-2 mb-lg-0 m-2">
          <li class="nav-item">
            <a class="nav-link" href="${root}/notice/list?&pgno=1&key=&word="
              >공지사항</a
            >
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              회원관리
            </a>
            <ul class="dropdown-menu">
              <li>
                <a
                  class="dropdown-item"
                  href="#"
                  data-bs-toggle="modal"
                  data-bs-target="#signUpModal"
                  >회원가입</a
                >
              </li>
              <li>
                <a
                  class="dropdown-item"
                  id="login-dropdown"
                  href="#"
                  data-bs-toggle="modal"
                  data-bs-target="#loginModal"
                  >로그인</a
                >
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {};
</script>

<style></style>
