<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--shiro 标签 --%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>省运动会成绩管理系统</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<input type="hidden" value="${contextPath}" id="contextPath"></input>
<!-- Bootstrap core CSS -->
<link href="${contextPath}/static/css/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<link href="${contextPath}/static/css/zTree/metroStyle/metroStyle.css" rel="stylesheet">
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/font-awesome.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${contextPath}/static/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<link href="${contextPath}/static/css/dashboard.css" rel="stylesheet">
<link href="${contextPath}/static/css/main.css" rel="stylesheet">
<link href="${contextPath}/static/css/datepicker3.css" rel="stylesheet">
<link href="${contextPath}/static/css/bootstrap-table.css" rel="stylesheet">
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${contextPath}/static/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<script src="${contextPath}/static/js/jquery.min.js"></script>
<script src="${contextPath}/static/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/ie10-viewport-bug-workaround.js"></script>
<script src="${contextPath}/static/js/menu.js"></script> 
<script src="${contextPath}/static/layer/layer.js"></script>
<script src="${contextPath}/static/js/zTree/jquery.ztree.all-3.5.js"></script>
<script src="${contextPath}/static/js/bootstrap-datepicker.js"></script> 
<script src="${contextPath}/static/localjs/commonFunction.js"></script> 
<script src="${contextPath}/static/localjs/comreg.js"></script> 
<script src="${contextPath}/static/js/bootstrap-table.js"></script> 
<!-- 表单验证 -->
<script src="${contextPath}/static/js/jquery-validation/jquery.validate.js"></script>
<script src="${contextPath}/static/js/jquery-validation/additional-methods.js"></script>
<script src="${contextPath}/static/js/jquery-validation/localization/messages_zh.js"></script>
<script src="${contextPath}/static/js/jquery-validation/jquery.serialize-object.min.js"></script>
</head>