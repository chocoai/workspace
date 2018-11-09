    <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_cult/list.htm">培训管理-列表</a> >
<a href="#">培训管理-查看</a>
</div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			培训管理表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table" style="margin-top:0px;">
                                       <tr>
                                         <td width="15%" class="tab_title"><p>主题</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hrtrain.theme}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>培训方式</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           <#if t_hrtrain.mode ==1 >内训</#if>
											<#if t_hrtrain.mode ==2 >外训</#if>
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>培训时间</p></td>
                                         <td style="background-color:#fff;">
											${t_hrtrain.tdate}
                                         </td>
                                         <td class="tab_title"><p>培训地点</p></td>
                                         <td style="background-color:#fff;">
											${t_hrtrain.tplace}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>培训内容</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                          ${t_hrtrain.content}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>授课人</p></td>
                                         <td colspan="5" style="background-color:#fff;">
                                          ${t_hrtrain.teach}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>受训人员</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                          ${t_hrtrain.traineestr}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>备注</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                          ${t_hrtrain.remark}
                                         </td>
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

