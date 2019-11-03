<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误</title>
</head>
<body>
<p>用户未登录</p>
<%
response.setHeader("refresh", "2;URL=/imagewall/index");
%>
</body>
</html>