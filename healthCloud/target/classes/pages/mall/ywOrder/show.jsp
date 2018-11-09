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
					<shiro:hasPermission name="submenu:mall:order:list">
						<li class="active"><a href="<%=contextPath%>/ywOrder/list" target="_self"><span>订单管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:mall:goods:list">
						<li><a href="<%=contextPath%>/goods/list" target="_self"><span>商品管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<ul class="nav nav-tabs">
				<li><a href="list">订单列表</a></li>
				<li class="active"><a href="show?orderId=${order.orderId }">订单详情</a></li>
			</ul>
	
			<div class="col-sm-12" style="padding-left: 0px; padding-right: 0px;">
				<form class="form-horizontal pull-left form-normallr" enctype="multipart/form-data">
					<div class="form-gd-hd">订单基本信息</div>
		      		<table class="table table-bordered">
		          		<tr>
		          			<th>
		          				<label class="control-label">工单编号：</label>
		          			</th>
		               		<td>
								<input id="cext1" type="text" class="form-control" name="cext1" value="${order.cext1 }" readonly="readonly">
		               		</td>
		               		<th>
		               			<label class="control-label">下单时间：</label>
		               		</th>
		               		<td>
		               			<input id="createTime" type="text" class="form-control" name="createTime" value="${order.createTime }" readonly="readonly">
		               		</td>
		          		</tr>
		          		
		          		<tr>
		          			<th>
		               			<label class="control-label">订单状态：</label>
		               		</th>
		               		<td>
		               			<input id="orderStatus" type="text" class="form-control" name="orderStatus" value="${order.orderStatusView }" readonly="readonly">
		               		</td>
		          			<th>
		          				<label class="control-label">订单总价：</label>
		          			</th>
		               		<td>
		              			<input id="orderAmount" type="text" class="form-control" name="orderAmount" value="${order.orderAmount }" readonly="readonly">
		               		</td>
		               	</tr>
		               	
		               	<tr>
		               		<th>
		               			<label class="control-label">运费：</label>
		               		</th>
		               		<td>
		               			<input id="freight" type="text" class="form-control" name="freight" value="${order.freight }" readonly="readonly">
		               		</td>
		               		<th>
		               			<label class="control-label">优惠额：</label>
		               		</th>
		               		<td>
		               			<input id="discounts" type="text" class="form-control" name="discounts" value="${order.discounts }" readonly="readonly">
		               		</td>
		          		</tr>
		          		
				       	<tr>
			           		<th>
			           			<label class="control-label">实际付款：</label>
							</th>
			                <td>
			                	<input id="actualPayment" type="text" class="form-control" name="actualPayment" value="${order.actualPayment }" readonly="readonly">
							</td>
			           		<th>
			           			<label class="control-label">支付类型：</label>
							</th>
			                <td>
			                	<input id="payType" type="text" class="form-control" name="payType" value="${order.payTypeView }" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>
			           			<label class="control-label">支付时间：</label>
							</th>
			                <td>
			                	<input id="payTime" type="text" class="form-control" name="payTime" value="${order.payTime }" readonly="readonly">
							</td>
							<th>
			           			<label class="control-label">物流公司：</label>
							</th>
			                <td>
				            	<input id="logistics" type="text" class="form-control" name="logistics" value="${order.logistics }" readonly="readonly">
							</td>
				      	</tr>
				      	
				      	<tr>
				      		<th>
			           			<label class="control-label">货运单号：</label>
							</th>
			                <td>
						      	<input id="waybill" type="text" class="form-control" name="waybill" value="${order.waybill }" readonly="readonly">
							</td>
			           		<th>
			           			<label class="control-label">收货人：</label>
							</th>
			                <td>
			                	<input id="recipient" type="text" class="form-control" name="recipient" value="${order.memberAddress.recipient }" readonly="readonly">
							</td>
				      	</tr>
				      	
				      	<tr>
				      		<th>
			           			<label class="control-label">联系电话：</label>
							</th>
			                <td>
			                	<input id="tel" type="text" class="form-control" name="tel" value="${order.memberAddress.tel }" readonly="readonly">
							</td>
						</tr>
						<tr>
			           		<th>
			           			<label class="control-label">收货地址：</label>
							</th>
			                <td colspan="3">
			                	<textarea rows="3" class="form-control" name="address" id="address" style="resize:none;" readonly="readonly">${order.memberAddress.address }</textarea>
							</td>
				      	</tr>
		      		</table>
		      		
		      		<div class="form-gd-hd">订单商品信息</div>
		      		<table id="table" class="table table-striped table-hover table-bordered">
			            <tr>
			                <th style="text-align: center;">商品信息</th>
			                <th style="text-align: center;">单价</th>
			                <th style="text-align: center;">数量</th>
			                <th style="text-align: center;">优惠额(元)</th>
			                <th style="text-align: center;">实付(元)</th>
			                <th style="text-align: center;">明细状态</th>
			            </tr>
			            <c:forEach var="orderDetail" items="${orderDetails}" varStatus="status">
			                <tr>
			                    <td style="vertical-align: middle;text-align: center;width: 25%;"><img alt="图片丢失" src="${orderDetail.goods.titleImg }" style="width:100px;height:100px;">${orderDetail.goods.goodsName }</td>
			                    <td style="vertical-align: middle;text-align: center;width: 10%;">${orderDetail.price }</td>
			                    <td style="vertical-align: middle;text-align: center;width: 10%;">${orderDetail.amount }</td>
			                    <td style="vertical-align: middle;text-align: center;width: 15%;">${orderDetail.differMoney}</td>
			                    <td style="vertical-align: middle;text-align: center;width: 15%;">${orderDetail.actualPayment }</td>
			                    <td style="vertical-align: middle;text-align: center;width: 15%;">${orderDetail.statusView }</td>
			                </tr>
			            </c:forEach>
		        	</table>
		    	</form>
		    </div>
	    </div>
    </div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
</body> 
</html>