<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_reso/list.htm">信息登记-列表</a> >
<a href="#">信息登记-查看</a>
</div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			用户信息表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
                                       <tr>
                                         <td width="15%" class="tab_title"><p>姓名</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hremployee.name}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>分配部门</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hremployee.dept_id.name}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>性别</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												<#if  t_hremployee.sex==1>男</#if>
												<#if  t_hremployee.sex==2>女</#if>
                                         </td>
                                         <td  width="15%" class="tab_title"><p>出生年月</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           <#if t_hremployee.birth !="">${t_hremployee.birth?string('yyyy-MM-dd')}</#if>
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>联系电话</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hremployee.phone}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>专业</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hremployee.phone}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>紧急联系人</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hremployee.emg_contact}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>电子邮箱</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hremployee.email}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>身份证</p></td>
                                         <td colspan="3" style="background-color:#fff;">
                                          ${t_hremployee.id_card}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>系统账号</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hremployee.sys_account.username}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>排序号</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hremployee.sort_no}
                                         </td>
                                       </tr>
                                       <!---->
                                       <#if t_hreducationlist.size()!=0 >
                                       <tr>
                                       <td colspan="4">
                                       <table border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
											  <tr>
											    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:30px;">序号</td>
											     <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">毕业院校</td>
											    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">学历</td>
											    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">毕业时间</td>
											  </tr>
										    <#list t_hreducationlist as t_hreducation>
											    <input type="hidden" value=${t_Contact.id}/>
											  <tr>
											    <td style=" text-align:center;background-color:#fff;">${t_hreducation_index + 1}</td>
											    <td style=" text-align:center;background-color:#fff;"> ${t_hreducation.college }</td>
											      <td style=" text-align:center;background-color:#fff;">${t_hreducation.education }</td>
											    <td style=" text-align:center;background-color:#fff;"><#if t_hreducation.gdate!=''>${t_hreducation.gdate?string('yyyy-MM-dd')}</#if></td>
											  </tr>
											</#list>
										</table>
                                       </td>
                                       </tr>
                                       </#if>
                                       <!---->
                                       <tr>
                                         <td width="15%" class="tab_title"><p>QQ</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hremployee.qq}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>微信</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hremployee.we_chat}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>MSN</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hremployee.msn}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>其它</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hremployee.other}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>备注</p></td>
                                         <td colspan="3" style="background-color:#fff;height:80px;">
                                          ${t_hremployee.remark}
                                         </td>
                                       </tr>  
                                       <tr>
                                         <td width="15%" class="tab_title"><p>入职时间</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hrentryrecord.edate}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>岗位描述</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hrentryrecord.post}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>工作性质</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												<#if  t_hrentryrecord.field==1>全职</#if>
												<#if  t_hrentryrecord.field==2>兼职</#if>
                                         </td>
                                         <td  width="15%" class="tab_title"><p>劳动合同有效期</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           <#if t_hrentryrecord.labor_date !="">${t_hrentryrecord.labor_date?string('yyyy-MM-dd')}</#if>
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>社保缴费日期</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												<#if t_hrentryrecord.ss_date !="">${t_hrentryrecord.ss_date?string('yyyy-MM-dd')}</#if>
                                         </td>
                                         <td  width="15%" class="tab_title"><p>社保缴费基数</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hrentryrecord.ss_base}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="15%" class="tab_title"><p>公积金缴费日期</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												<#if t_hrentryrecord.af_date !="">${t_hrentryrecord.af_date?string('yyyy-MM-dd')}</#if>
                                         </td>
                                         <td  width="15%" class="tab_title"><p>公积金缴费基数</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                           ${t_hrentryrecord.af_base}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>档案所在地</p></td>
                                         <td colspan="3" style="background-color:#fff;">
                                          ${t_hrentryrecord.archives}
                                         </td>
                                       </tr>
                                       <tr>
														<td  width="15%" class="tab_title"><p>转正时间</p></td>
								                 	<td  width="35%" style="background-color:#fff;">${t_hrentryrecord.fdate}</td>
								                 	<td  width="15%" class="tab_title"><p>转正操作人</p></td>
								                 	<td  width="35%" style="background-color:#fff;">${t_hrentryrecord.fuser.name}</td>
								               </tr>
								               <tr>
														<td  width="15%" class="tab_title"><p>转正操作时间</p></td>
								                 	<td  width="35%" style="background-color:#fff;">${t_hrentryrecord.frtime}</td>
								                 	<td  width="15%" class="tab_title"><p>离职时间</p></td>
								                 	<td  width="35%" style="background-color:#fff;">${t_hrentryrecord.qdate}</td>
								               </tr>
								               <tr>
														<td  width="15%" class="tab_title"><p>离职操作人</p></td>
								                 	<td  width="35%" style="background-color:#fff;">${t_hrentryrecord.quser.name}</td>
								                 	<td  width="15%" class="tab_title"><p>离职操作时间</p></td>
								                 	<td  width="35%" style="background-color:#fff;">${t_hrentryrecord.qrtime}</td>
								               </tr>
                                       <!---->
                                       <tr>
                                       <td colspan="4">
                                       <table border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
					  <tr>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:30px;">序号</td>
					     <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">证件名称</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">证书编号</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">专业</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">等级</td>
					     <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">签发单位</td>
					      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">签发日期</td>
					      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">有效期(年)</td>
					      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">备注</td>
					  </tr>
					    <#list t_hrcertificatelist as t_hrcertificate>
					    <input type="hidden" value=${t_hrcertificate.id}/>
					  <tr>
					    <td style=" text-align:center;background-color:#fff;">${t_hrcertificate_index + 1}</td>
					    <td style=" text-align:center;background-color:#fff;"> ${t_hrcertificate.name }</td>
					      <td style=" text-align:center;background-color:#fff;">${t_hrcertificate.no }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_hrcertificate.major }</td>
					       <td style=" text-align:center;background-color:#fff;">${t_hrcertificate.grade }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_hrcertificate.issuing_unit }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_hrcertificate.idate }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_hrcertificate.validity_period }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_hrcertificate.remark }</td>
					  </tr>
					</#list>
					</table>
                                       </td>
                                       </tr>
                                       <!---->
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
                                            <td colspan="4" style=" text-align:right;">
                                            <input type="button"  id="button" value="返回" style="cursor:pointer;" onclick="javascript:history.go(-1);" class="sub"/>
                                                                                      
                                            </td>
                                        </tr>
                                        
                                                                    
                                     </table>
                                     
    </div>

