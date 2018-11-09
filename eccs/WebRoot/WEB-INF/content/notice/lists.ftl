
<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="javascript:;">消息公告</a>
</div>
<div id="content">

	<form action="/notices/lists.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px;">
			<div class="article" style="margin: 0; clear: both; width: 100%;">
				<div
					style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 12px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px; height: 30px; line-height: 30px; overflow: hidden;"
				>标题:</div>
				<div>
					<input name="notice.title" type="text" value="${notice.title}"
						style="float: left; line-height: 22px; width: 150px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div
					style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 3px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px; height: 30px; line-height: 30px; overflow: hidden;"
				>发布时间:</div>
				<div>
					<input name="ctimes" type="text" value="${ctimes}" readonly="readonly" id="ctimes" readonly="readonly"
						onFocus="var ctimee=$dp.$('ctimee');WdatePicker({onpicked:function(){ctimee.focus();},maxDate:'#F{$dp.$D(\'ctimee\')}'})"
						style="float: left; line-height: 22px; width: 150px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div
					style="float: left; line-height: 22px; width: 60px; height: 30px; text-indent: 20px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px; height: 30px; line-height: 30px; overflow: hidden;"
				>到:</div>
				<div>
					<input name="ctimee" type="text" value="${ctimee}" readonly="readonly" id="ctimee"
						onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ctimes\')}'})"
						style="float: left; line-height: 22px; width: 150px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
					/>
				</div>
				<div class="sub_sear" onclick="javascript:$('#searchForm').submit()" style="margin: 6px 0px 0px 0px">查询</div>
			</div>
		</div>
	</form>
	<div style="clear: both;"></div>

	<table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
		<tr class="head">
			<td style="background-color: #e6f6ff; text-align: center; width: 30px;">
				<p>编号</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>标题</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>发布部门</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>发布人</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center;">
				<p>发布时间</p>
			</td>
			<td style="background-color: #e6f6ff; text-align: center; width: 120px;">
				<p>操作</p>
			</td>
		</tr>
		<#list pageBean.list as notice>
		<tr>
			<td width="30" style="text-align: center;">${notice_index + 1}</td>
			<td>${notice.title}</td>
			<td>${notice.dept.name}</td>
			<td>${notice.user.name}</td>
			<td>${notice.ctime}</td>
			<td style="text-align: center;">
				<a href="/notices/shows.htm?notice.id=${notice.id}" title="查看">查看</a>
			</td>
		</tr>
		</#list>
	</table>
	<div style="width: 90%; margin: 0 auto; min-width: 980px; margin-top: -30px;">
		<#import "/WEB-INF/tld/page.ftl" as tt> <@tt.page pageBean=pageBean formId="searchForm" />
	</div>

</div>




