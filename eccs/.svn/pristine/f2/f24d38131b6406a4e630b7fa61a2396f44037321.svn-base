<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>

<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a> >
	<a href="/marketManager.htm">经营管理</a> >
	<a href="/contract/list.htm">合同登记</a> >${contract.name}
</div>
<form action="/contract/save.htm" method="post" id="contractForm"
	name="contractForm">
	<div id="content">
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">合同登记</div>
		<input type="hidden" name="project.id" value="${project.id}"  />
		<input type="hidden" name="contract.id" value="${contract.id}" />
		<input type="hidden" name="nextStepName" id="nextStepName" value="">
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td class="tab_title2" width="10%">
					合同名称
				</td>
				<td colspan="3">
					<input type="text" name="contract.name" value="${contract.name}" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					主合同名称
				</td>
				<td colspan="3">
					<input type="text" name="contract.masterName" value="${contract.masterName}" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					合同编号
				</td>
				<td>
					<input type="text" name="contract.no" value="${contract.no}" class="text_a" />
				</td>
				<td class="tab_title2" width="10%">
					投资规模(万元)
				</td>
				<td>
					<input type="text" name="contract.projectAmount" value="${contract.projectAmount}" class="text_a" />
				</td>
			</tr>
			
			<tr>
				<td class="tab_title2">
					工程性质
				</td>
				<td>
					<select name="projectNatureId">
					<#list projectNatureList as projectNature>
						<option value="${projectNature.id}" <#if contract.projectNature != null && contract.projectNature.id == projectNature.id>selected='selected'</#if> >
							${projectNature.name} 
						</option>
					</#list>
					</select>
				</td>
				<td class="tab_title2">
					工程类别
				</td>
				<td>
					<select name="projectTypeId">
						<#list projectTypeList as projectType>
						<option value="${projectType.id}" <#if contract.project.projectType != null && contract.project.projectType.id == projectType.id>selected='selected'</#if> >
							${projectType.name}
						</option>
						</#list>
					</select>
				</td>
			</tr>
			<!-- 
			<tr>
				<td width="100%" colspan="4" style="text-align:center;">
					客户信息
				</td>
			</tr>
			-->
			<tr>
				<td width="10%" class="tab_title">
					客户单位名称
				</td>
				<td colspan="3" style="background-color: #fff;">
						 ${customer.cusName}
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					联系人
				</td>
				<td>
					<input name="bidPlan.tendUserName" value="${t_Contact.contact}"
						type="text" class="text_a" />
				</td>
				<td width="10%" class="tab_title">
					联系电话
				</td>
				<td>
					<input name="bidPlan.tendTel" value="${t_Contact.telephone}"
						type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					传真
				</td>
				<td>
					<input name="bidPlan.tendFax" value="${customer.fax}" type="text"
						class="text_a" />
				</td>
				<td class="tab_title">
					邮箱
				</td>
				<td>
					<input name="bidPlan.tendMail" value="${t_Contact.email}"
						type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					通讯地址及邮编
				</td>
				<td colspan="3">
					<input name="bidPlan.tendAddress"
						value="${customer.address}${customer.postCode}" type="text"
						class="text_a" />
				</td>
			</tr>
			
			<tr>
				<td class="tab_title2">
					签订状态
				</td>
				<td>
					<input name="contract.signStatus" type="radio" value="1" <#if contract==null || contract.signStatus == 1>checked="checked"</#if> />
						&nbsp;已签订&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="contract.signStatus" type="radio" value="0" <#if contract.signStatus == 0>checked="checked"</#if> />
						&nbsp;未签订
				</td>
				<td class="tab_title2">
					合同状态
				</td>
				<td>
					<select name="contract.contractStatus">
						<option value="1" <#if contract.contractStatus== 1>selected='selected'</#if> >
							执行中
						</option>
						<option value="0" <#if contract.contractStatus== 0>selected='selected'</#if> >
							结束
						</option>
						<option value="-1" <#if contract.contractStatus== -1>selected='selected'</#if>>
							意外终止
						</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					合同项目地点
				</td>
				<td>
					<input type="text" name="contract.projectPlace" value="${contract.projectPlace }" class="text_a" />
				</td>
				<td class="tab_title2">
					是否补充协议
				</td>
				<td>
					<input name="contract.isAdd" type="radio" value="1" <#if contract==null || contract.isAdd == 1>checked="checked"</#if> />
						&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="contract.isAdd" type="radio" value="0" <#if contract.isAdd == 0>checked="checked"</#if> />
						&nbsp;否
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					咨询服务费支付方式
				</td>
				<td>
					<input name="contract.contractPay" type="radio" value="1" <#if contract==null || contract.contractPay == 1 >checked="checked"</#if> />
						&nbsp;包干&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="contract.contractPay" type="radio" value="0" <#if contract.contractPay == 0 >checked="checked"</#if> />
						&nbsp;暂定
				</td>
				<td class="tab_title2">
					死账金额
				</td>
				<td>
					<input type="text" name="contract.diedAmount" value="${contract.diedAmount}" class="text_a" />
				</td>

			</tr>
			<tr>
				<td class="tab_title2 red">
					咨询服务费(万元)
				</td>
				<td>
					<input name="contract.allAmount" value="${contract.allAmount}"
						type="text" class="text_a" />
				</td>
				<td class="tab_title2">
					其中分包费用(万元)
				</td>
				<td>
					<input name="contract.splitAmount" value="${contract.splitAmount}"
						type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2 red">
					签约部门
				</td>
				<td>
					<input type="hidden" name="contractDeptId" id="contractDeptId" value="${contract.contractDept.id}" />
					<input type="text" id="contractDeptName" readonly="readonly" class="text_a"
						value="${contract.contractDept.name}" onclick="javascript:getDept();" />
				</td>
				<td class="tab_title2 red">
					签约人
				</td>
				<td>
					<input type="hidden" name="managerId" id="managerId" value="${contract.manager.id}" />
					<input type="text" id="managerName" readonly="readonly" onclick="javascript:getUser();"
						value="${contract.manager.name}" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2 red">
					合同签约日期
				</td>
				<td>
					<input type="text" id="d4231" name="contract.contractSignDate" value="${contract.contractSignDate}" 
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4232\')}'})" class="text_a" readonly="readonly" />
				</td>
				<td class="tab_title2">
					合同返回日期
				</td>
				<td>
					<input type="text" id="d4232" name="contract.contractReturnDate" value="${contract.contractReturnDate}"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4231\')}'})" class="text_a" readonly="readonly" />
				</td>
			</tr>

			<tr>
				<td class="tab_title2">
					咨询类型
				</td>
				<td>
					<input type="hidden" name="serviceTypeId" value="${contract.project.serviceType.id}" />
					<input type="text" value="${contract.project.serviceType.name}" class="text_a" readonly="readonly"/>
				</td>
				<td class="tab_title2">
					业务范围
				</td>
				<td>
					<input type="hidden" name="editorialTypeId" value="${contract.project.editorialType.id}" />
					<input type="text" value="${contract.project.editorialType.name}" class="text_a" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td class="tab_title2 red">
					计划完成时间
				</td>
				<td>
					<input type="text" id="d4233" name="contract.planEndDate" value="${contract.planEndDate}"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4232\')}'})" class="text_a" readonly="readonly"/>
				</td>
				<td class="tab_title2">
					服务时限(月)
				</td>
				<td>
					<input type="text" name="contract.serviceTime" value="${contract.serviceTime}" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					履约保证金
				</td>
				<td>
					<input name="contract.isDeposit" type="radio" value="1" <#if contract==null || contract.isDeposit == 1>checked="checked"</#if> />
						&nbsp;有&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="contract.isDeposit" type="radio" value="0" <#if contract.isDeposit == 0>checked="checked"</#if> />
						&nbsp;无
				</td>
				<td class="tab_title2">
					履约保证金(万元)
				</td>
				<td>
					<input type="text" name="contract.depositAmount" value="${contract.depositAmount}" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					是否收回
				</td>
				<td>
					<input name="contract.isRecycle" type="radio" value="1" <#if contract==null || contract.isRecycle == 1>checked="checked"</#if> />
						&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="contract.isRecycle" type="radio" value="0" <#if contract.isRecycle == 0>checked="checked"</#if> />
						&nbsp;否
				</td>
				<td class="tab_title2">
					收回日期
				</td>
				<td>
					<input type="text" name="contract.recycleDate" value="${contract.recycleDate}"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="text_a" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					建设地点
				</td>
				<td colspan="3">
					<input type="text" name="contract.buildPlace" value="${contract.buildPlace}" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					建设阶段
				</td>
				<td colspan="3">
				<#list designStageList as designStage>
					<input type="checkbox" name="contract.designStageIds" value="${designStage.id}" 
						<#if contract.designStageIds != null && contract.designStageIds?contains('${designStage.id}') >checked='checked'</#if> />
						&nbsp;${designStage.name}&nbsp;&nbsp;&nbsp;&nbsp; 
				</#list>
				</td>
			</tr>
			<tr>
				<td class="tab_title2 red">
					咨询服务收费标准
				</td>
				<td colspan="3">
					<textarea name="contract.chargeRemark" cols="45" rows="5"
						style="width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${contract.chargeRemark}</textarea>
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					项目概况
				</td>
				<td colspan="3">
					<textarea name="contract.projectInfo" cols="45" rows="5"
						style="width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${contract.projectInfo}</textarea>
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					备注
				</td>
				<td colspan="3">
					<textarea name="contract.remark" cols="45" rows="5"
						style="width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${contract.remark}</textarea>
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					登记人
				</td>
				<td>
					<input type="text" value="${user.name }" class="text_a" readOnly="readOnly"/>
				</td>
				<td class="tab_title2">
					登记日期
				</td>
				<td>
					<input name="contract.ctime" class="text_a" value="${contract.ctime?string("yyyy-MM-dd")}"
						readOnly="readOnly" />
				</td>
			</tr>

			<tr id="nextOperator">
				<td class="tab_title2 red">
					项目管理人员分配处理人
				</td>
				<td colspan="3">
					<input type="hidden" name="nextOperatorId" id="nextOperatorId" value="${contract.project.nextOperator}" />
					<input type="text" name="nextOperatorName" id="nextOperatorName" value=""
						onclick="javacript:getNextOperator('nextOperatorId','nextOperatorName');" class="text_a" readonly="readonly"/>
				</td>
			</tr>

			<tr>
				<td colspan="4" class="tab_title2" style="text-align:center;">
					<strong>客户信息</strong>
				</td>
				
			</tr>
			<tr>
				<td colspan="4">
					<div style="display: block; text-align: right; border: 0px solid #dadada; border-bottom: 0px" class="add_link">
						<a href="javascript:addCustomer()">+新增</a>
					</div>
					<table id="customerTableId" border="1" cellspacing="1" cellpadding="1" class="list_table3">
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
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;">
								操作
							</td>
						</tr>
						<#list contractCustomerList as contractCustomer>
						<tr id="${contractCustomer.id}">
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
							<td style="text-align: center;">
								<a onclick="javascript:deleteRow(${contractCustomer.id} ,1)">删除</a>
							</td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right;">
					<input type="button" value="取消" onclick="javascript: history.go(-1);" class="sub" />
					<input type="button" value="提交任务" onclick="javascript: toNextStep();"  class="sub" />
					<input type="submit" value="保存" class="sub" />
				</td>
			</tr>
		</table>
	</div>
