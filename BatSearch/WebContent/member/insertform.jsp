<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/memberreg.jsp</title>
</head>
<body>
<a href="../main/main.jsp">pp.com</a>
<form action="insert.jsp" method="post">
	<label for="email">이메일</label>
	<input type="text" name="email" id="email"/>
	<br/>
	<label for="name">닉네임</label>
	<input type="text" name="name" id="name"/>
	<br/>
	<label for="pwd">비밀번호</label>
	<input type="password" name="pwd" id="pwd"/>
	<br/>
	<button type="submit">저장</button>
	<button type="reset">취소</button>
</form>
</body>
</html>