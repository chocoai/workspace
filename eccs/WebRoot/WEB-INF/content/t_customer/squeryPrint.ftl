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
 		 border:1px solid windowtext;
  		}
		</style>
		<script language="JavaScript">
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
		        <p height=48 class=xl69 > 客户信息统计表</p>                                      
               <table  width="1100"  style=" border:2px solid windowtext;">
                                       <tr height=26>
                                         <td   style="text-align:center;background-color:#e6f6ff;  font-weight:700; "  width=40 >序号 </td>
                                         <td   style="text-align:center;background-color:#e6f6ff;  font-weight:700; "  width=68 >客户名称</td>
                                         <td   style="text-align:center;background-color:#e6f6ff;  font-weight:700; "  width=68>单位性质</td>
                                         <td   style="text-align:center;background-color:#e6f6ff;  font-weight:700; "  width=68>单位类别 </td>
                                         <td   style="text-align:center; background-color:#e6f6ff; font-weight:700; " >主页</td>
                                         <td    style="text-align:center;background-color:#e6f6ff;  font-weight:700; "  width=68> 电子邮箱</td>
                                         <td  style="text-align:center; background-color:#e6f6ff; font-weight:700; "  width=68>办公电话</td>
                                         <td   style="text-align:center;background-color:#e6f6ff; font-weight:700; "  width=68>邮编</td>
                                         <td   style="text-align:center; background-color:#e6f6ff; font-weight:700; "  width=68> 传真</td>
                                         <td    style="text-align:center;background-color:#e6f6ff;  font-weight:700; "  width=68>地址</td>
                                         <td   style="text-align:center; background-color:#e6f6ff; font-weight:700; "  width=68>备注</td>
                                         <td    style="text-align:center;background-color:#e6f6ff;  font-weight:700; "  width=68>联系人</td>
                                         <td    style="text-align:center;background-color:#e6f6ff;  font-weight:700; "  width=68>电话</td>
                                         <td    style="text-align:center;background-color:#e6f6ff;  font-weight:700;"  width=68>手机</td>
                                         <td   style="text-align:center;background-color:#e6f6ff;  font-weight:700;"  width=68>qq</td>
                                        <td   style="text-align:center; background-color:#e6f6ff; font-weight:700; "  width=68>E-mail</td>
                                       </tr>
                                       <#if list?size!=0>
                                    <#list list as t_Customer>
                                       <tr height=26>
                                         <td   style=" text-align:center;"  >${t_Customer_index + 1}</td>
                                         <td   style=" text-align:left;"  >${t_Customer.cusName}</td>
                                         <td   style=" text-align:left;"  >
	                     					<#if t_Customer.cusNature ==1 >委托单位</#if>
											<#if t_Customer.cusNature ==2 >建设单位</#if>
											<#if t_Customer.cusNature ==3 >施工单位</#if>
											<#if t_Customer.cusNature ==4 >设计单位</#if>
                                         </td>
                                         <td style=" text-align:left;"  >
	                     					<#if t_Customer.cusType ==1 >企业客户</#if>
											<#if t_Customer.cusType ==2 >政府客户</#if>
											<#if t_Customer.cusType ==3 >其它</#if>
                                         </td>                                        
                                         <td style=" text-align:left;" class="x12">${t_Customer.cusHomepage}</td>
                                         <td style=" text-align:left;"  >${t_Customer.email}</td>
                                         <td style=" text-align:left;"  >${t_Customer.officePhone}</td>
                                          <td style=" text-align:left;"  >${t_Customer.postCode}</td>
                                         <td style=" text-align:left;"  >${t_Customer.fax}</td>
                                         <td style=" text-align:left;"  >${t_Customer.address}</td>
                                         <td style=" text-align:left;"  >${t_Customer.remark}</td>
                                           
      
 					                   <td  colspan="5" valign="top">
                                         <table class=x13  border=1 cellspacing=1 cellpadding=1 style="width:100%;height:100%;table-layout:fixed;word-wrap:break-word; border-collapse:collapse; border-style:hidden;">
                                         <#list t_Customer.contactlist as contacts>
                                         <tr >
                                         <td style="width:20%;" >
                                           ${contacts.contact}
                                           </td>
                                           <td style=";width:20%;" >
                                           ${contacts.phone}
                                           </td>
                                           <td style="width:20%;" >
                                           ${contacts.telephone}
                                           </td>
                                           <td style="width:20%;" >
                                            ${contacts.qq}
                                           </td>
 											<td style="width:20%;" >
                                            ${contacts.email}
                                           </td>
                                           </tr>
                                           </#list>
                                           </table>
                                           </td>
                              </tr>
                     </#list></#if>
               </table>
		</div><br/>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>
</html>
