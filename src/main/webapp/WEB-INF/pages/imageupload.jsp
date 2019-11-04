<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="com.nondirectional.imagewall.bean.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	User user = (User)session.getAttribute("user");
	if(user==null){
		request.getRequestDispatcher("/WEB-INF/pages/unlogin.jsp").forward(request, response);
	}
	Integer userId = user.getId();
	
%>
<form action="/imagewall/imageUpload" enctype="multipart/form-data" method="post">
	<input type="hidden" name="userId" value="${userId }"/>
	<input type="hidden" name="parentGroup" value="${groupId }"/>
    <input type="file" name="files" multiple="multiple"/>
    <input type="submit"/>
</form>
<p>${msg_fileupload }</p>
</body>
</html>