<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_seal/list.htm">印章借用-列表</a> >
<a href="#">印章借用-查看</a>
</div>
<div id="content">
	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			印章借用登记表
		</div>
	<table border="1" cellspacing="1" cellpadding="1" class="input_table">
		<tr>
			<td width="15%" class="tab_title"><p>登记部门</p></td>
			<td  width="35%" style="background-color:#fff;">
				${t_sealBorrow.dept_id.name}
			</td>
			<td  width="15%" class="tab_title"><p>登记人</p></td>
			<td  width="35%" style="background-color:#fff;">
				${t_sealBorrow.user_id.name}
			</td>
		</tr>
		<tr>
			<td class="tab_title"><p>项目名称</p></td>
			<td colspan="5" style="background-color:#fff;">
				${t_sealBorrow.project_id.name}
			</td>
		</tr>
		<tr>
			<td class="tab_title"><p>印章类型</p></td>
			<td style="background-color:#fff;">
				<#if t_sealBorrow.type ==1 >公章</#if>
				<#if t_sealBorrow.type ==2 >合同章</#if>
				<#if t_sealBorrow.type ==3>项目章</#if>
				<#if t_sealBorrow.type ==4 >其他</#if>
			</td>
			<td class="tab_title"><p>盖章份数</p></td>
			<td style="background-color:#fff;">
				${t_sealBorrow.count}
			</td>
		</tr>
		<tr>
			<td class="tab_title"><p>印章内容</p></td>
			<td colspan="5" style="background-color:#fff;height:80px;">
				${t_sealBorrow.detail}
			</td>
		</tr>
		<tr>
			<td width="15%" class="tab_title"><p>归还日期</p></td>
			<td  width="35%" style="background-color:#fff;">
				${t_sealBorrow.return_date}
			</td>
			<td  width="15%" class="tab_title"><p>登记时间</p></td>
			<td  width="35%" style="background-color:#fff;">
				${t_sealBorrow.rtime}
			</td>
		</tr>
		<tr>
			<td class="tab_title"><p>印章主管部门</p></td>
			<td colspan="5" style="background-color:#fff;">
				${t_sealBorrow.sealdept_id.name}
			</td>
		</tr>
		
		<!--<tr><td class="tab_title" colspan="6"></td></tr>-->
		
		<tr>
			<td width="15%" class="tab_title"><p>处理人</p></td>
			<td  width="35%" style="background-color:#fff;">
				${t_sealBorrow.option_id.user_id.name}
			</td>
			<td  width="15%" class="tab_title"><p>登记时间</p></td>
			<td  width="35%" style="background-color:#fff;">
				${t_sealBorrow.option_id.odate}
			</td>
		</tr>
		<tr>
			<td class="tab_title"><p>处理意见</p></td>
			<td colspan="5" style="background-color:#fff;">
				<#if t_sealBorrow.option_id.option ==1>准许</#if>
				<#if t_sealBorrow.option_id.option ==2>不准</#if>
				<#if t_sealBorrow.option_id.option ==3>占用</#if>
			</td>
		</tr>
		<tr>
			<td class="tab_title"><p>意见明细</p></td>
			<td colspan="5" style="background-color:#fff;height:80px;">
				${t_sealBorrow.option_id.detail}
			</td>
		</tr>
		<tr>
			<td colspan="6" style=" text-align:right;">
				<input type="button"  id="button" value="返回" style="cursor:pointer;" onclick="javascript:history.go(-1);" class="sub"/>
			</td>
		</tr>
	</table>
</div>

