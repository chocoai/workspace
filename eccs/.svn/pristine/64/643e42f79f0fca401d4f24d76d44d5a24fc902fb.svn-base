<script type="text/javascript" src="../../../js/mydate/WdatePicker.js"></script>
<script type="text/javascript" src="../../../js/jquery.validate.js"></script>
<script type="text/javascript" src="../../../js/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="../../../js/jquery.metadata.js"></script>

<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a href="/bid/list.htm">投标策划</a>
</div>
<form action="/bid/save.htm" method="post" id="contractForm">
	<div id="content">
		<div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
		>投标策划</div>
		<input type="hidden" name="bidPlan.id" value="${bidPlan.id}" />
		<input type="hidden" name="projectId" value="${project.id}" />
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td width="10%" class="tab_title">投标项目名称</td>
				<td colspan="4" style="background-color: #fff;">${project.name}</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title">项目编号</td>
				<td colspan="4" style="background-color: #fff;">${project.no}</td>
			</tr>
			<tr>
				<td colspan="5">
					<p>
						<strong>一、项目概述</strong>
						<strong> </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title red">投标部门</td>
				<td colspan="2" class="tab_title red">
					<input type="hidden" name="bidDeptId" id="bidDeptId" value="${bidPlan.bidDept.id}" />
					<input type="text" name="bidDeptName" id="bidDeptName" class="text_a" readonly="readonly"
						value="${bidPlan.bidDept.name}" onclick="javascript:getDept();"
					/>
				</td>
				<td width="10%" class="tab_title red">投标时间</td>
				<td>
					<input id="d4311" type="text" class="text_a" readonly="readonly" name="bidPlan.bidDate" value="${bidPlan.bidDate}"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4312\')}',maxDate:'#F{$dp.$D(\'d4313\')}'})"
					/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title red">所属片区</td>
				<td colspan="4">
					<input name="bidPlan.area" value="${bidPlan.area}" type="text" class="text_a" />
				</td>
			</tr>


			<#if '${bidPlan.tendDeptName}' == null>
			<tr>
				<td width="10%" rowspan="4" class="tab_title">招标人</td>
				<td width="10%" class="tab_title">单位名称：</td>
				<td colspan="3" style="background-color: #fff;">
					<input name="bidPlan.tendDeptName" value="${customer.cusName}"
						 readonly="readonly" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">联系人：</td>
				<td>
					<input name="bidPlan.tendUserName" value="${t_Contact.contact}" type="text" class="text_a" />
				</td>
				<td width="10%" class="tab_title">联系电话：</td>
				<td>
					<input name="bidPlan.tendTel" value="${t_Contact.telephone}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">传真：</td>
				<td>
					<input name="bidPlan.tendFax" value="${customer.fax}" type="text" class="text_a" />
				</td>
				<td class="tab_title">邮箱：</td>
				<td>
					<input name="bidPlan.tendMail" value="${t_Contact.email}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">通讯地址及邮编：</td>
				<td colspan="3">
					<input name="bidPlan.tendAddress" value="${customer.address}${customer.postCode}" type="text" class="text_a" />
				</td>
			</tr>

			<#else>
			<tr>
				<td width="10%" rowspan="4" class="tab_title">招标人</td>
				<td width="10%" class="tab_title">单位名称：</td>
				<td colspan="3" style="background-color: #fff;">
					<input name="bidPlan.tendDeptName" value="${bidPlan.tendDeptName}"
						readonly="readonly"	type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">联系人：</td>
				<td style="background-color: #fff;">
					<input name="bidPlan.tendUserName" value="${bidPlan.tendUserName}" type="text" class="text_a" />
				</td>
				<td width="10%" class="tab_title">联系电话：</td>
				<td style="background-color: #fff;">
					<input name="bidPlan.tendTel" value="${bidPlan.tendTel}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">传真：</td>
				<td style="background-color: #fff;">
					<input name="bidPlan.tendFax" value="${bidPlan.tendFax}" type="text" class="text_a" />
				</td>
				<td class="tab_title">邮箱：</td>
				<td style="background-color: #fff;">
					<input name="bidPlan.tendMail" value="${bidPlan.tendMail}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">通讯地址及邮编：</td>
				<td colspan="3" style="background-color: #fff;">
					<input name="bidPlan.tendAddress" value="${bidPlan.tendAddress}" type="text" class="text_a" />
				</td>
			</tr>

			</#if>

			<tr>
				<td width="10%" class="tab_title red">招标范围</td>
				<td colspan="4">
					<input name="bidPlan.tendRange" value="${bidPlan.tendRange}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title red">招标内容</td>
				<td colspan="4">
					<input name="bidPlan.tendContent" value="${bidPlan.tendContent}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td width="10%" rowspan="3" class="tab_title">投标信息</td>

				<td width="15%" class="tab_title red">投标文件数量及要求：</td>
				<td colspan="3">
					<input name="bidPlan.bidNeed" value="${bidPlan.bidNeed}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title red">开标时间：</td>
				<td colspan="3">
					<!--					<input name="bidPlan.openDate" value="${bidPlan.openDate}"
						readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
						type="text" class="text_a" />-->
					<input id="d4312" type="text" class="text_a" readonly="readonly" name="bidPlan.openDate"
						value="${bidPlan.openDate}" onClick
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4311\')||$dp.$D(\'d4313\')}'})"
					/>
				</td>
			</tr>
			<tr>
				<td class="tab_title red">开标地点：</td>
				<td colspan="3">
					<input name="bidPlan.openPlace" value="${bidPlan.openPlace}" type="text" class="text_a" />
				</td>

			</tr>
			<tr>
				<td width="10%" rowspan="3" class="tab_title">投标保证金</td>

				<td class="tab_title">金额（元）：</td>
				<td>
					<input name="bidPlan.bondAmount" value="${bidPlan.bondAmount}" type="text" class="text_a" />
				</td>
				<td class="tab_title">截止日期：</td>
				<td>
					<!--	<input type="text" class="text_a" name="bidPlan.bondEndDate" 
						value="${bidPlan.bondEndDate}" readonly="readonly"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />-->
					<input id="d4313" type="text" class="text_a" readonly="readonly" value="${bidPlan.bondEndDate}"
						name="bidPlan.bondEndDate"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')||$dp.$D(\'d4312\')}'})"
					/>
				</td>

			</tr>
			<tr>
				<td class="tab_title">开户名称：</td>
				<td>
					<input name="bidPlan.bondAccount" value="${bidPlan.bondAccount}" type="text" class="text_a" />
				</td>
				<td class="tab_title">开户银行：</td>
				<td>
					<input name="bidPlan.bondBank" value="${bidPlan.bondBank}" type="text" class="text_a" />
				</td>


			</tr>
			<tr>
				<td class="tab_title">开户账号：</td>
				<td colspan="3">
					<input name="bidPlan.bondAccountNumber" value="${bidPlan.bondAccountNumber}" type="text" class="text_a" />
				</td>
			</tr>

			<tr>
				<td colspan="5">
					<p>
						<strong>二、工作内容</strong>
						<strong> </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="133" class="tab_title red">投标文件组成</td>
				<td colspan="8">
					<#list annexTypeList as annexType>
					<input type="checkbox" name="bidPlan.bidFileIds" value="${annexType.id}"
					<#if bidPlan.bidFileIds != null && bidPlan.bidFileIds?contains('${annexType.id}') >checked='checked'</#if>/>
					&nbsp;${annexType.name}&nbsp;&nbsp;&nbsp;&nbsp; 
					</#list>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<p>
						<strong>三、项目组成员</strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title">项目成员</td>
				<td colspan="4">
					<div style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px" class="add_link">
						<a href="javascript:addBidMember(); ">+新增</a>
					</div>
					<table border="1" cellspacing="1" id="bidMemberId" cellpadding="1" class="list_table4">
						<tr>
							<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">名称</td>
							<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">职位</td>
							<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">联系方式</td>
							<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 45px;">操作</td>
						</tr>
						<#list bidMemberList as bidMember>
						<tr>
							<td style="text-align: center; background-color: #fff;">${bidMember.name}</td>
							<td style="text-align: center; background-color: #fff;">${bidMember.position}</td>
							<td style="text-align: center; background-color: #fff;">${bidMember.contact}</td>
							<td style="text-align: center;">
								<a onclick="javascript:deleteRow(${bidMember.id} ,1)">删除</a>
							</td>
						</tr>
						</#list>

					</table>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title">专业负责人</td>
				<td colspan="4">
					<input name="bidPlan.masterName" value="${bidPlan.masterName}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<p>
						<strong>四、进度计划</strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title">进度计划</td>
				<td colspan="4">
					<div style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px" class="add_link">
						<a href="javascript:addBidSpeed(); ">+新增</a>
					</div>
					<table id="bidSpeedId" border="1" cellspacing="1" cellpadding="1" class="list_table4">
						<tr>
							<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">工作内容</td>
							<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">形成时间</td>
							<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">责任人</td>
							<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 45px;">操作</td>
						</tr>
						<#list bidSpeedList as bidSpeed>
						<tr>
							<td style="text-align: center; background-color: #fff;">${bidSpeed.workContent}</td>
							<td style="text-align: center; background-color: #fff;">${bidSpeed.completeDate}</td>
							<td style="text-align: center; background-color: #fff;">${bidSpeed.masterName}</td>
							<td style="text-align: center;">
								<a onclick="javascript:deleteRow(${bidSpeed.id} ,2)">删除</a>
							</td>
						</tr>
						</#list>

					</table>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title red">审批人</td>
				<td colspan="4">
					<input type="hidden" name="approverId" id="approverId" value="${bidPlan.approver.id}" />
					<input type="text" class="text_a" name="approverName" id="approverName" value="${bidPlan.approver.name}"
						readonly="readonly" onclick="javascript:getApprover('approverId','approverName');"
					/>
				</td>
			</tr>
			<#if bidPlan.approver.id == '${user.id}' >
			<tr>
				<td width="10%" class="tab_title red">处理方式</td>
				<td colspan="4">
					<input type="radio" name="bidPlan.operaterType" id="operaterType" value="0" onclick="javascript:isStop(this);"
					<#if bidPlan.operaterType == 0 >checked='checked'</#if>/> &nbsp;&nbsp;继续项目 &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="bidPlan.operaterType" id="operaterType" value="1" onclick="javascript:isStop(this);"
					<#if bidPlan.operaterType == 1 >checked='checked'</#if>/> &nbsp;&nbsp;终止项目
				</td>
			</tr>

			<tr>
				<td width="10%" class="tab_title red">审批意见</td>
				<td colspan="4">
					<input name="bidPlan.auditContent" value="${bidPlan.auditContent}" type="text" class="text_a" />
				</td>
			</tr>


			<tr id="nextOperator">
				<td width="10%" class="tab_title red">合同评审处理人</td>
				<td colspan="8">
					<input type="hidden" name="nextOperatorId" id="nextOperatorId" value="${project.nextOperator}" />
					<input type="text" name="nextOperatorName" id="nextOperatorName" value="${nextOperatorName}"
						class="text_a" readonly="readonly" onclick="getNextOperator('nextOperatorId','nextOperatorName')"
					/>
				</td>
			</tr>

			</#if>
			<tr>
				<td colspan="10" style="text-align: right;">
					<input type="button" value="取消" class="sub" onclick="javascript: history.go(-1);" />
					<#if bidPlan.status == '2' >
					<input type="button" value="提交任务" class="sub" onclick="javascript: toNextStep();" />
					<#else>
					<input type="button" value="提交审批" class="sub" onclick="javascript: toSubmit();" />
					</#if>
					<input type="submit" value="保存" class="sub" />

				</td>
			</tr>
		</table>

	</div>
