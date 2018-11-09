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
				成果文件审定签发表
			</div>
		     <table border="1" cellspacing="1" cellpadding="1" width="800" style="border-collapse: collapse;">
              <!-- <tr>
                	<td colspan="5" style=" font-weight:bold; font-size:20px; text-align:center;">成果文件审定签发表</td>
              </tr> -->
              <tr>
	                <td class="tab_title" align="right" width="180" style="font-weight:bold;"  height="30"><font size="2">项目名称</font></td>
	                <td width="80%" colspan="4" style="background-color:#fff;" align="left"  height="30"><font size="2">${project.name}</font></td>
              </tr>
              <tr>
	                <td  class="tab_title" align="right" width="180" style="font-weight:bold;"  height="30"><font size="2">文件名称</font></td>
	                <td  style="background-color:#fff;" align="left" colspan="4"><font size="2"  height="30">${step12.fileName}</font></td>
              </tr>
              <tr>
	                <td class="tab_title" align="right" width="180" style="font-weight:bold;"  height="30"><font size="2">报送部门（项目部）</font></td>
	                <td style="background-color:#fff;" align="left"  height="30" colspan="4"><font size="2">${step12.receiverDeptName}</font></td>
              </tr>
              <tr>
	                <td class="tab_title" rowspan="2" align="right" style="font-weight:bold;"><font size="2">审核</font></td>
	                <td valign="top" align="left" colspan="4">
	                	<div   style="min-height: 100px;"><font size="2">${step12.writerDescription}</font></div>
			        </td>
            </tr>  
             <tr height="30">
	                <td  align="right"  width="20%" style="vertical-align: middle;">
			             	<span style="width:60%;background-color:#fff;"  height="30"><font size="2" style="font-weight:bold;">签名</font></span>
			        </td>
	                <td align="left" width="20%" style="vertical-align: middle;">
			             	<font size="2" style="margin-left: 10px;">${step12.writerName}</font>
			        </td>
	                <td  align="right" width="20%" style="vertical-align: middle;">
			            	<span style="width:40%;background-color:#fff;"  height="30"><font size="2" style="font-weight:bold;"> 日期</font></span>
			        </td>
	                <td  align="left" style="vertical-align: middle;">
	                		<font size="2" style="margin-left: 10px;">${step12.writerDate}</font>
			        </td>
            </tr>  
            <tr>
	                <td class="tab_title" align="right" style="font-weight:bold;" rowspan="2"><font size="2">审定</font></td>
	                <td  align="left" colspan="4" style="vertical-align: middle;">
	              			<div   style="min-height: 100px;">${step12.deptMasterView }</div>
			        </td>
            </tr>
            <tr height="30">
            		 <td  align="right"  width="20%" style="vertical-align: middle;">
			             	<span style="width:60%;background-color:#fff;"  height="30"><font size="2" style="font-weight:bold;">签名</font></span>
			        </td>
	                <td  align="left" width="20%" style="vertical-align: middle;">
			             	<font size="2" style="margin-left: 10px;">${step12.deptMasterName}</font>
			        </td>
	                <td  align="right" width="20%" style="vertical-align: middle;">
			            	<span style="width:40%;background-color:#fff;"  height="30"><font size="2" style="font-weight:bold;"> 日期</font></span>
			        </td>
	                <td  align="left" style="vertical-align: middle;">
	                		<font size="2" style="margin-left: 10px;">${step12.deptMasterDate}</font>
			        </td>
            </tr>  
            <!-- <tr>
	                <td colspan="2" class="tab_title" align="right" style="font-weight:bold;"><font size="2">公司领导审批意见</font></td>
	                <td colspan="3"  valign="top" align="left">
	                	<font size="2">
	                	 <textarea readonly="readonly"  cols="45" rows="5" style="font-size:12px;  width:100%; height="100%;" border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;min-width:380px;">${step12.compMasterView }</textarea>
	                	</font>
			           		  <span style=" float:left; width:60%;background-color:#fff;"  height="30"><font size="2" style="font-weight:bold;"> 签名：</font><font size="2" style="margin-left: 10px;">${step12.compMasterName}</font></span>
			         		  <span style=" float:left;width:40%;background-color:#fff;"  height="30"><font size="2" style="font-weight:bold;"> 日期：</font><font size="2" style="margin-left: 10px;">${step12.compMasterDate}</font></span>
			        </td>
            </tr> -->
            <tr>
                	<td colspan="5" align="left" style="font-weight:bold;"  height="30"><font size="2">说明：对较重要文件的审签，部门负责人应明示相关会签部门，并建议其同步签审。</font></td>
            </tr> 
          </table>
		</div>
		</br>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>

</html>
