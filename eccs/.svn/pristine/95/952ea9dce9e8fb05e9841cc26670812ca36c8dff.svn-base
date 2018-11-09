<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a>人员分配列表</a>
</div>

<div id="content">
	<form action="/person/list.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe"
			style="width: 90%; margin: 0 auto; min-width: 980px;">
			<div class="article" style="margin: 0; clear: both; width: 100%;">
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px;">
					项目编号:
				</div>
				<div>
					<input name="project.no" type="text" value="${project.no}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;" />
				</div>
				<div
					style="float: left; line-height:30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;">
					项目名称:
				</div>
				<div>
					<input name="project.name" type="text" value="${project.name}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;" />
				</div>
				<div class="sub_sear" onclick="javascript:$('#searchForm').submit();">
					查询
				</div>
			</div>
		</div>
	</form>
	<div style="clear: both;"></div>
	<table border="1" align="center" cellpadding="1" cellspacing="1"
		class="list_table5 stripe">
		<tr class="head">
			<td
				style="background-color: #e6f6ff; text-align: center; width: 3%;">
				<p>
					序号
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 10%;">
				<p>
					项目编号
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center;width: 20%;">
				<p>
					项目名称
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center; width: 7%;">
				<p>
					立项日期
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 15%;">
				<p>
					委托单位
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center; width: 5%;">
				<p>
					咨询类型
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 10%;">
				<p>
					编审类型
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: left;width: 10%;">
				<p>
					当前步骤
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center; width: 10%;">
				<p>
					操作
				</p>
			</td>
		</tr>
		<#list pageBean.list as pps>
		
		 
		 <tr>
			<td width="30" style="text-align: center;">
				${pps_index + 1}
			</td>
			<td>
				<span title="${pps.project.no}">${pps.project.no}</span>
			</td>
			<td>
				<span title="${pps.project.name}">${pps.project.name}</span>
			</td>
			<td>
				<span title="">${pps.project.recordDate?string("yyyy-MM-dd")}</span>
			</td>
			<td>
				<span title="${pps.project.customer.cusName}">${pps.project.customer.cusName}</span>
			</td>
			<td>
				<span title="${pps.project.serviceType.name}">${pps.project.serviceType.name}</span>
			</td>
			<td>
				<span title="${pps.project.editorialType.name}">${pps.project.editorialType.name}</span>
			</td>
			<td style="color: red;">
				${pps.currentStep.stepName}
			</td>
			<td style="text-align: center;">
				<#if user.isAdmin() >
					<a href="/person/edit.htm?project.id=${pps.project.id}">编辑</a>
				<#else>
					<#if pps.currentStep.stepCode =='-99' >
						<#list pps.currentUsers?split(",") as currentUserId>
							<#if currentUserId == '${user.id}' >
								<a href="/person/edit.htm?project.id=${pps.project.id}">编辑</a> 
							</#if>
						</#list>
					</#if>
				</#if>
				<a href="/person/show.htm?projectId=${pps.project.id}">查看</a>
			</td>
		</tr>
		</#list>
	</table>
	<div style="width: 90%; margin: 0 auto; min-width: 980px;">
		<#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" />
	</div>
</div>




