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
			 <table border="1" cellspacing="0" cellpadding="0" width="800" style="border-collapse: collapse;">
			 	  <div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">任务分配表</div>
			      <tr>
				         <td   style="text-align:right;width:25%; font-weight:bold;"  height="30"><font size="2">项目名称</font></td>
				         <td style="text-align:left;" colspan="3"><font size="2"> ${project.name}</font></td>
			      </tr>
			      <tr>
				         <td style="text-align:right;width:25%; font-weight:bold;"  height="30"><font size="2">项目编号</td>
				         <td style="text-align:left;" colspan="3"><font size="2"> ${project.no }</font></td>
			      </tr>
			      <tr>
				         <td  style=" text-align:right; font-weight:bold;"  height="30"><font size="2">有关操作</font></td> 
				         <td  style=" text-align:center; font-weight:bold;"  height="30"><font size="2">姓名</font></td>
				         <td  style=" text-align:center;font-weight:bold;"  height="30"><font size="2">执业资格</font></td>
				         <td  style=" text-align:center;font-weight:bold;"  height="30"><font size="2">执业资格注册编号</font></td>
			      </tr>
			    <#list swtList as template>  
			      <tr>
			        <td  height="30" style=" text-align:right; font-weight:bold;" class="red"><font size="2">${template.proceStepDef.stepName}</font></td>
			        <td  height="30" style=" text-align:left;"> 
			        <font size="2">${template.step3Worker.workUserName}</font></td>
			        <td  height="30" style=" text-align:left;"><font size="2">${template.step3Worker.workLevel}</font></td>
			        <td   height="30" style=" text-align:left;"><font size="2">${template.step3Worker.workLevelNo}</font></td>
			      </tr>
 			    </#list>
			</table>
		</div>
		</br>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>

</html>
