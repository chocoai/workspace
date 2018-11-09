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
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid" >
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
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<ul class="nav nav-tabs">
				<li><a href="list">工单列表</a></li>
				<li class="active"><a href="show?orderId=${workOrder.orderId }">工单详情</a></li>
			</ul>
	
			<div class="col-sm-12" style="padding-left: 0px; padding-right: 0px;">
				<form class="form-horizontal pull-left form-normallr" enctype="multipart/form-data">
					<div class="form-gd-hd">工单基本信息</div>
      					<table class="table table-bordered">
			          		<tr>
			          			<th>
			          				<label class="control-label">工单编号：</label>
			          			</th>
			               		<td>
									<input id="cext1" type="text" class="form-control" name="cext1" value="${workOrder.cext1 }" readonly="readonly">
			               		</td>
			               		<th>
			               			<label class="control-label">订购用户：</label>
			               		</th>
			               		<td>
			               			<input id="memberName" type="text" class="form-control" name="memberName" value="${workOrder.member.realName }" readonly="readonly">
			               		</td>
			          		</tr>
			          		
			          		<tr>
			          			<th>
			          				<label class="control-label">工单内容：</label>
			          			</th>
			               		<td>
			              			<input id="orderContent" type="text" class="form-control" name="orderContent" value="${workOrder.orderContent }" readonly="readonly">
			               		</td>
			               		<th>
			               			<label class="control-label">服务对象：</label>
			               		</th>
			               		<td>
			               			<input id="serviceObject" type="text" class="form-control" name="serviceObject" value="${workOrder.serviceObject }" readonly="readonly">
			               		</td>
			          		</tr>
			          		
					       	<tr>
				           		<th>
				           			<label class="control-label">服务费用：</label>
								</th>
				                <td>
				                	<input type="text" name="serviceFee" class="form-control" id="serviceFee" value="${workOrder.serviceFee}" readonly="readonly">
								</td>
				           		<th>
				           			<label class="control-label">工单状态：</label>
								</th>
				                <td>
				                	<input type="text" name="orderStatus" class="form-control" id="orderStatus" value="${workOrder.orderStatusView }" readonly="readonly">
								</td>
					      	</tr>
					      	
					      	<tr>
				           		<th>
				           			<label class="control-label">联系电话：</label>
								</th>
				                <td>
				                	<input id="tel" type="text" class="form-control" name="tel" value="${workOrder.tel }" readonly="readonly">
								</td>
					      	</tr>
					      	
					      	<tr>
				           		<th>
				           			<label class="control-label">服务地址：</label>
								</th>
				                <td colspan="3">
				                	<textarea rows="3" class="form-control" name="address" id="address" style="resize:none;" readonly="readonly">${workOrder.address }</textarea>
								</td>
					      	</tr>
			      		</table>

						<div class="form-gd-hd">工单其他信息</div>
						<table class="table table-bordered">
		          		<tr>
		          			<th>
		          				<label class="control-label">工单创建时间：</label>
		          			</th>
		               		<td>
								<input type="text" name="createTime" class="form-control" id="createTime" value="${workOrder.createTime}" readonly="readonly">
		               		</td>
		               		<th>
		               			<label class="control-label">预约服务时间：</label>
		               		</th>
		               		<td>
		               			<input type="text" name="startTime" class="form-control" id="startTime" value="${workOrder.startTime}" readonly="readonly">
		               		</td>
		          		</tr>
		          		
		          		<tr>
		          			<th>
		          				<label class="control-label">工单处理人：</label>
		          			</th>
		               		<td>
		              			<input type="text" name="handler" class="form-control" id="handler" value="${workOrder.handlerName}" readonly="readonly">
		               		</td>
		               		<th>
		               			<label class="control-label">处理时间：</label>
		               		</th>
		               		<td>
		               			<input type="text" name="handleTime" class="form-control" id="handleTime" value="${workOrder.cext3}" readonly="readonly">
		               		</td>
		          		</tr>
		               		
				       	<tr>
			           		<th>
			           			<label class="control-label">指派给：</label>
							</th>
			                <td>
			                	<input type="text" name="toUser" class="form-control" id="toUser" value="${workOrder.toUserName}" readonly="readonly">
							</td>
			           		<th>
			           			<label class="control-label">支付类型：</label>
							</th>
			                <td>
			                	<input type="text" name="payType" class="form-control" id="payType" value="${workOrder.payTypeView }" readonly="readonly">
							</td>
				      	</tr>
				      	
				      	<tr>
			           		<th>
			           			<label class="control-label">服务单价：</label>
							</th>
			                <td>
			                	<input type="text" name="unitPrice" class="form-control" id="unitPrice" value="${workOrder.unitPrice}" readonly="readonly">
							</td>
			           		<th>
			           			<label class="control-label">订购数量：</label>
							</th>
			                <td>
			                	<input type="text" name="amount" class="form-control" id="amount" value="${workOrder.amount}" readonly="readonly">
							</td>
				      	</tr>
					      	
				      	<tr>
			           		<th>
			           			<label class="control-label">实际付款：</label>
							</th>
			                <td>
			                	<input type="text" name="actualPayment" class="form-control" id="actualPayment" value="${workOrder.actualPayment}" readonly="readonly">
							</td>
			           		<th>
			           			<label class="control-label">优惠额：</label>
							</th>
			                <td>
			                	<input type="text" name="discounts" class="form-control" id="discounts" value="${workOrder.discounts}" readonly="readonly">
							</td>
				      	</tr>
					      	
				      	<tr>
			           		<th>
			           			<label class="control-label">备注说明：</label>
							</th>
			                <td colspan="3">
			                	<textarea rows="3" class="form-control" name="remark" id="remark" style="resize:none;" readonly="readonly">${workOrder.remark }</textarea>
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