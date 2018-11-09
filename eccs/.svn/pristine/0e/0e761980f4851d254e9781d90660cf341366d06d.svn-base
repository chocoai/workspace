<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>

<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a> >
	<a href="/marketManager.htm">经营管理</a> >
	<a href="/review/list.htm">合同评审</a> > ${contract.project.name}
</div>
<form action="/review/save.htm" method="post" id="contractReviewForm">
	<div id="content">
		<div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;">
			合同评审
		</div>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td class="tab_title" style="width: 10%; color: Red;">
					合同名称
				</td>
				<td style="background-color: #fff;">
					<input type="hidden" name="contract.id" value="${contract.id}" />
					<input type="hidden" name="contractReview.id" value="${contractReview.id}" />
					<input type="hidden" name="contractReview.name" value="${contractReview.name}" />
					<input type="hidden" name="nextStepName" id="nextStepName" value=""/>
					<input type="text" class="text_a" name="contract.name" value="${contract.name}" />
				</td>
				<td width="10%" class="tab_title red">
					合同编号
				</td>
				<td width="30%" style="background-color: #fff;">
					<input type="text" class="text_a" name="contractReview.contractNo" value="${contractReview.contract.no}" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					项目名称
				</td>
				<td style="background-color: #fff;">
					${contract.project.name}
				</td>
				<td width="10%" class="tab_title">
					项目编号
				</td>
				<td width="30%" style="background-color: #fff;">
					${contract.project.no}
				</td>
			</tr>
			<!-- 
			<tr>
				<td class="tab_title">
					工程概况
				</td>
				<td colspan="3" style="background-color: #fff;">
					${contract.projectInfo}
				</td>
			</tr>
			-->
			<tr>
				<td class="tab_title">
					咨询类型
				</td>
				<td style="background-color: #fff;">
					${contract.project.serviceType.name}
				</td>
				<td class="tab_title">
					业务范围
				</td>
				<td style="background-color: #fff;">
					${contract.project.editorialType.name}
				</td>
			</tr>
			
			<tr>
				<td class="tab_title">
					委托单位
				</td>
				<td style="background-color: #fff;">
					${contract.project.customer.cusName}
				</td>
				<#list contractReviewItemList as contractReviewItem>
				<#if contractReviewItem_index = 0>
				<td  class="tab_title">
					${contractReviewItem.reviewName}
					<input type="hidden" name="reviewName" value="${contractReviewItem.reviewName}" />
				</td>
				<td style="background-color: #fff;">
					<input type="text" class="text_a" name="content" value="${contractReviewItem.content}" />
				</td>
					<input type="hidden" class="text_a" name="satisfaction" value="${contractReviewItem.satisfaction}"/>
			</tr>
			<!-- 
			<tr>
				<td class="tab_title2">
					合同收费程序
				</td>
				<td colspan="3" style="background-color: #fff;">
					${contract.chargeRemark}
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					签约部门
				</td>
				<td style="background-color: #fff;">
					${contract.contractDept.name}
				</td>
				<td class="tab_title">
					签约人
				</td>
				<td style="background-color: #fff;">
					${contract.manager.name}
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					结算方式
				</td>
				<td colspan="3" style="background-color: #fff;">
					<#if contract.contractPay == 1>&nbsp;包干&nbsp;&nbsp;&nbsp;&nbsp; </#if> 
					<#if contract.contractPay == 0>&nbsp;暂定</#if>
				</td>
			</tr>
			
			<tr>
				<td class="tab_title">
					工程投资(万元)
				</td>
				<td style="background-color: #fff;">
					${contract.projectAmount}
				</td>
				<td class="tab_title">
					合同金额(万元)
				</td>
				<td style="background-color: #fff;">
					${contract.allAmount}
				</td>
			</tr>
			-->
			
			<tr>
				<td colspan="4">
					<em>&nbsp;</em>
				</td>
			</tr>
			<tr>
				<td class="tab_title red">
					初评人
				</td>
				<td style="background-color: #fff;">
					<input type="hidden" name="receiveManagerId" id="receiveManagerId" />
					<input type="text" name="contractReview.recordName" id="contractReview.recordName" value="${contractReview.recordName}" readonly="readonly"
						onclick="getProjectManager('receiveManagerId','contractReview.recordName');" class="text_a" />
				</td>
			<#-->
				<td>
					<input name="contractReview.recordName"
						value="${contractReview.recordName}" type="text" class="text_a" />
				</td>
			<-->	
				<td class="tab_title red">
					记录日期
				</td>
				<td  style="background-color: #fff;">
					<input name="contractReview.recordDate" readonly="readonly" onClick="javascript:WdatePicker({dateFmt : 'yyyy-MM-dd'});"
						 value="${contractReview.recordDate}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					评审内容
				</td>
				<td colspan="3" class="tab_title">
					<div
						style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px"
						class="add_link">
						<#if  contractReview.status == '2' >
						<#else>
						<a href="javascript:addReviewItem(); ">+新增</a>
						</#if>
					</div>
					<table border="1" id="contractReviewItemId" cellspacing="1"
						cellpadding="1" class="list_table3" style="margin-top: 10px;">
						<tr>
							<td width="234"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								名称
							</td>
							<td width="133"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								评审内容
							</td>
							<td width="155"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								评审意见
							</td>
							<td width="60"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								操作
							</td>
						</tr>
						<#--><#list contractReviewItemList as contractReviewItem><-->
						<#else>
						<tr>
							<td style="text-align: center; background-color: #fff;">
								${contractReviewItem.reviewName}
								<input type="hidden" name="reviewName" value="${contractReviewItem.reviewName}" />
							</td>
							<td style="text-align: center; background-color: #fff;">
								<input type="text" class="text_a" name="content" value="${contractReviewItem.content}" />
							</td>
							<input type="hidden" class="text_a" name="satisfaction" value="${contractReviewItem.satisfaction}"/>
				<!--			<td style="text-align: center; background-color: #fff;">
								<input type="text" class="text_a" name="satisfaction" value="${contractReviewItem.satisfaction}"/>
							</td> -->
							<#if  contractReviewItem_index = 1 >
							<td style="text-align: center; background-color: #fff;" id="textareatdid" rowSpan="${contractReviewItemList?size - 1}">
								<textarea  style="margin-top:7px;min-height:120px;width:100%;overflow:auto;border:solid 1px #ccffff" id="textareaid" rows="${contractReviewItemList?size -1}"  name="contractReview.reviewView" >${contractReview.reviewView} </textarea>
							</td>
							</#if>
							<#if  contractReview.status == '2' >
							<#else>
							<td style="text-align: center;">
									<a onclick="javascript:deleteRow(${contractReviewItem.id} ,1)">删除</a>
							</td>
							</#if>
						</tr>
						</#if>
						</#list>
					</table>
				</td>
			</tr>
			<#-->
			<tr>
				<td class="tab_title">
					评审人签字
				</td>
				<td colspan="3">
					<input type="text" class="text_a" name="contractReview.reviewName" value="${contractReview.reviewName}" />
				</td>
			</tr>
			<-->
	<!--		<tr>
				<td class="tab_title">
					评审意见
				</td>
				<td colspan="3">
					<input type="text" class="text_a" name="contractReview.reviewView" value="${contractReview.reviewView}" />
				</td>
			</tr> -->
			<tr>
				<td class="tab_title red">
					审批人
				</td>
				<td colspan="3">
					<input type="hidden" name="approverId" id="approverId" value="${contractReview.approver.id}" />
					<input type="text" name="approverName" id="approverName" value="${contractReview.approver.name}" 
						readonly="readonly" class="text_a" onclick = "javascript:getApprover('approverId','approverName');" />
				</td>
			</tr>
			<#if contractReview.approver.id == '${user.id}' && contractReview.status==2>
			<tr>
				<td class="tab_title red">
					处理方式
				</td>
				<td colspan="3">
					<input type="radio" name="contractReview.operaterType" id="operaterType" value="0" onclick="javascript:isStop(this);" <#if contractReview.operaterType == 0 >checked='checked'</#if>/>
						&nbsp;&nbsp;继续项目 &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="contractReview.operaterType" id="operaterType" value="1" onclick="javascript:isStop(this);" <#if contractReview.operaterType == 1 >checked='checked'</#if>/>
						&nbsp;&nbsp;终止项目
				</td>
			</tr>

			<tr>
				<td  class="tab_title red">
					审批意见
				</td>
				<td colspan="8">
					<input type="text" class="text_a" name="contractReview.approveView" value="${contractReview.approveView}" />
				</td>
			</tr>

			<tr id="nextOperator">
				<td class="tab_title red">
					合同登记处理人
				</td>
				<td colspan="3">
					<input type="hidden" name="nextOperatorId" id="nextOperatorId" value="${contractReview.contract.project.nextOperator.id}" />
					<input type="text" name="nextOperatorName" id="nextOperatorName" class="text_a"  readonly="readonly"
						value="" onclick="javascript:getUser('nextOperatorId','nextOperatorName');" />
				</td>
			</tr>
			</#if>
			<tr>
				<td class="tab_title">
					添加附件
				</td>
				<td colspan="3">
					<div
					<#if  contractReview.status == '2' >
					<#else>
						style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px"
						class="add_link">
						<a href="javascript:showFileUpload()">+新增</a>
					</#if>
					</div>
					<table border="1" id="fileListId" cellspacing="1" cellpadding="1"
						class="list_table3" style="margin-top: 10px;">
						<tr>
							<td width="254"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								附件名称
							</td>
							<td width="100"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								附件类型
							</td>
							<td width="205"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								上传人
							</td>
							<td width="120"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								上传时间
							</td>
							<td width="100"
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								操作
							</td>
						</tr>

						<#list annexList as annex>
						<tr>
							<td style="text-align: center; background-color: #fff;">
								${annex.name }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${annex.annexType.name }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${annex.user.name }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${annex.ctime }
							</td>
							<td style="text-align: center;">
							<#if  contractReview.status == '2' >
							<#else>
								<a onclick="javascript:deleteRow(${annex.id} ,2)">删除</a>
							</#if>	
							</td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right;">
					<input type="button" class="sub" value="取消" onclick="javascript: history.go(-1);" />
					<#if  contractReview.status == '2' >
					<input type="button" class="sub" value="提交任务" onclick="javascript: toNextStep();" />
					<#else>
					<input type="button" class="sub" value="提交审批" onclick="javascript: toSubmit();" />
					</#if>
					<input type="submit" class="sub" value="保存" />
				</td>
			</tr>
		</table>
	</div>
