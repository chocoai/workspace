<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/tagtld.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>工程咨询云工作平台</title>
<link rel="stylesheet" href="/css/main.css" />
<link rel="stylesheet" href="/css/base.css" />

<link rel="stylesheet" href="/css/messenger.css" />
<link rel="stylesheet" href="/css/messenger-theme-future.css" />

<script src="/js/jquery.js"></script>
<script src="/js/common.js"></script>

<script src="/js/messenger.min.js"></script>
<script src="/js/messenger-theme-future.js"></script>

</head>
<body>
	<div id="main">
		<div id="top">
			<div class="title">
				<img src="/images/title.png" />
			</div>
			<ul class="nav">
				<li>
					<a id="systemManager" href="/systemManager.htm">
						<img src="/images/nav_06.png" />
						系统管理
					</a>
				</li>
				<li>
					<a id="dailyOfficeManager" href="/dailyOfficeManager.htm">
						<img src="/images/nav_05.png" />
						OA办公
					</a>
				</li>
				<li>
					<a id="performanceManager" href="/performanceManager.htm">
						<img src="/images/nav_08.png" />
						绩效管理
					</a>
				</li>
				<li>
					<a id="financeManager" href="/financeManager.htm">
						<img src="/images/nav_07.png" />
						财务管理
					</a>
				</li>
				<li>
					<a id="knoledgeManager" href="/knoledgeManager.htm">
						<img src="/images/nav_02.png" />
						知识管理
					</a>
				</li>
				<li>
					<a id="projectManager">
						<img src="/images/nav_04.png" />
						项目管理
					</a>
					<div>
						<p class="navtop"></p>
						<ul>
							<c:forEach var="serviceType" items="${serviceTypeList }">
								<li>
									<a href="/projectManager.htm?serviceTypeId=${serviceType.id }"> ${serviceType.name }</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</li>
				<li>
					<a id="marketManager" href="/marketManager.htm">
						<img src="/images/nav_03.png" />
						经营管理
					</a>
				</li>
				<li>
					<a id="workbench" href="/workbench.htm">
						<img src="/images/nav_01.png" />
						工作台
					</a>
				</li>
			</ul>
		</div>
		<div id="user">
			<div class="user">
				<div class="user_bg">
					<img src="/images/user.png" />
				</div>
				<div class="name">
					欢迎您！
					<br />
					<a href="javascript:window.location.reload();">${user.name }</a>
				</div>
				<div class="user_xiala">
					<p class="usertop"></p>
					<ul>
						<c:forEach var="roletmp" items="${myAllRoleList }">
							<c:if test="${currentRoleId == roletmp.id }">
								<li class="firstli">
									<a href="/changeDept.htm?roleId=${roletmp.id}" style="color: red;">【${roletmp.dept.name}】${roletmp.name}</a>
								</li>
							</c:if>
							<c:if test="${currentRoleId != roletmp.id }">
								<li>
									<a href="/changeDept.htm?roleId=${roletmp.id}">【${roletmp.dept.name}】${roletmp.name}</a>
								</li>
							</c:if>
						</c:forEach>

					</ul>

				</div>
			</div>


			<div class="user2">
				<a href="javascript:window.location.reload();">用户中心</a>
			</div>
			<div class="user2">
				<a href="/logout.htm">退出</a>
			</div>
		</div>


		<div id="content">
			<sitemesh:write property="body" />
		</div>
		<!-- border-top:2px solid #666;加在线面DIV中的线条 -->
		<div
			style="float: left; margin: 0px auto; width: 100%; height: 102px; color: #666; min-width: 1280px; margin-top: 10px;"
		>
			<div style="clear: both; height: 20px;"></div>
			<div style="margin: 0px auto; width: 100%; height: 82px; background-color: #62a8d1;">
				<div style="margin: 0px auto; width: 1280px; text-align: center; padding-top: 25px; color: #fff;">
					Copyright ◎ 2016  All Rights Reserved<br>鼎正工程咨询股份有限公司
					<br>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">

	Messenger.options = {
		extraClasses : 'messenger-fixed messenger-on-bottom messenger-on-right',
		theme : 'future'
	}

	Messenger().run(
			{
				action : $.ajax,
				successMessage : '',
				errorMessage : '',
				progressMessage : '',
				showCloseButton: true,
				hideAfter: 10
			},
			{
				/* These options are provided to $.ajax, with success and error wrapped */
				url : '/backlog/countBacklog.htm',
				success : function(data) {
					if (data > 0){
						return "<a href='/backlog/list.htm'>您好，您有 " + data + " 条待办事项需要处理，请及时处理</a>";
					}
				}
			});

	$(".user").mouseenter(function() {
		$(".user_xiala").stop(true, true).slideDown();
	}).mouseleave(function() {
		$(".user_xiala").stop(true, true).slideUp();
	})

	$("#top ul.nav li").eq(5).mouseenter(function() {
		$("#top ul.nav li div").stop(true, true).slideDown();
	}).mouseleave(function() {
		$("#top ul.nav li div").stop(true, true).slideUp();
	})

	$("#top ul.nav li").eq(5).mouseenter(function() {
		$("#top ul.nav li span").stop(true, true).slideDown();
	}).mouseleave(function() {
		$("#top ul.nav li span").stop(true, true).slideUp();
	})

	nav();

	function nav() {
		var url = window.location.href;
		url = url.replace("http://", "");
		url = url.substring(url.indexOf('/'));
		if (url.startWith('/systemManager') || url.startWith('/dept/')
				|| url.startWith('/user/') || url.startWith('/notice/')) {
			$('#systemManager').addClass('current');
		} else if (url.startWith('/workbench') || url.startWith('/notices/')
				|| url.startWith('/calendar/') || url.startWith('/doc/')
				|| url.startWith('/backlog/')) {
			$('#workbench').addClass('current');
		} else if (url.startWith('/marketManager') || url.startWith('/bid/')
				|| url.startWith('/review/') || url.startWith('/information/')
				|| url.startWith('/t_customer/')
				|| url.startWith('/project/list.htm')
				|| url.startWith('/project/edit.htm')
				|| url.startWith('/project/show.htm')
				|| url.startWith('/project/new.htm')
				|| url.startWith('/contract/')) {
			$('#marketManager').addClass('current');
		} else if (url.startWith('/project/')
				|| url.startWith('/projectManager') || url.startWith('/step1/')
				|| url.startWith('/step2/') || url.startWith('/step3/')
				|| url.startWith('/step4/') || url.startWith('/step5/')
				|| url.startWith('/step6/') || url.startWith('/step7/')
				|| url.startWith('/step8/') || url.startWith('/step9/')
				|| url.startWith('/step10/') || url.startWith('/step12/')
				|| url.startWith('/step13/') || url.startWith('/step14/')
				|| url.startWith('/step15/')) {
			$('#projectManager').addClass('current');
		} else if (url.startWith('/dailyOfficeManager')) {
			$('#dailyOfficeManager').addClass('current');
		} else if (url.startWith('/financeManager') || url.startWith('/requ/')) {
			$('#financeManager').addClass('current');
		} else if (url.startWith('/knoledgeManager')) {
			$('#knoledgeManager').addClass('current');
		} else if (url.startWith('/cusManager')) {
			$('#cusManager').addClass('current');
		} else if (url.startWith('/performanceManager')) {
			$('#performanceManager').addClass('current');
		} else {

		}
	}

	function updateUser() {
		showIframe('editUser', '', '编辑用户', '${path}/user/edit.htm', 430, 260,
				true);
	}

	function changeRole(roleid) {
		var url = "/changeDept.htm?roleId=" + roleid;
		window.location.href = url;
	}
</script>
</html>
