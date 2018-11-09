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
    <a href="">开标中标情况</a>
</div>
<form action="/bid/info/save.htm" method="post" id="contractForm">
    <div id="content">
        <div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
        >开标，中标情况一览表</div>
        <input type="hidden" name="info.cid" value="${info.cid}" />
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
                <td width="10%" class="tab_title">项目名称</td>
                <td colspan="4" class="tab_title">
                    <input type="text" name="info.projectInfo.proname" value="${info.projectInfo.proname}" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">开标日期</td>
                <td>
                    <input class="text_a" id="opentime" type="text" name="info.bidOpenDate"
                        value="${info.bidOpenDate}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">开标人员</td>
                <td colspan="4">
                    <input name="info.bidOpenPerson" value="${info.bidOpenPerson}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">投标家数</td>
                <td>
                    <input name="info.bidNumber" value="${info.bidNumber}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">中标单位</td>
                <td colspan="2">
                    <input name="info.bidWinner" value="${info.bidWinner}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">是否中标</td>
                <td>
                    <i>中标</i>&nbsp;&nbsp;<input value="1" type="radio" name="info.isSuccess" checked="checked"/>
                    <i>未中标</i>&nbsp;&nbsp;<input value="0" type="radio" name="info.isSuccess" />
                </td>
                <td class="tab_title">中标价</td>
                <td>
                    <input name="info.bidWinnerPrice" value="${info.bidWinnerPrice}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td colspan="8">
                    <table border="1" id="infoTableId" cellspacing="1" cellpadding="1" class="list_table4">
                        <tr>
                            <td width="5%" style="text-align:center;">序号</td>
                            <td width="30%" style="text-align:center;">单位</td>
                            <td width="20%" style="text-align:center;">报价</td>
                            <td width="20%" style="text-align:center;">预算</td>
                            <td width="20%" style="text-align:center;">比例</td>
                        </tr>
                        <#list infoQuoteList as infoQuote>
                        <tr>
                            <td width="5%" style="text-align:center;">
                            ${infoQuote_index+1}
                                <input type="hidden" value="${infoQuote.cid}" name="infoQuoteCid" readonly="readonly">
                            </td>
                            <td width="30%">
                                <input type="text" value="${infoQuote.company}" name="infoQuoteCompany"  class="text_a" readonly="readonly"/>
                            </td>
                            <td width="20%">
                                <input type="text" value="${infoQuote.quotedPrice}" name="infoQuoteQuotedPrice"  class="text_a" readonly="readonly"/>
                            </td>
                            <td width="20%">
                                <input type="text" value="${infoQuote.budgetaryPrice}" name="infoQuoteBudgetaryPrice"  class="text_a" readonly="readonly"/>
                            </td>
                            <td width="20%" >
                                <input type="text" value="${infoQuote.ratio}" name="infoQuoteRatio"  class="text_a" readonly="readonly"/>
                            </td>
                        </tr>
                        </#list>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</form>

<script type="text/javascript">

	if("${info.isSuccess}"==1){
        $(":radio").eq(0).attr("checked","checked");
    }else{
        $(":radio").eq(1).attr("checked","checked");
    }
</script>