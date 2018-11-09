<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_recr/list.htm">招聘计划-列表</a> >
<a href="#">招聘计划-<#if t_hrrecruitment.id==null>新增<#else>编辑</#if></a>
</div>
  <!--<form action="/t_liborrow/save.htm" method="post" id="contractForm">  -->
    <form action="/t_recr/save.htm" method="post" id="contractForm">  
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			招聘计划
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table" style="margin-top:0px;">
                                       <tr>
                                        <input name="t_hrrecruitment.id" value="${t_hrrecruitment.id}" hidden/>
                                         <td width="50" class="tab_title"><p>登记部门</p></td>
                                         <td><input id="t_theme" type="text" value="${dept.name}${t_hrrecruitment.dept_id.name}" class="text_a"  disabled="value" style="background-color:#fff;border:1px solid #fff"/></td>
                                         <td width="50" class="tab_title"><p>登记时间</p></td>
                                         <td><input id="t_theme" value="${rctime}${t_hrrecruitment.rdate}" type="text"  class="text_a"  disabled="value" style="background-color:#fff;border:1px solid #fff"/></td>
                                       <tr>
                                       <td width="50" class="tab_title"><p>人员需求岗位描述</p></td>
                                         <td colspan="4"><textarea  style="width:100%;height:80px;" name="t_hrrecruitment.demand" id="t_content" >${t_hrrecruitment.demand}</textarea></td>
                                       </tr>
                                       </tr>
                                       <tr>
                                       <td width="50" class="tab_title"><p>备注</p></td>
                                         <td colspan="4"><textarea  style="width:100%;height:80px;" name="t_hrrecruitment.remark" id="t_content">${t_hrrecruitment.remark}</textarea></td>
                                       </tr>
                                       </tr>
                                       <tr>
                                        <td width="50" class="tab_title red"><p>处理部门</p></td>
				                         <input name="t_hrrecruitment.hdept_id.id"  id="senderDeptIdId" type="hidden"  value="${t_hrrecruitment.hdept_id.id}" />
				                        <td colspan="4"><input id="takbranch" onclick="getDept()" readonly="true"  type="text"  class="text_a" name="t_hrrecruitment.hdept_id.name" value="${t_hrrecruitment.hdept_id.name}"/></td>
                                       </tr>
                                       <tr>
                                       <td colspan="5"><div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" ><a href="javascript:addpapers();">新增</a> <a href="javascript:removepapers()">删除</a></div></td>
                                       </tr>
                                      <tr>
                                      <td colspan="4">
                                       <table  border="1" id="papersTableId"  cellspacing="1" cellpadding="1" class="list_table4">
                                         <tr>
										    <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;">序号</td>
										     <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;"><input id="qxcheckbox"  type="checkbox"/></td>
										    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">招聘专业</td>
										    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">岗位</td>
										    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">人数</td>
										    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">备注</td>
										  </tr>
										  <#list list as t_hrrecruitmentitem>
										  <tr>
										   <td style=" text-align:center;background-color:#fff;">${t_hrrecruitmentitem_index + 1}</td>
										    <td style=" text-align:center;background-color:#fff;"><input name="a" type="checkbox" autocomplete="off" value="${t_hrrecruitmentitem.id}" class="a" /></td>
										    <td style=" text-align:center;background-color:#fff;"> <input type="text" name="t_hrrecruitmentitemmajor" onchange="check1($(this));" class="text_a" value="${t_hrrecruitmentitem.major}" /><input name="t_hrrecruitmentitemid" type="hidden" value=${t_hrrecruitmentitem.id}></td>
										      <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_hrrecruitmentitempost" onchange="check($(this));" class="text_a" value="${t_hrrecruitmentitem.post}" /></td>
										    <td style=" text-align:center;background-color:#fff;"><input type="text" onkeypress="return IsNum(event)"  name="t_hrrecruitmentitemno" onchange="check($(this));"  class="text_a" value="${t_hrrecruitmentitem.no}" /></td>
										       <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_hrrecruitmentitemremark"  class="text_a"  value="${t_hrrecruitmentitem.remark}"/></td>
										  </tr>
										  </#list>
                                         </table>
                                       </td>
                                      </tr>
                                      <br/>
                                      <!-- 
                                      <#if t_hrrecruitment.id!="">
                                      <tr>
                                       <input name="t_hropinion.id" value="${t_hropinion.id}"  hidden  />
                                         <td width="50" class="tab_title"><p>处理人:</p></td>
                                         <td><input id="t_theme" type="text"  value="${t_hropinion.user_id.name}" class="text_a"   disabled="value" style="background-color:#fff;border:1px solid #fff"/></td>
                                         <td width="50" class="tab_title"><p>处理时间:</p></td>
                                         <td><input id="t_theme" value="${t_hropinion.cdate}" type="text"  class="text_a"  disabled="value" style="background-color:#fff;border:1px solid #fff"/></td>
                                       </tr>
                                       <tr>
                                       <td width="50" class="tab_title"><p>处理意见:</p></td>
                                         <td colspan="4"><div >  <select name="t_hropinion.opinion" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
										<option value="">全部</option>
										<option value="1" <#if  t_hropinion.opinion==1>selected='selected'</#if> >同意</option>
									    <option value="2" <#if  t_hropinion.opinion==2>selected='selected'</#if> >不同意</option>
									    </select></div></td>
                                       </tr>    
                                       <tr>
                                       <td width="50" class="tab_title"><p>意见明细:</p></td>
                                         <td colspan="4"><textarea  style="width:100%;height:80px;" name="t_hropinion.detail" id="t_content">${t_hropinion.detail}</textarea></td>
                                       </tr>
                                       </#if>
                                      <!-- -->
                                      
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
			//debug : true,
			rules : {
				't_hrrecruitment.hdept_id.name' : {
					required : true
				}
			},
			submitHandler : function(form){
				$(".remove").remove();
				var ok = true;
				$(".a").each(function(){
					if($(this).parent().parent().find('input[name=t_hrrecruitmentitemmajor]').val().trim()==""){
						var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
						$(this).parent().next().append(html);
						ok = false;
					}
					if($(this).parent().parent().find('input[name=t_hrrecruitmentitempost]').val().trim()==""){
						var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
						$(this).parent().next().next().append(html);
						ok = false;
					}
					if($(this).parent().parent().find('input[name=t_hrrecruitmentitemno]').val().trim()==""){
						var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
						$(this).parent().next().next().next().append(html);
						ok = false;
					}else{
						var reg = /^[1-9][0-9]{0,4}$/;
						if(!reg.test($(this).parent().parent().find('input[name=t_hrrecruitmentitemno]').val())){
							var html = '<p class="remove red" style="text-align:center;">*必须填写数字</p>';
							$(this).parent().next().next().next().append(html);
							ok = false;
						}
					}
				});
				$(".b").each(function(){
					if($(this).parent().parent().find('input[name=major]').val().trim()==""){
						var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
						$(this).parent().next().append(html);
						ok = false;
					}
					if($(this).parent().parent().find('input[name=post]').val().trim()==""){
						var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
						$(this).parent().next().next().append(html);
						ok = false;
					}
					if($(this).parent().parent().find('input[name=no]').val().trim()==""){
						var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
						$(this).parent().next().next().next().append(html);
						ok = false;
					}else{
						var reg = /^[1-9][0-9]{0,4}$/;
						if(!reg.test($(this).parent().parent().find('input[name=no]').val())){
							var html = '<p class="remove red" style="text-align:center;">*必须填写数字</p>';
							$(this).parent().next().next().next().append(html);
							ok = false;
						}
					}
				});
				if(($(".a").length+$(".b").length)>0){
					if(ok){
						form.submit();
					}
				}else{
					var html = '<tr class="remove">';
					html += '<td colspan="6" class="red" style="text-align:center;">*招聘清单不能为空 </td>';
					html += '</tr>';
					$("#papersTableId").append(html);
				}
			}
		});
	});
