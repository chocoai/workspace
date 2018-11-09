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
    <a href="/bidPlanning.htm">投标策划</a>
    >
    <a href="">保证金评估编辑</a>
</div>
<form action="/bid/bond/save.htm" method="post" id="contractForm">
	<div id="content">
		<div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
		>支付投标保证金申请函</div>
		<input type="hidden" name="bondAssess.cid" value="${bondAssess.cid}" />
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td width="10%" class="tab_title">申请编号</td>
				<td colspan="2">
					<input type="text" name="bondAssess.applyNo" value="${bondAssess.applyNo}" class="text_a"  readonly="readonly"/>
				</td>
				<td width="10%" class="tab_title">申请日期</td>
				<td>
                    <input class="text_a" id="opentime" type="text" name="bondAssess.createTime"
                        value="${bondAssess.createTime}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
                <td width="10%" class="tab_title">项目名称</td>
                <td colspan="2">
                    <input name="bondAssess.projectInfo.proname" value="${bondAssess.projectInfo.proname}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">标包</td>
                <td>
                    <input name="bondAssess.bidPkg" value="${bondAssess.bidPkg}" type="text" class="text_a" readonly="readonly"/>
                </td>
			</tr>
			<tr>
				<td width="10%" class="tab_title red">汇款户名</td>
				<td colspan="2">
                    <input name="bondAssess.remittancesName" value="${bondAssess.remittancesName}" type="text" class="text_a" maxlength="20"/>
				</td>
				<td width="10%" class="tab_title red">汇款账号</td>
				<td>
                    <input name="bondAssess.remittancesAccount" value="${bondAssess.remittancesAccount}" type="text" class="text_a" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title red">开户银行</td>
				<td colspan="2">
                    <input name="bondAssess.depositBank" value="${bondAssess.depositBank}" type="text" class="text_a" maxlength="20"/>
				</td>
				<td width="10%" class="tab_title red">行号</td>
				<td>
                    <input name="bondAssess.bankAccount" value="${bondAssess.bankAccount}" type="text" class="text_a" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title red">汇款金额</td>
				<td colspan="2">
                    <input name="bondAssess.remittanceAmount" value="${bondAssess.remittanceAmount}" type="text" class="text_a" />
				</td>
				<td width="15%" class="tab_title">保证金递交截止时间</td>
				<td>
                    <input class="text_a" id="opentime" type="text" name="bondAssess.deadline"
                        value="${bondAssess.deadline}" readonly="readonly"
                        onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title">招标人</td>
				<td colspan="2">
                    <input name="bondAssess.customer.cusName" value="${bondAssess.customer.cusName}" type="text" class="text_a" readonly="readonly"/>
				</td>
				<td width="10%" class="tab_title">联系人电话</td>
				<td>
                    <input name="bondAssess.bidderTel" value="${bondAssess.bidderTel}" type="text" class="text_a" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title red">招标代理人</td>
				<td colspan="2">
                    <input name="bondAssess.bidAgent" value="${bondAssess.bidAgent}" type="text" class="text_a" maxlength="20"/>
				</td>
				<td width="10%" class="tab_title red">联系人电话</td>
				<td>
                    <input name="bondAssess.agentTel" value="${bondAssess.agentTel}" type="text" class="text_a" maxlength="15"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title">特殊要求</td>
				<td colspan="4">
                    <input name="bondAssess.specialRequirements" value="${bondAssess.specialRequirements}" type="text" class="text_a" maxlength="50"/>
				</td>
			</tr>
            <tr>
                <td width="10%" class="tab_title">退还保证金时间</td>
                <td colspan="2">
                    <input class="text_a" id="opentime" type="text" name="bondAssess.rreturnTime"
                        value="${bondAssess.rreturnTime}" readonly="readonly"
                        onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                </td>
                <td width="10%" class="tab_title">退还保证金特殊说明</td>
                <td>
                    <input name="bondAssess.returmSpecialInstr" value="${bondAssess.returmSpecialInstr}" type="text" class="text_a" maxlength="100"/>
                </td>
            </tr>
            <tr>
                <td class="tab_title red" width="12%">
                    <span id="nextStepNamea">下一环节处理人</span>
                </td>
                <td colspan="5">
                    <input type="hidden" name="nextOperatorId" id="nextOperatorId" value="${nextOperatorId}" />
                    <input type="text" name="nextOperatorName" id="nextOperatorName" value="${nextOperatorName}"
                        class="text_a" readonly="readonly" onclick="javascript:getNextOperator('nextOperatorId','nextOperatorName');"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="6" style="text-align: right;">
                    <input type="button" class="sub" value="取消" onclick="javascript: history.go(-1);" />
                    <input type="button" class="sub" value="提交" onclick="toNextStep()"/>
                    <input type="submit" class="sub" value="保存" />
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
				'bondAssess.remittancesName' : {
                    required : true
                },
                'bondAssess.remittancesAccount' : {
                    required : true
                },
                'bondAssess.depositBank' : {
                    required : true
                },
                'bondAssess.bankAccount' : {
                    required : true
                },
                'bondAssess.remittanceAmount' : {
                    required : true,
                    number : true
                },
                'bondAssess.bidAgent' : {
                    required : true
                },
                'bondAssess.agentTel' : {
                    required : true
                }
			}
		})
	});
	
	function toNextStep() { //执行下一步
		var nextOperator = $("#nextOperatorName").val();
		if(nextOperator.length<1){
		alert("请选择下一环节处理人");
		return;
		}
		var message = "";
		if(operaterType==0){
			message = "当前步骤执行完毕,下一环节为【保证金申请审核】，下一环节处理人为【" + nextOperator + "】，确定要执行下一步吗?";
		}else{
			message = "确认终止此项目吗？";
		}
		if (confirm(message)) {
			document.getElementById("contractForm").action = "/bid/bond/toNextStep.htm";
			$("#contractForm").submit();
		}
	}
	
	function getNextOperator(userIdId, userNameId) {
		window.open('/user/selectMultiUser.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId, '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}
	
	function getNextOperatorValue(ids,names) {
		$('#nextOperatorId').val(ids);
		$('#nextOperatorName').val(names);
	}

</script>

