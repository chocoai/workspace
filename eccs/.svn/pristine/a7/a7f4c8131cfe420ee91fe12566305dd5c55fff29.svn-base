
<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>

<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a href="/bid/listSummy.htm">投标总结</a> > 
${project.name}
</div>

<form action="/bid/saveSummy.htm" method="post" id="bidSummaryForm">
	<div id="content">
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">
			投标总结
		</div>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td colspan="2" class="tab_title">
					投标项目名称
				</td>
				<td colspan="7">
					<input name="bidSummary.id" value="${bidSummary.id}" type="hidden" />
					<input name="project.id" value="${project.id}" type="hidden" id="projectIdId" />
					<input name="project.name" value="${project.name}"
						readonly="readonly" id="projectNameId" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="tab_title red">
					投标部门
				</td>
				<td colspan="3">
					<input name="bidSummary.bidDeptName"
						value="${bidSummary.bidDeptName}" type="text" class="text_a" />
				</td>
				<td colspan="2" class="tab_title red">
					开标时间
				</td>
				<td colspan="2">
					<input type="text" class="text_a" name="bidSummary.openDate" value="${bidSummary.openDate}"
						readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="tab_title">
					项目分级
				</td>
				<td colspan="3">
					<input name="bidSummary.projectLevel"
						value="${bidSummary.projectLevel}" type="text" class="text_a" />
				</td>
				<td colspan="2" class="tab_title">
					所属片区
				</td>
				<td colspan="2">
					<input type="text" class="text_a" name="bidSummary.area" value="${bidSummary.area}" />
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<strong>中标结果信息</strong>
				</td>
			</tr>
			<tr>
				<td colspan="2" rowspan="3" class="tab_title2">
					我方投标结果
				</td>
				<td width="89" class="tab_title3 red">
					投标结果
				</td>
				<td colspan="3">
					<input name="bidSummary.bidResult" value="${bidSummary.bidResult}" type="text" class="text_a" />
				</td>
				<td width="60" class="tab_title3 red">
					中标价格（元）/费率/折扣
				</td>
				<td colspan="2">
					<input name="bidSummary.inBidAmount"
						value="${bidSummary.inBidAmount}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td class="tab_title2 red">
					投标补偿费（元）
				</td>
				<td colspan="3">
					<input type="text" class="text_a" name="bidSummary.bidCompensateAmount" value="${bidSummary.bidCompensateAmount}" />
				</td>
				<td colspan="3" id="radioN" class="red">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="bidSummary.isPaid" value="1" <#if bidSummary==null || bidSummary.isPaid == 1>checked="checked"</#if> /> 
						&nbsp;已支付&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="bidSummary.isPaid" value="0" <#if bidSummary.isPaid == 0>checked="checked"</#if> /> 
						&nbsp;未支付
				</td>
			</tr>
			<tr>
				<td class="tab_title2 red">
					投标直接费用（元）
				</td>
				<td colspan="3">
					<input name="bidSummary.bidDirectAmount"
						value="${bidSummary.bidDirectAmount}" type="text" class="text_a" />
				</td>
				<td class="tab_title2 red">
					投标人工费用（元）
				</td>
				<td colspan="2">
					<input name="bidSummary.bidPersionAmount"
						value="${bidSummary.bidPersionAmount}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="tab_title">
					本次投标心得与总结
				</td>
				<td colspan="7">
					<input name="bidSummary.bidContent"
						value="${bidSummary.bidContent}" type="text" class="text_a" />
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<strong>投标保证金</strong>
				</td>
			</tr>
			<tr>
				<td colspan="2"
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%; color:red;">
					单位名称
				</td>
				<td colspan="2"
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%; color:red;">
					缴纳日期
				</td>
				<td
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%; color:red;" >
					保证金（元）
				</td>
				<td
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%; color:red;">
					退回 日期
				</td>
				<td
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%; color:red;">
					退回金额（元）
				</td>
				<td
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 20%; color:red;">
					未退金额（元）
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<input name="bidSummary.bondDeptName"
						value="${bidSummary.bondDeptName}" type="text" class="text_a" />
				</td>
				<td colspan="2" style="text-align: center;">
					<input name="bidSummary.bondPayDate"
						value="${bidSummary.bondPayDate}" readonly="readonly"
						onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						class="text_a" />
				</td>
				<td style="text-align: center;">
					<input name="bidSummary.bondAmount"
						value="${bidSummary.bondAmount}" type="text" class="text_a" />
				</td>
				<td style="text-align: center;">
					<input type="text" class="text_a" name="bidSummary.bondReturnDate"
						value="${bidSummary.bondReturnDate}" readonly="readonly"
						onClick="javascript:WdatePicker( {dateFmt : 'yyyy-MM-dd'});"  />
				</td>
				<td style="text-align: center;">
					<input name="bidSummary.bondReturnAmount"
						value="${bidSummary.bondReturnAmount}" type="text" class="text_a" />
				</td>
				<td style="text-align: center;">
					<input name="bidSummary.bondNoreturnAmount"
						value="${bidSummary.bondNoreturnAmount}" type="text"
						class="text_a" />
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<div style="float: left; border-bottom: 0px">
						<strong>竞争对手情况</strong>
					</div>
					<div style="float: left;" class="add_link">
						<a href="javascript:addJZDS();">+新增</a>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<table width="100%" id="jingzhengduishouid" border="1"
						cellspacing="1" cellpadding="1" class="list_table4">
						<tr>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								竞争对手名称
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								报价方式
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								报价值
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								总得分
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								排名
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								中标结果
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 45px;">
								操作
							</td>
						</tr>
						<#list bidSummaryOpponentList as bidSummaryOpponent>
						<tr>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryOpponent.name}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryOpponent.amountType}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryOpponent.amount}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryOpponent.count}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryOpponent.rank}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryOpponent.bidResult}
							</td>
							<td style="text-align: center;">
									<a onclick="javascript:deleteRow(${bidSummaryOpponent.id} ,3)">删除</a>
							</td>
						</tr>
						</#list>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="9">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<div style="float: left; border-bottom: 0px">
						<strong>评标专家&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>
					</div>
					<div style="float: left;" class="add_link">
						<a href="javascript:addPBZJ();">+新增</a>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<table width="100%" id="pingbiaozhuanjiaid" border="1"
						cellspacing="1" cellpadding="1" class="list_table4">
						<tr>

							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								姓名
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								单位
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								联系电话
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								备注
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 45px;">
								操作
							</td>
						</tr>
						<#list bidSummaryExpertList as bidSummaryExpert>
						<tr>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryExpert.name}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryExpert.deptName}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryExpert.tel}
							</td>
							<td style="text-align: center; background-color: #fff;">
								${bidSummaryExpert.remark}
							</td>
							<td style="text-align: center;">
								<a onclick="javascript:deleteRow(${bidSummaryExpert.id} ,4)">删除</a>
							</td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>


			<tr>
				<td colspan="9">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td colspan="9">
					<div
						style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px"
						class="add_link">
						<a href="javascript:fileUpLoad()">+新增</a>
					</div>
					<table border="1" id="fileListId" cellspacing="1" cellpadding="1"
						class="list_table4">

						<tr>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 100px;">
								文件名
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								文件类型
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								上传人
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								上传时间
							</td>
							<td
								style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
								操作
							</td>
						</tr>
						<#list annexList as annex>
						<tr>
							<td style="text-align: center; background-color: #fff;">
								${annex.name }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${annex.annexType.name }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${annex.user.name }
							</td>
							<td style="text-align: center; background-color: #fff;">
								${annex.ctime }
							</td>
							<td style="text-align: center;">
								<a onclick="javascript:deleteRow(${annex.id} ,5)">删除</a>
							</td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="10" style="text-align: right;">
					<input type="button" class="sub" value="取消" onclick="javascript: history.go(-1);"/>
					<input type="submit" class="sub" value="保存" class="sub" />
					<input type="button" class="sub" value="提交任务" onclick="javascript: toNextStep();"/>
				</td>
			</tr>
		</table>

	</div>
