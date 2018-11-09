<link href="../../../css/main.css" rel="stylesheet" type="text/css">

<div id="main">	
		<div id="content">
		
			<form action="/t_liborrow/zzlist.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                     <table  border="1"  align="center" cellpadding="1" cellspacing="1" style="width:100%; margin:0 auto; min-width:980px;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:80px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center" >证照名称:</div></td>
                     <td style="width:60px"><input name="liregistration.licname" type="text" value="${liregistration.licname}" style=" float:left;line-height:22px; height:30px; width:150px;text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:80px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center" >证照编号:</div></td>
                     <td style="width:60px"><input name="liregistration.licnumber" type="text" value="${liregistration.licnumber}" style=" float:left;line-height:22px; height:30px; width:150px;text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                    	<td><div class="sub_sear" style="margin:6px 0px 0px 0px;" onclick="javascript:$('#searchForm').submit()">查询</div></td>
                     <td></td>
                     </tr>
                     </table>
             </div>
         </form>
		
		<table width="90%" border="1" align="center" cellpadding="1" cellspacing="1" class="input_table">
			<tr class="head">
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:8%;">
					<p>序号</p>
				</td>
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:30%;">
					<p>证照名称</p>
				</td>
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:25%;">
					<p>证照编号</p>
				</td>
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:30%;">
					<p>备注说明</p>
				</td>
				<td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:7%;">
					<p><input id="qxcheckbox"  type="checkbox"/></p>
				</td>
		
			</tr>
			<#list pageBean.list as liregistration>
			<tr>
				<td width="30" style="text-align: center;background-color:#fff;">
					<input type="text" id="id" name="id" value="${liregistration.id}" hidden/>
					<p>${liregistration_index+1}</p>
				</td>
				<td style="background-color:#fff;">
					<input type="text" name="licname" value="${liregistration.licname}" hidden/>
					<p>${liregistration.licname}</p>
				</td>
				<td style="background-color:#fff;">
				<input type="text" name="licnumber" value="${liregistration.licnumber}" hidden/>
					<p>${liregistration.licnumber}</p>
				</td>
				<td style="background-color:#fff;">
				<input type="text" name="explain" value="${liregistration.explain}" hidden/>
					<p>${liregistration.explain}</p>
				</td>
				<td style="text-align: center;background-color:#fff;">
					<input name="a" type="checkbox"  value="${liregistration.id}" />
					
				</td>
			</tr>
			</#list>
		</table>
		<div style="width:90%; margin:0 auto; min-width:980px;">
			<#import "/WEB-INF/tld/page.ftl" as tt>
			<@tt.page pageBean=pageBean formId="searchForm" />
		</div>
		<div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
			<a href="javascript:window.close();">关闭</a>
			<a href="javascript:window.addUser();">确认</a>
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
		<script language="JavaScript" src="../../../js/jquery-1.9.1.min.js"></script>
		<script language="JavaScript" src="../../../js/jquery-1.6.2.min.js"></script>
		<script language="JavaScript" src="../../../js/jquery-1.4.2.js"></script>
	<script>
		
		$(function(){
//		var objid= window.opener.document.getElementById("paramID");
//		var idsz=objid.split(",");
//		for(var i=0;i<idsz.length;i++){
//		$("input[name='a']").each(function(){
//		if(idsz[i].trim()==$(this).val().trim()){
//			$(this).attr("checked",'true');
//		}
//		});
//		}
		$("#qxcheckbox").click(function(){
		
		if($("#qxcheckbox").is(':checked')){
		$("[name='a']").attr("checked",'true');
		}else{
		$("[name='a']").removeAttr("checked",'true');
		}
		
		$("input[name='a']").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		
		});
		});
		function addUser() {
		    var id=new Array();
		    var licname=new Array();
		    var licnumber=new Array();
		    var explain=new Array(); 
		    
			$("input:checkbox[name='a']:checked").parent().parent().find("input[name='id']").each(function(){
			id.push($(this).val().trim());
			});
			$("input:checkbox[name='a']:checked").parent().parent().find("input[name='licname']").each(function(){
			licname.push($(this).val().trim());
			});
			$("input:checkbox[name='a']:checked").parent().parent().find("input[name='licnumber']").each(function(){
			licnumber.push($(this).val().trim());
			});
			$("input:checkbox[name='a']:checked").parent().parent().find("input[name='explain']").each(function(){
			explain.push($(this).val().trim());
			});
			if(id!=""){
				window.opener.returnRow(id,licname,licnumber,explain);
			}
			
    		window.close();
		}
	</script>
