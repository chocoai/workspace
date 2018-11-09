<%@page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>炎黄创新-健康养老服务云平台</title>

<%@include file="/pages/base/head.jsp"%>

</head>

<body>
	<div id="wrapper">
		<%@include file="/pages/base/menu.jsp"%>

		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div id="top"><%@include file="/pages/base/top.jsp"%></div>
			<div id="content">
				<iframe id="rframe" name="rframe" src="content.do" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"> 
				</iframe>
			</div>
			<div id="footer">
				<%@include file="/pages/base/footer.jsp"%>
			</div>

		</div>
	</div>

</body>
</html>