<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/projectManager.htm">项目管理</a>
	>
	<a href="javascript:window.location.reload();"> 我的项目</a>
</div>
<div id="content">
	<div style="width: 90%; min-width: 980px; margin: 20px auto;">
		<div class="tabPanel">
			<ul>
				<li onclick="javascript:getMyProject();">待处理项目</li>
				<li onclick="javascript:getMyProjectDone();">已处理项目</li>
				<li class="hit">所有项目</li>
			</ul>
			<div class="panes">
				<div class="pane" style="display: block;">
					<form action="/project/myProjectList.htm" method="post" id="searchForm">
						<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px;">
							<div class="article" style="margin: 0; clear: both; width: 100%;">
								<div
									style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px;"
								>项目编号:</div>
								<div>
									<input name="all" id="allid" type="hidden" value="done">
									<input name="project.no" type="text" value="${project.no}"
										style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
									/>
								</div>
								<div
									style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;"
								>项目名称:</div>
								<div>
									<input name="project.name" type="text" value="${project.name}"
										style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
									/>
								</div>
								<div class="sub_sear" onclick="javascript:getAllProject();">查询</div>
								<div>
									<input type="hidden" name="projectids" id="projectids" value="${values}" />
								</div>
							</div>
						</div>
					</form>
					<div style="clear: both;"></div>
					<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
						<tr class="head">

							<td style="background-color: #e6f6ff; text-align: center; width: 5%;">
								<p>序号</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 15%;">
								<p>项目编号</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center;">
								<p>项目名称</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 8%;">
								<p>工作流程</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 8%;">
								<p>进度图</p>
							</td>
							<td style="background-color: #e6f6ff; text-align: center; width: 8%;">
								<p>其他</p>
							</td>
						</tr>
						<#list pageBean.list as project>
						<tr>

							<td style="text-align: center;">
								<p>${project_index + 1}</p>
							</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
								<span title="${project.no}">
									<a href="/project/show.htm?project.id=${project.id}">${project.no}</a>
								</span>
							</td>
							<td style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
								<span title="${project.name}"> ${project.name} </span>
							</td>

							<td style="text-align: center;">
								<a href="/project/workflow.htm?project.id=${project.id}">工作流程</a>
							</td>

							<td style="text-align: center;">
								<a href="/project/gantt.htm?project.id=${project.id}">进度图</a>
							</td>

							<td style="text-align: center;">
								<a href="/project/viewProcessHistory.htm?projectId=${project.id}">处理详情</a>
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
	function getMyProject() {
		document.getElementById("searchForm").action = "/project/myProjectList.htm";
		document.getElementById("allid").value = "";
		document.getElementById("searchForm").submit();
	}

	function getMyProjectDone() {
		document.getElementById("searchForm").action = "/project/myProjectList.htm";
		document.getElementById("allid").value = "done";
		document.getElementById("searchForm").submit();
	}

	function getAllProject() {
		document.getElementById("searchForm").action = "/project/getAllProject.htm";
		$("#searchForm").submit();
	}
</script>



