
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
			
			#main #content {
				float: left;
				width: 100%;
				min-height: 100%;
				background: #f3f4ed no-repeat right bottom;
			}
			
			.add_link a {
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
			
			.add_link a:hover {
				float: left;
				width: 40px;
				height: 26px;
				display: block;
				text-align: center;
				line-height: 26px;
				background-color: #8e3c00;
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

	<body>
		<form action="save.htm" method="post" id="submitForm">
			<input name="id" id="id" type="hidden" value="${dept.id}" />
			<input name="dept.pid" id="pid" type="hidden" value="${parentDept.id}" />
			<div id="main">
				<div id="content">
					<div align="center" class="list_table stripe"
						style="width: 100%; margin: 20px auto;">
						<div class="article" style="margin: 0; clear: both; width: 100%;">
							<div
								style="float: left; width: 80px; height: 50px; line-height: 40px; margin-left: 40px;">
								上级部门：
							</div>
							<div>
								<input type="text" name="parent.name" value="${parentDept.name}"
									disabled="disabled"
									style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 20px 0px 20px;" />
							</div>
							<div style="clear: both;"></div>
							<div
								style="float: left; width: 80px; height: 50px; line-height: 40px; margin-left: 40px;">
								部门名称：
							</div>
							<div>
								<input type="text" name="dept.name" id="name"
									value="${dept.name}"
									style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 20px 0px 20px;" />
							</div>
							<div style="clear: both;"></div>
							<#if (childDepts?size>0) >
							<div
								style="float: left; width: 80px; height: 50px; line-height: 40px; margin-left: 40px;">
								部门排序：
							</div>
							<div>
								<select name="order1"
									style="float: left; line-height: 22px; width: 150px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 20px 0px 20px;">
									<#list childDepts as dept1>
									<option value="${dept1.id}" <#if dept1.id==dept.id>selected='selected'</#if>>
										${dept1.name}
									</option>
									</#list>
								</select>
							</div>
							</#if>
							<div style="clear: both;"></div>
							<div
								style="float: right; width: 100%; height: 50px; line-height: 40px;">
								<div
									style="display: block; margin-left: 40px; line-height: 40px; text-align: right; border: 1px solid #dadada; border-bottom: 0px"
									class="add_link">
									<a href="javascript:saveDept();">保存</a>
								</div>
								<div
									style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px"
									class="add_link">
									<a href="javascript:window.close();">关闭</a>
								</div>
							</div>
						</div>
					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
		</form>
	</body>
	<script>
	function saveDept() {
		var deptname = document.getElementById("name").value;
		var dept = deptname.replace(/(^\s*)/g, "");
		if (dept.length == 0) {
			alert("请输入部门");
			document.getElementById("name").focus();
		} else {
			$.ajax( {
				cache : true,
				type : "POST",
				url : "/dept/save.htm",
				data : $('#submitForm').serialize(),// 你的formid
				async : false,
				success : function(data) {
					if (data == 1) {
						opener.location.reload();
						window.close();
					}else{
						alert("操作失败");
					}
				}
			});

		}
	}
</script>
</html>
