<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<% String contextPath = request.getContextPath(); %>
<link href="<%=contextPath%>/res/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<script src="<%=contextPath%>/res/plugins/blueimp/js/jquery.blueimp-gallery.min.js"></script>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden; ">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:workOrder:list">
						<li class="active"><a href="<%=contextPath%>/workOrder/list" target="_self"><span>工单管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:service:list">
						<li><a href="<%=contextPath%>/service/list" target="_self"><span>服务管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>

		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right"style="padding-left: 0px; ">
			<ul class="nav nav-tabs">
				<li><a href="list">工单列表</a></li>
				<li class="active"><a href="showLog?orderId=${serviceLog.orderId }">服务日志</a></li>
			</ul>

			<div class="col-sm-12" style="padding-left: 0px; ">
				<form class="form-horizontal pull-left form-normallr" enctype="multipart/form-data">
					<div class="form-gd-hd">服务日志信息</div>
					<table class="table table-bordered">
						<tr>
							<th><label class="control-label">工单编号：</label></th>
							<td><input id="cext1" type="text" class="form-control" name="cext1" value="${serviceLog.workOrder.cext1 }" readonly="readonly"></td>
							<th><label class="control-label">订购服务：</label></th>
							<td><input id="serviceName" type="text" class="form-control" name="serviceName" value="${serviceLog.workOrder.service.serviceName }" readonly="readonly"></td>
						</tr>

						<tr>
							<th><label class="control-label">工单内容：</label></th>
							<td><input type="text" class="form-control" name="orderContent" id="orderContent" value="${serviceLog.workOrder.orderContent }" readonly="readonly"></td>
							<th><label class="control-label">服务对象：</label></th>
							<td><input type="text" name="serviceObject" class="form-control" id="serviceObject" value="${serviceLog.workOrder.serviceObject}" readonly="readonly"></td>
						</tr>
						
						<tr>
							<th><label class="control-label">服务地址：</label></th>
							<td colspan="3"><textarea class="form-control" name="address" id="address" style="resize: none;" readonly="readonly">${serviceLog.workOrder.address }</textarea></td>
						</tr>

						<tr>
							<th><label class="control-label">服务时间：</label></th>
							<td><input type="text" name="createTime" class="form-control" id="createTime" value="${serviceLog.createTime}" readonly="readonly"></td>
							<th><label class="control-label">服务处理人：</label></th>
							<td><input type="text" name="handler" class="form-control" id="handler" value="${serviceLog.workOrder.toUserName}" readonly="readonly"></td>
						</tr>

						<tr>
							<th><label class="control-label">用户评分：</label></th>
							<td><input type="text" name="score" class="form-control" id="score" value="${serviceLog.score}" readonly="readonly"></td>
						</tr>

						<tr>
							<th><label class="control-label">用户评价：</label></th>
							<td colspan="3"><textarea class="form-control" name="serviceComment" id="serviceComment" style="resize: none;" readonly="readonly">${serviceLog.serviceComment }</textarea>
							</td>
						</tr>
						
						<tr>
							<th><label class="control-label">服务描述：</label></th>
							<td colspan="3"><textarea class="form-control" name="serviceContent" id="serviceContent" style="resize: none;" readonly="readonly">${serviceLog.serviceContent }</textarea></td>
						</tr>

						<tr>
							<th><label class="control-label">服务场景图：</label></th>
							<td colspan="3">
								<div class="row" style="margin-bottom: 30px;">
									<div class="lightBoxGallery">
										<c:forEach items="${imgList }" var="img" varStatus="status">
											<div class="col-xs-6 col-md-3" style="margin-bottom:30px;">
												<a href="${img.imgPath }" title="服务场景图" data-gallery="">
													<img style="width: 100%; height: 180px;" src="${img.imgPath }" alt="图片丢失">
												</a>
											</div>
										</c:forEach>
										<div id="blueimp-gallery" class="blueimp-gallery">
											<div class="slides"></div>
											<h3 class="title"></h3>
											<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a>
											<a class="play-pause"></a>
											<ol class="indicator"></ol>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
</body>
</html>