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
				造价咨询项目征询意见回访记录表
			</div>
		     <table border="1" cellspacing="0" width="800" cellpadding="0" style="border-collapse: collapse;">
              <!-- <tr>
               		<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目征询意见回访记录表</td>
              </tr> -->
              <tr>
	                <td width="17%" class="tab_title" align="right" style="font-weight:bold;"  height="30"><font size="2">项目名称</font></td>
	                <td width="25%" style="background-color:#fff;" align="left"  height="30"><font size="2">${project.name}</font></td>
	                <td width="17%" class="tab_title" align="right" style="font-weight:bold;"  height="30"><font size="2">项目编号</font></td>
	                <td width="41%" style="background-color:#fff;" align="left"  height="30"><font size="2">${project.no }</font></td>
              </tr>
              <tr>
	                <td class="tab_title" align="right" style="font-weight:bold;"  height="30"><font size="2">回访对象</font></td>
	                <td style="background-color:#fff;" align="left"  height="30"><font size="2">${step13.visitObject}</font></td>
	                <td class="tab_title" align="right" style="font-weight:bold;"  height="30"><font size="2">服务内容</font></td>
	                <td style="background-color:#fff;" align="left"  height="30"><font size="2">${step13.serviceContent}</font></td>
              </tr>
              <tr>
	                <td colspan="2" style=" text-align:center; font-weight:bold;"  height="30"><font size="2">征询意见主要内容</font></td>
	                <td colspan="3" style=" text-align:center; font-weight:bold; "  height="30"><font size="2">满意度</font></td>
              </tr>
         <#if step13ItemList &&step13ItemList?size!=0>     
			    <#list step13ItemList as step13Item>
				  <tr>
				    <td  colspan="2"  height="30" style=" text-align:center; background-color:#fff;;"><font size="2">${step13Item.content}</font></td>
				    <td colspan="3"  height="30" style="background-color:#fff;"><input name="radio${ step13Item_index}" disabled="disabled" type="radio"  <#if step13Item.result == 1>checked="checked"</#if>   />&nbsp;<font size="2">很满意</font>
		                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}"  type="radio" disabled="disabled"  <#if step13Item.result == 2>checked="checked"</#if> />&nbsp;<font size="2">满意   </font>   
		                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}" type="radio" disabled="disabled"  <#if step13Item.result == 3>checked="checked"</#if> />&nbsp;<font size="2">一般</font>
		                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}"  type="radio" disabled="disabled"  <#if step13Item.result == 4>checked="checked"</#if> />&nbsp;<font size="2">不满意     </font> 
		                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}" type="radio" disabled="disabled"  <#if step13Item.result == 5>checked="checked"</#if> />&nbsp;<font size="2">很不满意</font></td>
				  </tr>
			  </#list>
	  	<#else>
	  	 		<tr><td  colspan="2"  height="30"></td><td colspan="3"  height="30" ></td></tr>
	  	 		<tr><td  colspan="2"  height="30"></td><td colspan="3"  height="30" ></td></tr>
	  	 		<tr><td  colspan="2"  height="30"></td><td colspan="3"  height="30" ></td></tr>
	  	</#if>
          <tr>
                <td rowspan="2" class="tab_title" style=" text-align:right; font-weight:bold;"><font size="2">征询单位评价意见</font></td>
                <td colspan="3" style="background-color:#fff;" valign="top" align="left">
		               <div   style="min-height: 100px;">${step13.summaryContent}</div>
	            </td>
          </tr>
          <tr>
                <td colspan="2" style="font-weight: bold;"  align="left" height="30">
	             	<font size="2">项目负责人&nbsp;&nbsp;${step13.masterName}</font>
	            </td>
                <td style="font-weight: bold;"  align="left">
	             	<font size="2">日期&nbsp;&nbsp;${step13.masterSignDate}</font>
	            </td>
          </tr>
          <tr>
                <td rowspan="2"  class="tab_title" align="right"><font size="2" style="font-weight:bold;">完善措施</font></td>
                <td colspan="3"  style="background-color:#fff;" valign="top" align="left">
	               	 <div   style="min-height: 100px;">${step13.perfectContent} </div>
	            </td>
          </tr>
          <tr>
                <td colspan="2" style="font-weight: bold;"  align="left" height="30">
	             	<font size="2">项目负责人&nbsp;&nbsp;${step13.techName}</font>
	            </td>
                <td style="font-weight: bold;"  align="left">
	             	<font size="2">日期 &nbsp;&nbsp;${step13.techSingDate}</font>
	            </td>
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
