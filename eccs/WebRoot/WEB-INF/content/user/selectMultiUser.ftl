<!DOCTYPE html>
<html>
<head>
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
	margin: 20px auto;
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
	float: right;
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

<body class="zh14">
	<input type="hidden" id="userIds" />
	<input type="hidden" id="userNames" />
	<div id="main" style="width:98%;">
		<form action="/user/selectMultiUser.htm" method="post" id="searchForm">
			<div
				style="float: left; line-height: 30px; width: 80px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 20px 0px 0px 2px; text-align: center; vertical-align: middle"
			>用户名</div>
			<input name="name1" id="name" value="${name1}" placeholder="请输入用户名" type="text"
				style="float: left; line-height: 22px; width: 300px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 20px 20px 0px 0px;"
			/>
			<input type="button" class="btn" onclick="javascript:$('#searchForm').submit()" value="查询"
				style="cursor: pointer; float: left; line-height: 22px; width: 80px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 20px 10px 0px 0px;"
			/>
			<div
				style="float: left; line-height: 30px; width: 180px; height: 30px;"
			></div>
			<input type="button" class="btn" onclick="javascript:window.close();" value="取消"
				style="cursor: pointer; float: left; line-height: 22px; width: 80px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 20px 10px 0px 0px;"
			/>
			<input type="button" class="btn" onclick="javascript:submitNextUser()" value="确定"
				style="cursor: pointer; float: left; line-height: 22px; width: 80px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 20px 10px 0px 0px;"
			/>
			<input type="hidden" name="userIdId" value="${userIdId }">
			<input type="hidden" name="userNameId" value="${userNameId }">
		</form>
		<div id="content">
			<table width="90%" border="1" align="center" cellpadding="1" cellspacing="1" class="input_table">
				<tr class="head">
					<td style="font-weight: bold; background-color: #d3e0f1;">
						<p>操作</p>
					</td>
				<#-->
					<td style="width: 30px; text-align: center; font-weight: bold; background-color: #d3e0f1;">
						<p>序号</p>
					</td>
				<-->	
					<td style="font-weight: bold; background-color: #d3e0f1;">
						<p>用户名</p>
					</td>
				<#-->	
					<td style="font-weight: bold; background-color: #d3e0f1;">
						<p>性别</p>
					</td>
					<td style="font-weight: bold; background-color: #d3e0f1;">
						<p>电话</p>
					</td>
				<-->	

				</tr>
				<#list pageBean.list as user>
				<tr>
					<td style="text-align: center; background-color: #fff;">
						<input type="checkbox" name="checkbox" value="${user.id}"
							onclick="javascript:addUser('${user.id}','${user.name}',this);"
						/>
					</td>
				<#-->
					<td width="30" style="text-align: center; background-color: #fff;">
						<p>${user_index + 1}</p>
					</td>
				<-->	
					<td style="background-color: #fff;">
						<p name="useeName">${user.name}</p>
					</td>
				<#-->	
					<td style="background-color: #fff;">
						<p>${user.sexStr}</p>
					</td>
					<td style="background-color: #fff;">
						<p>${user.tel}</p>
					</td>
				<-->	
				</tr>
				</#list>
			</table>
		</div>
		<div><#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" divId="main" /></div>
		<div style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px" class="add_link">
			<a href="javascript:submitNextUser();">确定</a>
			<a href="javascript:window.close();">取消</a>
		</div>
	</div>
</body>

<script>
	var userIds = [];
	var userNames = [];
	var useBames = [];
	useBames = opener.document.getElementById("handleManagerName").value;
	var useeName =document.getElementByName("useeName");

	Array.prototype.indexOf = function(val) {
		for (var i = 0; i < this.length; i++) {
			if (this[i] == val)
				return i;
		}
		return -1;
	};

	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};

	function addUser(userId, userName, o) {
		userIds = $("#userIds").val().split(",");
		userNames = $("#userNames").val().split(",");
		if (o.checked) {
			if (jQuery.inArray(userId, userIds) < 0) {
				userIds.push(userId);
				userNames.push(userName);
			}
		} else {
			userIds.remove(userId);
			userNames.remove(userName);
		}
		$("#userIds").val(userIds.join());
		$("#userNames").val(userNames.join());

	}

	function submitNextUser() {
		userIds = $("#userIds").val().split(",");
		userIds.shift();
		userNames = $("#userNames").val().split(",");
		userNames.shift();
		var elementUserId = '${userIdId}';
		var elementUserNameId = '${userNameId}'
		window.opener.document.getElementById(elementUserId).value = userIds;
		window.opener.document.getElementById(elementUserNameId).value = userNames;
		//window.opener.getNextOperatorValue(userIds,userNames);
		window.close();
	}
</script>
</html>
