<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_recr/list.htm">招聘计划-列表</a> >
<a href="#">招聘计划-处理</a>
</div>
  <!--<form action="/t_liborrow/save.htm" method="post" id="contractForm">  -->
    <form action="/t_recr/managesave.htm" method="post" id="contractForm">  
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			招聘计划处理表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
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
                                       <input name="t_hrrecruitment.id" value="${t_hrrecruitment.id}" type="hidden"   />
                                         <td width="50" class="tab_title"><p>处理人</p></td>
                                         <td><input id="t_theme" type="text" style="background-color:#ccc" value="${user.name}" class="text_a"   readonly= "true"/></td>
                                         <td width="50" class="tab_title"><p> 时间</p></td>
                                         <td><input id="t_theme" value="${rctime}" type="text"  class="text_a"  readonly= "true "/></td>
                                       </tr>
                                       <tr>
                                       <td width="50" class="tab_title"><p>处理意见</p></td>
                                         <td colspan="4"><div >  <select id="option" name="t_hropinion.opinion" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
										<option value="">全部</option>
										<option value="1" <#if  t_hropinion.opinion==1>selected='selected'</#if> >同意</option>
									    <option value="2" <#if  t_hropinion.opinion==2>selected='selected'</#if> >不同意</option>
									    </select></div></td>
                                       </tr>    
                                       <tr>
                                       <td width="50" class="tab_title"><p>意见明细</p></td>
                                         <td colspan="4"><textarea  style="width:100%;height:80px;" name="t_hropinion.detail" id="t_content"></textarea></td>
                                       </tr>
                                       <tr>
                                            <td colspan="4" style=" text-align:right;">
                                            <input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                                            <input type="submit" value="保存" style="cursor:pointer;" class="sub" />                                              
                                            </td>
                                        </tr>   
           </table>
    </div>
</form>
<script>
	$().ready(function(){
		$("#contractForm").validate({
			rules : {
				't_hropinion.opinion' : {
					required : true
				}
			},
			submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form
				if($("#contactTableId tr").length==1){
					alert("该招聘无效");
				}
				if($("#contactTableId tr").length>1){
					form.submit();
				}
         }
		});
	});
function submitForm(){
	var ok=true;
	if($("#option").val().trim()==""){
	ok=false;
	alert("处理意见不能为空!");
	return;
	}
	if(ok){
	$("#contractForm").submit();
	}
}
	</script>