<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<#if showShare==1> <a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> > </#if>
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if>
<a href="javascript:window.location.reload();">成果文件编制</a>
</div>
  <form action="/step11/save.htm" method="post" id="contractForm" enctype="multipart/form-data">  
    <div id="content">
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
	       	<input name="step11.id" value="${step11.id}"  type="hidden" />
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
            <td colspan="8" style=" width:20px; height:30px;font-weight:bold; line-height:30px; text-align:center;"> 
         		   附件资料
            </td>
  </tr>
  <tr>
	    <td width="20%" style=" text-align:center; font-weight:bold;">资料类型</td>
	  	<td width="15%" style=" text-align:center; font-weight:bold;">文件</td>
	    <td width="15%" style=" text-align:center; font-weight:bold;">资料名称</td>
	    <td width="10%" style=" text-align:center; font-weight:bold;">文件字号</td>
	    <td width="15%" style=" text-align:center; font-weight:bold;">文件作者</td>
	    <td width="15%" style=" text-align:center; font-weight:bold;">页号</td>
	    <td width="15%" style=" text-align:center; font-weight:bold;">备注</td>
	    <td width="5%" style=" text-align:center; font-weight:bold;width:42px;">操作</td>
  </tr>
   <#list annexList as annex>  
  <tr>
	    <td  style="text-align:center;background:#fff;" >${annex.annexType.name }</td>
	    <td  style="text-align:center;background:#fff;" >${annex.name }</td>
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
	        <input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
        </td>
    </tr>
    	</table>
   </div>
</form>
<script>
	function toPrint(id){
		var url = "/step11/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
</script>
