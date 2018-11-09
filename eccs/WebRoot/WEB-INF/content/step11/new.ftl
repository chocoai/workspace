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
<a href="javascript:window.location.reload();">成果文件编制</a>
</div>
  <form action="/step11/save.htm" method="post" id="contractForm" enctype="multipart/form-data">  
    	<div id="content">
       	<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
       	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				成果文件编制
			</div>
      <table border="1" cellspacing="1" cellpadding="1" class="input_table">
  <!-- <tr>
    	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">成果文件编制</td>
  </tr> -->
  <tr>
	    <td class="tab_title">工程名称</td>
	    <td colspan="3" width="34%" style="background:#fff;">
		    <input name="project.id" value="${project.id}" type="hidden" />
		    <input name="step11.id" value="${step11.id}" type="hidden" />
		    ${project.name}
	    </td>
  </tr>
  <tr>
	    <td class="tab_title">委托单位</td>
	    <td width="34%" style="background:#fff;">${project.customer.cusName}</td>
	    <td width="16%" class="tab_title">编审类型</td>
	    <td width="35%" style="background:#fff;">${project.editorialType.name}</td>
   </tr>
  <tr>
	    <td class="tab_title">工程编号</td>
	    <td width="34%" style="background:#fff;">${project.no}</td>
	    <td width="16%" class="tab_title">服务类别</td>
	    <td width="35%"  style="background:#fff;">${project.serviceType.name}</td>
  </tr>
  <tr>
	    <td colspan="4" style="padding: 0px 0px;">
	    <table border="1" id="fileUploadTableId" cellspacing="1" cellpadding="1" class="list_table4" style="margin: 0px auto;border-width:0px; border-style:hidden;">
	              <tr>
	            
	            <td colspan="8" style=" width:20px; height:30px; line-height:30px; text-align:right;"> 
	            <div class="add_link"  style=" display:block; text-align:right;float:right;  border-bottom:0px;" >
	                                            <a href="javascript:addFileUpload();">+新增</a>
	     </div></td>
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
	var i=0;
	var flag = true;
	$().ready(function() {  
	  	      $("#contractForm").validate({
					rules : {  
					'nextWorkerNameId' : { required : true } 
					}
				})
	}); 
	
	function toNextStep(){  //执行下一步
		 if(confirm("当前步骤执行完毕,确定要执行下一步吗?"))
		 {
			 document.getElementById("contractForm").action="/step11/toNextStep.htm";
			  $("#contractForm").submit();
		 }
	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step11/save.htm";
		document.getElementById("contractForm").submit();
	}

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

	function deleteRow(id){
	    
		  $('#'+id+'00000').remove();
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
</script>

