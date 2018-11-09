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
<a href="javascript:window.location.reload();">回访记录</a>
</div>
  <form action="/step13/save.htm" method="post" id="contractForm" enctype="multipart/form-data">  
    <input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
    <div id="content">
    		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				造价咨询项目征询意见回访记录表
			</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
              <!-- <tr>
                	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目征询意见回访记录表</td>
              </tr> -->
              <tr>
	                <td width="12%" class="tab_title">项目名称</td>
	                <td width="38%"><input name="step13.id" value="${step13.id}"  type="hidden" />
		                <input name="project.id" value="${project.id}" type="hidden" />
		    			<input name="project.name" value="${project.name}" readonly="readonly"  id="projectNameId"  type="text" class="text_a" /></td>
	                <td width="12%" class="tab_title">项目编号</td>
	                <td width="38%"><input readonly="readonly" value="${project.no }"  id="projectNoId"  type="text" class="text_a" /></td>
              </tr>
              <tr>
	                <td class="tab_title red">回访对象</td>
	                <td><input name="step13.visitObject" value="${step13.visitObject}" type="text" class="text_a"/></td>
	                <td class="tab_title red">服务内容</td>
	                <td><input name="step13.serviceContent" value="${step13.serviceContent}" type="text" class="text_a"/></td>
              </tr>
              <tr>
    				<td colspan="4"  >
	              		<div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
	                             <a href="javascript:addStep13Item(); " >+新增</a>             
	     			</div>
	    <table border="1" id="step13ItemId" cellspacing="1" cellpadding="1" class="list_table3" style=" margin-top:10px;">
			  <tr>
				    <td colspan="2"  style=" text-align:center; font-weight:bold; background-color:#d3e0f1;" width="160">征询意见主要内容</td>
				    <td colspan="2" style=" text-align:center; font-weight:bold; background-color:#d3e0f1;" width="160">满意度</td>
				    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:42px;">操作</td>
			  </tr>
		    <#list step13ItemList as step13Item>
			  <tr>
				    <td colspan="2" style=" text-align:center;background-color:#fff;" width="160">${step13Item.content}</td>
				    <td colspan="2" style=" text-align:center;background-color:#fff;" width="160"><input name="radio${ step13Item_index}" disabled="disabled" type="radio"  <#if step13Item.result == 1>checked="checked"</#if>   />&nbsp;很满意
		                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}"  type="radio" disabled="disabled"  <#if step13Item.result == 2>checked="checked"</#if> />&nbsp;满意      
		                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}" type="radio" disabled="disabled"  <#if step13Item.result == 3>checked="checked"</#if> />&nbsp;一般
		                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}"  type="radio" disabled="disabled"  <#if step13Item.result == 4>checked="checked"</#if> />&nbsp;不满意      
		                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}" type="radio" disabled="disabled"  <#if step13Item.result == 5>checked="checked"</#if> />&nbsp;很不满意</td>
				     <td style=" text-align:center;"><a href="javascript:deletesz(${step13Item.id})">删除</a></td>
		  </tr>
	  </#list>
</table>
    </td>
  </tr>
              <tr>
	                <td class="tab_title">征询单位评价意见</td>
	                <td colspan="3"><label for="textarea"></label>
		                <textarea name="step13.summaryContent"  id="summaryContent" cols="45" rows="5" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step13.summaryContent}</textarea>
		             	<span style=" float:left; width:60%;">项目负责人（签字）
		            		 <input name="step13.masterName" value="${step13.masterName}" id="masterName" type="text" style="margin:0 auto;line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
		            	<span style=" float:left;width:40%;">日期
		            		<input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="step13.masterSignDate" value="${step13.masterSignDate}" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/>
		            	</span>
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
              <tr>
	                <td class="tab_title">完善措施</td>
	                <td colspan="3"><label for="textarea"></label>
		                <textarea name="step13.perfectContent" cols="45" rows="5" id="perfectContent" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step13.perfectContent}</textarea>
		                <span style=" float:left; width:60%;">项目负责人（签字）
			            	 <input name="step13.techName" value="${step13.techName}" id="techName" type="text" style="margin:0 auto;line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			            <span style=" float:left;width:40%;">日期<input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="step13.techSingDate" value="${step13.techSingDate}" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span></td>
              </tr>
              <tr>
                    <td colspan="6" style=" text-align:right;">
                    <input type="button"  value="取消" onclick="location.href ='javascript:history.back();';" class="sub"/>
                    <input type="submit"  value="保存"  class="sub" />  
                    <input type="button" onclick="javascript:toNextStep1();"  value="转交任务"  class="sub" />  
                    <#if user.username != "${SUPERADMIN}" >    
                    <input type="button" onclick="javascript:toNextStep();"  value="提交任务"  class="sub" />                                               
                    </#if>
                    </td>
             </tr>
          </table>
    </div>
