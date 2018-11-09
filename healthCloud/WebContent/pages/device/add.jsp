<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
					<shiro:hasPermission name="submenu:device:list">
						<li class="active"><a href="<%=contextPath%>/device/list" target="_self"><span>终端列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:setting">
						<li><a href="<%=contextPath%>/devicesetting" target="_self"><span>终端设置</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:video:list">
						<li><a href="<%=contextPath%>/deviceVideo/list" target="_self"><span>视频设备管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:video:monitor">
						<li><a href="<%=contextPath%>/device/videoControl" target="_self"><span>视频监控</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			
			<ul class="nav nav-tabs" style="margin-bottom: 10px;">
				<li role="presentation"><a href="list">终端列表</a></li>
				<li role="presentation" class="active">
					<c:choose>
						<c:when test="${device.deviceId != null }">
							<a href="#">编辑终端设备</a>
						</c:when>
						<c:otherwise>
							<a href="#">新增终端设备</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
			
			<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>
			
			<div class="col-sm-12" style="padding-right: 0px;padding-top: 0px;padding-left: 0px;">
				<form class="form-horizontal pull-left form-normallr" action="save" id="deviceForm" method="post" enctype="multipart/form-data">
			    	<input type="hidden" id="deviceId" name="deviceId" value="${device.deviceId}">
			      	<div class="form-gd-hd">设备基本信息</div>
		      		<table class="table table-bordered">
		      			<tr>
		          			<th>
		          				<label class="control-label"><span style="color: red;">* 所属机构：</span></label>
		          			</th>
		               		<td>
		                       	<div class="input-group">
									<input type="text" class="form-control" id="orgName" name="orgName" value="${device.org.orgName}" placeholder="请选择所属机构" readonly="readonly" > 
									<input type="hidden" name="orgId" id="orgId" value="${device.org.orgId}" /> 
									<span class="input-group-btn">
										<button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal" href="/healthCloud/org/showOrgTree?orgId=${orgId }" data-target="#organizationTree">
											<i class="fa fa-search"></i>
										</button>
									</span>
								</div>
		               		</td>
		               		<th>
								<label class="control-label"><span style="color: red;">* 设备名称：</span></label>
		               		</th>
		               		<td>
								<input type="text" class="form-control" id=deviceName name="deviceName" placeholder="请输入设备名称" value="${device.deviceName}" />
		               		</td>
		          		</tr>
		      		
		          		<tr>
		          			<th>
		          				<label class="control-label"><span style="color: red;">* IMEI号码：</span></label>
		          			</th>
		               		<td>
		                       	<input type="text" class="form-control" id="imei" name="imei" placeholder="请输入设备IMEI号码(格式：888820000036226)" value="${device.imei}" />
		               		</td>
		               		<th>
								<label class="control-label"><span style="color: red;">* 设备型号：</span></label>
		               		</th>
		               		<td>
								<input type="text" class="form-control" id="deviceType" name="deviceType" placeholder="请输入设备型号" value="${device.deviceType}" >
		               		</td>
		          		</tr>
		          		
				       	<tr>
				       		<th>
								<label class="control-label">SIM号码：</label>
							</th>
		               		<td>
								<input type="text" class="form-control" id="sim" name="sim" placeholder="请输入设备SIM号码" value="${device.sim}" >
							</td>
			           		<th>
								<label class="control-label">排序号：</label>
							</th>
			                <td>
			                	<input type="text" class="form-control" id="orderNum" name="orderNum" placeholder="请输入排序号" value="${device.orderNum}" >
							</td>
						</tr>
		          		
			         	<tr>
		                	<th>
		          				<label class="control-label">设备描述：</label>
		                	</th>
		                	<td colspan="3">
								<textarea class="form-control" id="remark" name="remark" placeholder="请输入设备描述信息" style="resize: none;">${device.remark}</textarea>
		                	</td>
			         	</tr>
			         	
			         	<tr>
			         		<th colspan="5">
			         			<button type="submit" class="btn btn-primary">保存</button>&nbsp;
								<button type="reset" class="btn btn-default" >重置</button>
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
		$("#deviceForm").validate({
			rules : {
				orgName : "required",
				deviceName : "required",
				deviceType : "required",
				sim : {
					isMobile : true,
					remote : {
						type : "POST",
						url : "checkSIM",
						data : {
							sim : function() {
								return $("#sim").val();
							},
							deviceId : function() {
								return $("#deviceId").val();
							}
						}
					}
				},
				imei : {
					required : true,
					imei : true,
					remote : {
						type : "POST",
						url : "checkIMEI",
						data : {
							imei : function() {
								return $("#imei").val();
							},
							deviceId : function() {
								return $("#deviceId").val();
							}
						}
					}
				},
				orderNum : {
					digits : true
				}
			},
			messages: {
				imei : {remote : $.validator.format("IMEI已存在，请重新输入")},
				sim : {remote : $.validator.format("SIM已被绑定，请重新输入")}
			},
               errorPlacement:function(error,element) {
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
