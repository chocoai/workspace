<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<a href="javascript:window.location.reload();">登记资料交接</a>
</div>
<form action="/step2/save.htm" method="post" id="contractForm" enctype="multipart/form-data">
	<div id="content">
		<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			登记资料交接
		</div>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<!-- <tr>
			<td colspan="4"
				style="font-weight: bold; font-size: 20px; text-align: center;">
				建设工程造价咨询相关资料交接单
			</td>
			</tr> -->
			<tr>
				<td width="15%" class="tab_title">
					工程名称
				</td>
				<td colspan="3">
					<input name="step2.id" value="${step2.id}" type="hidden" />
					<input name="project.id" value="${project.id}" type="hidden" />
					<input name="project.name" value="${project.name}" readonly="readonly" id="projectNameId" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					委托单位
				</td>
				<td width="34%">
					<input value="${project.customer.cusName}" type="text" readonly="readonly" id="senderDeptNameId" class="text_a" />
				</td>
				<td width="16%" class="tab_title">
					咨询类别
				</td>
				<td width="35%"> 
					<input value="${project.serviceType.name}" type="text"  readonly="readonly"  class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					<span class="red" style="font-weight: bold;">委托方于</span>
				</td>
				<td width="31%">
					<input name="step2.senderDataDate" value="${step2.senderDataDate }" id="startDate"  readonly="readonly" onFocus="var endDate=$dp.$('endDate');WdatePicker({onpicked:function(){endDate.focus();},maxDate:'#F{$dp.$D(\'endDate\')}'})" type="text" class="text_a" />
				</td>
				<td width="19%" class="tab_title" style="min-width: 150px;">
					<span class="red"  style="font-weight: bold;">日将下列资料交给受托方</span>
				</td>
				<td width="35%">
					<input name="step2.receiverUnit" value="${step2.receiverUnit }" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
			    <td colspan="4" style="padding: 0px 0px;">
			    <table border="1" id="fileUploadTableId" cellspacing="1" cellpadding="1" class="list_table4" style="margin: 0px auto;border-width:0px; border-style:hidden;">
		             <tr>
			            <td colspan="8" style=" width:20px; height:30px; line-height:30px; text-align:right;"> 
			            <div class="add_link"  style=" display:block; text-align:right;float:right;  border-bottom:0px;" >
			                                            <a href="javascript:addFileUpload();">+新增</a>
					    </div>
					    </td>
		  			</tr>
			  	<tr>
				    <td width="15%" style=" text-align:center; font-weight:bold;">资料类型</td>
				    <td width="15%" style=" text-align:center; font-weight:bold;">文件</td>
				    <td width="15%" style=" text-align:center; font-weight:bold;">资料名称</td>
				    <td width="10%" style=" text-align:center; font-weight:bold;">文件字号</td>
				    <td width="10%" style=" text-align:center; font-weight:bold;">文件作者</td>
				    <td width="15%" style=" text-align:center; font-weight:bold;">页号</td>
				    <td width="15%" style=" text-align:center; font-weight:bold;">备注</td>
				    <td width="5%" style=" text-align:center; font-weight:bold;width:42px;">操作</td>
			   </tr>
			   <#list annexList as annex>  
			  	<tr <#if !annex.id>id="null_${annex_index}"</#if> >
				    <td  style="text-align:center;" >
					    <input type="hidden" name="annexId"  value="${annex.id }"  class="text_a"  />
					    <input type="hidden" name="annexTypeName"  value="${annex.annexType.name }"  class="text_a"  />
					    <input name="annexTypeId"  value="${annex.annexType.id }" type="hidden" class="text_a"/>${annex.annexType.name }</td>
				    <td ><input name="file"   type="file"  onchange="return FileUpload_onselect(this,'annexTypeId_${annex_index }')" /></td>
				    <td ><input name="name"  value="${annex.name }" placeholder="非必填" type="text" class="text_a"/></td>
				    <td><input name="fileNum"  value="${annex.fileNum}" type="text" class="text_a"/></td>
				    <td><input name="fileOwner"  value="${annex.fileOwner }" type="text" class="text_a"/></td>
				    <td><input name="filePage"  value="${annex.filePage }" type="text" class="text_a"/></td>
				    <td><input name="description"  value="${annex.description}" type="text" class="text_a"/></td>
				    <td><input type="hidden"  id="annexTypeId_${annex_index }"  name="isuploads" value="0" />
			     	<#if !annex.id>
			     		<a href="javascript:deletesz('null_','${annex_index}')">删除</a>
			     	<#else>
			     		<a href="javascript:deletesz('','${annex.id}')">删除</a>
			     	</#if>
			    </td>
		    </tr>
		   </#list>
		</table></td>
		</tr>
		<#-->
			<tr>
				<td colspan="4" style="padding: 0px 0px;">
					<table border="1" id="step2ItemId" cellspacing="1" cellpadding="1" class="list_table4" style="margin: 0 auto;border-collapse: collapse;border-width:0px; border-style:hidden;">
				         <tr>
				            <td colspan="3" style=" width:20px; height:30px; line-height:30px; text-align:right;"> 
					            <div class="add_link"  style=" display:block; text-align:right;float:right;border-bottom:0px;" >
                                 	<a href="javascript:addStep2Item(); ">+新增</a>
								</div>
							</td>
  						</tr>
						<tr>
							<td style="text-align: center; font-weight: bold;" width="70%;">
								资料名称
							</td>
							<td style="text-align: center; font-weight: bold;"width="15%;">
								份数
							</td>
							<td style="text-align: center; font-weight: bold;" width="15%;">
								操作
							</td>
						</tr>
						<#list step2Filelist as num >
						<tr>
							<td style="background-color: #fff; text-align: center;">
								${num.name }
							</td>
							<td style="background-color: #fff; text-align: center;">
								${num.count}
							</td>
							<td style="background-color: #fff;  text-align: center;">
								<a href="javascript:deleteFile(${num.id})">删除</a>
							</td>
						</tr>
						</#list>
						
					</table>
				</td>
			</tr>
		<-->
			<tr>
				<td class="tab_title" width="15%">
					<span class="red" style="font-weight: bold;">受托方于</span>
				</td>
				<td width="34%">
					<input name="step2.receiverEndDate" value="${step2.receiverEndDate }" readonly="readonly" id="endDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" type="text" class="text_c" />
				</td>
				<td  class="tab_title" colspan="2" style="text-align:left;padding: 0px;">
					<span style="font-weight: bold;">日结束项目审核，将上列资料一并交还给委托方。</span>
				</td>
			</tr>
			<tr>
				<td class="tab_title">
					<span class="red" style="font-weight: bold;">委托方经办人</span>
				</td>
				<td >
					<input name="step2.senderUserName" value="${step2.senderUserName}" type="text" class="text_c" />
				</td>
				<td  class="tab_title"  style="text-align:right;" width="16%" >
					<span class="red" style="font-weight: bold;">受委托方经办人</span>
				</td>
				<td  class="tab_title"  style="text-align:left;" width="35%">
					<input name="step2.receiverUserName" value="${step2.receiverUserName}" type="text" class="text_c" />
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
			'step2.senderDataDate' : {
				required : true
			},
			'step2.receiverUnit' : {
				required : true
			},
			'step2.receiverEndDate' : {
				required : true
			},
			'step2.receiverUnit' : {
				required : true
			},
			'step2.senderUserName' : {
				required : true
			},
			'step2.receiverUserName' : {
				required : true
			}
		}
	})
});

