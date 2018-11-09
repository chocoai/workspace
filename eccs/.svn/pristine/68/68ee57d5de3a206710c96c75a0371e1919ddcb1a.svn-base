<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>

<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a href="/project/list.htm">项目信息列表</a> >
<a>项目立项</a>
</div>
  	
<form action="/project/save.htm" method="post" id="projectForm" name="projectForm">  
	<input type="hidden" id="projectID">
	<input type="hidden" id="projectName">
	<div id="content">
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">项目立项</div>
		<input type="hidden" name="stepTempleteId" value="1"/>
		<input type="hidden" name="projectInfo.id" id="projectInfoId" value="${projectInfo.id}">
		<input type="hidden" name="projectInfoId" id="projectInfoId" value="${project.projectInfo.id}">
		<input type="hidden" name="project.id" value="${project.id}"/>
		<input type="hidden" name="nextStepName" value="" id="nextStepName"/>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
		
			<tr>
            	<td class="lefttd" colspan="6" style=" text-align:center">项目基本信息</td>
            </tr>
            
        	<tr>
            	<td width = "12%" class="tab_title red"><p>项目编号</p></td>
            	<td colspan="3">
            		<input class="text_a" type="text" name="project.no" readonly="readonly" id="projectnoid" onblur="javascript:checkNO();" value="${project.no}"/>
            	</td>
                <td width = "1%" class="tab_title red"><p>立项日期</p></td>
                <td>
                	<input class="text_a" type="text" name="project.recordDate" value="${project.recordDate}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                </td>
            </tr>                              
                                       
            <tr>
            <#if i=='2'>
             <td class="tab_title red"><p>项目名称</p></td>
				<td colspan="5">
					<input name="" id="projectid" value="${project.id}" type="hidden"/>
					<input name="project.name" id="projectnameid" readonly="readonly" value="${project.name}"  
						onclick="getProject('projectid','projectnameid');" type="text" class="text_a" />
               </td>
            <#else>
                <td class="tab_title red"><p>选择项目</p></td>
				<td colspan="6">
					<input name="" id="projectid" value="${project.id}" type="hidden"/>
					<input name="project.name" id="projectnameid"  readonly="readonly" value="${project.name}"  
						 type="text" class="text_a" onclick="editproid('${project.id}')" />
               </td>
            </#if>
            </tr>
                                      
            <tr>
	            <td class="tab_title red"><p>委托单位</p></td>
	            <td>
			        <input name="customerId" id="customerId" value="${project.customer.id}" type="hidden"/>
					<input name="" id="customername" readonly="readonly" value="${project.customer.cusName}"  
							onclick="getCustomer();" type="text" class="text_a" />
	    		</td>
	    		
	    		<td class="tab_title red"><p>实施部门</p></td>
				<td>    
					<input name="receiveDeptId" id="receiveDeptId" value="${project.receiveDept.id}" type="hidden"/>
					<input name="receiveDeptName" id="receiveDeptName" readonly="readonly" value="${project.receiveDept.name}"  
						onclick="getDept();" type="text" class="text_a" />
				</td>
				
				<td class="tab_title red"><p>协作部门</p></td>
				<td>    
					<input name="handleDeptId"  id="handleDeptId" value="${deptIds}"   type="hidden"/>
					<input name="handleDeptName" id="handleDeptName" readonly= "readonly" value="${deptNames}"  
						onclick="getMultiDept();" type="text" class="text_a" />
				</td>
				</tr>
				<tr>
	            <td class="tab_title red"><p>立项人</p></td>
	            <td>${user.name}</td>
	    		
	    		<td class="tab_title red"><p>项目经理</p></td>
				<td>
					<input type="hidden" name="receiveManagerId" id="receiveManagerId" value="${project.receiveManager.id}" />
					<input type="text" name="receiveManagerName" id="receiveManagerName" value="${project.receiveManager.name}" readonly="readonly"
						onclick="getProjectManager('receiveManagerId','receiveManagerName');" class="text_a" />
				</td>

				<td class="tab_title red"><p>协作负责人</p></td>
				<td>
					<input type="hidden" name="handleManagerId" id="handleManagerId" value="${project.handleManagerId}" />
					<input type="text" name="handleManagerName" id="handleManagerName" readonly="readonly" value="${handleManagerNames}"
						<#-->onclick="getCooperator();"<-->  onclick="javascript:getNextOperator2('handleManagerId','handleManagerName');" class="text_a" />
				</td>

			</tr>                        
            <tr>
				<td class="tab_title red"><p>咨询类型</p></td>
				<td>
					<select name="serviceTypeId" id="serviceTypeId" onClick="changeEditorialType()" >
						<option value="">请选择</option>
						<#list serviceTypeList as serviceType>
						<option value="${serviceType.id}"  <#if project.serviceType != null && project.serviceType.id == serviceType.id>selected='selected'</#if> >
							${serviceType.name}
						</option>
						</#list>
					</select>	
				</td>
				<td width = "1%" class="tab_title red"><p>业务范围</p></td>
				<td>
					<select name="editorialTypeId" id="editorialTypeId">
						<#list editorialTypeList as editorialType>
						<option value="${editorialType.id}"  <#if project.editorialType != null && project.editorialType.id == editorialType.id>selected='selected'</#if> >
							${editorialType.name}
						</option>
						</#list>
					</select>	
				</td>                     
				<td width = "1%" class="tab_title red"><p>紧急与否</p></td>
				<td>
				<#list urgentTypeList as urgentType>
				<#if urgentType.id != 2>
					<input name="urgentTypeId" type="radio" value="${urgentType.id}" <#if project.urgentType != null && project.urgentType.id == urgentType.id>selected='selected'</#if> />${urgentType.name}&nbsp;&nbsp;&nbsp;
				</#if>
				</#list>
				<#-->
					<select name="urgentTypeId">
						<option value = "">请选择</option>
						<#list urgentTypeList as urgentType>
						<option value="${urgentType.id}" <#if project.urgentType != null && project.urgentType.id == urgentType.id>selected='selected'</#if> >
							${urgentType.name}
						</option>
						</#list>
					</select>
				<-->		
				</td>
			</tr>                           
                                       
            <tr>
				<td class="tab_title red"><p>承接类型</p></td>
				<td>
					<select name="project.receiveType" id="receiveTypeId" onchange="changeNextStep()">
						<option value="">请选择</option>
				 		<option value=1 <#if project.receiveType== 1>selected='selected'</#if>>投标</option>
				 		<option value=0 <#if project.receiveType== 0>selected='selected'</#if> >直接委托</option>
					</select>
				</td>
				<td class="tab_title red"><p>流程类型</p></td>
				<td colspan="3">
					<input type="radio" id="" name="project.processType" value="0" onclick="showProjManageOperator(this.value)"
						<#if project.processType== 0>checked="checked"</#if> checked="checked">先经营管理后项目实施&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="" name="project.processType" value="1" onclick="showProjManageOperator(this.value)" <#if project.processType== 1>checked="checked"</#if>>经营管理和项目实施可同时独立进行
					<!-- <select id="processType" name="processType" onchange="showProjManageOperator()">
						<option value="">请选择</option>
						<option value="0">先经营管理后项目实施</option>
						<option value="1">经营管理和项目实施可同时独立进行</option>
					</select> -->
					<!-- 
					<select name="project.doContact" id="doContactId">
				    	<option value=1 <#if project.doContact== 1>selected='selected'</#if> >签合同</option>
					</select>
					-->
				</td>
				<!-- <td class="tab_title"></td>
				<td></td> -->
				
			</tr>
			            
            <tr>
            	<td class="tab_title"><p>项目规模</p></td>
				<td colspan="5"><input type="text" class="text_a" name="project.scaleType" value="${project.scaleType}"/></td>
            </tr>

			<tr>
				<td class="tab_title">
					<p>工程概况</p>
				</td>
				<td colspan="5">
					<input type="text" class="text_a" name="project.projectProfile" value="${project.projectProfile}" />
				</td>
			</tr>

			<tr>
				<td class="tab_title red">
					<span id="nextStepNamea">下一环节处理人</span>
				</td>
				<td colspan="5">
					<input type="hidden" name="nextOperatorId" id="nextOperatorId" value="${project.nextOperator}" />
					<input type="text" name="nextOperatorName" id="nextOperatorName" value="${nextOperatorName}"
						class="text_a" readonly="readonly" onclick="javascript:getNextOperator('nextOperatorId','nextOperatorName');"
					/>
				</td>
			</tr>
			
			<tr id="projManageOperator" style="display: none;">
				<td class="tab_title red">
					<span id="nextStepNamea">项目管理人员分配处理人</span>
				</td>
				<td colspan="5">
					<input type="hidden" name="pmOperatorId" id="pmOperatorId" value="${project.pmOperator}" />
					<input type="text" name="pmOperatorName" id="pmOperatorName" value="${pmOperatorName}"
						 class="text_a" readonly="readonly" onclick="javascript:getNextOperator('pmOperatorId','pmOperatorName');"
					/>
				</td>
			</tr>

			<tr>
            	<td class="lefttd" colspan="6" style=" text-align:center">客户基本信息</td>
            </tr>
            
            <tr>
				<td colspan="6">
					<div class="add_link" style="display:block;text-align:right;border:0px solid #dadada;border-bottom:0px">
						<a href="javascript:addContact()">+新增</a>
					</div>
					
					<table class="list_table4" border="1" id="contactTableId" cellspacing="1" cellpadding="1"> 
						<tr>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:120px;">单位名称</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">单位性质</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">联系人</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">电话</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">手机</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">QQ/Email</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">是否跟踪</td>
							<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;">操作</td>
						</tr>
						<#list projectContactList as projectContact>
						<tr id="${projectContact.id }">
							<td style=" text-align:center;background-color:#fff;"><span title="${projectContact.deptName }">${projectContact.deptName }</span></td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.deptType }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.userName }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.officeTel }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.imNo }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.tel }</td>
							<td style=" text-align:center;background-color:#fff;">${projectContact.isTrack }</td>
							<td style=" text-align:center;"><a onclick="javascript:delProjectContact('${projectContact.id }')">删除</a></td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>
			
			<tr>
				<td style=" text-align:right;">备注</td>
				<td colspan="3" style="width:20px; height:30px;line-height:30px;text-align:left;">
					<input type="text" name="project.remark" value="${project.remark}" style="width:98%; height:50px; margin:5px;"/>
				</td>
				<td style=" text-align:right;">
					业主负责人
				</td>
				<td >
					<input type="text" class="text_a" name="project.recordName"  value="${project.recordName}"/>
				</td>
			</tr>

			<tr>
				<td colspan="6" style=" text-align:right;">
					<input type="button" class="sub" onclick="javascript:history.go(-1);" value="取消"/>
					<input type="button" class="sub" onclick="javascript:toNextStep();" value="提交"/> 
					<input type="button" class="sub" onclick="javascript:submitForm();" value="保存"/>                                            
				</td>
			</tr>
		</table>
	</div>
