<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% String contextPath = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="7200">
<meta name="author" content="http://www.yhcrt.com">
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10">

<title>炎黄创新-健康养老服务云平台</title>
<link rel="shortcut icon" href="<%=contextPath%>/res/img/logo.png">
<link rel="stylesheet" href="<%=contextPath%>/res/css/login/typica-login.css">
<link rel="stylesheet" href="<%=contextPath%>/res/css/login/bootstrap.min.css">
<link rel="stylesheet" href="<%=contextPath%>/res/css/login/jquery.validate.min.css">
<link rel="stylesheet" href="<%=contextPath%>/res/css/login/webservice.min.css">
<link rel="stylesheet" href="<%=contextPath%>/res/plugins/layer/css/layer.css">

<style type="text/css">
	.control-group { border-bottom: 0px; }
	.fancybox-margin { margin-right: 0px; }
</style>
</head>

<script language="javascript">
	if (window != top)
	top.location.href = location.href;
</script> 

<body>
	<div class="navbar navbar-fixed-top" style="height: 95px;">
		<div class="navbar-inner">
			<div class="container" style="width: 83% !important;">
				<a class="brand" href="#"> 
					<img src="<%=contextPath%>/res/img/logo.png" alt="炎黄创新" style="height: 70px;"> 
					<span style="font-size: 30px;">炎黄创新智慧健康养老平台</span>
				</a>
			</div>
			<div style="position: relative; float: right; top: -60px;"></div>
		</div>
	</div>
	<div class="container">
		<div id="login-wraper">
			<form id="loginForm" class="form login-form" action="check" method="post" novalidate="novalidate">
				<fieldset>
					<legend style="margin-bottom: 30px;">
						<span style="color: #FFFFCC;">系统登录 </span>
					</legend>
				</fieldset>
				<div class="body">
					<div class="control-group">
						<div class="controls">
							<input type="text" id="username" name="username" class="required" placeholder="用户名">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<input type="password" id="password" name="password" class="required" placeholder="密码">
						</div>
					</div>
				</div>
				<div class="footer">
					<label class="checkbox inline"> <input type="checkbox" id="rememberMe" name="rememberMe" onclick="remember()"> 
						<span style="color: #FFFFCC;">记住我</span>
					</label>
					<button type="button" id="login" class="btn btn-primary">登 录</button>
				</div>
			</form>
		</div>
	</div>
	<footer class="white navbar-fixed-bottom"> 武汉炎黄创新科技服务有限公司  Copyright &copy; 2017 </footer>
</body>

<script src="<%=contextPath%>/res/js/login/jquery-1.9.1.min.js"></script>
<script src="<%=contextPath%>/res/js/login/jquery-migrate-1.1.1.min.js"></script>
<script src="<%=contextPath%>/res/js/login/jquery.validate.min.js"></script>
<script src="<%=contextPath%>/res/js/login/jquery.validate.method.min.js"></script>
<script src="<%=contextPath%>/res/js/login/bootstrap.min.js"></script>
<script src="<%=contextPath%>/res/js/login/mustache.min.js"></script>
<script src="<%=contextPath%>/res/js/login/select2.js"></script>
<script src="<%=contextPath%>/res/js/login/select2_locale_zh-CN.js"></script>
<script src="<%=contextPath%>/res/js/login/jquery.fancybox.js"></script>
<script src="<%=contextPath%>/res/js/login/webservice.min.js"></script>
<script src="<%=contextPath%>/res/js/login/jquery.imgbox.pack.js"></script>
<script src="<%=contextPath%>/res/js/login/backstretch.min.js"></script>
<script src="<%=contextPath%>/res/plugins/layer/js/layer.js"></script>

<script type="text/javascript">
$(document).ready(
	function() {
		$.backstretch(["<%=contextPath%>/res/img/xhtx01.jpg", "<%=contextPath%>/res/img/xhtx02.jpg", "<%=contextPath%>/res/img/xhtx03.jpg" ], {
				duration : 5000,
				fade : 2000
		});
});

//回车事件绑定
document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if(e && e.keyCode==13){ 
		$('#login').click();
	}
};

//登录操作
$('#login').click(function(){
    var username = $('#username').val();
    var password = $('#password').val();
    if(username == '') {
    	return layer.msg('请输入账号！',function(){}),!1;
    }
    if(password == '') {
    	return layer.msg('请输入密码！',function(){}),!1;
    }
    var paras = {};
	paras.username = username;
	paras.password = password;
    var load = layer.load();
    $.post("<%=contextPath%>/login",paras ,function(data){
    	layer.close(load);
		if(data.result){
			window.location.href= "<%=contextPath%>/main";
		}else{
			$('#password').val('');
			$('#password').focus();
			layer.msg(data.message, { anim: 6 });
			return;
		}
	});
    
});
</script>
</html>