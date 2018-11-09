    <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_liborrow/list.htm">证照借用-列表</a> >
<a href="#">证照借用-<#if liborrow.id==null>新增<#else>编辑</#if></a>
</div>
  <!--<form action="/t_liborrow/save.htm" method="post" id="contractForm">  -->
    <form action="/t_liborrow/save.htm" method="post" id="contractForm">  
    	<input type="hidden" id="paramID">
    		<div id="content">
    			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
					证照借用表
				</div>
        		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
        			<input name="liborrow.id" value="${t_liborrow.id}" hidden/>
               <tr>
                 	<td width="50" class="tab_title"><p>单据NO</p></td>
                 	<td><input id="cusName"  type="text" readonly="true" class="text_a"  name="liborrow.documents" value="${no}${t_liborrow.documents}"/></td>
                 	<td width="50" class="tab_title red"><p>归还日期</p></td>
                 	<td><input id="t_liborrowreturntime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true" type="text"  class="text_a"  name="liborrow.returntime" value="${t_liborrow.returntime}"/></td>
               </tr>
               <tr>
                	<td width="50" class="tab_title"><p>备注</p></td>
                 	<td colspan="4"><textarea  style="width:100%;height:80px;" name="liborrow.remarks">${t_liborrow.remarks}</textarea></td>
               </tr>
               <tr>
                 	<td width="50" class="tab_title"><p>登记人</p></td>
                 	<td><input id="cusName" readonly="true"  type="text"  class="text_a" value="${name}${t_liborrow.user.name}"/></td>
                 	<td width="50" class="tab_title"><p>登记时间</p></td>
                 	<td><input id="cusName" readonly="true"  type="text"  class="text_a"  name="liborrow.rtime" value="${time}${t_liborrow.rtime}"/></td>
               </tr>
               <tr>
               	<td width="50" class="tab_title red"><p>保管部门</p></td>
                	<input name="liborrow.sdeptid.id"  id="senderDeptIdId"   type="hidden" value="${t_liborrow.sdeptid.id}"  />
               	<td colspan="4"><input id="t_liborrowsdeptid"  onclick="getDept()" readonly="true"  type="text"  class="text_a" name="t_liborrow.sdeptid.name" value="${t_liborrow.sdeptid.name}"/></td>
           		</tr>
               <tr>
				    	<td colspan="4">
				        	<div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
                    		<a href="javascript:addContact();">新增</a> <a href="javascript:removeContact()">删除</a>
				     		</div>
				    		<table border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
							  	<tr>
							    	<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:8%;">序号</td>
							     	<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:8%;"><input id="qxcheckbox"  type="checkbox"/></td>
							    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:18%;">证照名称</td>
							    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:18%;">证照编号</td>
							    	<td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:48%;">备注</td>
							 		<input id="aaab" name="lireid"  type="hidden" />
							  	</tr>
				   			<#list list as t_borrow_registration>
								  	<tr>
								   	<td style=" text-align:center;background-color:#fff;">${t_borrow_registration_index + 1}</td>
								    	<td style=" text-align:center;background-color:#fff;"><input name="a" type="checkbox" autocomplete="off" value="${t_borrow_registration.id}" class="a" /></td>
								    	<td style=" background-color:#fff;"> ${t_borrow_registration.registration.licname}</td>
								      <td style=" background-color:#fff;">${t_borrow_registration.registration.licnumber}</td>
								    	<td style=" background-color:#fff;">${t_borrow_registration.registration.explain}</td>
								  	</tr>
								</#list>
							</table>
						</td>
				    </tr>
                <tr>
                    <td colspan="4" style=" text-align:right;">
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
				'liborrow.returntime' : {
					required : true
				},
				't_liborrow.sdeptid.name' : {
					required : true
				}
			},
			submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
				var sid="";
				var zid="";
				$('.stringid').each(function(){
					if(sid==""){
						sid=$(this).val();
					}else{
						sid=sid+","+$(this).val();
					}
				});
				$('.a').each(function(){
					if(zid==""){
						zid=$(this).val();
					}else{
						zid=zid+","+$(this).val();
					}
				});
				$("#aaab").val(sid);
				if(sid!=""||zid!=""){
					form.submit();   //提交表单   
				}else{
					var html = '<tr class="remove">';
					html += '<td colspan="5" class="tab_title red" style="text-align:center;">必须选择需要借用的证照</td>';
					html += '</tr>';
					$("#contactTableId").append(html);
				}
         }
		})
	});
