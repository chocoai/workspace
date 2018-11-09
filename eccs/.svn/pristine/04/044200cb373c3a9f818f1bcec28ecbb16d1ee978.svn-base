<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/systemManager.htm">系统管理</a> >
<a>组织列表</a>
</div>
<input type="hidden" name="name" id="name" value="${dept.name}">
<form action="list.htm" method="get" id="searchForm">
	<input type="hidden" name="id" id="id" value="${dept.id}">
</form>
<div class="about">
	<div class="left" style="height: 500px;" id="ist">

		<link type="text/css" rel="stylesheet" href="/js/dtree/dtree.css">
		<script src="/js/dtree/dtree.js"></script>
		<script type="text/javascript">
			var d = new dTree('d');
			d.add('${rootDept.id}','-1','${rootDept.name}',"javascript:search('${rootDept.id}')");
			<#list deptList as dept>
				d.add('${dept.id}','${dept.pid}','${dept.name}',"javascript:search('${dept.id}')");
			</#list>
			document.write(d);
		</script>
	</div>
	<div class="list_table stripe"
		style="float: left; width: 70%; min-width: 700px;">
		<div class="article"
			style="margin: 0; clear: both; width: 100%; margin-left: 20px;">
			<a href="javascript:addDept()"><span class="sub_add">添加部门</span>
			</a>
			<a href="javascript:editDept()"><span class="sub_add">编辑部门</span>
			</a>
			<a href="javascript:deleteDept()"><span class="sub_add">删除部门</span>
			</a>
			<a href="javascript:selectUser()"><span class="sub_add">添加用户</span>
			</a>
		</div>
	</div>
	<input type="hidden" id="userID">
	<table border="1" cellspacing="1" cellpadding="1"
		class="list_table stripe"
		style="float: left; width: 70%; min-width: 700px; margin-left: 20px;">
		<tr class="head">
			<td
				style="background-color: #e6f6ff; text-align: center; width: 30px;">
				<p>
					编号
				</p>
			</td>
			<td width="62"
				style="background-color: #e6f6ff; text-align: center; width: 100px;">
				<p>
					用户名
				</p>
			</td>
			<td width="120"
				style="background-color: #e6f6ff; text-align: center;">
				<p>
					姓名
				</p>
			</td>
			<td width="80" style="background-color: #e6f6ff; text-align: center;">
				<p>
					性别
				</p>
			</td>
			<td width="192"
				style="background-color: #e6f6ff; text-align: center;">
				<p>
					角色
				</p>
			</td>
			<td width="192"
				style="background-color: #e6f6ff; text-align: center;">
				<p>
					创建时间
				</p>
			</td>
			<td width="120"
				style="background-color: #e6f6ff; text-align: center;">
				<p>
					操作
				</p>
			</td>
		</tr>
		<#list duList as du>
		<tr>
			<td width="44" style="text-align: center;">
				<p>
					${du_index + 1}
				</p>
			</td>
			<td>
				<p>
					${du.user.username}
				</p>
			</td>
			<td>
				<p>
					${du.user.name}
				</p>
			</td>
			<td>
				<p>
					${du.user.sexStr}
				</p>
			</td>

			<td>
				<p>
					<#list du.roleList as role> ${role.name}, </#list>
				</p>
			</td>

			<td>
				<p>
					${du.ctime}
				</p>
			</td>
			<td>
				<p>
					<a href="javascript:deleteUser(${du.id})" title="删除">删除</a>
					<a href="javascript:setRole(${du.user.id})">设置角色</a>
				</p>
			</td>
		</tr>
		</#list>
	</table>
	<div style="clear: both;"></div>

</div>
<script src="/js/jquery-1.6.2.min.js"></script>
<script src="/js/jquery.contextmenu.js"></script>
<link rel="stylesheet" href="/js/jquery.contextmenu.css">
<style type="text/css">
body {
	margin: 0px;
	background-image: url(icons/pattern.png);
}

