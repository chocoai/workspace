
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a href="/contract/list.htm">合同登记</a> > ${contract.name}
</div>
<form action="/contract/save.htm" method="post" id="contractForm">
	<div id="content">
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">合同登记</div>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td class="tab_title2" width="10%">
					合同名称
				</td>
				<td colspan="3" style="background-color: #fff;">
					${contract.name}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					主合同名称
				</td>
				<td colspan="3" style="background-color: #fff;">
					${contract.masterName}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					合同编号
				</td>
				<td style="background-color: #fff;">
					${contract.no}
				</td>
				<td class="tab_title2"  width="10%">
					投资规模(万元)
				</td>
				<td style="background-color: #fff;">
					${contract.projectAmount}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					工程性质
				</td>
				<td style="background-color: #fff;">
					${contract.projectNature.name}
				</td>
				<td class="tab_title2">
					工程类别
				</td>
				<td style="background-color: #fff;">
					${contract.projectType.name}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					签订状态
				</td>
				<td style="background-color: #fff;">
					<#if contract!=null && contract.signStatus == 1>&nbsp;已签订</#if> 
					<#if contract!=null && contract.signStatus == 0>&nbsp;未签订</#if>
				</td>
				<td class="tab_title2">
					合同状态
				</td>
				<td style="background-color: #fff;">
					<#if contract!=null && contract.contractStatus == 1>执行中</#if> 
					<#if contract!=null && contract.contractStatus == 0>结束</#if> 
					<#if contract!=null && contract.contractStatus ==-1>意外终止</#if>
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					合同项目地点
				</td>
				<td style="background-color: #fff;">
					${contract.projectPlace }
				</td>
				<td class="tab_title2">
					是否补充协议
				</td>
				<td style="background-color: #fff;">
					<#if contract!=null && contract.isAdd == 1>&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;</#if> 
					<#if contract!=null && contract.isAdd == 0>&nbsp;否</#if>
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					结算方式
				</td>
				<td style="background-color: #fff;">
					<#if contract==null || contract.contractPay == 1>&nbsp;包干&nbsp;&nbsp;&nbsp;&nbsp;</#if> 
					<#if contract.contractPay == 0> 暂定</#if>
				</td>
				<td class="tab_title2">
					死账金额(万元)
				</td>
				<td style="background-color: #fff;">
					${contract.diedAmount}
				</td>

			</tr>
			<tr>
				<td class="tab_title2">
					合同金额(万元)
				</td>
				<td style="background-color: #fff;">
					${contract.allAmount}
				</td>
				<td class="tab_title2">
					其中分包费用(万元)
				</td>
				<td style="background-color: #fff;">
					${contract.splitAmount}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					签约部门
				</td>
				<td style="background-color: #fff;">
					${contract.contractDept.name}
				</td>
				<td class="tab_title2">
					签约人
				</td>
				<td style="background-color: #fff;">
					${contract.manager.name}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					合同签约日期
				</td>
				<td style="background-color: #fff;">
					${contract.contractSignDate}
				</td>
				<td class="tab_title2">
					合同返回日期
				</td>
				<td style="background-color: #fff;">
					${contract.contractReturnDate}
				</td>
			</tr>


			<tr>
				<td class="tab_title2">
					咨询类型
				</td>
				<td style="background-color: #fff;">
					${contract.serviceType.name}
				</td>
				<td class="tab_title2">
					业务范围
				</td>
				<td style="background-color: #fff;">
					${contract.editorialType.name}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					计划完成时间
				</td>
				<td style="background-color: #fff;">
					${contract.planEndDate}
				</td>
				<td class="tab_title2">
					服务时限(月)
				</td>
				<td style="background-color: #fff;">
					${contract.serviceTime}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					履约保证金
				</td>
				<td style="background-color: #fff;">
					<#if contract!=null && contract.isDeposit == 1>&nbsp;有</#if> 
					<#if contract!=null && contract.isDeposit == 0>&nbsp;无</#if>
				</td>
				<td class="tab_title2">
					履约保证金(万元)
				</td>
				<td style="background-color: #fff;">
					${contract.depositAmount}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					是否收回
				</td>
				<td style="background-color: #fff;">
					<#if contract!=null && contract.isRecycle == 1>&nbsp;是</#if> 
					<#if contract!=null && contract.isRecycle == 0>&nbsp;否</#if>
				</td>
				<td class="tab_title2">
					收回日期
				</td>
				<td style="background-color: #fff;">
					${contract.recycleDate}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					建设地点
				</td>
				<td colspan="3" style="background-color: #fff;">
					${contract.buildPlace}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					建设阶段
				</td>
				<td colspan="3" style="background-color: #fff;">
					<#list designStageList as designStage>
					<input type="checkbox" name="contract.designStageIds" value="${designStage.id}" disabled="disabled"
						<#if contract.designStageIds != null && contract.designStageIds?contains('${designStage.id}') >checked='checked'</#if> />
						&nbsp;${designStage.name}&nbsp;&nbsp;&nbsp;&nbsp; 
					</#list>
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					收费程序
				</td>
				<td colspan="3" style="background-color: #fff; height: 80px;">
					<label for="fileField"></label>
					<label for="textarea"></label>
					${contract.chargeRemark}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					项目概况
				</td>
				<td colspan="3" style="background-color: #fff; height: 80px;">
					<label for="fileField"></label>
					<label for="textarea"></label>
					${contract.projectInfo}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					备注
				</td>
				<td colspan="3" style="background-color: #fff; height: 80px;">
					<label for="fileField"></label>
					<label for="textarea"></label>
					${contract.remark}
				</td>
			</tr>

			<tr>
				<td class="tab_title2">
					登记人
				</td>
				<td style="background-color: #fff;">
					${contract.user.name }
				</td>
				<td class="tab_title2">
					登记日期
				</td>
				<td style="background-color: #fff;">
					${contract.ctime?string("yyyy-MM-dd")}
				</td>
			</tr>
			<tr>
				<td colspan="4" class="tab_title2" style="text-align:center;">
					<strong>客户信息</strong>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<table border="1" cellspacing="1" cellpadding="1" class="list_table3">
						<tr>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								客户编号
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								客户名称
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								联系人
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								联系电话
							</td>
						</tr>
						<#list contractCustomerList as contractCustomer>
						<tr>
							<td style="text-align: center; background-color: #fff;">
								${contractCustomer.no}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${contractCustomer.name}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${contractCustomer.contactName}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${contractCustomer.tel}
							</td>
							
						</tr>
						</#list>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right;">
					<input type="button" value="返回" class="sub" onclick="javascript:history.go(-1);" />
					<input type="button" value="打印" class="sub" onclick="javascript:toPrint(${contract.id});" />
				</td>
			</tr>
		</table>
	</div>
</form>

<script>
function toPrint(id){
	var url = "/contract/print.htm?contract.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>

