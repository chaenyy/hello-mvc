<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%
	// page지시어 isErrorPage="true"로 지정한 경우
	// 발생한 예외객체에 선언없이 접근 가능
	// error code로 넘어온 경우, exception 객체는 null!
	// String msg = exception.getMessage();
	int statusCode = response.getStatus();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류</title>
<style>
body {text-align: center;}
h1 {font-size :500px; margin : 0;}
.err-msg {color : red;}
</style>
</head>
<body>
	<h1>텅</h1>
	<p class="err-msg">찾으시는 페이지가 없습니다.</p>
	<hr />
	<a href="<%= request.getContextPath() %>">홈으로</a>
	<br />
	<a href="javascript:history.back();">뒤로가기</a>
</body>
</html>