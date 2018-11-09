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
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> ></#if>
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if> 
<a href="javascript:window.location.reload();">征求意见稿</a>
</div>
  <form action="/step10/save.htm" method="post" id="contractForm">  
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
						      <table border="1" cellspacing="1" id="zxyjid" cellpadding="1" class="input_table2">
							      <tr>
								        <td width="100%" colspan="3" style=" font-weight:bold; font-size:20px; text-align:center;">(征求意见稿)答复意见表</td>
									    </td>
							   	 </tr>
							  	 <tr>
							       		<td  width="30%" style=" text-align:center; font-weight:bold;">参阅规范或条款编号</td>
								        <td  width="30%" style=" text-align:center; font-weight:bold;">修改意见内容</td>
								        <td   width="30%" style=" text-align:center; font-weight:bold;">理由或依据</td>
							      </tr>
						      		  <#list  step10DFYJList as dfyj >
											  <tr >
												    <td  style="background-color:#fff; text-align:center;">${dfyj.num}</td>
												    <td  style="background-color:#fff; text-align:center;">${dfyj.content}</td>
												    <td style="background-color:#fff; text-align:center;">${dfyj.reason}</td>
											  </tr>
									  </#list>
									  <tfoot>
											 <tr height="50px">
													<td "  colspan="4" style="text-align: left;">
														<span style="font-weight:bold;">是否通过：</span>
											        	<#if step10.status==-1>不通过<#elseif step10.status==1>通过<#else>&nbsp;</#if>
											        </td>
										      </tr>
											 <tr height="50px">
												 	<td colspan="4">
													           <input type="button" onclick="location.href ='javascript:history.back();';" value="返回" class="sub" style="width: 80px;height: 30px;"/>
					    									   <input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub" style="width: 80px;height: 30px;"/>   
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
	
	function toPrint(id){
		var url = "/step10/print.htm?project.id=" + id;
		
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>
