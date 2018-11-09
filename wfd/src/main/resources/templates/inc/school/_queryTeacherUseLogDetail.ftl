<!--明细数据 S-->

<div class="w1200 ni_g_bg_fff ni_g_tj ni_mglr_auto mgt30 ni_g_tjbb">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="ni_g_tit fl">
			<a href="report.html">教师使用统计</a>&nbsp;&nbsp;<em>&gt;</em>&nbsp;&nbsp;<a class="hover">明细数据</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20 pdb10">
	<form id="pageForm"  name="pageForm" action="queryTeacherUseLogDetail.html" method="post">
		<input type="hidden" name="userName" value=${userName}>
		<input type="hidden" name="userId" value=${userId}>
		<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}">
    	<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
    	
    	<input type="hidden" name="currPage" id="currPage" value="1">
<input type="hidden" name="totalPage" id="totalPage">
<input type="hidden" name="pageSize" id="pageSize" value="10">
    	
		<div class="clearfix ni_g_c_555555 f16 lh30 mglr40">
			<div class="fl">${userName}</div>
			<a href="javascript:void(0)" class="ni_g_btn mglr10 fr" id="ni_g_btn" >查询</a>
			<input type="text" name="currentTime"<#if currentTime??>value="${currentTime}"</#if> class="Wdate inp mgl10 fr  mglr10" placeholder="选择日期" onclick="WdatePicker({maxDate:'2020-10-01\'}'})" style="">
		</div>
	</form>
	
		<div class="ni_g_table01 mglr40 mg20">
			<table class="t_c">
				<thead>
					<tr>
						<th width="5%">编号</th>
						<th width="15%">开机时间</th>
						<th width="12%">使用时长</th>
						<th width="10%">教室</th>
						<th width="40%">使用应用</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<#list pageInfo.list as bean>
						<tr>
							<td>${bean_index+1}</td>
							<td>
								<#if bean.hdkt_login_time??>
									${bean.hdkt_login_time}
								</#if>
							</td>
							<td>
								<#if bean.use_taking??>
									${formatDuring(bean.use_taking)}
								</#if>
							</td>
							<td>
								<#if bean.class_room_name??>
										${bean.class_room_name}
								</#if>
							</td>
							
							<td>
								<#if bean.app_name??>
									${formatAppName(bean.app_name)}
								</#if>
							</td>
						</tr>
					</#list>
				
				</tbody>
			</table>
			<div class="turnPage t_c mgt30">
				<#include "../_page.ftl">
            </div>
        </div>
		</div>
	</div>
</div>
<!--明细数据 E-->