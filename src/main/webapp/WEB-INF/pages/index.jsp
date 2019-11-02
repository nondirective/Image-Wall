<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.nondirectional.imagewall.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主页</title>
</head>
<body>
<h1>首页</h1>
<h3>
<%
if(session.getAttribute("user")!=null){
	out.write("欢迎你,"+((User)session.getAttribute("user")).getUsername());
}else{
	out.write("用户未登录");
}
%>
</h3>
<a href="/imagewall/registPage">注册</a>
<a href="/imagewall/loginPage">登录</a>
<a href="/imagewall/logout">注销</a>
</body>
</html>