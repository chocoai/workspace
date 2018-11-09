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
<a href="javascript:window.location.reload();">咨询任务书</a>
</div>
<form action="/step1/save.htm" method="post" id="contractForm">
	<div id="content">
		<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			咨询任务书
		</div>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<!-- <tr>
        	 		<td colspan="7" style="font-weight: bold; font-size: 20px; text-align: center;">咨询任务书</td>
        	 </tr> -->
			<tr>
				<td width="109" class="tab_title">项目名称</td>
				<td colspan="6">
					<input name="step1.id" value="${step1.id}" type="hidden" />
					<input name="project.id" value="${project.id}" type="hidden" />
					<input name="project.name" value="${project.name}"
						 readonly="readonly" id="projectNameId" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title ">咨询类型</td>
				<td colspan="6">
					<input id="serviceTypeNameId" value="${project.serviceType.name}"
						readonly="readonly" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title ">编审类型</td>
				<td colspan="6">
					<input id="editorialTypeNameId"
						value="${project.editorialType.name}" readonly="readonly"
						type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td  class="tab_title">项目类型</td>
				<td  colspan="6" style="padding: 0px 0px; background-color: #fff;">
					<table   cellspacing="1" id="step1ContactId" cellpadding="1" class="list_table4" style="margin: 0 auto;margin-left:0px;">				
						<tr>
							<td style="padding: 0px 0px; background-color: #fff;">
								1.土建工程：基础<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('基础')>checked="checked"</#if> value="基础"/>
								主体<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('主体')>checked="checked"</#if> value="主体"/>
								屋面<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('屋面')>checked="checked"</#if> value="屋面"/>
							</td>
						</tr>
						<tr>
							<td style="padding: 0px 0px; background-color: #fff;">
								2.装饰装修工程<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('装饰装修工程')>checked="checked"</#if> value="装饰装修工程"/>
							</td>
						</tr>
						<tr>
							<td style="padding: 0px 0px; background-color: #fff;">
								3.安装工程：给排水（含消防）<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('给排水')>checked="checked"</#if> value="给排水"/>
								电气<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('电气')>checked="checked"</#if> value="电气"/>
								通风空调<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('通风空调')>checked="checked"</#if> value="通风空调"/>
								智能建筑（含综合布线）<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('智能建筑')>checked="checked"</#if> value="智能建筑"/>
								电梯<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('电梯')>checked="checked"</#if> value="电梯"/>
							</td>
						</tr>
						<tr>
							<td style="padding: 0px 0px; background-color: #fff;">
								4.园林景观绿化工程<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('园林景观绿化工程')>checked="checked"</#if> value="园林景观绿化工程"/>
								5.市政工程<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('市政工程')>checked="checked"</#if> value="市政工程"/>
								6.其他<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('其他')>checked="checked"</#if> value="其他"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="109" class="tab_title">委托方资料</td>
				<td colspan="6" style="padding: 0px 0px;">
					<table border="1" cellspacing="1" id="step1ContactId" cellpadding="1" class="list_table4" style="width: auto;margin: 0 auto;border-collapse: collapse;border-width:0px; border-style:hidden;">
						<tr>
							<td colspan="1"
								style="font-weight: bold; text-align: right;">
								委托单位名称
							</td>
							<td colspan="5">
								<input name="step1.senderUnit" value="${project.customer.cusName}"
									type="text" class="text_d" />
							</td>
						</tr>
						<tr>
							<td colspan="1"
								style="font-weight: bold; text-align: right; color: red">
								详细地址
							</td>
							<#if project.customer.address!=null>
								<td colspan="5">
									<input name="step1.senderAddress"
										value="${project.customer.address}" type="text" class="text_d" />
								</td>
							<#else>
								<td colspan="5">
									<input name="step1.senderAddress"
										value="${step1.senderAddress }" type="text" class="text_d" />
								</td>
							</#if>
						</tr>
						<tr>
							<td width="100" style="text-align: center; font-weight: bold;">
								联系人姓名
							</td>
							<td width="306" style="text-align: center; font-weight: bold;">
								手机
							</td>
							<td width="221" style="text-align: center; font-weight: bold;">
								办公电话
							</td>
							<td width="221" style="text-align: center; font-weight: bold;">
								传真
							</td>
							<td width="220" style="text-align: center; font-weight: bold;">
								QQ或Email
							</td>
							<td width="45" style="text-align: center; font-weight: bold;">
								<div
									style="display: block; text-align: right; float: right;border-bottom: 0px"
									class="add_link">
									<a href="javascript:addStep1Contact(); ">+新增</a>
								</div>
							</td>
						</tr>
						<#list step1ContactList as step1Contact>
						<tr>
							<td style="text-align: center; background-color: #fff;">
								${step1Contact.name }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${step1Contact.officeTel }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${step1Contact.tel }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${step1Contact.fax }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${step1Contact.email }
							</td>
							<td style="text-align: center">
								 <a href="javascript:deletesz(${step1Contact.id})">删除</a>
							</td>
						</tr>
						</#list>
					</table>
					<tr>
						<td rowspan="5" style="text-align: right;">具体工作任务</td>
						<td
							style="width: 120px; height: 30px; line-height: 30px; text-align: right;">
							工期要求
						</td>
						<td colspan="5" style="width: 20px; height: 30px; line-height: 30px; text-align: left;">
							<input name="step1.startWorkTime" id="rtimec" onFocus="var rtimej=$dp.$('rtimej');WdatePicker({onpicked:function(){rtimej.focus();},maxDate:'#F{$dp.$D(\'rtimej\')}'})"  type="text" readonly= "true" value="${step1.startWorkTime}" />
		                     至
		                    <input name="step1.endWorkTime"  id="rtimej" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rtimec\')}'})" type="text" readonly= "true" value="${step1.endWorkTime}"  >
						</td>
					</tr>
					<tr>
						<td
							style="width: 120px; height: 30px; line-height: 30px; text-align: right;color: red">
							咨询范围
						</td>
						<td colspan="5" style="width: 20px; height: 30px; line-height: 30px; text-align: left; ">
							<input name="step1.consultRange" value="${step1.consultRange }" type="text" class="text_a" />
						</td>
					</tr>
					<tr>
						<td style="width: 120px; height: 30px; line-height: 30px; text-align: right; color: red">
							质量要求
						</td>
						<td colspan="5" style="height: 30px; line-height: 30px; text-align: left;" >
							<textarea  style="width:100%;height:80px;" name="step1.qualityRequirement" class="text_a">${step1.qualityRequirement}</textarea>
						</td>
					</tr>
					<tr>
						<td style="width: 120px; height: 30px; line-height: 30px; text-align: right;">
							其它要求
						</td>
						<td colspan="5" style="height: 30px; line-height: 30px; text-align: left;" >
							<textarea  style="width:100%;height:80px;" name="step1.otherRequirements" class="text_a">${step1.otherRequirements}</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="5"
							style="width: 20px; height: 30px; line-height: 30px; text-align: left;">
							<a>相关附件</a>（合同、中标通知书、业主指令等）
						</td>
						<td
							style="width: 20px; height: 30px; line-height: 30px; text-align: right;">
							<div class="add_link"
								style="display: block; text-align: right; float: right; border-bottom: 0px;">
								<a href="javascript:fileUpLoad()">+新增</a>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="7" style="padding: 0px 0px;">
							<table border="1" id="fileListId" cellspacing="1" cellpadding="1" class="list_table4" style="margin: 0 auto;border-collapse: collapse;border-width:0px; border-style:hidden;">
								<tr>
									<td
										style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 100px;">
										文件名
									</td>
									<td
										style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
										文件类型
									</td>
									<td
										style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
										上传人
									</td>
									<td
										style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
										上传时间
									</td>
									<td
										style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;">
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
									<td style="text-align: center; background-color: #fff;">
										<a href="javascript:delProjectInfo(${annex.id})">删除</a>
								</td>
								</tr>
								</#list>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="7" style="text-align: right;">
							<input type="button" onclick="location.href ='javascript:history.back();';"" value="取消" class="sub" />
							<input type="submit" value="保存" class="sub" />
							<input type="button" onclick="javascript:toNextStep1()" value="转交任务" class="sub" />
							<#if user.username != '${SUPERADMIN}' >
							<input type="button" onclick="javascript:toNextStep()" value="提交任务" class="sub" />
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
				'step1.senderAddress' : {required : true},
				'step1.consultRange' : {required : true},
				'step1.qualityRequirement' : {required : true},
				'step1.workRequirements' : {required : true},
				'officeTel' : {isPhone : true},
				'tel' : {isPhone : true}
			}
		})
	});

	function toNextStep() { //执行下一步
		if (confirm("当前步骤执行完毕,确定要执行下一步吗?")) {
			document.getElementById("contractForm").action = "/step1/toNextStep.htm";
			$("#contractForm").submit();
		}

	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step1/save.htm";
		document.getElementById("contractForm").submit();
	}
	
	
	function AddRow() {
		//添加一行
		var tab1 = document.getElementById("step1ContactId");
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
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<input type="text" name="name"  class="text_a"  />';
		newTd1.innerHTML = '<input type="text" name="officeTel"  class="text_a"  />';
		newTd2.innerHTML = '<input type="text" name="tel" placeholder="请加区号" class="text_a"  required />';
		newTd3.innerHTML = '<input type="text" name="fax"  class="text_a"  />';
		newTd4.innerHTML = '<input type="text" name="qqemail"  class="text_a"  />';
		newTd5.innerHTML = '<a onclick="javascript:deleteRow(' + i + ')">删除</a>';
	}
	
	function deleteRow(id) {

		$('#' + id).remove();
	}

	function addStep1Contact() {
		AddRow();
	}
	
	function fileUpLoad() {
	  	   var iWidth=650;                          //弹出窗口的宽度; 
           var iHeight=500;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open('/file/fileStep1.htm?project.id=${project.id}','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	
	function delProjectInfo(id) {
		if (!confirm("确定删除此信息")) {
			return ;
		}
		var url = "/step1/deleteannex.htm?aid="+id;
		$.ajax({
			type:"post",
			url:url,
			asyn:true,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					window.parent.location.reload();
				} else {
					alert('删除失败');
				}
			}
		})
	}
	
	function deletesz(id){
			$.ajax({
					type:"get",
					url:'/step1/deletesz.htm?id=' + id,
					asyn:true,		//false为同步提交
					dataType:'text',	//返回文本
					success:function(d){
						window.parent.location.reload();
					}
				})
}
</script>