var i=0;
var flag = true;
var id="";
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
	function addContact(){
		var sid="";
		var zid="";
		 $('.stringid').each(function(){
		 if(sid==""){
		 sid=$(this).val();
		 }else{
		 sid=sid+","+$(this).val();
		 }
		 });
		 $('.a').each(function(){
		 if(zid==""){
		 zid=$(this).val();
		 }else{
		 zid=zid+","+$(this).val();
		 }
		 if(zid!="" && sid!=""){
		 sid=sid+","+zid
		 }
		 });
		$("#paramID").val(sid);
		  
	  	var iWidth=1000;                          //弹出窗口的宽度; 
	   	var iHeight=500;                         //弹出窗口的高度; 
	   	//获得窗口的垂直位置 
	   	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
	   	//获得窗口的水平位置 
	   	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;   
		
		window.open('/t_liborrow/zzlist.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
		
	}
	function returnRow(ids,licnames,licnumbers,explains){
					$("#aaab").val("");
					$("#contactTableId .remove").remove();
					if(ids.indexOf(",")>-1){
						var id=ids.split(",");
					    var licname=licnames.split(",");
					    var licnumber=licnumbers.split(",");
					    var explain=explains.split(","); 
					    for(var t=0;t<id.length;t++){
							i=$("#contactTableId tr").length;
							i=i++;
							var htmls='<tr class="remove">';
							htmls+='<td style="text-align:center;">'+i+'</td>';
							htmls+='<td style="text-align:center;"><input type="checkbox"  class="b" /><input class="stringid" type="hidden" value="'+id[t]+'"/></td>';
							htmls+='<td style="text-align:center;">'+licname[t]+'</td>';
							htmls+='<td style="text-align:center;">'+licnumber[t]+'</td>';
							htmls+='<td style="text-align:center;">'+explain[t]+'</td>';
							htmls+='</tr>';
							$("#contactTableId").append(htmls);
						}
				}else{
							i=$("#contactTableId tr").length;
							i=i++;
							var htmls='<tr class="remove">';
							htmls+='<td style="text-align:center;">'+i+'</td>';
							htmls+='<td style="text-align:center;"><input type="checkbox"  class="b" /><input class="stringid" type="hidden" value="'+ids+'"/></td>';
							htmls+='<td style="text-align:center;">'+licnames+'</td>';
							htmls+='<td style="text-align:center;">'+licnumbers+'</td>';
							htmls+='<td style="text-align:center;">'+explains+'</td>';
							htmls+='</tr>';
							$("#contactTableId").append(htmls);
				}		
	}
	
	
	
	function removeContact(){
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
      	$('input:checkbox[class=b]').each(function(i){
				$(this).parent().prev().text(++$('input:checkbox[name=a]').length);
	      });
      }
      if(id!=""){
		var url = "/t_liborrow/deletecls.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					$('input:checkbox[name=a]').each(function(i){//删除了包含原始存在的证照时重置序号
						$(this).parent().prev().text(i+1);
			      });
			      $('input:checkbox[class=b]').each(function(i){//删除了包含原始存在的证照时重置序号
						$(this).parent().prev().text(++$('input:checkbox[name=a]').length);
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
	if($("#t_liborrowreturntime").val().trim()==""){
	alert("归还日期不能为空!");
	ok=false;
	return;
	}
	if($("#t_liborrowsdeptid").val().trim()==""){
	alert("保管部门不能为空!");
	ok=false;
	return;
	}
	var sid="";
		var zid="";
		 $('.stringid').each(function(){
		 if(sid==""){
		 sid=$(this).val();
		 }else{
		 sid=sid+","+$(this).val();
		 }
		 });
		 //此处虽然其原本的意思是想获取原来的已存在的证件id，但是由于其选择的地方并不是证件的id，所以在保存是会报错
		 //$('.a').each(function(){
		 //if(zid==""){
		 //zid=$(this).val();
		 //}else{
		 //zid=zid+","+$(this).val();
		 //}
		 //if(zid!="" && sid!=""){
		 //sid=sid+","+zid
		 //}
		 //});
		 $("#aaab").val(sid);
	if(ok){
	$("#contractForm").submit();
	}
}

	function getDept() {
		window.open('/dept/selectDept.htm','_blank','channelmode=yes,width=400,height=500,left=200,top=100');
	}
	
	function doAfterGetDept(obj){
		$('#senderDeptIdId').val(obj.deptId);
		$('#t_liborrowsdeptid').val(obj.deptName);
	}

	function getUser(){
	   	    var iWidth=650;                          //弹出窗口的宽度; 
		   	var iHeight=500;                         //弹出窗口的高度; 
		   	//获得窗口的垂直位置 
		   	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		   	//获得窗口的水平位置 
		   	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;      
		    window.open('/user/selectUser.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function returnParam(id,name,username){
			$("#userId4").val(id);
			$("#takname").val(name);
	}
	</script>