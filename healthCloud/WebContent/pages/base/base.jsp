<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% String contextPath = request.getContextPath(); %>

<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/bootstrap-theme.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/dataTables/css/dataTables.bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/zTree/css/zTreeStyle/zTreeStyle.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/layer/css/layer.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/health/healthManage.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/health/font-awesome.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">

<script src="<%=contextPath%>/res/js/jquery-3.2.1.min.js"></script>
<script src="<%=contextPath%>/res/js/bootstrap.min.js"></script>
<script src="<%=contextPath%>/res/plugins/dataTables/js/jquery.dataTables.min.js"></script>
<script src="<%=contextPath%>/res/plugins/dataTables/js/dataTables.bootstrap.min.js"></script>
<script src="<%=contextPath%>/res/plugins/zTree/js/jquery.ztree.core.min.js"></script>
<script src="<%=contextPath%>/res/plugins/zTree/js/jquery.ztree.excheck.min.js"></script>

<script src="<%=contextPath%>/res/plugins/layer/js/layer.js"></script>
<script src="<%=contextPath%>/res/plugins/jquery-validation/jquery.validate.js"></script>
<script src="<%=contextPath%>/res/plugins/jquery-validation/additional-methods.js"></script>
<script src="<%=contextPath%>/res/plugins/jquery-validation/localization/messages_zh.js"></script>
<script src="<%=contextPath%>/res/plugins/datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="<%=contextPath%>/res/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=contextPath%>/res/js/jquery.form.js"></script>
<script src="<%=contextPath%>/res/js/health/echarts.js"></script>

<script src="https://webapi.amap.com/maps?v=1.3&key=81528f81f3d776262054cf8281d5572a"></script>