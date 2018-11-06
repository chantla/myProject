<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/main/main.jsp</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
<div id="jb-container">

	<div id="jb-header">
		<%@ include file="/include/topMenu.jsp" %>
	</div>
      
	<div id="jb-sidebar-left">
		<div id="jb-sidebar">
			<%@ include file="/include/left.jsp" %>
		</div>
		<div id="jb-cal">
			<p>가나다라마바사</p>
			<%-- <jsp:include page="" /> --%>
		</div>
	</div>
	
	<div id="jb-content">

	</div>
	
	<div id="jb-footer">
		<%@ include file="/include/bottom.jsp" %>
	</div>
</div>

</body>
</html>