<%@page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">发货</h4>
</div>
<form class="form-horizontal" id="orderForm" method="post" action="shipment">
	<div class="modal-body">
		<input id="orderId" type="hidden" name="orderId" value="${order.orderId }">
		<table class="table table-bordered">
        	<tr>
        		<th>
       				<label class="control-label">物流公司：</label>
       			</th>
          		<td>
					<input id="logistics" type="text" class="form-control" name="logistics" value="${order.logistics }">
           		</td>
           		<th>
          			<label class="control-label">货运单号：</label>
           		</th>
           		<td>
           			<input id="waybill" type="text" class="form-control" name="waybill" value="${order.waybill }">
           		</td>
       		</tr>
       		
       		<tr>
        		<th>
       				<label class="control-label">收货人：</label>
       			</th>
          		<td>
					<input id="recipient" type="text" class="form-control" readonly="readonly" name="recipient" value="${order.memberAddress.recipient }">
           		</td>
           		<th>
          			<label class="control-label">联系电话：</label>
           		</th>
           		<td>
           			<input id="tel" type="text" class="form-control" readonly="readonly" name="tel" value="${order.memberAddress.tel }">
           		</td>
       		</tr>
       		
       		<tr>
        		<th>
       				<label class="control-label">运费：</label>
       			</th>
          		<td>
					<input id="freight" type="text" class="form-control" name="freight" value="${order.freight }">
           		</td>
           		<th>
          			<label class="control-label">收货地址：</label>
           		</th>
           		<td>
           			<input id="address" type="text" class="form-control" readonly="readonly" name="address" value="${order.memberAddress.address }">
           		</td>
       		</tr>
       		<tr>
        		<th colspan="5">
        			<input id="submitBtn" type="submit" class="btn btn-primary" value="提交" />
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</th>
        	</tr>
   		</table>
	</div>
</form>
<script type="text/javascript">
	$("#submitBtn").click(function() {
		return validata();
	});
	
	function validata() {
		$("#orderForm").validate({
			rules : {
				logistics : "required",
				waybill : {
					required : true,
					number : true
				},
				freight : "number"
			},
			errorPlacement : function(error, element) {
				layer.tips($(error).text(), element, {
					tips : 3,
					tipsMore : true
				});
			}
		});
	}
</script>