</form>
<script>
	var i=0;
	var flag = true;
	$().ready(function() {  
	  	      $("#contractForm").validate({
					rules : {  
					'step13.visitObject' : { required : true },
					'step13.serviceContent' : { required : true },
					'nextWorkerNameId' : { required : true } 
					}
				})
	});
	
	function toNextStep(){  //执行下一步
	var summaryContent=$("#summaryContent").val();
	var masterName=$("#masterName").val();
	var Content=summaryContent.replace(/(^\s*)/g, "");
	var Name=masterName.replace(/(^\s*)/g, "");
	if(Content.length!=0&&Name.length==0){
		alert("请输入征询单位评价意见的项目负责人签名");
		return false;
	}
	var perfectContent=$("#perfectContent").val();
	var techName=$("#techName").val();
	var Content1=perfectContent.replace(/(^\s*)/g, "");
	var Name1=techName.replace(/(^\s*)/g, "");
	if(Content1.length!=0&&Name1.length==0){
		alert("请输入完善措施的项目负责人签名");
		return false;
	}
		 if(confirm("当前步骤执行完毕,确定要执行下一步吗?"))
		 {
			 document.getElementById("contractForm").action="/step13/toNextStep.htm";
			  $("#contractForm").submit();
		 }
	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step13/save.htm";
		document.getElementById("contractForm").submit();
	}
	function AddRow1()
	{
		//添加一行
		//var tab1 = $("#contractReviewItemId");
	var tab1 = document.getElementById("step13ItemId");
	var len  = tab1.rows.length;
		if(flag ){
			i =  i+ tab1.rows.length;
			flag =false;
		}else{
			i =  i+ 1;
		}
	var Nam="'div1'";
	var Cod="fuJ"+i;
	var newTr = tab1.insertRow();
		newTr.id = i;
		//添加列
	var newTd0 = newTr.insertCell();
	newTd0.colSpan =2;
	var newTd1 = newTr.insertCell();
		newTd1.colSpan =2;
		newTd1.style="text-align:center;";
	var newTd2 = newTr.insertCell();
		newTd2.style="text-align:center;";
		//设置列内容和属性
		newTd0.innerHTML = '<input type="text" name="content"  width="160" class="text_a"  /><input type="hidden" name="values" value='+i+'  class="text_a"  />'; 
		newTd1.innerHTML = '<input name=result_'+i+' type="radio"  value="1"  />&nbsp;很满意' +
            '&nbsp;&nbsp;&nbsp;<input name=result_'+i+'  type="radio"  value="2" />&nbsp;满意'+      
            '&nbsp;&nbsp;&nbsp;<input type="radio" name=result_'+i+'  value="3" />&nbsp;一般'+
            '&nbsp;&nbsp;&nbsp;<input name=result_'+i+'  type="radio"  value="4" />&nbsp;不满意 '+     
            '&nbsp;&nbsp;&nbsp;<input type="radio" name=result_'+i+'   value="5" />&nbsp;很不满意';
		newTd2.innerHTML = '<a onclick="javascript:deleteRow('+i+')">删除</a>';
}

	function deleteRow(i){
		var tab1 = document.getElementById("step13ItemId");
		  $('#'+i).remove();
		//tab1.deleteRow(i);
	}
	
	function addStep13Item(){
		AddRow1();
	}

</script>
<script>
	function deletesz(id){
		$.ajax({
					type:"get",
					url:'/step13/deletesz.htm?id=' + id,
					asyn:true,		//false为同步提交
					dataType:'text',	//返回文本
					success:function(d){
							window.parent.location.reload();
					}
				})
}
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
				url:'/step11/deletesz.htm?id=' + id,
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
<#-->上传<--><#--><-->

</script>
