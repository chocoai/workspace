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
    <a href="">招标文件评估</a>
</div>
<form action="/bid/call/save.htm" method="post" id="contractForm">
    <div id="content">
        <div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
        >招标文件评估</div>
        <input type="hidden" name="callAssess.cid" value="${callAssess.cid}" />
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
                <td width="10%" class="tab_title">项目名称</td>
                <td colspan="2" class="tab_title">
                    <input type="text" name="bidDeptName" value="${callAssess.projectInfo.proname}" class="text_a" readonly="readonly" />
                </td>
                <td width="10%" class="tab_title">开标日期</td>
                <td>
                    <input class="text_a" id="opentime" type="text" name="proinfo.opentime"
                        value="${callAssess.projectInfo.opentime}" readonly="readonly" />
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">咨询类型</td>
                <td colspan="2">
                    <input name="callAssess.consultType" value="${callAssess.consultType}" type="text" class="text_a" readonly="readonly" />
                </td>
                <td width="10%" class="tab_title">项目归属</td>
                <td>
                    <input name="callAssess.projectAffiliation" value="${callAssess.projectAffiliation}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">其他</td>
                <td colspan="4">
                    <input name="callAssess.other" value="${callAssess.other}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td colspan="8" style="text-align:center;"><h3>招标文件评估表</h3></td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">1.失分情况</td>
                <td colspan="8">
                    <textarea rows="4" name="callAssess.loseScoreCase" maxlength="225" style="width:100%;margin-top:10px;margin-bottom:0px;resize:none" readonly="readonly">${callAssess.loseScoreCase}</textarea>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">2.特殊要求</td>
                <td colspan="8">
                    <textarea rows="4" name="callAssess.specificRequirements" maxlength="225" style="width:100%;margin-top:10px;margin-bottom:0px;resize:none" readonly="readonly">${callAssess.specificRequirements}</textarea>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">评估结果</td>
                <td width="20%">
                    <i>投标</i>&nbsp;&nbsp;<input value="1" type="radio" name="callAssess.assessResult" disabled="disabled"/>
                    <i>不参与</i>&nbsp;&nbsp;<input value="0" type="radio" name="callAssess.assessResult" disabled="disabled"/>
                </td>
                <td width="15%" class="tab_title">不参与的评估说明</td>
                <td colspan="2">
                    <input name="callAssess.appraisalnotes" value="${callAssess.appraisalnotes}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="15%" class="tab_title">投标文件编制责任人</td>
                <td>
                    <input value="${nextOperatorName!callAssess.responsiblePerson}" type="text" name="callAssess.responsiblePerson" readonly="readonly"/>
                </td>
                <td width="15%" class="tab_title">初稿提交审核日期</td>
                <td colspan="2">
                    <input class="text_a" id="assesDate" type="text" name="callAssess.auditDate"
                        value="${callAssess.auditDate}" readonly="readonly"/>
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">

    if("${callAssess.assessResult}"==1){
        $(":radio").eq(0).attr("checked","checked");
    }else{
        $(":radio").eq(1).attr("checked","checked");
    }

</script>

