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
    <a href="">保证金评估审核</a>
</div>
<form action="/bid/bond/save2.htm" method="post" id="contractForm">
    <div id="content">
        <div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
        >支付投标保证金申请函</div>
        <input type="hidden" name="bondAssess.cid" value="${bondAssess.cid}" />
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
                <td width="10%" class="tab_title">申请编号</td>
                <td colspan="2">
                    <input type="text" name="bondAssess.applyNo" value="${bondAssess.applyNo}" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">申请日期</td>
                <td>
                    <input class="text_a" id="opentime" type="text" name="bondAssess.createTime"
                        value="${bondAssess.createTime}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">项目名称</td>
                <td colspan="2">
                    <input name="bondAssess.projectInfo.proname" value="${bondAssess.projectInfo.proname}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">标包</td>
                <td>
                    <input name="bondAssess.bidPkg" value="${bondAssess.bidPkg}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">汇款户名</td>
                <td colspan="2">
                    <input name="bondAssess.remittancesName" value="${bondAssess.remittancesName}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">汇款账号</td>
                <td>
                    <input name="bondAssess.remittancesAccount" value="${bondAssess.remittancesAccount}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">开户银行</td>
                <td colspan="2">
                    <input name="bondAssess.depositBank" value="${bondAssess.depositBank}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">行号</td>
                <td>
                    <input name="bondAssess.bankAccount" value="${bondAssess.bankAccount}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">汇款金额</td>
                <td colspan="2">
                    <input name="bondAssess.remittanceAmount" value="${bondAssess.remittanceAmount}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="15%" class="tab_title">保证金递交截止时间</td>
                <td>
                    <input class="text_a" id="opentime" type="text" name="bondAssess.deadline"
                        value="${bondAssess.deadline}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">招标人</td>
                <td colspan="2">
                    <input name="bondAssess.customer.cusName" value="${bondAssess.customer.cusName}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">联系人电话</td>
                <td>
                    <input name="bondAssess.bidderTel" value="${bondAssess.bidderTel}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">招标代理人</td>
                <td colspan="2">
                    <input name="bondAssess.bidAgent" value="${bondAssess.bidAgent}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">联系人电话</td>
                <td>
                    <input name="bondAssess.agentTel" value="${bondAssess.agentTel}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">特殊要求</td>
                <td colspan="4">
                    <input name="bondAssess.specialRequirements" value="${bondAssess.specialRequirements}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">退还保证金时间</td>
                <td colspan="2">
                    <input class="text_a" id="opentime" type="text" name="bondAssess.rreturnTime"
                        value="${bondAssess.rreturnTime}" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">退还保证金特殊说明</td>
                <td>
                    <input name="bondAssess.returmSpecialInstr" value="${bondAssess.returmSpecialInstr}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">审核意见</td>
                <td colspan="2">
                    <i>同意</i>&nbsp;&nbsp;<input value="1" type="radio" name="bondAssess.auditOpinion" disabled="disabled"/>
                    <i>不同意</i>&nbsp;&nbsp;<input value="0" type="radio" name="bondAssess.auditOpinion" disabled="disabled"/>
                </td>
                <td width="15%" class="tab_title">财务部处理情况</td>
                <td colspan="2">
                    <i>已支付</i>&nbsp;&nbsp;<input value="1" type="radio" name="bondAssess.financeHandleSituation" disabled="disabled"/>
                    <i>未支付</i>&nbsp;&nbsp;<input value="0" type="radio" name="bondAssess.financeHandleSituation" disabled="disabled"/>
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">

    if("${bondAssess.auditOpinion}"==1){
        $(":radio").eq(0).attr("checked","checked");
    }else{
        $(":radio").eq(1).attr("checked","checked");
    }
    if("${bondAssess.financeHandleSituation}"==1){
        $(":radio").eq(2).attr("checked","checked");
    }else{
        $(":radio").eq(3).attr("checked","checked");
    }

</script>
