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
    <a href="">报名评估</a>
</div>
<form action="/bid/apply/save2.htm" method="post" id="contractForm">
    <div id="content">
        <div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
        >投标报名评估</div>
        <input type="hidden" name="applyAssess.cid" value="${applyAssess.cid}" />
        <table id="assess" border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
                <td width="10%" class="tab_title">项目名称</td>
                <td colspan="2" class="tab_title">
                    <input type="text" value="${applyAssess.projectInfo.proname}" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">标包</td>
                <td>
                    <input name="applyAssess.bidPkg" type="text" class="text_a" value="${applyAssess.bidPkg}" readonly="readonly"/>
                </td>
            </tr>

            <tr>
            	<td width="10%" class="tab_title">招标人</td>
                <td colspan="2" class="tab_title">
                    <input value="${applyAssess.customer.cusName}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">咨询类型</td>
                <td>
                    <input name="applyAssess.consultType" value="${applyAssess.consultType}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>

            <tr>
            	<td width="10%" class="tab_title">招标文件(公告)</td>
                <td colspan="2">
                    <a href="${applyAssess.callBidFile}"   target="_blank">
                    	<input style="color:blue;" name="applyAssess.callBidFile" value="${applyAssess.callBidFile}" type="text" class="text_a" readonly="readonly"/>
                    </a>
                </td>
                <td width="10%" class="tab_title">代理公司</td>
                <td colspan="2">
                    <input name="applyAssess.agentCompany" value="${applyAssess.agentCompany}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">报名日期</td>
                <td colspan="2">
                    <input class="text_a" type="text" name="applyAssess.applyDate" value="${applyAssess.applyDate}" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">开标日期</td>
                <td>
                    <input class="text_a" type="text" name="applyAssess.bidOpenDate" value="${applyAssess.bidOpenDate}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">咨询费估算</td>
                <td colspan="2">
                    <input name="applyAssess.consultFee" value="${applyAssess.consultFee}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">报名城市</td>
                <td>
                    <input name="applyAssess.signupCity" value="${applyAssess.signupCity}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">评估结果</td>
                <td width="20%">
                    <i>报名</i>&nbsp;&nbsp;<input type="radio" name="applyAssess.assessResult" value="1" disabled="disabled"/><!-- assessResult -->
                    <i>不参与</i>&nbsp;&nbsp;<input type="radio" name="applyAssess.assessResult" value="0" disabled="disabled"/>
                </td>
                <td width="15%" class="tab_title">不参与的评估说明</td>
                <td colspan="2">
                    <input name="applyAssess.appraisalnotes" value="${applyAssess.appraisalnotes}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
        </table>
        <div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
        >报名情况</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
                <td width="10%" class="tab_title">报名完成情况</td>
                <td width="20%">
                    <i>是</i>&nbsp;&nbsp;<input value="1" type="radio" name="applyAssess.completeStatus" disabled="disabled"/>
                    <i>否</i>&nbsp;&nbsp;<input value="0" type="radio" name="applyAssess.completeStatus" disabled="disabled"/>
                </td>
                <td width="15%" class="tab_title">领取招标文件日期</td>
                <td colspan="2">
                    <input class="text_a" id="opentime" type="text" name="applyAssess.getFileDate"
                        value="${applyAssess.getFileDate}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">备注</td>
                <td  colspan="4">
                    <input name="applyAssess.remark" value="${applyAssess.remark}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">
    if("${applyAssess.assessResult}"==1){
        $(":radio").eq(0).attr("checked","checked");
    }else{
        $(":radio").eq(1).attr("checked","checked");
    }
    
    if("${applyAssess.completeStatus}"==1){
        $(":radio").eq(2).attr("checked","checked");
    }else{
        $(":radio").eq(3).attr("checked","checked");
    }
</script>

