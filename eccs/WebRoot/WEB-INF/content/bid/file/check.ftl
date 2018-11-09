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
    <a href="">投标文件评估审核</a>
</div>
<form action="/bid/file/save2.htm" method="post" id="contractForm">
    <div id="content">
        <div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
        >投标文件初稿提交</div>
        <input type="hidden" name="fileAssess.cid" value="${fileAssess.cid}" />
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
                <td width="10%" class="tab_title">项目名称</td>
                <td colspan="2" class="tab_title">
                    <input type="text" class="text_a" name="fileAssess.projectInfo.proname" value="${fileAssess.projectInfo.proname}" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">招标人</td>
                <td>
                    <input type="text" class="text_a" name="fileAssess.customer.cusName" value="${fileAssess.customer.cusName}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">咨询类型</td>
                <td colspan="2" class="tab_title">
                    <input type="text" name="fileAssess.consultType" class="text_a" value="${fileAssess.consultType}" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">开标日期</td>
                <td>
                    <input class="text_a" type="text" name="fileAssess.bidOpenDate"
                        value="${fileAssess.bidOpenDate}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="15%" class="tab_title">投标文件编制责任人</td>
                <td colspan="2">
                    <input name="fileAssess.responsiblePerson" value="${fileAssess.responsiblePerson}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">初稿提交时间</td>
                <td>
                    <input name="fileAssess.firstdraftTime" value="${fileAssess.firstdraftTime}" type="text" class="text_a" readonly="readonly"
                        onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">编制完成情况</td>
                <td colspan="2">
                    <i>已完成并提交初稿至审核人</i>&nbsp;&nbsp;<input value="1" type="checkbox" name="fileAssess.completeStatus" />
                </td>
                <td width="10%" class="tab_title">实际提交时间</td>
                <td>
                    <input name="fileAssess.submissionTime" value="${fileAssess.submissionTime}" type="text" class="text_a" readonly="readonly" />
                </td>
            </tr>
        </table>
        <div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
        >投标文件审核</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
                <td width="10%" class="tab_title">审核情况</td>
                <td width="20%">
                    <i>通过</i>&nbsp;&nbsp;<input value="1" type="radio" name="fileAssess.auditResult" checked="checked"/>
                </td>
                <td width="15%" class="tab_title">审核评定</td>
                <td colspan="2">
                    <i>优</i>&nbsp;&nbsp;<input value="3" type="radio" name="fileAssess.auditAssess" />&nbsp;&nbsp;&nbsp;&nbsp;
                    <i>良</i>&nbsp;&nbsp;<input value="2" type="radio" name="fileAssess.auditAssess" />&nbsp;&nbsp;&nbsp;&nbsp;
                    <i>一般</i>&nbsp;&nbsp;<input value="1" type="radio" name="fileAssess.auditAssess" checked="checked"/>&nbsp;&nbsp;&nbsp;&nbsp;
                    <i>较差</i>&nbsp;&nbsp;<input value="0" type="radio" name="fileAssess.auditAssess" />&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
            </tr>
            <tr>
                <td class="tab_title red" width="12%">
                    <span id="nextStepNamea">下一环节处理人</span>
                </td>
                <td colspan="5">
                    <input type="hidden" name="nextOperatorId" id="nextOperatorId" value="${nextOperatorId}" />
                    <input type="text" name="nextOperatorName" id="nextOperatorName" value="${nextOperatorName}"
                        class="text_a" readonly="readonly" onclick="javascript:getNextOperator('nextOperatorId','nextOperatorName');"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="6" style="text-align: right;">
                    <input type="button" class="sub" value="取消" onclick="javascript: history.go(-1);" />
                    <input type="button" class="sub" value="提交" onclick="toNextStep()"/>
                    <input type="submit" class="sub" value="保存" />
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">

    if("${fileAssess.completeStatus}"==1){
        $(":checkbox").eq(0).attr("checked","checked");
    }
    
    if("${fileAssess.auditResult}"==1){
        $(":radio").eq(0).attr("checked","checked");
    }
    
    if("${fileAssess.auditAssess}"==3){
        $(":radio").eq(1).attr("checked","checked");
    }else if("${fileAssess.auditAssess}"==2){
        $(":radio").eq(2).attr("checked","checked");
    }else if("${fileAssess.auditAssess}"==1){
        $(":radio").eq(3).attr("checked","checked");
    }else if("${fileAssess.auditAssess}"==0){
        $(":radio").eq(4).attr("checked","checked");
    }

    var i = 0;
    var j = 10000;
    var flag = true;
    var flog = true;
    var operaterType = 0;

    function toNextStep() { //执行下一步
        var nextOperator = $("#nextOperatorName").val();
        if(nextOperator.length<1){
        alert("请选择下一环节处理人");
        return;
        }
        var message = "";
        if(operaterType==0){
            message = "当前步骤执行完毕,下一环节为【开标中标信息】，下一环节处理人为【" + nextOperator + "】，确定要执行下一步吗?";
        }else{
            message = "确认终止此项目吗？";
        }
        if (confirm(message)) {
            document.getElementById("contractForm").action = "/bid/file/toNextStep2.htm";
            $("#contractForm").submit();
        }
    }
    
    function deleteRow(id,s) {
        if(s=="0"){
        $('#' + id).remove();
            return;
        }
        if (!confirm("确定删除?")) {
            return;
        }   
        var url = "/bid/bond/delete.htm?bidId=" + id+"&types="+s;
        $.ajax({
            type:"post",
            url:url,
            dataType:'text',
            success:function(d){
                if (d == '1') {
                    alert('删除成功');
                    window.parent.location.reload();
                    //location.reload();
                } else {
                    alert('删除失败');
                }
            }
        });
        }

    function getNextOperator(userIdId, userNameId) {
        window.open('/user/selectMultiUser.htm?userIdId=' + userIdId
                + '&userNameId=' + userNameId, '_blank','channelmode=yes,width=800px,height=500px,left=200,top=100,scrollbars=yes');
    }
    
    function getNextOperatorValue(ids,names) {
        $('#nextOperatorId').val(ids);
        $('#nextOperatorName').val(names);
    }

</script>
