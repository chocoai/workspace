<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>炎黄创新-健康养老服务云平台</title>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: "微软雅黑";
	font-size: 14px;
	background-color: #fff;
}

.main {
	margin: 0 auto;
	width: 100%;
	background-color: #eef2b4;
	height: auto;
	overflow: hidden;
}

.main .top {
	margin: 0 auto;
	width: 100%;
	height: 100px;
	background-color: #f9f5cb;
	line-height: 100px;
	text-align: center;
	text-indent: 30px;
	font-size: 32px;
	font-family: "微软雅黑";
}

.main .content {
	margin: 0 auto;
	width: 100%;
	min-height: 500px;
	height: 500px;
	height: auto !important;
	padding-left: 50px;
	padding-right: 50px;
}

.main .content dl {
	float: left;
	width: 250px;
	padding-top: 10px;
	padding-bottom: 10px;
}

.main .content dl dt {
	float: left;
	width: 250px;
	line-height: 26px;
	text-align: left;
	text-indent: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
}

.main .content dl dd {
	float: left;
	width: 250px;
	line-height: 26px;
	text-align: left;
	padding-left: 10px;
	font-size: 12px;
}

* {
	list-style: none;
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
	color: #666;
}

a:hover {
	text-decoration: underline;
	color: #C00;
}

.main .footer {
	margin: 0 auto;
	width: 100%;
	height: 62px;
	background-color: #f9f5cb;
	padding-top: 10px;
	line-height: 26px;
	font-size: 12px;
}

li {
	list-style: none;
	float: left;
}

#tab {
	float: left;
	margin-top: 20px;
	border-bottom: 2px solid #DEA600;
	height: 38px;
}

#tab ul li {
	padding: 0 15px;
	height: 38px;
	line-height: 38px;
	margin-right: 10px;
	color: #333;
	cursor: pointer;
}

#tab ul li.active {
	background: #DEA600;
	color: #fff;
}
</style>
</head>

