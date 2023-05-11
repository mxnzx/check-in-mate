// 로그인 메서드
function login() {
  var inputID = document.querySelector("#id");
  var inputPW = document.querySelector("#pw");
  if (!inputID ) {
    alert("아이디를 입력하세요");
    return;
  }
  if (!inputPW) {
    alert("비밀번호를 입력하세요");
    return;
  }
  if (inputID.value=="ssafy" && inputPW.value == "1111") {
    alert("로그인 성공");
    //로그인 후 변경사항 여기에 구현
    self.document.location.reload();
  } else {
    alert("아이디 또는 비밀번호가 틀렸습니다.")
    return;
  }
}

//회원가입 로직
 let isUseId = false;
    document.querySelector("#userid").addEventListener("keyup", function () {
		let userid = this.value;
		console.log(userid);
  	 	let resultDiv = document.querySelector("#idcheck-result");
  	 	if(userid.length < 6 || userid.length > 16) {
  		 	resultDiv.setAttribute("class", "mb-3 text-dark");
  		 	resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다.";
  		 	isUseId1 = false;
  	 	} else {
  		 	fetch("${root}/user?act=idcheck&userid=" + userid)
	   		.then(response => response.text())
	   		.then(data => {
		 		if(data == 0) {
		   			resultDiv.setAttribute("class", "mb-3 text-primary");
	       			resultDiv.textContent = userid + "는 사용할 수 있습니다.";
	       			isUseId = true;
		 		} else {
		   			resultDiv.setAttribute("class", "mb-3 text-danger");
 		       		resultDiv.textContent = userid + "는 사용할 수 없습니다.";
 		     		isUseId = false;
		 		}
  		   });
  	 	}
    });
    
	document.querySelector("#btn-join").addEventListener("click", function () {
		if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userid").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (document.querySelector("#userpwd").value != document.querySelector("#pwdcheck").value) {
          alert("비밀번호 확인!!");
          return;
        } else {
          let form = document.querySelector("#form-join");
          form.setAttribute("action", "${root}");
          form.submit();
        }
	});