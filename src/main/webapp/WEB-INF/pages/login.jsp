<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<body>

	<form action="/imagewall/login" method="post">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" /></td>
				<td>${msg_username }</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" /></td>
				<td>${msg_password }</td>
			</tr>
		</table>
		<input type="submit" value="登陆"/>
	</form>
</body>
</html>