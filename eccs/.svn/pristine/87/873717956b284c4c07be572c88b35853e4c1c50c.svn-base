<script type="text/javascript" src="/js/jquery.validate.js"></script>
<script type="text/javascript" src="/js/jquery.validate.messages_cn.js"></script>

<div id="map"><img src="/images/home.png" width="19" height="24" /> 当前位置：
	<a href="/workbench.htm">工作台</a> >
	<a href="/systemManager.htm">系统管理</a> >
	<a href="/user/list.htm">用户列表</a> >
	<a href="javascript:;">用户新增</a>
</div>
<form action="save.htm" method="post" name = "userForm" id="userForm">
<input name="id" id="id" type="hidden" value="${u.id}" />
<div id="content">
	<table border="1" cellspacing="1" cellpadding="1" class="input_table">
		<tr>
			<td width="265" class="tab_title red"><p>账号</p></td>
			<td colspan="4">
				<#if u.id == null>
				<input name="u.username" id="username" type="text" class="text_a" value="${u.username}" />
				<#else>
				${u.username}
				</#if>
			</td>
			<td width="265" class="tab_title"><p>&nbsp;</p></td>
		</tr>
		<tr>
			<td class="tab_title red"><p>密码</p></td>
			<td colspan="4"><input name="u.password" id="password" type="text" value="${u.password}" class="text_a" /></td>
			<td width="" class="tab_title"><p>&nbsp;</p></td>
		</tr>
		<tr>
			<td class="tab_title red"><p>姓名</p></td>
			<td colspan="4"><input name="u.name" id="name" type="text" value="${u.name}" class="text_a" /></td>
			<td width="" class="tab_title"><p>&nbsp;</p></td>
		</tr>
		<tr>
			<td class="tab_title red"><p>性别</p></td>
			<td colspan="4">
				<select name="u.sex">
					<option value="1" <#if u.sex == 1>selected=selected</#if> >先生</option>
					<option value="0" <#if u.sex == 0>selected=selected</#if> >女士</option>
				</select>
			</td>
			<td width="85" class="tab_title"><p>&nbsp;</p></td>
		</tr>
        <tr>
			<td class="tab_title red"><p>部门</p></td>
			<td colspan="4">			
                <input type="hidden" id="deptId" name="deptId"   value="${deptIds}" />
			    <input type="text" id="deptName" name="deptName" value="${deptNames}" readonly="readonly" onclick="getDept();" class="text_a" />
 			</td>
			<td width="85" class="tab_title"><p>&nbsp;</p></td>
		</tr>

        <tr>
			<td class="tab_title red"><p>电话</p></td>
			<td colspan="4"><input name="u.tel" id="tel" type="text" value="${u.tel}" class="text_a" /></td>
			<td width="" class="tab_title"><p>&nbsp;</p></td>
		</tr>


		<tr> 
			<td colspan="5" style=" text-align:right;">
				<input type="button" value="返回" class="sub" onclick="history.back()"/> 
				<input type="button" value="保存" class="sub" onclick="javascript:submitForm();" />
			</td>
			<td width="" class="tab_title"><p>&nbsp;</p></td>
		</tr>
	</table>
</div>
</form>

<script>

	$().ready(function() {
		$("#userForm").validate({
			rules : {  
	  	    		 'u.username' : { required : true,username:true,checkNO:true } ,
	  	    		 'u.password' : { required : true,password:true } ,
	  	    		 'u.name' : { required : true } ,
	  	    		 'u.tel' : { isTel : true}
					}  
		})
	});

	function submitForm() {
		$("#userForm").submit();
	}
	
    function getDept(){
		window.open('/dept/selectMultiDept.htm','_blank','channelmode=yes,width=400,height=500,left=200,top=100');
	}
	
	function doAfterGetMultiDept(obj){
		$('#deptId').val(obj.deptId);
		$('#deptName').val(obj.deptName);
	}
	 
</script>