<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	/*
	* 根据照片组id获取
	* 	-名称
	*   -封面图url
	*/
	
%>
<title></title>
</head>
<body>
	<p>当前照片组：${imageGroup.groupName }</p>
	<a href="/imagewall/imageUploadPage/${imageGroup.id }">
	<button>上传图片</button>
	</a>

</body>
</html>