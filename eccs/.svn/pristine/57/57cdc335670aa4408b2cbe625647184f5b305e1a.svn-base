<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<meta name=ProgId content=Excel.Sheet>
		<meta name=Generator content="Microsoft Excel 12">
		<script type="text/javascript" src="../../../js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="../../../js/jQuery.print.js"></script>
		<style>
		.table-d table{border:1px solid #000000;border-collapse: collapse}
		.table-d table td{border:1px solid #000000;padding: 0px 5px;}
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
			咨询任务书
		</div>
		     <table border="1" cellspacing="0" cellpadding="0" width="800" style="border-collapse: collapse;" >  
             <!-- <tr>
        	 		<td colspan="7" style="font-weight: bold; font-size: 20px; text-align: center;">咨询任务书</td>
        	 </tr> -->
              <tr >
                     <td width="50" class="tab_title" align="right" height="30" style="font-weight:bold;"><font size="2">项目名称</font></td>
                     <td width="350" colspan="6" style="background-color:#fff;" align="left"><font size="2.5">${project.name}</td>
              </tr>
              <tr>
                       <td class="tab_title red" align="right" height="30" style="font-weight:bold;"><font size="2">咨询类型</font></td>
                       <td colspan="6" style="background-color:#fff;" align="left">
                             <font size="2">${project.serviceType.name}</font>
                        </td>
              </tr>
              <tr>
                        <td class="tab_title red" align="right" height="30" style="font-weight:bold;"><font size="2">编审类型</font></td>
                        <td colspan="6" style="background-color:#fff;" align="left">
					   		 <font size="2">${project.editorialType.name}  </font>                                  
                        </td>
               </tr>
                <tr>
                        <td width="109" class="tab_title" align="right"><font size="2">委托方资料</font></td>
                        <td colspan="6" style="padding: 0px 0px;">
                             <table border="1" cellspacing="1" style="border-collapse: collapse;border-width:0px; border-style:hidden;" cellpadding="1" >
								<tr>
                                     <td colspan="1"style="width: 120px; height: 30px; line-height: 30px; text-align: right;"><font size="2">委托单位名称</font></td>
                                     <td colspan="4" style="background-color:#fff;"><font size="2">${step1.senderUnit }</font></td>
                                </tr>
                                <tr>
                                     <td colspan="1" style="width: 120px; height: 30px; line-height: 30px; text-align: right;font-weight:bold;"><font size="2">详细地址</font></td>
                                     <td colspan="4" style="background-color:#fff;"><font size="2">${step1.senderAddress }</font></td>
                                 </tr>
                                 <tr>
                                     <td width="189" height="30" style=" text-align:center;font-weight:bold;"><font size="2">联系人姓名</font></td>
                                     <td width="256" height="30" style=" text-align:center;font-weight:bold; "><font size="2">手机</font></td>
                                     <td width="221" height="30" style=" text-align:center;font-weight:bold;"><font size="2">办公电话</font></td>
                                     <td width="221" height="30" style=" text-align:center;font-weight:bold;"><font size="2">传真</font></td>
                                     <td width="225" height="30" style=" text-align:center;font-weight:bold; "><font size="2">QQ或Email</font></td>
                                  </tr>
                                 <#if step1ContactList && step1ContactList?size!=0>                                 
		                                <#list step1ContactList as step1Contact>  
		                                   <tr height="30" >
		                                     <td style=" text-align:center;background-color:#fff;"><font size="2">${step1Contact.name }</font></td>
		                                     <td style=" text-align:center;background-color:#fff;"><font size="2">${step1Contact.officeTel }</font></td>
		                                     <td style=" text-align:center;background-color:#fff;"><font size="2">${step1Contact.tel }</font></td>
		                                     <td style=" text-align:center;background-color:#fff;"><font size="2">${step1Contact.fax }</font></td>
		                                     <td style=" text-align:center;background-color:#fff;"><font size="2">${step1Contact.email }</font></td>
		                                   </tr>
		                                </#list>
                                   <#else>
	                                   <tr height="30"><td ></td><td ></td><td ></td><td ></td><td ></td></tr>
	                                   <tr height="30"><td ></td><td ></td><td ></td><td ></td><td ></td></tr>
	                                   <tr height="30"><td ></td><td ></td><td ></td><td ></td><td ></td></tr>
                                   </#if>
                                 </table>
                              </td>
                           </tr>
                     
				  <tr>
						<td rowspan="5" style="text-align: right;font-weight:bold;"><font size="2">具体工作任务</font></td>
						<td
							style="width: 105px; height: 30px; line-height: 30px; text-align: right;font-weight:bold;">
							<font size="2">工期要求</font>
						</td>
						<td colspan="5" style="height: 30px;ext-align: left;background-color:#fff;">
							<#if step1!=null>
							<font size="2">
								${step1.startWorkTime}
			                     至:
			                    ${step1.endWorkTime}
		                    </font>
		                    </#if>
						</td>
					</tr>
					<tr>
						<td
							style="height: 30px; line-height: 30px; text-align: right;font-weight:bold;">
							<font size="2">咨询范围</font>
						</td>
						<td colspan="5" style="height: 30px;  text-align: left;background-color:#fff;">
							<font size="2">${step1.consultRange }</font>
						</td>
					</tr>
					<tr>
						<td style="height: 30px; line-height: 30px; text-align: right;font-weight:bold;">
							<font size="2">质量要求</font>
						</td>
						<td colspan="5" style="height: 30px;text-align: left;background-color:#fff;" >
							<font size="2">${step1.qualityRequirement}</font>
						</td>
					</tr>
					<tr>
						<td style="height: 30px; line-height: 30px; text-align: right;font-weight:bold;">
							<font size="2">其它要求</font>
						</td>
						<td colspan="5" style="height: 30px;text-align: left;background-color:#fff;" >
							<font size="2">${step1.otherRequirements}</font>
						</td>
					</tr>
          <tr>
	            <td colspan="6" style="text-align:left;font-weight:bold;" height="30"><font size="2">相关附件（合同、中标通知书、业主指令等）</font></td>
          </tr>
          <tr>
            <td colspan="7" style="padding: 0px 0px;">
    <table border="1" cellspacing="1" cellpadding="1"   style="border-collapse: collapse;border-width:0px; border-style:hidden;" class="list_table4">
  <tr>
	    <td style=" text-align:center;  background-color:#fff;   font-weight:bold;" height="30" width="300"><font size="2">文件名</font></td>
	    <td style=" text-align:center;  background-color:#fff;   font-weight:bold;" height="30" width="100"><font size="2">文件类型</font></td>
	    <td style=" text-align:center;  background-color:#fff;  font-weight:bold;" height="30" width="80"><font size="2">上传人</font></td>
	    <td style=" text-align:center;  background-color:#fff;   font-weight:bold;" height="30" width="320"><font size="2">上传时间</font></td>
  </tr>
         <#if annexList && annexList?size!=0>  
			    <#list annexList as annex>
					  <tr>
						    <td style=" text-align:center;background-color:#fff;" height="30"><font size="2">${annex.name }</font></td>
						    <td style=" text-align:center;background-color:#fff;" height="30"> <font size="2">${annex.annexType.name }</font></td>
						    <td style=" text-align:center;background-color:#fff;" height="30"><font size="2">${annex.user.name }</font></td>
						    <td style=" text-align:center;background-color:#fff;" height="30"><font size="2">${annex.ctime }</font></td>
					  </tr>
				</#list>
		<#else>
		  <tr height="30">
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
		  </tr>
		  <tr height="30">
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
		  </tr>
		  <tr height="30">
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
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
