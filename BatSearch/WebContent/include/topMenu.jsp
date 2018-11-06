<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@page import="eduBeans.Cookies"%> 
<% Cookies cookies = new Cookies(request); %>  --%>
<!DOCTYPE html>
<html>
<head><title> topMenu.jsp </title></head>
<body>
<a href="../main/main.jsp">pp.com</a> 동접:
<%
 if(session.getAttribute("SESSION_ID") == null){ 
/* if(!cookies.exists("COOKIE_ID")){ */
%>
	<a style="float:right;" href="../member/loginform.jsp" >로그인</a>
 <% }else{ %>
	<a style="float:right;" href="../member/logoutform.jsp">로그아웃</a>
	<a style="float:right;" href="/member/memberDetail.jsp">회원정보</a>
 <% } %>
<br />

<a href="../board/weaponinfo.jsp">무기정보</a>
<a href="../board/freeboard.jsp">커뮤니티</a>
<a href="../board/chatting.jsp">채팅</a>
<input type="text" /> <a href="../member/searchresult.jsp">검색하기</a>
<br />


</body>
</html>