.m {
	margin-right: auto; margin-left: auto;
	width: 500px;
	height: 500px;
	border: 1px dashed #666666;
	padding: 6px;
	margin-top: 50px;
	margin-bottom: 10px;
	background-color: #2E2E2E;
}
</style>
<script>
	   $(function() {
        $('#ist').contextPopup({
          title: '部门移动',
          items: [
            {label:'上移', icon:'',  action:function() { 
           		var id=  $('#id').val();
   				// location.href="/dept/orderS.htm?id="+id;
   				$.ajax({
					type:"post",
					url:'/dept/orderS.htm?id=' + id,
					asyn:true,		//false为同步提交
					dataType:'text',	//返回文本
					success:function(d){
						if (d == '1') {
							alert('不能上移');
						} else {
							window.parent.location.reload();
						}
					}
				})
			} 
            },
            {label:'下移', icon:'', action:function() {
     			var id=  $('#id').val();
   				// location.href="/dept/orderX.htm?id="+id;
             	$.ajax({
					type:"post",
					url:'/dept/orderX.htm?id=' + id,
					asyn:true,		//false为同步提交
					dataType:'text',	//返回文本
					success:function(d){
						if (d == '1') {
							alert('不能下移');
						} else {
							window.parent.location.reload();
						}
					}
				})
             }
             },
            null, 
          ]
        });
      });
	
	function search(id) {
		$("#id").val(id);
		$("#searchForm").submit();
	}
	
	function deleteUser(duId) {
		var is='${rootDept.id}';
		var	id = $("#id").val();
		if(is.length=='6'){
			if(id.length=='6'){
				alert('没有权限删除，请联系管理员');
			}
			else{
				if (!confirm("确定删除此用户")) {
					return;
				}
				
				$.ajax({
					type:"get",
					url:'/dept/deleteUser.htm?duId=' + duId,
					asyn:true,		//false为同步提交
					dataType:'text',	//返回文本
					success:function(d){
						if (d == '1') {
							window.parent.location.reload();
						} else {
							alert('删除失败');
						}
					}
				})
			}
		}else{
			if (!confirm("确定删除此用户")) {
				return;
			}
			
			$.ajax({
				type:"get",
				url:'/dept/deleteUser.htm?duId=' + duId,
				asyn:true,		//false为同步提交
				dataType:'text',	//返回文本
				success:function(d){
					if (d == '1') {
						window.parent.location.reload();
					} else {
						alert('删除失败');
					}
				}
			})
			}
		
	}
	
	function addDept() {
	   var	id = $("#id").val();
	   var iWidth=500;                          //弹出窗口的宽度; 
       var iHeight=300;                         //弹出窗口的高度; 
       //获得窗口的垂直位置 
       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
       //获得窗口的水平位置 
       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
	   window.open('/dept/new.htm?id=' + id,'新增部门','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	
	function editDept() {
		var	id = $("#id").val();
	   var iWidth=500;                          //弹出窗口的宽度; 
       var iHeight=300;                         //弹出窗口的高度; 
       //获得窗口的垂直位置 
       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
       //获得窗口的水平位置 
       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
	   window.open('/dept/edit.htm?id=' + id,'编辑部门','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	
	function deleteDept() {
		var is='${rootDept.id}';//顶层部门
		var	id = $("#id").val();//选中的部门
		if(is.length=='6'){
			if(id.length=='6'){
				alert("没有权限删除，请联系管理员");
			}else{
				var name = $('#name').val();
				var id = $('#id').val();
				
				if (!confirm("确定删除 " + name)) {
					return;
				}
				$.ajax({
					type:"get",
					url:'/dept/delete.htm?id=' + id,
					asyn:true,		//false为同步提交
					dataType:'text',	//返回文本
					success:function(d){
						if (d == '1') {
							window.parent.location.reload();
						}else {
							alert('删除失败');
						}
					}
				})
			}
		}else{
			var name = $('#name').val();
			var id = $('#id').val();
			
			if (!confirm("确定删除 " + name)) {
				return;
			}
			$.ajax({
				type:"get",
				url:'/dept/delete.htm?id=' + id,
				asyn:true,		//false为同步提交
				dataType:'text',	//返回文本
				success:function(d){
					if (d == '1') {
						window.parent.location.reload();
					}else {
						alert('删除失败');
					}
				}
			})
		}
	}
	
	//向部门中添加用户
	function selectUser() {
		$.ajax({//清空session
			type:"get",
			url:'/user/selectUser4.htm?type=1',
			asyn:true,		//false为同步提交
			dataType:'text',	//返回文本
			success:function(d){
			}
		});
		var is='${rootDept.id}';
		var	id = $("#id").val();
		if(is.length=='6'){
			if(id.length=='6'){
				alert("没有权限添加，请联系管理员");
			}else{
				   var iWidth=800;                          //弹出窗口的宽度; 
			       var iHeight=500;                         //弹出窗口的高度; 
			       //获得窗口的垂直位置 
			       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
			       //获得窗口的水平位置 
			       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
				   window.open('/user/selectUser1.htm','选择用户','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
			}
		}else{
			    var iWidth=800;                          //弹出窗口的宽度; 
		        var iHeight=500;                         //弹出窗口的高度; 
		        //获得窗口的垂直位置 
		        var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		        //获得窗口的水平位置 
		        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
			    window.open('/user/selectUser1.htm','选择用户','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
		}
	}
	function returnParam1(value){
				if (value != null && value != "==,,,==") {
					var userIds = value.split('==,,,==')[0];
					var deptId = $("#id").val();
					$.ajax({
						type:"get",
						url:'/dept/addUser.htm?id=' + deptId + '&userIds=' + userIds,
						asyn:true,		//false为同步提交
						dataType:'text',	//返回文本
						success:function(d){
								alert('添加成功');
								window.parent.location.reload();
						}
					})
			}
	}
	//给用户添加角色
	function setRole(userId){
		$("#userID").val(userId);
		var iWidth=800;                          //弹出窗口的宽度; 
        var iHeight=500;                         //弹出窗口的高度; 
        //获得窗口的垂直位置 
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
        //获得窗口的水平位置 
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
	    window.open('/role/selectRole.htm?deptId=${id}&userId=' + userId,'选择角色','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
		
	}
	function returnParamRole(roleIdList){
			var userId = $("#userID").val();
			$.ajax({
					type:"get",
					url:'/role/saveUserRole.htm?deptId=${id}&roleIdList=' + roleIdList + '&userId=' + userId,
					asyn:true, 
					dataType:'text', 
					success:function(d){
						if (d == '1') { 
							alert('操作成功');
							window.parent.location.reload();
						} else {
							alert('操作失败');
						}
					}
				})
	}
	
	function selectRole(userId) {
		$("#userID").val(userId);
		var	id = $("#id").val();
		var is='${rootDept.id}';
		if(is.length=='6'){
			if(id.length=='6'){
				alert("没有权限添加，请联系管理员");
			}else{
			   var iWidth=800;                          //弹出窗口的宽度; 
		       var iHeight=500;                         //弹出窗口的高度; 
		       //获得窗口的垂直位置 
		       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		       //获得窗口的水平位置 
		       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
			   window.open('/role/selectRole.htm?deptId=${id}&userId=' + userId,'选择角色','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
			}
		}else{
			   var iWidth=800;                          //弹出窗口的宽度; 
		       var iHeight=500;                         //弹出窗口的高度; 
		       //获得窗口的垂直位置 
		       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		       //获得窗口的水平位置 
		       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
			   window.open('/role/selectRole.htm?deptId=${id}&userId=' + userId,'选择角色','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
		}
	}

	
</script>
