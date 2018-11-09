<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){	
		$('.tabPanel ul li').click(function(){
			$(this).addClass('hit').siblings().removeClass('hit');
			$('.panes>div:eq('+$(this).index()+')').show().siblings().hide();	
		})
	})
</script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if>
<a href="javascript:window.location.reload();">制定编制咨询方案</a>
</div>
<form action="/step3/save.htm" method="post" id="contractForm">
	<div id="content">
		<input name="project.id" value="${project.id}" type="hidden" />
		<input name="step3.id" value="${step3.id}" type="hidden" />
		<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			建设工程造价咨询工作方案
		</div>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<!-- <tr>
				<td colspan="6"
					style="font-weight: bold; font-size: 20px; text-align: center;">
					建设工程造价咨询工作方案
				</td>
			</tr> -->
			<tr>
				<td width="" class="tab_title">
					项目基本情况
				</td>
				<td width="" colspan="5">
					<textarea name="step3.projectInfo" cols="45" rows="5"
						style="font-size:14px;resize:none;width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${step3.projectInfo }</textarea>
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					咨询工作目标和范围
				</td>
				<td colspan="5">
					<textarea name="step3.consultTargetInfo" cols="45" rows="5"
						style="font-size:14px;resize:none;width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${step3.consultTargetInfo }</textarea>
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					人员分配表
				</td>
				<td colspan="5">
					<table border="1" cellspacing="1" cellpadding="1" class="input_table" style="width: 100%;">
						<#list swtList as swt>
											<#if swt_index%2==0>
											<tr>
											<td style="text-align:right;background-color:#fff;width: 20%;">${swt.proceStepDef.stepName}</td>
											<td style="text-align:left;background-color:#fff;">${swt.step3Worker.workUserName}</td>
											<#else> 
											<td style="text-align:right;background-color:#fff;width: 20%;">${swt.proceStepDef.stepName}</td>
											<td style="text-align:left;background-color:#fff;">${swt.step3Worker.workUserName}</td>
											</tr>
											</#if>
								</#list>
					</table>
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					工作程序和处理原则
				</td>
				<td colspan="5">
					<textarea name="step3.workHandleInfo" cols="45" rows="5"
						style="font-size:14px;resize:none;width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${step3.workHandleInfo }</textarea>
				</td>
			</tr>
		<!--	<tr>
				<td class="tab_title">
					编制依据
				</td>
				<td colspan="5">
					<textarea name="step3.basisCompilation" cols="45" rows="5"
						style="font-size:14px;width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${step3.basisCompilation}</textarea>
				</td>
			</tr>
			-->
			<tr>
				<td  class="tab_title">合同编号</td>
		        <td colspan="4" style="background-color:#efefef;" valign="top" align="left" >
		        ${step3.project.no}
		        </td>
			</tr>
			<tr>
				<td class="tab_title">
					领导批示
				</td>
				<td colspan="5">
					<textarea name="step3.masterSign" cols="45" rows="5"
						style="font-size:14px;resize:none;width: 100%; border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">${step3.masterSign }</textarea>
				</td>
			</tr>
		<!--	<tr>
				<td class="tab_title red">
					技术总负责人
				</td>
				<td colspan="2">
					<input name="step3.techMasterName" value="${step3.techMasterName }"
						type="text" class="text_a" />
				</td>
				<td class="tab_title red">
					单位法定代表人
				</td>
				<td colspan="2">
					<input name="step3.lawMasterName" value="${step3.lawMasterName }"
						type="text" class="text_a" />
				</td>
			</tr>
			-->
			<tr>
				<td class="tab_title">
					制定日期
				</td>
				<td colspan="2">
					<input name="step3.ctime" value="${step3.ctime }" readonly="readonly"
						onClick="javascript:WdatePicker( {dateFmt: 'yyyy-MM-dd'});" type="text" class="text_a" />
				</td>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td colspan="6" style="text-align: right;">
					<input type="button" onclick="location.href ='javascript:history.back();';" value="取消" class="sub" />
					<input type="submit" value="保存" class="sub" />
					<input type="button" onclick="javascript:toNextStep1();"  value="转交任务"  class="sub" />  
					<#if user.username != "${SUPERADMIN}" >
					<input type="button" onclick="javascript: toNextStep();" value="提交任务" class="sub" />
					</#if>
				</td>
			</tr>
		</table>
	</div>
</form>

<script>
//<!--	$().ready(function() {
//		$("#contractForm").validate( {
//			rules : {
//				'step3.techMasterName' : {
//					required : true
//				},
//				'step3.lawMasterName' : {
//					required : true
//				}
//			}
//		})
//	});
//-->
	function toNextStep() { //执行下一步
		if (confirm("当前步骤执行完毕,确定要执行下一步吗?")) {
			document.getElementById("contractForm").action = "/step3/toNextStep.htm";
			$("#contractForm").submit();
		}
	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step3/save.htm";
		document.getElementById("contractForm").submit();
	}
	
</script>
