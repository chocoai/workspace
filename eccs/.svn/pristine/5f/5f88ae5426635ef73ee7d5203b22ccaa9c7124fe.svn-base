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
		.xl63 {
				mso-style-parent: style0;
				font-size: 9.0pt;
				font-weight: 700;
				border-top: 1pt solid windowtext;
				border-right:1.5pt solid windowtext;
				border-left: 1.5pt solid windowtext;
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
		     <table border="0" cellspacing="0" cellpadding="0" width="800px" class="input_table"  style="border-bottom: 1.5pt solid windowtext;">
					 <tr>
					<td colspan="6"  height=48 class=xl69 width=660
											style='height: 48.0pt; width: 600pt'>发票信息表</td>
					</tr>
					
 					<!-- <tr height=26>
					<td colspan="6"  class=xl63 style="border-top: 1.5pt solid windowtext;"><p style="margin-left:35px;"> 项目信息</p></td>
					</tr> -->
  	         
                                       <tr height=26>
                                         <td width="120px"  class=xl64 align="right" style="font-weight: 700;">项目编号</td>
                                         <td  colspan="2" class=xl65 align="left">
												${invoice.project.no}
                                         </td>
                                         <td  width="120px"class=xl65  align="right"style="font-weight: 700;">实际应收总额(元)</td>
                                         <td  colspan="2" class=xl66  align="left" >
                                               ${pay}
                                         </td>
                                       </tr>
                                       
                                       <tr height=26>
                                         <td  align="right" class=xl64 style="font-weight: 700;">项目名称</td>
                                         <td colspan="5"  class=xl66 align="left"  >
                                          ${invoice.project.name}
                                         </td>
                                       </tr>
                                       
                                        <tr height=26>
                                         <td width="120" class=xl64 align="right" style="font-weight: 700;">累计已开票(元)</td>
                                         <td colspan="2"  class=xl65 align="left">
												${aay}
                                         </td>
                                         <td  width="120"  class=xl65 align="right" style="font-weight: 700;">未开票总额(元)</td>
                                         <td colspan="2" class=xl66 align="left"  >
                                           ${bay}
                                         </td>
                                       </tr>
                                       
                                       <tr height=26>
										<td colspan="6"  class=xl63 style="font-weight: 700;"><p style="margin-left:35px;"> 发票信息</p></td>
										</tr>
                                       
                                       <tr height=26>
                                         <td  align="right" class=xl64 style="font-weight: 700;">付款单位</td>
                                         <td colspan="5"  class=xl66 align="left"  >
                                          ${invoice.payCompany}
                                         </td>
                                       </tr>
                                       
                                       <tr height=26>
                                         <td width="120"  align="right" class=xl64 style="font-weight: 700;">发票编号</td>
                                         <td  colspan="2"   class=xl65 align="left" >
												${invoice.invoiceNo}
                                         </td>
                                         <td  width="120"  align="right" class=xl65 style="font-weight: 700;">发票类型</td>
                                         <td  colspan="2"  class=xl66 align="left"  >
                                             <#if invoice.invoiceType ==1 >普通发票</#if>
											<#if invoice.invoiceType ==2 >增值税专用发票</#if>
											<#if invoice.invoiceType ==3>定额发票</#if>
                                         </td>
                                       </tr>
                                       
                                       <tr height=26>
                                         <td width="120"  align="right" class=xl64 style="font-weight: 700;">开票金额(元)</td>
                                         <td colspan="2" class=xl65 align="left">
												${invoice.invoiceAmount}
                                         </td>
                                         <td  width="120" align="right" class=xl65 style="font-weight: 700;">开票日期</td>
                                         <td  colspan="2"  class=xl66 align="left"  >
                                           		<#if invoice.invoiceDate!=''>${invoice.invoiceDate?string('yyyy-MM-dd')}</#if>
                                         </td>
                                       </tr>
                                       
                                       <tr height="60px;">
                                         <td  align="right" class=xl64 style="font-weight: 700;">发票内容</td>
                                         <td colspan="5" class=xl66  align="left" >
                                        		 ${invoice.invoiceContent}
                                         </td>
                                       </tr>
                                       
                                       <tr height="60px;">
                                         <td  align="right" class=xl64 style="font-weight: 700;">备注</td>
                                         <td colspan="5"  class=xl66   align="left" >
                                          		${invoice.remark}
                                         </td>
                                       </tr>
                                       
                                      <tr height=26>
                                         <td width="120" align="right" class=xl64 style="font-weight: 700;">发票领用人</td>
                                         <td  colspan="2"   class=xl65  align="left" >
												${invoice.invoiceUser.name}
                                         </td>
                                         <td  width="120"  align="right" class=xl65 style="font-weight: 700;">领用日期</td>
                                         <td  colspan="2" class=xl66   align="left" >
                                          		<#if invoice.userDate!=''>${invoice.userDate?string('yyyy-MM-dd')}</#if>
                                         </td>
                                       </tr>
          </table>
		</div>
		<br/>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
	</body>
</html>
