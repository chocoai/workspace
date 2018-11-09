<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<meta name=ProgId content=Excel.Sheet>
		<meta name=Generator content="Microsoft Excel 12">
		<script type="text/javascript" src="../../../js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="../../../js/jQuery.print.js"></script>
		<style>
		table{table-layout: fixed;}
		td(word-break: break-all; word-wrap:break-word;)
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
	</br></br>
		<div id="print" align="center" class="table-d" >
			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				造价咨询项目审定表
			</div>
  <table border="1" cellspacing="0" cellpadding="0" width="800" style="border-collapse: collapse;">
  	<!-- <tr>
    	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目审定表</td>
    </tr> -->
  	<tr>
	    <td width="92" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">项目名称</font></td>
	    <td style="background-color:#fff;" align="left" width="300">
	    <font size="2">${project.name}</font>
		</td>
    	<td width="92" align="right" style="font-weight:bold;" height="30"><font size="2">项目编号</font></td>
    	<td style="background-color:#fff;" align="left" width="300">
     	<font size="2">${project.no }</font>
		</td>
    </tr>
  	<tr>
    	<td colspan="4"  style="padding-right: 0px; padding-left: 0px;" >
	  <table border="1" id="step9ItemId" cellspacing="1"  cellpadding="1" style="border-collapse: collapse;border-width:0px; border-style:hidden;" width="100%">
	 	 <tr>
		    <td width="50%" style=" text-align:center;  background-color:#fff;font-weight:bold;" height="30"><font size="2">主要问题</font></td>
		    <td width="50%" style=" text-align:center;  background-color:#fff;font-weight:bold;" height="30"><font size="2">修改及执行情况</font></td>
	  	 </tr>
	  	 <#if step9ItemList && step9ItemList ?size!=0>
		  		<#list step9ItemList as step9Item>
		  		     <tr>
						    <td height="30" style=" text-align:center;background-color:#fff; word-break: break-all;"><font size="2">${step9Item.content}</td>
						    <td height="30" style=" text-align:center;background-color:#fff;">
						    <#--- <input name="radio${step8Item_index}" disabled="disabled" type="radio"  <#if step8Item.result == 1>checked="checked"</#if>   />&nbsp;<font size="2">修改通过</font>
				                    &nbsp;&nbsp;&nbsp;<input name="radio${ step8Item_index}"  type="radio" disabled="disabled"  <#if step8Item.result == 2>checked="checked"</#if> />&nbsp;<font size="2">修改未通过</font>   
				                    &nbsp;&nbsp;&nbsp;<input name="radio${ step8Item_index}"  type="radio" disabled="disabled"  <#if step8Item.result == 0>checked="checked"</#if> />&nbsp;<font size="2">未修改    </font>
								--->
							 	  <#if step9Item.result == 1>修改通过
				                 <#elseif step9Item.result == 2>修改未通过
				                 <#elseif step9Item.result == 0>未修改
				                 </#if> 
							   </td>
		  		        </tr>
		  			</#list>
		  	<#else>
					<tr><td height="30" ></td><td height="30" ></td></tr>
		  			<tr><td height="30" ></td><td height="30" ></td></tr>
		  			<tr><td height="30" ></td><td height="30" ></td></tr>
		  	</#if>
</table>
    </td>
  </tr>
  <tr>
          <td  height="30" class="tab_title" align="right" width="92" style="font-weight:bold;"> <font size="2">编制人</font></td>
          <td  height="30" style=" background-color:#fff;" align="left"> <font size="2">${step6.user.name}</font></td>
          <td  height="30" class="tab_title" align="right" width="92" style="font-weight:bold;"><font size="2">编制日期</font></td>     
          <td  height="30" style="background-color:#fff;" align="left"> <font size="2">${step6.confirmTime}</font></td>       
  </tr>
  <tr>
          <td  height="30" class="tab_title" align="right" width="92" style="font-weight:bold;"><font size="2">审核人</font></td>
          <td  height="30" style=" background-color:#fff;" align="left"> <font size="2">${step9.validateName}</font></td>
          <td  height="30" class="tab_title" align="right" width="92" style="font-weight:bold;"><font size="2">审核日期</font></td>     
          <td  height="30" style="background-color:#fff;" align="left"> <font size="2">${step9.validateDate}</font></td>       
  </tr>
  <tr>
    		<td colspan="4" style="padding-right: 0px; padding-left: 0px;">
    		<table border="1" id="fileUploadTableId" cellspacing="1" style="border-collapse: collapse;border-width:0px; border-style:hidden;" cellpadding="1" >
		  		<tr>
				    <td  width="150" style=" text-align:center;font-weight:bold " height="30"><font size="2">资料类型</font></td>
				    <td width="200" style=" text-align:center; font-weight:bold" height="30"><font size="2">资料名称</font></td>
				    <td width="100" style=" text-align:center;font-weight:bold " height="30"><font size="2">文件字号</font></td>
				    <td width="100" style=" text-align:center;font-weight:bold " height="30"><font size="2">文件作者</font></td>
				    <td width="100" style=" text-align:center;font-weight:bold " height="30"><font size="2">页号</font></td>
				    <td width="150" style=" text-align:center; font-weight:bold" height="30"><font size="2">备注</font></td>
		     	</tr>
		     	<#if annexList && annexList?size!=0>
					   <#list annexList as annex>  
					  		<tr>
							    <td  style="text-align:center;" ><font size="2">${annex.annexType.name }</font></td>
							    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.name }</font></td>
							    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.fileNum}</font></td>
							    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.fileOwner }</font></td>
							    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.filePage }</font></td>
							    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.description}</font></td>
					   		 </tr>
					   </#list>
				<#else>
					  		<tr><td  ></td><td  height="30"></td><td  height="30"></td><td  height="30"></td><td  height="30"></td><td  height="30"></td></tr>
					  		<tr><td  ></td><td  height="30"></td><td  height="30"></td><td  height="30"></td><td  height="30"></td><td  height="30"></td></tr>
					  		<tr><td  ></td><td  height="30"></td><td  height="30"></td><td  height="30"></td><td  height="30"></td><td  height="30"></td></tr>
				</#if>
</table></td>
</tr>
          </table>
		</div>
		</br>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>

</html>
