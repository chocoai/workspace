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
    <a href="">报名评估编辑</a>
</div>
<form action="/bid/apply/save.htm" method="post" id="contractForm">
	<div id="content">
		<div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
		>投标报名评估</div>
		<input type="hidden" name="applyAssess.cid" value="${applyAssess.cid}" />
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td width="10%" class="tab_title">项目名称</td>
				<td colspan="2" class="tab_title">
					<input type="text" value="${applyAssess.projectInfo.proname}" class="text_a" readonly="readonly"/>
				</td>
				<td width="10%" class="tab_title">标包</td>
				<td>
					<input name="applyAssess.bidPkg" type="text" maxlength=50 class="text_a" value="${applyAssess.bidPkg}"/>
				</td>
			</tr>

			<tr>
            	<td width="10%" class="tab_title">招标人</td>
                <td colspan="2" class="tab_title">
                    <input value="${applyAssess.customer.cusName}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">咨询类型</td>
                <td>
                    <input name="applyAssess.consultType" value="${applyAssess.consultType}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>

            <tr>
            	<td width="10%" class="tab_title">招标文件(公告)</td>
                <td colspan="2">
                    <a href="${applyAssess.callBidFile}"   target="_blank">
                    	<input style="color:blue;" name="applyAssess.callBidFile" value="${applyAssess.callBidFile}" type="text" class="text_a" readonly="readonly"/>
                    </a>
                </td>
                <td width="10%" class="tab_title">代理公司</td>
                <td colspan="2">
                    <input name="applyAssess.agentCompany" value="${applyAssess.agentCompany}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
			<tr>
				<td width="10%" class="tab_title">报名日期</td>
				<td colspan="2">
                    <input class="text_a" type="text" name="applyAssess.applyDate" value="${applyAssess.applyDate}" readonly="readonly"/>
				</td>
				<td width="10%" class="tab_title">开标日期</td>
				<td>
                    <input class="text_a" type="text" name="applyAssess.bidOpenDate" value="${applyAssess.bidOpenDate}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title">咨询费估算</td>
				<td colspan="2">
                    <input name="applyAssess.consultFee" value="${applyAssess.consultFee}" type="text" class="text_a" readonly="readonly"/>
				</td>
				<td width="10%" class="tab_title">报名城市</td>
				<td>
                    <input name="applyAssess.signupCity" value="${applyAssess.signupCity}" type="text" class="text_a" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td width="10%" class="tab_title">评估结果</td>
				<td width="20%">
                    <i>报名</i>&nbsp;&nbsp;<input type="radio" name="applyAssess.assessResult" value="1" checked="checked"/>
                    <i>不参与</i>&nbsp;&nbsp;<input type="radio" name="applyAssess.assessResult" value="0"/>
				</td>
				<td width="15%" class="tab_title">不参与的评估说明</td>
				<td colspan="2">
                    <input name="applyAssess.appraisalnotes" value="${applyAssess.appraisalnotes}" type="text" class="text_a" maxlength="120"/>
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

    if("${applyAssess.assessResult}"==1){
        $(":radio").eq(0).attr("checked","checked");
    }else{
        $(":radio").eq(1).attr("checked","checked");
    }

	var i = 0;
	var j = 10000;
	var flag = true;
	var flog = true;
	var operaterType = 0;

	function toNextStep() { //执行下一步
		var nextOperator = $("#nextOperatorName").val();
		if(nextOperator.length<1){
		alert("请选择下一环节处理人");
		return;
		}
		var message = "";
		if(!$(":radio").eq(1).prop("checked")){
			message = "当前步骤执行完毕,下一环节为【报名评估审核】，下一环节处理人为【" + nextOperator + "】，确定要执行下一步吗?";
		}else{
			message = "不参与将终止此项目，是否继续？";
		}
		if (confirm(message)) {
			document.getElementById("contractForm").action = "/bid/apply/toNextStep.htm";
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

