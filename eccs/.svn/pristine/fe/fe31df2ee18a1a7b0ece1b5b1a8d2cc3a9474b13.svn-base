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
		<form action="/user/selectUser3.htm" method="post" id="searchForm" >  
		<input  id="ids"  type="hidden"  name="ids" value="${ids}" />
		<input  name="userids"  type="hidden" id="userids" value="${values}"/>
		<div></div>
		<div>
			<input name="name1" id="name" placeholder="请输入用户名" value="${name1}" type="text" style="float: left; line-height: 22px; width: 300px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 20px 0px 20px;" />
			<input type="button" class="btn" onclick="javascript:$('#searchForm').submit();" value="查询"  style="float: left; line-height: 22px; width:50px; height: 30px; text-indent: 6px;  margin: 6px 20px 0px 20px;" size="100px"/>
			<input type="button" class="btn" onclick="javascript:addUpty();" value="全部共享" style="float: left; line-height: 22px; width:60px; height: 30px; text-indent: 6px;  margin: 6px 20px 0px 20px; red;" size="100px"/>
		</div>
		</form>
	<body class="zh14"  >
	<div id="main">	
		<div id="content">
		<table width="70%" border="1" align="center" cellpadding="1" cellspacing="1" class="input_table">
			<tr class="head">
				<td width="30" style="text-align: center;">
					<p>序号</p>
				</td>
				<td  style="text-align: center;">
					<p>用户名</p>
				</td>
				<td  style="text-align: center;">
					<p>性别</p>
				</td>
				<td  style="text-align: center;">
					<p>电话</p>
				</td>
				<td  style="text-align: center;">
					<p>操作</p>
				</td>
		
			</tr>
			<#list pageBean.list as user>
			<tr>
				<td width="30" style="text-align: center; background-color:#fff;">
					<p>${user_index + 1}</p>
				</td>
				<td style="text-align: center;background-color:#fff;">
					<p>${user.name}</p>
				</td>
				<td style="text-align: center;background-color:#fff;">
					<p>${user.sexStr}</p>
				</td>
				<td style="text-align: center;background-color:#fff;">
					<p>${user.tel}</p>
				</td>
				<td style="text-align: center;background-color:#fff;">
					<input type="checkbox" name="checkbox" value="${user.id}" onclick="addvalues(this);"/>
				</td>
			</tr>
			</#list>
		</table>
	</div>
	</div>
	</body>
	<div>
			<#import "/WEB-INF/tld/page.ftl" as tt>
			<@tt.page pageBean=pageBean formId="searchForm" />
	</div>
	<div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
			<a href="javascript:addUser()">确定</a>
			<a href="javascript:window.close()">关闭</a>
	</div>
	<script>
		var userids="";
	function addvalues(o){
		var start=0;
		userids=o.value;
		if(o.checked) 
		{
			start=1;
		}
		var b=0;	
		$.ajax({
			type:"get",
			url:'/user/selectUser4.htm?type=0&values=' + userids+'&start='+start,
			asyn:true,		//false为同步提交
			dataType:'text',	//返回文本
			success:function(d){
				$("#userids").val(d);
	             b=1;
			}
		});
	}
	
	window.onload=function (){
		var idd = document.getElementsByName("checkbox");//checkbox的id名称，自己定义
		var idv ="";
		$.ajax({
			async: false,//false为同步提交
            type: "POST",
			url:'/user/selectUser4.htm?type=0',
			dataType:'text',	//返回文本
			success:function(d){
		          $("#userids").val(d);
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
		var s = $("#userids").val().split("==,,,==")[0];
		var ss=$('#ids').val();
		window.opener.returnParam3(s+"*"+ss);
		window.close();
	}
	
	function addUpty(){
		var s='-';
		var ss=$('#ids').val();
		window.opener.returnParam3(s+"*"+ss);
	    window.close();
	}
	</script>
</html>
