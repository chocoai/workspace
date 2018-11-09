<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a href="/information/list.htm">项目信息列表</a> >
<a href="javascript:window.location.reload();">项目信息添加</a>
</div>

<form action="/information/save.htm" method="post" id="contractForm"
	name="contractForm">
	<div id="content">
		<div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;">
			项目信息
		</div>
		<input name="proinfo.id" id="id" type="hidden" value="${proinfo.id}" />
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td class="tab_title red">
					项目名称
				</td>
				<td>
					<input class="text_a" type="text" name="proinfo.proname"
						id="proName" value="${proinfo.proname}" />
						<input class="text_a" type="hidden" name="proinfo.proNo"
						id="proNo" value="${proinfo.proNo}" />
				</td>
				<td class="tab_title red" id="bidmen">
					招标人
				</td>
				<td>
					<input type='hidden' name='bidMenId' id='bidMenId' value="${proinfo.bidmen.id}" />
					<input class="text_a" type="text" name="bidMenName" value="${proinfo.bidmen.cusName}" 
						readonly="readonly" id='bidMenName' onclick="getBidMen()" />
				</td>
				
			</tr>
			
			<tr>
				<td class="tab_title red">
					咨询类型
				</td>
				<td>
					<select name="proinfo.serviceType.id" id="serviceTypeId" onChange="changeEditorialType()" >
						<#list serviceTypeList as serviceType>
						<option value="${serviceType.id}"  <#if proinfo.serviceType != null && proinfo.serviceType.id == serviceType.id>selected='selected'</#if> >
							${serviceType.name}
						</option>
						</#list>
					</select>	
				</td>
				<td class="tab_title red">
					承接类型
				</td>
				<td>
					<select name="proinfo.receiveType" id="receiveType" onchange="bibType()">
						<option value=1 <#if proinfo.receiveType == 1 > selected="selected" </#if>>投标类型</option>
				 		<option value=0 <#if proinfo.receiveType == 0 > selected="selected" </#if>>直接委托类型</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td class="tab_title red" width="11%">
					咨询费估算(万)
				</td>
				<td>
					<input type="text" class="text_a" name="proinfo.zixungusuan" value="${proinfo.zixungusuan}" />
				</td>
				<td class="tab_title red" id="bidfile">
					招标文件(公告)
				</td>
				<td colspan="3" id="bidfiles">
					<input type="text" class="text_a" name="proinfo.bidfile" value="${proinfo.bidfile}" />
				</td>
				
				<td class="tab_title red" id="bulidNum" style="display: none">
					建设投资（万元）
				</td>
				<td colspan="3" id="bulidNums" style="display: none">
					<input type="text" class="text_a" name="proinfo.buildmoney" value="${proinfo.buildmoney}" />
				</td>
			</tr>
			
			<tr id="startDate">
				<td class="tab_title red">
					报名日期
				</td>
				<td>
					<#if proinfo.qsj!=null && proinfo.zsj!=null>
					<input id="qsj"  class="Wdate" type="text" name="proinfo.qsj" value="${proinfo.qsj}" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){},minDate:'%y-%M-{%d}'})" />
						至
					<input id="zsj"  class="Wdate" type="text" name="proinfo.zsj" value="${proinfo.zsj}" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){},minDate:'#F{$dp.$D(\'qsj\')}'})" />
					<#else>
					<input id="qsj"  class="Wdate" type="text" name="proinfo.qsj" value="${proinfo.qsj}" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){},minDate:'%y-%M-{%d}'})" />
						至
					<input id="zsj"  class="Wdate" type="text" name="proinfo.zsj" value="${proinfo.zsj}" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){},minDate:'#F{$dp.$D(\'qsj\')}'})" />
					</#if>
				</td>

				<td class="tab_title red">
					开标日期
				</td>
				<td>
					<#if proinfo.opentime!=null>
					<input id="opentime" style="width: 100%;" name="proinfo.opentime"
						class="Wdate" type="text" value="${proinfo.opentime}" readonly="readonly"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'zsj\')}'})" />
					<#else>
					<input id="opentime" style="width: 100%;" name="proinfo.opentime"
						class="Wdate" type="text" value="${proinfo.opentime}" readonly="readonly"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){},minDate:'#F{$dp.$D(\'zsj\')}',maxDate:'#F{$dp.$D(\'opentime\')}'})" />
					</#if>
				</td>
			</tr>
			
			<tr id="cityC">
				<td class="tab_title red">
					代理公司
				</td>
				<td>
					<input id="agentcompany" name="proinfo.agentcompany"  type="text" class="text_a" value="${proinfo.agentcompany}" />
				</td>

				<td class="tab_title red">
					报名城市
				</td>
				<td>
					<input id="signcity" name="proinfo.signcity"  type="text" class="text_a" value="${proinfo.signcity}" />
				</td>
			</tr>
			<tr id="projectPro" style="display: none">
				<td class="tab_title red">
					工程概况
				</td>
				<td colspan="6">
					<input id="projectProfile" name="proinfo.projectProfile"  type="text" class="text_a" value="${proinfo.projectProfile}"/>
				</td>
			</tr>
			<tr>
				<td class="tab_title2 red">
					下一环节处理人
				</td>
				<td colspan="3">
					<input type="hidden" name="proinfo.nextOperatorId" id="nextOperatorId" value="${proinfo.nextOperator}" />
					<input type="text" name="nextOperatorName" id="nextOperatorName" value="${nextOperatorName}"
						class="text_a" readonly="readonly" onclick="javascript:getNextOperator('nextOperatorId','nextOperatorName');"
					/>
				</td>
			</tr>

			<tr>
				<td colspan="6" style="text-align: right;">
					<input type="button" class="sub" value="取消" onclick="javascript: history.go(-1);" />
					<input type="submit" class="sub" value="保存" />
					<input type="button" class="sub" onclick="javascript:toNextStep();" value="提交"/> 
				</td>
			</tr>

		</table>
	</div>
