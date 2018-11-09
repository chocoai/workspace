    <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_liborrow/list.htm">证照借用-列表</a> >
<a href="#">证照借用-处理</a>
</div>
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			证照借用处理表
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
						    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; width:30px;">序号</td>
						    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">证照名称</td>
						    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">证照编号</td>
						    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">备注</td>
						  	</tr>
						   <#list list as t_borrow_registration>
						    	<input type="hidden" value=${t_liregistration.id}/>
						  		<tr id="t_borrow_registration">
							    	<td style=" text-align:center;background-color:#fff;">${t_borrow_registration_index + 1}</td>
							    	<td style=" text-align:center;background-color:#fff;"> ${t_borrow_registration.registration.licname }</td>
							      <td style=" text-align:center;background-color:#fff;">${t_borrow_registration.registration.licnumber }</td>
							    	<td style=" text-align:center;background-color:#fff;">${t_borrow_registration.registration.explain }</td>
						  		</tr>
							</#list>
						</table>
					</td>
				</tr>
				<form action="/t_liborrow/managesave.htm" method="post" id="contractForm">
					<tr>
               	<td width="15%" class="tab_title"><p>处理人</p></td>
	              	<td width="35%" style="background-color:#fff;">
						${username}
	              	</td>
	              	<td width="15%" class="tab_title"><p>处理日期</p></td>
	              	<td width="35%" style="background-color:#fff;" name="t_sealOption.odate">
						${rctime}
	              	</td>
              	</tr>
              	<tr>
                 	<td width="50" class="tab_title red"><p>处理意见</p></td>
                 	<td colspan="3"><div>  <select id="option" name="handle.suggestion" onchange="javascript:check();" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
					 		<option value="">全部</option>
							<option value="1">准许</option>
					    	<option value="2">不准</option>
							<option value="3">占用</option>
				    		</select></div></td>
              
              	</tr>
               <input name="clid" type="hidden" value="${clid}"/>    
               <tr>
                  <td width="50" class="tab_title"><p>意见备注</p></td>
                  <td colspan="3"><textarea  name="handle.tremarks" style="width:100%;height:80px;"></textarea></td>
               </tr>
             	<tr>
                 	<td colspan="5" style=" text-align:right;">
                 	<input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                 	<input type="submit"  value="保存" style="cursor:pointer;" class="sub" />                                              
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
				if($("#t_borrow_registration").length==0){
					alert("该借用无效，不能处理");
					return;
				}
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
	if($("#t_borrow_registration").length==0){
		alert("该借用无效，不能处理");
		return;
	}
	if(ok){
	$("#contractForm").submit();
	}
}
</script>