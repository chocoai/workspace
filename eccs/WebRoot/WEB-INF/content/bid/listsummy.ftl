<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a>投标总结列表</a>
</div>
<div id="content">
	<form action="/bid/listSummy.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px;">
			<div class="article" style="margin: 0; clear: both; width: 100%;">
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px;"
				>项目编号:</div>
				<div>
					<input name="project.no" type="text" value="${project.no}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;"
				>项目名称:</div>
				<div>
					<input name="project.name" type="text" value="${project.name}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;"
				>投标部门:</div>
				<div>
					<input name="bidSummary.bidDeptName" type="text" value="${bidSummary.bidDeptName}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div class="sub_sear" onclick="javascript:$('#searchForm').submit()">查询</div>
			</div>
		</div>
	</form>
	<div style="clear: both;"></div>
	<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table5 stripe">
		<tr class="head">
			<td style="background-color: #e6f6ff; text-align: center; width: 3%;">
				<p>序号</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 12%;">
				<p>项目编号</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>项目名称</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 15%;">
				<p>投标部门</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 7%;">
				<p>投标日期</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 15%;">
				<p>操作</p>
			</td>
		</tr>
		<#list pageBean.list as bidSummary>
		<tr style="text-align: center;">
			<td>${bidSummary_index + 1}</td>
			<td>
				<span title="${bidSummary.project.no}">${bidSummary.project.no}</span>

			</td>
			<td>
				<span title="${bidSummary.project.name}">${bidSummary.project.name}</span>
			</td>
			<td>
				<span title="${bidSummary.bidDeptName}">${bidSummary.bidDeptName}</span>
			</td>
			<td>
				<span title="${bidSummary.openDate}">${bidSummary.openDate}</span>
			</td>
			<td style="text-align: center;">
				<!--<a href="javascript:delbidSummy(${bidSummary.id})"><img src="../../../images/delete2.png" width="20" height="20" /></a> -->
				<a href="/bid/editSummy.htm?bidSummary.id=${bidSummary.id}" title="编辑">编辑</a>
				<a href="/bid/showSummy.htm?bidSummary.id=${bidSummary.id}" title="查看">查看</a>
			</td>
		</tr>
		</#list>
	</table>
	<div style="width: 90%; margin: 0 auto; min-width: 980px;"><#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page
		pageBean=pageBean formId="searchForm" /></div>

</div>
<script>
	function delbidSummy(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}

		var url = "/bid/deleteSummy.htm?bidSummary.id=" + id;
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

	function addBidSummary() {
		window.parent.location.href = "/bid/newSummy.htm";
	}
</script>



