<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a href="/information/list.htm">项目信息列表</a>
	>
	<a href="javascript:window.location.reload();">项目信息显示</a>
</div>


<div id="content">
	<div
		style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
	>项目信息</div>
	<input class="text_a" type="hidden" name="stepTempleteId" value="1" />
	<table border="1" cellspacing="1" cellpadding="1" class="input_table">

		<tr>
			<td class="tab_title" width="11%">项目名称</td>
			<td  style="background-color: #fff;">${proinfo.proname}</td>
			<td class="tab_title">招标人</td>
			<td  style="background-color: #fff;">${proinfo.bidmen.cusName}</td>
		</tr>
		<tr>
			<td class="tab_title">咨询类型</td>
			<td colspan="3" style="background-color: #fff;">
			<#list serviceTypeList as serviceType>
			<#if proinfo.serviceType != null && proinfo.serviceType.id == serviceType.id>
				${serviceType.name}
			</#if> 
			</#list>
			</td>
		</tr>
		<#if proinfo.receiveType==0>
		<tr>
			<td class="tab_title">承接类型</td>
			<td colspan="3" style="background-color: #fff;">直接委托类型</td>	
			
		</tr>
		<tr>
			<td width="1%" class="tab_title">咨询费估算(万)</td>
			<td style="background-color: #fff;">${proinfo.zixungusuan}</td>
			<td width="1%" class="tab_title">建设投资（万元）</td>
			<td style="background-color: #fff;">${proinfo.buildmoney}</td>
		</tr>
		<tr>
			<td class="tab_title">工程概况</td>
			<td colspan="3" style="background-color: #fff;">${proinfo.projectProfile}</td>
		</tr>
		<#else>
		<tr>
			<td class="tab_title">招标文件(公告)</td>
			<td colspan="3" style="background-color: #fff;"><a href="${proinfo.bidfile}" target="_blank">${proinfo.bidfile}</a></td>
		</tr>

		<tr>
			<td class="tab_title">承接类型</td>
			<td style="background-color: #fff;">投标类型</td>
			<td class="tab_title" width="11%">咨询费估算(万)</td>
			<td style="background-color: #fff;">${proinfo.zixungusuan}</td>
		</tr>

		<tr>
			<td class="tab_title">报名日期</td>
			<td style="background-color: #fff;">${proinfo.qsj} 至 ${proinfo.zsj}</td>
			<td class="tab_title">开标日期</td>
			<td style="background-color: #fff;">${proinfo.opentime}</td>
		</tr>

		<tr>
			<td width="1%" class="tab_title">代理公司</td>
			<td style="background-color: #fff;">${proinfo.agentcompany}</td>
			<td width="1%" class="tab_title">报名城市</td>
			<td style="background-color: #fff;">${proinfo.signcity}</td>
		</tr>
		</#if>
		<tr>
			<td colspan="4" style="text-align: right;">
				<input type="button" class="sub" onclick="javascript:history.go(-1);" value="返回" />
				<input type="button" class="sub" onclick="javascript:toPrint(${proinfo.id});" value="打印" />
			</td>
		</tr>

	</table>
</div>

<script>
	function toPrint(projectInfoId){
		var url = "/information/print.htm?proinfo.id=" + projectInfoId;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
	
	function changeEditorialType() {
		var serviceTypeId = $("#serviceTypeId").val();
		$
				.ajax({
					type : "post",
					url : '/project/select.htm',
					data : {
						'ids' : serviceTypeId
					},
					asyn : true,
					dataType : 'json',
					cache : false,
					success : function(d) {
						if (d != null && d != "") {
							var Ids = d.id;
							var names = d.name;
							var cobj = document.getElementById("editorialTypeId");
							cobj.innerHTML = '';
							for (var i = 0; i < Ids.length; i++) {
								document.projectForm.editorialTypeId.options[i] = new Option(
										names[i], Ids[i]);
							}
						}
					}
				});
	}
</script>


