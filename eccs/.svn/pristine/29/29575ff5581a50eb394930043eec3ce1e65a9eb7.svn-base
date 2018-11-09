<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_liregistration/list.htm">证照登记-列表</a> >
<a href="/t_liborrow/list.htm">证照登记-<#if liregistration.id==null>新增<#else>编辑</#if></a>
</div>
  <form action="/t_liregistration/save.htm?liregistration.id=${liregistration.id}" method="post" id="contractForm">  
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			证照登记表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
                                       <tr>
                                         <td width="50" class="tab_title red"><p>证照名称</p></td>
                                         <td><input id="licname" type="text"   class="text_a" name="liregistration.licname" value="${liregistration.licname}"/></td>
                                          <td width="50" class="tab_title red"><p>证照编号</p></td>
                                          <input id="invos" type="hidden" value="${invos}"/>
                                         <td><input id="licnumber" type="text"   class="text_a" name="liregistration.licnumber" value="${liregistration.licnumber}"/></td>
                                        <td width="50" class="tab_title red"><p>状态</p></td>
                                         <td width="25%">
                                         <select id="licstatus" name="liregistration.licstatus">
											<option value="">请选择</option>
											<option value="1" <#if  liregistration.licstatus==1>selected='selected'</#if> >闲置</option>
										    <option value="2" <#if  liregistration.licstatus==2>selected='selected'</#if> >占用</option>
											<option value="3" <#if  liregistration.licstatus==3>selected='selected'</#if> >其他</option>
										 </select>
										 </td>
                                       </tr>
                                       <tr>
                                         <td width="50" class="tab_title red"><p>颁发日期</p></td>
                                         <td><input id="predate" type="text"   class="text_a" name="liregistration.predate" value="${liregistration.predate}" readonly= "true" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'effdate\'||%y-%M-%d)}'})"/></td>
                                         <!-- onFocus="var effdate=$dp.$('effdate');WdatePicker({onpicked:function(){effdate.focus();},maxDate:'#F{$dp.$D(\'effdate\')}'})" -->
                                          <td width="50" class="tab_title red"><p>颁发部门</p></td>
                                         <td><input id="prebranch" type="text"   class="text_a" name="liregistration.prebranch" value="${liregistration.prebranch}"/></td>
                                        <td width="50" class="tab_title red"><p>下次年检日期</p></td>
                                         <td width="25%"><input id="anndate" type="text"   class="text_a" readonly= "true" name="liregistration.anndate" value="${liregistration.anndate}" onClick="WdatePicker({minDate:'#F{$dp.$D(\'predate\')}',maxDate:'#F{$dp.$D(\'effdate\')}'})"/></td>
                                       </tr>
                                       <tr>
                                         <td width="50" class="tab_title"><p>年检部门</p></td>
                                         <td><input  type="text"   class="text_a" name="liregistration.annbranch" value="${liregistration.annbranch}"/></td>
                                          <td width="50" class="tab_title red" ><p>有效期至</p></td>
                                         <td><input id="effdate" type="text"   class="text_a" name="liregistration.effdate" readonly= "true" value="${liregistration.effdate}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'predate\')}'})"/></td>
                                        <td width="50" class="tab_title"><p>网上填报系统</p></td>
                                         <td width="25%"><input  type="text"   class="text_a" name="liregistration.filsystem" value="${liregistration.filsystem}"/></td>
                                       </tr>
                                       <tr>
                                         <td width="50" class="tab_title red"><p>保管部门</p></td>
                                         <input name="liregistration.dept_id.id"  id="senderDeptIdId" value="${liregistration.dept_id.id}"   type="hidden"   />
                                         <td><input  type="text" id="takbranch" readonly= "true"  class="text_a" name="liregistration.takbranch" value="${liregistration.dept_id.name}"  onclick="getDept();"/></td>
                                          <td width="50" class="tab_title red"><p>保管人</p></td>
                                          <input name="liregistration.kuser_id.id" id="userId4"    value="${liregistration.kuser_id.id }" type="hidden" class="text_c"/>
                                         <td><input id="takname" type="text" readonly= "true" onclick="getUser()"  class="text_a" name="liregistration.takname" value="${liregistration.kuser_id.name}"/></td>
                                        <td width="50" class="tab_title"><p>正副本标识</p></td>
                                         <td width="25%">
                                         <select id="cusnatures" name="liregistration.characteristic">
											<option value="">请选择</option>
											<option value="1" <#if  liregistration.characteristic==1>selected='selected'</#if> >正本</option>
										    <option value="2" <#if  liregistration.characteristic==2>selected='selected'</#if> >副本</option>
										 </select>
                                         </td>
                                       </tr>
                                       <tr>
                                       <td width="50" class="tab_title"><p>年检材料</p></td>
                                         <td colspan="5"><textarea  style="width:100%;height:80px;" name="liregistration.material">${liregistration.material}</textarea></td>
                                       </tr>
                                       <tr>
                                       <td width="50" class="tab_title"><p>年检记录</p></td>
                                         <td colspan="5"><textarea  style="width:100%;height:80px;" name="liregistration.record">${liregistration.record}</textarea></td>
                                       </tr>
                                       <tr>
                                       <td width="50" class="tab_title"><p>备注说明</p></td>
                                         <td colspan="5"><textarea  style="width:100%;height:80px;" name="liregistration.explain">${liregistration.explain}</textarea></td>
                                       </tr>
                                       <tr><td colspan="6"><div class="t_sub_adds" onclick="attachment();">添加附件</div><div class="t_sub_delete" onclick="detelefile();">删除附件</div></td></tr>
                                       <tr>
                                        <td colspan="6" id="we">
                                        <table width="100%" border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
                                        <tr>
                                        <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;">序号</td>
									     <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;"><input id="qxcheckbox"  type="checkbox"/></td>
									    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:25%">文件名称</td>
									    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">文件描述</td>
                                        </tr>
                                         <#list list as t_file>
										  <tr>
										   <td style=" text-align:center;background-color:#fff;">${t_file_index + 1}</td>
										    <td style=" text-align:center;background-color:#fff;"><input name="files" type="checkbox" autocomplete="off" value="${t_file.id}" class="files" /></td>
										    <td style=" text-align:center;background-color:#fff;"> ${t_file.name}</td>
										      <td style=" text-align:center;background-color:#fff;">${t_file.remarks}</td>
										  </tr>
										  </#list>
                                        </table>
                                        </td>
                                        </tr>
                                        <tr>
                                            <td colspan="8" style=" text-align:right;">
                                            <input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                                            <input type="submit" value="保存"  style="cursor:pointer;" class="sub" />                                              
                                            </td>
                                        </tr> 
                                                   
           </table>
    </div>
