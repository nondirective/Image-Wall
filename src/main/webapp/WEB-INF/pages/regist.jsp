<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
</head>
<body>
	<form method="post" action="/imagewall/regist">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username"
					value="${user.username }" /></td>
				<td>${msg_username }</td>
			</tr>

			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"
					value="${user.password }" /></td>
				<td>${msg_password }</td>
			</tr>

			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="password2" /></td>
				<td>${msg_password2 }</td>
			</tr>

			<tr>
				<td>二级密码：</td>
				<td><input type="password" name="secondryPassword"
					value="${secondryPassword }" /></td>
				<td>${msg_passwordl2 }</td>
			</tr>

			<tr>
				<td>验证码：</td>
				<td><input type="text" name="checkcode" /></td>
				<td><img src="/imagewall/checkcodeImage" /></td>
				<td>${msg_checkcode }</td>
			</tr>

		</table>
		<input type="submit" value="提交" />
	</form>
</body>
</html>