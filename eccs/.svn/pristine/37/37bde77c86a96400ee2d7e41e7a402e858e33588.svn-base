
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a href="/bid/listSummy.htm">投标总结</a> >
${project.name}
</div>

<form action="/bid/saveSummy.htm" method="post" id="contractForm">
	<div id="content">
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">
			投标总结
		</div>
		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
			<tr>
				<td colspan="2" class="tab_title">
					投标项目名称
				</td>
				<td colspan="7" style="background-color: #fff;">
					${project.name}
				</td>
			</tr>
			<tr>
				<td colspan="2" class="tab_title">
					投标部门
				</td>
				<td colspan="3" style="background-color: #fff;">
					${bidSummary.bidDeptName}
				</td>
				<td colspan="2" class="tab_title">
					开标时间
				</td>
				<td colspan="2" style="background-color: #fff;">
					${bidSummary.openDate}
				</td>
			</tr>
			<tr>
				<td colspan="2" class="tab_title">
					项目分级
				</td>
				<td colspan="3" style="background-color: #fff;">
					${bidSummary.projectLevel}
				</td>
				<td colspan="2" class="tab_title">
					所属片区
				</td>
				<td colspan="2" style="background-color: #fff;">
					${bidSummary.area}
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
				<td width="89" class="tab_title3">
					投标结果
				</td>
				<td colspan="3" style="background-color: #fff;">
					${bidSummary.bidResult}
				</td>
				<td width="60" class="tab_title3">
					中标价格（元）/费率/折扣
				</td>
				<td colspan="2" style="background-color: #fff;">
					${bidSummary.inBidAmount}
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					投标补偿费（元）
				</td>
				<td colspan="3" style="background-color: #fff;">
					${bidSummary.bidCompensateAmount}
				</td>
				<td colspan="3" style="background-color: #fff;">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<#if bidSummary==null && bidSummary.isPaid == 1>&nbsp;已支付&nbsp;&nbsp;&nbsp;&nbsp; </#if>
					<#if bidSummary.isPaid == 0> &nbsp;未支付</#if>
				</td>
			</tr>
			<tr>
				<td class="tab_title2">
					投标直接费用（元）
				</td>
				<td colspan="3" style="background-color: #fff;">
					${bidSummary.bidDirectAmount}
				</td>
				<td class="tab_title2">
					投标人工费用（元）
				</td>
				<td colspan="2" style="background-color: #fff;">
					${bidSummary.bidPersionAmount}
				</td>
			</tr>
			<tr>
				<td colspan="2" class="tab_title">
					本次投标心得与总结
				</td>
				<td colspan="7" style="background-color: #fff;">
					${bidSummary.bidContent}
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<strong>投标保证金</strong>
				</td>
			</tr>
			<tr>
				<td colspan="2"
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%;">
					单位名称
				</td>
				<td colspan="2"
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%;">
					缴纳日期
				</td>
				<td
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%;">
					保证金（元）
				</td>
				<td
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%;">
					退回日期
				</td>
				<td
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 16%;">
					退回金额（元）
				</td>
				<td
					style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 20%;">
					未退金额（元）
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center; background-color: #fff;">
					${bidSummary.bondDeptName}
				</td>
				<td colspan="2" style="text-align: center; background-color: #fff;">
					${bidSummary.bondPayDate}
				</td>
				<td style="text-align: center; background-color: #fff;">
					${bidSummary.bondAmount}
				</td>
				<td style="text-align: center; background-color: #fff;">
					${bidSummary.bondReturnDate}
				</td>
				<td style="text-align: center; background-color: #fff;">
					${bidSummary.bondReturnAmount}
				</td>
				<td style="text-align: center; background-color: #fff;">
					${bidSummary.bondNoreturnAmount}
				</td>
			</tr>
			<tr>
				<td colspan="9">
					<div style="float: left; border-bottom: 0px">
						<strong>竞争对手情况</strong>
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
								-
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
					<table border="1" cellspacing="1" cellpadding="1"
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
								<#if annex.id!="" >
								<a href="/file/xiazai.htm?proid=${annex.id}">下载</a>
								</#if>
							</td>
						</tr>
						</#list>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="10" style="text-align: right;">
					<input type="button" value="返回" class="sub" onclick="javascript:history.go(-1);" />
					<input type="button" value="打印" class="sub" onclick="javascript:toPrint(${bidSummary.id});" />
				</td>
			</tr>
		</table>

	</div>
</form>

<script>
function toPrint(id){
	var url = "/bid/printSummy.htm?bidSummary.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>

