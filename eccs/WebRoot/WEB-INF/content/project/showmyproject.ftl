<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>

    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a href="/project/list.htm">项目立项</a> >
<#if project!=null><a href="javascript:void(0);">${project.name}</a></#if>
</div>

<form action="/project/save.htm" method="post" id="contractForm"> 
	<div id="content">
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">项目立项</div>
			<tr>
				<td class="lefttd" colspan="6" style=" text-align:center">项目基本信息</td>
			</tr>
			<tr>
				<td width = "11%" class="tab_title"><p>项目编号</p></td>
				<td colspan ="3" style="background-color:#fff;">
					<input type="hidden"  name="project.id"  value="${project.id}" class="text_a" />${project.no}
				</td>
				<td width = "11%" class="tab_title"><p>立项日期</p></td>
				<td style="background-color:#fff;">${project.recordDate}</td>
			</tr>
			
			<tr>
				<td class="tab_title red"><p>项目名称</p></td>
				<td colspan="5" style="background-color:#fff;">${project.name}</td>
			</tr>
			
			<tr>
				<td width = "11%" class="tab_title red"><p>委托单位</p></td>
				<td width = "22%" style="background-color:#fff;">${project.customer.cusName}</td>
				<td width = "11%" class="tab_title red"><p>实施部门</p></td>
				<td width = "22%" style="background-color:#fff;">${project.receiveDept.name}</td>   
				<td width = "11%" class="tab_title red"><p>协作部门</p></td>
				<td width = "22%" style="background-color:#fff;">${project.handleDept.name}</td>	
			</tr>
				<tr>
				<td width = "11%" class="tab_title red"><p></p></td>
				<td width = "22%" style="background-color:#fff;"></td>
				<td width = "11%" class="tab_title red"><p>实施负责人</p></td>
				<td width = "22%" style="background-color:#fff;">${project.receiveManager.name}</td>   
				<td width = "11%" class="tab_title red"><p>协作负责人</p></td>
				<td width = "22%" style="background-color:#fff;">${handleManagerNames}</td>	
			</tr>
			<tr>
				<td class="tab_title red"><p>咨询类型</p></td>
				<td style="background-color:#fff;">${project.serviceType.name}</td>
				<td class="tab_title red"><p>业务范围</p></td>
				<td style="background-color:#fff;">${project.editorialType.name}</td>
				<td class="tab_title red"><p>紧急程度</p></td>
				<td style="background-color:#fff;">${project.urgentType.name}</td>
			</tr>

			<tr>
				<td class="tab_title red"><p>承接类型</p></td>
				<td style="background-color:#fff;">
					<#if project.receiveType== 1>招投标</#if>
					<#if project.receiveType== 0>直接委托</#if>
				</td>
				<td class="tab_title red"><p>签订合同</p></td>
				<td style="background-color:#fff;">
					<#if project.doContact== 1>签合同</#if>
					<#if project.doContact== 0>不签合同</#if>
				</td>
				<td width="" class="tab_title"><p>立项人</p></td>
				<td width="" style="background-color:#fff;">${user.name }</td>
				
			</tr>
			
			<tr>
            	<td class="tab_title"><p>项目规模</p></td>
				<td colspan="5" style="background-color:#fff;">${project.scaleType}</td>
            </tr>

			<tr>
				<td class="lefttd" colspan="6" style=" text-align:center">往来单位信息</td>
			</tr>

			<tr>
				<td colspan="9">
					<table border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
						<tr>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:120px;">单位名称</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">单位性质</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">联系人</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">电话</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">手机</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">是否跟踪</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;">操作</td>
						</tr>
						<#list projectContactList as projectContact>
						<tr>
							<td style=" text-align:center;background-color:#fff;">${projectContact.deptName }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.deptType }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.userName }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.officeTel }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.tel }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.isTrack }</td>
							<td style=" text-align:center;">-</td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>
			 
			<tr>
				<td style="text-align:right;">备注</td>
				<td colspan="3" style=" width:20px; height:30px; line-height:30px; text-align:left;background-color:#fff;">${project.remark}</td>
				<td>业主负责人</td>
				<td style=" text-align:left;background-color:#fff;">${project.recordName}</td>
			</tr>

			<tr class = "no-print">
				<td colspan="6" style=" text-align:right;">
					<input type="button"  id="button" value="返回" onclick="javascript:history.go(-1);" class="sub"/>
					<input type="button" class="sub" onclick="javascript:toPrint(${project.id});" value="打印"/>                                          
				</td>
			</tr>                          
		</table>                          
	</div>
	
</form>

<script>
function toPrint(id){
	var url = "/project/print.htm?project.id=" + id;
	var value = window.open(url,"","height=600,width=760,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
}
</script>
