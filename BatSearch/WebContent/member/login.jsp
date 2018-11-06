<%@page import="test.member.dao.MemberDao"%>
<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//요청 인코딩 설정 (post 방식 인코딩 설정) 한글 깨지지 않도록
	request.setCharacterEncoding("utf-8");
	int check=0;
	//카카오계정으로 로그인한 정보
	String kaid=request.getParameter("kaid");
	String kaname=request.getParameter("kaname");
	//구글계정으로 로그인한 정보
	String gooid=request.getParameter("gooid");
	String gooname=request.getParameter("gooname");
	String gooemail=request.getParameter("gooemail");
	if(kaid==null && gooid==null){
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		check=MemberDao.getInstance().login(email,pwd);
	}else if(kaid!=null){
		MemberDao.getInstance().kalogin(kaid);
		session.setAttribute("name", kaname);
		check=1;
	}else if(gooid!=null){
		MemberDao.getInstance().goologin(gooid,gooname,gooemail);
		session.setAttribute("name", gooname);
		check=1;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/login.jsp</title>
</head>
<body>
<h3>로그인 결과 페이지</h3>
<%if(check==1){ %>
	<p> 로그인 성공!</p>
	<a href="../main/main.jsp">메인 페이지 가기</a>	
<%}else{ %>
	<p>아이디나 비밀번호를 확인해주세요.</p>
	<a href="javascript:history.back()">로그인 다시하러 가기</a>
<%} %>
</body>
</html>