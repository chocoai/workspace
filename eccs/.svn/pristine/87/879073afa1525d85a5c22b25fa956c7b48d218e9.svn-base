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
		<div id="print" align="center" class="table-d">
			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				征求意见稿发出确认签收单
			</div>
        <table border="1" cellspacing="0" cellpadding="0" width="800" style="border-collapse: collapse;">
             <!-- <tr>
                <td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">征求意见稿发出确认签收单</td>
             </tr> -->
             <tr>
                <td width="25%" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">项目名称</font></td>
                <td colspan="3" align="left" height="30" width="75%">
	    			<font size="2">${project.name}</font>
				</td>
            </tr>
            <tr>
                <td class="tab_title red" height="30" align="right" style="font-weight:bold;"><font size="2">文件名称</font></td>
                <td align="left" height="30" width="25%"><font size="2">${step10.fileName}</font></td>
                <td width="25%" height="30" class="tab_title red" align="right" style="font-weight:bold;"><font size="2">主送单位及部门</font></td>
                <td align="left" height="30" width="25%"><font size="2">${step10.receiverDeptName}</font></td>
            </tr>
            <tr>
                <td height="30" class="tab_title red" align="right" style="font-weight:bold;"><font size="2">报告份数</font></td>
                <td height="30" align="left"><font size="2">${step10.fileCount}</font></td>
                <td  height="30" class="tab_title" align="right"  style="font-weight:bold;"><font size="2">文号</font></td>
                <td  height="30" align="left"><font size="2">${step10.fileNo}</font></td>
            </tr>
          <tr>
                <td class="tab_title red" align="right" style="font-weight:bold;"><font size="2">有关情况说明</font></td>
                <td colspan="3"  valign="top" align="left">
                <div   style="min-height: 100px;font-size:12px;">${step10.description }</div>
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
           <tr>
                <td class="tab_title" align="right" style="font-weight:bold;"><font size="2">意见</font></td>
                <td colspan="3"  valign="top" align="left" height="30">
                 <div   style="min-height: 100px;font-size:12px;">${step10.receiverView }</div>
             		<font size="2" style="font-weight:bold;">签收方式：</font>
			            <#if step10.receiverType== 1><font size="2">签名</font></#if>  
			            <#if step10.receiverType== 2><font size="2">电邮</font></#if> 
			            <#if step10.receiverType== 3><font size="2">电话</font></#if>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			       	<font size="2" style="font-weight:bold;">签收人：</font>
             			<font size="2">${step10.receiverUserName}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            		<font size="2" style="font-weight:bold;">签收日期：</font><font size="2">${step10.receiverDate}</font></td>
            </tr>
          </table></br>
      <div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			(征求意见稿)意见反馈表
		</div>
      <table border="1" cellspacing="0" cellpadding="0" width="800" style="border-collapse: collapse;">
      <!-- <tr>
	        <td width="700" colspan="2" style=" font-weight:bold; font-size:20px; text-align:center;">(征求意见稿)意见反馈表</td>
        </tr> -->
       <tr>
	        <td  height="30" width="50%" style=" text-align:center;font-weight:bold;"><font size="2">反馈意见内容</font></td>
	        <td  height="30"  width="50%" style=" text-align:center;font-weight:bold;"><font size="2">理由或依据</font></td>
      </tr>
      <#if step10FKYJList && step10FKYJList?size!=0>
         <#list  step10FKYJList as fkyj >
		  <tr >
		    <td  height="30" style="background-color:#fff; text-align:center;"><font size="2">${fkyj.content}</font></td>
		    <td  height="30" style="background-color:#fff; text-align:center;"><font size="2">${fkyj.reason}</font></td>
		  </tr>
		</#list>
		<#else>
		<tr ><td  height="30" ></td><td  height="30"></td></tr>
		<tr ><td  height="30" ></td><td  height="30"></td></tr>
		<tr ><td  height="30" ></td><td  height="30"></td></tr>
		</#if>
		
    </table> </br>
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			(征求意见稿)答复意见表
		</div>
      <table border="1" cellspacing="0" id="zxyjid" cellpadding="0" width="800" class="input_table2" style="border-collapse: collapse;">
	      <!-- <tr>
		        <td  colspan="3" style=" font-weight:bold; font-size:20px; text-align:center;">(征求意见稿)答复意见表</td>
	   	 </tr> -->
	  	 <tr>
	       		<td height="30"  width="25%" style=" text-align:center; word-break: break-all;font-weight:bold;"><font size="2">参阅规范或条款编号</font></td>
		        <td height="30"  width="45%" style=" text-align:center; word-break: break-all;font-weight:bold;"><font size="2">修改意见内容</font></td>
		        <td height="30"  width="35%" style=" text-align:center; word-break: break-all;font-weight:bold;"><font size="2">理由或依据</font></td>
	      </tr>
	      <#if step10DFYJList && step10DFYJList?size!=0>
		      	<#list  step10DFYJList as dfyj >
						  <tr >
							    <td  height="30" style="background-color:#fff; text-align:center; word-break: break-all;"><font size="2">${dfyj.num}</font></td>
							    <td height="30" style="background-color:#fff; text-align:center; word-break: break-all;"><font size="2">${dfyj.content}</font></td>
							    <td height="30" style="background-color:#fff; text-align:center; word-break: break-all;"><font size="2">${dfyj.reason}</font></td>
						  </tr>
				</#list>
		<#else>
				<tr ><td  height="30" ></td><td  height="30"></td><td  height="30"></td></tr>
				<tr ><td  height="30" ></td><td  height="30"></td><td  height="30"></td></tr>
				<tr ><td  height="30" ></td><td  height="30"></td><td  height="30"></td></tr>
		</#if>
			<tr height="50px">
					<td "  colspan="4" style="text-align: left;">
						<span style="font-weight:bold;"><font size="2">是否通过：</font></span>
			        	<font size="2"><#if step10.status==-1>不通过<#elseif step10.status==1>通过<#else>&nbsp;</#if></font>
			        </td>
		      </tr>
    </table> 
    </table>
		</div>
		</br>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>

</html>
