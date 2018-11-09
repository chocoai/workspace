
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
	<a href="#">任务分配-查看</a>
</div>
<div id="content">
	<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table">
		<tr>
			<td style="text-align: center; color: #4d4d4d; font-size: 18px; font-weight: bold;" colspan="7">
				<p>投标任务分配表</p>
			</td>
		</tr>
		<tr class="head">
			<td style="text-align: center; width: 30px;" rowspan="2">
				<p>序号</p>
			</td>
			<td style="text-align: center; width: 80px;" rowspan="2">
				<p>阶段</p>
			</td>
			<td style="text-align: center; width: 80px;" rowspan="2">
				<p>任务名称</p>
			</td>
			<td style="text-align: center; width: 400px;" rowspan="2">
				<p>工作内容描述</p>
			</td>
			<td style="text-align: center; width: 100px;" rowspan="2">
				<p>责任人</p>
			</td>
			<td style="text-align: center; width: 100px;" colspan="2">
				<p>计划</p>
			</td>
		</tr>
		<tr class="head">
			<td style="text-align: center;">
				<p>开始</p>
			</td>
			<td style="text-align: center;">
				<p>结束</p>
			</td>
		</tr>
		<tr>

			<td style="text-align: center;">
				<p>1</p>
			</td>
			<td rowspan="2">
				<p>策划阶段</p>
			</td>
			<td>
				<p>前期策划</p>
			</td>
			<td>
				<p>信息收集，投标与否做出论证</p>
			</td>
			<td>${user1.name}</td>
			<td>${pstart1}</td>
			<td>${pcontinue1}</td>
		</tr>
		<tr>
			<td style="text-align: center;">
				<p>2</p>
			</td>
			<td>
				<p>后期策划</p>
			</td>
			<td>
				<p>制定投标策略</p>
			</td>
			<td>${user2.name}</td>
			<td>${pstart2}</td>
			<td>${pcontinue2}</td>


		</tr>
		<tr>
			<td style="text-align: center;">
				<p>3</p>
			</td>
			<td rowspan="3">
				<p>实施阶段</p>
			</td>
			<td>
				<p>投标报名</p>
			</td>
			<td>
				<p>报名资料准备，投标保证金缴纳</p>
			</td>
			<td>${user3.name}</td>
			<td>${pstart3}</td>
			<td>${pcontinue3}</td>
		</tr>
		<tr>
			<td style="text-align: center;">
				<p>4</p>
			</td>

			<td>
				<p>投标准备</p>
			</td>
			<td>
				<p>招标文件提疑，投标文件编制及审核</p>
			</td>
			<td>${user4.name}</td>
			<td>${pstart4}</td>
			<td>${pcontinue4}</td>
		</tr>
		<tr>
			<td style="text-align: center;">
				<p>5</p>
			</td>

			<td>
				<p>投标</p>
			</td>
			<td>
				<p>递交投标文件或竞争性谈判、磋商文件</p>
			</td>
			<td>${user5.name}</td>
			<td>${ pstart5}</td>
			<td>${ pcontinue5}</td>
		</tr>
		<tr>
			<td style="text-align: center;">
				<p>6</p>
			</td>
			<td>
				<p>结束阶段</p>
			</td>
			<td>
				<p>投标总结</p>
			</td>
			<td>
				<p>投标总结，好的做法及失误情况分析中标项目领取中标通知书，项目移交</p>
			</td>
			<td>${user6.name}</td>
			<td>${ pstart6}</td>
			<td>${ pcontinue6}</td>
		</tr>
		<tr>
			<td colspan="7" style="text-align: right;">
				<input type="button" class="sub" onclick="javascript:history.go(-1);" value="取消" />
				<input type="button" class="sub" onclick="javascript:toPrint();" value="打印" />
			</td>
		</tr>
	</table>

</div>
</div>
<script>
	function toPrint() {
		var url = "/person/print.htm?projectId=${personFenpei.project.id}";
		var iWidth = 1000; //弹出窗口的宽度; 
		var iHeight = 600; //弹出窗口的高度; 
		//获得窗口的垂直位置 
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
		//获得窗口的水平位置 
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		window
				.open(
						url,
						'',
						'height='
								+ iHeight
								+ ',innerHeight='
								+ iHeight
								+ ',width='
								+ iWidth
								+ ',innerWidth='
								+ iWidth
								+ ',top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>


