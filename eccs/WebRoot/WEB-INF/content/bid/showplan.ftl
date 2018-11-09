
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a href="/bid/list.htm">投标策划</a> >
${project.name}
</div>

<div id="content">
	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">
		投标策划
	</div>
	<table border="1" cellspacing="1" cellpadding="1" class="input_table">
		<tr>
			<td width="10%" class="tab_title">
				投标项目名称
			</td>
			<td colspan="6" style="background-color: #fff;">
				${project.name}
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				项目编号
			</td>
			<td colspan="6" style="background-color: #fff;">
				${project.no}
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<p>
					<strong>一、项目概述</strong>
				</p>
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				投标部门
			</td>
			<td colspan="3" style="background-color: #fff;">
				${bidPlan.bidDept.name}
			</td>
			<td width="10%" class="tab_title">
				投标时间
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.bidDate}
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				所属片区
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.area}
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title" rowspan="4">
				招标人
			</td>

			<td width="10%" class="tab_title">
				单位名称
			</td>
			<td colspan="5" style="background-color: #fff;">
				${bidPlan.tendDeptName}
			</td>
		</tr>
		<tr>
			<td class="tab_title">
				联系人
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.tendUserName}
			</td>
			<td width="10%" class="tab_title">
				联系电话
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.tendTel}
			</td>
		</tr>
		<tr>
			<td class="tab_title">
				传真
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.tendFax}
			</td>
			<td class="tab_title">
				邮箱
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.tendMail}
			</td>
		</tr>
		<tr>
			<td width="15%" class="tab_title">
				通讯地址及邮编
			</td>
			<td colspan="5" style="background-color: #fff;">
				${bidPlan.tendAddress}
			</td>
		</tr>

		<tr>
			<td width="10%" class="tab_title">
				招标范围
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.tendRange}
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				招标内容
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.tendContent}
			</td>
		</tr>
		<tr>
			<td width="10%" rowspan="3" class="tab_title">
				投标信息
			</td>

			<td width="15%" class="tab_title">
				投标文件数量及要求
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.bidNeed}
			</td>
		</tr>
		<tr>
			<td class="tab_title">
				开标时间
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.openDate}
			</td>
		</tr>
		<tr>
			<td class="tab_title">
				开标地点
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.openPlace}
			</td>
		</tr>

		<tr>
			<td width="10%" rowspan="3" class="tab_title">
				投标保证金
			</td>

			<td width="10%" class="tab_title">
				金额
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.bondAmount}
			</td>
			<td width="10%" class="tab_title">
				截止日期
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.bondEndDate}
			</td>
			
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				开户名称
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.bondAccount}
			</td>
			<td width="10%" class="tab_title">
				开户银行
			</td>
			<td colspan="2" style="background-color: #fff;">
				${bidPlan.bondBank}
			</td>
			
		</tr>
		
		<tr>
			<td width="10%" class="tab_title">
				开户账号
			</td>
			<td colspan="5" style="background-color: #fff;">
				${bidPlan.bondAccountNumber}
			</td>
		</tr>

		<tr>
			<td colspan="7">
				<p>
					<strong>二、工作内容</strong><strong> </strong>
				</p>
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				投标文件组成
			</td>
			<td colspan="6" style="background-color: #fff;">
				<#list annexTypeList as annexType>
					<input type="checkbox" name="bidPlan.bidFileIds" value="${annexType.id}" disabled="disabled" 
						<#if bidPlan.bidFileIds != null && bidPlan.bidFileIds?contains('${annexType.id}') >checked='checked'</#if>/>
						&nbsp;${annexType.name}&nbsp;&nbsp;&nbsp;&nbsp; 
				</#list>
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<p>
					<strong>三、项目组成员</strong>
				</p>
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				项目经理
			</td>
			<td colspan="6">
				<table border="1" cellspacing="1" cellpadding="1"
					class="list_table4">
					<tr>
						<td
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							名称
						</td>
						<td
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							职位
						</td>
						<td
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							联系方式
						</td>
					</tr>

					<#list bidMemberList as bidMember>
					<tr>
						<td style="text-align: center; background-color: #fff;">
							${bidMember.name}
						</td>
						<td style="text-align: center; background-color: #fff;">
							${bidMember.position}
						</td>
						<td style="text-align: center; background-color: #fff;">
							${bidMember.contact}
						</td>
					</tr>
					</#list>

				</table>
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				专业负责人
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.masterName}
			</td>
		</tr>
		<tr>
			<td colspan="7">
				<p>
					<strong>四、进度计划</strong>
				</p>
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				进度计划
			</td>
			<td colspan="6">
				<table border="1" cellspacing="1" id="bidSpeedId" cellpadding="1"
					class="list_table4">
					<tr>
						<td
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							工作内容
						</td>
						<td
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							形成时间
						</td>
						<td
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							责任人
						</td>
						
					</tr>
					<#list bidSpeedList as bidSpeed>
					<tr>
						<td style="text-align: center; background-color: #fff;">
							${bidSpeed.workContent}
						</td>
						<td style="text-align: center; background-color: #fff;">
							${bidSpeed.completeDate}
						</td>
						<td style="text-align: center; background-color: #fff;">
							${bidSpeed.masterName}
						</td>
						
					</tr>
					</#list>

				</table>
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				审批人
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.approver.name}
			</td>
		</tr>
		<tr>
			<td width="10%" class="tab_title">
				审批意见
			</td>
			<td colspan="6" style="background-color: #fff;">
				${bidPlan.auditContent}
			</td>
		</tr>
		<tr>
			<td colspan="7" style="text-align: right;">
				<input type="button" value="返回" class="sub" onclick="javascript:history.go(-1);"  />
				<input type="button" value="打印" class="sub" onclick="javascript:toPrint(${bidPlan.id});" />
			</td>
		</tr>
	</table>

</div>

<script>
function toPrint(id){
	var url = "/bid/print.htm?bidPlan.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>

