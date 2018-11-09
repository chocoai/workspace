<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>

<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> >
<a href="/requ/list.htm">项目请款</a> >
${project.name}
</div>
   <div id="content">
   	  <input name="requisition.id" value="${requisition.id}" type="hidden" />
   	  <input name="project.id" value="${project.id}"  type="hidden" id="projectIdId" />
      <table border="1" cellspacing="1" cellpadding="1" class="input_table" width="800px" >
    	<tr style="height:50px;">
		    <td colspan="10" style=" font-weight:bold; font-size:20px; text-align:center;">请款申请单</td>
    	</tr>
	  <tr>
	    <td colspan="3" class="tab_title2">项目名称 </td>
	    <td colspan="4"  style="background-color: #fff;">${project.name}</td>
	    <td colspan="2" class="tab_title2">付款单位 </td>
	    <td  style="background-color: #fff;">${requisition.payCustomerName}</td>
	  </tr>
     <tr>
	    <td colspan="3" class="tab_title2">咨询类型</td>
	       <td colspan="7">
			     <#list serviceTypeList as serviceType>
			        <input name="requisition.serviceIds" type="radio"  disabled  <#if requisition.serviceIds != null && requisition.serviceIds?contains('${serviceType.id}') >checked='checked'</#if>  value="${serviceType.id}" />&nbsp;${serviceType.name}&nbsp;&nbsp;&nbsp;&nbsp;
			     </#list>	
           </td>
     <!--<td colspan="4">
      <input name="project.serviceType.id" id="serviceTypeId"   readonly="readonly" value="${project.serviceType.id}" class="text_a" hidden/>
      <input name="project.serviceType.name" id="serviceTypeName"   readonly="readonly" value="${project.serviceType.name}" type="text" class="text_a" />
    </td>
    <td colspan="2" class="tab_title2 red">业务范围 </td>
    <td width="279">
    <input name="project.editorialType.id"  id="editorialTypeId"    value="${project.editorialType.id }"  class="text_a"  hidden/>
    <input name="project.editorialType.name"id="editorialTypeName"  readonly="readonly" value="${project.editorialType.name }" type="text" class="text_a" />
    </td>-->
  </tr>
    <tr>
    <td colspan="3" class="tab_title2">合同编号 </td>
    <td colspan="4"  style="background-color: #fff;">${requisition.contacton}</td>
    <td colspan="2" class="tab_title2">报告编号 </td>
    <td width="279"  style="background-color: #fff;">${requisition.reportNo }</td>
  </tr>
  <tr>
    <td colspan="3" class="tab_title2">请款类别</td>
    <td colspan="7" >
     <#list requisitionTypeList as requisitionType>
       <input name="requisition.requisitionTypeIds" type="radio"  disabled   <#if requisition.requisitionTypeIds != null && requisition.requisitionTypeIds?contains('${requisitionType.id}') >checked='checked'</#if>  value="${requisitionType.id}" />&nbsp;${requisitionType.name}&nbsp;&nbsp;&nbsp;&nbsp;
     </#list>	
     </td>
  </tr>
      <tr>
    <td colspan="3" class="tab_title2 red">服务内容</td>
     <td colspan="7">
    <table  >
     <tr colspan="5">
	      <td><input  name="requisition.serviceContentIds"  disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("a") >checked='checked'</#if>type="checkbox" value="a" />&nbsp;规划咨询</td>
	     <td> <input  name="requisition.serviceContentIds"  disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("b") >checked='checked'</#if> type="checkbox" value="b"/>&nbsp;项目建议书</td>
	     <td> <input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("c") >checked='checked'</#if> type="checkbox" value="c"/>&nbsp;可行性研究报告</td>
	     <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("d") >checked='checked'</#if> type="checkbox" value="d"/>&nbsp;项目申请报告</td>        
    </tr>
     <tr colspan="5">
         <td> <input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("e") >checked='checked'</#if> type="checkbox" value="e"/>&nbsp;资金申请报告</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("f") >checked='checked'</#if> type="checkbox" value="f"/>&nbsp;评估咨询</td>
        <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("g") >checked='checked'</#if> type="checkbox" value="g"/>&nbsp;节能评估</td>
        <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("h") >checked='checked'</#if> type="checkbox" value="h"/>&nbsp;工程项目管理</td>
    </tr>
     <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("i") >checked='checked'</#if> type="checkbox" value="i"/>&nbsp;项目可能性研究投资估算编制 </td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("10") >checked='checked'</#if> type="checkbox" value="10"/>&nbsp;项目可能性研究投资估算审核 </td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("11") >checked='checked'</#if> type="checkbox" value="11"/>&nbsp;项目经济评价 </td>
         <td></td>   
      </tr>    
      <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("12") >checked='checked'</#if> type="checkbox" value="12"/> &nbsp;概算编制</td>
        <td> <input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("13") >checked='checked'</#if> type="checkbox" value="13"/>&nbsp;预算编制</td>
        <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("14") >checked='checked'</#if> type="checkbox" value="14"/>&nbsp;结算编制</td>
        <td> <input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("15") >checked='checked'</#if> type="checkbox" value="15"/>&nbsp;竣工结算编制</td>
      </tr> 
      <tr colspan="5">
         <td> <input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("16") >checked='checked'</#if> type="checkbox" value="16"/>&nbsp;概算审核</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("17") >checked='checked'</#if> type="checkbox" value="17"/>&nbsp;预算审核</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("18") >checked='checked'</#if> type="checkbox" value="18"/> &nbsp;结算审核</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("19") >checked='checked'</#if> type="checkbox" value="19"/>&nbsp;竣工结算审核</td>
    </tr>
    <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("20") >checked='checked'</#if> type="checkbox" value="20"/>&nbsp;工程清单编制</td>
          <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("21") >checked='checked'</#if> type="checkbox" value="21"/> &nbsp;招标控制价编制</td>
          <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("22") >checked='checked'</#if> type="checkbox" value="22"/>&nbsp;招标标底编制</td>
          <td> <input  name="requisition.serviceContentIds" disabled<#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("23") >checked='checked'</#if> type="checkbox" value="23"/>&nbsp;投标报价编制</td>
    </tr>
   <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("24") >checked='checked'</#if> type="checkbox" value="24"/>&nbsp;工程清单审核</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("25") >checked='checked'</#if> type="checkbox" value="25"/>&nbsp;招标控制价审核</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("26") >checked='checked'</#if> type="checkbox" value="26"/>&nbsp;招标标底审核</td>
         <td> <input  name="requisition.serviceContentIds" disabled<#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("27") >checked='checked'</#if> type="checkbox" value="27"/>&nbsp;投标报价审核</td>
    </tr>
   <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("28") >checked='checked'</#if> type="checkbox" value="28"/>&nbsp;工程洽谈、变更及合同争议的鉴定</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("29") >checked='checked'</#if> type="checkbox" value="29"/>&nbsp;索赔</td>
         <td></td>
         <td></td>
    </tr>
   <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("30") >checked='checked'</#if> type="checkbox" value="30"/>&nbsp;编制工程造价计价依据</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("31") >checked='checked'</#if> type="checkbox" value="31"/>&nbsp;造价监控及提供造价信息等</td>
         <td><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("32") >checked='checked'</#if> type="checkbox" value="32"/>&nbsp;项目实施阶段造价跟踪控制</td>
         <td></td>
    </tr>
    <tr>
         <td colspan="5"><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("33") >checked='checked'</#if> type="checkbox" value="33"/>&nbsp;编制及发布招标公告及招标文件、组织踏勘及答疑、组织开评标会议、签发中标通知书、整理招标汇编资料等</td>
    </tr>
    <tr>
         <td colspan="5"><input  name="requisition.serviceContentIds" disabled <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("34") >checked='checked'</#if> type="checkbox" value="34"/>&nbsp;土地整治可研、规划设计与预算编制</td>
    </tr>
    </table>
     </td>
  </tr>
  <tr>
    <td width="44" rowspan="2" colspan="3" class="tab_title2">计费方法及申请金额 </td>
    <td colspan="3" class="tab_title2 red">计费方式</td>
    <td colspan="4"  style="background-color: #fff;">${requisition.payType }</td>
    </tr>
  <tr>
    <td colspan="3" class="tab_title2 red">申请金额(人民币/万元)</td>
    <td width="145" colspan="4"  style="background-color: #fff;">${requisition.amount }</td>
  </tr>
  
