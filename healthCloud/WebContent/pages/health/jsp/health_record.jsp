<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Health Record</title>
</head>

<jsp:include page="/pages/base/base.jsp"></jsp:include>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2">
				<ul class="nav nav-pills nav-stacked text-center">
					<li class="active"><a href="${pageContext.request.contextPath }/healthService/record?memberId=${member.memberId }" ><span>健康档案</span></a></li>
					<li><a href="${pageContext.request.contextPath }/healthService/data?memberId=${member.memberId }" ><span>健康数据</span></a></li>
					<li><a href="${pageContext.request.contextPath }/healthService/mer?memberId=${member.memberId }" ><span>体检报告</span></a></li>
					<li><a href="${pageContext.request.contextPath }/healthService/proposal?memberId=${member.memberId }" ><span>医师建议</span></a></li>
				</ul>
			</div>
			<div class="col-sm-10">
				<!-- tab strat -->
				<ul class="nav nav-tabs">
					<li class="active"><a
						href="${pageContext.request.contextPath }/healthService/record?memberId=${member.memberId}">健康档案</a></li>
					<li><a
						href="${pageContext.request.contextPath }/healthService/data?memberId=${member.memberId}">健康数据</a></li>
					<li><a
						href="${pageContext.request.contextPath }/healthService/mer?memberId=${member.memberId}">体检报告</a></li>
					<li><a
						href="${pageContext.request.contextPath }/healthService/proposal?memberId=${member.memberId}">医师建议</a></li>
				</ul>
				<!-- tab end -->

				<!-- 健康档案表 strat -->
				<div class="col-sm-11 mar-t10">
					<h4 class="text-center">${member.realName }健康档案</h4>
					<table class="table table-bordered mytable">
						<tr>
							<th colspan="2">姓名</th>
							<td>${member.realName }</td>
							<th>性别</th>
							<td>${not empty member.gender?(member.gender == 1?"男":"女"):""}</td>
							<th>出生日期</th>
							<td>${member.memberInfo.birthday }</td>
						</tr>
						<tr>
							<th colspan="2">本人电话</th>
							<td>${member.phoneNo }</td>
							<th>IMEI号</th>
							<td>${imei }</td>
							<th>会员卡号</th>
							<td>${member.memberId }</td>
						</tr>
						<tr>
							<th colspan="2">身份证号</th>
							<td colspan="2">${member.identityCard }</td>
							<th>工作单位</th>
							<td colspan="2"></td>
						</tr>
						<tr>
							<th colspan="2">药物过敏史</th>
							<td colspan="2">${record.allergicHistory }</td>
							<th>传染病史</th>
							<td colspan="2">${record.communicableDiseases }</td>
						</tr>
						<tr>
							<th rowspan="4" style="vertical-align: middle;">既<br>往<br>史
							</th>
							<th style="vertical-align: middle;">疾<br>病
							</th>
							<td colspan="5">${isHyperlipoidemia }${isDm }${isCancer }${isCerebralApoplexy }${isCopd }${isTuberculosis }${isHepatitis }${isCholesterol }${isHypertension }${isDermatosis }</td>
						</tr>
						<tr>
							<th style="vertical-align: middle;">手<br>术
							</th>
							<td colspan="5">${operation }</td>
						</tr>
						<tr>
							<th style="vertical-align: middle;">外<br>伤
							</th>
							<td colspan="5">${trauma }</td>
						</tr>
						<tr>
							<th style="vertical-align: middle;">输<br>血
							</th>
							<td colspan="5">${transfusion }</td>
						</tr>
						<tr>
							<th colspan="2" rowspan="2" style="vertical-align: middle;">家族史</th>
							<td>父亲</td>
							<td colspan="2">${record.fatherDisease }</td>
							<td>母亲</td>
							<td colspan="2">${record.motherDisease }</td>
						</tr>
						<tr>
							<td>兄弟姐妹</td>
							<td colspan="2">${record.siblingDisease }</td>
							<td>子女</td>
							<td colspan="2">${record.childrenDisease }</td>
						</tr>
						<tr>
							<th colspan="2">遗传病史</th>
							<td colspan="6">${record.inheritedDiseases }</td>
						</tr>
						<tr>
							<th colspan="2">残疾病史</th>
							<td colspan="6">${record.disability }</td>
						</tr>
					</table>
				</div>
				<!-- 健康档案表 end -->
			</div>
		</div>
	</div>

</body>
</body>
</html>