</form>
<script>
	var i = 0;
	var j = 10000;
	var flag = true;
	var flog = true;
	$().ready(function() {
		$("#bidSummaryForm").validate( {
			rules : {
				'bidSummary.bidDeptName' : {
					required : true
				},
				'bidSummary.openDate' : {
					required : true
				},
				'bidSummary.bidResult' : {
					required : true
				},
				'bidSummary.inBidAmount' : {
					required : true
				},
				'bidSummary.bidCompensateAmount' : {
					required : true,
					number : true
				},
				'bidSummary.isPaid' : {
					required : true
				},
				'bidSummary.bidDirectAmount' : {
					required : true,
					number : true
				},
				'bidSummary.bidPersionAmount' : {
					required : true,
					number : true
				},
				'bidSummary.bondDeptName' : {
					required : true
				},
				'bidSummary.bondPayDate' : {
					required : true
				},
				'bidSummary.bondAmount' : {
					required : true,
					number : true
				},
				'bidSummary.bondReturnDate' : {
					required : true
				},
				'bidSummary.bondReturnAmount' : {
					required : true,
					number : true
				},
				'bidSummary.bondNoreturnAmount' : {
					required : true,
					number : true
				},
				'bidSummary.inBidAmount' : {
					required : true,
					number : true
				}

			}
		})
	});
	
	function toNextStep() { //执行下一步
		if (confirm("当前步骤执行完毕,确定要执行下一步吗?")) {
			document.getElementById("bidSummaryForm").action = "/bid/toNextStep2.htm";
			document.getElementById("bidSummaryForm").submit();
		}
	}
	
	function AddRow_JZDS() {
		//添加一行
		var tab1 = document.getElementById("jingzhengduishouid");
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
		var newTd6 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<input type="text" name="o_name"  class="text_a"  />';
		newTd1.innerHTML = '<input type="text" name="o_amountType"  class="text_a"  />';
		newTd2.innerHTML = '<input type="text" name="o_amount"  class="text_a"  />';
		newTd3.innerHTML = '<input type="text" name="o_count"  class="text_a"  />';
		newTd4.innerHTML = '<input type="text" name="o_rank"  class="text_a"  />';
		newTd5.innerHTML = '<input type="text" name="o_bidResult"  class="text_a"  />';
		newTd6.innerHTML = '<a onclick="javascript:deleteRow(' + i + ',0)">删除</a>';
	}
	
	function AddRow_PBZJ() {
		//添加一行
		var tab2 = document.getElementById("pingbiaozhuanjiaid");
		if (flog) {
			j = j + tab2.rows.length;
			flog = false;
		} else {
			j = j + 1;
		}
		var newTr1 = tab2.insertRow();
		newTr1.id = j;
		//添加列
		var newTd01 = newTr1.insertCell();
		var newTd11 = newTr1.insertCell();
		var newTd21 = newTr1.insertCell();
		var newTd31 = newTr1.insertCell();
		var newTd41 = newTr1.insertCell();
		//设置列内容和属性
		newTd01.innerHTML = '<input type="text" name="e_name"  class="text_a" />';
		newTd11.innerHTML = '<input type="text" name="e_deptName"   class="text_a"/>';
		newTd21.innerHTML = '<input type="text" name="e_tel"   class="text_a" />';
		newTd31.innerHTML = '<input type="text" name="e_remark"  class="text_a" />';
		newTd41.innerHTML = '<a onclick="javascript:deleteRow(' + j + ',0)">删除</a>';
	}
	
	function deleteRow(id,s) {
        if(s=="0"){
		$('#' + id).remove();
			return;
	    }
		if (!confirm("确定删除?")) {
			return;
		}   
		var url = "/bid/deletecls.htm?bidId=" + id+"&types="+s;
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

	function addJZDS() {
		AddRow_JZDS();
	}

	function addPBZJ() {
		AddRow_PBZJ();
	}

	function fileUpLoad() {
 		   var iWidth=650;                          //弹出窗口的宽度; 
           var iHeight=500;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open('/file/fileSummary.htm?bidSummary.id=${bidSummary.id}','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	
	function getProject() {
		var iWidth=1200;                          //弹出窗口的宽度; 
   		var iHeight=600;                         //弹出窗口的高度; 
   		//获得窗口的垂直位置 
   		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
   		//获得窗口的水平位置 
   		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
	    window.open('/project/listForSelect.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function returnSelectProject(id,name,pno,cno,projectTypeName,serviceTypeId,serviceTypeName,editorialTypeId,editorialTypeName,senderDeptName){
		$("#projectIdId").val(id);
		$("#projectNameId").val(name);
	}	
	
</script>

