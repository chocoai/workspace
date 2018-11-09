<div>
	<form action="/demo/list.htm" method="get" id="searchForm">
		姓名: <input type="text" id="name" name="name" value="${name}"> 
		部门：<select name="deptId">
			<option value="">全部</option>
			<#list deptList as dept>
				<option value="${dept.id}" <#if deptId == dept.id>selected='selected'</#if> >${dept.name}</option>
			</#list>
		</select>
		<a href="javascript:$('#searchForm').submit()" class="btn2">查询</a> 
		
		<a href="/demo/new.htm" class="btn3">添加用户</a>
	</form>
</div>

<table width="800" class="ys20">
	<tr>
		<td>编号</td>
		<td>用户名</td>
		<td>姓名</td>
		<td>部门</td>
		<td>创建时间</td>
		<td>操作</td>
	</tr>
	<#list pageBean.list as deptUser>
	<tr>
		<td>${deptUser_index + 1}</td>
		<td>
			<a href="/demo/${deptUser.user.id}" target="_blank">${deptUser.user.username}</a>
		</td>
		<td>${deptUser.user.name}</td>
		<td>${deptUser.dept.name}</td>
		<td>${deptUser.user.ctime}</td>
		<td>
			<a href="/demo/edit.htm?id=${deptUser.id}">编辑</a>
			<a href="javascript:delUser(${deptUser.id})">删除</a>
		</td>
	</tr>
	</#list>
</table>
<div class="ys10">
	<#import "/WEB-INF/tld/page.ftl" as tt>
	<@tt.page pageBean=pageBean formId="searchForm" />
</div>
<script>
	function delUser(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		
		var url = "/user/delete.htm?id=" + id;
		$.ajax({
			type:"post",
			url:url,
			asyn:true,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	}
</script>