var i=0;
var flag = true;
var id="";
var j=0;
$(function(){
	$("#qxcheckbox").click(function(){
		
		if($("#qxcheckbox").is(':checked')){
		$("[name='a']").attr("checked",'true');
		$(".b").attr("checked",'true');
		}else{
		$("[name='a']").removeAttr("checked",'true');
		$(".b").removeAttr("checked",'true');
		}
		
		$("input[name='a']").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		$(".b").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		});
	});
	function addpapers(){
		$(".remove").remove();
		j = $('#papersTableId tr').length-1;
		j++;
		var html= '<tr>';
		html += '<td style="text-align:center;">'+j+'</td>';
		html += '<td style="text-align:center;"><input type="checkbox" class="text_a b"  /></td>';
		html += '<td style="text-align:center;"><input type="text" name="major" onchange="check($(this));" class="text_a"  /></td>';
		html += '<td style="text-align:center;"><input type="text" name="post" onchange="check($(this));" class="text_a"  /></td>'; 
		html += '<td style="text-align:center;"><input type="text" name="no" onchange="check($(this));" onkeypress="return IsNum(event)" class="text_a"  /></td>';
		html += '<td style="text-align:center;"><input type="text" name="remark"  class="text_a"  /></td>';
		$('#papersTableId').append(html);
	}
