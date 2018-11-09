<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/projectManager.htm">项目管理</a>
	>
	<a href="javascript:window.location.reload();">所有项目列表</a>
</div>
<div id="content">
	<form action="/project/myProjectList.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px;">
			<div class="article" style="margin: 0; clear: both; width: 100%;">
				<div
					style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px;"
				>项目编号:</div>
				<div>
					<input name="all" id="allid" type="hidden" value="true">
					<input name="project.no" type="text" value="${project.no}"
						style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div
					style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;"
				>项目名称:</div>
				<div>
					<input name="project.name" type="text" value="${project.name}"
						style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div class="sub_sear" onclick="javascript:getMyProject();">查询</div>
				<div class="sub_add" onclick="javascript:addProject();">共享</div>
				<div>
					<input type="hidden" name="projectids" id="projectids" value="${values}" />
				</div>
			</div>
		</div>
	</form>

	<div style="clear: both;"></div>
	<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table5 stripe">
		<tr class="head">
			<td style="background-color: #e6f6ff; text-align: center; width: 2%;">
				<p></p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 3%;">
				<p>序号</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 10%;">
				<p>项目编号</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 15%;">
				<p>项目名称</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 7%;">
				<p>立项日期</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 13%;">
				<p>委托单位</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width:10%;">
				<p>咨询类型</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 10%;">
				<p>紧急程度</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 10%;">
				<p>立项人</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;width: 10%;">
				<p>项目节点</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 10%;">
				<p>操作</p>
			</td>
		</tr>
		<#list pageBean.list as ProjectProcessState>
		<tr>
			<td style="text-align: center;">
				<input type="checkbox" id="checkbox" name="checkbox" value="${project.id}" onclick="addvalues(this);" />
			</td>
			<td style="text-align: center;">
				<p>${ProjectProcessState_index + 1}</p>
			</td>
			<td>${ProjectProcessState.project.no}</td>
			<td>${ProjectProcessState.project.name}</td>
			<td>${ProjectProcessState.project.recordDate}</td>
			<td>${ProjectProcessState.project.customer.cusName}</td>
			<td>${ProjectProcessState.project.serviceType.name}</td>
			<td>${ProjectProcessState.project.urgentType.name}</td>
			<td>${ProjectProcessState.project.recordName}</td>
			<td>${ProjectProcessState.currentStep.stepName}</td>
			<td style="text-align: center;">
				<a href="/project/showcheckPerson.htm?project.id=${ProjectProcessState.project.id}">查看</a>
				<#list ProjectProcessState.project.pmOperator?split(",") as currentUserId> 
					<#if currentUserId == '${user.id}' >
						<a href="/project/checkPerson.htm?project.id=${ProjectProcessState.project.id}">分配人员</a>
					</#if> 
				</#list>
			</td>
		</tr>
		</#list>
		
	</table>
	<div style="width: 90%; margin: 0 auto; min-width: 980px;">
		<#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" />
	</div>
</div>
<script>
	function delProject(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		var url = "/project/delete.htm?project.id=" + id;
		$.ajax({
			type : "post",
			url : url,
			asyn : true,
			dataType : 'text',
			success : function(d) {
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	}

	function getMyProject() {
		document.getElementById("searchForm").action = "/project/myProjectList.htm";
		document.getElementById("allid").value = "true";
		document.getElementById("searchForm").submit();
	}

	var projectids = "";
	function addvalues(o) {
		var start = 0;
		projectids = o.value;
		if (o.checked) {
			start = 1;
		}
		var b = 0;
		$.ajax({
			type : "get",
			url : '/project/selectProject1.htm?type=0&values=' + projectids
					+ '&start=' + start,
			asyn : true, //false为同步提交
			dataType : 'text', //返回文本
			success : function(d) {
				$("#projectids").val(d);
				b = 1;
			}
		});
	}

	window.onload = function() {//获取上一页的checkbox状态
		var idd = document.getElementsByName("checkbox");//checkbox的id名称，自己定义
		var idv = "";
		$.ajax({
			async : false,//false为同步提交
			type : "POST",
			url : '/project/selectProject1.htm?type=0',
			dataType : 'text', //返回文本
			success : function(d) {
				$("#projectids").val(d);
				idv = d;
			}
		});
		var arr = idv.split(",");
		for (i = 0; i < idd.length; i++) {
			for (n = 0; n < arr.length; n++) {
				if (idd[i].value == arr[n]) {
					idd[i].checked = true;//如果有匹配的就选中
				}
			}
		}
	}

	function addProject() {
		$.ajax({
			type : "get",
			url : '/user/selectUser4.htm?type=1',
			asyn : true, //false为同步提交
			dataType : 'text', //返回文本
			success : function(d) {
			}
		});
		var s = $("#projectids").val();
		if (s != null && s != "") {
			  var iWidth=600;                          //弹出窗口的宽度; 
		       var iHeight=300;                         //弹出窗口的高度; 
		       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		       //获得窗口的水平位置 
		       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
			   window.open('/user/selectUser3.htm?ids='+s,'选择用户','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=no, resizable=no,titlebar=no');
		} else {
			alert("请选择共享项目");
		}
	}
	
	function returnParam3(value){
			var arr = value.split("*");
			if(arr[0]!=null&&arr[0]!=""){//arr[0]用户id arr[1]项目id
				$.ajax({
						type:"post",
						url:'/project/saveShare.htm?usersid='+arr[0]+'&shareid='+arr[1],
						asyn:true,
						dataType:'text',
						success:function(d){
								alert('共享成功！！！');
								window.parent.location.reload();
						}
					});
			}else{//没有选择
				alert("请选择用户");
			}
}
</script>


