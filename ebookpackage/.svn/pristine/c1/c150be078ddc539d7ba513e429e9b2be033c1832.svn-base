<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>平板密码</title>
<meta name="viewport" content="initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="full-screen" content="true">
<meta name="screen-orientation" content="portrait">
<meta name="x5-fullscreen" content="true">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/password/css/style.css">
</head>

<title>平板密码</title>
<script type="text/javascript">
	
</script>
</head>
<body class="internetVoting">

	<form action="${ctx}/api/padPwd/index" method="post" id="pageForm">
		<input type="hidden" name="personId" value="${personId}"> <input
			type="hidden" name="platformCode" value="${platformCode}">
	</form>

	<div class="page-group">
		<div class='page'>
			<div class="content">
				<c:if test="${isShow == '1'}">
					<div class="item-passwordTips">
						<div class="rect">
							<p class="tips">平板管理密码为</p>
							<p class="tips">${pwd}</p>
							<!-- <p class="time">失效时间 ${invaildTime}</p> -->
						</div>
						<p class="password-btn">
							<a href="javascript:void(0)" onclick="re()" class="btn-green">刷新</a>
						</p>
					</div>
				</c:if>

				<c:if test="${isShow == '2'}">
					<div class="item-passwordTips">
						<div class="rect">
							<p class="tips">您没有权限获取平板密码</p>
							<p class="tips">请联系学校管理员</p>
						</div>
						<p class="password-btn">
						</p>
					</div>
				</c:if>

				<div class="site-footanimate">
					<p class="foot-l footico">
						<img
							src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/password/images/foot_animatebg_l.png"
							alt="">
					</p>
					<p class="foot-r footico">
						<img
							src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/password/images/foot_animatebg_r.png"
							alt="">
					</p>
					<p class="tortoise footico">
						<img
							src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/password/images/tortoise.png"
							alt="">
					</p>
					<p class="fish footico">
						<img
							src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/password/images/fish.png"
							alt="">
					</p>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function closeWindows() {
			var userAgent = navigator.userAgent;
			if (userAgent.indexOf("Firefox") != -1
					|| userAgent.indexOf("Chrome") != -1) {
				window.location.href = "about:blank";
				close();//直接调用JQUERY close方法关闭
			} else {
				window.opener = null;
				window.open("", "_self");
				window.close();
			}
		};
		function re() {
			$("#pageForm").submit();
		}
	</script>
</body>
</html>
