<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/imagewall/imageUpload" enctype="multipart/form-data" method="post">
    <input type="file" name="files" multiple="multiple"/>
    <input type="submit"/>
</form>
<p>${msg_fileupload }</p>
</body>
</html>