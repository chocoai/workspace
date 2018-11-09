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
	<h4 class="modal-title">数据采集频率设置</h4>
</div>
<form class="form-inline" id="dsfSettingForm" action="<%=contextPath%>/devicesetting/dsfSetting" method="post">
	<div class="modal-body">
		<input type="hidden" id="imei" name="imei" value="${imei }">
		<div class="form-group" style="margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label>数据采集类型</label>&nbsp;&nbsp;&nbsp; 
			<select id="dataType" name="dataType" class="form-control" style="width: 196px;">
				<option value="">请选择</option>
				<option value="0">安全定位</option>
				<option value="1">心率</option>
				<option value="2">步长</option>
				<option value="3">睡眠</option>
			</select>
		</div>
		<div class="form-group" style="margin-bottom: 10px;">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label>数据采集频率</label>&nbsp;&nbsp;&nbsp; 
			<select id="sampleFrequency" name="sampleFrequency" class="form-control" style="width: 196px;">
				<option value="">请选择</option>
				<option value="10">10分钟/次</option>
				<option value="30">30分钟/次</option>
				<option value="60">60分钟/次</option>
				<option value="120">120分钟/次</option>
				<option value="240">240分钟/次</option>
				<option value="300">300分钟/次</option>
			</select>
		</div>
	</div>
	<div class="modal-footer">
		<button type="button" id="saveBtn" class="btn btn-primary">保存</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
</form>
<script type="text/javascript">
	$(function() {
		
		$("#dataType").find("option[value='${dsfSetting.dataType}']").attr("selected",true);
		$("#sampleFrequency").find("option[value='${dsfSetting.sampleFrequency}']").attr("selected",true);
		
		$("#dsfSettingForm").validate(
			{
				rules : {
					dataType : "required",
					sampleFrequency : "required"
				},
				messages: {
					dataType : "请选择数据采集类型",
					sampleFrequency : "请选择数据采集频率"
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
			$('#dsfSettingForm').submit();
			
		});	
		
		$('#dataType').change(function() {
			$.ajax({
				cache : true,
				type : "post",
				url : "<%=contextPath%>/devicesetting/getDsfSetting",
				async : false,
				dataType : "json",
				data : {'imei': $("#imei").val(),'dataType':$("#dataType").val()},
				traditional : true,
				success : function(data) {
					if (data) {
						console.info(data);
						$("#sampleFrequency").find("option[value='"+ data.sampleFrequency +"']").prop("selected","selected");
					}else{
						$("#sampleFrequency").find("option[value='']").prop("selected","selected");
					}
				},
				error : function(data) {
					$("#sampleFrequency").find("option[value='']").prop("selected","selected");
				}
			})
			
		});	
	});
</script>