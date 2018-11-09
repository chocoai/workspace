<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> ></#if>
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
<a  href="javascript:window.location.reload();">校对</a>
</div>
  <form action="/step7/save.htm" method="post" id="contractForm">  
    	<div id="content">
    		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				造价咨询项目校对表
			</div>
  <table border="1" cellspacing="1" cellpadding="1" class="input_table">
	  <!-- <tr>
	    	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目校对表</td>
	  </tr> -->
	  <tr>
		    <td width="92" class="tab_title">项目名称</td>
		    <td style="background-color:#fff;">
		    ${project.name}
			</td>
		    <td class="tab_title">项目编号</td>
		    <td style="background-color:#fff;">
		     ${project.no }
			</td>
	  </tr>
	  <tr>
   			 <td colspan="4"  >
	    <table border="1" id="step7ItemId" cellspacing="1" cellpadding="1" class="list_table3" style=" margin-top:10px;">
	  		<tr>
			    <td width="133" style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">主要问题</td>
			    <td width="155" style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">修改及执行情况</td>
	  		</tr>
  		<#list step7ItemList as step7Item>
  			<tr>
			    <td style=" text-align:center;background-color:#fff;">${step7Item.content}</td>
			    <td style=" text-align:center;background-color:#fff;">
				     <#if step7Item.result == 1>修改通过
	                 <#elseif step7Item.result == 2>修改未通过
	                 <#elseif step7Item.result == 0>未修改
	                 </#if> 
				</td>
 			 </tr>
  		</#list>
	    </table>
    </td>
  </tr>
  <tr>
          <td  class="tab_title"> 编制人</td>
          <td  style=" background-color:#fff;"> ${step6.user.name}</td>
          <td  class="tab_title">编制日期</td>     
          <td  style="background-color:#fff;"> ${step6.confirmTime}</td>       
  </tr>
  <tr>
          <td  class="tab_title"> 校对人</td>
          <td  style=" background-color:#fff;">
         	<#if step7.validateName!=null || step7==null>
          			<input name="step7.validateName" value="${step7.validateName}" type="hidden"  class="text_a" />${step7.validateName}
           <#else>
           			<input name="step7.validateName" value="${user.name}"   type="hidden"  class="text_a" />${user.name}
           </#if>
           </td>
          <td  class="tab_title">校对日期</td>     
          <td  style="background-color:#fff;">
           <#if step7.validateDate!=null>
          			<input name="step7.validateDate" value="${step7.validateDate}" type="hidden" />${step7.validateDate}
           <#else>
           			<input name="step7.validateDate" value="${validateDate}" type="hidden" />${validateDate}
           </#if>
           </td>       
   </tr>
   <tr>
        <td colspan="7" style=" text-align:right;">
       		 <input type="button"  onclick="location.href ='javascript:history.back();';" value="返回" class="sub"/>                                          
        	 <input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
        </td>
   </tr>
</table>
</div>
</form>
<script>
	
	function toPrint(id){
		var url = "/step7/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
</script>