</form>
<script type="text/javascript">
	var i = 0;
	var j = 10000;
	var flag = true;
	var flog = true;
	var operaterType = 0;
	$().ready(

	function() {
		$("#contractForm").validate( {
			rules : {
				'bidDeptName' : {
					required : true
				},
				'bidPlan.area' : {
					required : true
				},
				'bidPlan.bidDate' : {
					required : true
				},
				'bidPlan.tendTel' : {
					required : true
				},
				'bidPlan.tendTel' : {
					isTel : true
				},
				'bidPlan.tendRange' : {
					required : true
				},
				'bidPlan.tendContent' : {
					required : true
				},
				'bidPlan.bidNeed' : {
					required : true
				},
				'bidPlan.openDate' : {
					required : true
				},
				'bidPlan.openPlace' : {
					required : true
				},
				'bidPlan.bondAmount' : {
					required : true
				},
				'bidPlan.bondAmount' : {
					number : true
				},
				'bidPlan.bidFileIds' : {
					required : true
				},
				
				'bidPlan.auditContent' : {
					required : true
				},
				'bidPlan.tendMail' : {
					email : true
				},
				'bidPlan.operaterType' : {
					required : true
				},
				'approverName' : {
					required : true
				},
			}
		})
	});
	
	function toSubmit(){
		var approverName = $('#approverName').val();
		if(approverName.length<1){
			alert("请选择审批人！");
			return;
		}
		if (confirm("确定提交到【"+approverName+"】进行审批吗?")){
			document.getElementById("contractForm").action = "/bid/save.htm?toApprover=true";
			$("#contractForm").submit();
		}
	}

	function isStop(obj){
		if(obj.value==0){
			$("#nextOperator").css("display",""); 
			operaterType = 0;
		}
		if(obj.value==1){
			$("#nextOperator").css("display","none");
			operaterType = 1;
		}
	}
	
	function toNextStep() { //执行下一步
		var nextOperator = $("#nextOperatorName").val();
		if(nextOperator.length<1){
		alert("请选择下一环节处理人");
		return;
		}
		var message = "";
		if(operaterType==0){
			message = "当前步骤执行完毕,下一环节为【合同评审】，下一环节处理人为【" + nextOperator + "】，确定要执行下一步吗?";
		}else{
			message = "确认终止此项目吗？";
		}
		if (confirm(message)) {
			document.getElementById("contractForm").action = "/bid/toNextStep.htm";
			$("#contractForm").submit();
		}
	}
	
	function AddRow_member() {
		//添加一行
		var tab1 = document.getElementById("bidMemberId");
		if (flag) {
			i = i + tab1.rows.length;
			flag = false;
		} else {
			i = i + 1;
		}
		var newTr = tab1.insertRow();
		newTr.id = i;
		//添加列
		var newTd0 = newTr.insertCell();
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<input type="text" name="name"  class="text_a"  />';
		newTd1.innerHTML = '<input type="text" name="position"  class="text_a"  />';
		newTd2.innerHTML = '<input type="text" name="contact"  class="text_a"  />';
		newTd3.innerHTML = '<a onclick="javascript:deleteRow(' + i + ',0)">删除</a>';
	}
	
	function AddRow_speed() {
		//添加一行
		var tab2 = document.getElementById("bidSpeedId");
		if (flog) {
			j = j + tab2.rows.length;
			flog = false;
		} else {
			j = j + 1;
		}
		var newTr1 = tab2.insertRow();
		newTr1.id = j;
		//添加列
		var newTd01 = newTr1.insertCell();
		var newTd11 = newTr1.insertCell();
		var newTd21 = newTr1.insertCell();
		var newTd31 = newTr1.insertCell();
		//设置列内容和属性
		newTd01.innerHTML = '<input type="text" name="workContent"  class="text_a"  />';
		newTd11.innerHTML = '<input type="text" name="completeDate" readonly="readonly" onClick="WdatePicker({dateFmt:\'yyyy-MM-dd\'})"  class="text_a"  />';
		newTd21.innerHTML = '<input type="text" name="masterName"  class="text_a"  />';
		newTd31.innerHTML = '<a onclick="javascript:deleteRow(' + j + ',0)">删除</a>';
	}
	
	function deleteRow(id,s) {
        if(s=="0"){
		$('#' + id).remove();
			return;
	    }
		if (!confirm("确定删除?")) {
			return;
		}   
		var url = "/bid/deletecls.htm?bidId=" + id+"&types="+s;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
					//location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	    }

	function addBidMember() {
		AddRow_member();
	}

	function addBidSpeed() {
		AddRow_speed();
	}
	
	function getNextOperator(userIdId, userNameId) {
		window.open('/user/selectMultiUser.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId, '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}
	
	function getNextOperatorValue(ids,names) {
		$('#nextOperatorId').val(ids);
		$('#nextOperatorName').val(names);
	}

	function getDept() {
		window.open('/dept/selectDept.htm','_blank','channelmode=yes,width=400,height=500,left=200,top=100');
	}
	
	function doAfterGetDept(obj){
		$('#bidDeptId').val(obj.deptId);
		$('#bidDeptName').val(obj.deptName);
	}
	
	function getApprover(userIdId,userNameId) {
		window.open('/user/getUserByDeptId.htm?userIdId='+userIdId+'&userNameId='+userNameId,'_blank','channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}
	
</script>

