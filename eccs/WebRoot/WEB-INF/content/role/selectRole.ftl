<!DOCTYPE html >
<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>工程咨询云工作平台</title>
		<script src="/js/jquery.js"></script>
		<style type="text/css">
		*{margin:0;padding:0;border:0;}
		body{background: repeat-x top center #f3f4ed;color: #222;font-size: 12px; }
		a{ color: #333; text-decoration:none;}
		a:hover{ color: #333; text-decoration:underline;}
		#main{ margin:0 auto; width:100%; background-color:#fff; height:auto;}
		/*输入部分背景*/
		#main #content{float:left; width:100%; min-height:100%; background: #f3f4ed no-repeat right bottom;}
		#main #content table.input_table{ width:100%; margin:20px auto; text-align: center; background-color:#c2cdd3;}
		#main #content table.input_table tr{ height:30px; line-height:30px;}
		#main #content table.input_table tr td{ background-color:#efefef; text-align:left; padding-left:8px; padding-right:8px;}
		#main #content table.input_table tr td input{ margin:0 auto; border:1px solid #c1e9ff;line-height:22px;}
		#main #content table.input_table tr td input.text_a{ margin:0 auto; border:1px solid #c1e9ff;line-height:22px; width:100%; height:22px; text-indent:6px;}
		#main #content table.input_table tr td.tab_title{ text-align: center; font-weight:bold;}
		.add_link a{ float:right; width:40px; height:26px; display:block; text-align:center; line-height:26px; background-color:#01749e; color:#fff; margin-right:20px; text-decoration:none;  margin-top:5px; margin-bottom:5px; margin-left:6px;border-radius:3px; font-size:12px;}
		.add_link a:hover{ float:right; width:40px; height:26px; display:block; text-align:center; line-height:26px; background-color:#8e3c00; color:#fff; margin-right:20px; text-decoration:none;  margin-top:5px; margin-bottom:5px; margin-left:6px;border-radius:3px; font-size:12px;}
		</style>
	</head>

	<body>
	<div id="main">	
		<div id="content">
		<table width="90%" border="1" align="center" cellpadding="1" cellspacing="1" class="input_table">
			<tr class="head">
				<td style=" width: 30px;">
					<p>序号</p>
				</td>
				<td style="">
					<p>角色名称</p>
				</td>
				<td style="">
					<p>选择</p>
				</td>
		
			</tr>
			<#list roleList as role>
			<tr>
				<td width="30" style="text-align: center;">
					<p>${role_index + 1}</p>
				</td>
				<td>
					<p>${role.name}</p>
				</td>
				<td style="text-align: center;">
					<input type="checkbox" class="ck_checkbox" id="ck_${role.id}"  name="role" value="${role.id}">
				</td>
			</tr>
			</#list>
		</table>
		<!--
		<a href="javascript:addUserRole()">提交</a>
		-->
		<div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
			<a href="javascript:window.close();">关闭</a>
		</div>
		<div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
			<a href="javascript:addUserRole();">提交</a>
		</div>
		</div>
		</div>
	</body>
	<script>
		init();
		function init() {
			$(".ck_checkbox").each(function() {
				$(this).attr('checked', false);
			});
		
			<#list myRoleList as role>
			id = 'ck_' + '${role.id}';
			$("#" + id).attr('checked', true);
			</#list>
		}
		
		
		function addUserRole() {
			var arr = new Array(); 
			$("input[type='checkbox'][name='role']:checked").each(function(){ 
			     arr.push($(this).val()); 
			}); 
			if(arr.length>0){
				window.opener.returnParamRole(arr.join(","));
			}
    		window.close();
		}
	</script>
</html>
