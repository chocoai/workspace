<style>
.title {
	text-align: center;
	font-weight: bold;
	font-family: Microsoft YaHei;
	font-size: 20px;
	margin-top: 20px;
	margin-bottom: 20px;
}

.header {
	text-align:center;
	font-weight: bold;
}
</style>

<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/projectManager.htm">项目管理</a>
	>
	<a href="/project/myProjectList.htm?all=true">所有项目列表</a>
	>
	<a href="javascript:window.location.reload();">操作人员分工一览</a>
</div>
<form action="/project/savePerson.htm" method="post" id="contractForm">  
 <div id="content">

 <table border="1" cellspacing="1" cellpadding="1" class="input_table">
  		<div class="title">人员分配表</div>
		<tr>
			<td style="text-align: right;width: 20%">项目名称</td>
			<td colspan="3">
				<input name="project.id" value="${project.id}" type="hidden" />
				${project.name}
			</td>
		</tr>
		<tr>
			<td style="text-align: right;">项目编号</td>
			<td colspan="3">${project.no }</td>
		</tr>

		<tr>
			<td style="text-align: right; font-weight: bold;">有关操作</td>
			<td style="text-align:center;font-weight: bold;">姓名</td>
			<td style="text-align:center;font-weight: bold;">执业资格</td>
			<td style="text-align:center;font-weight: bold;">执业资格注册编号</td>
		</tr>
  	   <#list swtList as template>
			<tr>
				<td class="header red" style="text-align: right;">
					${template.proceStepDef.stepName}
				</td>
				<td style="text-align: left;">
					${template.step3Worker.workUserName}
				</td>
				<td style="text-align: left;" >
					${template.step3Worker.workLevel}
				</td>
				<td style="text-align: left;">
					${template.step3Worker.workLevelNo}
				</td>
			</tr>
			</#list>
			<tr>
				<td colspan="4" style="text-align: right;">
					<input type="button" onclick="location.href ='javascript:history.back();';" value="返回" class="sub" />
					<input type="button" class="sub" onclick="javascript:toPrint(${project.id});" value="打印" />
				</td>
			</tr>
		</table>
      </div>
</form>
<script>
	function toPrint(id){
		   var url = "/project/printCheckPerson.htm?project.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=800;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>
