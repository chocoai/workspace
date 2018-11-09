<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if>
<a href="javascript:window.location.reload();">现场勘察</a>
</div>
<form action="/step5/save.htm" method="post" id="contractForm">
	<div id="content">
		<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">
			施工现场勘察记录
		</div>
<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<!-- <tr>
				<td colspan="4"
					style="font-weight: bold; font-size: 20px; text-align: center;">
					施工现场勘察记录
				</td>
			</tr> -->
			<tr>
				<td width="13%" class="tab_title">
					工程名称
				</td>
				<td width="29%">
					<input name="step5.id" value="${step5.id}" type="hidden" />
					<input name="project.id" value="${project.id}" type="hidden" />
					<input name="project.name" value="${project.name}"
						readonly="readonly" id="projectNameId" type="text" class="text_a" />
				</td>
				<td width="21%" class="tab_title">
					工程地点
				</td>
				<td width="37%">
					<input id="projectAddressId" type="text" class="text_a" name="step5.projectAddress" value="${step5.projectAddress}"/>
				</td>
			</tr>
			<tr>
				<td class="tab_title red">
					勘察时间
				</td>
				<td>
					<input name="step5Logo.ctime"  readonly="readonly"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-{%d}',isShowClear:false,readOnly:true})" type="text" class="text_a" />
				</td>
				<td class="tab_title">
					记录人
				</td>
				<td>
					<input readonly="readonly"  value="${user.name}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title red">
					勘察内容
				</td>
				<td colspan="3">
					<textarea  name="step5Logo.logoNext"  cols="45" rows="6" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;"></textarea>
				</td>
			</tr>
			  <tr>
		    <td class="tab_title">勘察历史记录</td>
		    <td colspan="3">
		    		<textarea   cols="45" rows="10" style="font-size:12px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;color:gray;" readonly="readonly"
		    		><#list step5LogoList as step5Logo>${step5Logo.ctime}：[${step5Logo.user.name}]&nbsp;&nbsp;${step5Logo.logoNext}
		    		</#list>		    		
		    		</textarea>
		    </td>
	   		 </tr>
			<tr>
				<td class="tab_title">
					勘察结果确认
				</td>
				<td colspan="3" style="padding: 0px 0px;">
					<table  cellspacing="1" cellpadding="1" class="input_table"  style="margin: 0px auto;border-width:0px; border-style:hidden;">
						<tr>
				            <td colspan="3"> 
					            <div class="add_link"  style=" display:block; text-align:right;float:right;border-bottom:0px;" >
                                 	<a href="javascript:addStep5Item(); ">+新增</a>
								</div>
							</td>
  						</tr>
						<tbody id="tbodyID">
								<tr style="background-color:#fff;">
									<td width="40%"  style=" text-align:center; font-weight:bold;">单位</td>
									<td width="40%"  style=" text-align:center; font-weight:bold;">法人代表</td>
									<td  style=" text-align:center; font-weight:bold;">操作</td>
								</tr>
							<#list step5ItemList as step5Item>
								<#if  step5Item.id!=null>
										<tr id="step5Item_${step5Item_index}">
											
											<td style="text-align:center;"><input name="step5ItemId"  type="hidden" class="text_a" value="${step5Item.id}"/><input name="company"  type="text" class="text_a" value="${step5Item.company}"/></td>
											<td style="text-align:center;"><input name="companyRen"  type="text" class="text_a" value="${step5Item.companyRen}"/></td>
											<td style="background-color:#fff; text-align:center;"><a href="javascript:deleteStep5Item('${step5Item.id}','${step5Item_index}')">删除</a></td>
										</tr>
									<#else>
										<tr id="step5Item_${step5Item_index}">
											<td style="text-align:center;"><input name="pany"  type="text" class="text_a" value="${step5Item.company}"/></td>
											<td style="text-align:center;"><input name="panyRen"  type="text" class="text_a" value="${step5Item.companyRen}"/></td>
											<td style="background-color:#fff; text-align:center;"><a href="javascript:deleteStep5Item('${step5Item.id}','${step5Item_index}')">删除</a></td>
										</tr>
									</#if>
							</#list>
						</tbody >
						
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="7" style="text-align: right;">
					<input type="button" onclick="location.href ='javascript:history.back();';" value="取消" class="sub" />
					<input type="submit" value="保存" class="sub" />
				<#if (step5.project.step)==5>
					<input type="button" onclick="javascript:toNextStep1();"  value="转交任务"  class="sub" />  
					<#if user.username != "${SUPERADMIN}" >
					<input type="button" onclick="javascript:toNextStep();" value="提交任务" class="sub" />
					</#if>
				<#else>
					  <input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
				</#if>
				</td>
			</tr>
		</table>
	</div>
</form>
<script>
	$().ready(function() {
		$("#contractForm").validate( {
			rules : {
				'step5Logo.ctime' : {
					required : true
				},
				'step5Logo.logoNext' : {
					required : true
				}
			}
		})
	});
	
	function toNextStep() { //执行下一步
		if (confirm("当前步骤执行完毕,确定要执行下一步吗?")) {
			document.getElementById("contractForm").action = "/step5/toNextStep.htm";
			$("#contractForm").submit();
		}
	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step5/save.htm";
		document.getElementById("contractForm").submit();
	}
	
	function toPrint(id){
		var url = "/step5/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
	
	function deleteStep5Item(id,index){
		if(!id){
			$("#step5Item_"+index).remove();
		}else{
			$.ajax({
						type:"get",
						url:'/step5/deleteItem.htm?stepItemId=' + id,
						asyn:true,		//false为同步提交
						dataType:'text',	//返回文本
						success:function(d){
								if(d=="1"){
									$("#step5Item_"+index).remove();
								}else{
									alert("删除失败");
								}
						}
				})
		}
	}
	function deleteItem(index){
		$("#"+index).remove();
	}
	
	var i=0;
	var flag = true;
	function addStep5Item(){
				//添加一行
				var tab1 = document.getElementById("tbodyID");
					if(flag ){
						i =  i+ tab1.rows.length;
						flag =false;
					}else{
						i =  i+ 1;
					}
				var newTr = tab1.insertRow();
				newTr.id = i;
				//添加列
				var newTd0 = newTr.insertCell();
				var newTd1 = newTr.insertCell();
				var newTd2 = newTr.insertCell();
				//设置列内容和属性
				newTd0.innerHTML = '<input name="pany"  type="text" class="text_a"/>';
				newTd1.innerHTML = '<input name="panyRen"  type="text" class="text_a"/>';
				newTd2.innerHTML = '<a onclick="javascript:deleteItem('+i+')">删除</a>';
	
	}
</script>