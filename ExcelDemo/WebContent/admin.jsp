<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h4>Admin Page</h4>
	<a href="excel/out">你点一下试试啊</a>
	
	导出
	<form action="excel/add" id="deviceForm" method="post" enctype="multipart/form-data" >
	<input type="file" id="deviceFile" name="deviceFile" >
	<input type="submit" value="上传"> 
	</form>
</body>
</html>