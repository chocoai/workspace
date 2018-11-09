<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> ></#if>
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
<a href="javascript:window.location.reload();">回访记录</a>
</div>
  <form action="/step13/save.htm" method="post" id="contractForm">  
    <div id="content">
    		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				造价咨询项目征询意见回访记录表
			</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
              <!-- <tr>
               		<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">造价咨询项目征询意见回访记录表</td>
              </tr> -->
              <tr>
	                <td width="13%" class="tab_title">项目名称</td>
	                <td width="38%" style="background-color:#fff;">${project.name}</td>
	                <td width="12%" class="tab_title">项目编号</td>
	                <td width="38%" style="background-color:#fff;">${project.no }</td>
              </tr>
              <tr>
	                <td class="tab_title">回访对象</td>
	                <td style="background-color:#fff;">${step13.visitObject}</td>
	                <td class="tab_title">服务内容</td>
	                <td style="background-color:#fff;">${step13.serviceContent}</td>
              </tr>
              <tr>
	                <td  colspan="2" style=" text-align:center; font-weight:bold;width:160px;">征询意见主要内容</td>
	                <td colspan="2" style=" text-align:center; font-weight:bold; ">满意度</td>
              </tr>
              <#if step13ItemList &&step13ItemList?size!=0>         
				    <#list step13ItemList as step13Item>
					  <tr>
					    <td  colspan="2" style=" text-align:center;  background-color:#fff;width:160px;">${step13Item.content}</td>
					    <td colspan="3" style="background-color:#fff;"><input name="radio${ step13Item_index}" disabled="disabled" type="radio"  <#if step13Item.result == 1>checked="checked"</#if>   />&nbsp;很满意
			                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}"  type="radio" disabled="disabled"  <#if step13Item.result == 2>checked="checked"</#if> />&nbsp;满意      
			                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}" type="radio" disabled="disabled"  <#if step13Item.result == 3>checked="checked"</#if> />&nbsp;一般
			                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}"  type="radio" disabled="disabled"  <#if step13Item.result == 4>checked="checked"</#if> />&nbsp;不满意      
			                    &nbsp;&nbsp;&nbsp;<input name="radio${ step13Item_index}" type="radio" disabled="disabled"  <#if step13Item.result == 5>checked="checked"</#if> />&nbsp;很不满意</td>
					  </tr>
				  </#list>
	  	  	<#else>
	  	 		<tr><td  colspan="2"  height="30" style="background-color:#fff;"></td><td colspan="3"  height="30" style="background-color:#fff;"></td></tr>
	  	 		<tr><td  colspan="2"  height="30" style="background-color:#fff;"></td><td colspan="3"  height="30" style="background-color:#fff;"></td></tr>
	  	 		<tr><td  colspan="2"  height="30" style="background-color:#fff;"></td><td colspan="3"  height="30" style="background-color:#fff;"></td></tr>
	  	   </#if>
         <tr>
                <td rowspan="2" class="tab_title">征询单位评价意见</td>
                <td colspan="3" style="background-color:#fff;" valign="top" align="left">
		               <div   style="min-height: 150px;">${step13.summaryContent}</div>
	            </td>
          </tr>
          <tr>
                <td colspan="2"   align="left" height="30">
	             	项目负责人&nbsp;&nbsp; ${step13.masterName}
	            </td>
                <td  align="left">
	             	日期&nbsp;&nbsp;${step13.masterSignDate}
	            </td>
          </tr>
          <tr>
                <td rowspan="2"  class="tab_title" align="right" >完善措施</td>
                <td colspan="3"  style="background-color:#fff;"  valign="top" align="left">
	               	 <div   style="min-height: 150px;">${step13.perfectContent} </div>
	            </td>
          </tr>
          <tr>
                <td colspan="2"   align="left" height="30">
	             	项目负责人&nbsp;&nbsp; ${step13.techName}
	            </td>
                <td align="left">
	             	日期  &nbsp;&nbsp;${step13.techSingDate}
	            </td>
          </tr>
          <tr>
		    <td colspan="4" style="padding: 0px 0px;">
		    <table border="1" id="fileUploadTableId" cellspacing="1" cellpadding="1" class="list_table4" style="margin: 0px auto;border-width:0px; border-style:hidden;">
		        <tr>
		            <td colspan="8" style=" width:20px; height:30px;font-weight:bold; line-height:30px; text-align:center;"> 
		         		   附件资料
		            </td>
		  </tr>
		  <tr>
			    <td width="20%" style=" text-align:center; font-weight:bold;">资料类型</td>
			  	<td width="15%" style=" text-align:center; font-weight:bold;">文件</td>
			    <td width="15%" style=" text-align:center; font-weight:bold;">资料名称</td>
			    <td width="10%" style=" text-align:center; font-weight:bold;">文件字号</td>
			    <td width="15%" style=" text-align:center; font-weight:bold;">文件作者</td>
			    <td width="15%" style=" text-align:center; font-weight:bold;">页号</td>
			    <td width="15%" style=" text-align:center; font-weight:bold;">备注</td>
			    <td width="5%" style=" text-align:center; font-weight:bold;width:42px;">操作</td>
		  </tr>
		   <#list annexList as annex>  
		  <tr>
			    <td  style="text-align:center;background:#fff;" >${annex.annexType.name }</td>
			    <td  style="text-align:center;background:#fff;" >${annex.name }</td>
			    <td style="text-align:center;background:#fff;">${annex.name }</td>
			    <td style="text-align:center;background:#fff;">${annex.fileNum}</td>
			    <td style="text-align:center;background:#fff;">${annex.fileOwner }</td>
			    <td style="text-align:center;background:#fff;">${annex.filePage }</td>
			    <td style="text-align:center;background:#fff;">${annex.description}</td>
			    <td style="text-align:center;background:#fff;"><#if annex.id!="" ><a href="/file/xiazai.htm?proid=${annex.id}">下载</a></#if></td>
		   </tr>
		   </#list>
		</table></td>
		</tr>
          <tr>
				<td colspan="6" style=" text-align:right;">
					<input type="button" onclick="location.href ='javascript:history.back();';"  value="返回" class="sub"/>
					<input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
				</td>
		   </tr>
        </table>
    </div>
</form>

<script>
	function toPrint(id){
		var url = "/step13/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
</script>
