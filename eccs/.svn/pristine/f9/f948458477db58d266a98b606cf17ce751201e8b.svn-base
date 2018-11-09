<div id="home">
	<img src="/images/home.png" width="18" height="15" />
	当前位置：<a href="/workbench.htm">工作台</a> > <a href="/role/list.htm">角色管理</a> > <a href="javascript:;">我的权限</a>
</div>

<div style="clear: both;"></div>

<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
	<tr class="head">
		<td style="background: url(/images/table_head.jpg) repeat-x top center; text-align: center; width: 100px;">
			<p>编号</p>
		</td>
		<td style="background: url(/images/table_head.jpg) repeat-x top center; text-align: center; width: 150px;">
			<p>模块</p>
		</td>
		<td style="background: url(/images/table_head.jpg) repeat-x top center; text-align: center;">
			<p>地址</p>
		</td>
	</tr>
	<#list resList as res>
	
	<tr>
		<td>
			<p><#if res.pid != '0'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</#if><input class="ck_checkbox" type="checkbox" name="" id="ck_${res.id}" value="${res.id}" onchange="checkItem('${res.id}')">&nbsp;${res.id}</p>
		</td>
		<td>
			<p>${res.name}</p>
		</td>
		<td>
			<p>${res.url}</p>
		</td>
	</tr>
	
	</#list>
</table>

<div style="clear: both;"></div>
<script>
	init();
	function init() {
		$(".ck_checkbox").each(function() {
			$(this).attr('checked', false);
		});
	
		<#list myResMap?keys as r>
		id = 'ck_' + '${r}';
		$("#" + id).attr('checked', true);
		</#list>
	}
	

</script>
