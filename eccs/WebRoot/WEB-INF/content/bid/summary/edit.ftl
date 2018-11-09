<script type="text/javascript" src="../../../js/mydate/WdatePicker.js"></script>
<script type="text/javascript" src="../../../js/jquery.validate.js"></script>
<script type="text/javascript" src="../../../js/jquery.validate.messages_cn.js"></script>
<script type="text/javascript" src="../../../js/jquery.metadata.js"></script>

<div id="map">
    <img src="/images/home.png" width="19" height="24" />
    当前位置：
    <a href="/workbench.htm">工作台</a>
    >
    <a href="/marketManager.htm">经营管理</a>
    >
    <a href="/bidPlanning.htm">投标策划</a>
    >
    <a href="">项目总结编辑</a>
</div>
<form action="/bid/summary/save.htm" method="post" id="contractForm">
	<div id="content">
		<div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
		>项目总结</div>
		<input type="hidden" name="summary.cid" value="${summary.cid}" />
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td width="10%" class="tab_title">项目名称</td>
				<td colspan="8" class="tab_title">
					<input type="text" name="summary.projectInfo.proname" value="${summary.projectInfo.proname}" class="text_a" readonly="readonly"/>
				</td>
			</tr>
            <tr>
                <td width="10%" class="tab_title">汇总小结</td>
                <td colspan="8">
                    <textarea rows="4" maxlength="120" style="width:100%;margin-top:10px;margin-bottom:0px;resize:none" name="summary.summary">${summary.summary}</textarea>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">完善措施</td>
                <td colspan="8">
                    <textarea rows="4" maxlength="120" style="width:100%;margin-top:10px;margin-bottom:0px;resize:none" name="summary.perfectMeasures">${summary.perfectMeasures}</textarea>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">归档情况</td>
                <td colspan="8">
                    <textarea rows="4" maxlength="120" style="width:100%;margin-top:10px;margin-bottom:0px;resize:none" name="summary.archiveSituation">${summary.archiveSituation}</textarea>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">其他</td>
                <td colspan="8">
                    <textarea rows="4" maxlength="120" style="width:100%;margin-top:10px;margin-bottom:0px;resize:none" name="summary.other">${summary.other}</textarea>
                </td>
            </tr>
            <tr>
                <td colspan="9" style="text-align: right;">
                    <input type="button" class="sub" value="取消" onclick="javascript: history.go(-1);" />
                    <input type="submit" class="sub" value="提交"/>
                    <input type="submit" class="sub" value="保存" />
                </td>
            </tr>
		</table>
	</div>
</form>

