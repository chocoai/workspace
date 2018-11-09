<div class="w1200 ni_g_bg_fff ni_g_tj ni_mglr_auto mgt30 ni_g_tjbb">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="ni_g_tit fl">
			<a href="report.html">设备使用统计</a>&nbsp;&nbsp;<em>&gt;</em>&nbsp;&nbsp;<a class="hover">明细数据</a>
		</div>
	</div>
	<form id="pageForm"  name="pageForm" action="queryDeviceUseCountDetal.html" method="post">
		<input type="hidden" name="deviceCode" value=${deviceCode}>
		<input type="hidden" name="deviceName" value=${deviceName}>
		<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}">
    	<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
		<div class="ni_g_cont clearfix mgt20 pdb10">
		<div class="clearfix ni_g_c_555555 f16 lh30 mglr40">
			<div class="fl">${deviceName}</div>
			<a href="javascript:void(0)" class="ni_g_btn mglr10 fr" id="ni_g_btn" >查询</a>
			<input type="text" name="currentTime"<#if currentTime??>value="${currentTime}"</#if> class="Wdate inp mgl10 fr" placeholder="选择日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="">
		</div>
		</form>
		<div class="ni_g_table01 mglr40 mgt10 chklist2 ni_g_dev_list_tb ">
		<table class="t_c">
			<thead>
				<tr>
					<th width="5%">编号</th>
					<th width="15%">日期</th>
					<th width="10%">星期</th>
					<th width="10%">教师</th>
					<th width="15%">有效时长</th>
					<th width="20%">使用应用</th>
					<th width="35%">截图</th>
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
								<#if bean.week??>
									${bean.week}
								</#if>
							</td>
							
							<td>
								<#if bean.user_name??>
									${bean.user_name}
								</#if>
							</td>
							
							<td>
								<#if bean.use_taking??>
									${formatDuring(bean.use_taking)}
								</#if>
							</td>
							<td>
								<#if bean.app_name??>
									${formatAppName(bean.app_name)}
								</#if>
							</td>
							
							<td class="ni_fun_tab_img ni_g_tab_img" data-img=
								<#if bean.imgs??>
									"${bean.imgs}"
								</#if>
							>
								<#if bean.imgs??>
									<#assign imgList=bean.imgs?split(",") />
									共${imgList?size}张
								</#if>
							</td>
							
							
						</tr>
					</#list>
				
				
			</tbody>
		</table>
	</div>
			<div class="turnPage t_c mgt30">
				<#include "../_page.ftl">
			</div>
	</div>
</div>
<!--明细数据 E-->
<div class="ni_g_pop_show_imgs_list dis_none">
	<div class="ni_img_box">
		<div class="ni_prev"></div>
		<div class="ni_next"></div>
		<div class="ni_list">
			<ul class="ni_ul">
			</ul>
		</div>
		<div class="ni_del clearfix mgt25">
			<span class="fl ni_time"></span>
			<span class="fr ni_num">第<em class="ni_now">1</em>张，共<em class="ni_all">10</em>张</span>
		</div>
		
	</div>
</div>