</form>
<script>
	var i = 0;
	var flag = true;
	var processType = '${project.processType}';
	if(processType == "1"){
		$("#nextOperator").hide();	
	}
	
	$().ready(function() {
		$("#contractForm").validate( {
			rules : {
				'contract.name' : {
					required : true
				},
				'contract.allAmount' : {
					required : true,
					number : true
				},
				'contract.contractDept' : {
					required : true
				},
				'contract.contractSignDate' : {
					required : true
				},
				'contract.planEndDate' : {
					required : true
				},
				'contract.chargeRemark' : {
					required : true
				},
				'contract.diedAmount' : {
					number : true
				},
				'contract.splitAmount' : {
					number : true
				},
				'contract.depositAmount' : {
					number : true
				},
				'contract.projectAmount' : {
					number : true
				},
				'contract.serviceTime' : {
					digits : true
				},
				'contractDeptId' : {
					required : true
				},
				'managerId' : {
					required : true
				},
				'nextOperatorName' : {
					required : true
				},
				'tel' : {
					isTel : true
				}
			}
		})
	});

	function toNextStep() {
		if(processType == "1"){
			document.getElementById("contractForm").action = "/contract/toNextStep.htm";
			$("#contractForm").submit();
		}else{
			//执行下一步
			var nextOperator = $("#nextOperatorName").val();
			var nextStepName = "项目管理-人员分配";
			if(nextOperator==""){
				alert("确定下一环节处理人?");
				return;
			}
			$("#nextStepName").val(nextStepName);
			if (confirm("当前步骤执行完毕,下一环节为【"+nextStepName+"】，下一环节处理人为【" + nextOperator + "】，确定要执行下一步吗?")) 
			{
				document.getElementById("contractForm").action = "/contract/toNextStep.htm";
				$("#contractForm").submit();
			}
		}
	}
	
	function getDept() {
		window.open('/dept/selectDept.htm','_blank','channelmode=yes,width=400,height=500,left=200,top=100');
	}
	
	function doAfterGetDept(obj){
		$('#contractDeptId').val(obj.deptId);
		$('#contractDeptName').val(obj.deptName);
	}
	
	function AddRow() {
		//添加一行
		//var tab1 = $("#contractReviewItemId");
		var tab1 = document.getElementById("customerTableId");
		var len = tab1.rows.length;
		if (flag) {
			i = i + tab1.rows.length;
			flag = false;
		} else {
			i = i + 1;
		}
		var Nam = "'div1'";
		var Cod = "fuJ" + i;
		var newTr = tab1.insertRow();
		newTr.id = i;
		//添加列
		var newTd0 = newTr.insertCell();
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<input type="text" name="no"  class="text_a"  />';
		newTd1.innerHTML = '<input type="text" name="name"  class="text_a"  />';
		newTd2.innerHTML = '<input type="text" name="contactName"  class="text_a"  />';
		newTd3.innerHTML = '<input type="text" name="tel"  class="text_a"    />';
		newTd4.innerHTML = '<a onclick="javascript:deleteRow(' + i + ',0)">删除</a>';
	}
	
	function deleteRow(i,s) {
		if(s=="0"){
			$('#' + i).remove();
			return;
		}
		if (!confirm("确定删除?")) {
			return;
		}   
		var url = "/contract/deletecls.htm?customerId=" + i;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					$("#"+i).remove();
					//window.parent.location.reload();
					//location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	}

	function addCustomer() {
		AddRow();
	}

	function getUser() {
	   var deptid=$("#contractDeptId").val();
	    if(deptid==""){
                alert("请您先选择签约部门");
                return;
        }
		var iWidth=650;                          //弹出窗口的宽度; 
   		var iHeight=500;                         //弹出窗口的高度; 
   		//获得窗口的垂直位置 
   		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
   		//获得窗口的水平位置 
   		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
		window.open('/user/selectUser.htm?deptId='+deptid,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function returnParam(id,name,username){
			$("#managerId").val(id);
			$("#managerName").val(name);
	}
	function getNextOperator(userIdId,userNameId) {
		window.open('/user/getUserByDeptId.htm?userIdId='+userIdId+'&userNameId='+userNameId,'_blank','channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}
	
</script>
