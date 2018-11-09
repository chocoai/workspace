<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>

<div id="map"><img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> >
<a href="/requ/list.htm">项目请款</a> >
<a href="#">项目请款单</a>
</div>
<form action="/requ/save.htm" method="post" id="contractForm">
    <div id="content">
      <table border="1" cellspacing="1" cellpadding="1" class="input_table">
        <tr style="height:50px;">
    <td colspan="10" style=" font-weight:bold; font-size:20px; text-align:center;">请款申请单</td>
    </tr>
  <tr>
      <td colspan="3" class="tab_title2 red">项目名称 </td>
    <td colspan="4"><input name="project.id" value="${project.id}"  type="hidden" id="projectIdId" />
    <input name="project.name" value="${project.name}" readonly="readonly" <#if project.name == null>  onclick="getProject();" </#if> id="projectNameId"  type="text" class="text_a" />
	</td>
    <td colspan="2" class="tab_title2 red">付款单位 </td>
    <td  width="279"><input name="requisition.id" value="${requisition.id}" type="hidden" />
    <input name="requisition.payCustomerName" value="${requisition.payCustomerName}" type="text" class="text_a" /></td>
  </tr>
 
     <tr>
    <td colspan="3" class="tab_title2 red">咨询类型</td>
    <td colspan="7">
                  <input name="requisition.serviceIds" type="hidden" id="serviceIds" value="${requisition.serviceIds}">
			     <#list serviceTypeList as serviceType>
			        <input name="serviceIds" type="radio" disabled  value="${serviceType.id}" <#if project.serviceType != null && project.serviceType.id?contains('${serviceType.id}') >checked='checked'</#if> />&nbsp;${serviceType.name}&nbsp;&nbsp;&nbsp;&nbsp;
			     </#list>	
           </td>
   <!--    <td colspan="4">
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
    <td colspan="4"><input name="requisition.contacton" id="contacton"   readonly="readonly" value="${project.no}" type="text" class="text_a" /></td>
    <td colspan="2" class="tab_title2">报告编号 </td>
    <td width="279"><input name="requisition.reportNo" value="${requisition.reportNo }" type="text" class="text_a" /></td>
  </tr>
  <tr>
    <td colspan="3" class="tab_title2 red">请款类别</td>
    <td colspan="7">
     <#list requisitionTypeList as requisitionType>
        <input name="requisition.requisitionTypeIds" type="radio" <#if requisition.requisitionTypeIds != null && requisition.requisitionTypeIds?contains('${requisitionType.id}') >checked='checked'</#if>  value="${requisitionType.id}" />&nbsp;${requisitionType.name}&nbsp;&nbsp;&nbsp;&nbsp;
     </#list>	
     </td>
  </tr>
    <tr>
    <td colspan="3"  class="tab_title2 red">服务内容</td>
    <td colspan="7">
    <table  >
     <tr colspan="5">
	      <td><input  name="requisition.serviceContentIds"  <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("a") >checked='checked'</#if>type="checkbox" value="a" />&nbsp;规划咨询</td>
	     <td> <input  name="requisition.serviceContentIds"  <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("b") >checked='checked'</#if> type="checkbox" value="b"/>&nbsp;项目建议书</td>
	     <td> <input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("c") >checked='checked'</#if> type="checkbox" value="c"/>&nbsp;可行性研究报告</td>
	     <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("d") >checked='checked'</#if> type="checkbox" value="d"/>&nbsp;项目申请报告</td>        
    </tr>
     <tr colspan="5">
         <td> <input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("e") >checked='checked'</#if> type="checkbox" value="e"/>&nbsp;资金申请报告</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("f") >checked='checked'</#if> type="checkbox" value="f"/>&nbsp;评估咨询</td>
        <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("g") >checked='checked'</#if> type="checkbox" value="g"/>&nbsp;节能评估</td>
        <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("h") >checked='checked'</#if> type="checkbox" value="h"/>&nbsp;工程项目管理</td>
    </tr>
     <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("i") >checked='checked'</#if> type="checkbox" value="i"/>&nbsp;项目可能性研究投资估算编制 </td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("10") >checked='checked'</#if> type="checkbox" value="10"/>&nbsp;项目可能性研究投资估算审核 </td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("11") >checked='checked'</#if> type="checkbox" value="11"/>&nbsp;项目经济评价 </td>
         <td></td>   
      </tr>    
      <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("12") >checked='checked'</#if> type="checkbox" value="12"/> &nbsp;概算编制</td>
        <td> <input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("13") >checked='checked'</#if> type="checkbox" value="13"/>&nbsp;预算编制</td>
        <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("14") >checked='checked'</#if> type="checkbox" value="14"/>&nbsp;结算编制</td>
        <td> <input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("15") >checked='checked'</#if> type="checkbox" value="15"/>&nbsp;竣工结算编制</td>
      </tr> 
      <tr colspan="5">
         <td> <input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("16") >checked='checked'</#if> type="checkbox" value="16"/>&nbsp;概算审核</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("17") >checked='checked'</#if> type="checkbox" value="17"/>&nbsp;预算审核</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("18") >checked='checked'</#if> type="checkbox" value="18"/> &nbsp;结算审核</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("19") >checked='checked'</#if> type="checkbox" value="19"/>&nbsp;竣工结算审核</td>
    </tr>
    <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("20") >checked='checked'</#if> type="checkbox" value="20"/>&nbsp;工程清单编制</td>
          <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("21") >checked='checked'</#if> type="checkbox" value="21"/> &nbsp;招标控制价编制</td>
          <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("22") >checked='checked'</#if> type="checkbox" value="22"/>&nbsp;招标标底编制</td>
          <td> <input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("23") >checked='checked'</#if> type="checkbox" value="23"/>&nbsp;投标报价编制</td>
    </tr>
   <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("24") >checked='checked'</#if> type="checkbox" value="24"/>&nbsp;工程清单审核</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("25") >checked='checked'</#if> type="checkbox" value="25"/>&nbsp;招标控制价审核</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("26") >checked='checked'</#if> type="checkbox" value="26"/>&nbsp;招标标底审核</td>
         <td> <input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("27") >checked='checked'</#if> type="checkbox" value="27"/>&nbsp;投标报价审核</td>
    </tr>
   <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("28") >checked='checked'</#if> type="checkbox" value="28"/>&nbsp;工程洽谈、变更及合同争议的鉴定</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("29") >checked='checked'</#if> type="checkbox" value="29"/>&nbsp;索赔</td>
         <td></td>
         <td></td>
    </tr>
   <tr colspan="5">
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("30") >checked='checked'</#if> type="checkbox" value="30"/>&nbsp;编制工程造价计价依据</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("31") >checked='checked'</#if> type="checkbox" value="31"/>&nbsp;造价监控及提供造价信息等</td>
         <td><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("32") >checked='checked'</#if> type="checkbox" value="32"/>&nbsp;项目实施阶段造价跟踪控制</td>
         <td></td>
    </tr>
    <tr>
         <td colspan="5"><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("33") >checked='checked'</#if> type="checkbox" value="33"/>&nbsp;编制及发布招标公告及招标文件、组织踏勘及答疑、组织开评标会议、签发中标通知书、整理招标汇编资料等</td>
    </tr>
    <tr>
         <td colspan="5"><input  name="requisition.serviceContentIds" <#if requisition.serviceContentIds != null && requisition.serviceContentIds?contains("34") >checked='checked'</#if> type="checkbox" value="34"/>&nbsp;土地整治可研、规划设计与预算编制</td>
    </tr>
    </table>
     </td>
  </tr>
  <tr>
    <td width="44" rowspan="2" colspan="3" class="tab_title2">计费方法及申请金额 </td>
    <td class="red" colspan="3">计费方式</td>
    <td colspan="4"><input name="requisition.payType" value="${requisition.payType }" type="text" class="text_a" /></td>
  </tr>
  <tr>
    <td class="red" colspan="3">申请金额(人民币/万元)</td>
    <td width="145" colspan="4"><input name="requisition.amount"  value="${requisition.amount }" type="text" class="text_a" /></td>
    
  </tr>
  
