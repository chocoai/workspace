
<script src="/js/jquery.js"></script>

<script>
	$(function(){
		$('input[type="text"]').attr('readonly','readonly');
		$("textarea").attr('readonly','readonly');
		$('select').attr('disabled','disabled');
	});
</script>

<form action="" name="calendarForm">
	<input type="hidden" name="calId" value="${calendar.calId}">
	<table border="1" cellspacing="1" cellpadding="1" class="input_table">
		<tr>
			<td class="tab_title">
				日程标题：
			</td>
			<td colspan="3">
				<input style="width: 100%;" type="text" name="calTitle" value="${calendar.calTitle}">
			</td>

		</tr>
		<tr>
			<td class="tab_title">
				日程类型：
			</td>
			<td colspan="3">
				<select style="width: 100%;" name="calType">
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
				<textarea style="width: 100%;" rows="3"  name="calContent" >${calendar.calContent}</textarea>
			</td>
		</tr>

		<tr>
			<td class="tab_title">
				开始时间：
			</td>
			<td>
				<input type="text" name="calStartTime" value="${calendar.calStartTime}" readonly="readonly"  />
			</td>
			<td class="tab_title">
				结束时间：
			</td>
			<td>
				<input type="text" name="calEndTime" value="${calendar.calEndTime}" readonly="readonly"  />
			</td>
		</tr>
		<tr>
			<td>
				参与人员：
			</td>
			<td colspan="3">
				<input style="width: 100%;" type="text" name="calPerson" value="${calendar.calPerson}">
			</td>

		</tr>

		<tr>
			<td>
				地址：
			</td>
			<td colspan="3">
				<input style="width: 100%;" type="text" name="calAddress" value="${calendar.calAddress}">
			</td>
		</tr>

		
	</table>

</form>

