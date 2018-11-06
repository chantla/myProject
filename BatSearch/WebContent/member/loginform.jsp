<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"/>
<title>/member/loginform.jsp</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src = "https://plus.google.com/js/client:platform.js" async defer></script>
</head>
<body>
<a href="../main/main.jsp">pp.com</a>
<a href="insertform.jsp">가입하러가기</a>
<form action="login.jsp" method="post">
	<label for="email">이메일</label>
	<input type="text" name="email" id="email"/>
	<br/>
	<label for="pwd">비밀번호</label>
	<input type="password" name="pwd" id="pwd"/>
	<br/>
	<button type="submit">로그인</button>
	<button type="reset">취소</button>
</form>
<%-- 카카오버튼 --%>
<a id="kakao-login-btn"></a>
<%-- 구글버튼 --%>
<div id="gConnect" class="button">
	<button class="g-signin"
	    data-scope="email"
	    data-clientid="244347192953-24pvdphvu4oi4jn42c7uq3o6r57dcacb.apps.googleusercontent.com"
	    data-callback="onSignInCallback"
	    data-theme="dark"
	    data-cookiepolicy="single_host_origin">
	</button>
</div>
<script type='text/javascript'>
//카카오 정보를 불러올 스크립트 입니다.
//<![CDATA[
	// 사용할 앱의 JavaScript 키를 설정해 주세요.
	Kakao.init('43389023faf5b8dd6b8dbefce4756069');
	// 카카오 로그인 버튼을 생성합니다.
	Kakao.Auth.createLoginButton({
		container: '#kakao-login-btn',
		success: function(authObj) {
			// 로그인 성공시, API를 호출합니다.
			Kakao.API.request({
				url: '/v2/user/me',
				success: function(res) {
					location.href="login.jsp?kaid="+res.id+"&kaname="+res.properties.nickname;
				},
				fail: function(error) {
				  alert(JSON.stringify(error));
				}
			});
		},
		fail: function(err) {
			alert(JSON.stringify(err));
		}
	});
//]]>
</script>
<script>
  /**구글 정보를 불러오는 스크립트 입니다.
   * Handler for the signin callback triggered after the user selects an account.
   */
  function onSignInCallback(resp) {
    gapi.client.load('plus', 'v1', apiClientLoaded);
  }

  /**
   * Sets up an API call after the Google API client loads.
   */
  function apiClientLoaded() {
    gapi.client.plus.people.get({userId: 'me'}).execute(handleEmailResponse);
  }

  /**
   * Response callback for when the API client receives a response.
   *
   * @param resp The API response object with the user email and profile information.
   */
  function handleEmailResponse(resp) {
    var primaryEmail;
    for (var i=0; i < resp.emails.length; i++) {
      if (resp.emails[i].type === 'account') primaryEmail = resp.emails[i].value;
    }
    location.href="login.jsp?gooid="+resp.id+"&gooname="+resp.displayName+"&gooemail="+primaryEmail;
  }
</script>
</body>
</html>