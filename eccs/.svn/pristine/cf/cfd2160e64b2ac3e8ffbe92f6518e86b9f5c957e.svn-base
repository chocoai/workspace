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
    <a href="">项目移交单</a>
</div>
<form action="/bid/transfer/save.htm" method="post" id="contractForm">
    <div id="content">
        <div style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;"
        >项目移交单</div>
        <input type="hidden" name="transfer.cid" value="${transfer.cid}" />
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <tr>
                <td width="10%" class="tab_title">单号</td>
                <td colspan="2" class="tab_title">
                    <input type="text" name="transfer.transferNo" value="${transfer.transferNo}" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">移交日期</td>
                <td>
                    <input class="text_a" name="transfer.transferTime" type="text"
                        value="${transfer.transferTime!.now?string('yyyy-MM-dd')}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">项目名称</td>
                <td colspan="2">
                    <input name="transfer.projectInfo.proname" value="${transfer.projectInfo.proname}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">咨询类型</td>
                <td>
                    <input name="transfer.consultType" value="${transfer.consultType}" type="text" class="text_a"  readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">主要服务内容及范围</td>
                <td colspan="8">
                    <textarea rows="4" name="transfer.serviceContent" maxlength="225" style="width:100%;margin-top:10px;margin-bottom:0px;resize:none" readonly="readonly">${transfer.serviceContent}</textarea>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">中标价</td>
                <td colspan="2">
                    <input name="transfer.bidPrice" value="${transfer.bidPrice}" type="text" class="text_a"  readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">目前状态</td>
                <td>
                    <input name="transfer.currentStatus" value="${transfer.currentStatus}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" rowspan="3" class="tab_title">移交清单</td>
                <td width="10%" class="tab_title">1.中标公示</td>
                <td colspan="3">
                    <input name="transfer.noticeUrl" value="${transfer.noticeUrl}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">2.招标文件</td>
                <td colspan="3">
                    <input name="transfer.callBidFile" value="${transfer.callBidFile}" type="text" class="text_a"  readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">3.投标文件</td>
                <td colspan="3">
                    <input name="transfer.bidFile" value="${transfer.bidFile}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" rowspan="3" class="tab_title">业主信息</td>
                <td width="10%" class="tab_title">单位</td>
                <td colspan="4">
                    <input name="transfer.ownerCompany" value="${transfer.ownerCompany}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">姓名</td>
                <td>
                    <input name="transfer.ownerName" value="${transfer.ownerName}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">职务</td>
                <td>
                    <input name="transfer.ownerPosition" value="${transfer.ownerPosition}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">电话</td>
                <td>
                    <input name="transfer.ownerTel" value="${transfer.ownerTel}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">地址</td>
                <td colspan="2">
                    <input name="transfer.ownerAddr" value="${transfer.ownerAddr}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            
            <tr>
                <td width="10%" rowspan="3" class="tab_title">备注</td>
                <td width="10%" class="tab_title">事项</td>
                <td colspan="3">
                    <input name="transfer.event" value="${transfer.event}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">时间</td>
                <td>
                    <input class="text_a" id="opentime" type="text" name="transfer.eventTime"
                        value="${transfer.eventTime}" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">地点</td>
                <td colspan="2">
                    <input name="transfer.eventAddr" value="${transfer.eventAddr}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">其他要求</td>
                <td colspan="3">
                    <input name="transfer.others" value="${transfer.others}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td width="10%" class="tab_title">移交人</td>
                <td colspan="2">
                    <input name="transfer.transferPerson" value="${transfer.transferPerson}" type="text" class="text_a" readonly="readonly"/>
                </td>
                <td width="10%" class="tab_title">接收人</td>
                <td>
                    <input name="transfer.receiver" value="${transfer.receiver}" type="text" class="text_a" readonly="readonly"/>
                </td>
            </tr>
        </table>
    </div>
</form>
