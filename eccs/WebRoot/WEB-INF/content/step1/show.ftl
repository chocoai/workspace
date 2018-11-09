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
<a href="javascript:window.location.reload();">咨询任务书</a>
</div>
  	<form action="/step1/save.htm" method="post" id="contractForm">  
  	<div id="content">
  		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			咨询任务书
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">  
        	 <!-- <tr>
        	 		<td colspan="7" style="font-weight: bold; font-size: 20px; text-align: center;">咨询任务书</td>
        	 </tr> -->
              <tr>
                     <td width="109" class="tab_title">项目名称</td>
                     <td colspan="6" style="background-color:#fff;">${project.name}</td>
              </tr>
              <tr>
                       <td class="tab_title">咨询类型</td>
                       <td colspan="6" style="background-color:#fff;">
                             ${project.serviceType.name}
                        </td>
              </tr>
              <tr>
                        <td class="tab_title">编审类型</td>
                        <td colspan="6" style="background-color:#fff;">
					   		 ${project.editorialType.name}                                    
                        </td>
               </tr>
               <tr>
				<td  class="tab_title">项目类型</td>
				<td  colspan="6" style="padding: 0px 0px; background-color: #fff;">
					<table   cellspacing="1" id="step1ContactId" cellpadding="1" class="list_table4" style="margin: 0 auto;margin-left:0px;">				
						<tr>
							<td style="padding: 0px 0px; background-color: #fff;">
								1.土建工程：基础<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('基础')>checked="checked"</#if> value="基础"/>
								主体<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('主体')>checked="checked"</#if> value="主体"/>
								屋面<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('屋面')>checked="checked"</#if> value="屋面"/>
							</td>
						</tr>
						<tr>
							<td style="padding: 0px 0px; background-color: #fff;">
								2.装饰装修工程<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('装饰装修工程')>checked="checked"</#if> value="装饰装修工程"/>
							</td>
						</tr>
						<tr>
							<td style="padding: 0px 0px; background-color: #fff;">
								3.安装工程：给排水（含消防）<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('给排水')>checked="checked"</#if> value="给排水"/>
								电气<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('电气')>checked="checked"</#if> value="电气"/>
								通风空调<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('通风空调')>checked="checked"</#if> value="通风空调"/>
								智能建筑（含综合布线）<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('智能建筑')>checked="checked"</#if> value="智能建筑"/>
								电梯<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('电梯')>checked="checked"</#if> value="电梯"/>
							</td>
						</tr>
						<tr>
							<td style="padding: 0px 0px; background-color: #fff;">
								4.园林景观绿化工程<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('园林景观绿化工程')>checked="checked"</#if> value="园林景观绿化工程"/>
								5.市政工程<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('市政工程')>checked="checked"</#if> value="市政工程"/>
								6.其他<input type="checkbox" name="step1.stepType" <#if step1.stepType! && step1.stepType?contains('其他')>checked="checked"</#if> value="其他"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
                <tr>
                        <td width="109" class="tab_title">委托方资料</td>
                        <td colspan="6" style="padding: 0px 0px;">
                             <table border="1" cellspacing="1" cellpadding="1" class="list_table4" style="width: auto;margin: 0 auto;border-collapse: collapse;border-width:0px; border-style:hidden;">
								<tr>
                                     <td colspan="1" style="font-weight:bold;text-align:right;">委托单位名称</td>
                                     <td colspan="4" style="background-color:#fff;">${step1.senderUnit }</td>
                                </tr>
                                <tr>
                                     <td colspan="1" style=" font-weight:bold;text-align:right;">详细地址</td>
                                     <td colspan="4" style="background-color:#fff;">${step1.senderAddress }</td>
                                 </tr>
                                 <tr>
                                     <td width="148" style=" text-align:center; font-weight:bold;">联系人姓名</td>
                                     <td width="306" style=" text-align:center; font-weight:bold;">手机</td>
                                     <td width="221" style=" text-align:center; font-weight:bold;">办公电话</td>
                                     <td width="221" style=" text-align:center; font-weight:bold;">传真</td>
                                     <td width="220" style=" text-align:center; font-weight:bold;">QQ或Email</td>
                                  </tr>
                                <#if step1ContactList && step1ContactList?size!=0>  
	                                <#list step1ContactList as step1Contact>  
	                                   <tr>
	                                     <td style=" text-align:center;background-color:#fff;">${step1Contact.name }</td>
	                                     <td style=" text-align:center;background-color:#fff;">${step1Contact.officeTel }</td>
	                                     <td style=" text-align:center;background-color:#fff;">${step1Contact.tel }</td>
	                                     <td style=" text-align:center;background-color:#fff;">${step1Contact.fax }</td>
	                                     <td style=" text-align:center;background-color:#fff;">${step1Contact.email }</td>
	                                   </tr>
	                                   </#list>
                                   <#else>
	                                   <tr>
	                                     <td style="background-color:#fff;"></td><td style="background-color:#fff;"></td><td style="background-color:#fff;"></td>
	                                     <td style="background-color:#fff;"></td><td style="background-color:#fff;"></td>
	                                   </tr>
	                                   <tr>
	                                     <td style="background-color:#fff;"></td><td style="background-color:#fff;"></td><td style="background-color:#fff;"></td>
	                                     <td style="background-color:#fff;"></td><td style="background-color:#fff;"></td>
	                                   </tr>
	                                   <tr>
	                                     <td style="background-color:#fff;"></td><td style="background-color:#fff;"></td><td style="background-color:#fff;"></td>
	                                     <td style="background-color:#fff;"></td><td style="background-color:#fff;"></td>
	                                   </tr>
                                   </#if>
                                 </table>
                              </td>
                           </tr>
                     
        				  <tr>
						<td rowspan="5" style="text-align: right;">具体工作任务</td>
						<td
							style="width: 112px; height: 30px; line-height: 30px; text-align: right;">
							工期要求
						</td>
						<td colspan="5" style="height: 30px; line-height: 30px; text-align: left;background-color:#fff;">
						<#if project!=null>	
							${step1.startWorkTime}
		                     至:
		                    ${step1.endWorkTime}
		                 </#if>   
						</td>
					</tr>
					<tr>
						<td
							style=" height: 30px; line-height: 30px; text-align: right;">
							咨询范围
						</td>
						<td colspan="5" style="height: 30px; line-height: 30px; text-align: left;background-color:#fff;">
							${step1.consultRange }
						</td>
					</tr>
					<tr>
						<td style="height: 30px; line-height: 30px; text-align: right;">
							质量要求
						</td>
						<td colspan="5" style="height: 30px; line-height: 30px; text-align: left;background-color:#fff;" >
							${step1.qualityRequirement}
						</td>
					</tr>
					<tr>
						<td style="height: 30px; line-height: 30px; text-align: right">
							其它要求
						</td>
						<td colspan="5" style="height: 30px; line-height: 30px; text-align: left;background-color:#fff;" >
							${step1.otherRequirements}
						</td>
					</tr>
          <tr>
	            <td colspan="5" style=" width:20px; height:30px; line-height:30px; text-align:left;"><a >相关附件</a>（合同、中标通知书、业主指令等）</td>
          </tr>
          <tr>
            <td colspan="7" style="padding: 0px 0px;">
    <table border="1" cellspacing="1" cellpadding="1" class="list_table4" style="margin: 0 auto;border-collapse: collapse;border-width:0px; border-style:hidden;">
  <tr>
	    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:109px;">文件名</td>
	    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">文件类型</td>
	    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">上传人</td>
	    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">上传时间</td>
	    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:42px;">操作</td>
  </tr>
  <#if annexList && annexList?size!=0>
    <#list annexList as annex>
		  <tr>
			    <td style=" text-align:center;background-color:#fff;">${annex.name }</td>
			    <td style=" text-align:center;background-color:#fff;"> ${annex.annexType.name }</td>
			     <td style=" text-align:center;background-color:#fff;">${annex.user.name }</td>
			    <td style=" text-align:center;background-color:#fff;">${annex.ctime }</td>
			    <td style=" text-align:center;background-color:#fff;"><a href="/file/xiazai.htm?proid=${annex.id}">下载</a></td>
		  </tr>
	</#list>
	<#else>
		  <tr>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
		  </tr>
		  <tr>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
		  </tr>
		  <tr>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
			    <td style="background-color:#fff;"></td>
		  </tr>
	
	</#if>
</table></td>
          </tr>
            <tr>
			    <td colspan="7" style=" text-align:right;">
			    	<input type="button"  onclick="location.href ='javascript:history.back();';";" value="返回" class="sub"/>                               
			    	<input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
			    </td>
             </tr>
          </table>
    </div>
</form>
<script>
	function toPrint(id){
		var url = "/step1/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
</script>


