<link href="../../../css/main.css" rel="stylesheet" type="text/css">
<div id="main">	
		<div id="content">
		<form action="/t_seal/choose.htm" method="post" id="searchForms">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%;">
                     <table width="100%">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:80px; height:30px; text-indent:1px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目名称:</div></td>
                     <td style="width:60px"><input name="project.name" type="text" value="${project.name}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:80px; height:30px; text-indent:1px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目编号:</div></td>
                     <td style="width:60px"><input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td><input type="submit" style="cursor:pointer;" class="sub_sear" value="查询"/></td>
                     </tr>
                     </table>
                    </div>
             </div>
         </form>
		<table width="90%" border="1" align="center" cellpadding="1" cellspacing="1" class="input_table">
			<tr class="head">
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:10%;">
					<p>序号</p>
				</td>
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:45%;">
					<p>项目名称</p>
				</td>
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:35%;">
					<p>项目编号</p>
				</td>
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:10%;">
					<p>操作</p>
				</td>
		
			</tr>
			<#list pageBean.list as project>
			<tr>
				<td width="30" style="text-align: center;background-color:#fff;">
					<p>${project_index+1}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${project.name}</p>
				</td>
				<td style="background-color:#fff;">
					<p>${project.no}</p>
				</td>
				<td style="text-align: center;background-color:#fff;">
					<a class="bca" href="javascript:addUser('${project.id}','${project.name}')">选择</a>
				</td>
			</tr>
			</#list>
		</table>
		<div style="width:90%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForms" />
		</div>
		<div style=" display:block; text-align:right;border-bottom:0px" class="add_link" >
			<a href="javascript:window.close();">关闭</a>
		</div>
	</div>
	</div>
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
	<script>
		function addUser(id,name) {
			window.opener.returnUser(id, name);
    		window.close();
		}
		function show(){
		$('#searchForms').submit();
		}
	</script>
