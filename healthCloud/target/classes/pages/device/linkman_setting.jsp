<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">联系人设置</h4>
</div>
<form class="form-inline" id="linkmanSettingForm" action="<%=contextPath%>/devicesetting/linkmanSetting" method="post">
	<div class="modal-body">
		<input type="hidden" id="imei" name="imei" value="${imei }">
		<c:forEach items="${linkmans }" var="linkman" varStatus="status">
			<div class="form-group" style="margin-bottom: 10px;">
				<!-- <label>联系人</label>&nbsp;&nbsp;&nbsp;  -->
				<input type="hidden" name="cid" value="${linkman.cid }">
				<input type="text" class="form-control" id="lankman" name="lankman" value="${linkman.lankman }" placeholder="请输入联系人姓名">&nbsp;&nbsp;&nbsp;
				<input type="tel" class="form-control" id="phoneNum" name="phoneNum" value="${linkman.phoneNum }" placeholder="请输入联系人手机号码">
			</div>
		</c:forEach>
		<!-- 
		<div class="form-group" style="margin-bottom: 10px;">
			<label>联系人</label>&nbsp;&nbsp;&nbsp; 
			<input type="text" class="form-control" id="lankman" name="lankman" placeholder="请输入联系人姓名">&nbsp;&nbsp;&nbsp;
			<input type="tel" class="form-control" id="phoneNum" name="phoneNum" placeholder="请输入联系人手机号码">
		</div>
		<div class="form-group" style="margin-bottom: 10px;">
			<label>联系人</label>&nbsp;&nbsp;&nbsp; 
			<input type="text" class="form-control" id="lankman" name="lankman" placeholder="请输入联系人姓名">&nbsp;&nbsp;&nbsp;
			<input type="tel" class="form-control" id="phoneNum" name="phoneNum" placeholder="请输入联系人手机号码">
		</div>
		<div class="form-group" style="margin-bottom: 10px;">
			<label>联系人</label>&nbsp;&nbsp;&nbsp; 
			<input type="text" class="form-control" id="lankman" name="lankman" placeholder="请输入联系人姓名">&nbsp;&nbsp;&nbsp;
			<input type="tel" class="form-control" id="phoneNum" name="phoneNum" placeholder="请输入联系人手机号码">
		</div> 
		-->
	</div>
	<div class="modal-footer">
		<button type="button" id="saveBtn" class="btn btn-primary">保存</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		$("#linkmanSettingForm").validate(
			{
				rules : {
					phoneNum : {
						isMobile : true
					}
				},
                errorPlacement:function(error,element) {
                	layer.tips($(error).text(), element, {
                		tips: 3,
                		tipsMore: true
                	});
                }
	
			}

		);
		
		$('#saveBtn').click(function() {
			var imei = $("#imei").val();
			$.ajax({
				cache : true,
				type : "post",
				url : "<%=contextPath%>/devicesetting/linkmanSetting",
				async : false,
				dataType : "json",
				data : $('#linkmanSettingForm').serialize(),
				traditional : true,
				success : function(data) {
					if (data.result) {
						layer.msg(data.msg, { anim: 6 });
						$('#linkmanSetting').modal('hide');
						window.location.href = "<%=contextPath%>/devicesetting?imei="+imei;
					}else{
						layer.msg(data.msg, { anim: 6 });
					}
				},
				error : function(data) {
					layer.msg('联系人设置失败', { anim: 6 });
				}
			})
		});	
	});
</script>