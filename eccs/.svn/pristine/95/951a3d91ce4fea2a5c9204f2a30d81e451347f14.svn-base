<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> > 
<a href="javascript:history.go(-1);">${project.name}</a> >
<a href="javascript:window.location.reload();">成果文件签订签发</a>
</div>
  <form action="/step12/save.htm" method="post" id="contractForm">  
    <div id="content">
	<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				成果文件审定签发表
			</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
              <!-- <tr>
               		 <td colspan="5" style=" font-weight:bold; font-size:20px; text-align:center;">成果文件审定签发表</td>
              </tr> -->
              <tr>
	                <td colspan="2" class="tab_title">项目名称</td>
	                <td width="82%" colspan="3">
						<input name="step12.id" value="${step12.id}"  type="hidden" />
						<input name="project.id" value="${project.id}" type="hidden" />
					    <input name="project.name" value="${project.name}" readonly="readonly"   id="projectNameId"  type="text" class="text_a" />
					</td>
              </tr>
              
            <#if step12.nextWorker!=null && step12.step == 2>
            <tr>
	                <td colspan="2" class="tab_title">文件名称</td>
	                <td width="82%" colspan="3">
	                	<input type="hidden" id="step" name="step12.step" value="2"/>
	                	<input name="step12.fileName" value="${step12.fileName}" type="text" readonly="readonly" class="text_a"/>
	                </td>
              </tr>
              <tr>
	                <td colspan="2" class="tab_title">报送部门（项目部）</td>
	                <td colspan="3"><input name="step12.receiverDeptName" value="${step12.receiverDeptName}" type="text" readonly="readonly" class="text_a"/></td>
              </tr>
            <tr>
	                <td colspan="2" class="tab_title">审核</td>
	                <td colspan="3"><textarea name="step12.writerDescription"  id="writerDescription" cols="45" rows="5" style=" font-size:14px;width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;min-width:380px;">${step12.writerDescription}</textarea>
			             <span style=" float:left; width:60%;">签名
			             <input name="step12.writerName" value="${step12.writerName}" id="writerName" type="text" style="margin:0 auto;line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			            <span style=" float:left;width:40%;">日期<input name="step12.writerDate" value="${step12.writerDate}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
		            </td>
            </tr>  
            <tr>
	                <td colspan="2" class="tab_title red">审定</td>
	                <td colspan="3"><textarea name="step12.deptMasterView" id="deptMasterView" cols="45" rows="5" style=" font-size:14px;width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;min-width:380px;">${step12.deptMasterView }</textarea>
			             <span style=" float:left; width:60%;color:red;">签名
			             <input name="step12.deptMasterName" value="${step12.deptMasterName}" id="deptMasterName" type="text" style="margin:0 auto;line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			            <span style=" float:left;width:40%;color:red;">日期<input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="step12.deptMasterDate" value="${step12.deptMasterDate}" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			        </td>
            </tr>
            <tr style="display:none;">
	                <td colspan="2" class="tab_title">公司领导审批意见</td>
	                <td colspan="3"><textarea name="step12.compMasterView" id="compMasterView" cols="45" rows="5" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;min-width:380px;">${step12.compMasterView }</textarea>
			             <span style=" float:left; width:60%;">签名
			             <input name="step12.compMasterName" value="${step12.compMasterName}" id="compMasterName" type="text" style="margin:0 auto;line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			             <span style=" float:left;width:40%;">日期<input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="step12.compMasterDate" value="${step12.compMasterDate}" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
		            </td>
            </tr>
            <#else>
            <tr>
	                <td colspan="2" class="tab_title red">文件名称</td>
	                <td width="82%" colspan="3">
	                	<input type="hidden" id="step" name="step12.step" value="1"/>
	                	<input name="step12.fileName" value="${step12.fileName}" type="text" class="text_a"/>
	                </td>
              </tr>
              <tr>
	                <td colspan="2" class="tab_title red">报送部门（项目部）</td>
	                <td colspan="3"><input name="step12.receiverDeptName" value="${step12.receiverDeptName}" type="text" class="text_a"/></td>
              </tr>
            <tr>
	                <td colspan="2" class="tab_title red">审核</td>
	                <td colspan="3"><textarea name="step12.writerDescription"  id="writerDescription" cols="45" rows="5" style=" font-size:14px;width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;min-width:380px;">${step12.writerDescription}</textarea>
			             <span style=" float:left; width:60%;color:red;">签名
			             <input name="step12.writerName" value="${step12.writerName}" id="writerName" type="text" style="margin:0 auto;line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			            <span style=" float:left;width:40%;color:red;">日期<input name="step12.writerDate" value="${step12.writerDate}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
		            </td>
            </tr> 
            <tr style="display:none;">
	                <td colspan="2" class="tab_title red">审定</td>
	                <td colspan="3"><textarea name="step12.deptMasterView" id="deptMasterView" cols="45" rows="5" style=" font-size:14px;width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;min-width:380px;">${step12.deptMasterView }</textarea>
			             <span style=" float:left; width:60%;color:red;">签名
			             <input name="step12.deptMasterName" value="${step12.deptMasterName}" id="deptMasterName" type="text" style="margin:0 auto;line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			            <span style=" float:left;width:40%;color:red;">日期<input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="step12.deptMasterDate" value="${step12.deptMasterDate}" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			        </td>
            </tr>
				<tr style="display:none;">
	                <td colspan="2" class="tab_title">公司领导审批意见</td>
	                <td colspan="3"><textarea name="step12.compMasterView" id="compMasterView" cols="45" rows="5" style="font-size:14px; width:100%; border:1px solid #c1e9ff; margin-top:5px; margin-bottom:5px;min-width:380px;">${step12.compMasterView }</textarea>
			             <span style=" float:left; width:60%;">签名
			             <input name="step12.compMasterName" value="${step12.compMasterName}" id="compMasterName" type="text" style="margin:0 auto;line-height:22px; width:60%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
			             <span style=" float:left;width:40%;">日期<input readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="step12.compMasterDate" value="${step12.compMasterDate}" type="text" style="line-height:22px; width:70%; height:22px; text-indent:6px; border-bottom:1px solid #c1e9ff;"/></span>
		            </td>
            </tr>
            </#if>
            
            <!-- <tr>
                	<td colspan="4" style=" text-indent:20px;"><strong>说明：对较重要文件的审签，部门负责人应明示相关会签部门，并建议其同步签审。</strong></td>
            </tr> -->
            
            <tr>
                    <td colspan="7" style=" text-align:right;">
	                    <input type="button"  onclick="location.href ='javascript:history.back();';" value="取消" class="sub"/>
	                    <input type="submit"  value="保存"  class="sub" />    
	                    <input type="button" onclick="javascript:toNextStep1();"  value="转交任务"  class="sub" />  
	                     <#if user.username != "${SUPERADMIN}" >    
	                    <input type="button" onclick="javascript:toNextStep();"  value="提交任务"  class="sub" />                                           
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
               'step12.fileName' : { required : true },
					'step12.receiverDeptName' : { required : true }
				}
			})
		
	}); 
	
	function toNextStep(){  //执行下一步
	var	writerDescription = $("#writerDescription").val();
	var	writerName = $("#writerName").val();
	var writer=writerDescription.replace(/(^\s*)/g, "");
	var name=writerName.replace(/(^\s*)/g, "");
	if(writer.length!=0&&name.length==0){
		alert("请输入技术负责人签名");
		return false;
	}
	var deptMasterView=$("#deptMasterView").val();
	var deptMasterName=$("#deptMasterName").val();
	var View=deptMasterView.replace(/(^\s*)/g, "");
	var name1=deptMasterName.replace(/(^\s*)/g, "");
	if(View.length!=0&&name1.length==0){
		alert("请输入分管领导签名");
		return false;
	}
	var compMasterView=$("#compMasterView").val();
	var compMasterName=$("#compMasterName").val();
	var v=compMasterView.replace(/(^\s*)/g, "");
	var name2=compMasterName.replace(/(^\s*)/g, "");
	if(v.length!=0&&name2.length==0){
		alert("请输入公司领导签名");
		return false;
	}
	var step = $("#step").val();
	if(step == 1 && $("#nextOperatorId").val() == ''){
		alert("请指定下一步处理人");
		return false;
	}
	var nextWorkerName = $("#nextOperatorName").val();
 	if(confirm("当前步骤执行完毕,确定要执行下一步吗?"))
	 {
		 document.getElementById("contractForm").action="/step12/toNextStep.htm";
		  $("#contractForm").submit();
	 }	
	}
	var userStatus ="";
	function toNextStep1() { //转交 nextWorkerId
		userStatus ="nextWorker";
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function getUser2() {
		userStatus ="nextOperator";
		window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(id,name,usernmae) {
		if(userStatus=='nextWorker'){
				$('#nextWorkerId').val(id);
				document.getElementById("contractForm").action="/step12/save.htm";
				document.getElementById("contractForm").submit();
		}else if(serStatus='nextOperator'){
				$("#nextOperatorId").val(id);
				$("#nextOperatorName").val(name);
		}
	}
</script>