</form>
<script>
	$().ready(function(){
	//此处是做必填字段的提醒的；由于此处做了，所以下面利用alert();方法做的就没用了，可以将其删除
		$("#contractForm").validate({
			//debug : true,//此处只是用于调试
			rules : {
				'liregistration.licname' : {
					required : true
				},
				'liregistration.licnumber' : {
					required : true
				},
				'liregistration.licstatus' : {
					required : true
				},
				'liregistration.predate' : {
					required : true
				},
				'liregistration.prebranch' : {
					required : true
				},
				'liregistration.anndate' : {
					required : true
				},
				'liregistration.effdate' : {
					required : true
				},
				'liregistration.takbranch' : {
					required : true
				},
				'liregistration.takname' : {
					required : true
				}
			}
		})
	});
var b=0;
	$(function(){
	$("#qxcheckbox").click(function(){
		
		if($("#qxcheckbox").is(':checked')){
		$("[name='files']").attr("checked",'true');
		$(".c").attr("checked",'true');
		}else{
		$("[name='files']").removeAttr("checked",'true');
		$(".c").removeAttr("checked",'true');
		}
		
		$("input[name='files']").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		$(".c").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		});
	});
function detelefile(){
if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
      $('input:checkbox[name=files]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      var cc=$("input[type='checkbox']:checked.c").length;
      if(id=="" && cc==0){
      alert("请选择删除项!");
      return;
      }
      if(id!=""){
		var url = "/t_liregistration/deletefile.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					//window.parent.location.reload();
					//location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
		}
$("input[type='checkbox']:checked.files").parent().parent().remove();
$("input[type='checkbox']:checked.c").parent().parent().remove();
}
	function attachment(){
		   var iWidth=650;                          //弹出窗口的宽度; 
           var iHeight=500;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
           window.open('/t_file/filet_lire.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	
	function returnlire(id,name,remarks){
		b=$("#contactTableId tr").length-1;
		b++;
		var htmls='<tr>';
		htmls+='<td style="text-align:center; width:42px;">'+b+' <input  name="file_id"  type="hidden" value="'+id+'"/></td>';
		htmls+='<td style="text-align:center; width:42px;"><input  type="checkbox" autocomplete="off" value="'+id+'" class="c" /></td>';
		htmls+='<td style="text-align:center; width:42px;">'+name+'</td>';
		htmls+='<td style="text-align:center; width:42px;">'+remarks+'</td>';
		htmls+='</tr>'
		$("#contactTableId").append(htmls);
	} 
	
	
	function submitForm(){
	var ok=true;
	if($("#licname").val().trim()==""){
	alert("证照名称不能为空!");
	ok=false;
	return;
	}
	if($("#licnumber").val().trim()==""){
	alert("证照编号不能为空!");
	ok=false;
	return;
	}
	if($("#licnumber").val().trim()!=""){
	var str=$("#invos").val();
	var strs=str.split(",,,");
	for(var i=0;i<strs.length;i++){
	if(strs[i]==$("#licnumber").val().trim()){
	alert("发票编号重复!");
	ok=false;
	return;
	}
	}
	}
	if($("#licstatus").val().trim()==""){
	alert("状态不能为空!");
	ok=false;
	return;
	}
	
	var predate=$("#predate").val().trim();
	if(predate==""){
	alert("颁发日期不能为空!");
	ok=false;
	return;
	}else if(!predate.match(/^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/)){
	alser("颁发日期格式不正确!");
	ok=false;
	return;
	}
	if($("#prebranch").val().trim()==""){
	alert("颁发部门不能为空!");
	ok=false;
	return;
	}
	if($("#anndate").val().trim()==""){
	alert("下次年检日期不能为空!");
	ok=false;
	return;
	}
	if($("#effdate").val().trim()==""){
	alert("有效期不能为空!");
	ok=false;
	return;
	}
	if($("#takbranch").val().trim()==""){
	alert("保管部门不能为空!");
	ok=false;
	return;
	}
	if($("#takname").val().trim()==""){
	alert("保管人不能为空!");
	ok=false;
	return;
	}
	if(ok){
	$("#contractForm").submit();
	}
}

	function getDept() {
		window.open('/dept/selectDept.htm','_blank','channelmode=yes,width=400,height=500,left=200,top=100');
	}
	
	function doAfterGetDept(obj){
		$('#senderDeptIdId').val(obj.deptId);
		$('#takbranch').val(obj.deptName);
	}


		function getUser(){
			var iWidth=650;                          //弹出窗口的宽度; 
		   	var iHeight=500;                         //弹出窗口的高度; 
		   	//获得窗口的垂直位置 
		   	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		   	//获得窗口的水平位置 
		   	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;    
		
		    var deptid=$("#senderDeptIdId").val();
		    window.open('/user/selectUser2.htm?deptId='+deptid,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
		}
		function returnParam2(userId, name, userName){
				$("#userId4").val(userId);
				$("#takname").val(name);
		}
	</script>