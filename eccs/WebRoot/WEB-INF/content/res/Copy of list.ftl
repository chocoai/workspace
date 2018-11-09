<div id="home">
	<img src="/images/home.png" width="18" height="15" />
	当前位置：<a href="/workbench.htm">工作台</a> > <a href="/role/list.htm">角色管理</a> > <a href="javascript:;">修改权限</a>
</div>

<form action="save.htm" method="post" id="submitForm">
<input type="hidden" name="roleId" id="roleId" value="${roleId}">
<input type="hidden" name="resIdList" id="resIdList" value="">

<div align="center" class="list_table stripe" style="width: 80%; margin: 0 auto; min-width: 980px;">
	<div class="article" style="margin: 0; clear: both; width: 100%;">
		<a href="javascript:submitForm()"><div class="sub_add">
			保存
		</div></a>
	</div>
</div>
</form>
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
	
		<#list list as r>
		id = 'ck_' + '${r.id}';
		$("#" + id).attr('checked', true);
		</#list>
	}
	
	function submitForm() {
		var resIdList = "";
		$(".ck_checkbox").each(function() {
			var flag = $(this).attr('checked');
			var id = $(this).val();
			if (flag == 'checked') {
				resIdList = resIdList + "," + id;
			}
		});
		$('#resIdList').val(resIdList);
		$('#submitForm').submit();
	}

	function checkItem(v) {
		var id = "ck_" + v;
		var ck = $('#' + id).attr('checked');
		
		//大类取消，小类都取消
		if (ck != 'checked' && v.length == 3) {
			$(".ck_checkbox").each(function() {
				var cid = $(this).attr('id');
				if (cid.startWith(id)) {
					$(this).attr('checked', false);
				}
			});
		}
		
		//小类选中，大类必选中
		if (ck == 'checked' && v.length == 6) {
			$('#' + id.substring(0, 6)).attr('checked', true);
		}
	}
</script>
