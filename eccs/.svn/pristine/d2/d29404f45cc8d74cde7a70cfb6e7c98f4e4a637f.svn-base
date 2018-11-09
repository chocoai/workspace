<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/systemManager.htm">系统管理</a>
	>
	<a href="javascript:;">用户列表</a>
</div>
<form action="list.htm" method="get" id="searchForm">
	<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px;">
		<div class="article" style="margin: 0; clear: both; width: 100%;">
			<a href="/user/new.htm">
				<div class="sub_add">添加</div>
			</a>
			<div>
				<input name="name" id="name" placeholder="请输入用户名" value="${name}" type="text"
					style="float: left; line-height: 22px; width: 300px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 20px 0px 20px;"
				/>
			</div>
			<a href="javascript:$('#searchForm').submit()">
				<div class="sub_sear">查询</div>
			</a>
		</div>
	</div>
</form>
<div style="clear: both;"></div>
<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table5 stripe">
	<tr class="head">
		<td style="background-color: #e6f6ff; repeat-x top center; text-align: center; width: 30px;">
			<p>序号</p>
		</td>
		<td style="background-color: #e6f6ff; repeat-x top center; text-align: center;">
			<p>用户账号</p>
		</td>
		<td style="background-color: #e6f6ff; repeat-x top center; text-align: center;">
			<p>用户名</p>
		</td>
		<td style="background-color: #e6f6ff; repeat-x top center; text-align: center;">
			<p>性别</p>
		</td>
		<td style="background-color: #e6f6ff; repeat-x top center; text-align: center;">
			<p>电话</p>
		</td>
		<td style="background-color: #e6f6ff; repeat-x top center; text-align: center; width: 300px;">
			<p>所在部门</p>
		</td>
		<td style="background-color: #e6f6ff; repeat-x top center; text-align: center;">
			<p>创建时间</p>
		</td>
		<td style="background-color: #e6f6ff; repeat-x top center; text-align: center;">
			<p>操作</p>
		</td>

	</tr>
	<#list pageBean.list as u>
	<tr>
		<td width="30" style="text-align: center;">
			<p>${u_index + 1}</p>
		</td>
		<td>
			<p>${u.username}</p>
		</td>
		<td>
			<p>${u.name}</p>
		</td>
		<td style="text-align: center;">
			<p>${u.sexStr}</p>
		</td>
		<td style="text-align: center;">
			<p>${u.tel}</p>
		</td>
		<td>
			<p><#list u.deptList as d> ${d.name}, </#list></p>
		</td>
		<td style="text-align: center;">
			<p>${u.ctime?string('yyyy-MM-dd')}</p>
		</td>
		<td style="text-align: center;">
			<a href="javascript:deleteUser('${u.id}')" title="删除">删除 </a>
			<a href="edit.htm?id=${u.id}" title="编辑">编辑</a>
		</td>
	</tr>
	</#list>
</table>
<div style="width: 90%; margin: 0 auto; min-width: 980px;">
	<#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" />
</div>
<div style="clear: both;"></div>
<script>
	function deleteUser(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}

		var url = "delete.htm?id=" + id;
		$.ajax({
			type : "post",
			url : url,
			asyn : true,
			dataType : 'text',
			success : function(d) {
				if (d == '1') {
					window.parent.location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	}
</script>
