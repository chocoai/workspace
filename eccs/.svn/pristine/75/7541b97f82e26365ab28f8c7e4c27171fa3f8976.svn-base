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
        <form action="/project/listForSelect.htm" method="get" id="searchForm">
                     <div  style="float:left;line-height:30px; width:80px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 0px 0px 2px;text-align: center;vertical-align:middle" >项目编号:</div>
                     <input name="project.no" type="text" value="${project.no}"  style=" float:left;line-height:30px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 20px 0px 0px;text-align: center;vertical-align:middle" />
                     <div  style=" float:left;line-height:30px; width:80px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 0px 0px 0px;text-align: center;vertical-align:middle">项目名称:</div>
                     <input name="project.name" type="text" value="${project.name}"  style=" float:left;line-height:30px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 20px 0px 0px;text-align: center;vertical-align:middle" />
                    <div  style=" float:left;line-height:30px; width:80px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 0px 0px 0px;text-align: center;vertical-align:middle" >项目类型:</div>
 
                    <select name="projectTypeId" style="float:left;line-height:30px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 20px 0px 0px;text-align: center;vertical-align:middle"">
					<option value="">全部</option>
					<#list projectTypeList as projectType>
						<option value="${projectType.id}" >${projectType.name}</option>
					</#list>
				  </select>
				      <input type="button" class="sub_sear" onclick="javascript:$('#searchForm').submit()" value="查询"  style=" float:left;line-height:22px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:20px 20px 0px 0px;text-align: center;vertical-align:middle""/>
         </form>
<body class="zh14">
	<div id="main">	
		<div id="content"> 
		<table width="90%" border="1" align="center" cellpadding="1" cellspacing="1" class="input_table">
                                       <tr class="head" style="text-align:center;font-size:13px;">
                                         <td style=" background-color:#e6f6ff; text-align:center; width:30px"><p>序号 </p></td>
                                         <td style=" background-color:#e6f6ff; width:120px"><p>项目编号</p></td>
                                         <td style=" background-color:#e6f6ff; "><p>项目名称</p></td>
                                         <td style=" background-color:#e6f6ff;width: 200px; "><p>委托单位</p></td>
                                         <td style=" background-color:#e6f6ff;width: 60px; "><p>项目类型</p></td>
                                         <td style=" background-color:#e6f6ff;width: 60px; "><p>咨询类型</p></td>
                                         <td style=" background-color:#e6f6ff;width: 60px; "><p>紧急与否</p></td>
                                       </tr>
                            <#list pageBean.list as project>     
                                       <tr style="text-align:center;font-size:12px;" onclick="selectProject('${project.id}','${project.name}','${project.no}','${project.contacton}','${project.projectType.name}','${project.serviceType.id}','${project.serviceType.name}','${project.editorialType.id}','${project.editorialType.name}','${project.customer.cusName}');">
                                         <td style="background-color: #fff;text-align:center;" ><p>${project_index + 1}</p></td>
                                         <td style="background-color: #fff;" >${project.no}</td>
                                         <td style="background-color: #fff;" >${project.name} </td>
                                         <td style="background-color: #fff;" >${project.customer.cusName}</td>
                                          <td style="background-color: #fff;" >${project.projectType.name}</td>
                                         <td style="background-color: #fff;" >${project.serviceType.name}</td>
                                          <td style="background-color: #fff;" >${project.urgentType.name}</td>                         
                					</tr>
   							</#list>
           </table>
           	</div>
	    </div>
	</body>
     <div style="width:100%; margin:0 auto; min-width:980px;">
			<#import "/WEB-INF/tld/page.ftl" as tt>
			<@tt.page pageBean=pageBean formId="searchForm" />
		</div>

<script>

function selectProject(id,name,pno,cno,projectTypeName,serviceTypeId,serviceTypeName,editorialTypeId,editorialTypeName,senderDeptName) {
	window.opener.returnSelectProject(id,name,pno,cno,projectTypeName,serviceTypeId,serviceTypeName,editorialTypeId,editorialTypeName,senderDeptName);
	window.close();
}
</script>
</html>



