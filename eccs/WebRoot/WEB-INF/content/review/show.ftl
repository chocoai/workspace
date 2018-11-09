<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a href="/review/list.htm">合同评审</a> > ${contract.name}
</div>
<div id="content">
	<div
		style="text-align: center; font-weight: bold; font-family: Microsoft YaHei; font-size: 18px; margin-top: 20px; margin-bottom: 20px;">
		合同评审
	</div>
	<table border="1" cellspacing="1" cellpadding="1" class="input_table">
		<tr>
			<td colspan="2" class="tab_title" style="width: 120px;">
				项目名称
			</td>
			<td colspan="6" style="background-color: #fff;">
				${contract.project.name}
			</td>
		</tr>

		<tr>
			<td colspan="2" class="tab_title" style="width: 120px;">
				合同名称
			</td>
			<td colspan="6" style="background-color: #fff;">
				${contract.name}
			</td>
		</tr>

		<tr>
			<td colspan="2" class="tab_title">
				项目编号
			</td>
			<td colspan="3" style="background-color: #fff;">
				${contract.project.no}
			</td>
			<td width="108" class="tab_title">
				合同编号
			</td>
			<td width="346" style="background-color: #fff;">
				${contractReview.contractNo}
			</td>
		</tr>
		<!-- 
		<tr>
			<td colspan="2" class="tab_title">
				工程概况
			</td>
			<td colspan="6" style="background-color: #fff;">
				${contract.projectInfo}
			</td>
		</tr>
		 -->
		<tr>
			<td colspan="2" class="tab_title">
				咨询类型
			</td>
			<td colspan="3" style="background-color: #fff;">
				${contract.project.serviceType.name}
			</td>
			<td class="tab_title">
				业务范围
			</td>
			<td width="346" style="background-color: #fff;">
				${contract.project.editorialType.name}
			</td>
		</tr>
		<tr>
			<td colspan="2" class="tab_title2">
				咨询服务收费标准
			</td>
			<td colspan="6" style="background-color: #fff;">
				${contract.chargeRemark}
			</td>
		</tr>
		<tr>
			<td colspan="2" class="tab_title">
				签约部门
			</td>
			<td colspan="3" style="background-color: #fff;">
				${contract.contractDept.name}
			</td>
			<td class="tab_title">
				签约人
			</td>
			<td width="346" style="background-color: #fff;">
				${contract.manager.name}
			</td>
		</tr>
		<tr>
			<td colspan="2" class="tab_title">
				咨询服务费支付方式
			</td>
			<td colspan="6" style="background-color: #fff;">
				<#if contract.contractPay == 1>&nbsp;包干</#if> 
				<#if contract.contractPay == 0>&nbsp;暂定</#if>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="tab_title">
				委托单位
			</td>
			<td colspan="6" style="background-color: #fff;">
				${contract.project.customer.cusName}
			</td>
		</tr>
		<tr>
			<td colspan="2" class="tab_title">
				工程投资（万元）
			</td>
			<td colspan="3" style="background-color: #fff;">
				${contract.projectAmount}
			</td>
			<td class="tab_title">
				咨询服务费（万元）
			</td>
			<td width="346" style="background-color: #fff;">
				${contract.allAmount}
			</td>
		</tr>
		<tr>
			<td colspan="9">
				<em>&nbsp;</em>
			</td>
		</tr>
		<tr>
			<td width="133" class="tab_title">
				初评人
			</td>
			<td colspan="4" style="background-color: #fff;">
				${contractReview.recordName}
			</td>
			<td class="tab_title">
				记录日期
			</td>
			<td style="background-color: #fff;">
				${contractReview.recordDate}
			</td>
		</tr>
		<tr>
			<td width="133" class="tab_title">
				评审内容
			</td>
			<td colspan="8" class="tab_title">
				<table border="1" id="contractReviewItemId" cellspacing="1"
					cellpadding="1" class="list_table3" style="margin-top: 10px;">
					<tr>
						<td width="234"
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							名称
						</td>
						<td width="133"
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							评审内容
						</td>
						<td width="155"
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							评审意见
						</td>

					</tr>
					<#list contractReviewItemList as contractReviewItem>
					<tr>
						<td style="text-align: center; background-color: #fff;">
							${contractReviewItem.reviewName}
						</td>
						<td style="text-align: center; background-color: #fff;">
							${contractReviewItem.content}
						</td>
						<td style="text-align: center; background-color: #fff;">
							${contractReviewItem.satisfaction}
						</td>

					</tr>
					</#list>
				</table>
			</td>
		</tr>
		<tr>
			<td width="133" class="tab_title">
				评审人签字
			</td>
			<td colspan="8" style="background-color: #fff;">
				${contractReview.reviewName}
			</td>
		</tr>
		<tr>
			<td width="133" class="tab_title">
				评审意见
			</td>
			<td colspan="8" style="background-color: #fff;">
				${contractReview.reviewView}
			</td>
		</tr>

		<tr>
			<td width="133" class="tab_title">
				审批人
			</td>
			<td colspan="8" style="background-color: #fff;">
				${contractReview.approver.name}
			</td>
		</tr>
		<tr>
			<td width="133" class="tab_title">
				审批意见
			</td>
			<td colspan="8" style="background-color: #fff;">
				${contractReview.approveView}
			</td>
		</tr>
		<tr>
			<td width="133" class="tab_title">
				添加附件
			</td>
			<td colspan="8">

				<table border="1" cellspacing="1" cellpadding="1"
					class="list_table3" style="margin-top: 10px;">
					<tr>
						<td width="254"
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							附件名称
						</td>
						<td width="100"
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							所属步骤
						</td>
						<td width="205"
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							上传人
						</td>
						<td width="120"
							style="text-align: center; font-weight: bold; background-color: #d3e0f1;">
							上传时间
						</td>
						<td width="100"
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
				<input type="button" value="打印" class="sub" onclick="javascript:toPrint(${contractReview.id});" />
			</td>
		</tr>
	</table>
</div>

<script>
function toPrint(id){
	var url = "/review/print.htm?contractReview.id=" + id;
		   var iWidth=1000;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open(url,'','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	}
</script>


