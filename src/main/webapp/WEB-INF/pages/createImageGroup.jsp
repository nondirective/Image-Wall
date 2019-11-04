<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.nondirectional.imagewall.bean.User"
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>创建照片组</title>
</head>
<%
	User user = (User)session.getAttribute("user");
	if (user == null) {
		request.getRequestDispatcher("/WEB-INF/pages/unlogin.jsp").forward(request, response);
	}
%>
<body>
	<form action="/imagewall/createImageGroup" method="post">
		照片组名称： <input type="text" name="groupName" /> ${msg_groupName }
		<br/>
		描述：<textarea rows="10" cols="10" name="description"></textarea>${msg_description }
		</br>
		<input type="hidden" value="${user.id}" name="userId"/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>