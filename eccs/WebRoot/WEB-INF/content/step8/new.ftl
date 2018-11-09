<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js"   type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
<a href="javascript:window.location.reload();">审核</a>
</div>
  <form action="/step8/save.htm" method="post" id="contractForm">  
  		<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
    	<div id="content">
    		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				造价咨询项目审核表
			</div>
  <table border="1" cellspacing="1" cellpadding="1" class="input_table">
  	  <!-- <tr>
    		<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目审核表</td>
      </tr> -->
  	  <tr>
		    <td width="92" class="tab_title">项目名称</td>
		    <td style="background-color:#fff;">
				<input name="step8.id" value="${step8.id}"  type="hidden" />
				<input name="project.id" value="${project.id}" type="hidden" />
   				<input name="project.name" value="${project.name}"   id="projectNameId"  type="hidden" />
				${project.name}
			</td>
   			<td class="tab_title">项目编号</td>
    		<td  style="background-color:#fff;">
     			<input value="${project.no }"  id="projectNoId"  type="hidden" />${project.no }
			</td>
     </tr>
  	 <tr>
   			<td colspan="4"  >
	              <div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
	              <a href="javascript:addstep8Item(); " >+新增</a>             
	    		  </div>
	    <table border="1" id="step8ItemId" cellspacing="1" cellpadding="1" class="list_table3" style=" margin-top:10px;">
	  		<tr>
			    <td width="133" style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">主要问题</td>
			    <td width="155" style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">修改及执行情况</td>
			    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:42px;">操作</td>
	  		</tr>
  	<#list step8ItemList as step8Item>
  			<tr>
			    <td style=" text-align:center;background-color:#fff;"><input type="text" name="content" value="${step8Item.content}"  class="text_a"  /><input type="hidden" name="values" value='${step8Item_index}'  class="text_a"  /></td>
			    <td style=" text-align:center;background-color:#fff;">
			    	<input name="result_${step8Item_index}"  type="radio" value="1"  <#if step8Item.result == 1>checked="checked"</#if>   />&nbsp;修改通过
			    	 &nbsp;&nbsp;&nbsp;<input name="result_${ step8Item_index}"  type="radio" value="2"   <#if step8Item.result == 2>checked="checked" </#if> />&nbsp;修改未通过 
			     	&nbsp;&nbsp;&nbsp;<input name="result_${ step8Item_index}"  type="radio" value="0"   <#if step8Item.result == 0>checked="checked" </#if> />&nbsp;未修改 </td>
			    <td style=" text-align:center;"><a href="javascript:deletesz(${step8Item.id})">删除</a></td>
  </tr>
  </#list>
  </table>
    </td>
  </tr>
  <tr>
          <td  class="tab_title red"> 编制人</td>
          <td   style="background-color:#fff;" >
          <input name="step8.writeName" value="${step8.writeName}"  type="hidden"/>${step6.user.name}</td>
          <td  class="tab_title">编制日期</td>     
          <td   style="background-color:#fff;"> <input value="${step6.confirmTime}" type="hidden" />${step6.confirmTime}</td>       
  </tr>
  <tr>
          <td  class="tab_title red"> 审核人</td>
          <td  class="tab_title">
          		<#if step8.validateName!=null>
	          			<input name="step8.validateName" value="${step8.validateName}"  type="text" class="text_a" />
	           <#else>
	           			<input name="step8.validateName" value="${user.name}"   type="text" class="text_a" />
	           </#if>
           </td>
          <td  class="tab_title">审核日期</td>     
          <td   style="background-color:#fff;">
          		<#if step8.validateDate!=null>
	          			<input name="step8.validateDate" value="${step8.validateDate}" type="hidden" />${step8.validateDate}
	           <#else>
	           			<input name="step8.validateDate" value="${validateDate}" type="hidden" />${validateDate}
	           </#if> 
          </td>       
  </tr>
  <tr>
				<td colspan="4"  style="padding: 0px 0px;">
					<table border="1" id="fileUploadTableId" cellspacing="1" cellpadding="1" class="list_table4"  style="margin: 0px auto;border-width:0px; border-style:hidden;">
						<tr>
							<td colspan="2" width="15%" style=" text-align:center; font-weight:bold;">资料类型</td>
							<td width="20%" style=" text-align:center; font-weight:bold;">资料名称</td>
							<td width="15%" style=" text-align:center; font-weight:bold;">文件字号</td>
							<td width="15%" style=" text-align:center; font-weight:bold;">文件作者</td>
							<td width="15%" style=" text-align:center; font-weight:bold;">页号</td>
							<td width="15%" style=" text-align:center; font-weight:bold;">备注</td>
							<td width="5%" style=" text-align:center; font-weight:bold;width:42px;">操作</td>
						</tr>
						<#list annexList as annex>  
						<tr>
							<td  colspan="2"  style="text-align:center;background:#fff;" >${annex.annexType.name }</td>
							<td style="text-align:center;background:#fff;">${annex.name }</td>
							<td style="text-align:center;background:#fff;">${annex.fileNum}</td>
							<td style="text-align:center;background:#fff;">${annex.fileOwner }</td>
							<td style="text-align:center;background:#fff;">${annex.filePage }</td>
							<td style="text-align:center;background:#fff;">${annex.description}</td>
							<td style="text-align:center;background:#fff;"><#if annex.id!="" ><a href="/file/xiazai.htm?proid=${annex.id}">下载</a></#if></td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>
  <tr>
        <td colspan="7" style=" text-align:right;">
	        <input type="button"  onclick="location.href ='javascript:history.back();';" value="取消" class="sub"/>
	        <input type="submit"  value="保存"  class="sub" />  
	        <input type="button" onclick="javascript:toNextStep1();"  value="转交审核"  class="sub" />  
	         <#if user.username != "${SUPERADMIN}" >     
	        <input type="button" onclick="javascript:toNextStep();"  value="提交审定"  class="sub" />                                           
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
	                   'step8.writeName' : { required : true }, 
	                   'step8.validateDate' : { required : true } ,
	                   'step8.validateName' : { required : true } 
					}
				})
	}); 
	
	function toNextStep(){  //执行下一步
		 if(confirm("当前步骤执行完毕,确定要执行下一步吗?"))
		 {
			 document.getElementById("contractForm").action="/step8/toNextStep.htm";
			  $("#contractForm").submit();
		 }
	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("contractForm").action="/step8/save.htm";
		document.getElementById("contractForm").submit();
	}
	
	function AddRow()
	{
		//添加一行
		//var tab1 = $("#contractReviewItemId");
		var tab1 = document.getElementById("step8ItemId");
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
		newTd0.innerHTML = '<input type="text" name="content"  class="text_a"  /><input type="hidden" name="values" value='+i+'  class="text_a"  />'; 
		newTd1.innerHTML = '<input name=result_'+i+' type="radio"  value="1"  />&nbsp;修改通过' +
		'&nbsp;&nbsp;&nbsp;<input name=result_'+i+'  type="radio"  value="2" />&nbsp;修改未通过'+
            '&nbsp;&nbsp;&nbsp;<input name=result_'+i+'  type="radio"  value="0" />&nbsp;未修改';
		newTd2.innerHTML = '<a onclick="javascript:deleteRow('+i+')">删除</a>';
}

	function deleteRow(i){
		var tab1 = document.getElementById("step8ItemId");
		  $('#'+i).remove();
		//tab1.deleteRow(i);
	}
	
	function addstep8Item(){
		AddRow();
	}

</script>
<script>
	function deletesz(id){
		$.ajax({
					type:"get",
					url:'/step8/deletesz.htm?id=' + id,
					asyn:true,		//false为同步提交
					dataType:'text',	//返回文本
					success:function(d){
							window.parent.location.reload();
					}
				})
}
</script>