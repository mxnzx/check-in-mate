<template>
    <!-- content 자리 -->
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="basic">로그인</mark>
        </h2>
      </div>
      <div class="col-lg-8 col-md-10 col-sm-12">
        <input type="hidden" name="action" value="login" />
  
        <div class="mb-3">
          <label for="userid-login" class="form-label">아이디 : </label>
          <input type="text" class="form-control" id="userid" name="userid" placeholder="아이디..." />
        </div>
        <div class="mb-3">
          <label for="userpwd" class="form-label">비밀번호 : </label>
          <input
            type="password"
            class="form-control"
            id="userpwd"
            name="userpwd"
            placeholder="비밀번호..."
          />
        </div>
        <div class="form-check mb-3 float-end">
          <input class="form-check-input" type="checkbox" value="ok" id="saveid" name="saveid" />
          <label class="form-check-label" for="saveid"> 아이디저장 </label>
        </div>
        <div class="text-danger mb-2">로그인이 필요한 페이지 입니다</div>
        <div class="col-auto text-center">
          <button type="button" id="btn-login" class="btn btn-outline-primary mb-3">로그인</button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {};
  </script>
  
  <style></style>