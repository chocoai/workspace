<%@page language="java" pageEncoding="utf-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>炎黄创新-健康养老服务云平台</title>
<link rel="shortcut icon" href="<%=contextPath%>/res/img/logo.png">

<jsp:include page="/pages/base/base.jsp"></jsp:include>

<style type="text/css">

#footer {
    float: left;
	width: 100%;
	margin: 8px 0 0 0;
	padding: 3px 0 0 0;
	font-size: 11px;
	text-align: center;
	border-top: 2px solid #0663A2;
	position: fixed;
	bottom: 0;
	left: 0;
	background:#fff;
}
</style>

</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="nav">
				<jsp:include page="nav.jsp"></jsp:include>
			</div>
			<div id="content" class="row-fluid" style="padding-top: 60px;">
				<iframe id="contentFrame" name="contentFrame" src="" scrolling="auto" frameborder="0" width="100%" 
					style="padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;" > 
				</iframe>
			</div>

			<div id="footer">
				Copyright &copy; 2017 Powered By <a href="http://www.yhcrt.com" target="_blank">武汉炎黄创新科技服务有限公司</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#menu li a").click(function() {
				$("#menu li").removeClass("active");
				$(this).parent().addClass("active");
			});
		});
		
		$("#content").height(window.innerHeight - $("#nav").height() - $("#footer").height() - 60);
		$("#contentFrame").height($("#content").height());
		
		$(window).resize(function() {
			autoResize();
		});
		
		function autoResize(){
			$("#content").height(window.innerHeight - $("#nav").height() - $("#footer").height() - 60);
			$("#contentFrame,#mainFrame").height($("#content").height());
			$("#mainFrame").width(window.innerWidth - $("#openClose").width() - $("#left").width()-100);
			
		}

	</script>

</body>
</html>