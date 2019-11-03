<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>创建照片组</title>
</head>
<%
	if (session.getAttribute("user") == null) {
		request.getRequestDispatcher("/WEB-INF/pages/unlogin.jsp").forward(request, response);
	}
%>
<body>
	<form action="/imagewall/createImageGroup" action="post">
		照片组名称： <input type="text" name="groupName" /> ${msg_groupName }
		<br/>
		描述：<textarea rows="30" cols="10" name="description"></textarea>${msg_description }
		</br>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>