<tr>
    <td colspan="3" class="tab_title2">收款单位银行信息 </td>
    <td colspan="7"  style="background-color: #fff;">
 		<div name="requisition.bankInfo" cols="45" rows="6" style=" width:100%; margin-top:5px; margin-bottom:5px;" style="background-color: #fff;">${requisition.bankInfo}</div>
	</td>
    
</tr>

<tr>
    <td colspan="10" style=" font-weight:bold; text-align:center;">如需增值税专用发票请如实提供贵单位开专票信息</td>
</tr>
 <tr>
    <td colspan="5" class="tab_title2">经办人：</td>
    <td width="138"  style="background-color: #fff;">${requisition.recordUserName }</td>
    <td width="100" class="tab_title2">申请日期：</td>
    <td width="148"  style="background-color: #fff;">${requisition.requisitionDate}</td>
    <td width="100" class="tab_title2">请款单位：</td>
    <td  style="background-color: #fff;">${requisition.requisitionDeptName }</td>
    </tr>
                   <tr>
	                   <td colspan="10" style=" text-align:right;">
	                   <input type="button"  value="返回" onclick="javascript:history.go(-1);" class="sub"/>   
	                   <input type="button"  onclick="javascript:toPrint(${requisition.id});" value="打印" class="sub"/>                                         
	                   </td>
	               </tr>
</table>
  </div>
  <script>
function toPrint(id){
		  var url = "/requ/print.htm?requisition.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>

