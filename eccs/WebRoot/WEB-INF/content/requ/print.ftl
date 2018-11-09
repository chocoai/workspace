<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<meta name=ProgId content=Excel.Sheet>
		<meta name=Generator content="Microsoft Excel 12">
		<script type="text/javascript" src="../../../js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="../../../js/jQuery.print.js"></script>
		<style>
			table tr td{padding-left: 8px;padding-right: 8px;}
			.xl69 {
			mso-style-parent: style0;
			font-size: 16.0pt;
			font-weight: 700;
			font-family: 黑体, monospace;
			mso-font-charset: 134;
			text-align: center;
		}
			.xl64 {
				mso-style-parent: style0;
				font-size: 9.0pt;
				border-top: 1pt solid windowtext;
				border-right: 1pt solid windowtext;
				border-left: 1.5pt solid windowtext;
			}
			.xl65 {
				mso-style-parent: style0;
				font-size: 9.0pt;
				border-top: 1pt solid windowtext;
				border-right: 1pt solid windowtext;
			}
			.xl66 {
				mso-style-parent: style0;
				font-size: 9.0pt;
				border-top: 1pt solid windowtext;
				border-right: 1.5pt solid windowtext;
			}					
		table{table-layout: fixed;}
		td(word-break: break-all; word-wrap:break-word;)
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
		<div id="print" align="center">
		     <table border="0" cellspacing="0" cellpadding="0" width="800px" style="border-bottom: 1.5pt solid windowtext;margin-top:20px">
    	<tr style="mso-height-source: userset; height: 60px">
		    <td colspan="7" height=48 class=xl69 width=660
				style='height: 32.0pt; width: 600pt'>
				请款申请单
				</td>
    	</tr>
	  <tr  height=26>
	    <td colspan="1" width="200px"   align="right" class="xl64" style="font-weight: 700;border-top: 1.5pt solid windowtext;">项目名称 </td>
	    <td colspan="2"   align="left" class="xl65" style="border-top: 1.5pt solid windowtext;">${project.name}</td>
	    <td colspan="2"  align="right"  class="xl65"style="font-weight: 700;border-top: 1.5pt solid windowtext;">付款单位 </td>
	    <td colspan="2"   align="left" class="xl66" style="border-top: 1.5pt solid windowtext;">${requisition.payCustomerName}</td>
	  </tr>
    <tr  height=26>
    <td colspan="1"  align="right" class="xl64"style="font-weight: 700;">合同编号 </td>
    <td colspan="2" class="xl65" >${requisition.contacton}</td>
    <td colspan="2"  align="right" class="xl65"style="font-weight: 700;">报告编号 </td>
    <td colspan="2"  class="xl66">${requisition.reportNo }</td>
  </tr>
  <tr  height=26>
    <td colspan="1"  align="right" class="xl64"style="font-weight: 700;">请款类别</td>
    <td colspan="2" class="xl65" >
		    <#list requisitionTypeList as requisitionType>
				   <#if requisition.requisitionTypeIds != null && requisition.requisitionTypeIds?contains('${requisitionType.id}') >
				     ${requisitionType.name}
				   </#if> 
		   </#list>  
     </td>
	    <td colspan="2"  align="right"class="xl65" style="font-weight: 700;">咨询类型</td>
	       <td colspan="2"  class="xl66">
			   <#list serviceTypeList as serviceType>
	       			<#if requisition.serviceIds != null && requisition.serviceIds?contains('${serviceType.id}') >
			        ${serviceType.name}
			       </#if>
			  </#list>	
           </td>

  </tr>
      <tr>
    <td colspan="1" align="right" class="xl64"style="font-weight: 700;">服务内容</td>
     <td colspan="6" class="xl66" style="padding-right: 0px; padding-left: 0px;">
    <table style="mso-style-parent: style0;font-size: 9.0pt;" >
     <tr colspan="4" height=26 >
	     <td><input  name="requisition.serviceContentIds"  disabled    <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("a") >checked='checked'</#if>type="checkbox" value="a" />&nbsp;规划咨询</td>
	     <td> <input  name="requisition.serviceContentIds"  disabled    <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("b") >checked='checked'</#if> type="checkbox" value="b"/>&nbsp;项目建议书</td>
	     <td> <input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("c") >checked='checked'</#if> type="checkbox" value="c"/>&nbsp;可行性研究报告</td>
	     <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("d") >checked='checked'</#if> type="checkbox" value="d"/>&nbsp;项目申请报告</td>        
    </tr>
     <tr colspan="4"  height=26>
         <td> <input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("e") >checked='checked'</#if> type="checkbox" value="e"/>&nbsp;资金申请报告</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("f") >checked='checked'</#if> type="checkbox" value="f"/>&nbsp;评估咨询</td>
        <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("g") >checked='checked'</#if> type="checkbox" value="g"/>&nbsp;节能评估</td>
        <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("h") >checked='checked'</#if> type="checkbox" value="h"/>&nbsp;工程项目管理</td>
    </tr>
     <tr colspan="4"  height=26>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("i") >checked='checked'</#if> type="checkbox" value="i"/>&nbsp;项目可能性研究投资估算编制 </td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("10") >checked='checked'</#if> type="checkbox" value="10"/>&nbsp;项目可能性研究投资估算审核 </td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("11") >checked='checked'</#if> type="checkbox" value="11"/>&nbsp;项目经济评价 </td>
         <td></td>   
      </tr>    
      <tr colspan="4"  height=26>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("12") >checked='checked'</#if> type="checkbox" value="12"/> &nbsp;概算编制</td>
        <td> <input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("13") >checked='checked'</#if> type="checkbox" value="13"/>&nbsp;预算编制</td>
        <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("14") >checked='checked'</#if> type="checkbox" value="14"/>&nbsp;结算编制</td>
        <td> <input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("15") >checked='checked'</#if> type="checkbox" value="15"/>&nbsp;竣工结算编制</td>
      </tr> 
      <tr colspan="4"  height=26>
         <td> <input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("16") >checked='checked'</#if> type="checkbox" value="16"/>&nbsp;概算审核</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("17") >checked='checked'</#if> type="checkbox" value="17"/>&nbsp;预算审核</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("18") >checked='checked'</#if> type="checkbox" value="18"/> &nbsp;结算审核</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("19") >checked='checked'</#if> type="checkbox" value="19"/>&nbsp;竣工结算审核</td>
    </tr>
    <tr colspan="4"  height=26>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("20") >checked='checked'</#if> type="checkbox" value="20"/>&nbsp;工程清单编制</td>
          <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("21") >checked='checked'</#if> type="checkbox" value="21"/> &nbsp;招标控制价编制</td>
          <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("22") >checked='checked'</#if> type="checkbox" value="22"/>&nbsp;招标标底编制</td>
          <td> <input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("23") >checked='checked'</#if> type="checkbox" value="23"/>&nbsp;投标报价编制</td>
    </tr>
   <tr colspan="4"  height=26>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("24") >checked='checked'</#if> type="checkbox" value="24"/>&nbsp;工程清单审核</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("25") >checked='checked'</#if> type="checkbox" value="25"/>&nbsp;招标控制价审核</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("26") >checked='checked'</#if> type="checkbox" value="26"/>&nbsp;招标标底审核</td>
         <td> <input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("27") >checked='checked'</#if> type="checkbox" value="27"/>&nbsp;投标报价审核</td>
    </tr>
   <tr colspan="4"  height=26>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("28") >checked='checked'</#if> type="checkbox" value="28"/>&nbsp;工程洽谈、变更及合同争议的鉴定</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("29") >checked='checked'</#if> type="checkbox" value="29"/>&nbsp;索赔</td>
         <td></td>
         <td></td>
    </tr>
   <tr colspan="4"  height=26>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("30") >checked='checked'</#if> type="checkbox" value="30"/>&nbsp;编制工程造价计价依据</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("31") >checked='checked'</#if> type="checkbox" value="31"/>&nbsp;造价监控及提供造价信息等</td>
         <td><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("32") >checked='checked'</#if> type="checkbox" value="32"/>&nbsp;项目实施阶段造价跟踪控制</td>
         <td></td>
    </tr>
    <tr  height=26>
         <td colspan="4"><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("33") >checked='checked'</#if> type="checkbox" value="33"/>&nbsp;编制及发布招标公告及招标文件、组织踏勘及答疑、组织开评标会议、签发中标通知书、整理招标汇编资料等</td>
    </tr>
    <tr  height=26>
         <td colspan="4"><input  name="requisition.serviceContentIds"  disabled   <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("34") >checked='checked'</#if> type="checkbox" value="34"/>&nbsp;土地整治可研、规划设计与预算编制</td>
    </tr>
    </table>
     </td>
  </tr>
  <tr>
    <td  rowspan="2" colspan="1"  align="right" class="xl64" style="font-weight: 700;">计费方法及申请金额 </td>
    <td colspan="1" align="right" class="xl65"style="font-weight: 700;"  height=26>计费方式</td>
    <td colspan="5"   align="left" class="xl66">${requisition.payType }</td>
    </tr>
  <tr>
    <td colspan="1"  align="right" class="xl65"style="font-weight: 700;"  height=26>申请金额(万元)</td>
    <td  colspan="5"   align="left" class="xl66">${requisition.amount }</td>
  </tr>
  
<tr height=60 >
    <td colspan="1"  align="right" class="xl64" style="font-weight: 700;">收款单位银行信息 </td>
    <td colspan="6" class="xl66" >
 		<div name="requisition.bankInfo" cols="45" rows="6" style=" width:100%; margin-top:5px; margin-bottom:5px;"  align="left">${requisition.bankInfo}</div>
	</td>
    
</tr>

<tr  height=26>
    <td colspan="7" class="xl64" style="border-right: 1.5pt solid windowtext; text-align:center;">如需增值税专用发票请如实提供贵单位开专票信息</td>
</tr>
 <tr  height=26>
    <td colspan="1"  align="right" class="xl64" style="font-weight: 700;">经办人：</td>
    <td colspan="1"   align="left" class="xl65">${requisition.recordUserName }</td>
    <td colspan="1"  align="right"  class="xl65" style="font-weight: 700;">申请日期：</td>
    <td colspan="1"   align="left" class="xl65">${requisition.requisitionDate}</td>
    <td colspan="1"  align="right" class="xl65"  style="font-weight: 700;">请款单位：</td>
    <td colspan="2"   align="left" class="xl66">${requisition.requisitionDeptName }</td>
    </tr>
          </table>
		</div>
		<br/>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
	</body>
</html>
