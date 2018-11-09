<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a>合同登记</a>
</div>
<div id="content">

	<form action="/contract/list.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px;">
			<div class="article" style="margin: 0; clear: both; width: 100%;">
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px;"
				>合同编号:</div>
				<div>
					<input name="contract.no" type="text" value="${contract.no}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;"
				>合同名称:</div>
				<div>
					<input name="contract.name" type="text" value="${contract.name}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div class="sub_sear" onclick="javascript:$('#searchForm').submit();">查询</div>
			</div>
		</div>
	</form>
	<div style="clear: both;"></div>

	<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table5 stripe"
		style="margin-bottom: 10px;"
	>
		<tr class="head">
			<td style="background-color: #e6f6ff; text-align: center; width: 30px;">
				<p>编号</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>合同编号</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>合同名称</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>签订状态</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>合同金额</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>签约部门</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>签约人</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>签约日期</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>咨询类型</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>编审类型</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>投资规模</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 120px;">
				<p>操作</p>
			</td>
		</tr>
		<#list pageBean.list as contract>
		<tr>
			<td width="30" style="text-align: center;">${contract_index + 1}</td>
			<td>
				<span title="${contract.no}">${contract.no}</span>
			</td>
			<td>
				<span title="${contract.name}">${contract.name}</span>
			</td>
			<td><#if contract.signStatus == 0>未签订</#if> <#if contract.signStatus == 1>已签订</#if></td>
			<td>
				<span title="${contract.allAmount}">${contract.allAmount}</span>
			</td>
			<td>
				<span title="${contract.contractDept.name}">${contract.contractDept.name}</span>
			</td>
			<td>
				<span title="${contract.manager.name}">${contract.manager.name}</span>
			</td>
			<td>
				<span title="${contract.contractSignDate}">${contract.contractSignDate}</span>
			</td>
			<td>
				<span title="${contract.project.serviceType.name}">${contract.project.serviceType.name}</span>
			</td>
			<td>
				<span title="${contract.project.editorialType.name}">${contract.project.editorialType.name}</span>
			</td>
			<td>
				<span title="${contract.projectAmount}">${contract.projectAmount}</span>
			</td>
			<td style="text-align: center;">
				<#if contract.operable  >
				<!--<a href="javascript:delContract(${contract.id})">删除</a> -->
				<a href="/contract/edit.htm?project.id=${contract.project.id}" title="编辑">编辑</a>
				</#if>
				<a href="/contract/show.htm?contract.id=${contract.id}" title="查看">查看</a>
			</td>
		</tr>
		</#list>
	</table>
	<div style="width: 90%; margin: 0 auto; min-width: 980px;">
		<#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" />
	</div>
</div>
<script>
	function delContract(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		
		var url = "/contract/delete.htm?contract.id=" + id;
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
	
	function addContract() {
         window.parent.location.href="/contract/new.htm";
	}
</script>