</form>

<script type="text/javascript">
	var i = 0;
	var flag = true;
	var operaterType = 0;
	$().ready(
	function() {
		$("#contractReviewForm").validate( {
			rules : {
				'contract.name' : {
					required : true
				},
				'sendUser.name' : {
					required : true
				},
				'contractReview.recordName' : {
					required : true
				},
				'contractReview.recordDate' : {
					required : true
				},
				'contractReview.approveName' : {
					required : true
				},
				'approverName' : {
					required : true
				},
				'contractReview.contractNo' : {
					required : true
				},
				'contractReview.operaterType' : {
					required : true
				},
			}
		})
	});

	function getProjectManager(userIdId, userNameId) {
		var deptId = "";
		window.open('/user/getUserByDeptId.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId + '&deptId=' + deptId, '_blank',
				'channelmode=yes,width=800px,height=500px,left=200,top=100');
	}

	function toNextStep() { //执行下一步
		var nextOperator = $("#nextOperatorName").val();
		
		var message = "";
		var nextStepName = "合同登记";
		if(operaterType==0){
			if(nextOperator==""){
				alert("请选择下一环节处理人？");
				return;
			}
			$("#nextStepName").val(nextStepName);
			message = "当前步骤执行完毕,下一环节为【"+nextStepName+"】，下一环节处理人为【" + nextOperator + "】，确定要执行下一步吗?";
		}else{
			message = "确认终止此项目吗？";
		}
		if (confirm(message)) 
		{
			document.getElementById("contractReviewForm").action = "/review/toNextStep.htm";
			$("#contractReviewForm").submit();
		}
	}
	
	function toSubmit(){
		var approverName = $('#approverName').val();
		if(approverName.length<1){
			alert("请选择审批人");
			return;
		}
		var nextStepName = "合同审批";
		if (confirm("确定提交到【"+approverName+"】进行审批吗?")){
			$("#nextStepName").val(nextStepName);
			document.getElementById("contractReviewForm").action = "/review/save.htm?toApprover=true";
			$("#contractReviewForm").submit();
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

	function showFileUpload() { //上传附件
		   var iWidth=650;                          //弹出窗口的宽度; 
           var iHeight=500;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open('/file/fileReview.htm?contractReview.id=${contractReview.id}','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}

	function AddRow() {
		//添加一行
		//var tab1 = $("#contractReviewItemId");

		var textareatdid =  document.getElementById("textareatdid");
		textareatdid.rowSpan = textareatdid.rowSpan + 1;
		var textareaid = $("#textareaid");
		textareaid.attr("rows",textareatdid.rowSpan)
		
		var tab1 = document.getElementById("contractReviewItemId");
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
	//	var newTd3 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<input type="text" name="reviewName"  class="text_a"  />';
		newTd1.innerHTML = '<input type="text" name="content"  class="text_a"  />';
	//	newTd2.innerHTML = '<input type="text" name="satisfaction"  class="text_a"  />';
		newTd2.style.textAlign="center";
		newTd2.innerHTML = '<a onclick="javascript:deleteRow(' + i + ',0)">删除</a>';
	}

	function deleteRow(i,s) {
	  if(s=="0"){
		$('#' + i).remove();
		var textareatdid =  document.getElementById("textareatdid");
		textareatdid.rowSpan = textareatdid.rowSpan - 1;
		var textareaid = $("#textareaid");
		textareaid.attr("rows",textareatdid.rowSpan)
		
		
			return;
	    }
		if (!confirm("确定删除?")) {
			return;
		}   
		var url = "/review/deletecls.htm?bidId=" + i+"&types="+s;
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

	function addReviewItem() {
		AddRow();
	}

	function getUser(userIdId,userNameId) {
		window.open('/user/getUserByDeptId.htm?userIdId='+userIdId+'&userNameId='+userNameId,'_blank','channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
		
	}
	
	function getApprover(userIdId,userNameId) {
		window.open('/user/getUserByDeptId.htm?userIdId='+userIdId+'&userNameId='+userNameId,'_blank','channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}
</script>