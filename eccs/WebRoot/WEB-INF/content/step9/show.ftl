<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> > </#if>
 <#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
 <a href="javascript:window.location.reload();">审定</a>
 </div>
  <form action="/step9/save.htm" method="post" id="contractForm">  
    	<div id="content">
    		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				造价咨询项目审定表
			</div>
  <table border="1" cellspacing="1" cellpadding="1" class="input_table">
  	<!-- <tr>
    	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目审定表</td>
    </tr> -->
  	<tr>
	     <td width="92" class="tab_title">项目名称</td>
	    <td style="background-color:#fff;">
	    ${project.name}
		</td>
	    <td class="tab_title">项目编号</td>
	    <td style="background-color:#fff;">
	     ${project.no }
		</td>
    </tr>
  	<tr>
    	<td colspan="4"  >
	  <table border="1" id="step9ItemId" cellspacing="1" cellpadding="1" class="list_table3" style=" margin-top:10px;">
	 	 <tr>
		    <td width="133" style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">主要问题</td>
		    <td width="155" style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">修改及执行情况</td>
	  	 </tr>
  		<#list step9ItemList as step9Item>
  		  <tr>
		    <td style=" text-align:center;background-color:#fff;">${step9Item.content}</td>
		    <td style=" text-align:center;background-color:#fff;">
		     	 <#if step9Item.result == 1>修改通过
                 <#elseif step9Item.result == 2>修改未通过
                 <#elseif step9Item.result == 0>未修改
                 </#if> 
			</td>
  		 </tr>
  </#list>
</table>
    </td>
  </tr>
  <tr>
          <td  class="tab_title"> 编制人</td>
          <td  style=" background-color:#fff;">${step6.user.name}</td>
          <td  class="tab_title">编制日期</td>     
          <td  style="background-color:#fff;"> ${step6.confirmTime}</td>  
  </tr>
  <tr>
          <td  class="tab_title">审核人</td>
          <td  style=" background-color:#fff;"> ${step9.validateName}</td>
          <td  class="tab_title">审核日期</td>     
          <td  style="background-color:#fff;"> ${step9.validateDate}</td>      
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
    			<input type="button"  onclick="location.href ='javascript:history.back();';" value="返回" class="sub"/>                                          
    	 		<input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
    	  </td>
   </tr>
</table>
</div>
</form>
<script>
	function toPrint(id){
		var url = "/step9/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
</script>