<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String contextPath = request.getContextPath();
%>
<style type="text/css">
.badge{ width:24px; padding:3px; margin-left:0px; margin-top:-15px;color:#fff; background-color:#ed1000;border-radius:50%;}
</style>
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#" style="padding-top: 0px;"> 
				<img src="<%=contextPath%>/res/img/logo.png" height="50px" alt="炎黄创新">
			</a>
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>

		</div>

		<div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
			<ul id="menu" class="nav navbar-nav">
			<shiro:hasPermission name="nav:security">
				<li><a href="<%=contextPath%>/security/getMap" target="contentFrame"><span>安全服务</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:health">
				<li><a href="<%=contextPath%>/healthService/list" target="contentFrame"><span>健康服务</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:service">
				<li><a href="<%=contextPath%>/workOrder/list" target="contentFrame"><span>服务管理<span class="badge" id="todoWorkOrder"></span></span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:mall">
				<li><a href="<%=contextPath%>/ywOrder/list" target="contentFrame"><span>商城管理<span class="badge" id="todoOrder"></span></span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:cms">
				<li><a href="<%=contextPath%>/content/list" target="contentFrame"><span>内容管理</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:member">
				<li><a href="<%=contextPath%>/member/list?status=0" target="contentFrame"><span>会员管理</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:user">
				<li><a href="<%=contextPath%>/employee/list" target="contentFrame"><span>用户管理</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:org">
				<li><a href="<%=contextPath%>/org/list" target="contentFrame"><span>机构管理</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:provider">
				<li><a href="<%=contextPath%>/provider/toList" target="contentFrame"><span>服务供应商</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:device">
				<li><a href="<%=contextPath%>/device/list" target="contentFrame"><span>终端设备</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="nav:system">
				<li><a href="<%=contextPath%>/role/list" target="contentFrame"><span>系统管理</span></a></li>
			</shiro:hasPermission>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="<%=contextPath%>/alarm/toList?isRead=0" target="contentFrame">
						<i class="fa fa-bell" aria-hidden="true"></i>
						<span class="badge" id="alarm_num"></span>
					</a>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">您好, ${loginUser.emp.realName }
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" style="min-width:100%;">
						<li><a href="<%=contextPath%>/personal/index" target="contentFrame">个人信息</a></li>
						<li><a href="<%=contextPath%>/pages/personal/updatePwd.jsp" target="contentFrame">修改密码</a></li>
					</ul>
				</li>
				<li><a href="logout">退出</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>

<script type="text/javascript">
	$(document).ready(function() {
		$("#menu li a").click(function() {
			$("#menu li").removeClass("active");
			$(this).parent().addClass("active");
		});
		
		$("#bs-example-navbar-collapse-1 ul li:eq(2) a span").click();
		
		setUnreadAlarmNum();
		setToDoNum();
	});
	
	function setToDoNum() {
 		$.ajax({
			async : true,
			cache : false,
			contentType : false,
			dataType : "json",
			processData : false,
			type : "get",
			url : "workOrder/getToDoNum",
			success : function(data) {
				var json = JSON.parse(data); 
				var toDoWorkNum = json.toDoWorkNum;
				var toDoGoodsNum = json.toDoGoodsNum;
				if(toDoWorkNum>0){
					$("#todoWorkOrder").text(toDoWorkNum>99?"99+":toDoWorkNum+"");
				}else{
					$("#todoWorkOrder").hide();
				}
				if(toDoGoodsNum>0){
					$("#todoOrder").text(toDoGoodsNum>99?"99+":toDoGoodsNum+"");
				}else{
					$("#todoOrder").hide();
				}
                
			},
		}); 
	}
	
	function setUnreadAlarmNum(){
		$.ajax({
			async : true,
			cache : false,
			contentType : false,
			dataType : "json",
			processData : false,
			type : "get",
			url : "alarm/countUnreadAlarm",
			success : function(data) {
				var unreadAlarmNum = data.unreadAlarmNum;
				if(unreadAlarmNum>0){
					$("#alarm_num").text(unreadAlarmNum > 99 ? "99+" : unreadAlarmNum + "");
				}else{
					$("#alarm_num").hide();
				}
			}
		});
	}

	window.setInterval("setToDoNum()", 180000); //三分钟刷新一次
	window.setInterval("setUnreadAlarmNum()", 180000); //三分钟刷新一次
	
</script>