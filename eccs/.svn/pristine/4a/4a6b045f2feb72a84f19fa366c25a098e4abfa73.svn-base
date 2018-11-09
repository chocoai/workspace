<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){	
		$('.tabPanel ul li').click(function(){
			$(this).addClass('hit').siblings().removeClass('hit');
			$('.panes>div:eq('+$(this).index()+')').show().siblings().hide();	
		})
	})
	function next(divid){
		$('.tabPanel ul li').eq(divid).addClass('hit').siblings().removeClass('hit');
		$('.panes>div:eq('+divid+')').show().siblings().hide();	
	}
</script>  
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if>
<a href="javascript:window.location.reload();">征求意见稿</a>
</div>
  <form action="/step10/validateSave.htm" method="post" id="contractForm">  
  	<input name="nextWorkerId" id="nextWorkerId"  type="hidden" />
    <div id="content">
    	 <div style="width:68%;margin:0px auto;">
    			<div class="tabPanel">
						<ul>
							<li class="hit">征求意见稿发出确认签收单</li>
							<li>（征求意见稿）意见反馈表</li>
							<li>（征求意见稿）答复意见表</li>
						</ul>
						<div class="panes">
								<div class="pane" style="display:block;"><br />
									        <table border="1" cellspacing="1" cellpadding="1" class="input_table2">
									              <tr>
									                	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">征求意见稿发出确认签收单</td>
									              </tr>
									              <tr>
										                <td width="13%" class="tab_title">项目名称</td>
										                <td colspan="3" style="background-color:#fff; ">
											                <input name="step10.id" value="${step10.id}"  type="hidden" />
											                <input name="project.id" value="${project.id}" type="hidden" />
											  				${project.name}
														</td>
									              </tr>
									              <tr>
											                <td width="13%" class="tab_title">文件名称</td>
											                <td colspan="3" style="background-color:#fff; ">${step10.fileName}</td>
									              </tr>
									              <tr>
											                <td width="13%" class="tab_title">主送单位及部门</td>
											                <td colspan="3" style="background-color:#fff; ">${step10.receiverDeptName}</td>
									              </tr>
									              <tr>
											                <td class="tab_title">报告份数</td>
											                <td width="35%" style="background-color:#fff; ">${step10.fileCount}</td>
											                <td width="14%" class="tab_title">文号</td>
											                <td width="38%" style="background-color:#fff; " >${step10.fileNo}</td>
									              </tr>
									          	  <tr>
											                <td class="tab_title">有关情况说明</td>
											                <td colspan="3" style="background-color:#fff; ">${step10.description }</td>
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
											                <td class="tab_title" rowspan="2">意见</td>
											                <td colspan="3" style="background-color:#fff; ">
									                			  ${step10.receiverView }
									            			</td>
									           		 </tr>
									          	  <tr>
											                <td colspan="3">
									            				<span style=" float:left; width:33%;">签收方式：<#if step10.receiverType== 1>签名<#elseif step10.receiverType== 2>电邮<#elseif step10.receiverType== 3>电话</#if></span>
									             				<span style=" float:left; width:33%;">签收人：${step10.receiverUserName}</span>
									            				<span style=" float:left;width:33%;">签收日期：${step10.receiverDate}</span>
									            			</td>
									           		 </tr>
									           		 <tr height="50px"><td colspan="4">
							                			<input onclick="javascript:next(1);" value="下一步" class="sub" type="button" style="width: 80px;height: 30px;">
					            					</td></tr>
									           		 
									         </table><br/>
				   			 </div>
				  
						    <div class="pane"><br />
								      <table border="1" id="fkyjid" cellspacing="1" cellpadding="1" class="input_table2">
								      <tr>
									        <td width="100%" colspan="2" style=" font-weight:bold; font-size:20px; text-align:center;">(征求意见稿)意见反馈表</td>
								       </tr>
								    <tr>
									        <td   width="50%" style=" text-align:center; font-weight:bold;">反馈意见内容</td>
									        <td   width="50%" style=" text-align:center; font-weight:bold;">理由或依据</td>
								      </tr>
								      <#list  step10FKYJList as fkyj >
											<tr >
												    <td  style="background-color:#fff; text-align:center;">${fkyj.content}</td>
												    <td  style="background-color:#fff; text-align:center;">${fkyj.reason}</td>
										    </tr>
									  </#list>
									  
									  
									  <tr height="50px"><td colspan="4">
							                <input onclick="javascript:next(2);" value="下一步" class="sub" type="button" style="width: 80px;height: 30px;">
					            	  </td></tr>
								 </table><br />  
						    </div>
						   <div class="pane"><br />
								      <table border="1" cellspacing="1" cellpadding="1" class="input_table2">
								      <tbody id="zxyjitbody">
								      <tr>
									        <td width="92%" colspan="3" style=" font-weight:bold; font-size:20px; text-align:center;">(征求意见稿)答复意见表</td>
									        <td width="8%">
									        		<div style="display:block; text-align:center; border:1px solid #dadada; border-bottom:0px" class="add_link" >
									                       <input type="button" onclick="javascript:addZXYJItem();" value="+新增" class="sub"/> 
									                </div>
									                       
									        </td>
								        </tr>
								       <tr>
									        <td  width="30%" style=" text-align:center; font-weight:bold;">参阅规范或条款编号</td>
									        <td  width="30%" style=" text-align:center; font-weight:bold;">修改意见内容</td>
									        <td   width="30%" style=" text-align:center; font-weight:bold;">理由或依据</td>
									        <td  colspan="1" width="10%" style=" text-align:center; font-weight:bold;">操作</td>
								      </tr>
								        <#list  step10DFYJList as dfyj >
											  <tr>
												    <td  style="background-color:#fff; text-align:center;">${dfyj.num}</td>
												    <td  style="background-color:#fff; text-align:center;">${dfyj.content}</td>
												    <td style="background-color:#fff; text-align:center;">${dfyj.reason}</td>
												    <td style="background-color:#fff; text-align:center;"><a href="javascript:deleteDFYJ(${dfyj.id})">删除</a></td>
											  </tr>
										</#list>
										</tbody>
										<tfoot>
											 <tr height="50px">
													<td colspan="4" style="text-align: left;">
														<span class="red">是否通过</span>
											        	<input type="radio" name="status"  value="-1" <#if step10.status=="-1">checked='checked'</#if>style="width: 50px;height: 30px;vertical-align: middle;"/>&nbsp;&nbsp;不通过
														<input type="radio" name="status" value="1"  <#if step10.status=="1">checked='checked'</#if>style="width: 50px;height: 30px;vertical-align: middle;"/>&nbsp;&nbsp;通过
											        </td>
										      </tr>
											 <tr height="50px">
												 	<td colspan="4">
													           <input onclick="location.href ='javascript:history.back();';" value="取消" class="sub" type="button" style="width: 80px;height: 30px;">
													          <input  type="submit"  value="保存"  class="sub" style="width: 80px;height: 30px;">
													           <input onclick="javascript:toNextStep1();" value="转交任务" class="sub" type="button" style="width: 80px;height: 30px;">
													           <input onclick="javascript:toNextStep();" value="提交任务" class="sub" type="button" style="width: 80px;height: 30px;">
											        </td>
										      </tr>
									   </tfoot>
								    </table><br />  
						    </div>
				    </div>
    		</div>
		</div>
   </div>
