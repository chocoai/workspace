<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a>>
<a href="/financeManager.htm">财务管理</a> >
<a href="/t_invoice/list.htm">发票管理列表</a>>
<a href="#">发票管理-查看</a>
</div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				发票信息
			</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
							        	 <!-- <tr>
							       		<td colspan="6"> <p ><strong style="margin-left:35px;">项目信息</strong></p></td>
							             </tr> -->
                                       <tr>
                                         <td width="15%" class="tab_title"><p>项目编号</p></td>
                                         <td  colspan="2" style="background-color:#fff;">
												${invoice.project.no}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>实际应收总额(元)</p></td>
                                         <td id="payid"  colspan="2" style="background-color:#fff;">
                                           ${pay}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>项目名称</p></td>
                                         <td colspan="5" style="background-color:#fff;">
                                          ${invoice.project.name}
                                         </td>
                                       </tr>
                                        <tr>
                                         <td width="15%" class="tab_title"><p>累计已开票(元)</p></td>
                                         <td  id="aayid" colspan="2" style="background-color:#fff;">
												${aay}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>未开票总额(元)</p></td>
                                         <td id="bayid" colspan="2" style="background-color:#fff;">
                                           ${bay}
                                         </td>
                                       </tr>
                                       <tr>
							       		<td colspan="6"> <p ><strong style="margin-left:35px;">发票信息</strong></p></td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>付款单位</p></td>
                                         <td colspan="5" style="background-color:#fff;">
                                          ${invoice.payCompany}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>发票编号</p></td>
                                         <td  colspan="2" style="background-color:#fff;">
												${invoice.invoiceNo}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>发票类型</p></td>
                                         <td  colspan="2" style="background-color:#fff;">
                                           <#if invoice.invoiceType ==1 >普通发票</#if>
											<#if invoice.invoiceType ==2 >增值税专用发票</#if>
											<#if invoice.invoiceType ==3>定额发票</#if>
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>开票金额</p></td>
                                         <td id="invoiceAmount" colspan="2" style="background-color:#fff;">
												${invoice.invoiceAmount}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>开票日期</p></td>
                                         <td  colspan="2" style="background-color:#fff;">
                                           		<#if invoice.invoiceDate!=''>${invoice.invoiceDate?string('yyyy-MM-dd')}</#if>
                                         </td>
                                       </tr>
                                       
                                       <tr>
                                         <td class="tab_title"><p>发票内容</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                        		 ${invoice.invoiceContent}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>备注</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                          		${invoice.remark}
                                         </td>
                                       </tr>
                                      <tr>
                                         <td width="15%" class="tab_title"><p>发票领用人</p></td>
                                         <td  colspan="2" style="background-color:#fff;">
												${invoice.invoiceUser.name}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>领用日期</p></td>
                                         <td  colspan="2" style="background-color:#fff;">
                                          		<#if invoice.userDate!=''>${invoice.userDate?string('yyyy-MM-dd')}</#if>
                                         </td>
                                       </tr>
                                       <tr>
                                            <td colspan="6" style=" text-align:right;">
                                            <input type="button"  id="button" value="返回" style="cursor:pointer;" onclick="javascript:history.go(-1);" class="sub"/>
                                            <input type="button"  onclick="javascript:toPrint(${invoice.id});" value="打印" class="sub"/> 
                                            </td>
                                        </tr>
                                        
                                                                    
                                     </table>
                                     
    </div>
<script>
$(function(){
var payid=$("#payid").html();
$("#payid").html(formatCurrency(payid));
var aayid=$("#aayid").html();
$("#aayid").html(formatCurrency(aayid));
var bayid=$("#bayid").html();
$("#bayid").html(formatCurrency(bayid));
var invoiceAmount=$("#invoiceAmount").html();
$("#invoiceAmount").html(formatCurrency(invoiceAmount));
});
 function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
        num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  

function toPrint(id){
		var url = "/t_invoice/print.htm?invoice.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>
