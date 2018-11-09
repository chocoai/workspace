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
<a href="javascript:window.location.reload();">项目总结</a>
</div>
  <form action="/step14/save.htm" method="post" id="contractForm"  enctype="multipart/form-data">  
 		<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
 		<div id="content">
 			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				项目总结表
			</div>
  <table border="1" cellspacing="1" cellpadding="1" class="input_table">
	  	<!-- <tr>
	    		<td colspan="7" style=" font-weight:bold; font-size:20px; text-align:center;">项目总结表</td>
	    </tr> -->
	  	<tr>
			    <td rowspan="5" width="120" class="tab_title">项目概况</td>
			    <td class="tab_title">项目名称</td>
			    <td style="background-color:#fff;width:180px;">
			    <input name="step14.id" value="${step14.id}"  type="hidden" />
			    <input name="project.id" value="${project.id}" type="hidden" />
			    ${project.name}
			    </td>
			    <td class="tab_title" >项目类型</td>
			    <td style="background-color:#fff;width:120px;">
			    ${project.projectType.id}</td>
			    <td class="tab_title">项目编号</td>
			    <td style="background-color:#fff;width:100px;">${project.no}</td>
	   </tr>
	    <tr>
			    <td class="tab_title">咨询类型</td>
			    <td style="background-color:#fff;">${project.serviceType.name}</td>
			    <td class="tab_title">编审类型</td>
			    <td colspan="3" style="background-color:#fff;">${project.editorialType.name}</td>
        </tr>
        <tr>
			    <td class="tab_title">委托单位</td>
			    <td colspan="5" style="background-color:#fff;">${project.customer.cusName}</td>
   		 </tr>
  		<tr>
			    <td class="tab_title">规模类型</td>
			    <td colspan="5" style="background-color:#fff;">
			    ${project.scaleType} 
			    </td>
       </tr>
  		<tr>
			    <td class="tab_title">规模大小</td>
			    <td style="background-color:#fff;">
			     ${project.projectSize} 
			    </td>
			    <td class="tab_title">工期要求</td>
			    <td colspan="3" style="background-color:#fff;">
			    ${step1.startWorkTime}&nbsp;至&nbsp; ${step1.endWorkTime}
			    </td>
   	   </tr>
   <#-->
 	   <tr>
			    <td class="tab_title red">执行过程描述</td>
			    <td colspan="6"><textarea name="step14.process"  cols="45" rows="5" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step14.process }</textarea></td>
   		</tr>
  		<tr>
			    <td class="tab_title red">执行效果评价</td>
			    <td colspan="6"><textarea name="step14.evaluate"  cols="45" rows="5" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step14.evaluate }</textarea></td>
   		 </tr>
 		 <tr>
			    <td class="tab_title red">项目请款及收款情况</td>
			    <td colspan="6"><textarea name="step14.qmoney"  cols="45" rows="5" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step14.qmoney }</textarea></td>
  		</tr>
	<-->
  		<tr>
			    <td class="tab_title">存在问题及改进方法</td>
			    <td colspan="6"><textarea name="step14.question"  cols="45" rows="5" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step14.question }</textarea></td>
    	</tr>
    	<tr>
			    <td colspan="7 style="padding: 0px 0px;">
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
			    <td class="tab_title">总结时间</td>
			    <td colspan="2"><input  type="text"  name="step14.ctime"  value="${ctime}" readonly="readonly"   class="text_a" /></td>
			    <td class="tab_title">总结人</td>
			    <td colspan="3"><input   value="${user.name}" readonly="readonly"  type="text" class="text_a"/></td>
	    </tr>
	    <tr>
		         <td colspan="7" style=" text-align:right;">
			         <input type="button"  onclick="location.href ='javascript:history.back();';" value="取消" class="sub"/>
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
	$().ready(function() {  
	  	      $("#contractForm").validate({
					rules : {  
					'step14.process' : { required : true },
					'step14.evaluate' : { required : true }
					}
				})
	}); 
	
	function toNextStep(){  //执行下一步
	 if(confirm("当前步骤执行完毕,确定要执行下一步吗?"))
	 {
		 document.getElementById("contractForm").action="/step14/toNextStep.htm";
		  $("#contractForm").submit();
	 }
}
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step14/save.htm";
		document.getElementById("contractForm").submit();
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
