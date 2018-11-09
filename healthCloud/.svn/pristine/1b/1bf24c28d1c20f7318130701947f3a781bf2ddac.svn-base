<%@page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">商品退款信息</h4>
</div>
<div class="modal-body">
	<table id="dataTable" class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<th style="width: 6%;">序号</th>
				<th style="width: 9%;">商品名称</th>
				<th style="width: 6%;">单价</th>
				<th style="width: 6%;">数量</th>
				<th style="width: 9%;">支付金额</th>
				<th style="width: 12%;">退款申请时间</th>
				<th style="width: 9%;">退款金额</th>
				<th style="width: 15%;">退款原因</th>
				<th style="width: 8%;">状态</th>
				<th style="width: 10%;">退款操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${order }" var="goods" varStatus="status">
				<tr>
					<td style="text-align: center;">${status.count }</td>
					<td>${goods.goodsName }</td>
					<td>${goods.price }</td>
					<td>${goods.amount }</td>
					<td>${goods.actualPayment }</td>
					<td>${goods.applyTime }</td>
					<td>${goods.refundMoney }</td>
					<td>${goods.refundReson }</td>
					<td>${goods.statusView }</td>
					<td>
						<c:if test="${goods.status == '5' }">
							<button class="btn btn-primary btn-xs" type="button" onclick="toRefund(${goods.refId},'6');">确认</button>
							<button class="btn btn-primary btn-xs" type="button" onclick="toRefund(${goods.refId},'7');">拒绝</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script type="text/javascript">
	function toRefund(cid,status) {
		layer.confirm(status==6?"确定要退款？":"确定要拒绝退款?", {btn: ["确认","取消"]}, function(){
			window.location.href="goodsRefund?cid="+cid+"&status="+status;
		}, function(){
			return;
		});
		
	}
</script>