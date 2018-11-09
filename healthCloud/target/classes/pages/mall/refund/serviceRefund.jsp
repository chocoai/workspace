<%@page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">服务退款信息</h4>
</div>
<form id="serviceRefundForm" action="serviceRefund" class="form-horizontal" enctype="multipart/form-data">
	<div class="modal-body">
		<input type="hidden" id="id" name="id" value="${order.id }">
		<div class="form-group">
			<label class="col-sm-2 control-label">申请时间</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="applyTime" name="applyTime" value="${order.applyTime }" disabled="disabled">
			</div>
			<label class="col-sm-2 control-label">退款金额</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="refundMoney" name="refundMoney" value="${order.refundMoney }" disabled="disabled">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">退款类型</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="refundType" name="refundType" value="${order.refundType }" disabled="disabled">
			</div>
			<label class="col-sm-2 control-label">
			<span style="color: red;">是否退款</span></label>
			<div class="col-sm-4">
				<select class="form-control" id="status" name="status" required="required">
					<option value="">--选择--</option>
					<option value="6">确认退款</option>
					<option value="7">拒绝退款</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">退款原因</label>
			<div class="col-sm-10">
				<textarea rows="" cols="" class="form-control" id="refundReason" maxlength="256" name="refundReason" style="resize: none;" disabled="disabled">${order.refundReson }</textarea>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<input type="submit" id="submitBtn" class="btn btn-primary" value="确认">
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
</form>