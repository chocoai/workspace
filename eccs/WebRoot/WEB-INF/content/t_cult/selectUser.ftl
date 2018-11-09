<!DOCTYPE html>
<html>
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
		.btn {
				float: left;
				width: 40px;
				height: 26px;
				display: block;
				text-align: center;
				line-height: 26px;
				background-color: #01749e;
				color: #fff;
				margin-right: 20px;
				text-decoration: none;
				margin-top: 5px;
				margin-bottom: 5px;
				margin-left: 6px;
				border-radius: 3px;
				font-size: 12px;
			}
		</style>
	</head>
	<form action="/t_cult/selectUser.htm" method="post" id="searchForm" >  
				<div>
				<input name="name" id="name" placeholder="请输入用户名" value="${name}" type="text" style="float: left; line-height: 26px; width: 300px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 20px 0px 20px;" />
				<input  name="ids" type="hidden" id="ids" value="${values}"/>
			</div>
			<a href="javascript:$('#searchForm').submit()">
				<p class="btn" style="float: left; line-height: 26px; width:50px; height: 30px; text-indent: 6px;  margin: 5px 5px 6px 20px;" size="100px">查询</p>
			</div></a>
			</form>
	<body class="zh14">
	<div id="main">	
		<div id="content">
		<table width="90%" border="1" align="center" cellpadding="1" cellspacing="1" class="input_table">
			<tr class="head">
				<td style="text-align:center;font-weight:bold; background-color:#d3e0f1; width:6%;">
					<p>序号</p>
				</td>
				<td style="text-align:center;font-weight:bold; background-color:#d3e0f1; width:17%;">
					<p>用户账号</p>
				</td>
				<td style="text-align:center;font-weight:bold; background-color:#d3e0f1; width:12%;">
					<p>用户名</p>
				</td>
				<td style="text-align:center;font-weight:bold; background-color:#d3e0f1; width:10%;">
					<p>性别</p>
				</td>
				<td style="text-align:center;font-weight:bold; background-color:#d3e0f1; width:17%;">
					<p>所在部门</p>
				</td>
				<td style="text-align:center;font-weight:bold; background-color:#d3e0f1; width:16%;">
					<p>电话</p>
				</td>
				<td style="text-align:center;font-weight:bold; background-color:#d3e0f1; width:16%;">
					<p>创建时间</p>
				</td>
				<td style="text-align:center;font-weight:bold; background-color:#d3e0f1; width:6%;">
					<!--<p><input id="qxcheckbox"  type="checkbox"/></p>-->
							<p>选择</p>
				</td>
			</tr>
			<#list pageBean.list as  user>
			<tr>
				<td width="30" style="text-align: center;background-color:#fff;">
					<p>${user_index + 1}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${user.username}</p>
				</td>
				<td style="background-color:#fff;">
					<p class="name">${user.name}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${user.sexStr}</p>
				</td>
				<td style="background-color:#fff;">
					<p>
					<#list user.deptList as d>
						${d.name}, 
					</#list>
					</p>
				</td>
				<td style="background-color:#fff;">
					<p>${user.tel}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${user.ctime?string('yyyy-MM-dd')}</p>
				</td>
				<td style="text-align: center;background-color:#fff;">
					<input type="checkbox" name="checkbox" value="${user.id}" onclick="addvalues(this);" />
				</td>
			</tr>
			</#list>
		</table>
			<div>
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
		</div>

		<div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
			<a href="javascript:addUser()">确认</a>
			<a href="javascript: 	window.close();">关闭</a>
		</div>
	</div>
	</div>
	</body>
	<script>
		$(function(){
		var objid=window.opener.document.getElementById("traineesid").val();
		var objids=objid.split(",");
		for(var i=0;i<objids.length;i++){
		$('input:checkbox[name=checkbox]').each(function(){
			if(objids[i]==$(this).val().trim()){
				$(this).attr("checked",'true');
			}
			});
		}		
		$("#qxcheckbox").click(function(){
		
		if($("#qxcheckbox").is(':checked')){
		$("[name='checkbox']").attr("checked",'true');
		}else{
		$("[name='checkbox']").removeAttr("checked",'true');
		}
		
		$("input[name='checkbox']").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		
		});
		});

		var ids="";
		function addvalues(o){
		var start=0;
		ids=o.value;
		if(o.checked) 
		{
		start=1;
		}
		var b=0;	
		$.ajax({
			type:"post",
			url:'/t_cult/selectUser1.htm?type=0&values=' + ids+'&start='+start,
			asyn:true,		//false为同步提交
			dataType:'text',	//返回文本
			success:function(d){
					$("#ids").val(d);
		             b=1;
			}
		});
	}
window.onload=function (){
var idd = document.getElementsByName("checkbox");//checkbox的id名称，自己定义
var obj=window.dialogArguments;
var idv ="";
$.ajax({
			 async: false,//false为同步提交
            type: "POST",
			url:'/t_cult/selectUser1.htm?type=0&oldids=' + obj.ids+'&oldnames='+obj.names,
			dataType:'text',	//返回文本
			success:function(d){
		      	 $("#ids").val(d);
		           idv=d.split("==,,,==");
			}
		});
var arr = idv[0].split(",");

for(i=0;i<idd.length;i++){
     for(n=0;n<arr.length;n++){
     if(idd[i].value==arr[n]){

idd[i].checked = true;//如果有匹配的就选中
     }
     }
}
}
	function addUser(){
     var ids = $("#ids").val();
     window.opener.returnSelectUser(ids);
     window.close();
}

	</script>
</html>
