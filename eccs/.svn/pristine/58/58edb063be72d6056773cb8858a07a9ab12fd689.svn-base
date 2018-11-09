<div id="map"><img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/systemManager.htm">系统管理</a> >
<a href="/role/list.htm">角色列表</a> >
<a href="javascript:;">角色新增</a>
</div>

<form action="save.htm" method="post" id="submitForm">
	<input name="id" id="id" type="hidden" value="${role.id}" />
	<input name="deptId" id="deptId" type="hidden" value="${deptId}" />
	<div id="content">
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td class="tab_title red"><p>所属公司</p></td>
				<#if role.id != null>
				<td colspan="4">
					<input type="text" value="${role.dept.name}" class="text_a" disabled=disabled/>
				</td>
				<#else>
				<td colspan="4">
					<input type="text" value="${dept.name}" class="text_a" disabled=disabled/>
				</td>
				</#if>
				<td width="" class="tab_title"><p>&nbsp;</p></td>
			</tr>
			<tr>
				<td class="tab_title red"><p>角色名称</p></td>
				<td colspan="4"><input name="role.name" id="name" type="text" value="${role.name}" class="text_a" /></td>
				<td width="" class="tab_title"><p>&nbsp;</p></td>
			</tr>
			<tr> 
				<td colspan="5" style=" text-align:right;">
					<input type="button" value="返回" class="sub" onclick="history.back()"/> 
					<input type="button" value="保存" class="sub" onclick="submitForm()" />
				</td>
				<td width="" class="tab_title"><p>&nbsp;</p></td>
			</tr>
		</table>
	</div>
</form>

<script>
	function submitForm() {
		$("#submitForm").submit();
	}
</script>