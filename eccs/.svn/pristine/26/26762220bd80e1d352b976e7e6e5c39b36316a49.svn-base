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
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
<a href="javascript:window.location.reload();">项目总结</a>
</div>
  <form action="/step13/save.htm" method="post" id="contractForm">  
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
			    <input name="step1.id" value="${step14.id}"  type="hidden" />
				<input name="project.id" value="${project.id}"  type="hidden" />
			    ${project.name}
		    </td>
		    <td class="tab_title" >项目类型</td>
		    <td style="background-color:#fff;width:120px;">${project.projectType.name}</td>
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
		    <td  style="background-color:#fff;">${step14.projectSize}</td>
		    <td class="tab_title" >工期要求</td>
		    <td colspan="3" style="background-color:#fff;">${step14.projectLimit}</td>
       </tr>
   <#-->
  	   <tr>
		    <td class="tab_title">执行过程描述</td>
		    <td colspan="6" style="background-color:#fff;" valign="top" align="left">
		    	<div   style="min-height: 100px;">${step14.process }</div>
		    </td>
       </tr>
	   <tr>
		    <td class="tab_title">执行效果评价</td>
		    <td colspan="6" style="background-color:#fff;" valign="top" align="left">
		    	 <div   style="min-height: 100px;">${step14.evaluate}</div>
		    </td>
	   </tr>
	   <tr>
		    <td class="tab_title">项目请款及收款情况</td>
		    <td colspan="6" style="background-color:#fff;" valign="top" align="left">
		    	<div   style="min-height: 100px;">${step14.qmoney }</div >
		    </td>
	   </tr>
   <-->
	   <tr>
		    <td class="tab_title">存在问题及改进方法</td>
		    <td colspan="6" style="background-color:#fff;" valign="top" align="left">
		    	<div   style="min-height: 100px;">${step14.question}</div>
		    </td>
	   </tr>
	   <tr>
		    <td colspan="7" style="padding: 0px 0px;">
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
		    <td class="tab_title">总结时间</td>
		    <td colspan="2" style="background-color:#fff;">${step14.confirmTime}</td>
		    <td class="tab_title">总结人</td>
		    <td colspan="3" style="background-color:#fff;">${step14.user.name}</td>
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
		var url = "/step14/print.htm?project.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>