</form>

<script>
	$().ready(function() {
	var receiveType = $('#receiveType').val();
		if (receiveType == 0) {
			$("#projectPro").css('display' ,''); 
			$("#startDate").css('display' ,'none');
			$("#cityC").css('display' ,'none');
			$("#bulidNum").css('display' ,'');
			$("#bulidNums").css('display' ,'');
			$("#bidfile").css('display' ,'none');
			$("#bidfiles").css('display' ,'none');
			$("#bidmen").html("委托人")
		} else {
			$("#projectPro").css('display' ,'none');
			$("#startDate").css('display' ,'');
			$("#cityC").css('display' ,'');
			$("#bulidNum").css('display' ,'none');
			$("#bulidNums").css('display' ,'none');
			$("#bidfile").css('display' ,'');
			$("#bidfiles").css('display' ,'');
			$("#bidmen").html("招标人")
		}
		$("#contractForm").validate( {
			rules : {
				'proinfo.proname' : {
					required : true
				},
				'proinfo.opentime' : {
					required : true
				},
				'proinfo.bidfile' : {
					required : true
				},
				'proinfo.bidway' : {
					required : true
				},
				'proinfo.zixungusuan' : {
					required : true,
				},
				'bidMenName' : {
					required : true
				},
				'proinfo.qsj' : {
					required : true
				},
				'proinfo.zsj' : {
					required : true
				},
				'collectionmenName' : {
					required : true
				},
				'nextOperatorName' : {
					required : true
				},
				'proinfo.projectProfile':{
					required : true
				},
				'proinfo.buildmoney' :{
					required : true,
				},
			}
		})
	});

	function getCustomer() {
		$.ajax( {
			type : "get",
			url : '/user/selectUser4.htm?type=1',
			asyn : true, //false为同步提交
			dataType : 'text', //返回文本
			success : function(d) {
			}
		});
		   var iWidth=700;                          //弹出窗口的宽度; 
           var iHeight=500;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		
		window.open('/user/selectUser1.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function returnParam1(value) {
		if (value == null || value == "==,,,==") {
			$("#collectionmenId").val("");
			$("#collectionmenName").val("");
			return;
		}
		var roleIdList = value.split("==,,,==")[0];
		$.ajax( {
					type : "get",
					url : '/information/saveUser.htm?id=${id}&roleIdList=' + roleIdList,
					asyn : true,
					dataType : 'text',
					success : function(d) {
						if (d != null && d != "") {
							var arr = d.split("*");
							$("#collectionmenId").val(arr[0]);
							$("#collectionmenName").val(arr[1]);
						} else {
							window.parent.location.reload();
						}
					}
		})
	}





	function getBidMen() {
	   var iWidth=700;                          //弹出窗口的宽度; 
       var iHeight=500;                         //弹出窗口的高度; 
       //获得窗口的垂直位置 
       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
       //获得窗口的水平位置 
       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
	   window.open('/project/selectCustomer.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function returnParamUser(id,name){
			$('#bidMenId').val(id);
			$('#bidMenName').val(name);
	}
	
	function bibType(){
		var receiveType = $('#receiveType').val();
		if (receiveType == 0) {
			$("#projectPro").css('display' ,''); 
			$("#startDate").css('display' ,'none');
			$("#cityC").css('display' ,'none');
			$("#bulidNum").css('display' ,'');
			$("#bulidNums").css('display' ,'');
			$("#bidfile").css('display' ,'none');
			$("#bidfiles").css('display' ,'none');
			$("#bidmen").html("委托人")
		} else {
			$("#projectPro").css('display' ,'none');
			$("#startDate").css('display' ,'');
			$("#cityC").css('display' ,'');
			$("#bulidNum").css('display' ,'none');
			$("#bulidNums").css('display' ,'none');
			$("#bidfile").css('display' ,'');
			$("#bidfiles").css('display' ,'');
			$("#bidmen").html("招标人")
		}
	}
	
	function changeEditorialType() {
		var serviceTypeId = $("#serviceTypeId").val();
		$
				.ajax({
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
	
	function toNextStep() { //执行下一步
		var receiveType = $("#receiveType").val();
		var nextStepName = "项目立项";
		$("#nextStepName").val(nextStepName);
		var nextOperator = $("#nextOperatorName").val();
		if (confirm("当前步骤执行完毕,下一环节处理人为【"
				+ nextOperator + "】,确定要执行下一步吗?")) {
			document.getElementById("contractForm").action = "/information/nextStep.htm";
			$("#contractForm").submit();
		}
	}
	
	function getNextOperator(userIdId, userNameId) {
		window.open('/user/selectMultiUser.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId, '_blank',
				'channelmode=yes,left=200,top=100,scrollbars=yes,scrollbars=yes');
	}
</script>

