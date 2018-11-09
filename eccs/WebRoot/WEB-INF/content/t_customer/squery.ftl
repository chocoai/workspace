<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a href="#">客户台账</a>
</div>
<div id="content">
	<form action="/t_customer/squery.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px; margin-top: 8px">

			<div class="t_sub_excel" onclick="javascript:exportScore();">Excel</div>
			<div class="t_sub_stamp" onclick="javascript:pang();">打印</div>
			<br />
			<br />

			<table style="width: 100%; margin-top: 8px;">
				<tr>
					<td style="width: 60px; text-align: center;">
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

		</div>
	</form>

	<div style="clear: both;"></div>
	<!--startprint-->
	<table id="printArea" border="1" align="center" cellpadding="1" border="1px" cellspacing="0px"
		class="list_table5 stripe"
		style="width: 90%; margin: 0 auto; min-width: 980px; margin-top: 8px; table-layout: fixed; word-wrap: break-word;"
	>
		<tr class="head" height=26>

			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; width: 40px; border: 1px solid #ccc; background-color: #e6f6ff;"
			>
				<p>序号</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; width: 5%; background-color: #e6f6ff;"
			>
				<p>客户名称</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; width: 70px; background-color: #e6f6ff;"
			>
				<p>单位性质</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; width: 70px; background-color: #e6f6ff;"
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
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; width: 70px; background-color: #e6f6ff;"
			>
				<p>邮编</p>
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
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff; width: 100px"
			>
				<p>联系人</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff; width: 100px"
			>
				<p>电话</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff; width: 100px"
			>
				<p>手机</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff; width: 100px"
			>
				<p>qq</p>
			</th>
			<th
				style="background: url(../../../images/table_head.jpg) repeat-x top center; text-align: center; border: 1px solid #ccc; background-color: #e6f6ff; width: 100px"
			>
				<p>E-mail</p>
			</th>
		</tr>
		<#if pageBean.list?size!=0> 
		<#list pageBean.list as t_Customer>
		<input type="hidden" value=${t_Customer.id }/>
		<tr height=26>
			<td style="text-align: center; width: 40px; border: 1px solid #ccc">
				<p>${(t_Customer_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p>
			</td>
			<td style="text-align: left; border: 1px solid #ccc"><span title="${t_Customer.cusName}">${t_Customer.cusName}</span></td>
			<td style="text-align: left; border: 1px solid #ccc">
				<#if t_Customer.cusNature ==1 >委托单位</#if> 
				<#if t_Customer.cusNature ==2 >建设单位</#if> 
				<#if t_Customer.cusNature ==3 >施工单位</#if> 
				<#if t_Customer.cusNature ==4 >设计单位</#if></td>
			<td style="text-align: left; border: 1px solid #ccc">
				<#if t_Customer.cusType ==1 >企业客户</#if> 
				<#if t_Customer.cusType ==2 >政府客户</#if> 
				<#if t_Customer.cusType ==3 >其它</#if>
			</td>
			<td style="text-align: left; border: 1px solid #ccc"><span title="${t_Customer.cusHomepage}">${t_Customer.cusHomepage}</span></td>
			<td style="text-align: left; border: 1px solid #ccc"><span title="${t_Customer.email}">${t_Customer.email}</span></td>
			<td style="text-align: left; border: 1px solid #ccc"><span title="${t_Customer.officePhone}">${t_Customer.officePhone}</span></td>
			<td style="text-align: left; border: 1px solid #ccc"><span title="${t_Customer.postCode}">${t_Customer.postCode}</span></td>
			<td style="text-align: left; border: 1px solid #ccc"><span title="${t_Customer.fax}">${t_Customer.fax}</span></td>
			<td style="text-align: left; border: 1px solid #ccc"><span title="${t_Customer.address}">${t_Customer.address}</span></td>
			<td style="text-align: left; border: 1px solid #ccc"><span title="${t_Customer.remark}">${t_Customer.remark}</span></td>
			<td style="text-align: left; padding: 0px 0px 0px 0px; border: 1px solid #ccc" colspan="5">
				<table class="zzzzz" border=0 cellspacing=0 cellpadding=0
					style="text-align: center; width: 100%; height: 100%; table-layout: fixed; word-wrap: break-word;"
				>
					<#list t_Customer.contactlist as contacts>
					<tr height=26>
						<td
							style="text-align: left; border: 1px solid #ccc; width: 20%; border-bottom: 0; border-left: 0; border-right: 0;"
						><span title="${contacts.contact}">${contacts.contact}</span></td>
						<td style="text-align: left; border: 1px solid #ccc; width: 20%; border-bottom: 0; border-right: 0;">
							<span title="${contacts.phone}">${contacts.phone}</span></td>
						<td style="text-align: left; border: 1px solid #ccc; width: 20%; border-bottom: 0; border-right: 0;">
							<span title="${contacts.telephone}">${contacts.telephone}</span></td>
						<td style="text-align: left; border: 1px solid #ccc; width: 20%; border-bottom: 0; border-right: 0;">
							<span title="${contacts.qq}">${contacts.qq}</span></td>
						<td style="text-align: left; border: 1px solid #ccc; width: 20%; border-bottom: 0; border-right: 0;">
							<span title="${contacts.email}">${contacts.email}</span></td>
					</tr>
					</#list>
				</table>
			</td>
		</tr>
		</#list></#if> <#if pageBean.list?size==0>
		<tr>
			<td style="text-align: center;" colspan="16">暂无数据!</td>
		</tr>
		</#if>
	</table>
	<!--endprint-->
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

.noprint {
	display: none
}
</style>
<script>

	function exportScore() {
		$('#searchForm').attr('action', '/t_customer/excel.htm').submit();
		$('#searchForm').attr('action', '/t_customer/squery.htm');
	}
	$(function() {
		for (var i = 0; i < $(".zzzzz").length; i++) {
			$(".zzzzz").eq(i).find("tr").eq(0).children("td").css("border-top",
					"0");
		}
	});
	//打印
	function pang() {
		// preview();

		$('#searchForm').attr('target', 'newWindow');
		$('#searchForm').attr('action', '/t_customer/squeryPrint.htm');
		window
				.open(
						"",
						"newWindow",
						"height=700,width=1200,top=40,left=70,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
		$('#searchForm').submit();
		$('#searchForm').removeAttr("target");
		$('#searchForm').attr('action', '/t_customer/squery.htm');
	}
	function preview() {
		bdhtml = window.document.body.innerHTML;//获取当前页的html代码
		sprnstr = "<!--startprint-->";//设置打印开始区域
		eprnstr = "<!--endprint-->";//设置打印结束区域
		prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); //从开始代码向后取html

		prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));//从结束代码向前取html
		window.document.body.innerHTML = prnhtml;
		window.print();
		window.document.body.innerHTML = bdhtml;
	}
</script>