<#-->
function AddRow() {
	//添加一行
	var tab1 = document.getElementById("step2ItemId");
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
	//设置列内容和属性
	newTd0.innerHTML = '<input name="name"  type="text" class="text_a"/>';
newTd1.innerHTML = '<input name="count"  type="text" class="text_a"/>';
newTd2.innerHTML = '<a onclick="javascript:deleteRow(' + i + ')">删除</a>';
}
<-->

<#-->上传<--><#--><-->
function FileUpload_onselect(thisfile,flagid)
	{
	    var path=thisfile.value;      
		var flag = document.getElementById(flagid);
		if(path){
			flag.value="1";
		}else{
			flag.value="0";
		}
	}

function AddRow()
{
		//添加一行
	var tab1 = document.getElementById("fileUploadTableId");
		if(flag ){
			i =  i+ tab1.rows.length;
			flag =false;
		}else{
			i =  i+ 1;
		}
		var newTr = tab1.insertRow();
		newTr.id = i+'00000';
		//添加列
	var newTd0 = newTr.insertCell();
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
	var newTd3 = newTr.insertCell();
	var newTd4 = newTr.insertCell();
	var newTd5 = newTr.insertCell();
	var newTd6 = newTr.insertCell();
	var newTd7 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<input type="hidden" name="annexId"  class="text_a"  /><input type="hidden" name="annexTypeId"  class="text_a"  /><input type="text" name="annexTypeName"  class="text_a"  />'; 
		newTd1.innerHTML = '<input type="file" name="file" onchange="return FileUpload_onselect(this, '+i+')"  class="text_a"  />';
		newTd2.innerHTML = '<input type="text" name="name"  placeholder="非必填" class="text_a"  />';
		newTd3.innerHTML = '<input type="text" name="fileNum"  class="text_a"  />';
		newTd4.innerHTML = '<input type="text" name="fileOwner"  class="text_a"  />';
		newTd5.innerHTML = '<input type="text" name="filePage"  class="text_a"  />';
		newTd6.innerHTML = '<input type="text" name="description"  class="text_a"  />';
		newTd7.innerHTML = '<input type="hidden"  id='+i+'  name="isuploads" value="0" /><a onclick="javascript:deleteRow('+i+')">删除</a>';
}

	
function addFileUpload(){
	AddRow();
}

function deletesz(str,id){
		if(str){
			$("#"+str+id).remove();
			return;
		}
		$.ajax({
				type:"get",
				url:'/step2/deletesz.htm?id=' + id,
				asyn:true,		//false为同步提交
				dataType:'text',	//返回文本
				success:function(d){
					if(d=='1'){
						window.parent.location.reload();
					}else{
						alert("删除失败");
					}
				}
			})
}

function deleteRow(id){
    
	  $('#'+id+'00000').remove();
}
<#-->上传<--><#--><-->

function addStep2Item() {
	AddRow();
}

function toNextStep() { //执行下一步
	if (confirm("当前步骤执行完毕,确定要执行下一步吗?")) {
		document.getElementById("contractForm").action = "/step2/toNextStep.htm";
		$("#contractForm").submit();
	}
}
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step2/save.htm";
		document.getElementById("contractForm").submit();
	}

function deleteFile(id){
$.ajax({
		type:"get",
		url:'/step2/deleteFile.htm?fileId='+id,
		asyn:true,		//false为同步提交
		dataType:'text',	//返回文本
		success:function(d){
				window.parent.location.reload();
		}
	})
}
</script>