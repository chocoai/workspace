
<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/backlog/list.htm">待办事项列表</a>
	>
	<a href="javascript:window.location.reload();">已办事项详情</a>
</div>
<div id="content">

	<div style="text-align: center; margin-top: 10px; margin-bottom: 10px;">
		<span>
			<h2>流程处理历史记录</h2>
		</span>
	</div>
	<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table5">
		<tr class="head">
			<td style="background-color: #e6f6ff; text-align: center; width: 3%;">
				<p>序号</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 18%;">
				<p>处理环节</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 15%;">
				<p>处理人</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 15%;">
				<p>处理时间</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 7%;">
				<p>处理方式</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 18%;">
				<p>下一环节</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>下一环节处理人</p>
			</td>
		</tr>
		<#list pphList as pph>
		<tr>
			<td width="30" style="text-align: center;">
				<p>${pph_index + 1}</p>
			</td>
			<td style="text-align: center;">
				<span>${pph.operateStep.stepName}</span>
			</td>
			<td style="text-align: center;">
				<span>${pph.operateUser.name}</span>
			</td>
			<td style="text-align: center;">
				<span>${pph.operateTime}</span>
			</td>
			<td style="text-align: center;">
				<span>${pph.operateType}</span>
			</td>
			<td style="text-align: center;">
				<span>${pph.nextStep.stepName}</span>
			</td>
			<td style="text-align: center;">
				<span>${pph.nextHandlerName}</span>
			</td>
		</tr>
		</#list>
		<tr>
			<td colspan="7" style="text-align: center;">
				<input type="button" onclick="javascript:history.go(-1);" value="返回" class="sub" />
			</td>
		</tr>
	</table>
</div>


