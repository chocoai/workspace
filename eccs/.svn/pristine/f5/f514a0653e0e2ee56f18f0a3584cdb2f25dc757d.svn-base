<!DOCTYPE html>
﻿<html>
	<head>
		<title> 工程咨询云工作平台 </title>
		<script type="text/javascript" src="../../../js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="../../../js/jQuery.print.js"></script>
			<style>
			.xl69 {
			mso-style-parent: style0;
			font-size: 16.0pt;
			font-weight: 700;
			font-family: 黑体, monospace;
			mso-font-charset: 134;
			text-align: center;
		}

		table{
		 table-layout:fixed;word-wrap:break-word;
		  border-collapse:collapse;
		  }
		table, td{
		mso-style-parent: style0;
		font-size: 9.0pt;
 		 border:1px solid black;
  		}
		</style>
		<script>
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
		        <p height=48 class=xl69 > 发票信息统计表        
		                                      
                       <table align="center"  style="border:2px solid windowtext;"  width="1100">
                                       <tr height=30>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;width:40px;"; >序号 </td>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;">项目编号</td>
                                          <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;">项目名称</td>                                        
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;">实际应收总额(元)</td>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;">累计已开票(元)</td>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;">未开票总额(元)</td>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;width:100px;">发票编号</td>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;">开票金额(元)</td>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;width:80px;" ;>开票日期</td>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;width:80px;">收费状态</td>
                                         <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;">项目负责人</td>
                                       <td style="  text-align:center;background-color:#e6f6ff;font-weight:700;width:70px;">状态</td>
                                       </tr>
                                       <#if list?size!=0>
                            <#list list as invoice>
                                       <tr height=26>
                                         <td  style="text-align:center;">${invoice_index + 1}</td>
                                         <td  >${invoice.project.no}</td>
                                          <td >${invoice.project.name}</td>
                                          <td >${invoice.rAccount}</td>
                                          <td >${invoice.cAccount}</td>
                                          <td >${invoice.wAccount}</td>
                                          <td >${invoice.invoiceNo}</td>
                                          <td >${invoice.invoiceAmount}</td>
                                          <td ><#if invoice.invoiceDate!="">${invoice.invoiceDate?string('yyyy-MM-dd')}</#if></td>
                                          <td >
                                          <#if invoice.reStatus ==1 >未回款</#if>
                                          <#if invoice.reStatus ==2 >已回款</#if>
                                          <#if invoice.reStatus ==3 >部分回款</#if>
                                          </td>                                        
                                          <td >${invoice.project.recordName}</td>
                                         <td  style="text-align:center;">
                                          <#if invoice.fistatu ==1 >正常</#if>
                                          <#if invoice.fistatu ==2 >作废</#if>
                                          </td> 
                					</tr>
   							</#list>
   							</#if>
   							<#if list?size==0>
   							<tr><td style="text-align:center;" colspan="13">暂无数据!</td></tr>
   							</#if>
           </table>
           
           
		</div><br/>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>
</html>
