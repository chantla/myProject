<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/signup_form.jsp</title>
</head>
<body>
<a href="../main/main.jsp">pp.com</a>
<form action="insert.jsp" method="post" id="signupForm">
	<label for="email">이메일</label>
	<input type="text" name="email" id="email" autocomplete="off" />
	<span id="checkResult"></span>
	<br/>
	<label for="name">닉네임</label>
	<input type="text" name="name" id="name" autocomplete="off"/>
	<br/>
	<label for="pwd">비밀번호</label>
	<input type="password" name="pwd" id="pwd" autocomplete="off"/>
	<br />
    <button type="submit" 
    	class='g-recaptcha'
        data-sitekey='6LfninYUAAAAAK6tNYNnHecKEOzpMfy3mmxiglnR'
        data-callback='onSubmit'>가입</button>
	<button type="reset">취소</button>
</form>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script>
	//폼의 유효성 여부
	var formValid=false;
	
	function onSubmit() {
		//유효성 검증후
		if(formValid==false){//폼이 유효하지 않으면
			return false;	
		}
		//폼 제출하기
		document.getElementById('signupForm').submit();
	}
	
	//아이디 입력란에 입력했을때 실행할 함수 등록
	$("#email").on("input", function(){
		//현재까지 입력한 아이디를 ajax 요청을 통해서 서버에 전송해서
		//사용가능 여부를 리턴 받는다.
		var inputEmail=$(this).val();
		$.ajax({
			url:"checkid.jsp",
			method:"get",
			data:{inputEmail:inputEmail},
			success:function(responseData){
				//콘솔에 테스트 출력
				console.log(responseData);
				if(responseData.canUse){
					$("#checkResult")
					.text("사용가능")
					.css("color","#0f0");
					formValid=true;
				}else{
					$("#checkResult")
					.text("사용불가")
					.css("color","#ff0000");
					formValid=false;
				}
			}
		});
	});
</script>
</body>
</html>