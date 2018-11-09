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
		</style>
	</head>
		<form action="/t_invoice/choose.htm" method="post" id="searchForms">
		     <div  style=" float:left;line-height:30px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 0px 0px 2px;text-align: center;vertical-align:middle">项目名称</div>
			<input name="project.name" value="${project.name}" type="text" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:20px 20px 0px 0px;"  />
		     <div  style=" float:left;line-height:30px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px -20px 0px 0px;text-align: center;">项目编号</div>
			<input name="project.no" value="${project.no}" type="text" style="float: left; line-height: 22px; width:200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 20px 20px 0px 20px;" />
				      <input type="button" class="sub_sear" onclick="javascript:$('#searchForms').submit()" value="查询"  style=" float:left;line-height:22px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 10px 0px 0px;text-align: center;"/>
		<!--
		<a href="javascript:$('#searchForms').submit()">
			<p style="float: left; line-height: 22px; width:50px; height: 30px; text-indent: 6px;  margin: 6px 20px 0px 20px;" size="100px">查询</p>
		</a>-->
         </form>
  <body class="zh14">
	<div id="main">	
		<div id="content">
		<table width="90%" border="1" align="center" cellpadding="1" cellspacing="1" class="input_table">
			<tr class="head">
				<td style="">
					<p>序号</p>
				</td>
				<td style="">
					<p>项目名称</p>
				</td>
				<td style="">
					<p>项目编号</p>
				</td>
				<td style="">
					<p>实际应收</p>
				</td>
				<td style="">
					<p>累计开票</p>
				</td>
				<td style="">
					<p>未开票</p>
				</td>
				<td style="text-align: center;">
					<p>操作</p>
				</td>
		
			</tr>
			<#list pageBean.list as project>
			<tr >
				<td width="30" style="text-align: center;background-color:#fff;">
					<p>${project_index+1}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${project.name}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${project.no}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${project.receivables}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${project.totalinvoice}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${project.noinvoice}</p>
				</td>
				<td style="text-align: center;background-color:#fff;">
					<a class="bca" href="javascript:addUser('${project.id}','${project.no}','${project.receivables}','${project.name}','${project.totalinvoice}',${project.noinvoice})">选择</a>
				</td>
			</tr>
			</#list>
		</table>
	</div>
	</div>
	</body>
		<div>
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForms" />
		</div>
		<div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
			<a href="javascript:window.close();">关闭</a>
		</div>
	<script>
		function addUser(id, no,payAmount,name,totalinvoice,noinvoice) {
    		window.opener.returninvoice(id,no,payAmount,name,totalinvoice,noinvoice);
    		window.close();
		}
		function show(){
		$('#searchForms').submit();
		}
	</script>
