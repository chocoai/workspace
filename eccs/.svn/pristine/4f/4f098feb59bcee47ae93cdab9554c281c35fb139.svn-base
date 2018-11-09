    <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_liborrow/list.htm">证照借用-列表</a> >
<a href="#">证照借用-查看</a>
 </div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			证照借用表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
              	<td width="15%" class="tab_title"><p>单据No</p></td>
              	<td  width="35%" style="background-color:#fff;">
					${t_liborrow.documents}
              	</td>
              	<td  width="15%" class="tab_title"><p>归还时间</p></td>
              	<td  width="35%" style="background-color:#fff;">
              	${t_liborrow.returntime}
              	</td>
            </tr>
           	<tr>
              	<td class="tab_title"><p>备注</p></td>
              	<td colspan="5" style="background-color:#fff;height:80px;">
               ${t_liborrow.remarks}
              	</td>
            </tr>
            <tr>
              	<td width="15%" class="tab_title"><p>登记人</p></td>
              	<td  width="35%" style="background-color:#fff;">
					${t_liborrow.user.name}
              	</td>
              	<td  width="15%" class="tab_title"><p>登记时间</p></td>
              	<td  width="35%" style="background-color:#fff;">
              	${t_liborrow.rtime}
              	</td>
            </tr>
            <tr>
              	<td class="tab_title"><p>保管部门</p></td>
              	<td colspan="5" style="background-color:#fff;">
               ${t_liborrow.sdeptid.name}
              	</td>
            </tr>
           	<tr>
 				   <td colspan="9">
					   <table border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
							<tr>
						    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:8%;">序号</td>
						    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:18%;">证照名称</td>
						    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:18%;">证照编号</td>
						    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:56%;">备注</td>
						  	</tr>
							<#list list as t_borrow_registration>
						   	<input type="hidden" value=${t_liregistration.id}/>
							  	<tr>
							    	<td style=" text-align:center;background-color:#fff;">${t_borrow_registration_index + 1}</td>
							    	<td style=" background-color:#fff;"> ${t_borrow_registration.registration.licname }</td>
							      <td style=" background-color:#fff;">${t_borrow_registration.registration.licnumber }</td>
							    	<td style=" background-color:#fff;">${t_borrow_registration.registration.explain }</td>
							  	</tr>
							</#list>
						</table>
					</td>
				</tr>
				<tr>
              	<td width="15%" class="tab_title"><p>处理人</p></td>
              	<td  width="35%" style="background-color:#fff;">
					${t_handle.user.name}
              	</td>
              	<td  width="15%" class="tab_title"><p>登记时间</p></td>
              	<td  width="35%" style="background-color:#fff;">
              	<#if t_handle.rtime!=''>${t_handle.rtime?string('yyyy-MM-dd')}</#if>
              	</td>
            </tr>
            <tr>
              	<td class="tab_title"><p>处理意见</p></td>
              	<td colspan="5" style="background-color:#fff;">
                  <#if t_handle.suggestion ==1>准许</#if>
                  <#if t_handle.suggestion ==2>不准</#if>
                  <#if t_handle.suggestion ==3>占用</#if>
              	</td>
            </tr>
            <tr>
              	<td class="tab_title"><p>意见明细</p></td>
              	<td colspan="5" style="background-color:#fff;height:80px;">
               ${t_handle.tremarks}
              	</td>
            </tr>
          	<tr>
              	<td colspan="6" style=" text-align:right;">
              	<input type="button"  id="button" value="返回" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
              	</td>
          	</tr>                    
       	</table>
       </div>                           
    </div>

