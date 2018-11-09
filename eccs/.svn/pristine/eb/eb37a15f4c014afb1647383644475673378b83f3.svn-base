<!DOCTYPE html>
﻿<html>
	<head>

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
				border-left: 1.5pt solid windowtext;
				border-top: 1pt solid windowtext;
				border-right: 1pt solid windowtext;
			}
			.xl64 {
				mso-style-parent: style0;
				font-size: 9.0pt;
				border-top: 1pt solid windowtext;
				border-right: 1pt solid windowtext;
			}
			.xl65 {
				mso-style-parent: style0;
				font-size: 9.0pt;
				border-top: 1pt solid windowtext;
				border-right: 1.5pt solid windowtext;
			}
			.xl66 {
				mso-style-parent: style0;
				font-size: 9.0pt;
				border-left: 1.5pt solid windowtext;
				border-top: 1.5pt solid windowtext;
				border-right: 1.5pt solid windowtext;			}
		table{
		border-bottom: 1.5pt solid windowtext
		table-layout: fixed;}
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
	<body link="blue"  vlink="purple">
		<div id="print" align="center">
		     <table border="0" cellspacing="0" cellpadding="0" width="800px" style="border-bottom: 1.5pt solid windowtext;table-layout: fixed;word-break: break-all; word-wrap:break-word;">
							     <tr style="mso-height-source: userset; height: 32.0pt;">
										<td colspan=30 height=48 class=xl69 width=660
											style='height: 32.0pt; width: 600pt'>
											客户信息表
										</td>
									</tr>
									<tr height=26 >
										<td colspan=30   align=center class=xl66  style="font-weight:700">
											客户信息
										</td>
									</tr>
                                       <tr height=26 >
                                         <td   colspan="5" class=xl63  width="134px" align="right" style="font-weight:700"> 客户名称</td>
                                         <td   width="666px" colspan="25" class=xl65>
												${t_Customer.cusName}
                                         </td>
                                         </tr>
                                         <tr  height=26 >
                                         <td colspan="5" class=xl63  align="right" style="font-weight:700"> 客户性质 </td>
                                         <td colspan="10" width="266px"   align="left" class=xl64 >
                                           <#if t_Customer.cusNature ==1 >委托单位</#if>
											<#if t_Customer.cusNature ==2 >建设单位</#if>
											<#if t_Customer.cusNature ==3 >施工单位</#if>
											<#if t_Customer.cusNature ==4 >设计单位</#if>
                                         </td>
                                         <td class=xl64 colspan="5" width="133px" align="right" style="font-weight:700"> 客户类别</td>
                                         <td class=xl65 colspan="10" width="266px"  align="left" >
                                         <#if t_Customer.cusType ==1 >企业客户</#if>
											<#if t_Customer.cusType ==2 >政府客户</#if>
											<#if t_Customer.cusType ==3 >其它</#if>
                                         </td>
                                       </tr>
                                       
                                       <tr height=26 >
                                         <td class=xl63  colspan="5" align="right" style="font-weight:700"> 组织机构代码</td>
                                         <td    colspan="10" class=xl64 align="left">
											${t_Customer.ogCode}
                                         </td>
                                         <td class=xl64  colspan="5" align="right" style="font-weight:700"> 客户信用级别</td>
                                         <td class=xl65 colspan="10"  align="left">
											<#if  t_Customer.cusLevel==1>A</#if>
											<#if  t_Customer.cusLevel==2>B</#if>
											<#if  t_Customer.cusLevel==3>C</#if>
											<#if  t_Customer.cusLevel==4>D</#if>
                                         </td>
                                       </tr>
                                       
                                        <tr height=26 >
                                         <td class=xl63  colspan="5" align="right" style="font-weight:700"> 法人代表</td>
                                         <td class=xl64 colspan="10"  align="left">
											${t_Customer.lega}
                                         </td>
                                         <td class=xl64 colspan="5" align="right" style="font-weight:700"> 客户主页</td>
                                         <td class=xl65 colspan="10"  align="left">
                                          ${t_Customer.cusHomepage}
                                         </td>
                                        </tr>
                                           
                                       <tr height=26 >
                                         <td class=xl63 colspan="5" align="right" style="font-weight:700"> 邮政编码</td>
                                         <td class=xl64  colspan="10"   align="left" >
                                           ${t_Customer.postCode}
                                         </td>
                                         <td class=xl64 colspan="5" align="right" style="font-weight:700">传真</td>
                                         <td class=xl65  colspan="10"  align="left">
											${t_Customer.fax}	
                                         </td>
                                       </tr >
                                       
                                       <tr height=26 >
                                         <td class=xl63  colspan="5" align="right" style="font-weight:700">开户银行</td>
                                         <td colspan="10" class=xl64 align="left">
                                          ${t_Customer.bankAccount}
                                         </td>
                                         <td class=xl64 colspan="5" align="right" style="font-weight:700">开户账号</td>
                                         <td class=xl65 colspan="10"  align="left">
                                           ${t_Customer.accountOpening}
                                         </td>
                                           </tr>
                                           
                                          <tr height=26 >
                                         <td class=xl63  colspan="5" align="right" style="font-weight:700">电子邮箱</td>
                                         <td class=xl64 colspan="10"  align="left">
											${t_Customer.email}	
                                         </td>
                                         <td class=xl64 colspan="5" align="right" style="font-weight:700">办公电话</td>
                                         <td class=xl65 colspan="10"  align="left">
                                          ${t_Customer.officePhone}
                                         </td>
                                       </tr> 
                                       
                                       <tr height=60>
                                         <td class=xl63 colspan="5" align="right" style="font-weight:700">详细地址</td>
                                         <td colspan="25"   align="left" class=xl65>
                                          ${t_Customer.address}
                                         </td>
                                       </tr> 
                                       
                                       <tr height=60>
                                         <td class=xl63 align="right" colspan="5"style="font-weight:700" >备注</td>
                                         <td colspan="25"  class=xl65 align="left">
                                          ${t_Customer.remark}
                                         </td>
                                       </tr>
                                    <tr height=26 >
										<td colspan=30  class=xl63  align=center style=" font-weight:700;border-right: 1.5pt solid windowtext;">
											联系人信息
										</td>
									  </tr>            
					  <tr colspan=30 height=26 > 
					    <td style=" text-align:center; font-weight:700;  "class=xl63 colspan="2" >序号</td>
					     <td style=" text-align:center; font-weight:700;" class=xl64 colspan="3">联系人</td>
					    <td style=" text-align:center; font-weight:700; " class=xl64 colspan="3">部门</td>
					    <td style=" text-align:center; font-weight:700; " class=xl64 colspan="3">职务</td>
					    <td style=" text-align:center;  font-weight:700; " class=xl64 colspan="3">电话</td>
					     <td style=" text-align:center;  font-weight:700;" class=xl64 colspan="3">手机</td>
					      <td style=" text-align:center;  font-weight:700; " class=xl64 colspan="4">E-Mail</td>
					      <td style=" text-align:center; font-weight:700; " class=xl64 colspan="3">QQ</td>
					      <td style=" text-align:center;  font-weight:700; " class=xl64 colspan="3">默认联系人</td>
					      <td style=" text-align:center; font-weight:700; "  class=xl65  colspan="3">备注</td>
					  </tr>
					  <#if t_Contact.list && t_Contact.list?size!=0>
					    <#list t_Contact.list as t_Contact>
					  <tr height=26 >
					    <td style=" text-align:center;"  class=xl63 colspan="2">${t_Contact_index + 1}</td>
					    <td style=" text-align:center;" class=xl64 colspan="3"> ${t_Contact.contact }</td>
					    <td style=" text-align:center;" class=xl64 colspan="3">${t_Contact.deptName }</td>
					    <td style=" text-align:center;" class=xl64 colspan="3">${t_Contact.post }</td>
					    <td style=" text-align:center;" class=xl64 colspan="3">${t_Contact.phone }</td>
					    <td style=" text-align:center;" class=xl64 colspan="3">${t_Contact.telephone }</td>
					    <td style=" text-align:center;" class=xl64 colspan="4">${t_Contact.email }</td>
					    <td style=" text-align:center;" class=xl64 colspan="3">${t_Contact.qq }</td>
					       <td style=" text-align:center;" class=xl64 colspan="3">
					       <#if t_Contact.defContact==1>*</#if>
					       </td>
					    <td style=" text-align:center;" class=xl65 colspan="3">${t_Contact.remark }</td>
					  </tr>
					</#list>
					<#else>
					 <tr height=26 >
					    <td style=" text-align:center;"  class=xl63 colspan="2"></td>
					    <td style=" text-align:center;" class=xl64 colspan="3"></td>
					    <td style=" text-align:center;" class=xl64 colspan="3"></td>
					    <td style=" text-align:center;" class=xl64 colspan="3"></td>
					    <td style=" text-align:center;" class=xl64 colspan="3"></td>
					    <td style=" text-align:center;" class=xl64 colspan="3"></td>
					    <td style=" text-align:center;" class=xl64 colspan="4"></td>
					    <td style=" text-align:center;" class=xl64 colspan="3"></td>
					     <td style=" text-align:center;" class=xl64 colspan="3"></td>
					    <td style=" text-align:center;" class=xl65 colspan="3"></td>
					  </tr>
					</#if>
					     <tr height=26 >
								<td colspan=30 height=26  style=' font-weight:700;text-align: center;border-right: 1.5pt solid windowtext;' class=xl63>
											附件信息
								</td>
						</tr>
                        <tr colspan=30 height=26 >
                         <td style="text-align:center;  font-weight:700; width:60px;" class=xl63 colspan="2">序号</td>
						<td style=" text-align:center; font-weight:700; width:30%" class=xl64 colspan="9">文件名称</td>
						<td style=" text-align:center; font-weight:700; " class=xl65 colspan="19">文件描述</td>
                         </tr>
                         <#if lists &&  lists?size!=0 >
                           <#list lists as t_file>
							<tr height=26 >
							<td style=" text-align:center; " class=xl63 colspan="2">${t_file_index + 1}</td>
							<td style=" text-align:center; " class=xl64 colspan="9"> ${t_file.name}</td>
							<td style=" text-align:center;" class=xl65 colspan="19">${t_file.remarks}</td>
							</tr>
							</#list>
							<#else>
							<tr height=26 ><td class=xl63 colspan="2"></td><td class=xl64 colspan="9"> </td><td class=xl65 colspan="19"></td></tr>
							<tr height=26 ><td class=xl63 colspan="2"></td><td class=xl64 colspan="9"> </td><td class=xl65 colspan="19"></td></tr>
							<tr height=26 ><td class=xl63 colspan="2"></td><td class=xl64 colspan="9"> </td><td class=xl65 colspan="19"></td></tr>
							</#if>
                </table>
		</div>
		<br/>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
	</body>
</html>
