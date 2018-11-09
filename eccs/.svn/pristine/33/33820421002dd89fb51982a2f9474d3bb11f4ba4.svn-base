<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if>
<a href="javascript:window.location.reload();">整理资料清单</a>
</div>
<form action="/step4/save.htm" method="post" id="contractForm">
	<div id="content">
	<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			建设工程造价咨询相关资料整理单
		</div>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<!-- <tr>
				<td colspan="4"
					style="font-weight: bold; font-size: 20px; text-align: center;">
					建设工程造价咨询相关资料整理单
				</td>
			</tr> -->
			<tr>
				<td width="15%" class="tab_title">
					工程名称
				</td>
				<td colspan="3">
					<input name="step4.id" value="${step4.id}" type="hidden" />
					<input name="project.id" value="${project.id}" type="hidden" />
					<input name="project.name" value="${project.name}"
						readonly="readonly" id="projectNameId" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					委托单位
				</td>
				<td width="31%">
					<input value="${project.customer.cusName}" type="text"
						readonly="readonly" id="senderDeptNameId" class="text_a" />
				</td>
				<td width="19%" class="tab_title">
					咨询类别
				</td>
				<td width="35%">
				<input value="${project.serviceType.name} " type="text" 
						readonly="readonly" id="consultTypeId" class="text_a" />
				</td>
			</tr>
			<tr>
				<td width="15%" class="tab_title">
					软件、规范等
				</td>
				<td colspan="3">
					<textarea name="step4.standard" cols="45" rows="5"
						style="font-size:14px;width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${step4.standard}</textarea>
				</td>
			</tr>
			<tr>
				<td width="15%" class="tab_title">
					其他相关资料
				</td>
				<td colspan="3">
					<textarea name="step4.other" cols="45" rows="5"
						style="font-size:14px;width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${step4.other}</textarea>
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					<span class="red" style="font-weight: bold;">委托方于</span>
				</td>
				<td width="31%">
					<input name="step4.senderDataDate" value="${step4.senderDataDate }"
						readonly="readonly" onClick="WdatePicker( {dateFmt: 'yyyy-MM-dd'});" type="text" class="text_a" />
				</td>
				<td width="19%" class="tab_title">
					<span class="red"  style="font-weight: bold;">日将下列资料交给受托方</span>
				</td>
				<td width="35%">
					<input name="step4.receiverUnit" value="${step4.receiverUnit }"
						type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding: 0px 0px;">
					<table border="1" id="step4ItemId" cellspacing="1" cellpadding="1" class="list_table4" style="margin: 0px auto;border-width:0px; border-style:hidden;">
						<tr>
				            <td colspan="3"> 
					            <div class="add_link"  style=" display:block; text-align:right;float:right;border-bottom:0px;" >
                                 	<a href="javascript:addStep4Item(); ">+新增</a>
								</div>
							</td>
  						</tr>
  						
						<tr>
							<td style="text-align: center; font-weight: bold;" width="70%;">
								资料名称
							</td>
							<td style="text-align: center; font-weight: bold;" width="15%;">
								份数
							</td>
							<td style="text-align: center; font-weight: bold;" width="15%;">
								操作
							</td>
						</tr>
						<#list numList as num >
						<tr>
							<td style="background-color: #fff; text-align: center;">
								${num.name }
							</td>
							<td style="background-color: #fff; text-align: center;">
								${num.count}
							</td>
							<td style="background-color: #fff; text-align: center;">
								<a href="javascript:deleteFile(${num.id})">删除</a>
							</td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="7" style="text-align: right;">
					<input type="button" onclick="location.href ='javascript:history.back();';" value="取消" class="sub" />
					<input type="submit" value="保存" class="sub" />
					<input type="button" onclick="javascript:toNextStep1();"  value="转交任务"  class="sub" />  
					<#if user.username != "${SUPERADMIN}" >
					<input type="button" onclick="javascript:toNextStep();" value="提交任务" class="sub" />
					</#if>
				</td>
			</tr>
		</table>
	</div>
</form>
<script>
	var i = 0;
	var flag = true;
	$().ready(function() {
		$("#contractForm").validate( {
			rules : {
				'step4.senderDataDate' : {
					required : true
				},
				'step4.receiverUnit' : {
					required : true
				}
			}
		})
	});
	
	function AddRow() {
		//添加一行
	var tab1 = document.getElementById("step4ItemId");
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
		//设置样式
		newTd2style="background-color: #fff; text-align: center;";
		//设置列内容和属性
		newTd2.innerHTML = '<a onclick="javascript:deleteRow(' + i + ')" >删除</a>';
		newTd0.innerHTML = '<input name="name"  type="text" class="text_a"/>';
		newTd1.innerHTML = '<input name="count"  type="text" class="text_a"/>';
	}

	function deleteRow(i) {
		var tab1 = document.getElementById("step4ItemId");
		$('#' + i).remove();
		//tab1.deleteRow(i);
	}
	
	function addStep4Item() {
		AddRow();
	}
	
	function toNextStep() { //执行下一步
		if (confirm("当前步骤执行完毕,确定要执行下一步吗?")) {
			document.getElementById("contractForm").action = "/step4/toNextStep.htm";
			$("#contractForm").submit();
		}
	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step4/save.htm";
		document.getElementById("contractForm").submit();
	}
	
	function deleteFile(id){
	$.ajax({
			type:"get",
			url:'/step4/deleteFile.htm?fileId='+id,
			asyn:true,		//false为同步提交
			dataType:'text',	//返回文本
			success:function(d){
					window.parent.location.reload();
			}
		})
	}
</script>