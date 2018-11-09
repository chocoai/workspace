<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> ></#if>
<a >${project.name}</a> >
<a href="javascript:window.location.reload();">底稿编制</a>
</div>
  <form action="/step6/save.htm" method="post" id="contractForm" enctype="multipart/form-data">  
   	 <div id="content">
  <table border="1" cellspacing="1" cellpadding="1" class="input_table">
   	<tr>
      	 <td colspan="3" style=" font-weight:bold; font-size:20px; text-align:right;"></td>
   		 <td  style=" font-weight:bold; font-size:12px; text-align:right;">
    		<div style=" float:left;line-height:22px; width:80px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;" >编制批次:</div>
     		<div ><select name="datestrId" id="datestrId" onchange="javascript:dateChange();"  style=" float:left;line-height:22px; width:180px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;">
				<#list dateList as datestr>
					<option value="${datestr}"  <#if datestrId ==datestr>selected='selected'</#if>  >${datestr}</option>
				</#list>
				</select></div>	
		</td>
    </tr>
  	<tr>
    	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">底稿编制</td>
    </tr>
    <tr>
	    <td class="tab_title">工程名称：</td>
	    <td colspan="3" width="34%" style="background:#fff;">
	       <input name="modify" value="${modify}"  type="hidden" />
	       <input name="step6.id" value="${step6.id}"  type="hidden" />
	       <input name="project.id" value="${project.id}" type="hidden" />
	    ${project.name}</td>
  	</tr>
  	<tr>
	    <td class="tab_title">委托单位：</td>
	    <td width="34%" style="background:#fff;">${project.customer.cusName}</td>
	    <td width="16%" class="tab_title">编审类型：</td>
	    <td width="35%" style="background:#fff;">${project.editorialType.name}</td>
    </tr>
  	<tr>
	    <td class="tab_title">工程编号：</td>
	    <td width="34%" style="background:#fff;">${project.no}</td>
	    <td width="16%" class="tab_title">服务类别：</td>
	    <td width="35%"  style="background:#fff;">${project.serviceType.name}</td>
  	</tr>
	<tr>
    	<td colspan="4" >
    <table border="1" id="fileUploadTableId" cellspacing="1" cellpadding="1" class="list_table4">
        <tr>
            <td colspan="8" style=" width:20px; height:30px; line-height:30px; text-align:right;"> 
	            <div class="add_link"  style=" display:block; text-align:right;float:right; border:1px solid #dadada; border-bottom:0px;" >
	             <a href="javascript:addFileUpload();">+新增</a>
	    		 </div>
    		 </td>
        </tr>
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
</table></td>
</tr>
        <tr>
            <td colspan="7" style=" text-align:right;">
            <input type="button"  onclick="location.href ='javascript:history.back();';" value="返回" class="sub"/>                                          
     
            </td>
        </tr>
    </div>
</form>
<script>
	
	function dateChange(){
	   var datestr = $("#datestrId").val();
	   window.location.href="/step6/edit.htm?project.id=${project.id}&datestrId="+datestr;
	}
</script>