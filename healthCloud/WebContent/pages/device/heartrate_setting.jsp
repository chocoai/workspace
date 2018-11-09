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
	<h4 class="modal-title">心率安全范围设置</h4>
</div>
<form class="form-inline" id="heartrateSettingForm" action="<%=contextPath%>/devicesetting/heartrateSetting" method="post">
	<div class="modal-body">
		<input type="hidden" id="imei" name="imei" value="${imei }">
		<div class="form-group" style="margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label class="control-label" for="highHeartRate">心率高值</label>&nbsp;&nbsp;&nbsp; 
			<input type="text" class="form-control" style="width: 180px;" id="highHeartRate" name="highHeartRate" value="${heartRateSetting.highHeartRate }" placeholder="请设置心率高值">
		</div>

		<div class="form-group" style="margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label class="control-label" for="lowHeartRate">心率低值</label>&nbsp;&nbsp;&nbsp; 
			<input type="text" class="form-control" style="width: 180px;" id="lowHeartRate" name="lowHeartRate" value="${heartRateSetting.lowHeartRate }" placeholder="请设置心率低值">
		</div>
		<div class="form-group" style="margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label class="control-label">告警开关</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="radio" id="switchOn" name="alarmSwitch" value="0" checked="checked">开&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" id="switchOff" name="alarmSwitch" value="1">关
		</div> 
	</div>
	<div class="modal-footer">
		<button type="button" id="saveBtn" class="btn btn-primary">保存</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		
		$(":radio[name='alarmSwitch'][value='${heartRateSetting.alarmSwitch}']").prop("checked", "checked");
		
		$("#heartrateSettingForm").validate(
			{
				rules : {
					highHeartRate : {
						required:true,
						digits : true
					},
					lowHeartRate : {
						required:true,
						digits : true
					}
				},
				messages: {
					highHeartRate : {
						required:'请设置心率高值'
					},
					lowHeartRate : {
						required:'请设置心率低值'
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
			$('#heartrateSettingForm').submit();
			
		});	
		

	});
</script>