</form>

<script>
	var i=0;
	var flag = true;
	var j=0;
	var flag2 = true;
	
	function AddZXYJRow(){
				//添加一行
				var tab1 = document.getElementById("zxyjitbody");
				var len  = tab1.rows.length;
					if(flag2 ){
						j =  j+ tab1.rows.length;
						flag2 =false;
					}else{
						j =  j+ 1;
					}
				var Nam="'div1'";
				var Cod="fuJ"+j;
				var newTr = tab1.insertRow();
					newTr.id = j;
				//添加列
				var newTd0 = newTr.insertCell();
				var newTd1 = newTr.insertCell();
				var newTd2 = newTr.insertCell();
				var newTd3 = newTr.insertCell();
					//设置列内容和属性
					newTd0.innerHTML = '<input name="numZX"  type="text" class="text_a"/>';
					newTd1.innerHTML = '<input name="contentZX"  type="text" class="text_a"/>';
					newTd2.innerHTML = '<input name="reasonZX"  type="text" class="text_a"/>';
					newTd3.innerHTML = '<a onclick="javascript:deleteZXYJRow('+j+')">删除</a>';
	}

	function deleteZXYJRow(i){
		var tab1 = document.getElementById("zxyjitbody");
		  $('#'+i).remove();
	}
	function addZXYJItem(){
		AddZXYJRow();
	}

	$().ready(function() {  
	  	      $("#contractForm").validate({
					rules : {  
					'status' : { required : true }  
					}
				})
	}); 

	function toNextStep(){  //执行下一步
			 if(confirm("当前步骤执行完毕,确定要执行下一步吗?")){
				 document.getElementById("contractForm").action="/step10/toNextStep.htm";
				 $("#contractForm").submit();
			 }
	}
	
	function toNextStep1(){  //转交
			window.open('/user/selectUser2.htm', '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100');
	}
	function returnParam2(userId, name, userName){
			$("#nextWorkerId").val(userId);
			document.getElementById("contractForm").action="/step10/validateSave.htm";
			document.getElementById("contractForm").submit();
	}
</script>
<script>
		function deleteDFYJ(id){
				$.ajax({
							type:"get",
							url:'/step10/deleteDFYJ.htm?id=' + id,
							asyn:true,		//false为同步提交
							dataType:'text',	//返回文本
							success:function(d){
									window.parent.location.reload();
							}
				})
		}
</script>
