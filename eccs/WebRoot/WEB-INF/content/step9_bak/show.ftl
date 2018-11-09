<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
 <a href="/projectManager.htm">项目管理</a> > 
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> > </#if>
 <#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
 <a href="javascript:window.location.reload();">审定</a>
 </div>
  <form action="/step9/save.htm" method="post" id="contractForm">  
<div id="content">
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
              <tr>
                <td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目审定记录表</td>
              </tr>
              <tr>
                <td width="4%" class="tab_title">项目名称</td>
                <td width="31%" style=" background-color:#fff;">${project.name}</td>
                <td width="17%" class="tab_title">项目编号</td>
                <td width="49%" style=" background-color:#fff;">${project.no }</td>
              </tr>
              <tr>
                <td class="tab_title">文件内容</td>
                <td style=" background-color:#fff;">${step9.fileContent }</td>
                <td class="tab_title">编制人</td>
                <td style=" background-color:#fff;">${step9.writeName }</td>
              </tr>
          <tr>
                <td class="tab_title">审核意见</td>
            <td colspan="2">
            <textarea name="step8.auditContent1" rows="5" style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step8.auditContent1 }</textarea>
            <span style=" float:left; width:60%;background-color:#fff;" >审核人1：${step8.auditName1}</span>
            <span style=" float:left;width:40%;background-color:#fff;">日期1：${auditDate1}</span>
            </td>
            <td colspan="2">
            <textarea name="step8.auditContent2" readOnly="readOnly" rows="5" style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;">${step8.auditContent2 }</textarea>
            <span style=" float:left; width:60%;background-color:#fff;">审核人2：${step8.auditName2}</span>
            <span style=" float:left;width:40%;background-color:#fff;">日期2：${auditDate2}</span>
            </td>
              </tr>   
          <tr>
                <td class="tab_title">审定意见</td>
            <td colspan="2">
            <textarea  rows="5" readOnly="readOnly" style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;"></textarea>
            <span style=" float:left; width:60%;background-color:#fff;">审定人1：${step9.validateName1}</span>
            <span style=" float:left;width:40%;background-color:#fff;">日期1：${step9.validateDate1}</span>
            </td>
            <td colspan="2">
            <textarea  readOnly="readOnly"  rows="5" style=" width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;"></textarea>
            <span style=" float:left; width:60%;background-color:#fff;">审定人2：${step9.validateName2}</span>
            <span style=" float:left;width:40%;background-color:#fff;">日期2：${step9.validateDate2}</span>
            </td>
              </tr>
              
              <tr>
                <td class="tab_title">审核比例</td>
                <td style=" background-color:#fff;">${step9.auditPercent}</td>
                <td class="tab_title">进度系数</td>
                <td style=" background-color:#fff;">${step9.progress }</td>
              </tr>
              
              <tr>
                <td colspan="4" style=" text-indent:20px;"><strong>注：进度考核系数 =1 - 延迟时间所占要求完成时间的比例</strong></td>
              </tr>
                       <tr>
                                            <td colspan="7" style=" text-align:right;">
                                            <input type="button"  onclick="location.href ='javascript:history.back();';" value="取消" class="sub"/>
                                            <input type="submit"  value="保存"  class="sub" />                                              
                                            </td>
                                        </tr>
            </table>
    </div>
</form>

