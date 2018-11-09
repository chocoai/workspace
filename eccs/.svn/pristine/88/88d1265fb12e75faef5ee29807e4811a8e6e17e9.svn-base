<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="javascript:window.location.reload();">待办事项列表</a>
</div>
<div id="content">
	<div style="width: 90%; min-width: 980px; margin: 20px auto;">
		<div class="tabPanel">
			<ul>
				<li style="font-size: 14px;" onclick="location.href = '/backlog/list.htm';">待办事项</li>
				<li class="hit" style="font-size: 14px;" onclick="javascript:getMyFinishedBacklog();">已办事项</li>
				<li style="font-size: 14px;" onclick="javascript:getAllFinishedBacklog();">所有事项</li>
			</ul>
			<div class="panes">
				<div class="pane" style="display: block;">
					<form action="/backlog/getMyFinishedBacklog.htm" method="post" id="searchForm">
						<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px;">
							<div class="article" style="margin: 0; clear: both; width: 100%;">
								<div
									style="float: left; line-height: 28px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px;"
								>项目编号:</div>
								<div>
									<input name="project.no" type="text" value="${project.no}"
										style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
									/>
								</div>
								<div
									style="float: left; line-height: 28px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;"
								>项目名称:</div>
								<div>
									<input name="project.name" type="text" value="${project.name}"
										style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
									/>
								</div>
								<div class="sub_sear" onclick="javascript:$('#searchForm').submit()">查询</div>
							</div>
						</div>
					</form>
					<div style="clear: both;"></div>
					<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
						<tr class="head">
							<td style="background-color: #e6f6ff; text-align: center; width: 3%;">
								<p>序号</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 10%;">
								<p>项目编号</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 25%;">
								<p>项目名称</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 5%;">
								<p>咨询类型</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 10%;">
								<p>编审类型</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 5%;">
								<p>紧急程度</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 6%;">
								<p>立项日期</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 5%;">
								<p>项目负责人</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 5%;">
								<p>操作</p>
							</td>
						</tr>
						<#list pageBean.list as project>
						<tr>
							<td style="text-align: center;">
								<p>${project_index + 1}</p>
							</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${project.no}</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${project.name}</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${project.serviceType.name}</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${project.editorialType.name}</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${project.urgentType.name}</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
								<span title="${project.recordDate?string("yyyy-MM-dd")}">${project.recordDate?string("yyyy-MM-dd")}</span>
							</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${project.recordName}</td>
							<td style="text-align: center;">
								<a href="/backlog/show.htm?project.id=${project.id}">查看</a>
							</td>
						</tr>
						</#list>
					</table>
				</div>
				<div style="width: 90%; margin: -40px auto; min-width: 980px; margin-top: -40px;">
					<#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" />
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function getMyFinishedBacklog(){
		location.href = "/backlog/getMyFinishedBacklog.htm";
	}
	function getAllFinishedBacklog() {
		location.href = "/backlog/getAll.htm";
	}
</script>