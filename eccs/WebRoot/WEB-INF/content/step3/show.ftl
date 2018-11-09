<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery-1.4.2.js" type="text/javascript"></script>
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
<a href="javascript:window.location.reload();">制定编制咨询方案</a>
</div>
  <form action="/step3/save.htm" method="post" id="contractForm">  
 <div id="content">
 		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			建设工程造价咨询工作方案
		</div>
      <table border="1" cellspacing="1" cellpadding="1" class="input_table">
	      <tr>
		        <td width="20%" class="tab_title">项目基本情况</td>
		        <td width="80%" colspan="4"  style="background-color:#fff;"  valign="top" align="left">
		        	<pre style="font-size:14px;white-space: pre-wrap;word-wrap: break-word;">${step3.projectInfo}</pre>
		        </td>
	      </tr>
	      <tr>
		        <td  class="tab_title">咨询工作目标和范围</td>
		        <td colspan="4"  valign="top" style="background-color:#fff;"  align="left">
						<pre style="font-size:14px;white-space: pre-wrap;word-wrap: break-word;">${step3.consultTargetInfo }</pre>
		        </td>
	      </tr>
	      <tr style="height: 100px;">
				<td class="tab_title" width="20%">人员分配表</td>
				<#if swtList??>
					<td colspan="5">
							<table border="1" cellspacing="1" cellpadding="1" class="input_table" style="width: 100%;">
								<#list swtList as swt>
											<#if swt_index%2==0>
											<tr style="height: 25px;">
											<td style="text-align:right;background-color:#fff;width: 20%;">${swt.proceStepDef.stepName}</td>
											<td style="text-align:left;background-color:#fff;">${swt.step3Worker.workUserName}</td>
											<#else> 
											<td style="text-align:right;background-color:#fff;width: 20%;">${swt.proceStepDef.stepName}</td>
											<td style="text-align:left;background-color:#fff;">${swt.step3Worker.workUserName}</td>
											</tr>
											</#if>
								</#list>
							</table>
					</td>
				<#else>	
					<td colspan="5" style="background-color:#fff;"></td>
				</#if>
			</tr>
	      <tr>
		        <td  class="tab_title">工作程序和处理原则</td>
		        <td colspan="4"  style="background-color:#fff;" valign="top" align="left">
			       	<pre style="font-size:14px;white-space: pre-wrap;word-wrap: break-word;">${step3.workHandleInfo }</pre>
		        </td>
	      </tr>
<!--	      <tr>
		        <td  class="tab_title">编制依据</td>
		        <td colspan="4"  style="background-color:#fff;" valign="top" align="left">
			        <div cols="45" rows="5" style="font-size:14px;width: 100%;min-height: 100px;" border: 1px solid #c1e9ff; margin-top: 5px; margin-bottom: 5px;">
			       		 ${step3.basisCompilation}
			        </div>
		        </td>
	      </tr>
-->
			<tr>
				<td  class="tab_title">合同编号</td>
		        <td colspan="4"  style="background-color:#fff;" valign="top" align="left">
		        ${step3.project.no}
		        </td>
			</tr>
		 <tr>
				<td class="tab_title">
					领导批示
				</td>
				<td colspan="4"  style="background-color:#fff;" valign="top" align="left">
			        <pre style="font-size:14px;white-space: pre-wrap;word-wrap: break-word;">${step3.masterSign }</pre>
		        </td>
			</tr>
<!--		<tr>
				<td class="tab_title red">
					技术总负责人
				</td>
				<td style="background-color:#fff;" >
					${step3.techMasterName }
				</td>
				<td class="tab_title red">
					单位法定代表人
				</td>
				<td  style="background-color:#fff;" >
					${step3.lawMasterName }
				</td>
			</tr>
-->
			<tr>
				<td class="tab_title">
					制定日期
				</td>
				<td colspan="4"  style="background-color:#fff;" valign="top" align="left">
			       		 ${step3.ctime }
		        </td>
			</tr>
        <tr>
            <td colspan="5" style=" text-align:right;">
            	<input type="button"  onclick="location.href ='javascript:history.back();';" value="返回" class="sub"/>
            	<input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
            </td>
        </tr>  
         </table>
  </div>
</form>
<script>
	function toPrint(id){
		   var url = "/step3/print.htm?project.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>
