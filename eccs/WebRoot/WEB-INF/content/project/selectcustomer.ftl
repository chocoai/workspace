<!DOCTYPE html>
<html>
	<head>
		<base target="_self">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>工程咨询云工作平台</title>
		<script src="/js/jquery.js"></script>
		<style type="text/css">
			* {
				margin: 0;
				padding: 0;
				border: 0;
			}
			
			body {
				background: repeat-x top center #f3f4ed;
				color: #222;
				font-size: 12px;
			}
			
			a {
				color: #333;
				text-decoration: none;
			}
			
			a:hover {
				color: #333;
				text-decoration: underline;
			}
			
			#main {
				margin: 0 auto;
				width: 100%;
				background-color: #fff;
				height: auto;
			}
			
			/*输入部分背景*/
			#main #content {
				float: left;
				width: 100%;
				min-height: 100%;
				background: #f3f4ed no-repeat right bottom;
			}
			
			#main #content table.input_table {
				width: 100%;
				margin: 10px 0px 20px 0px;
				text-align: center;
				background-color: #c2cdd3;
			}
			
			#main #content table.input_table tr {
				height: 30px;
				line-height: 30px;
			}
			
			#main #content table.input_table tr td {
				background-color: #efefef;
				text-align: left;
				padding-left: 8px;
				padding-right: 8px;
			}
			
			#main #content table.input_table tr td input {
				margin: 0 auto;
				border: 1px solid #c1e9ff;
				line-height: 22px;
			}
			
			#main #content table.input_table tr td input.text_a {
				margin: 0 auto;
				border: 1px solid #c1e9ff;
				line-height: 22px;
				width: 100%;
				height: 22px;
				text-indent: 6px;
			}
			
			#main #content table.input_table tr td.tab_title {
				text-align: center;
				font-weight: bold;
			}
			
			.add_link a {
				float: right;
				width: 60px;
				height: 28px;
				display: block;
				text-align: center;
				line-height: 28px;
				background-color: #01749e;
				color: #fff;
				margin-top: 20px;
				margin-right: 20px;
				margin-bottom: 5px;
				margin-left: 6px;
				text-decoration: none;
				border-radius: 3px;
				font-size: 12px;
			}
			
			.add_link a:hover {
				float: right;
				width: 60px;
				height: 28px;
				display: block;
				text-align: center;
				line-height: 28px;
				background-color: #8e3c00;
				color: #fff;
				margin-top: 20px;
				margin-right: 20px;
				margin-bottom: 5px;
				margin-left: 6px;
				text-decoration: none;
				border-radius: 3px;
				font-size: 12px;
			}
			
			.btn a {
				float: right;
				width: 60px;
				height: 28px;
				display: block;
				text-align: center;
				line-height: 28px;
				background-color: #01749e;
				color: #fff;
				margin-right: 20px;
				margin-bottom: 5px;
				margin-left: 6px;
				text-decoration: none;
				border-radius: 3px;
				font-size: 12px;
			}
			
			.btn a:hover {
				float: right;
				width: 60px;
				height: 28px;
				display: block;
				text-align: center;
				line-height: 28px;
				background-color: #8e3c00;
				color: #fff;
				margin-right: 20px;
				margin-bottom: 5px;
				margin-left: 6px;
				text-decoration: none;
				border-radius: 3px;
				font-size: 12px;
			}
		</style>
	</head>
	<form action="/project/selectCustomer.htm" method="post" id="searchForm">
		<div>
			<input type="text" name="cusName" id="name" placeholder="请输入客户单位" value="${cusName}"
				style="float: left; line-height: 22px; width: 150px; height: 28px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 20px 20px 10px 20px;" />
		</div>
		
		<div class="add_link" style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px;" >
			<a href="javascript:addUpty();">添加客户</a>
		</div>
		
		<div class="add_link" style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px;" >
			<a href="javascript:$('#searchForm').submit();">查询</a>
		</div>
		
	</form>

	<body>
		<div id="main">
			<div id="content">
				<table width="90%" border="1" align="center" cellpadding="1"
					cellspacing="1" class="input_table">
					<tr class="head">
						<td style="text-align: center;width: 60px;">
							<p>
								操作
							</p>
						</td>
						<td style="text-align: center;width: 30px;">
							<p>
								序号
							</p>
						</td>
						<td style="">
							<p>
								委托单位
							</p>
						</td>
						

					</tr>
					<#list pageBean.list as proj>
					<tr>
						<td style="text-align: center;">
							<input name="db" type="radio"
								onclick="addUser('${proj.id}', '${proj.cusName}')" />
						</td>
						<td  style="text-align: center;">
							<p>
								${proj_index + 1}
							</p>
						</td>
						<td>
							<p>
								${proj.cusName}
							</p>
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
	<div style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px" class="btn">
		<a href="javascript:window.close();">关闭</a>
	</div>
	<script>
	function addUser(id,name){
		window.returnValue = id + "," + name;
		window.opener.returnParamUser(id,name);
    	window.close();
	}
	function addUpty() {
	   var iWidth=700;                          //弹出窗口的宽度; 
       var iHeight=500;                         //弹出窗口的高度; 
       //获得窗口的垂直位置 
       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
       //获得窗口的水平位置 
       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
		window.open('/project/newcustomer.htm','新增用户','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function callback() {
　window.location.reload();
　//window.opener.location.reload(); 
	}
	</script>
</html>