</form>

<script>
	var i=0;
	var flag = true;
	
	var processType = '${project.processType}'
	showProjManageOperator(processType);

	$().ready(function() {
		var serviceTypeId = $("#serviceTypeId").val();
				$.ajax({
					type : "post",
					url : '/project/select.htm',
					data : {
						'ids' : serviceTypeId
					},
					asyn : true,
					dataType : 'json',
					cache : false,
					success : function(d) {
						if (d != null && d != "") {
							var Ids = d.id;
							var names = d.name;
							var cobj = document.getElementById("editorialTypeId");
							cobj.innerHTML = '';
							for (var i = 0; i < Ids.length; i++) {
								document.projectForm.editorialTypeId.options[i] = new Option(
										names[i], Ids[i]);
							}
						}
					}
				});
		$("#projectForm").validate({
			rules : {
				'project.no' : {
					required : true
				},
				'project.name' : {
					required : true
				},
				'project.recordDate' : {
					required : true
				},
				'project.receiveType' : {
					required : true
				},
				'handleDeptName' : {
					required : true
				},
				'customerId' : {
					required : true
				},
				'receiveDeptName' : {
					required : true
				},
				'serviceTypeId' : {
					required : true
				},
				'receiveManagerName' : {
					required : true
				},
				'handleManagerName' : {
					required : true
				},
				'urgentTypeId' : {
					required : true
				},
				'editorialTypeId' : {
					required : true
				},
				'nextOperatorName' : {
					required : true
				},
				'officeTel' : {
					isTel : true
				},
				'tel' : {
					isMobile : true
				},
				'processType' : {
					required : true
				},
			}
		})
	});

	function submitForm() {
		document.getElementById("projectForm").action = "/project/save.htm";
		$("#projectForm").submit();
	}

	function changeNextStep() {
		var receiveType = $('#receiveTypeId').val();
		if (receiveType == 0) {
			$('#nextStepNamea').html("合同评审处理人");
		} else {
			$('#nextStepNamea').html("投标主管");
		}
	}

	function showProjManageOperator(processType) {
		if (processType == 1) {
			$('#projManageOperator').show();
		} else {
			$('#projManageOperator').hide();
		}
	}

	function toNextStep() { //执行下一步
		var receiveType = $("#receiveTypeId").val();
		var nextStepName = "";
		if (receiveType == 0) {
			nextStepName = "合同评审";
		} else {
			nextStepName = "投标管理-人员分配";
		}
		$("#nextStepName").val(nextStepName);
		var nextOperator = $("#nextOperatorName").val();
		if (confirm("当前步骤执行完毕,下一环节为【" + nextStepName + "】,下一环节处理人为【"
				+ nextOperator + "】,确定要执行下一步吗?")) {
			document.getElementById("projectForm").action = "/project/toNextStep.htm";
			$("#projectForm").submit();
		}
	}

	function receiveChange(receive) {
		var value = receive.value;
		if (value == 1) { //招投标必须签合同
			$("#doContactId").attr("disabled", false);
			$("#doContactId").val(1);
			$("#doContactId").attr("disabled", true);
		} else {
			$("#doContactId").attr("disabled", false);
			$("#doContactId").val(1);
			$("#doContactId").attr("disabled", true);
		}
	}

	function AddRow() {
		//添加一行
		var tab1 = document.getElementById("contactTableId");
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
		var newTd6 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<input type="text" name="deptName"  class="text_a"  />';
		newTd1.innerHTML = '<select name="deptType" class="text_a"  ><option value="">请选择</option><option value="企业客户">企业客户</option><option value="政府客户">政府客户</option><option value="其他">其他</option></select>';
		newTd2.innerHTML = '<input type="text" name="userName"  class="text_a"  />';
		newTd3.innerHTML = '<input type="text" name="officeTel"  class="text_a"  />';
		newTd4.innerHTML = '<input type="text" name="tel"  class="text_a"  />';
		newTd5.innerHTML = '<input type="text" name="isTrack"  class="text_a"  />';
		newTd6.innerHTML = '<a onclick="javascript:deleteRow(' + i
				+ ')">删除</a>';
	}

	function delProjectContact(projectContactId) {
		if (confirm("确定删除此信息吗？")) {
			var url = "/project/delProjectContact.htm?projectContactId="
					+ projectContactId;
			$.ajax({
				type : "post",
				url : url,
				asyn : true,
				dataType : 'text',
				success : function(d) {
					if (d == 'true') {
						alert('删除成功');
						$('#' + projectContactId).remove();
					} else {
						alert('删除失败 ');
					}
				}
			});
		} else {
			return;
		}
	}

	function deleteRow(id) {
		$('#' + id).remove();
	}

	function addContact() {
		AddRow();
	}

	function changeEditorialType() {
		var serviceTypeId = $("#serviceTypeId").val();
		
				$.ajax({
					type : "post",
					url : '/project/select.htm',
					data : {
						'ids' : serviceTypeId
					},
					asyn : true,
					dataType : 'json',
					cache : false,
					success : function(d) {
						if (d != null && d != "") {
							var Ids = d.id;
							var names = d.name;
							var cobj = document.getElementById("editorialTypeId");
							cobj.innerHTML = '';
							for (var i = 0; i < Ids.length; i++) {
								document.projectForm.editorialTypeId.options[i] = new Option(
										names[i], Ids[i]);
							}
						}
					}
				});
	}

	function getDept() {
		window.open('/dept/selectDept.htm', '_blank',
				'channelmode=yes,width=400,height=500,left=200,top=100');
	}

	function getMultiDept() {
		window.open('/dept/selectMultiDept.htm', '_blank',
				'channelmode=yes,width=400,height=500,left=200,top=100');
	}

	function doAfterGetDept(obj) {
		$('#receiveDeptId').val(obj.deptId);
		$('#receiveDeptName').val(obj.deptName);
		$("#receiveManagerName").val("");
	}

	function doAfterGetMultiDept(obj) {
		$('#handleDeptId').val(obj.deptId);
		$('#handleDeptName').val(obj.deptName);
		$("#handleManagerName").val("");
	}

	function getProject(projectid, projectname) {
		$("projectID").val(projectid);
		$("projectName").val(projectname);
		var iWidth = 600; //弹出窗口的宽度; 
		var iHeight = 500; //弹出窗口的高度; 
		//获得窗口的垂直位置 
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
		//获得窗口的水平位置 
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		window.open('/project/selectProject.htm',
						'',
						'height='
								+ iHeight
								+ ',innerHeight='
								+ iHeight
								+ ',width='
								+ iWidth
								+ ',innerWidth='
								+ iWidth
								+ ',top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=no, resizable=no,titlebar=no');
	}
	function returnProjectUser(id, name, bidMenId, bidMenName,serviceTypeId,receiveType,proNo) {
		$('#projectInfoId').val(id);
		$('#projectid').val(id);
		$('#projectnameid').val(name);
		
		$('#customerId').val(bidMenId);
		$('#customername').val(bidMenName);
		$('#serviceTypeId').val(serviceTypeId);
		$('#receiveTypeId').val(receiveType);
		if (receiveType == 0) {
			$('#nextStepNamea').html("合同评审处理人");
		} else {
			$('#nextStepNamea').html("投标主管");
		}
		$('#projectnoid').val(proNo);
		$.ajax({
					type : "post",
					url : '/project/select.htm',
					data : {
						'ids' : serviceTypeId
					},
					asyn : true,
					dataType : 'json',
					cache : false,
					success : function(d) {
						if (d != null && d != "") {
							var Ids = d.id;
							var names = d.name;
							var cobj = document.getElementById("editorialTypeId");
							cobj.innerHTML = '';
							for (var i = 0; i < Ids.length; i++) {
								document.projectForm.editorialTypeId.options[i] = new Option(
										names[i], Ids[i]);
							}
						}
					}
				});
	}

	function getCustomer(customerId, customername) {
		var iWidth = 700; //弹出窗口的宽度; 
		var iHeight = 500; //弹出窗口的高度; 
		//获得窗口的垂直位置 
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
		//获得窗口的水平位置 
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		window.open('/project/selectCustomer.htm',
						'',
						'height='
								+ iHeight
								+ ',innerHeight='
								+ iHeight
								+ ',width='
								+ iWidth
								+ ',innerWidth='
								+ iWidth
								+ ',top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function returnParamUser(id, name) {
		$("#customerId").val(id);
		$("#customername").val(name);
	}

	function checkNO(projectNO) {
		var pno = $('#projectnoid').val();
		var url = "/project/checkNO.htm";
		$.ajax({
			type : "post",
			url : url,
			data : {
				'project.no' : pno
			},
			asyn : true,
			dataType : 'text',
			success : function(d) {
				if (d == '1') {
				} else if (d == '0') {
					alert('项目编号:' + pno + '已经重复,请重新为工程编号!');
					$('#projectnoid').val('')
				}
			}
		});
	}

	function editproid(projectid) {
			document.getElementById('projectnameid').onclick = function() {
				getProject('projectid', 'projectnameid');
			document.getElementById('projectnameid').readOnly = true;
		}
	}

	function getProjectManager(userIdId, userNameId) {
		var deptId = $('#receiveDeptId').val();
		window.open('/user/getUserByDeptId.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId + '&deptId=' + deptId, '_blank',
				'channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}

	function getCooperator() {
		var deptIds = $('#handleDeptId').val();
		deptIds = encodeURIComponent(deptIds);
		window.open('/user/getUserByDeptIds.htm?deptId=' + deptIds, '_blank',
				'channelmode=yes,width=800px,height=500px,left=200,top=100');
	}

	function doAfterGetCooperator(obj) {
		$('#handleManagerId').val(obj.userId);
		$('#handleManagerName').val(obj.userName);
	}

	function getNextOperator(userIdId, userNameId) {
		window.open('/user/selectMultiUser.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId, '_blank',
				'channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}

	function getNextOperator2(userIdId, userNameId) {
		window.open('/user/selectMultiUser.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId, '_blank',
				'channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}

	function getNextOperatorValue(ids, names) {
		$('#nextOperatorId').val(ids);
		$('#nextOperatorName').val(names);
	}
</script>
