<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<meta name=ProgId content=Excel.Sheet>
		<meta name=Generator content="Microsoft Excel 12">
		<script type="text/javascript" src="../../../js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="../../../js/jQuery.print.js"></script>
		<style>
		.table-d table{border:1px solid #000000;border-collapse: collapse}
		.table-d table td{border:1px solid #000000;}
		table tr td{padding-left: 8px;padding-right: 8px;}
		</style>
		<script language="JavaScript">
			function toPrint(){
				$("#print").print({
					globalStyles: true,
				    mediaPrint: false,
				    stylesheet: null,
				    noPrintSelector: ".no-print",
				    iframe: true,
				    append: null,
				    prepend: null,
				    manuallyCopyFormValues: true,
				    deferred: $.Deferred()
				});
				
			}
		</script>
	</head>
	<body link=blue vlink=purple>
		<div id="print" align="center" class="table-d">
		</br></br>
			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				建设工程造价咨询工作方案
			</div>
		     <table border="1" cellspacing="0" cellpadding="0" width="800"  style="border-collapse: collapse;" >
	      <tr>
		        <td class="tab_title" align="right" style="font-weight:bold;width: 18%;"><font size="2">项目基本情况</font></td>
		        <td height="80px" colspan="5" valign="top" align="left" style="width: 80%;">
		       		<pre style="font-size:14px;line-height: 20px;white-space: pre-wrap;word-wrap: break-word;">${step3.projectInfo}</pre>
				</td>
	      </tr>
	      <tr>
		        <td  class="tab_title" width="150" align="right" style="font-weight:bold;"><font size="2">咨询工作目标和范围</font></td>
		        <td colspan="5" height="80px" align="left" valign="top">
		        		<pre style="font-size:14px;line-height: 20px;white-space: pre-wrap;word-wrap: break-word;">${step3.consultTargetInfo }</pre>
				</td>
	      </tr>
	      <tr style="height: 100px;">
				<td class="tab_title" align="right" style="font-weight:bold;" >
					<font size="2">人员分配表</font>
				</td>
				<td colspan="5" style="padding-right: 0px; padding-left: 0px;"> 
					<table border="1" cellspacing="1" cellpadding="1" class="list_table4" style="width: 100%;border-collapse: collapse;border-width:0px; border-style:hidden;">
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
			</tr>
	      <tr>
		        <td  class="tab_title" align="right" style="font-weight:bold;"><font size="2">工作程序和处理原则</font></td>
		        <td colspan="5" align="left" height="80px" valign="top">
		       			<pre style="font-size:14px;line-height: 20px;">${step3.workHandleInfo }</pre>
		       </td>
	      </tr>
<!--	      <tr>
		        <td  class="tab_title" align="right" style="font-weight:bold;"><font size="2">编制依据</font></td>
		        <td colspan="5" align="left" height="80px" valign="top">
		       			<div   style="min-height: 100px;"><font size="2">${step3.basisCompilation}</font></div>
		       </td>
	      </tr>
	      -->
	      <tr>
				<td  class="tab_title" align="right" style="font-weight:bold;"><font size="2">合同编号</font></td>
		        <td colspan="4"  style="background-color:#fff;" valign="top" align="left">
		        ${step3.project.no}
		        </td>
		  </tr>
	      <tr>
		        <td  class="tab_title" align="right" style="font-weight:bold;"><font size="2">领导批示</font></td>
		        <td colspan="5" align="left"  valign="top">
		        	<pre style="font-size:14px;line-height: 20px;">${step3.masterSign }</pre>
		        </td>
	      </tr>
<!--	      <tr>
		        <td  align="right" height="30" ><font size="2" style="font-weight:bold;">技术总负责人</font></td>
		        <td  align="left" style="width: 18%;"><font size="2" >${step3.techMasterName }</font></td>
		        <td  align="right" style="width: 15%;"><font size="2"  style="font-weight:bold;">单位法定代表人</font></td>
		        <td  align="left" style="width: 18%;"><font size="2" >${step3.lawMasterName }</font></td>
		        <td  align="right" style="width: 10%;"><font size="2" align="right" style="font-weight:bold;">制定日期</font></td>
		        <td  align="left" ><font size="2" >${step3.ctime }</font></td>
	      </tr>
	      -->
	      <tr>
				<td class="tab_title" align="right" style="font-weight:bold;">
					<font size="2">制定日期</font>
				</td  >
				<td colspan="4" style="background-color:#fff;" >
					${step3.ctime }
				</td>
			</tr>
          </table>
		</div>
		</br>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>

</html>