<tr>
    <td colspan="3" class="tab_title2">收款单位银行信息 </td>
    <td colspan="7">
	 	<textarea name="requisition.bankInfo" cols="45" rows="6" style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${requisition.bankInfo}</textarea>
	</td>
</tr>

<tr>
    <td colspan="10" style=" font-weight:bold; text-align:center;">如需增值税专用发票请如实提供贵单位开专票信息</td>
    </tr>

 <tr>
    <td colspan="5" class="tab_title2 red">经办人：</td>
    <td width="138" ><input name="requisition.recordUserName"  value="${requisition.recordUserName }" type="text" class="text_a" /></td>
    <td width="100" class="tab_title2 red">申请日期：</td>
    <td width="148" ><input name="requisition.requisitionDate"  value="${requisition.requisitionDate}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" class="text_a" /></td>
    <td width="100" class="tab_title2 red">请款单位：</td>
    <td ><input name="requisition.requisitionDeptName"  value="${requisition.requisitionDeptName }" type="text" class="text_a" /></td>
</tr>
                     <tr>
	                   <td colspan="10" style=" text-align:right;">
	                   <input type="button" value="取消" onclick="javascript:history.go(-1);" class="sub"/>
	                   <input type="submit" value="保存"  class="sub" /> 
	                   <#if requisition.nextOperatorId == user.id>            
	                   <input type="button"   id="nextSteps"  class="sub" onclick="javascript:toNextStep();" value="提交"/>  
	                   </#if>                                
	                   </td>
	               </tr>
</table>
  </div>
   </form>
<script>
$().ready(function() {  
  	      $("#contractForm").validate({
				rules : {  
				    'requisition.payCustomerName' : { required : true } ,
				  'project.name' : { required : true } , 
				  'requisition.payType' : { required : true } ,
				  'requisition.amount' : { required : true, number:true } ,
				  'requisition.recordUserName' : { required : true } , 
				  'requisition.requisitionDate' : { required : true } , 
				   'requisition.requisitionDeptName' : { required : true } 
				}
			})
}); 
function getProject(){
		var iWidth=1200;                          //弹出窗口的宽度; 
   		var iHeight=600;                         //弹出窗口的高度; 
   		//获得窗口的垂直位置 
   		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
   		//获得窗口的水平位置 
   		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
	    window.open('/project/listForSelect.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes,titlebar=no');
}
function returnSelectProject(id,name,pno,cno,projectTypeName,serviceTypeId,serviceTypeName,editorialTypeId,editorialTypeName,senderDeptName){
		$("#projectIdId").val(id);
		$("#projectNameId").val(name);
		$("#contacton").val(cno);
	    $("#serviceIds").val(serviceTypeId);
	    var t="input[name='serviceIds'][value="+serviceTypeId+"]";
		 $(t).attr("checked","checked");
}
function getNextOperator(userIdId, userNameId) {
		window.open('/user/selectMultiUser.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId, '_blank',
				'channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}
function toNextStep() { //执行下一步
		if (confirm("当前步骤执行完毕,确定要执行下一步吗?")) {
			document.getElementById("contractForm").action = "/requ/nextStep.htm";
			$("#contractForm").submit();
		}
	}
</script>
