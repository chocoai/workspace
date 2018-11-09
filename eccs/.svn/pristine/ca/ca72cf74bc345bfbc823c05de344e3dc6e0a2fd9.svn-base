    <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> > 
<a href="/t_fire/list.htm">收款管理列表</a>>
<a href="#">收款管理-查看</a>
</div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				收款信息
			</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
                                       <tr>
                                         <td width="15%" class="tab_title"><p>本次到账金额(元)</p></td>
                                         <td id="arrivalAmount" width="35%" >
												${receivables.arrivalAmount}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>到账日期</p></td>
                                         <td  width="35%" >
                                          <#if receivables.arrivalDate!=''>${receivables.arrivalDate?string('yyyy-MM-dd')}</#if>
                                         </td>
                                       </tr>
                                     
                                       <tr>
                                         <td class="tab_title"><p>备注</p></td>
                                         <td colspan="5" >
                                          ${receivables.remark}
                                         </td>
                                       </tr>  
                                       <tr>
                                            <td colspan="6" style=" text-align:right;">
                                            <input type="button"  id="button" value="返回" style="cursor:pointer;" onclick="javascript:history.go(-1);" class="sub"/>
                                                                                      
                                            </td>
                                        </tr>
                                        
                                                                    
                                     </table>
                                     
    </div>
<script>
$(function(){
var arrivalAmount=$("#arrivalAmount").html();
$("#arrivalAmount").html(formatCurrency(arrivalAmount));
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
</script>
