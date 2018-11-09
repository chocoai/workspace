<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">医师建议详情</h4>
</div>
<form class="form-horizontal">
	<div class="modal-body">
		<div class="form-group">
			<label class="col-sm-2 control-label">医师姓名</label>
			<div class="col-sm-4">
				<input type="text" class="form-control"
					value="${proposal.doctor.realName }" disabled="disabled">
			</div>
			<label class="col-sm-2 control-label">建议对象</label>
			<div class="col-sm-4">
				<input type="text" class="form-control"
					value="${proposal.member.realName }" disabled="disabled">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">建议内容</label>
			<div class="col-sm-10">
				<textarea rows="8" cols="" class="form-control"
					style="resize: none;" disabled="disabled">${proposal.proposalContent }</textarea>
			</div>
		</div>

	</div>

	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
</form>
