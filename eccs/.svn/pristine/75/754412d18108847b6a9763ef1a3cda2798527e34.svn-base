<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.js"></script>
﻿<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>

<form action="/calendar/save.htm" name="calendarForm" id="calendarForm">
	<input type="hidden" id="calId" name="calId" value="${calendar.calId}">
	<table border="1" cellspacing="1" cellpadding="1" class="input_table">
		<tr>
			<td class="tab_title">
				日程标题：
			</td>
			<td colspan="3">
				<input style="width: 100%;" type="text" id ="calTitle" name="calTitle" value="${calendar.calTitle}">
			</td>

		</tr>
		<tr>
			<td class="tab_title">
				日程类型：
			</td>
			<td colspan="3">
				<select style="width: 100%;" id ="calType" name="calType">
					<option value="0" <#if calendar.calType== 0>selected='selected'</#if> >
						个人日程
					</option>
					<option value="1" <#if calendar.calType== 1>selected='selected'</#if> >
						部门日程
					</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="tab_title">
				日程类容：
			</td>
			<td colspan="3">
				<textarea style="width: 100%;" rows="3" id ="calContent" name="calContent" >${calendar.calContent}</textarea>
			</td>
		</tr>

		<tr>
			<td class="tab_title">
				开始时间：
			</td>
			<td>
				<input type="text" id="calStartTime" name="calStartTime" value="${calendar.calStartTime}" readonly="readonly"
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d %H:%m',maxDate:'#F{$dp.$D(\'calEndTime\')}'})" />
			</td>
			<td class="tab_title">
				结束时间：
			</td>
			<td>
				<input type="text" id="calEndTime" name="calEndTime" value="${calendar.calEndTime}" readonly="readonly"
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '#F{$dp.$D(\'calStartTime\')||\'%y-%M-%d %H:%m\'}'})" />
			</td>
		</tr>
		<tr>
			<td>
				参与人员：
			</td>
			<td colspan="3">
				<input style="width: 100%;" type="text" id ="calPerson" name="calPerson" value="${calendar.calPerson}">
			</td>

		</tr>

		<tr>
			<td>
				地址：
			</td>
			<td colspan="3">
				<input style="width: 100%;" type="text" id ="calAddress" name="calAddress" value="${calendar.calAddress}">
			</td>
		</tr>

		<tr>
			<td colspan="4" style="text-align: center;">
				<button type="button" id = "btnSave" onclick="javascript:submitForm();">
					保存
				</button>
				<button type="reset" id = "btnReset">
					重置
				</button>
				<button type="button" id = "btnCancel" onclick="javascript:window.close();">
					取消
				</button>
			</td>
		</tr>
	</table>

</form>

<script type="text/javascript">
	$(document).ready(function(){
		$("#calendarForm").validate({
				rules : {  
		  	    		 'calTitle' : { required : true } ,
		  	    		 'calType' : { required : true } ,
		  	    		 'calContent' : { required : true } ,
		  	    		 'calStartTime' : { required : true } ,
		  	    		 'calEndTime' : { required : true }
						}  
		})
	
	});

	function submitForm() {
		var calStartTime = $("#calStartTime").val();
	    var calEndTime = $("#calEndTime").val();
	    calStartTime = calStartTime.replace(/-/g,"/");
	    calEndTime = calEndTime.replace(/-/g,"/");
	    var sTime = new Date(calStartTime);
	    var eTime = new Date(calEndTime);
	    if(sTime>eTime){
	    	alert("开始时间不能大于结束时间");
	    	return
	    }
	    
	    var paras = {};
	    paras.calId = $("#calId").val();
		paras.calTitle = $("#calTitle").val();
		paras.calType = $("#calType").val();
		paras.calContent = $("#calContent").val();
		paras.calStartTime = $("#calStartTime").val();
		paras.calEndTime = $("#calEndTime").val();
		paras.calPerson = $("#calPerson").val();
		paras.calAddress = $("#calAddress").val();
	    
	    $.ajax({
				type:"post",
				url:'/calendar/save.htm',
				data : paras,
				asyn:true,
				dataType:'json',
				cache:false,
				success:function(data){
					if (data.IsSuccess=true) {
						window.close();
					}else{
						alert("创建日程失败,"+ data.Msg);
					}
				}
		});
	    
		//$("#calendarForm").submit();
	}
</script>

