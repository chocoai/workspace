<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="/marketManager.htm">经营管理</a>
	>
	<a href="/bidPlanning.htm">投标策划</a>
	>
	<a href="">保证金评估列表</a>
</div>
<div id="content">
	<form action="/bid/bond/list.htm" method="post" id="searchForm">
		<div align="center" class="list_table stripe" style="width: 90%; margin: 0 auto; min-width: 980px;">
			<div class="article" style="margin: 0; clear: both; width: 100%;">
				<div
                    style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 0px;"
                >项目名称:</div>
                <div>
                    <input name="projectInfo.proname" type="text" value="${projectInfo.proname}"
                        style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
                    />
                </div>
                <div
                    style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;"
                >招标人:</div>
                <div>
                    <input name="projectInfo.bidmen.cusName" type="text" value="${projectInfo.bidmen.cusName}"
                        style="float: left; line-height: 3022px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
                    />
                </div>
                <div
                    style="float: left; line-height: 30px; width: 60px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 0px 0px 10px;"
                >代理公司:</div>
                <div>
                    <input name="projectInfo.agentcompany" type="text" value="${projectInfo.agentcompany}"
                        style="float: left; line-height: 30px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
                    />
                </div>
				<div class="sub_sear" onclick="javascript:$('#searchForm').submit();">查询</div>
			</div>
		</div>
	</form>
	<div style="clear: both;"></div>
	<table id="table" border="1" align="center" cellpadding="1" cellspacing="1" class="list_table5 stripe">
        <tr class="head">
            <td style="background-color: #e6f6ff; text-align: center; width: 3%;">
                <p>序号</p>
            </td>
            <td style="background-color: #e6f6ff; text-align: center; width: 12%;">
                <p>招标人</p>
            </td>
            <td style="background-color: #e6f6ff; text-align: center;">
                <p>项目名称</p>
            </td>
            <td style="background-color: #e6f6ff; text-align: center; width: 18%;">
                <p>代理公司</p>
            </td>
            <td style="background-color: #e6f6ff; text-align: center; width: 12%;">
                <p>开标日期</p>
            </td>
            <td style="background-color: #e6f6ff; text-align: center; width: 15%;">
                <p>操作</p>
            </td>
        </tr>
        <#list pageBean.list as bondAssess>
        <tr>
            <td width="30" style="text-align: center;">${bondAssess_index+1}</td>
            <td style="text-align: center;">
                <span title="">${bondAssess.projectInfo.bidmen.cusName}</span>
            </td>
            <td style="text-align: center;">
                <span title="">${bondAssess.projectInfo.proname}</span>
            </td>
            <td style="text-align: center;">
                <span title="">${bondAssess.projectInfo.agentcompany}</span>
            </td>
            <td style="text-align: center;">
                <span title="">${bondAssess.projectInfo.opentime}</span>
            </td>
            <td style="text-align: center;">
                <#if bondAssess.resStates >
                <a href="javascript:;" onclick="deleteApply(${bondAssess.cid})" title="删除">删除</a>
                <a href="/bid/bond/edit.htm?projectInfo.id=${bondAssess.projectInfo.id}" title="编辑">编辑 </a>
                </#if>
                <#if bondAssess.checkStates >
                <a href="javascript:;" onclick="deleteApply(${bondAssess.cid})" title="删除">删除</a>
                <a href="/bid/bond/check.htm?projectInfo.id=${bondAssess.projectInfo.id}" title="审核">审核 </a>
                </#if>
                <a href="/bid/bond/show.htm?projectInfo.id=${bondAssess.projectInfo.id}" title="查看">查看</a>
            </td>
        </tr>
        </#list>
    </table>

</div>
<script>
    function deleteApply(id) {
        if (!confirm("确定删除此信息")) {
            return;
        }

        var url = "/bid/bond/delete.htm?bondAssess.cid=" + id;
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

</script>



