<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_liregistration/list.htm">证照登记-列表</a> >
<a href="#">证照登记-查看</a>
</div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			证照登记表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
                                       <tr>
                                         <td width="10%" class="tab_title"><p>证照名称</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.licname}
                                         </td>
                                         <td  width="10%" class="tab_title"><p>证照编号</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.licnumber}
                                         </td>
                                         <td width="10%" class="tab_title"><p>状态</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												<#if  liregistration.licstatus==1>闲置</#if>
												<#if  liregistration.licstatus==2>占用</#if>
												<#if  liregistration.licstatus==3>其他</#if>
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="10%" class="tab_title"><p>颁发日期</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.predate}
                                         </td>
                                         <td  width="10%" class="tab_title"><p>颁发部门</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.prebranch}
                                         </td>
                                         <td width="10%" class="tab_title"><p>下次年检日期</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.anndate}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="10%" class="tab_title"><p>年检部门</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.annbranch}
                                         </td>
                                         <td  width="10%" class="tab_title"><p>有效期</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.effdate}
                                         </td>
                                         <td width="10%" class="tab_title"><p>网上填报系统</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.filsystem}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="10%" class="tab_title"><p>保管部门</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.dept_id.name}
                                         </td>
                                         <td  width="10%" class="tab_title"><p>保管人</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												${liregistration.kuser_id.name}
                                         </td>
                                         <td width="10%" class="tab_title"><p>正副本标识</p></td>
                                         <td width="20%"  style="background-color:#fff;">
												<#if  liregistration.characteristic==1>正本</#if>
												<#if  liregistration.characteristic==2>副本</#if>
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>年检材料</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                         ${liregistration.material}
                                         </td>
                                       </tr>  
                                       <tr>
                                         <td class="tab_title"><p>年检记录</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                          ${liregistration.record}
                                         </td>
                                       </tr>  
                                       <tr>
                                         <td class="tab_title"><p>备注说明</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                          ${liregistration.explain}
                                         </td>
                                       </tr>  
                                       <tr>
					 <td colspan="9" id="we">
                                        <table width="100%" border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
                                        <tr>
                                        <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;">序号</td>
									    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:25%">文件名称</td>
									    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">文件描述</td>
									    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">操作</td>
                                        </tr>
                                         <#list lists as t_file>
										  <tr>
										   <td style=" text-align:center;background-color:#fff;">${t_file_index + 1}</td>
										    <td style=" text-align:center;background-color:#fff;"> ${t_file.name}</td>
										      <td style=" text-align:center;background-color:#fff;">${t_file.remarks}</td>
										      <td style=" text-align:center;background-color:#fff;"><a href="/t_file/xiazai.htm?t_file.id=${t_file.id}">下载</a></td>
										  </tr>
										  </#list>
                                        </table>
                                    
                                        </td>  
					</tr>
                                       <tr>
                                            <td colspan="6" style=" text-align:right;">
                                            <input type="button"  id="button" value="返回" style="cursor:pointer;" onclick="javascript:history.go(-1);" class="sub"/>
                                                                                      
                                            </td>
                                        </tr>
                                        
                                                                    
                                     </table>
                                     
    </div>

