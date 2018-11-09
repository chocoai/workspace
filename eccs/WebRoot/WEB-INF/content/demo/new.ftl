<form action="/demo/save.htm" method="post" id="submitForm">
	<input type="hidden" id="userId" name="userId" value=${user.id}>
	<div class="">
		<input type="text" id="title" name="user.username" value=${user.username}>
	</div>
	
	<div class="">
		<input type="text" id="title" name="user.password" value=${user.password}>
	</div>
	
	<div class="">
		<input type="text" id="title" name="user.name" value=${user.name}>
	</div>
	
	<div class="">
		部门：<select name="deptId">
			<option value="">全部</option>
			<#list deptList as dept>
				<option value="${dept.id}" <#if myDept.id == dept.id>selected='selected'</#if> >${dept.name}</option>
			</#list>
		</select>	
	</div>
	
	<div class="ys20">
		<a href="javascript:submitForm()" class="btn2">提交</a>
	</div>
</form>
<script>
	function submitForm() {
		var title = $('#title').val();
		if (title == '') {
			alert('请输入标题');
			return;
		}
		$('#submitForm').submit();
	}
</script>