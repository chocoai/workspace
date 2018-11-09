<%@page language="java" pageEncoding="utf-8"%>

<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%
	String contextPath = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>个人设置</title>

<jsp:include page="/pages/base/base.jsp"></jsp:include>
<style type="text/css">
.form-control {
	width: 50%;
}

.form-horizontal .form-actions {
	padding-left: 180px;
}
</style>
</head>
<body>
	<div class="container-fluid" style="margin-top: 20px; float: left;">
		<div style="float: left; width: 150px;">
			<ul class="nav nav-pills nav-stacked" style="width: 150px; text-align: center;">
				<li class="active"><a href="<%=contextPath%>/personal/index">个人信息</a></li>
				<li><a href="<%=contextPath%>/pages/personal/updatePwd.jsp">修改密码</a></li>
			</ul>
		</div>
		<c:if test="${not empty emp}">
			<div style="float: left; width: 600px; margin-left: 200px;">
				<form id="empForm" class="form-horizontal" id="perForm" action="<%=contextPath%>/personal/update" method="post">
					<div class="form-group">
						<label class="col-sm-2 control-label">所属机构:</label>
						<div class="col-sm-10">
							<input type="hidden" name="empId" value="${emp.empId}"> 
							<input type="text" disabled="disabled" value="${emp.org.orgName}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">职位:</label>
						<div class="col-sm-10">
							<input type="text" name="specialty" value="${emp.specialtyView}" disabled="disabled" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名:</label>
						<div class="col-sm-10">
							<input type="text" name="realName" value="${emp.realName}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">身份证:</label>
						<div class="col-sm-10">
							<input type="text" disabled="disabled" value="${emp.identityCard}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">手机:</label>
						<div class="col-sm-10">
							<input type="text" id="phoneNo" name="phoneNo" value="${emp.phoneNo}" class="form-control"> 
							<span id="phoneNo1" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-10">
							<input type="text" id="email" name="email" value="${emp.email}" class="form-control"> 
							<span id="email1" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">个人简介:</label>
						<div class="col-sm-10">
							<textarea class="form-control" style="resize: none;" name="remark" value="${emp.remark}" rows="5">${emp.remark}</textarea>
						</div>
					</div>
					<div class="form-actions">
						<input id="btnSubmit" class="btn btn-primary" value="保 存" type="submit">
					</div>
				</form>
			</div>
		</c:if>

		<c:if test="${not empty doc}">
			<div style="float: left; width: 600px; margin-left: 200px;">
				<form id="docForm" class="form-horizontal" id="perForm" action="<%=contextPath%>/personal/update" method="post">
					<div class="form-group">
						<label class="col-sm-2 control-label">所属机构:</label>
						<div class="col-sm-10">
							<input type="hidden" name="docId" value="${doc.docId}"> 
							<input type="text" disabled="disabled" value="${doc.org.orgName}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">职位:</label>
						<div class="col-sm-10">
							<input type="text" name="specialty" value="${doc.specialty}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名:</label>
						<div class="col-sm-10">
							<input type="text" name="realName" value="${doc.realName}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">身份证:</label>
						<div class="col-sm-10">
							<input type="text" disabled="disabled" value="${doc.identityCard}" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">手机:</label>
						<div class="col-sm-10">
							<input type="text" id="phoneNo" name="phoneNo" value="${doc.phoneNo}" class="form-control"> 
							<span id="phoneNo1" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-10">
							<input type="text" id="email" name="email" value="${doc.email}" class="form-control"> 
							<span id="email1" style="color: red"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">个人简介:</label>
						<div class="col-sm-10">
							<textarea class="form-control" name="remark" value="${doc.remark}" rows="5">${doc.remark}</textarea>
						</div>
					</div>
					<div class="form-actions">
						<input id="btnSubmit" class="btn btn-primary" value="保 存" type="submit">
					</div>
				</form>
			</div>
		</c:if>
	</div>

	<script type="text/javascript">
		$("#empForm").validate({
			rules : {
				phoneNo : {
					isMobile : true
				},
				email : {
					email : true
				},
			},
			errorPlacement : function(error, element) {
				layer.tips($(error).text(), element, {
					tips : 3,
					tipsMore : true
				});
			}
		})

		$("#docForm").validate({
			rules : {
				phoneNo : {
					isMobile : true
				},
				email : {
					email : true
				},
			},
			errorPlacement : function(error, element) {
				layer.tips($(error).text(), element, {
					tips : 3,
					tipsMore : true
				});
			}
		})

		if ("${result}" != null && "${result}" != "") {
			layer.msg('${result}', {
				time : 2000, //20s后自动关闭
			});
		}
	</script>
</body>
</html>