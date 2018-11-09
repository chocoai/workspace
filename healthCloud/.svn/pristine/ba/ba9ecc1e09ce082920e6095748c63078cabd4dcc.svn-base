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
	<h4 class="modal-title">提醒设置</h4>
</div>
<form class="form-inline" id="remindSettingForm" action="<%=contextPath%>/devicesetting/remindSetting" method="post">
	<div class="modal-body">
		<input type="hidden" id="imei" name="imei" value="${imei }">
		<div class="form-group" style="margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label for="remindTime" class="control-label">提醒时间</label>&nbsp;&nbsp;&nbsp; 
			<div class="input-group date form_time" style="width: 196px;" data-date-format="hh:ii" data-link-field="remindTime" data-link-format="hh:ii">
				<input class="form-control" id="input_time" name="input_time" size="9" type="text" value="" readonly>
				<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> 
				<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
			</div>
			<input type="hidden" id="remindTime" name="remindTime" value="${remindSetting.remindTime }" /><br />
		</div>

		<div class="form-group" style="margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label>提醒类型</label>&nbsp;&nbsp;&nbsp; 
			<select id="remindType" name="remindType" class="form-control" style="width: 196px;">
				<option value="0">步长</option>
				<option value="1">心率</option>
				<option value="2">天气</option>
			</select>
		</div>
		<div class="form-group" style="margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label>提醒开关</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="radio" id="switchOn" name="remindSwitch" value="0" checked="checked">开&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" id="switchOff" name="remindSwitch" value="1">关
		</div> 
	</div>
	<div class="modal-footer">
		<button type="button" id="saveBtn" class="btn btn-primary">保存</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		
		$("#remindType").find("option[value='${remindSetting.remindType}']").attr("selected",true);
		$(":radio[name='remindSwitch'][value='${remindSetting.remindSwitch}']").prop("checked", "checked");
		
		
		$('.form_time').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			format: 'hh:ii',
			startView: 1,
			minView: 0,
			maxView: 1,
			//pickerPosition: "bottom-left",
			forceParse: 0
	    });
		
		$("#input_time").val('${remindSetting.remindTime}');
		
		$("#remindSettingForm").validate(
			{
				rules : {
					input_time : "required"
				},
				messages: {
					input_time : "请设置提醒时间"
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
			$('#remindSettingForm').submit();
			
		});	
		
		$('#remindType').change(function() {
			$.ajax({
				cache : true,
				type : "post",
				url : "<%=contextPath%>/devicesetting/getRemindSetting",
				async : false,
				dataType : "json",
				data : $('#remindSettingForm').serialize(),
				traditional : true,
				success : function(data) {
					if (data) {
						$("#input_time").val(data.remindTime);
						$(":radio[name='remindSwitch']").each(function() {
							if ($(this).val() == data.remindSwitch) {
								$(this).prop("checked", "checked");
							}
						});
					}else{
						$("#input_time").val("");
					}
				},
				error : function(data) {
					$("#input_time").val("");
				}
			})
			
		});	
	});
</script>