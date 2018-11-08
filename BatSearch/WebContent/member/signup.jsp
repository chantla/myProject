<%@page import="test.member.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//요청 인코딩 설정 (post 방식 인코딩 설정) 한글 깨지지 않도록
	request.setCharacterEncoding("utf-8");

	//이걸 business logic 이라고 한다.
	String email=request.getParameter("email");
	String name=request.getParameter("name");
	String pwd=request.getParameter("pwd");
	MemberDto dto=new MemberDto();
	dto.setEmail(email);
	dto.setName(name);
	dto.setPwd(pwd);
	boolean isSuccess=MemberDao.getInstance().insert(dto);
%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8" />
<title>/member/insert.jsp</title>
</head>
<body>
<h3>회원 추가 결과 페이지</h3>
<%if(isSuccess){ %>
	<p> <strong><%=name %></strong> 님의 정보를 DB 에 저장했습니다.</p>
	<a href="../main/main.jsp">메인으로 가기</a>
<%}else{ %>
	<p>회원 정보 추가 실패!</p>
	<a href="insertform.jsp">회원추가 페이지로 다시 가기</a>
<%} %>
</body>
</html>