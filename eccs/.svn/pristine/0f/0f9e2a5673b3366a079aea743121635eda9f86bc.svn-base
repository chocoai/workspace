<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a>项目立项列表</a>
</div>

<div id="content">
	<form action="/project/list.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe"
			style="width: 90%; margin: 0 auto; min-width: 980px;">
			<div class="article" style="margin: 0; clear: both; width: 100%;">
				<div class="sub_add" onclick="javascript:addProject();">
					添加
				</div>
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;">
					项目编号:
				</div>
				<div>
					<input name="project.no" type="text" value="${project.no}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;" />
				</div>
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;">
					项目名称:
				</div>
				<div>
					<input name="project.name" type="text" value="${project.name}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;" />
				</div>
				<div class="sub_sear" onclick="javascript:$('#searchForm').submit()">
					查询
				</div>
			</div>
		</div>
	</form>
	<div style="clear: both;"></div>
	<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table5">
		<tr class="head">
			<td style="background-color: #e6f6ff; text-align: center; width: 3%;">
				<p>
					序号
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 10%;">
				<p>
					项目编号
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 15%;">
				<p>
					项目名称
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 7%;">
				<p>
					立项日期
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 15%;">
				<p>
					委托单位
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 5%;">
				<p>
					咨询类型
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 6%;">
				<p>
					编审类型
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 5%;">
				<p>
					紧急程度
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 6%;">
				<p>
					项目负责人
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center; width: 15%;">
				<p>
					操作
				</p>
			</td>
		</tr>
		<#list pageBean.list as project>
		<tr>
			<td width="30" style="text-align: center;">
				<p>
					${project_index + 1}
				</p>
			</td>
			<td>
				<span title="${project.no}"><a href="/project/show.htm?project.id=${project.id}">${project.no}</a></span>
			</td>
			<td>
				<span title="${project.name}"><a href="/project/viewProcessHistory.htm?projectId=${project.id}">${project.name}</a></span>
			</td>
			<td>
				<span title="${project.recordDate?string("yyyy-MM-dd")}">${project.recordDate?string("yyyy-MM-dd")}</span>
			</td>
			<td>
				<span title="${project.customer.cusName}">${project.customer.cusName}</span>
			</td>
			<td>
				<span title="${project.serviceType.name}">${project.serviceType.name}</span>
			</td>
			<td>
				<span title="${project.editorialType.name}">${project.editorialType.name}</span>
			</td>
			<td>
				<span title="${project.urgentType.name}">${project.urgentType.name}</span>
			</td>
			<td>
				<span title="${project.recordName}">${project.recordName}</span>
			</td>
			<td style="text-align: center;">
				<#if project.status ==1 && project.createUser.id == user.id || user.isAdmin() >
					<a href="javascript:delProject(${project.id})">删除</a>
					<a href="/project/edit.htm?project.id=${project.id}">编辑</a> 
				</#if>
				
				<a class="" href="/project/viewProcessHistory.htm?projectInfo.id=${project.projectInfo.id}">查看操作记录</a>
			</td>
		</tr>
		</#list>
	</table>
	<div style="width: 90%; margin: 0 auto; min-width: 980px;">
		<#import "/WEB-INF/tld/page.ftl" as tt> 
		<@tt.page pageBean=pageBean formId="searchForm" />
	</div>
</div>

<script>
	function delProject(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}

		var url = "/project/delete.htm?project.id=" + id;
		$.ajax({
			type : "post",
			url : url,
			asyn : true,
			dataType : 'text',
			success : function(d) {
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	}

	function addProject() {
		window.parent.location.href = "/project/new.htm";
	}
</script>



