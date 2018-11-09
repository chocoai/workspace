<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>炎黄创新-健康养老服务云平台</title>

<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/bootstrap-theme.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/animate.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/style.css">

<script type="text/javascript">

//如果在框架中，则跳转刷新上级页面
if (self.frameElement && self.frameElement.tagName == "IFRAME") {
	top.location.href='<%=contextPath%>/notFound';
}


</script>

</head>

<body class="gray-bg">


	<div class="middle-box text-center animated fadeInDown">
		<h1>404</h1>
		<h3 class="font-bold">找不到网页</h3>

		<div class="error-desc">
			非常抱歉，在我们的服务器上找不到您访问的页面。可能网页已经被关闭，或者您输入的网址错误，请检查一下浏览器器的网址输入栏，并点击浏览器的刷新按钮
			<form action="<%=contextPath%>/main" class="form-inline m-t" role="form">
				<!-- <div class="form-group">
					<input type="text" class="form-control"
						placeholder="Search for page">
				</div> -->
				<button type="submit" class="btn btn-primary">返回首页</button>
			</form>
		</div>
	</div>

	<!-- Mainly scripts -->
	<script src="<%=contextPath%>/res/js/jquery-3.2.1.min.js"></script>
	<script src="<%=contextPath%>/res/js/bootstrap.min.js"></script>

</body>

</html>
