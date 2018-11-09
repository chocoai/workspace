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
	</br></br>
		<div id="print" align="center" class="table-d">
			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				成果文件编制
			</div>
		      <table border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse;" width="800" >
  	<!-- <tr>
    	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">成果文件编制</td>
    </tr> -->
    <tr>
	    <td class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">工程名称</font></td>
	    <td colspan="3" width="34%" style="background:#fff;" align="left">
	    <font size="2">${project.name}</font>
	    </td>
  </tr>
  <tr>
	    <td class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">委托单位</font></td>
	    <td width="34%" style="background:#fff;" align="left" height="30"><font size="2">${project.customer.cusName}</font></td>
	    <td width="16%" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">编审类型</font></td>
	    <td width="35%" style="background:#fff;" align="left" height="30"><font size="2">${project.editorialType.name}</font></td>
  </tr>
  <tr>
	    <td class="tab_title" align="right"><font size="2" style="font-weight:bold;" height="30">工程编号</font></td>
	    <td width="34%" style="background:#fff;" align="left" height="30"><font size="2">${project.no}</font></td>
	    <td width="16%" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">服务类别</font></td>
	    <td width="35%"  style="background:#fff;" align="left" height="30"><font size="2">${project.serviceType.name}</font></td>
  </tr>
  <tr>
    <td colspan="4" style="padding-right: 0px; padding-left: 0px;" >
    <table border="1" cellspacing="1" cellpadding="1" style="border-collapse: collapse;border-width:0px; border-style:hidden;">
        <tr>
            <td colspan="7" style="font-weight:bold;  text-align:center;" height="30"> 
         		 <font size="2">  附件资料</font>
            </td>
  </tr>
  <tr>
	    <td width="100" style=" text-align:center;font-weight:bold; " height="30"><font size="2">资料类型</font></td>
	  	<td width="175" style=" text-align:center;font-weight:bold;  " height="30"><font size="2">文件</font></td>
	    <td width="175" style=" text-align:center;font-weight:bold;  " height="30"><font size="2">资料名称</font></td>
	    <td width="75" style=" text-align:center;font-weight:bold;  " height="30"><font size="2">文件字号</font></td>
	    <td width="75" style=" text-align:center;font-weight:bold;  " height="30"><font size="2">文件作者</font></td>
	    <td width="75" style=" text-align:center;font-weight:bold;  " height="30"><font size="2">页号</font></td>
	    <td width="125" style=" text-align:center;font-weight:bold;  " height="30"><font size="2">备注</font></td>
  </tr>
  <#if annexList && annexList?size!=0>
	   <#list annexList as annex>  
	  <tr>
		    <td  style="text-align:center;background:#fff;" height="30"><font size="2">${annex.annexType.name}</font></td>
		    <td  style="text-align:center;background:#fff;" height="30" ><font size="2">${annex.name }</font></td>
		    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.name }</font></td>
		    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.fileNum}</font></td>
		    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.fileOwner}</font></td>
		    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.filePage }</font></td>
		    <td style="text-align:center;background:#fff;" height="30"><font size="2">${annex.description}</font></td>
	   </tr>
	   </#list>
	   <#else>
			  <tr>
				    <td height="30"></td><td height="30" ></td><td height="30"></td>
				    <td height="30"></td>
				    <td height="30"></td><td height="30"></td><td height="30"></td>
			   </tr>
			  <tr>
				    <td height="30"></td><td height="30" ></td><td height="30"></td>
				    <td height="30"></td>
				    <td height="30"></td><td height="30"></td><td height="30"></td>
			   </tr>
			  <tr>
				    <td height="30"></td><td height="30" ></td><td height="30"></td>
				    <td height="30"></td>
				    <td height="30"></td><td height="30"></td><td height="30"></td>
			   </tr>
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