 <link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map"><img src="/images/home.png" width="19" height="24" />当前位置：<a href="/workbench.htm">工作台</a> > <a href="/marketManager.htm">经营管理</a> > <a href="#">客户登记-列表</a></div>
<div id="content">

	<form action="/t_customer/list.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px; margin-top: 8px">
			<div class="sub_add" onclick="javascript:addProject();">添加</div>
			<div class="t_sub_delete" onclick="delProject();">删除</div>
			<table style="width: 100%; margin-top: 8px">
				<tr>
					<td style="width: 60px">
						<div
							style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 1px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px; height: 30px; line-height: 30px; overflow: hidden;"
						>客户名称:</div>
					</td>
					<td style="width: 60px">
						<div>
							<input name="t_Customer.cusName" type="text" value="${t_Customer.cusName}"
								style="float: left; line-height: 22px; width: 150px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
							/>
						</div>
					</td>
					<td style="width: 60px">
						<div
							style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 1px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px; height: 30px; line-height: 30px; overflow: hidden;"
						>电子邮箱:</div>
					</td>
					<td style="width: 60px">
						<div>
							<input name="t_Customer.email" type="text" value="${t_Customer.email}"
								style="float: left; line-height: 22px; width: 150px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
							/>
						</div>
					</td>
					<td style="width: 60px">
						<div
							style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 1px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px; height: 30px; line-height: 30px; overflow: hidden;"
						>单位性质:</div>
					</td>
					<td style="width: 60px">
						<div>
							<select selected="true" name="t_Customer.cusNature"
								style="float: left; line-height: 22px; width: 100px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
							>
								<option value="">全部</option>
								<option value="1"<#if t_Customer.cusNature==1>selected='selected'</#if> >委托单位</option>
								<option value="2"<#if t_Customer.cusNature==2>selected='selected'</#if> >建设单位</option>
								<option value="3"<#if t_Customer.cusNature==3>selected='selected'</#if> >施工单位</option>
								<option value="4"<#if t_Customer.cusNature==4>selected='selected'</#if> >设计单位</option>
							</select>
						</div>
					</td>
					<td style="width: 60px">
						<div
							style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 1px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px; height: 30px; line-height: 30px; overflow: hidden;"
						>客户类别:</div>
					</td>
					<td style="width: 60px">
						<div>
							<select name="t_Customer.cusType"
								style="float: left; line-height: 22px; width: 100px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
							>
								<option value="" selected="true">全部</option>
								<option value="1"<#if t_Customer.cusType==1>selected='selected'</#if> >企业客户</option>
								<option value="2"<#if t_Customer.cusType==2>selected='selected'</#if> >政府客户</option>
								<option value="3"<#if t_Customer.cusType==3>selected='selected'</#if> >其它</option>
							</select>
						</div>
					</td>
					<td style="width: 60px">
						<div class="sub_sear" onclick="javascript:$('#searchForm').submit()" style="margin: 6px 0px 0px 0px">查询</div>
					</td>
					<td></td>
				</tr>
			</table>
			<div class="article" style="margin: 0; clear: both; width: 100%;"></div>
		</div>
	</form>
	<div style="clear: both;"></div>
	<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table5 stripe"
		style="width: 90%; margin: 0 auto; min-width: 980px; margin-top: 8px"
	>
		<tr class="head">
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; width: 40px; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>序号</p>
			</th>
			<td
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; width: 30px; background-color: #e6f6ff;"
			>
				<p>
					<input id="qxcheckbox" type="checkbox" />
				</p>
			</td>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; width: 70px; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>客户名称</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; width: 70px; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>单位性质</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; width: 70px; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>客户类别</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>主页</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>电子邮箱</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>办公电话</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>邮编</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>默认联系人</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>联系电话</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>传真</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>地址</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>备注</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; width: 120px; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>操作</p>
			</th>

		</tr>
		<#if pageBean.list?size!=0> <#list pageBean.list as t_Customer>
		<input type="hidden" value=${t_Customer.id }/>
		<tr>
			<td width="30" style="text-align: center;">
				<p>${(t_Customer_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p>
			</td>
			<td style="text-align: center;">
				<input name="a" type="checkbox" autocomplete="off" value="${t_Customer.id}" />
			</td>
			<td>
				<span title="${t_Customer.cusName}">${t_Customer.cusName}</span>
			</td>
			<td>
				<#if t_Customer.cusNature ==1 >委托单位</#if> 
				<#if t_Customer.cusNature ==2 >建设单位</#if> 
				<#if t_Customer.cusNature ==3 >施工单位</#if> 
				<#if t_Customer.cusNature ==4 >设计单位</#if>
			</td>
			<td>
				<#if t_Customer.cusType ==1 >企业客户</#if> 
				<#if t_Customer.cusType ==2 >政府客户</#if> 
				<#if t_Customer.cusType ==3 >其它</#if>
			</td>
			<td><span title="${t_Customer.cusHomepage}">${t_Customer.cusHomepage}</span></td>
			<td><span title="${t_Customer.email}">${t_Customer.email}</span></td>
			<td><span title="${t_Customer.officePhone}">${t_Customer.officePhone}</span></td>
			<td><span title="${t_Customer.postCode}">${t_Customer.postCode}</span></td>

			<td><span title="${t_Customer.contacts.contact}">${t_Customer.contacts.contact}</span></td>
			<td><span title="${t_Customer.contacts.phone}">${t_Customer.contacts.phone}</span></td>

			<td><span title="${t_Customer.fax}">${t_Customer.fax}</span></td>
			<td><span title="${t_Customer.address}">${t_Customer.address}</span></td>
			<td><span title="${t_Customer.remark}">${t_Customer.remark}</span></td>


			<td>
				<a href="/t_customer/edit.htm?t_Customer.id=${t_Customer.id}" title="编辑">编辑</a>
				<a href="/t_customer/show.htm?t_Customer.id=${t_Customer.id}" title="查看">查看</a>
			</td>
		</tr>
		</#list> 
		</#if> 
		<#if pageBean.list?size==0>
		<tr>
			<td style="text-align: center;" colspan="15">暂无数据!</td>
		</tr>
		</#if>
	</table>
	<br />
	<div style="width: 90%; margin: 0 auto; min-width: 980px;">
		<#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" />
	</div>

</div>
<style>
td {
	word-break: break-all;
	text-align: center;
}
</style>
<script>
	$(function(){
	$("#qxcheckbox").click(function(){
		
		if($("#qxcheckbox").is(':checked')){
		$("[name='a']").attr("checked",'true');
		}else{
		$("[name='a']").removeAttr("checked",'true');
		}
		
		$("input[name='a']").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		
		});
	});
	function delProject() {
	if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      if(id==""){
      alert("请选择删除项!");
      return;
      }
		var url = "/t_customer/delete.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
					location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	}
	function addProject() {
         window.parent.location.href="/t_customer/new.htm";
	}
</script>



