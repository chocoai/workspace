<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_seal/list.htm">印章借用-列表</a> >
<a href="#">印章借用-处理</a>
</div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			印章借用处理表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
				<tr>
              	 <td width="15%" class="tab_title"><p>登记部门</p></td>
              	 <td  width="35%" style="background-color:#fff;">
					 ${t_sealBorrow.dept_id.name}
             	 </td>
             	 <td  width="15%" class="tab_title"><p>登记人</p></td>
             	 <td  width="35%" style="background-color:#fff;">
                ${t_sealBorrow.user_id.name}
              	 </td>
            </tr>
            <tr>
              <td class="tab_title"><p>项目名称</p></td>
              <td colspan="3" style="background-color:#fff;">
              ${t_sealBorrow.project_id.name}
              </td>
            </tr>
            <tr>
              	<td class="tab_title"><p>印章类型</p></td>
              	<td style="background-color:#fff;">
					<#if t_sealBorrow.type ==1 >公章</#if>
					<#if t_sealBorrow.type ==2 >合同章</#if>
					<#if t_sealBorrow.type ==3>项目章</#if>
					<#if t_sealBorrow.type ==4 >其他</#if>
              	</td>
              	<td class="tab_title"><p>盖章份数</p></td>
              	<td style="background-color:#fff;">
					${t_sealBorrow.count}
              	</td>
            </tr>
            <tr>
              	<td class="tab_title"><p>印章内容</p></td>
              	<td colspan="3" style="background-color:#fff;height:80px;">
               ${t_sealBorrow.detail}
              	</td>
            </tr>
            <tr>
              	<td width="15%" class="tab_title"><p>归还日期</p></td>
              	<td  width="35%" style="background-color:#fff;">
					${t_sealBorrow.return_date}
              	</td>
              	<td  width="15%" class="tab_title"><p>登记时间</p></td>
              	<td  width="35%" style="background-color:#fff;">
               ${t_sealBorrow.rtime}
              	</td>
            </tr>
            <tr>
              	<td class="tab_title"><p>印章主管部门</p></td>
              	<td colspan="3" style="background-color:#fff;">
               ${t_sealBorrow.sealdept_id.name}
              	</td>
            </tr>
					    
		    	<form action="/t_seal/sav.htm?t_sealBorrow.option_id.id=${t_sealBorrow.option_id.id}" method="post" id="contractForm">
			   	<tr>
                 	<td class="tab_title" width="15%"><p>处理人</p></td>
                 	<td width="35%" style="background-color:#fff;">
						${username}
                 	</td>
                 	<td class="tab_title" width="15%"><p>处理日期</p></td>
						<input name="t_sealOption.odate"  type="hidden" value="${rctime}"  />
                 	<td width="35%" style="background-color:#fff;" name="t_sealOption.odate">
						${rctime}
                 	</td>
               </tr>
              	<tr>
	              	<td width="50" class="tab_title red"><p>处理意见</p></td>
	              	<td colspan="3"><div >  <select id="option" name="t_sealOption.option" onchange="javascript:check();"  style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
							<option value="">全部</option>
							<option value="1">准许</option>
				   	 	<option value="2">不准</option>
							<option value="3">占用</option>
			    		</select></div></td>
               </tr>
              	<input name="t_sealBorrow.id" type="hidden" value="${t_sealBorrow.id}"/>    
               <tr>
               	<td width="50" class="tab_title"><p>意见备注</p></td>
               	<td colspan="3"><textarea  name="t_sealOption.detail" style="width:100%;height:80px;"></textarea></td>
               </tr>
	            <tr>
	                <td colspan="5" style=" text-align:right;">
	                <input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
	                <input type="submit" value="保存" style="cursor:pointer;"  class="sub" />                                              
	                </td>
	            </tr>            
				</form>
        	</table>                    
    </div>
    <script>
    $().ready(function(){
		$("#contractForm").validate( {
			//debug : true,
			//此处用rules校验不成功
			submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form
				if($("#option").val().trim()==""){
					$(".remove").remove();
					var html = '<p class="remove red">*必填字段</p>';
					$("#option").parent().append(html);
				}else{
					form.submit();
				}
         }
		})
	});
	function check(){
		if($("#option").val().trim()!=""){
			$(".remove").remove();
		}else{
			var html = '<p class="remove red">*必填字段</p>';
			$("#option").parent().append(html);
		}
	}
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