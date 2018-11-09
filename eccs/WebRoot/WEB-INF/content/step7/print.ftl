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
				造价咨询项目校对表
			</div>
	 <table border="1" cellspacing="0" cellpadding="0" width="800" style="border-collapse: collapse;">
	  <!-- <tr>
	    	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目校对表</td>
	  </tr> -->
	  <tr>
		    <td width="92" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">项目名称</font></td>
		    <td style="background-color:#fff;" align="left" width="309">
		    <font size="2">${project.name}</font>
			</td>
		    <td align="right" width="92" style="font-weight:bold;" height="30"><font size="2">项目编号</td>
		    <td style="background-color:#fff;" align="left" width="309">
		     <font size="2">${project.no }</font>
			</td>
	  </tr>
  <tr>
   		 <td colspan="4" style="padding-right: 0px; padding-left: 0px;" >
	 <table border="1" id="step7ItemId" cellspacing="1" cellpadding="1" style="border-collapse: collapse;border-width:0px; border-style:hidden;" width="100%">
	 	<tr>
	    	<td width="50%" height="30" style=" text-align:center;  background-color:#fff;font-weight:bold;"><font size="2">主要问题</font></td>
	    	<td width="50%" height="30" style=" text-align:center;  background-color:#fff;font-weight:bold;"><font size="2">修改及执行情况</font></td>
	   </tr>
	   <#if step7ItemList && step7ItemList?size!=0>
			  <#list step7ItemList as step7Item>
			  		<tr>
					    <td height="30" style=" text-align:center;background-color:#fff; word-break: break-all;"><font size="2">${step7Item.content}</td>
					    <td height="30" style=" text-align:center;background-color:#fff;">
						 	  <#if step7Item.result == 1>修改通过
			                 <#elseif step7Item.result == 2>修改未通过
			                 <#elseif step7Item.result == 0>未修改
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
          <td  height="30" class="tab_title" align="right" style="font-weight:bold;"> <font size="2">编制人</font></td>
          <td  height="30" style=" background-color:#fff;" align="left"> <font size="2"> ${step6.user.name}</font></td>
          <td  height="30" class="tab_title" align="right" width="92" style="font-weight:bold;"><font size="2">编制日期</font></td>     
          <td  height="30" style="background-color:#fff;" align="left"><font size="2"> ${step6.confirmTime}</font></td>       
  </tr>
  <tr>
          <td  height="30" class="tab_title" align="right" style="font-weight:bold;"> <font size="2">校对人</font></td>
          <td  height="30" style=" background-color:#fff;" align="left"> <font size="2">${step7.validateName}</font></td>
          <td  height="30" class="tab_title" align="right" width="92" style="font-weight:bold;"><font size="2">校对日期</font></td>     
          <td  height="30" style="background-color:#fff;" align="left"> <font size="2">${step7.validateDate}</font></td>       
   </tr>
          </table>
		</div>
		</br>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
	</body>
</html>