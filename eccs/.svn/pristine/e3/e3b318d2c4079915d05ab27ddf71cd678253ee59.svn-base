<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/systemManager.htm">系统管理</a> >
<a href="javascript:;">角色列表</a>
</div>

<form action="list.htm" method="get" id="searchForm">
	<input type="hidden" name="deptId" id="deptId" value="${deptId}">
</form>
<div class="about">
	<div class="left" style="height:500px;">
		<link type="text/css" rel="stylesheet" href="/js/dtree/dtree.css">
		<script src="/js/dtree/dtree.js"></script>
		<script type="text/javascript">
			var d = new dTree('d');
			d.add('${myCompany.id}','-1','${myCompany.name}',"javascript:search('${myCompany.id}')",'${myCompany.name}','','','');
			<#list companyList as company>
				//d.add('${dept.id}','${dept.pid}','${dept.name}',"javascript:search('${dept.id}')",'','','','');
				d.add('${company.id}','${company.pid}','${company.name}',"javascript:search('${company.id}')",'','','','');
			</#list>
			document.write(d);
		</script>
	</div>
	
	<div class="list_table stripe" style=" float:left;width:70%; min-width:700px;">
		<div class="article" style="margin:0; clear:both; width:100%; margin-left:20px;">
			<a href="new.htm?deptId=${deptId}"><div class="sub_add">添加</div></a>
		</div>
	</div>
	<div>
	<table border="1" cellspacing="1" cellpadding="1" class="list_table stripe" style=" float:left;width:70%; min-width:700px; margin-left:20px;">
		<tr class="head">
			<td style="background-color:#e6f6ff; repeat-x top center; text-align: center; width: 30px;">
				<p>序号</p>
			</td>
			<td style="background-color:#e6f6ff; repeat-x top center; text-align: center; ">
				<p>所属公司</p>
			</td>
			<td style="background-color:#e6f6ff; repeat-x top center; text-align: center;">
				<p>角色名称</p>
			</td>
			<td style="background-color:#e6f6ff; repeat-x top center; text-align: center;">
				<p>创建人</p>
			</td>
			<td style="background-color:#e6f6ff; repeat-x top center; text-align: center;">
				<p>创建时间</p>
			</td>
			<td style="background-color:#e6f6ff; repeat-x top center; text-align: center;">
				<p>操作</p>
			</td>
		</tr>
		
		<#list pageBean.list as role>
		<tr>
			<td width="30" style="text-align: center;">
				<p>${role_index + 1}</p>
			</td>
			<td>
				<p>${role.dept.name}</p>
			</td>
			<td>
				<p>${role.name}</p>
			</td>
			<td>
				<p>${role.user.name}</p>
			</td>
			<td>
				<p>${role.ctime}</p>
			</td>
			<td style="text-align: center;">
				<a href="javascript:deleteRole('${role.id}')" title="删除">删除</a>
				<a href="edit.htm?id=${role.id}" title="编辑">编辑</a>
				<a href="/res/list.htm?roleId=${role.id}">权限设置</a>
			</td>
		</tr>
		</#list>
		<!--
		<#list roleList as role>
		<tr>
			<td width="30" style="text-align: center;">
				<p>${role_index + 1}</p>
			</td>
			<td>
				<p>${role.dept.name}</p>
			</td>
			<td>
				<p>${role.name}</p>
			</td>
			<td>
				<p>${role.user.name}</p>
			</td>
			<td>
				<p>${role.ctime}</p>
			</td>
			<td style="text-align: center;">
				<a href="javascript:deleteRole('${role.id}')" title="删除">删除</a>
				<a href="edit.htm?id=${role.id}" title="编辑">编辑</a>
				<a href="/res/list.htm?roleId=${role.id}">权限设置</a>
			</td>
		</tr>
		</#list>
		-->
	</table>
	</div>
	<div class="list_table stripe" style=" float:left;width:70%; min-width:700px;margin-left:20px;margin-top: -30px;">
		<#import "/WEB-INF/tld/page.ftl" as tt>
		<@tt.page pageBean=pageBean formId="searchForm" />
	</div>
</div>
<div style="clear: both;"></div>
<script>

	function search(id) {
		$("#deptId").val(id);
		$("#searchForm").submit();
	}
	
	function deleteRole(roleId){
		if (!confirm("确定删除此角色吗？")) {
			return;
		}
		var url = "delete.htm?id=" + roleId;
		$.ajax({
			type:"post",
			url:url,
			asyn:true,
			dataType:'text',
			success:function(d) {
				if (d == '1') { 
					window.parent.location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
				
	}

</script>
