<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_recr/list.htm">招聘计划-列表</a> >
<a href="#">招聘计划-查看</a>
</div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			招聘计划表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table" style="margin-top:0px;">
                                       <tr>
                                         <td width="15%" class="tab_title"><p>登记部门</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hrrecruitment.dept_id.name}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>登记时间</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                         ${t_hrrecruitment.rdate}
                                         </td>
                                       </tr>
                                      <tr>
                                         <td class="tab_title"><p>人员需求岗位</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                             ${t_hrrecruitment.demand}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>备注</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                             ${t_hrrecruitment.remark}
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>处理部门</p></td>
                                         <td colspan="5" style="background-color:#fff;">
                                             ${t_hrrecruitment.hdept_id.name}
                                         </td>
                                       </tr>
                                       
                                      <tr>
    				    <td colspan="9">
					    <table border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
					    
					  <tr>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:30px;">序号</td>
					     <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">招聘专业</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">岗位</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">人数</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">备注</td>
					  </tr>
					    <#list list as t_hrrecruitmentitem>
					    <input type="hidden" value=${t_hrrecruitmentitem.id}/>
					  <tr>
					    <td style=" text-align:center;background-color:#fff;">${t_hrrecruitmentitem_index + 1}</td>
					    <td style=" text-align:center;background-color:#fff;"> ${t_hrrecruitmentitem.major }</td>
					      <td style=" text-align:center;background-color:#fff;">${t_hrrecruitmentitem.post }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_hrrecruitmentitem.no }</td>
					       <td style=" text-align:center;background-color:#fff;">${t_hrrecruitmentitem.remark }</td>
					  </tr>
					</#list>
					</table></td></tr>
					<tr>
                                         <td width="15%" class="tab_title"><p>处理人:</p></td>
                                         <td  width="35%" style="background-color:#fff;">
												${t_hropinion.user_id.name}
                                         </td>
                                         <td  width="15%" class="tab_title"><p>登记时间:</p></td>
                                         <td  width="35%" style="background-color:#fff;">
                                         ${t_hropinion.cdate}
                                         </td>
                                       </tr>
                                      <tr>
                                         <td class="tab_title"><p>处理意见:</p></td>
                                         <td colspan="5" style="background-color:#fff;">
                                             <#if t_hrrecruitment.t_hropinion.opinion ==1>同意</#if>
                                             <#if t_hrrecruitment.t_hropinion.opinion ==2>不同意</#if>
                                         </td>
                                       </tr>
                                       <tr>
                                         <td class="tab_title"><p>意见明细:</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                             ${t_hropinion.detail}
                                         </td>
                                       </tr>
                                        <tr>
                                            <td colspan="6" style=" text-align:right;">
                                            <input type="button"  id="button" value="返回" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                                                                                      
                                            </td>
                                        </tr>                    
                                     </table>
                                     
    </div>