<body>
	<center>

		<div class="main">
			<div class="top">炎黄创新健康养老云平台 - 接口一览</div>
			<div class="content">
				<div id="tab" style="width: 100%;">
					<ul>
						<li class="active">一期APP接口</li>
						<li>二期APP接口</li>
						<li>小程序接口</li>
					</ul>
				</div>
				<div class="tab-content">
					<div class="cell">
						<dl>
							<dt>
								<strong>养老APP【普通用户】接口清单</strong>
							</dt>

							<dd>
								<font color="red">- 身份认证</font>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.register.getCaptcha","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"13297930710"}}'>*注册获取验证码接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.register","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"13297930710","captcha":"843270","password":"your_password"}}'>*注册接口</a>

							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.login","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"13297930710","password":"your_password"}}'>*登陆接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.retrievePassword.getCaptcha","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"13297930710"}}'>*找回密码获取验证码接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.retrievePassword.checkCaptcha","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"13297930710","captcha":"843270"}}'>*找回密码校验验证码接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.retrievePassword.resetPassword","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"13297930710","password":"your_password"}}'>*找回密码重置密码接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.profile.update","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"memberId":"12345","realName":"zhangsan","gender":"1","nickName":"张三","identityCard":"423456199012031234","phoneNo":"13212345678","email":"helloworld@yhcrt.com"}}'>*修改个人资料接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.changePassword","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"13297930710","old_password":"old_password","new_password":"new_password"}}'>*修改登录密码接口</a>
							</dd>

							<dd>
								<font color="red">- 健康</font>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getIndexData","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取首页数据接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getTodayStep","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取今日步数接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdSleep","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","current_page":"1","page_size":"3"}}'>*获取睡眠历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdSleep.byTime","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","start_time":"2017-11-10","end_time":"2017-12-20"}}'>*获取睡眠时间段内数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdStep","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取运动历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdStep.byTime","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","start_time":"2017-11-10","end_time":"2017-11-25"}}'>*获取运动时间段内数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdStep.insert","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"7788414","member_id":"1002","step_count":"88888"}}'>*上传运动运动数据接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getLatestPulse","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取最新心率接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdPulse","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取心率历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdPulse.byTime","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","start_time":"2017-11-10","end_time":"2017-11-24"}}'>*获取心率时间段内数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdPulse.upload","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000023678","member_id":"1002","pluse":"150"}}'>*心率数据上传接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodPressure","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取血压历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodPressure.byTime","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","start_time":"2017-11-01","end_time":"2017-11-20"}}'>*获取血压时间段内数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodPressure.insert","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000023678","member_id":"1002","dbp":"91","sbp":"128"}}'>*上传血压数据接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getLatestBloodGlucose","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取血糖最新测量数据接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodGlucose","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","current_page":"1","page_size":"6"}}'>*获取血糖历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodGlucose.byTime","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","start_time":"2017-11-01","end_time":"2017-12-19"}}'>*获取血糖时间段内数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodGlucose.insert","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","bg_type":"1","bg_value":"5.6","data_source":""}}'>*上传血糖数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.location.upload","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000008562","longitude":"114.440622","latitude":"30.529735","addr":"武汉东湖国家湿地公园科教宣传点"}}'>*终端设备定位数据上传接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.bloodOxygen.upload","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000023678","member_id":"1002","bo_value":"98.75"}}'>*血氧数据上传接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.temperature.upload","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000023678","member_id":"1002","temperature":"37.5"}}'>*体温数据上传接口</a>
							</dd>

						</dl>
						<dl>
							<dd>
								<font color="red">- 健康报告</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdStep.forWeek","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>近7天运动数据接口(已弃用)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdSleep.forWeek","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>近7天睡眠数据接口(已弃用)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdPulse.forWeek","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>近7天心率数据接口(已弃用)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodPressure.forWeek","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>近7天血压数据接口(已弃用)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodGlucose.forWeek","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>近7天血糖数据接口(已弃用)</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.healthData.forWeek","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取最近一周的健康数据</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.healthRecord","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取健康档案数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.mer","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取会员的体检报告列表</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.mer.detail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"mer_id":"1001"}}'>*获取体检报告详情数据</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.mer.latest","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*用户最近一次体检报告数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.proposalList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"current_page":"1","page_size":"5","member_id":"1002"}}'>*获取医师建议列表数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.proposal","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"cid":"20"}}'>*获取医师建议详情数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.proposal.insert","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"doc_id":"1002","member_id":"1002","proposal_content":"合理饮食，加强锻炼"}}'>*添加医师建议数据接口</a>
							</dd>



							<dd>
								<font color="red">- 关注</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.myAttention","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*我的关注</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.myFollowing","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*我关注的人列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.myFollowers","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*关注我的人列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getFollowAndScore","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取积分点赞关注（手表显示）</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.myOrgServicer","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*我的机构服务人员列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.toFollow","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1004","imei":"888820000036169","relation_desc":"备注名"}}'>*添加关注人接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.unFollow","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","follower_id":"1004"}}'>*取消关注接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.followerDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","follower_id":"1004"}}'>*关注人员详情接口</a>
							</dd>

							<dd>
								<font color="red">- 周边安全</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getLoction","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1012"}}'>*获取关注人的终端设备位置信息</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getLoctionHis","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000023678","data_date":"2017-12-05"}}'>*获取关注人的运动轨迹</a>
							</dd>


							<dd>
								<font color="red">- 健康预警</font>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.alarmMsg.list","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002",follow_id:"1002","current_page":"1","page_size":"10"}}'>*获取健康预警列表数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.alarmMsg.clear","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"cid":"b94ef5c5350b4166bbaf903ce4550ad4"}}'>*清除健康预警数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.alarmMsg.clearAll","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002",follow_id:"1012"}}'>*清除所有健康预警数据接口</a>
							</dd>
						</dl>
						<dl>

							<dd>
								<font color="red">- 我的</font>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getStepSetting","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*运动目标查询接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.updateStepSetting","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","target_step":"8000"}}'>*运动目标设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getBloodPressureSetting","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*血压安全范围查询接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.updateBloodPressureSetting","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","dbp_min":"60","dbp_max":"90","sbp_min":"90","sbp_max":"140","alarm_switch":"0"}}'>*血压安全范围设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.bindDevice","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","imei":"888820000036169","sim":"13212345678"}}'>*绑定终端设备接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.unBindDevice","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1013","imei":"WH4333002"}}'>*解绑终端设备接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setDefaultDevice","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1013","imei":"WH4333002"}}'>*设置默认终端设备接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getMyDevices","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*我的终端设备列表接口</a>
							</dd>
							<dd>
								<%-- <a target="_blank" href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.device","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"device_id":"1013"}}'>*获取我的终端设备详情接口</a> --%>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.deviceDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169"}}'>*我的终端设备详情接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setLinkman","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169","lankman":"liubei","phone_num":"13212345678"}}'>*终端设置联系人接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.modifyLinkman","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"sos_id":"1062","lankman":"hulili","phone_num":"13212345678"}}'>*终端修改联系人接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.delLinkman","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"sos_id":"1062,1064"}}'>*终端删除联系人接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getLinkman","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169"}}'>*终端设置查询联系人接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setRemind","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169","remind_type":"1","remind_time":"","remind_switch":"0"}}'>*终端设置提醒设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getRemindSet","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169","remind_type":"1"}}'>*终端设置查询提醒设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setHeart","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169","high_heart_rate":"150","low_heart_rate":"56","alarm_switch":"0"}}'>*终端设置心率安全范围设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getHeartSet","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169"}}'>*终端设置查询心率安全范围设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setSecureLocation","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169","longitude":"114.123456","latitude":"30.123456","radius":"12340","alarm_switch":"0"}}'>*终端设置安全定位设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getSecureLocation","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169"}}'>*终端设置查询安全定位设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.suggestionList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_id":"1052","page":"1","size":"5"}}'>*获取意见反馈列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.suggestion","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"cid":"1001"}}'>*获取意见反馈接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.suggestion.insert","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_id":"1052","suggestion_content":"suggestion content 123123"}}'>*提交意见反馈接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.direction","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*App使用说明接口（html页面）</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.share","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*推荐给亲友接口</a>
							</dd>

							<dd>
								<font color="red">- 辅助接口</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/userDirection/index.html'>*app帮助（HTML）</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getNewVersion","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"version_type":"WH"}}'>*版本更新下载</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.totalVersionNum","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"kid":"D11E7A0F40014344A84FA8FA2AC7A884"}}'>*统计版本下载次数</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/uploadServices'>*图片上传接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.thirdLogin","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"login_type":"wechat","uid":"0abcf95f12344b31b4e0d242c4b69ebe"}}'>*第三方登录接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.thirdLogin.getCaptcha","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"phone_num":"13297930710"}}'>*第三方登录获取验证码接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.thirdLogin.bindPhone","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"login_type":"wechat","uid":"0abcf95f12344b31b4e0d242c4b69ebe","phone_num":"13297930710","captcha":"123456","nick_name":"helloworld","gender":"1","head_pic":""}}'>*第三方登录绑定手机号码接口</a>
							</dd>
						</dl>
						<dl>
							<dt>
								<strong>养老APP【管理端】接口清单</strong>
							</dt>

							<dd>
								<font color="red"><strong>-- 工作</strong></font>
							</dd>
							<dd>
								<font color="blue">- 会员管理</font>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.memberList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>*获取会员列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.memberList.condition","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001","param":"请输入会员姓名或者省份证号码"}}'>*搜索会员接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.member","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取会员详情接口</a>
							</dd>


							<dd>
								<font color="blue">- 健康管理</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.memberList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>*获取会员列表接口(同会员管理)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.healthIndexData","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*健康管理首页数据(步数、心率、血压)</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodGlucose","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","current_page":"1","page_size":"6"}}'>*会员血糖记录接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.healthData.forWeek","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*健康报告-健康数据</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.healthReport","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*健康报告-健康档案</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.mer","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"current_page":"1","page_size":"5","member_id":"1002"}}'>*健康报告-体检报告列表</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.mer.detail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"mer_id":"1001"}}'>*健康报告-体检报告详情数据</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.proposalList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"current_page":"1","page_size":"5","member_id":"1002"}}'>*健康报告-医师建议列表</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.proposal","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"cid":"20"}}'>*健康报告-医师建议详情数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.proposal.insert","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"doc_id":"1002","member_id":"1002","proposal_content":"合理饮食，加强锻炼"}}'>*健康报告-添加医师建议</a>
							</dd>

							<dd>
								<font color="blue">- 安全服务</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.device.location","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>*获取在线终端设备的定位信息</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.device.location.search","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001","param":"23678"}}'>*搜索在线终端设备的定位信息</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getLoctionHis","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000023678","data_date":"2018-01-19"}}'>*获取终端用户的运动轨迹</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.deviceList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>*获取在线会员终端列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.device","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"device_id":"1013"}}'>*获取在线会员终端信息接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.deviceList.condition","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001","device_type":"","sim":"","is_used":"","device_name":""}}'>*搜索会员终端接口</a>
							</dd>


							<dd>
								<font color="blue">- 手表设置</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.getDeviceMembers","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>*获取终端用户列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getMyDevices","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取会员的终端设备列表</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.deviceDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169"}}'>*获取会员的终端设备详情</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setLinkman","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000008562","lankman":"7hulili","phone_num":"13212345678"}}'>*用户手表sos设置接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.modifyLinkman","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"sos_id":"1062","lankman":"hulili","phone_num":"13212345678"}}'>*用户手表sos修改</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.delLinkman","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"sos_id":"1078"}}'>*用户手表sos删除</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setSecureLocation","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169","longitude":"114.123456","latitude":"30.123456","radius":"12340","alarm_switch":"0"}}'>*用户手表围栏设置接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setHeart","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888820000036169","high_heart_rate":"150","low_heart_rate":"56","alarm_switch":"0"}}'>*用户手表心率设置接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.updateBloodPressureSetting","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","dbp_min":"60","dbp_max":"90","sbp_min":"90","sbp_max":"140","alarm_switch":"0"}}'>*血压安全范围设置接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.watch.startUse","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"imei":"888830000008562"}}'>*记录手表启用接口</a>
							</dd>
						</dl>
						<dl>

							<dd>
								<font color="red"><strong>-- 工单管理</strong></font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.ToDoWorkOrders","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001","current_page":"1","page_size":"3"}}'>待办工单列表(管理员)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.all","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001","status":"","current_page":"1","page_size":"3"}}'>全部工单列表(管理员)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.orderDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_id":"1172"}}'>获取工单详情</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.getOrgServicers","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>获取机构下的服务人员</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.distribute","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_id":"1173","handler_id":"1017","emp_id":"1081"}}'>分配服务人员(管理员)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.mine","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"emp_id":"1081","status":"","current_page":"1","page_size":"3"}}'>指派给我的工单列表(服务员)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.serviceLog.upload","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_id":"1001","emp_id":"1008","service_content":"","service_pic":"","service_address":""}}'>服务人员提交服务日志信息</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.serviceLog.detail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_id":"1171"}}'>获取工单的服务日志详细信息</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.confirmCompleted","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_id":"1171"}}'>确认工单完成接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.workOrders.traceDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_id":"1171"}}'>获取工单跟踪信息接口</a>
							</dd>

							<dd>
								<font color="red"><strong>-- 消息</strong></font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>获取消息列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>发送消息接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>给会员发消息接口</a>
							</dd>

							<dd>
								<font color="red"><strong>-- 联系人</strong></font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.linkmans","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>*获取联系人列表接口</a>
							</dd>

							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.empList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001","current_page":"1","page_size":"5"}}'>*获取机构下员工列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.docList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001","current_page":"1","page_size":"5"}}'>*获取机构下医生列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.getDeviceMembers","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>*获取终端用户接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.getAppMember","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1001"}}'>*获取APP用户接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.getUserProfile","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_id":"1015"}}'>*获取用户详细信息接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.getMemberImeis","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1007"}}'>*用户详情中关注的IMEI号列表接口</a>
							</dd>

							<dd>
								<font color="red"><strong>-- 我的</strong></font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.getProfile","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_id":"1010"}}'>*获取个人信息接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.updateProfile","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_id":"1083","nick_name":"刘玄德","head_pic":"/imgFile/2017-07-13/437268.jpg","real_name":"刘备","specialty":"","sex":"1","phone_no":"13297121234","email":"helloworld@yhcrt.com","identity_card":"420010198712034567"}}'>*修改个人信息接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.resetPassword","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"","old_password":"","new_password":""}}'>*修改密码接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.suggestion.insert","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_id":"1052","suggestion_content":"suggestion content 2222222"}}'>*意见反馈接口</a>
							</dd>
							<dd>
								<font color="blue">- 手表帮助</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/userDirection/index.html'>*获取手表帮助列表URL的接口（HTML）</a>
							</dd>
							<dd>
								<a target="_blank"
									href='http://api.ejyhealth.com:9995/yhcrtSocket/setting/api?pdata={"sid":"S13","biz":{"imei":"888830000008562"}}'>*手表实时位置接口</a>
							</dd>

							<dd>
								<font color="blue">- 个人app帮助</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.direction","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*获取app帮助列表URL的接口（HTML）</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/userDirection/versionList.html'>*功能介绍</a>
							</dd>
							<dd>
								<font color="blue">- 会员端app分享</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/userDirection/appshare.html'>*获取app会员端分享（HTML）</a>
							</dd>
						</dl>
					</div>

					<div class="cell" style="display: none;">
						<dl>
							<dt>
								<strong>养老APP【会员端】接口清单</strong>
							</dt>
							<dd>
								<font color="red">- 商城首页</font>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>1、获取商品或服务轮播图接口</a>
							</dd>
							<dd style="width: 220px;">
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getRecommendOrg","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>2、获取推荐服务组织、养老机构、日间照料中心、居家养老服务组织接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getDiscountedGoods","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"current_page":"1","page_size":"3"}}'>3、获取限时折扣接口</a>
							</dd>

							<dd>
								<font color="red">- 预约服务</font>
							</dd>
							<dd>
								<a target="_blank"
									title="order_by:排序字段  1:价格升序,2:价格降序,3:分数降序,4:热度降序"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getServices","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"provider_type":"","service_type":"","area":"","order_by":"","current_page":"1","page_size":"6"}}'>4、获取预约服务列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getServiceFilterItem","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>5、获取预约服务筛选接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getServiceDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"service_id":"1002"}}'>6、获取服务详情接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.collectService","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"service_id":"1002","member_id":"1005"}}'>7、收藏服务接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>8、获取服务分享接口</a>
							</dd>

							<dd>
								<font color="red">- 商品</font>
							</dd>
							<dd>
								<a target="_blank" title="order_by:排序字段  1:价格升序,2:价格降序,3:热度降序"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getGoodsList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"first":"","second":"","order_by":"","current_page":"1","page_size":"6"}}'>9、获取商品列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getGoodsFilterItem","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>10、获取商品筛选接口</a>
							</dd>
						</dl>

						<dl>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getGoodsDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"goods_id":"1001"}}'>11、获取商品详情接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.collectGoods","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"goods_id":"1001","member_id":"1005"}}'>12、收藏商品接口</a>
							</dd>
							<dd>
								<font color="red">- 服务机构</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getOrgs","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_type":"","org_nature":"","area":"洪山区","current_page":"1","page_size":"6"}}'>13、获取服务机构列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getOrgsFilterItem","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>14、获取服务机构筛选接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getOrgDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1024"}}'>15、获取服务机构详情接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.collectOrg","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1024","member_id":"1005"}}'>16、收藏服务机构接口</a>
							</dd>

							<dd>
								<font color="red">- 购物车</font>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>17、商品或服务添加到购物车接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>18、获取购物车列表接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>19、删除购物车商品或服务接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>20、生成购物车支付订单接口</a>
							</dd>

							<dd>
								<font color="red">- 支付接口</font>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>21、生成服务工单接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>22、生存商品订单接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>23、获取支付订单信息接口</a>
							</dd>

						</dl>

						<dl>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getMemberAddress","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1005"}}'>24、获取用户收货地址信息列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.addMemberAddress","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"address":"武汉市洪山区雄楚大道1011号核动力运行研究所小区5幢1单元","area":"湖北武汉市洪山区","email":"hello@world.com","is_default":0,"member_id":1005,"phone_num":"13912345678","postcode":"000000","recipient":"王小二","tag":"公司","tel":"18802738283"}}'>25、新增收货地址接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.updateMemberAddress","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"address":"东湖高新区汤逊湖北路光谷总部空间7栋2单元606室","area":"湖北武汉市洪山区","cid":1013,"email":"hello@world.com","is_default":0,"member_id":1005,"phone_num":"13912345678","postcode":"000000","recipient":"张三丰","tag":"公司","tel":"18802738283"}}'>26、编辑收货地址接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.deleteMemberAddress","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"address_id":"1013","member_id":"1005"}}'>27、删除收货地址接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.setDefaultAddress","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"address_id":"1013","member_id":"1005"}}'>28、设置默认收货地址接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>29、支付宝和微信支付接口</a>
							</dd>

							<dd>
								<font color="red">- 养老咨询</font>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>30、养老资讯首页接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>31、获取政策公告列表接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>32、获取新闻资讯列表接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>33、获取近期活动列表接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>34、资讯或活动详情接口</a>
							</dd>

							<dd>
								<font color="red">- 我的订单</font>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>35、获取全部订单接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>36、获取待付款订单接口</a>
							</dd>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>37、获取待收货接口</a>
							</dd>
						</dl>

						<dl>
							<dd>
								<a target="_blank" href='<%=contextPath%>/services?pdata={}'>38、获取已完成的订单接口</a>
							</dd>
							<dd>
								<font color="red">- 我的收藏</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getUserCollect","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1005","collect_type":"goods","current_page":"1","page_size":"6"}}'>39、获取商品收藏列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getUserCollect","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1005","collect_type":"service","current_page":"1","page_size":"6"}}'>40、获取服务收藏列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getUserCollect","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1005","collect_type":"org","current_page":"1","page_size":"6"}}'>41、获取组织机构列表接口</a>
							</dd>
						</dl>

					</div>
					<div class="cell" style="display: none;">
						<dl>
							<dt>
								<strong>养老小程序【普通用户】接口清单</strong>
							</dt>
							<dd>
								<font color="red">- 首页</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getImages","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*top滑动图片</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.register","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"nick_name":"懒小毛","sex":"0","head_pic":"http://XXXX.png","open_id":"123134"}}'>*注册接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getTelCode","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"18627899132"}}'>*获取验证码</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.bindingTel","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"user_code":"18627899132",tel_code:"3456","password":"999999"}}'>*绑定手机下一步</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getAreaCodeByCity","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"city_name":"武汉"}}'>*根据城市获取地区编码</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getItems","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*服务项目/机构类型ITEM</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getHotOrgByCity","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"area_code":"420100"}}'>*当前城市热门机构</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getDiscountedGoods","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"current_page":"1","page_size":"3"}}'>*限时活动</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getItemsMore","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"current_page":"1","page_size":"3"}}'>*更多服务类型及养老资讯</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.selectDictByParam","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"parent_id":"1099"}}'>*根据条件查询数据字典</a>
							</dd>


						</dl>
						<dl>
							<dd>
								<font color="red">- 服务/供应商</font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getServiceProviders","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"provider_type":"A","provider_name":"","area":"武汉","current_page":"1","page_size":"6"}}'>*获取服务供应商列表</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getServiceProviderDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"provider_id":"1001"}}'>*获取服务供应商详情</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getProvidersByGps","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"lng":"114.396504","lat":"30.444973"}}'>*获取当前地理位置附近的服务供应商</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getRecommendProviders","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"service_type":"SERVICE_TYPE_A1","provider_name":"","area":"武汉","current_page":"1","page_size":"6"}}'>*获取热门推荐服务供应商</a>
							</dd>
							
							<dd>
								<a target="_blank"
									title="order_by:排序字段  1:价格升序,2:价格降序,3:分数降序,4:热度降序"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getServices","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"provider_type":"","service_type":"","area":"","order_by":"","current_page":"1","page_size":"6"}}'>获取预约服务列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getServiceFilterItem","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*获取预约服务筛选接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getHotServiceType","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*热门服务类型</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getServiceDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"service_id":"1002"}}'>*获取服务详情接口</a>
							</dd>
							
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getServiceType","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*所有服务类型</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getOrgByServiceType","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"service_type":"照料护理","area_code":"420100","current_page":"1","page_size":"5"}}'>*按服务查询机构列表</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getOrgByType","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_type":"A","area_name":"武汉市","current_page":"1","page_size":"5"}}'>*按机构类型查询机构</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getOrgDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1024"}}'>*养老机构详情</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getMoreOrgComment","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"org_id":"1024","current_page":"1","page_size":"3"}}'>*机构评价更多</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getOrgListByGps","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"lat":"30.546517","lgt":"114.36448","org_type":"","service_type":""}}'>*附近养老机构</a>
							</dd>
						</dl>
						<dl>
							<dd>
								<font color="red"><strong>-- 资讯</strong></font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getRecommendCms","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*资讯top滑动图片</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getCmsChannel","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*资讯分类item</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getCmsContentList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"channel_id":"","current_page":"1","page_size":"3"}}'>*资讯列表数据</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getCmsContentDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"content_id":"1053"}}'>*小程序资讯详情</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getMoreContentComment","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"content_id":"1","current_page":"1","page_size":"3"}}'>*资讯评价更多</a>
							</dd>

							<dd>
								<font color="red">- 首页商品</font>
							</dd>
							<dd>
								<a target="_blank" title="order_by:排序字段  1:价格升序,2:价格降序,3:热度降序"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getGoodsList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"first":"","second":"","order_by":"","current_page":"1","page_size":"6"}}'>*获取商品列表接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.getGoodsFilterItem","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{}}'>*获取商品筛选接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getGoodsDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"goods_id":"0"}}'>*商品详情</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getMoreGoodsComment","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"goods_id":"0","current_page":"1","page_size":"3"}}'>*商品评价更多</a>
							</dd>

						</dl>
						<dl>
							<dd>
								<font color="red"><strong>-- 健康</strong></font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdStep","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取运动历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdPulse","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取心率历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodPressure","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取血压历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdBloodGlucose","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取血糖历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.hdSleep","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取睡眠历史数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.healthRecord","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取健康档案数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.mer.latest","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*用户最近一次体检报告数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.proposalList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"current_page":"1","page_size":"5","member_id":"1002"}}'>*获取医师建议列表数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.proposal","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"cid":"20"}}'>*获取医师建议详情数据接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getHealthData","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*获取健康5项指标接口</a>
							</dd>
							<dd>
								<font color="red"><strong>-- 支付</strong></font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.sendServiceOrder","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"service_id":"1001","service_time":"2017-11-01 ","member_id":"1002","address_id":"1034","pay_type":"alipay","open_id":"oh14h0SNOy5ODkvQUuh0zODRCZ6M"}}'>*服务项目预约</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.payWorkOrder","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_num":"jkyl2017110217131321178","open_id":"oh14h0SNOy5ODkvQUuh0zODRCZ6M"}}'>*服务工单支付</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.sendGoodsOrder","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"goods_id":"1001","member_id":"1002","goods_number":"3","address_id":"1034","pay_type":"alipay","open_id":"oh14h0SNOy5ODkvQUuh0zODRCZ6M"}}'>*商品购买</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.payYwOrder","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_num":"jkyldd2017103109515115924","open_id":"oh14h0SNOy5ODkvQUuh0zODRCZ6M"}}'>*商品订单支付</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.sendCartOrder","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002","address_id":"","pay_type":"wechat","open_id":"oh14h0SNOy5ODkvQUuh0zODRCZ6M"}}'>*购物车支付</a>
							</dd>
						</dl>

						<dl>
							<dd>
								<font color="red"><strong>-- 我的</strong></font>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.addMemberAddress","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"address":"武汉市洪山区雄楚大道1011号核动力运行研究所小区5幢1单元","area":"湖北武汉市洪山区","email":"hello@world.com","isDefault":0,"memberId":1005,"phoneNum":"13912345678","postcode":"000000","recipient":"王小二","tag":"公司","tel":"18802738283"}}'>新增收货地址接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.updateMemberAddress","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"address":"东湖高新区汤逊湖北路光谷总部空间7栋2单元606室","area":"湖北武汉市洪山区","cid":1013,"email":"hello@world.com","isDefault":0,"memberId":1005,"phoneNum":"13912345678","postcode":"000000","recipient":"张三丰","tag":"公司","tel":"18802738283"}}'>编辑收货地址接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getAddressId","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"address":"东湖高新区汤逊湖北路光谷总部空间7栋2单元606室",area:"湖北省武汉市江夏区","member_id":"1004","phone_num":"13912345678","recipient":"张三丰"}}'>获取收货地址id</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.app.profile.update","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"memberId":"12345","headPic":"/yhcx/hc/img/headimg/12345/helloworld.jpg","realName":"zhangsan","gender":"0","nickName":"张三","identityCard":"423456199012031234","phoneNo":"13212345678","email":"helloworld@yhcrt.com"}}'>*完善个人资料接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.manager.app.getInfoByTel","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"phone_no":"13297930710"}}'>*手机号查询个人信息</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.toFollowByTel","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1004","phone_no":"13297930710","relation_desc":"母亲"}}'>*绑定亲属接口</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.myFollowing","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1002"}}'>*我的亲属</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getYwOrderList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1007","order_status":"","current_page":"1","page_size":"3"}}'>*商品订单列表</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getYwOrderDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_num":"jkyldd2017103109515115924"}}'>*商品订单详情</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.setYwOrderStatus","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_num":"jkyldd2017103109515115924"}}'>*更改商品订单状态</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.setWorkOrderStatus","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_num":"jkyldd2017103109515115924"}}'>*更改服务工单状态</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getWorkOrderList","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1007","order_status":"","current_page":"1","page_size":"3"}}'>*服务工单列表</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.getWorkOrderDetail","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_num":"jkyl2017110709584071393"}}'>*服务工单详情</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.addUserComment","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"member_id":"1004","starts":["5","5"],"ref_id":["0","0"],"comment_type":"goods","content":["很好很不错","很好很不错"],"iext1":""}}'>*添加评价</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.refund","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_detail_id":"1261"}}'>*发起退货(对订单详情)</a>
							</dd>
							<dd>
								<a target="_blank"
									href='<%=contextPath%>/services?pdata={"bid":"2013082719093303452","uid":"18971690300","sid":"healthCloud.small.saveRefund","sts":"0","rmk":"","ver":"1","tok":"0~9","biz":{"order_id":"1001","order_detail_id":"1004","refund_type":"不想要了","content":"意见"}}'>*退货保存(对订单详情)</a>
							</dd>
						</dl>

					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
			<div class="footer">
				版权所有&copy;武汉炎黄创新科技服务有限公司 <br /> Copyright&copy;2017 All Rights Reserved
			</div>
		</div>
	</center>

	<script src="<%=contextPath%>/res/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		$(".content").css({ 'min-height' : screen.height - $('.top').height() - $('.footer').height() - 100 + "px" });
		//tab
		$("#tab ul li").click(function() {
			$(this).addClass("active").siblings().removeClass("active");
			var index = $("#tab ul li").index(this);
			$(".tab-content .cell").eq(index).show().siblings().hide();
		})
	</script>
</body>
</html>