function removepapers(){
if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      var cc=$("input[type='checkbox']:checked.b").length;
      if(id=="" && cc==0){
      alert("请选择删除项!");
      return;
      }
      if(id==""&&cc!=0){//当删除的只是新增的时候，重置序号
      	j = $('input:checkbox[name=a]:unchecked.a').length;
      	$("input[type='checkbox']:unchecked.b").each(function(i){
				$(this).parent().prev().text(++j);
	      });
      }
      if(id!=""){
		var url = "/t_recr/deletecls.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					$('input:checkbox[name=a]:unchecked.a').each(function(i){//删除了包含原始存在的证照时重置序号
						$(this).parent().prev().text(i+1);
			      });
			      j = $('input:checkbox[name=a]:unchecked.a').length;
			      $("input[type='checkbox']:unchecked.b").each(function(i){//删除了包含原始存在的证照时重置序号
						$(this).parent().prev().text(++j);
			      });
					//window.parent.location.reload();
					//location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
		}
		$("input[type='checkbox']:checked.a").parent().parent().remove();
		$("input[type='checkbox']:checked.b").parent().parent().remove();
	}
	function submitForm(){
	var ok=true;
	if($("#takbranch").val()==""){
	ok=false;
	alert("处理部门不能为空!");
	return;
	}
	for(var i=0;i<$("input[name='major']").length;i++){
	if($("input[name='major']").eq(i).val().trim()==""){
	alert("招聘专业不能为空!!");
	ok=false;
	return;
	}
	}
	for(var i=0;i<$("input[name='post']").length;i++){
	if($("input[name='post']").eq(i).val().trim()==""){
	alert("岗位不能为空!");
	ok=false;
	return;
	}
	}
	
	for(var i=0;i<$("input[name='no']").length;i++){
	var nos=$("input[name='no']").eq(i).val().trim();
	if(nos==""){
	alert("人数不能为空!");
	ok=false;
	return;
	}else if(!nos.match(/^[1-9]\d*$/)){
	ok=false;
	alert("人数格式不正确!");
	}
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


	function IsNum(e) {
      var k = window.event ? e.keyCode : e.which;
      if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
      } else {
          if (window.event) {
              window.event.returnValue = false;
          }
          else {
              e.preventDefault(); //for firefox
          }
      }
	}
	function check($this){
		$this.next().remove();
	}
	function check1($this){
		$this.next().remove();
	}

	</script>