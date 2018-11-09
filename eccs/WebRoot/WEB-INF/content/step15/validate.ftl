<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> ></#if>
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
<a href="javascript:window.location.reload();">资料归档</a>
</div>
  <form action="/step15/save.htm" method="post" id="contractForm"> 
  		<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
   		<input name="step15.validateStatus" id="step15.validateStatus"  type="hidden" /> 
    	<input name="step15.dealUserId" id="step15.dealUserId" value="${step15.dealUserId}" type="hidden" />
    	<input type="hidden" name="step15.receiveManagerId" id="receiveManagerId" />
    	<div id="content">
    		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				资料归档确认
			</div>
   <table border="1" cellspacing="1" cellpadding="1" class="input_table">
		<!-- <tr>
				<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">资料归档</td>
		</tr> -->
     	<tr>
			    <td class="tab_title">工程名称</td>
			    <td colspan="3" width="34%" style="background:#fff;">
			       	<input name="step15.id" value="${step15.id}"  type="hidden" />
				 	 <input name="project.id" value="${project.id}"  type="hidden" id="projectIdId" />
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
			    <td  colspan="6" class="tab_title" style="font-weight:bold;text-align: center;height: 30px;"><font size="2">附件</font></td>
  		</tr>
		<tr>
    			<td colspan="4" style="padding: 0px 0px;">
    				<table border="1" id="fileUploadTableId" cellspacing="1" cellpadding="1" class="list_table4" style="margin: 0px auto;border-width:0px; border-style:hidden;" >
		  <tr>
		      <td width="15%" style=" text-align:center; font-weight:bold;">资料类型</td>
		      <td width="15%" style=" text-align:center; font-weight:bold;">资料名称</td>
		      <td width="10%" style=" text-align:center; font-weight:bold;">文件字号</td>
		      <td width="10%" style=" text-align:center; font-weight:bold;">文件作者</td>
		      <td width="15%" style=" text-align:center; font-weight:bold;">页号</td>
		      <td width="15%" style=" text-align:center; font-weight:bold;">归档时间</td>
		      <td width="15%" style=" text-align:center; font-weight:bold;">备注</td>
		      <td width="15%" style=" text-align:center; font-weight:bold;">操作</td>
		  </tr>
   <#list annexList as annex>  
  		<tr >
			    <td  style="text-align:center;background-color:#fff;" ><input type="hidden" name="annexId"  value="${annex.id }"  class="text_a"  />${annex.annexType.name }</td>
			    <td style="background-color:#fff;">${annex.name }</td>
			    <td style="background-color:#fff;">${annex.fileNum}</td>
			    <td style="background-color:#fff;">${annex.fileOwner }</td>
			    <td style="background-color:#fff;">${annex.filePage }</td>
			    <td style="background-color:#fff;">${annex.filedTime }</td>
			    <td style="background-color:#fff;">${annex.description}</td>
			    <td style="background-color:#fff;"><#if annex.id!="" ><a href="/file/xiazai.htm?proid=${annex.id}">下载</a></#if></td>
    		</tr>
   </#list>
</table></td>
</tr>
			<tr height="50px">
				<td colspan="4" style="text-align: left;">
				<span class="red">是否归档</span>
				<input type="radio" name="step15.confirmId" id="step15.confirmId" value="1"  />&nbsp;&nbsp;已归档
				<input type="radio" name="step15.confirmId" id="step15.confirmId" value="0"  />&nbsp;&nbsp;未归档
				</td>
			</tr>
            <tr>
                <td colspan="7" style=" text-align:right;">
                	<input type="button"  onclick="location.href ='javascript:history.back();';" value="返回" class="sub"/>
                	<input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>
                	<input onclick="javascript:toNextStep1();" value="转交任务" class="sub" type="button" style="width: 80px;height: 30px;">
			        <input onclick="javascript:toNextStep();" value="提交任务" class="sub" type="button" style="width: 80px;height: 30px;">
                </td>
            </tr>
    	</table>
    </div>
</form>
<script>
	$().ready(function() {  
	  	      $("#contractForm").validate({
					rules : {  
						'step15.confirmId' : {
							required : true
						},
					}
				})
	});

	function toPrint(id){
		  var url = "/step15/print.htm?project.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
	
	function toNextStep(){  //执行下一步
		 if(confirm("当前步骤执行完毕,确定要执行下一步吗?"))
		 {
		 	var receiveManager=$("#receiveManagerId").val();
			document.getElementById("nextWorkerId").value = receiveManager;
		 	document.getElementById("step15.validateStatus").value = 1;
		 
		 	var confirm_id = $('input:radio:checked').val(); 
		 	if(confirm_id == 0){
			 		var dealUser=document.getElementById("step15.dealUserId").value;
					document.getElementById("receiveManagerId").value = dealUser;
			 		document.getElementById("step15.validateStatus").value = 0;
				 	document.getElementById("contractForm").action="/step15/toValidate.htm";
			 	}else{
			 		document.getElementById("step15.validateStatus").value = 2;
					document.getElementById("contractForm").action="/step15/toNextStep.htm";
			 }
			 $("#contractForm").submit();
		 }
	}
	
	function toNextStep1() { //转交 nextWorkerId
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		$('#nextWorkerId').val(id);
		document.getElementById("step15.validateStatus").value = 1;
		document.getElementById("contractForm").action="/step15/save.htm";
		document.getElementById("contractForm").submit();
	}
	
</script>
