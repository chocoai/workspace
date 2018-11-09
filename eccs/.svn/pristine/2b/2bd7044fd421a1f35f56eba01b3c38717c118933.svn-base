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
	function next(){
		$('.tabPanel ul li').eq(1).addClass('hit').siblings().removeClass('hit');
		$('.panes>div:eq(1)').show().siblings().hide();	
	}
</script>  
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> >
</#if> <a href="javascript:window.location.reload();">征求意见稿</a>
</div>
  <form action="/step10/writeSave.htm" method="post" id="contractForm"  enctype="multipart/form-data">  
  <input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
    <div id="content">
    <div style="width:68%;margin:0px auto;">
    <div class="tabPanel">
		<ul>
			<li class="hit">征求意见稿发出确认签收单</li>
			<li>（征求意见稿）意见反馈表</li>
		</ul>
		<div class="panes">
				<div class="pane" style="display:block;"><br />
					        <table border="1" cellspacing="1" cellpadding="1" class="input_table2" style="margin: 0px auto 10px;">
					              <tr>
					                	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">征求意见稿发出确认签收单</td>
					              </tr>
					              <tr>
						                <td width="13%" class="tab_title">项目名称</td>
						                <td colspan="3">
							                <input name="step10.id" value="${step10.id}"  type="hidden" />
							                <input name="project.id" value="${project.id}" type="hidden" />
							  				<input name="project.name" value="${project.name}" readonly="readonly"   id="projectNameId"  type="text" class="text_a" />
										</td>
					              </tr>
					              <tr>
							                <td width="13%" class="tab_title red">文件名称</td>
							                <td colspan="3"><input name="step10.fileName" value="${step10.fileName}" type="text" class="text_a"/></td>
					              </tr>
					              <tr>
							                <td width="13%" class="tab_title red">主送单位及部门</td>
							                <td colspan="3"><input name="step10.receiverDeptName" value="${step10.receiverDeptName}" type="text" class="text_a"/></td>
					              </tr>
					              <tr>
							                <td class="tab_title red">报告份数</td>
							                <td width="35%"><input name="step10.fileCount" value="${step10.fileCount}" maxlength=9 type="text" class="text_a"/></td>
							                <td width="14%" class="tab_title">文号</td>
							                <td width="38%"><input name="step10.fileNo" value="${step10.fileNo}" type="text" class="text_a"/></td>
					              </tr>
					          	  <tr>
							                <td class="tab_title red">有关情况说明</td>
							                <td colspan="3"><textarea name="step10.description" rows="5"  style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step10.description }</textarea></td>
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
							                <td class="tab_title">意见</td>
							                <td colspan="3">
					                			<textarea name="step10.receiverView" rows="5"  style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step10.receiverView }</textarea>
					            				<span style=" float:left; width:33%;margin-bottom: 10px;">签收方式：
					                				<select name="step10.receiverType" style="margin:0 auto;line-height:22px; width:33%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;" >
											            <option value="1" <#if step10.receiverType== 1>selected='selected'</#if>  >签名</option>
											            <option value="2" <#if step10.receiverType== 2>selected='selected'</#if> >电邮</option>
											            <option value="3" <#if step10.receiverType== 3>selected='selected'</#if>>电话</option>
							       					 </select>
							       				</span>
					             				<span style=" float:left; width:33%;margin-bottom: 10px;">签收人：
					             					<input name="step10.receiverUserName" value="${step10.receiverUserName}" type="text" style="margin:0 auto;line-height:22px; width:33%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/>
					             				</span>
					            				<span style=" float:left;width:33%;margin-bottom: 10px;">签收日期：
					            					<input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="step10.receiverDate" value="${step10.receiverDate}" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/>
					            				</span>
					            			</td>
					           		 </tr>
					           		 
					           		 <tr height="50px"><td colspan="4">
							                <input onclick="javascript:next();" value="下一步" class="sub" type="button" style="width: 80px;height: 30px;">
					            	</td></tr>
					         </table><br/>
   			 </div>
  
		    <div class="pane"><br />
				      <table border="1" cellspacing="1" cellpadding="1" class="input_table2">
				      <tbody id="fkyjtbody">
						      <tr>
							        <td width="92%" colspan="2" style=" font-weight:bold; font-size:20px; text-align:center;">(征求意见稿)意见反馈表</td>
							        <td width="8%"><div style="display:block; text-align:center; border:1px solid #dadada; border-bottom:0px" class="add_link" >
							                 <input type="button" onclick="javascript:addFKYZItem();" value="+新增" class="sub"/> 
							                       </div> 
							        </td>
						       </tr>
						       <tr>
							        <td   width="50%" style=" text-align:center; font-weight:bold;">反馈意见内容</td>
							        <td   width="40%" style=" text-align:center; font-weight:bold;">理由或依据</td>
							        <td  colspan="1" width="10%" style=" text-align:center; font-weight:bold;">操作</td>
						      </tr>
						         <#list  step10FKYJList as fkyj >
										<tr >
											    <td  style="background-color:#fff; text-align:center;">${fkyj.content}</td>
											    <td style="background-color:#fff; text-align:center;">${fkyj.reason}</td>
											    <td style="background-color:#fff; text-align:center;"><a href="javascript:deleteFKYJ(${fkyj.id})">删除</a></td>
									    </tr>
								</#list>
						</tbody>
						<tfoot>
							 <tr height="50px">
								 	<td colspan="4">
									           <input onclick="location.href ='javascript:history.back();';" value="取消" class="sub" type="button" style="width: 80px;height: 30px;">
									          <input type="submit"  value="保存"  class="sub" style="width: 80px;height: 30px;">
									           <input onclick="javascript:toNextStep1();" value="转交任务" class="sub" type="button" style="width: 80px;height: 30px;">
									           <input onclick="javascript:toValidate();" value="提交审定" class="sub" type="button" style="width: 80px;height: 30px;">
							        </td>
						      </tr>
					   </tfoot>
				 </table><br />  
		    </div>
		    
    </div>
    </div>
	</div>
   </div>
</form>
<script>
	var i=0;
	var flag = true;
	var j=0;
	var flag2 = true;
	function AddFKYJRow()
	{
	//添加一行
	var tab1 = document.getElementById("fkyjtbody");
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
	var newTd1 = newTr.insertCell();
	var newTd2 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<input name="contentFK"  type="text" class="text_a"/>';
		newTd1.innerHTML = '<input name="reasonFK"  type="text" class="text_a"/>';
		newTd2.innerHTML = '<a onclick="javascript:deleteFKYJRow('+i+')">删除</a>';
}

	function deleteFKYJRow(i){
		var tab1 = document.getElementById("fkyjid");
		  $('#'+i).remove();
	}
	function addFKYZItem(){
		AddFKYJRow();
	}
	$().ready(function() {  
	  	      $("#contractForm").validate({
					rules : {  
					'step10.fileName' : { required : true },
					'step10.receiverDeptName' : { required : true },
					'step10.fileCount' : { required : true, number : true },
					'step10.description' : { required : true },
					'nextWorkerNameId' : { required : true }  
					}
				})
	}); 

	function toValidate(){  //提交审定
		 if(confirm("确定要提交审定吗?"))
		 {
			 document.getElementById("contractForm").action="/step10/toValidate.htm";
			 $("#contractForm").submit();
		 }
	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step10/writeSave.htm";
		document.getElementById("contractForm").submit();
	}

</script>
<script>
	function deleteFKYJ(id){
	$.ajax({
				type:"get",
				url:'/step10/deleteFKYJ.htm?id=' + id,
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
</script>
