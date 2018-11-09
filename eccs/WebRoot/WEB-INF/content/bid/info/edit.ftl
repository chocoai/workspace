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
    <a href="">开标中标情况编辑</a>
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
                <td width="10%" class="tab_title red">开标人员</td>
                <td colspan="4">
                    <input name="info.bidOpenPerson" value="${info.bidOpenPerson}" type="text" class="text_a" maxlength="20"/>
                </td>
                <td width="10%" class="tab_title red">投标家数</td>
                <td>
                    <input name="info.bidNumber" value="${info.bidNumber}" type="text" class="text_a" />
                </td>
            </tr>
			<tr>
                <td width="10%" class="tab_title red">中标单位</td>
                <td colspan="2">
                    <input name="info.bidWinner" value="${info.bidWinner}" type="text" class="text_a" maxlength="30"/>
                </td>
                <td width="10%" class="tab_title red">是否中标</td>
                <td>
                    <i>中标</i>&nbsp;&nbsp;<input value="1" type="radio" name="info.isSuccess" checked="checked"/>
                    <i>未中标</i>&nbsp;&nbsp;<input value="0" type="radio" name="info.isSuccess" />
                </td>
                <td class="tab_title">中标价</td>
                <td>
                    <input name="info.bidWinnerPrice" value="${info.bidWinnerPrice}" type="text" class="text_a" />
                </td>
			</tr>
			<tr>
			    <td colspan="8">
    			    <div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
                                                                <a href="javascript:AddRow();">新增</a>
                    </div>
                    <table border="1" id="infoTableId" cellspacing="1" cellpadding="1" class="list_table4">
                        <tr>
            				<td width="5%" style="text-align:center;">序号</td>
            				<td width="30%" style="text-align:center;">单位</td>
            				<td width="20%" style="text-align:center;">报价</td>
            				<td width="20%" style="text-align:center;">预算</td>
            				<td width="20%" style="text-align:center;">比例</td>
            				<td width="5%" style="text-align:center;">操作</td>
            		    </tr>
                        <#list infoQuoteList as infoQuote>
            		    <tr>
                            <td width="5%" style="text-align:center;">
                            ${infoQuote_index+1}
                                <input type="hidden" value="${infoQuote.cid}" name="infoQuoteCid">
                            </td>
                            <td width="30%">
                                <input type="text" value="${infoQuote.company}" name="infoQuoteCompany"  class="text_a" maxlength="30"/>
                            </td>
                            <td width="20%">
                                <input type="text" value="${infoQuote.quotedPrice}" name="infoQuoteQuotedPrice" placeholder="请填入数字"  class="text_a"  />
                            </td>
                            <td width="20%">
                                <input type="text" value="${infoQuote.budgetaryPrice}" name="infoQuoteBudgetaryPrice" placeholder="请填入数字"  class="text_a"  />
                            </td>
                            <td width="20%" >
                                <input type="text" value="${infoQuote.ratio}" name="infoQuoteRatio" placeholder="请填入数字"  class="text_a"  />
                            </td>
                            <td width="5%" style="text-align:center;">
                                <a onclick="javascript:deleteRow(${infoQuote_index+1},0,${infoQuote.cid})">删除</a>
                            </td>
            		    </tr>
            		    </#list>
            	    </table>
            	</td>
			</tr>
			<tr>
                <td class="tab_title red" width="12%">
                    <span id="nextStepNamea">下一环节处理人</span>
                </td>
                <td colspan="6">
                    <input type="hidden" name="nextOperatorId" id="nextOperatorId" value="${nextOperatorId}" />
                    <input type="text" name="nextOperatorName" id="nextOperatorName" value="${nextOperatorName}"
                        class="text_a" readonly="readonly" onclick="javascript:getNextOperator('nextOperatorId','nextOperatorName');"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="7" style="text-align: right;">
                    <input type="button" class="sub" value="取消" onclick="javascript: history.go(-1);" />
                    <input type="button" class="sub" value="提交" onclick="toNextStep()"/>
                    <input type="submit" class="sub" value="保存" />
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

	var i = 0;
	var j = 10000;
	var flag = true;
	var flog = true;
	var operaterType = 0;
	$().ready(
	
	function() {
		$("#contractForm").validate( {
			rules : {
				'info.bidOpenPerson' : {
					required : true
				},
				'info.bidNumber' : {
					required : true
				},
				'info.bidWinner' : {
					required : true
				},
				'info.isSuccess' : {
					required : true
				},
				'infoQuoteCompany' : {
					required : true
				},
				'infoQuoteQuotedPrice' : {
					required : true,
					number : true
				},
				'infoQuoteBudgetaryPrice' : {
					required : true,
                    number : true
				},
				'infoQuoteRatio' : {
					required : true,
                    number : true
				}
			}
		})
	});
	
	function toNextStep() { //执行下一步
        var nextOperator = $("#nextOperatorName").val();
        if(nextOperator.length<1){
        alert("请选择下一环节处理人");
        return;
        }
	   var isSuccess = $(":radio").eq(0).prop("checked");
        var message = "";
	   if(isSuccess){
	       message = "当前步骤执行完毕,下一环节为【项目移交】，下一环节处理人为【" + nextOperator + "】，确定要执行下一步吗?";
	   }else{
           message = "当前步骤执行完毕,下一环节为【项目总结】，下一环节处理人为【" + nextOperator + "】，确定要执行下一步吗?";
	   }
		if (confirm(message)) {
			document.getElementById("contractForm").action = "/bid/info/toNextStep.htm";
			$("#contractForm").submit();
		}
	}
	
	function AddRow() {
		//添加一行
		var tab1 = document.getElementById("infoTableId");
		if (flag) {
			i = i + tab1.rows.length;
			flag = false;
		} else {
			i = i + 1;
		}
		var newTr = tab1.insertRow();
		newTr.id = i;
		//添加列
		var newTd0 = newTr.insertCell();
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		
		newTd0.style.textAlign = "center";
		newTd5.style.textAlign = "center";
		//设置列内容和属性
        newTd0.innerHTML = i;
		newTd1.innerHTML = '<input type="text" name="infoQuoteCompany" class="text_a"  />';
		newTd2.innerHTML = '<input type="text" name="infoQuoteQuotedPrice" placeholder="请填入数字"  class="text_a"  />';
		newTd3.innerHTML = '<input type="text" name="infoQuoteBudgetaryPrice" placeholder="请填入数字"  class="text_a"  />';
		newTd4.innerHTML = '<input type="text" name="infoQuoteRatio" placeholder="请填入数字"  class="text_a"  />';
		newTd5.innerHTML = '<a onclick="javascript:deleteRow(' + i + ',0)">删除</a>';
	}
	
	function deleteRow(id,s,cid) {
        if (!confirm("确定删除?")) {
            return;
        } 
        if(null != cid){
            var url = "/bid/info/deleteInfoQuote.htm?infoQuote=" + cid
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
        if(s=="0"){
        $('#' + id).remove();
            return;
        }  
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
