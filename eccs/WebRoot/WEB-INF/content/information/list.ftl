<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a>项目信息列表</a>
</div>
<div id="content">
	<form action="/information/list.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe"
			style="width: 90%; margin: 0 auto; min-width: 980px;">
			<div class="article" style="margin: 0; clear: both; width: 100%;">
				<div class="sub_add" onclick="javascript:addProjectinfo();">
					添加
				</div>
				<div
					style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;">
					项目名称:
				</div>
				<div>
					<input name="proinfo.proname" type="text" value="${proinfo.proname}"
						style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;" />
				</div>
				<div
					style="float: left; line-height: 30px; width: 80px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;">
					招标方式:
				</div>
				<div>
					<select name="proinfo.bidway" 
						style=" float:left;line-height:30px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;background-color: #fff;">
						<option value="">请选择</option>
						<option value="公开招标"  <#if proinfo.bidway=="公开招标" >selected='selected'</#if> >公开招标</option>
						<option value="邀请招标"  <#if proinfo.bidway=="邀请招标" >selected='selected'</#if> >邀请招标</option>
					</select>
				</div>

				<div class="sub_sear" onclick="javascript:$('#searchForm').submit()">
					查询
				</div>
			</div>
		</div>
	</form>
	<div style="clear: both;"></div>
	<table border="1" align="center" cellpadding="1" cellspacing="1"
		class="list_table5">
		<tr class="head">
			<td
				style="background-color: #e6f6ff; text-align: center;width: 3%;">
				<p>
					序号
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center;width: 27%;">
				<p>
					项目名称
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center;width: 15%;">
				<p>
					招标人
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 30%;">
				<p>
					报名日期
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center;width: 15%;">
				<p>
					开标时间
				</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: left;width: 8%;">
				<p>
					信息操作人员
				</p>
			</td>
			<td
				style="background-color: #e6f6ff; text-align: center;width: 20%;">
				<p>
					操作
				</p>
			</td>
		</tr>
		 <#if pageBean.list?size!=0>
		<#list pageBean.list as projcetInfo>
		<tr onclick="getViewHistory(${projcetInfo.id})">
			<td width="30" style="text-align: center;">
				${projcetInfo_index + 1}
			</td>
			<td>
				<span title="${projcetInfo.proname}">${projcetInfo.proname}</span>
			</td>
			<td>
				<span title="${projcetInfo.bidmen.cusName}">${projcetInfo.bidmen.cusName}</span>
			</td>
			<td  style="text-align: center;">
				<span >${projcetInfo.qsj}<#if projcetInfo.qsj != null>至</#if>${projcetInfo.zsj}</span>
			</td>
			<td  style="text-align: center;">
				<span title="${projcetInfo.opentime}">${projcetInfo.opentime}</span>
			</td>
			<td>
				<span title="${projcetInfo.operationmen}">${projcetInfo.operationmen}</span>
			</td>
			<td style="text-align: center;">
				<#if (projcetInfo.nextOperatorId ==user.id && (projcetInfo.status == 1 && projcetInfo.receiveType == 0)) || (projcetInfo.nextOperatorId ==user.id && projcetInfo.status == 2)>
				<a href="/project/new.htm?projectInfo.id=${projcetInfo.id}">立项</a>
				</#if>
				<#if projcetInfo.status == 1 >
				<a href="javascript:delProjectInfo(${projcetInfo.id})" title="删除">删除</a> 
				<a href="/information/edit.htm?proinfo.id=${projcetInfo.id}" title="编辑">编辑</a>
				</#if>
				<a href="/project/viewProcessHistory.htm?projectInfo.id=${projcetInfo.id}" title="查看">查看操作记录</a>
			</td>
		</tr>
	  </#list>
	 </#if>
	 <#if pageBean.list?size==0>
	 <tr><td style="text-align:center;" colspan="8">暂无数据!</td></tr>
	 </#if>
	</table>
	<div style="width: 90%; margin: 0 auto; min-width: 980px;">
		<#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" />
	</div>
</div>
<script>
	function delProjectInfo(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		
		var url = "/information/delete.htm?proinfo.id=" + id;
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
	
	function addProjectinfo() {
         window.parent.location.href="/information/new.htm";
	}
	
	function getViewHistory(projectInfoId){
		window.location.href="/information/show.htm?proinfo.id="+projectInfoId
	}
	
</script>



