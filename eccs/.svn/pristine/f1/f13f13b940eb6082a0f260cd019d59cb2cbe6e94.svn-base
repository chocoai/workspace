    <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
       <link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_cult/list.htm">培训管理-列表</a> >
<a href="#">培训管理-<#if t_hrtrain.id==null>新增<#else>编辑</#if></a>
</div>
  <!--<form action="/t_liborrow/save.htm" method="post" id="contractForm">  -->
    <form action="/t_cult/save.htm" method="post" id="contractForm">  
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			培训管理表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table" style="margin-top:0px;">
         								<input name="t_hrtrain.id" value="${t_hrtrain.id}" hidden/>
         								
                                       <tr>
                                       
                                         <td width="50" class="tab_title red"><p>主题</p></td>
                                         <td><input id="t_theme" type="text"  class="text_a"  name="t_hrtrain.theme" value="${t_hrtrain.theme}"/></td>
                                         <td width="50" class="tab_title"><p>培训方式</p></td>
                                         <td>
                                         <div >  <select name="t_hrtrain.mode" id="typeid" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
										<option value="">全部</option>
										<option value="1" <#if  t_hrtrain.mode==1>selected='selected'</#if> >内训</option>
									    <option value="2" <#if  t_hrtrain.mode==2>selected='selected'</#if> >外训</option>
									    </select></div>
                                         </td>
                                       </tr>
                                       <td width="50" class="tab_title red"><p>培训时间</p></td>
                                         <td><input id="t_tdate" <#if t_hrtrain.ctime!=null>onFocus="WdatePicker({minDate:'${t_hrtrain.ctime}'})"<#else>onFocus="WdatePicker({minDate:'%y-%M-%d'})"</#if> readonly="true" type="text"  class="text_a"  name="t_hrtrain.tdate" value="${t_hrtrain.tdate}"/></td>
                                       <td width="50" class="tab_title red"><p>培训地点</p></td>
                                         <td><input id="t_tplace" type="text"  class="text_a"  name="t_hrtrain.tplace" value="${t_hrtrain.tplace}"/></td>
                                       <tr>
                                       <td width="50" class="tab_title red"><p>培训内容</p></td>
                                         <td colspan="4"><textarea  style="width:100%;height:80px;" name="t_hrtrain.content" id="t_content">${t_hrtrain.content}</textarea></td>
                                       </tr>
                                       <tr>
                                        <td width="50" class="tab_title red"><p>授课人</p></td>
                                         <td colspan="4"><input id="t_teach" type="text"  class="text_a"  name="t_hrtrain.teach" value="${t_hrtrain.teach}"/></td>
                                       </tr>
                                       <tr>
                                        <td width="50" class="tab_title"><p>受训人员</p></td>
                                        <!--input id="t_hrtraintrainees" type="text" hidden class="text_a"  name="t_hrtrain.trainee" value="${t_hrtrain.trainee}"/-->
                                        <input id="traineesid" name="t_hrtrain.trainee" value="${t_hrtrain.trainee}" hidden/>
                                         <td colspan="4"><input id="trainees" type="text" onclick="gettrainee()" readonly class="text_a"  value="${t_hrtrain.traineestr}"/></td>
                                       </tr>
                                       <tr>
                                        <td width="50" class="tab_title"><p>备注</p></td>
                                         <td colspan="4"><textarea  style="width:100%;height:80px;" name="t_hrtrain.remark" id="detailid">${t_hrtrain.remark}</textarea></td>
                                       </tr>
                                       <tr><td colspan="4"><div class="t_sub_adds" onclick="attachment();">添加附件</div><div class="t_sub_delete" onclick="detelefile();">删除附件</div></td></tr>
                                       <td colspan="4" id="we">
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
                                       <tr>
                                            <td colspan="4" style=" text-align:right;">
                                            <input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                                            <input type="submit" style="cursor:pointer;" value="保存"  class="sub" />                                              
                                            </td>
                                        </tr>   
           </table>
    </div>
</form>
<script>
	$().ready(function(){
		$("#contractForm").validate({
			//debug : true,
			rules : {
				't_hrtrain.theme' : {
					required : true
				},
				't_hrtrain.tdate' : {
					required : true
				},
				't_hrtrain.tplace' : {
					required : true
				},
				't_hrtrain.content' : {
					required : true
				},
				't_hrtrain.teach' : {
					required : true
				}
			}
		});
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
		var url = "/t_cult/deletefile.htm?Stringid=" + id;
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
	function attachment() {
	   var iWidth=650;                          //弹出窗口的宽度; 
       var iHeight=500;                         //弹出窗口的高度; 
       //获得窗口的垂直位置 
       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
       //获得窗口的水平位置 
       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
      window.open('/t_file/filett_cult.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function returncult(id,name,remarks){
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
	
	
	function removeContact(){
			$("input[type='checkbox']:checked.b").parent().parent().remove();
	}
	function submitForm(){
	var ok=true;
	if($("#t_theme").val()==""){
	ok=false;
	alert("主题不能为空!");
	return;
	}
	if($("#t_tdate").val()==""){
	ok=false;
	alert("培训时间不能为空!");
	return;
	}
	if($("#t_tplace").val()==""){
	ok=false;
	alert("培训地点不能为空!");
	return;
	}
	if($("#t_content").val()==""){
	ok=false;
	alert("培训内容不能为空!");
	return;
	}
	if($("#t_teach").val()==""){
	ok=false;
	alert("授课人不能为空!");
	return;
	}
	if(ok){
	$("#contractForm").submit();
	}
}
function gettrainee(){
	$.ajax({
			type:"post",
			url:'/t_cult/selectUser1.htm?type=1',
			asyn:true,		//false为同步提交
			dataType:'text',	//返回文本
			success:function(d){
			}
		});
		
		 var iWidth=800;                          //弹出窗口的宽度; 
      	 var iHeight=500;                         //弹出窗口的高度; 
       	//获得窗口的垂直位置 
      	 var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
       	//获得窗口的水平位置 
       	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		window.open('/t_cult/selectUser.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
}
	function returnSelectUser(value){
			var values=value.split("==,,,==");
			if(value.trim()!=""){
				$("#trainees").val(values[1]);
				$("#traineesid").val(values[0]);
			}
	}
</script>