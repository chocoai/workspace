<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>${sysConfig.sitePame }</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<input type="hidden" value="${contextPath}" id="contextPath"></input>

<link   rel="icon" href="${contextPath}/avicon.ico" type="image/x-icon" />
<link   rel="shortcut icon" href="${contextPath}/favicon.ico" />

<!-- Bootstrap core CSS -->
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/font-awesome.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${contextPath}/static/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<link href="${contextPath}/static/css/dashboard.css" rel="stylesheet">
<link href="${contextPath}/static/css/main.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${contextPath}/static/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<script language="javascript">
if (window != top)
top.location.href = location.href;
</script> 
</head>
<body class="login-bg">
	<div class="container">
      <div class="rows">
        <!-- 标题与电脑背景 strat -->
        <div class="col-md-6 hidden-xs">
           <div class="login-l text-center">
               <h1 class="logo"><img src="${contextPath}/static/images/logo.png">${sysConfig.siteName }</h1>
               <div class="login-l-bg"><img src="${contextPath}/static/images/login-1.png"></div>
           </div>
        </div>
        <!-- 标题与电脑背景 end -->

        <!-- 登录表单 strat -->
        <div class="col-md-6">
           <div class="login-r text-center">
               <div>
                  <div class="form-group">
                     <img src="${contextPath}/static/images/login-2.png">
                  </div>
                  <div class="input-group login-input">
                    <span class="input-group-addon"><i class="icon-user"></i></span>
                    <input type="text" class="form-control" placeholder="用户名" id="username">
                  </div>
                  <div class="input-group login-input">
                    <span class="input-group-addon"><i class="icon-lock"></i></span>
                    <input type="password" class="form-control" placeholder="密码" id="password">
                  </div>
                  <div class="input-group login-input pull-left col-xs-6">
                    <span class="input-group-addon"><i class="icon-edit"></i></span>
                    <input type="text" class="form-control" placeholder="验证码" id="vcode">
                  </div>
                  <div class="input-group login-input pull-right col-xs-4" id="vcodeImg">
                    <img src="${contextPath}/open/getJPGCode" class="img-responsive">
                  </div>
                  <div class="col-xs-12 nopad-lr">
                    <div class="checkbox text-left nomar-t mar-b15">
                      <label><input type="checkbox" name="remmberpassword" id="rememberMe">记住密码</label>
                    </div>
                  </div>
                  <button type="button" class="btn btn-warning btn-lg col-xs-12" id="login">登录</button>
               </div>
           </div>
        </div>
        <!-- 登录表单 end -->
      </div>
    </div>
    
    <script src="${contextPath}/static/js/jquery.min.js"></script>
    <script src="${contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${contextPath}/static/js/ie10-viewport-bug-workaround.js"></script>
    <script src="${contextPath}/static/js/all.js"></script>
	<script src="${contextPath}/static/layer/layer.js"></script>
    
    <script src="${contextPath}/static/localjs/user/login.js"></script>
    <script src="${contextPath}/static/localjs/user/MD5.js"></script>
</body>
</html>