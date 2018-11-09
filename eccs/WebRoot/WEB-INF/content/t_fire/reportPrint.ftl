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
$(document).ready(function() {       
  for(var i=0;i<$(".x12").length;i++){
    var he = $(".x12").eq(i).height();//获取左侧的td的高度
    $(".x13").eq(i).css("height", he);//给右侧嵌套table添加属性（高）
    }
});
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
		        <p height=48 class=xl69 > 收款信息统计表        
		                                      
                          <table  align="center"  width="1100" style="border:1.5px solid black; ">
                                       <tr height=26>
                                         <td style=" text-align:center;background-color:#e6f6ff; width:40px;">序号</td>
                                         <td style=" text-align:center;background-color:#e6f6ff;">项目编号</td>
                                          <td style="text-align:center;background-color:#e6f6ff;" >项目名称</td>  
                                         <td style=" text-align:center;background-color:#e6f6ff;">发票编号</td>
                                         <td style=" text-align:center;background-color:#e6f6ff;">付款单位</td>
                                         <td style=" text-align:center;background-color:#e6f6ff;width:90px">开票金额(元)</td>
                                         <td style=" text-align:center;background-color:#e6f6ff;width:90px">合计</td>
                                         <td style=" text-align:center;background-color:#e6f6ff;width:90px">应收金额(元)</td>
                                         <td style=" text-align:center;background-color:#e6f6ff;width:90px">到账金额(元)</td>
                                         <td style=" text-align:center;background-color:#e6f6ff;width:90px">到账日期</td>
                                         <td style=" text-align:center;background-color:#e6f6ff;width:90px">登记人</td>
                                       </tr>
                                       <#if list?size!=0>
                            <#list list as rece>
                                       <tr height=26>
                                       
                                         <td width="30" style="text-align:center;">${rece_index + 1}</td>
                                         <td>${rece.project.no}</td>
                                          <td class="x12">${rece.project.name}</td>
                                          <td >${rece.invoiceNo}</td>
                                          <td >${rece.payCompany}</td>
                                          <td >${rece.invoiceAmount}</td>
                                          <td >${rece.aAccount}</td>
                                          <td style="padding:0px 0px 0px 0px;" colspan="4" valign="top">
                                         <table  class="x13"  border=1 cellspacing=1 cellpadding=1 style="width:100%;height:100%;table-layout:fixed;word-wrap:break-word; border-collapse:collapse; border-style:hidden;">
                                         <#list rece.receivableslist as receivableslist>
                                         <tr height=26>
                                          <td  style="width:25%;" >${receivableslist.receivable}</td>
                                          <td  style="width:25%;" class="hc3">${receivableslist.arrivalAmount}</td>
                                          <td  style="width:25%;" ><#if receivableslist.arrivalDate!="">${receivableslist.arrivalDate?string('yyyy-MM-dd')}</#if></td>
                                          <td  style="width:25%;">${receivableslist.user.name}</td>
                                          </tr>
                                          </#list>
                                          </table>
                                          </td>
                                          
                					</tr>
   							</#list>
   							</#if>
   							<#if list?size==0>
   							<tr><td style="text-align:center;" colspan="12">暂无数据!</td></tr>
   							</#if>
           </table>
           
           
		</div><br/>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>
</html>
