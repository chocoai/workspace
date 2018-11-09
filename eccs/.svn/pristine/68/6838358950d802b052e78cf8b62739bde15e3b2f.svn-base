<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="/t_seal/list.htm">印章借用-列表</a> >
<a href="#">印章借用-<#if t_sealBorrow.id==null>新增<#else>编辑</#if></a>
</div>
  <!--<form action="/t_liborrow/save.htm" method="post" id="contractForm">  -->
    <form action="/t_seal/save.htm" method="post" id="contractForm" name="contractForm">  
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			印章借用登记表
		</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
                                       <tr>
                                         <td width="50" class="tab_title"><p>登记部门</p></td>
                                         <td><input   type="text" readonly="true" class="text_a" value="${deptname}${t_sealBorrow.dept_id.name}"/></td>
                                         <td width="50" class="tab_title"><p>登记人</p></td>
                                         <td><input   type="text" readonly="true" class="text_a" value="${username}${t_sealBorrow.user_id.name}"/></td>
                                       </tr>
                                       <input name="t_sealBorrow.id" type="hidden" value="${t_sealBorrow.id}"/>
                                       <td width="50" class="tab_title red"><p>项目名称</p></td>
                                       <input name="t_sealBorrow.project_id.id"  id="project_ids" type="hidden" value="${t_sealBorrow.project_id.id}"/>
                                         <td colspan="4">
                                         <!--<select name="t_sealBorrow.project_id.id" id="projectid" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
										<option value="">--请选择项目--</option>
										<#list list as a>
										<option value="${a.id}" <#if  t_sealBorrow.project_id.id=='${a.id}'>selected='selected'</#if>>${a.name}</option>
										</#list>
									    </select>-->
									    <input type="text" id="getprojectss" readonly="true" class="text_a" onclick="getprojects()" name="t_sealBorrow.project_id.name" value="${t_sealBorrow.project_id.name}"/>
                                         </td>
                                       <tr>
                                         <td width="50" class="tab_title red"><p>印章类型</p></td>
                                         <td>
                                         <div >  <select name="t_sealBorrow.type" id="typeid" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
										<option value="">全部</option>
										<#list types as type>
											<#if type==1><option value="${type}" <#if check==1>selected='selected'</#if>>公章</option></#if>
											<#if type==2><option value="${type}" <#if check==1>selected='selected'</#if>>合同章</option></#if>
											<#if type==3><option value="${type}" <#if check==1>selected='selected'</#if>>项目章</option></#if>
											<#if type==4><option value="${type}" <#if check==1>selected='selected'</#if>>其他</option></#if>
										</#list>
									    </select></div>
                                         </td>
                                         <td width="50" class="tab_title"><p>盖章份数</p></td>
                                         <td><input id="countid" type="text"  class="text_a" onkeypress="return IsNum(event)" maxlength="10"  name="t_sealBorrow.count" value="${t_sealBorrow.count}"/></td>
                                       </tr>
                                       <tr>
                                        <td width="50" class="tab_title red"><p>印章内容</p></td>
                                         <td colspan="4"><textarea  style="width:100%;height:80px;" name="t_sealBorrow.detail" id="detailid">${t_sealBorrow.detail}</textarea></td>
                                       </tr>
                                       <tr>
                                         <td width="50" class="tab_title red"><p>归还日期</p></td>
                                         <td><input id="reverttime" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'cusName\')}'})" readonly="true" type="text"  class="text_a"  name="t_sealBorrow.return_date" value="${t_sealBorrow.return_date}"/></td>
                                         <td width="50" class="tab_title"><p>登记时间</p></td>
                                         <td><input id="cusName" readonly="true"  type="text"  class="text_a"  value="${rctime}${t_sealBorrow.rtime}"/></td>
                                       </tr>
                                       <tr>
                                       <td width="50" class="tab_title red"><p>印章主管部门</p></td>
				                         <input name="t_sealBorrow.sealdept_id.id"  id="senderDeptIdId" type="hidden" value="${t_sealBorrow.sealdept_id.id}"  />
				                        <td colspan="4"><input id="takbranch" onclick="getDept()" readonly="true"  type="text"  class="text_a" name="t_sealBorrow.sealdept_id.name" value="${t_sealBorrow.sealdept_id.name}"/></td>
				                        </tr>
				                        
                                        <tr>
                                            <td colspan="4" style=" text-align:right;">
                                            <input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                                            <input type="submit"   value="保存" style="cursor:pointer;" class="sub" />                                              
                                            </td>
                                        </tr>            
           </table>
    </div>
</form>
<script>
	$().ready(function(){
		//此处是做必填字段的提醒的；由于此处做了，所以下面利用alert();方法做的就没用了，可以将其删除
		$("#contractForm").validate( {
			//debug : true,//此处只是用于调试
			rules : {
				't_sealBorrow.project_id.name' : {
					required : true
				},
				't_sealBorrow.type' : {
					required : true
				},
				't_sealBorrow.detail' : {
					required : true
				},
				't_sealBorrow.return_date' : {
					required : true
				},
				't_sealBorrow.sealdept_id.name' : {
					required : true
				},
				't_sealBorrow.count' : {
					positiveNumber : true
				}
			}
		})
	});
var i=0;
var flag = true;
var id="";
	function removeContact(){
	$("input[type='checkbox']:checked.b").parent().parent().remove();
	}
	//function submitForm(){
	//var ok=true;
	//if($("#senderDeptIdId").val().trim()==""){
	//ok=false;
	//alert("印章主管部门不能为空!");
	//return;
	//}
	
	//if($("#getprojectss").val().trim()==""){
	//ok=false;
	//alert("项目名称不能为空!");
	//return;
	//}
	//if($("#typeid").val().trim()==""){
	//ok=false;
	//alert("印章类型不能为空!");
	//return;
	//}
	//if($("#detailid").val().trim()==""){
	//ok=false;
	//alert("印章内容不能为空!");
	//return;
	//}
	//if($("#reverttime").val().trim()==""){
	//ok=false;
	//alert("归还日期不能为空!");
	//return;
	//}
	//if($("#countid").val().trim()!=""){
	//if(isNaN($("#countid").val().trim())){
	//ok=false;
	//alert("印章份数格式不正确!");
	//return;
	//}
	//}
	//if(ok){
	//$("#contractForm").submit();
	//}
//}

	function getDept() {
		window.open('/dept/selectDept.htm','_blank','channelmode=yes,width=400,height=500,left=200,top=100');
	}
	
	function doAfterGetDept(obj){
		$('#senderDeptIdId').val(obj.deptId);
		$('#takbranch').val(obj.deptName);
	}

	function getUser(){
	   	    var iWidth=800;                          //弹出窗口的宽度; 
		   	var iHeight=600;                         //弹出窗口的高度; 
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

		function getprojects(){
			var iWidth=1000;                          //弹出窗口的宽度; 
		   	var iHeight=600;                         //弹出窗口的高度; 
		   	//获得窗口的垂直位置 
		   	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		   	//获得窗口的水平位置 
		   	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2
			window.open('/t_seal/choose.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
		}
		function returnUser(id, name){
			    $("#project_ids").val(id);
				$("#getprojectss").val(name);
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
	</script>