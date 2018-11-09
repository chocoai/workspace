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
<a href="javascript:window.location.reload();">成果文件签订签发</a>
</div>
  <form action="/step12/save.htm" method="post" id="contractForm">  
    <div id="content">
    		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				成果文件审定签发表
			</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
              <!-- <tr>
                	<td colspan="5" style=" font-weight:bold; font-size:20px; text-align:center;">成果文件审定签发表</td>
              </tr> -->
              <tr>
	                <td colspan="1" class="tab_title">项目名称</td>
	                <td width="80%" colspan="4" style="background-color:#fff;">${project.name}</td>
              </tr>
              <tr>
	                <td  class="tab_title">文件名称</td>
	                <td  style="background-color:#fff;" colspan="4">${step12.fileName}</td>
              </tr>
              <tr>
	                <td class="tab_title">报送部门（项目部）</td>
	                <td style="background-color:#fff;" colspan="4">${step12.receiverDeptName}</td>
              </tr>
              <tr>
	                <td class="tab_title" rowspan="2" align="right" style="font-weight:bold;"><font size="2">审核</font></td>
	                <td valign="top" align="left" colspan="4" style="background-color: #fff;">
	                	<div   style="min-height: 100px;">${step12.writerDescription}</div>
			        </td>
            </tr>
             <tr>
	                <td valign="top" style="text-align: center;"  width="20%">
			             	<span style="width:60%;"  height="30"><font size="2" style="font-weight:bold;">签名</font></span>
			        </td>
	                <td valign="top" align="left" width="20%" style="background-color: #fff;">
			             	<font size="2" style="margin-left: 10px;">${step12.writerName}</font>
			        </td>
	                <td valign="top" style="text-align: center;" width="20%">
			            	<span style="width:40%;"  height="30"><font size="2" style="font-weight:bold;"> 日期</font></span>
			        </td>
	                <td valign="top" align="left"style="background-color: #fff;" >
	                		<font size="2" style="margin-left: 10px;">${step12.writerDate}</font>
			        </td>
            </tr>  
            <tr>
	                <td class="tab_title" align="right" style="font-weight:bold;" rowspan="2"><font size="2">审定</font></td>
	                <td valign="top" align="left" colspan="4" style="background-color: #fff;">
	              			<div   style="min-height: 100px;">${step12.deptMasterView }</div>
			        </td>
            </tr>
            <tr>
            		 <td valign="top" style="text-align: center;"  width="20%">
			             	<span style="width:60%;"  height="30"><font size="2" style="font-weight:bold;">签名</font></span>
			        </td>
	                <td valign="top" align="left" width="20%" style="background-color: #fff;">
			             	<font size="2" style="margin-left: 10px;">${step12.deptMasterName}</font>
			        </td>
	                <td valign="top" style="text-align: center;" width="20%">
			            	<span style="width:40%;"  height="30"><font size="2" style="font-weight:bold;"> 日期</font></span>
			        </td>
	                <td valign="top" align="left" style="background-color: #fff;">
	                		<font size="2" style="margin-left: 10px;">${step12.deptMasterDate}</font>
			        </td>
            </tr>  
            <!-- <tr>
	                <td colspan="2" class="tab_title">公司领导审批意见</td>
	                <td colspan="3" style="background-color:#fff;"  valign="top" align="left">
	                <textarea readonly="readonly"  cols="45" rows="5" style="font-size:14px; width:100%; height="100%;" border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;min-width:380px;">${step12.compMasterView }</textarea>
			             <span style=" float:left; width:60%;background-color:#fff;">签名：${step12.compMasterName}</span>
			             <span style=" float:left;width:40%;background-color:#fff;">日期：${step12.compMasterDate}</span>
			        </td>
            </tr> -->
            <tr>
                	<td colspan="5" style=" text-indent:20px;"><strong>说明：对较重要文件的审签，部门负责人应明示相关会签部门，并建议其同步签审。</strong></td>
            </tr> 
            <tr>
                    <td colspan="5" style=" text-align:right;">
                    		<input type="button"  onclick="location.href ='javascript:history.back();';" value="返回" class="sub"/>
                    		<input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
                    </td>
             </tr>
       </table>
    </div>
</form>
<script>
	
	function toPrint(id){
		var url = "/step12/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
</script>