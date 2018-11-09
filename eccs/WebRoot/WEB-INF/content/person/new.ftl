<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>


<style>
#main #content table.list_table {
	table-layout: auto;
	margin-top: 20px;
}

#main #content table.list_table tr:hover {
	cursor: pointer;
	background-color: #fff;
}

#main #content table.list_table tr.head {
	font-size: 14px;
}

#main #content table.list_table tr td:hover {
	background-color: #A1D6F8;
}
</style>

<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a href="/person/list.htm">人员分配列表</a>
	>
	<a href="#">任务分配</a>
</div>
<div id="content">
	<form action="/person/save.htm" method="post" id="searchForm">
		<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
			<input name="id" id="id" value="${personFenpei.id}" type="hidden" />
			<input name="project.id" id="projectId" value="${personFenpei.project.id}" type="hidden" />
			<tr>
				<td colspan="7" style="text-align: center; color: #4d4d4d; font-size: 18px; font-weight: bold;">投标任务分配表</td>
			</tr>
			<tr class="head">
				<td style="text-align: center;" rowspan="2">序号</td>
				<td style="text-align: center;" rowspan="2">阶段</td>
				<td style="text-align: center;" rowspan="2">任务名称</td>
				<td style="text-align: center;" rowspan="2">工作内容描述</td>
				<td style="text-align: center;" rowspan="2">责任人</td>
				<td style="text-align: center;" colspan="2">计划</td>
			</tr>
			<tr class="head">
				<td style="text-align: center;">开始</td>
				<td style="text-align: center;">结束</td>
			</tr>
			<tr>
				<td style="text-align: center;">1</td>
				<td rowspan="2">策划阶段</td>
				<td>前期策划</td>
				<td>信息收集，投标与否做出论证</td>
				<td>
					<input type="hidden" name="userid1" id="useridid1" value="${user1.id}" />
					<input type="text" name="username1" id="usernameid1" value="${user1.name}" readonly="readonly"
						onclick="getUser('useridid1','usernameid1')" style="text-align: center; width: 80px;"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" class="text_a" type="text" name="pstart1"
						value="${pstart1}"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" type="text" name="pcontinue1" value="${pcontinue1}" />
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">2</td>
				<td>后期策划</td>
				<td>制定投标策略</td>
				<td>
					<input type="hidden" name="userid2" id="useridid2" value="${user2.id}" />
					<input type="text" name="username2" id="usernameid2" value="${user2.name}" readonly="readonly"
						onclick="getUser('useridid2','usernameid2')" style="text-align: center; width: 80px;"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" class="text_a" type="text" name="pstart2"
						value="${pstart2}"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" type="text" name="pcontinue2" value="${pcontinue2}" />
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">3</td>
				<td rowspan="3">实施阶段</td>
				<td>投标报名</td>
				<td>报名资料准备，投标保证金缴纳</td>
				<td>
					<input name="userid3" id="useridid3" value="${user3.id}" type="hidden" />
					<input name="username3" id="usernameid3" readonly="readonly" value="${user3.name}"
						onclick="getUser('useridid3','usernameid3')" type="text" style="text-align: center; width: 80px;"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" class="text_a" type="text" name="pstart3"
						value="${pstart3}"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" type="text" name="pcontinue3" value="${pcontinue3}" />
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">4</td>

				<td>投标准备</td>
				<td>招标文件提疑，投标文件编制及审核</td>
				<td>
					<input name="userid4" id="useridid4" value="${user4.id}" type="hidden" />
					<input name="username4" id="usernameid4" readonly="readonly" value="${user4.name}"
						onclick="getUser('useridid4','usernameid4')" type="text" style="text-align: center; width: 80px;"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" class="text_a" type="text" name="pstart4"
						value="${pstart4}"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" type="text" name="pcontinue4" value="${pcontinue4}" />
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">5</td>

				<td>投标</td>
				<td>递交投标文件或竞争性谈判、磋商文件</td>
				<td>
					<input name="userid5" id="useridid5" value="${user5.id}" type="hidden" />
					<input name="username5" id="usernameid5" readonly="readonly" value="${user5.name}"
						onclick="getUser('useridid5','usernameid5')" type="text" style="text-align: center; width: 80px;"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" class="text_a" type="text" name="pstart5"
						value="${pstart5}"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" type="text" name="pcontinue5" value="${pcontinue5}" />
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">6</td>
				<td>结束阶段</td>
				<td>投标总结</td>
				<td>投标总结，好的做法及失误情况分析中标项目领取中标通知书，项目移交</td>
				<td>
					<input name="userid6" id="useridid6" value="${user6.id}" type="hidden" />
					<input type="text" name="username6" id="usernameid6" readonly="readonly" value="${user6.name}"
						style="text-align: center; width: 80px;" onclick="javascript:getUser('useridid6', 'usernameid6');"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" class="text_a" type="text" name="pstart6"
						value="${pstart6}"
					/>
				</td>
				<td>
					<input style="text-align: center; width: 80px;" type="text" name="pcontinue6" value="${pcontinue6}" />
				</td>
			</tr>
			<tr>
				<td colspan="7" style="text-align: right;">
					<input type="button" class="sub" onclick="javascript:history.go(-1);" value="取消" />
					<input type="submit" class="sub" value="保存" />
				</td>
			</tr>
		</table>
	</from>
</div>

<script type="text/javascript">
	function getUser(userIdId, userNameId) {
		window.open('/user/getUserByDeptId.htm?userIdId=' + userIdId
				+ '&userNameId=' + userNameId, '_blank',
				'channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
	}
</script>

