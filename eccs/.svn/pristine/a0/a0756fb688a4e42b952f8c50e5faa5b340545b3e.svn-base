<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
<a href="javascript:window.location.reload();">成果文件签并订签发</a>
</div>
  <form action="/step9/save.htm" method="post" id="contractForm">  
<div id="content">
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
              <tr>
                <td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目审定记录表</td>
              </tr>
              <tr>
                <td width="4%" class="tab_title">项目名称</td>
                <td width="31%"><input name="step9.id" value="${step9.id}"  type="hidden" />
                <input name="project.id" value="${project.id}" type="hidden" />
    <input name="project.name" value="${project.name}" readonly="readonly"   id="projectNameId"  type="text" class="text_a" />
       </td>
                <td width="17%" class="tab_title">项目编号</td>
                <td width="49%"><input readonly="readonly" value="${project.no }"  id="projectNoId"  type="text" class="text_a" /></td>
              </tr>
              <tr>
                <td class="tab_title">文件内容</td>
                <td><input name="step9.fileContent" value="${step8.fileContent }" type="text" class="text_a"/></td>
                <td class="tab_title">编制人</td>
                <td><input name="step9.writeName" value="${step8.writeName }" type="text" class="text_a"/></td>
              </tr>
          <tr>
                <td class="tab_title">审核意见</td>
            <td colspan="2">
            <textarea disabled="disabled" rows="5" style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step8.auditContent1 }</textarea>
            <span style=" float:left; width:60%;">审核人1：<input disabled="disabled" type="text" value="${step8.auditName1}" style="margin:0 auto;line-height:22px; width:40%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
            <span style=" float:left;width:40%;">日期1：<input disabled="disabled" value="${auditDate1}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
            </td>
            <td colspan="2">
            <textarea disabled="disabled" rows="5" style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step8.auditContent2 }</textarea>
            <span style=" float:left; width:60%;">审核人2：<input disabled="disabled" type="text" value="${step8.auditName2}" style="margin:0 auto;line-height:22px; width:40%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
            <span style=" float:left;width:40%;">日期2：<input disabled="disabled" value="${auditDate2}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
            </td>
              </tr>   
          <tr>
                <td class="tab_title">审定意见</td>
            <td colspan="2">
            <textarea  name="step9.validateContent1"  rows="5"   style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step9.validateContent1 }</textarea>
            <span style=" float:left; width:60%;">审定人1：<input  name="step9.validateName1" value="${step9.validateName1 }" type="text" style="margin:0 auto;line-height:22px; width:40%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
            <span style=" float:left;width:40%;">日期1：<input  name="step9.validateDate1" value="${validateDate1 }"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
            </td>
            <td colspan="2">
            <textarea  name="step9.validateContent2" rows="5" style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step9.validateContent2 }</textarea>
            <span style=" float:left; width:60%;">审定人2：<input  name="step9.validateName2" value="${step9.validateName2 }"  type="text" style="margin:0 auto;line-height:22px; width:40%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
            <span style=" float:left;width:40%;">日期2：<input name="step9.validateDate2" value="${validateDate2 }"   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" style="line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
            </td>
              </tr>
              <tr>
                <td class="tab_title">审核比例</td>
                <td><input name="step9.auditPercent" value="${step9.auditPercent}" type="text" class="text_a"/></td>
                <td class="tab_title">进度系数</td>
                <td><input name="step9.progress" value="${step9.progress }" type="text" class="text_a"/></td>
              </tr>
              
              <tr>
                <td colspan="4" style=" text-indent:20px;"><strong>注：进度考核系数 =1 - 延迟时间所占要求完成时间的比例</strong></td>
              </tr>
                       <tr>
                                            <td colspan="7" style=" text-align:right;">
                                            <input type="button"  onclick="location.href ='javascript:history.back();';" value="取消" class="sub"/>
                                            <input type="submit"  value="保存"  class="sub" />   
                                            <input type="button" onclick="javascript:toNextStep();"  value="提交任务"  class="sub" />                                                                                 
                                            </td>
                                        </tr>
            </table>
    </div>
</form>
<script>
$().ready(function() {  
  	      $("#contractForm").validate({
				rules : {  

				}
			})
}); 
function toNextStep(){  //执行下一步
	 if(confirm("当前步骤执行完毕,确定要执行下一步吗?"))
	 {
		 document.getElementById("contractForm").action="/step9/toNextStep.htm";
		  document.getElementById("contractForm").submit();
	 }
}

</script>
