<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a>  >
<a href="/t_customer/list.htm">客户登记-列表</a>>
<a href="#">客户登记-查看</a>
</div>
    <div id="content">
        <table border="1" cellspacing="1" cellpadding="1" class="input_table" width="800px">
        	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">客户信息</div>
        								<!-- <tr><td colspan="6"><h3>客户信息</h3></td></tr> -->
                                       <tr>
                                         <td class="tab_title"  colspan="1" width="134px"><p>客户名称</p></td>
                                         <td  style="background-color:#fff;" width="666px" colspan="5">
												${t_Customer.cusName}
                                         </td>
                                         </tr>
                                         <tr>
                                         <td colspan="1" class="tab_title" ><p>客户性质</p></td>
                                         <td colspan="2" width="266px" style="background-color:#fff;">
                                           <#if t_Customer.cusNature ==1 >委托单位</#if>
											<#if t_Customer.cusNature ==2 >建设单位</#if>
											<#if t_Customer.cusNature ==3 >施工单位</#if>
											<#if t_Customer.cusNature ==4 >设计单位</#if>
                                         </td>
                                         <td class="tab_title " colspan="1" width="133px"><p>客户类别</p></td>
                                         <td style="background-color:#fff;" colspan="2" width="266px">
                                         <#if t_Customer.cusType ==1 >企业客户</#if>
											<#if t_Customer.cusType ==2 >政府客户</#if>
											<#if t_Customer.cusType ==3 >其它</#if>
                                         </td>
                                       </tr>
                                       
                                       <tr>
                                         <td class="tab_title"  colspan="1"><p>组织机构代码</p></td>
                                         <td style="background-color:#fff;"  colspan="2">
											${t_Customer.ogCode}
                                         </td>
                                         <td class="tab_title"  colspan="1"><p>客户信用级别</p></td>
                                         <td style="background-color:#fff;"  colspan="2">
											<#if  t_Customer.cusLevel==1>A</#if>
											<#if  t_Customer.cusLevel==2>B</#if>
											<#if  t_Customer.cusLevel==3>C</#if>
											<#if  t_Customer.cusLevel==4>D</#if>
                                         </td>
                                       </tr>
                                       
                                        <tr>
                                         <td class="tab_title"  colspan="1"><p>法人代表</p></td>
                                         <td style="background-color:#fff;" colspan="2">
											${t_Customer.lega}
                                         </td>
                                         <td class="tab_title" colspan="1"><p>客户主页</p></td>
                                         <td style="background-color:#fff;" colspan="2">
                                          ${t_Customer.cusHomepage}
                                         </td>
                                        </tr>
                                           
                                       <tr>
                                         <td class="tab_title" colspan="1"><p>邮政编码</p></td>
                                         <td style="background-color:#fff;" colspan="2">
                                           ${t_Customer.postCode}
                                         </td>
                                         <td class="tab_title" colspan="1"><p>传真</p></td>
                                         <td style="background-color:#fff;"  colspan="2">
											${t_Customer.fax}	
                                         </td>
                                       </tr>
                                       
                                       <tr>
                                         <td class="tab_title"  colspan="1"><p>开户银行</p></td>
                                         <td style="background-color:#fff;" colspan="2">
                                          ${t_Customer.bankAccount}
                                         </td>
                                         <td class="tab_title"  colspan="1"><p>开户账号</p></td>
                                         <td style="background-color:#fff;" colspan="2">
                                           ${t_Customer.accountOpening}
                                         </td>
                                           </tr>
                                           
                                          <tr>
                                         <td class="tab_title"  colspan="1"> <p>电子邮箱</p></td>
                                         <td style="background-color:#fff;" colspan="2">
											${t_Customer.email}	
                                         </td>
                                         <td class="tab_title" colspan="1"><p>办公电话</p></td>
                                         <td  style="background-color:#fff;" colspan="2">
                                          ${t_Customer.officePhone}
                                         </td>
                                       </tr> 
                                       
                                       <tr>
                                         <td class="tab_title" colspan="1"><p>详细地址</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                          ${t_Customer.address}
                                         </td>
                                       </tr> 
                                       
                                       <tr>
                                         <td class="tab_title"><p>备注</p></td>
                                         <td colspan="5" style="background-color:#fff;height:80px;">
                                          ${t_Customer.remark}
                                         </td>
                                       </tr>
                                        
                      <tr>
    				    <td colspan="6">
					    <table border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
					    <h3>联系人信息</h3>
					  <tr>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:30px;">序号</td>
					     <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">联系人</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">部门</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">职务</td>
					    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">电话</td>
					     <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">手机</td>
					      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">E-Mail</td>
					      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">QQ</td>
					      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">默认联系人</td>
					      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">备注</td>
					  </tr>
					    <#list t_Contact.list as t_Contact>
					    <input type="hidden" value=${t_Contact.id}/>
					  <tr>
					    <td style=" text-align:center;background-color:#fff;">${t_Contact_index + 1}</td>
					    <td style=" text-align:center;background-color:#fff;"> ${t_Contact.contact }</td>
					      <td style=" text-align:center;background-color:#fff;">${t_Contact.deptName }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_Contact.post }</td>
					       <td style=" text-align:center;background-color:#fff;">${t_Contact.phone }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_Contact.telephone }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_Contact.email }</td>
					    <td style=" text-align:center;background-color:#fff;">${t_Contact.qq }</td>
					       <td style=" text-align:center;background-color:#fff;">
					       <#if t_Contact.defContact==1>*</#if>
					       </td>
					    <td style=" text-align:center;background-color:#fff;">${t_Contact.remark }</td>
					  </tr>
					</#list>
					</table></td>
					</tr>
					<tr>
					 <td colspan="6" id="we">
                                        <table width="100%" border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
                                        <tr>
                                        <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:30px;">序号</td>
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
                                            <input type="button"  id="button" value="返回" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                                              <input type="button"  onclick="javascript:toPrint(${t_Customer.id});" value="打印" class="sub"/>                                            
                                            </td>
                                        </tr>
                </table>
    </div>
  <script>
function toPrint(id){
		   var url = "/t_customer/print.htm?t_Customer.id="+id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>
