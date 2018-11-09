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
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<li><a href="<%=contextPath%>/device/list" target="_self"><span>终端列表</span></a></li>
					<li><a href="<%=contextPath%>/devicesetting" target="_self"><span>终端设置</span></a></li>
					<li class="active"><a href="<%=contextPath%>/deviceVideo/list" target="_self"><span>视频设备管理</span></a></li>
					<li><a href="<%=contextPath%>/device/videoControl" target="_self"><span>视频监控</span></a></li>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			
			<ul class="nav nav-tabs" style="margin-bottom: 10px;">
				<li role="presentation"><a href="list">视频设备列表</a></li>
				<c:choose>
					<c:when test="${empty video.cid}">
						<li role="presentation" class="active"><a href="#">新增视频设备</a></li>
					</c:when>
					<c:otherwise>
						<li role="presentation" class="active"><a href="#">编辑视频设备</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			
			<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>
			
			<div class="col-sm-12" style="padding-right: 0px;padding-top: 0px;padding-left: 0px;">
				<form class="form-horizontal pull-left form-normallr" action="save" id="deviceVideoForm" method="post" enctype="multipart/form-data">
			    	<input type="hidden" name="cid" value="${video.cid}">
			      	<div class="form-gd-hd">视频设备基本信息</div>
		      		<table class="table table-bordered">
		      			<tr>
		          			<th><span style="color: red;">* 所属机构：</span></th>
		               		<td>
		                       	<div class="input-group">
									<input type="text" class="form-control" id="orgName" name="orgName" value="${org.orgName}" placeholder="请选择所属机构" readonly="readonly" > 
									<input type="hidden" name="orgId" id="orgId" value="${org.orgId}" /> 
									<span class="input-group-btn">
										<button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal" href="/healthCloud/org/showOrgTree?orgId=${orgId }" data-target="#organizationTree">
											<i class="fa fa-search"></i>
										</button>
									</span>
								</div>
		               		</td>
		               		<th><span style="color: red;">* 设备名称：</span></th>
		               		<td>
								<input type="text" class="form-control" id=deviceName name="deviceName" placeholder="请输入设备名称" value="${video.deviceName}" >
		               		</td>
		          		</tr>
		      		
		          		<tr>
		          			<th><span style="color: red;">* 设备id：</span></th>
		               		<td>
								<input type="text" class="form-control" id="deviceId" name="deviceId" placeholder="请输入设备id" value="${video.deviceId}" >
		               		</td>
		               		<th><span style="color: red;">* 设备型号：</span>
		               		</th>
		               		<td>
								<input type="text" class="form-control" id="deviceModel" name="deviceModel" placeholder="请输入设备型号" value="${video.deviceModel}" >
		               		</td>
		          		</tr>
		          		
				       	<tr>
				       		<th><span style="color: red;">* 设备状态：</span></th>
		               		<td>
								<select id="status" name="status" class="form-control">
							  		<option value="">全部</option>
							  		<option value="1">启用</option>
							  		<option value="2">禁用</option>
							  		<option value="3">损坏</option>
								</select>
							</td>
			           		<th>设备厂商：</th>
			                <td>
			                	<input type="text" class="form-control" id="deviceVendor" name="deviceVendor" value="${video.deviceVendor}">
							</td>
						</tr>
		          		
			         	<tr>
		                	<th><span style="color: red;">* 安装地址：</span></th>
		                	<td colspan="3">
								<textarea class="form-control" id="address" name="address" placeholder="请输入设备安装地址" style="resize: none;">${video.address}</textarea>
		                	</td>
			         	</tr>
			         	
			         	<tr>
			         		<th colspan="5">
			         			<button type="submit" class="btn btn-primary">保存</button>&nbsp;
								<a class="btn btn-default" href="list">取消</a>
							</th>
			         	</tr>
		      		</table>
			    </form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	$(function() {
		$("#status").val("${video.status}");
		$("#deviceVideoForm").validate({
			rules : {
				orgName : "required",
				deviceId : "required",
				deviceName : "required",
				deviceModel : "required",
				address : "required",
				status : "required"
			},
               errorPlacement:function(error,element){
               	layer.tips($(error).text(), element, {
               		tips: 3,
               		tipsMore: true
               	});
               }
		});
	});
</script>
</body>
</html>
