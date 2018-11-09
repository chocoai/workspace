<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
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
	<input type="hidden" id="hiddenID">
	<input type="hidden" id="hiddenName">
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
				<td class="header">姓名</td>
				<td class="header">执业资格</td>
				<td class="header">执业资格注册编号</td>
			</tr>
			
			<#list swtList as template>
				<tr>
					<td class="header red" style="text-align: right;">
						<input name="typeId" value="${template.proceStepDef.stepCode}" type="hidden"/>
						${template.proceStepDef.stepName}
					</td>
					<td style="text-align: center;">
						<input name="workUserId" id="id${template.proceStepDef.stepCode}" value="${template.step3Worker.workUserId}" type="hidden" class="text_a" />
						<input name="workUserName" id="name${template.proceStepDef.stepCode}" value="${template.step3Worker.workUserName}" readonly="readonly" 
						onclick="getUser('id${template.proceStepDef.stepCode}','name${template.proceStepDef.stepCode}')"
							type="text" class="text_a"
						/>
					</td>
					<td style="text-align: center;" >
						<input name="workLevel" value="${template.step3Worker.workLevel}" type="text" class="text_a" />
					</td>
					<td style="text-align: center;">
						<input name="workLevelNo" value="${template.step3Worker.workLevelNo}" type="text" class="text_a" />
					</td>
				</tr>
			</#list>
			
			<tr>
				<td colspan="4" style="text-align: right;">
					<input type="button" onclick="location.href ='javascript:history.back();';" value="取消" class="sub" />
					<input type="submit" value="保存" class="sub" />
					<input type="button" class="sub" onclick="javascript:toPrint(${project.id});" value="打印" />
				</td>
			</tr>
		</table>
	</div>
</form>

<script>
	$().ready(function() {  
		$("#contractForm").validate({  
			rules : {  
				'workUserName' : { required : true }
			}
		})
	});
	function getUser(userid , nameid){
    	$("#hiddenID").val(userid);
    	$("#hiddenName").val(nameid);
    	
    	var iWidth=700;                          //弹出窗口的宽度; 
   		var iHeight=500;                         //弹出窗口的高度; 
   		//获得窗口的垂直位置 
   		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
   		//获得窗口的水平位置 
   		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
		window.open('/user/selectUser1.htm','',
				'height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + 
				iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
		
	}
	function returnParam1(value){
			var id = $("#hiddenID").val();
			var name = $("#hiddenName").val();
			if (value != null && value != "") {
					var ar = value.split("==,,,==");
					var roleIdList = ar[0];
					$.ajax({
						type:"get",
						url:'/project/saveSwitch.htm?roleIdList=' + roleIdList,
						asyn:true, 
						dataType:'text', 
						success:function(d){
							if (d !=null&&d!="") {
							var arr = d.split("*");
							 	$("#"+id).val(arr[0]);
							 	$("#"+name).val(arr[1]);
							} else {
								window.parent.location.reload();
							}
						}
					})
				}
	
	}
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


