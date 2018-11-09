<!--设备详细 S-->



<div class="w1200 ni_g_bg_fff mgt30 ni_mglr_auto pdb30 ">

	<form id="pageForm"  name="pageForm" action="deviceDetail.html" method="post">
			<input type="hidden" name="offStatus" value="${deviceInfo.offStatus}" />
			<input type="hidden" name="schoolId"  value="${deviceInfo.schoolId}"/>
			<input type="hidden" name="deviceCode" value="${deviceInfo.deviceCode}"/>
			<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}">
    		<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
			
		<div class="ni_g_tit mglr20 pdlr10 clearfix">
			<div class="ni_g_tit fl">
				<a href="device.html">设备列表</a>&nbsp;&nbsp;<em>></em>&nbsp;&nbsp;<a class="hover">设备 ${deviceInfo.name}</a>
			</div>
			<div class="fr">
				时间
				<input type="text" <#if startTime??>value="${startTime}"</#if>  class="Wdate inp mgl10" name="startTime" placeholder="选择日期" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" style="">&nbsp;&nbsp;
				至&nbsp;&nbsp;<input type="text" <#if endTime??>value="${endTime}"</#if> class="Wdate inp" name="endTime" placeholder="选择日期" onclick="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" style="">
				<a href="javascript:void(0)" class="ni_g_btn mglr10" id="ni_g_btn">查询</a>
			</div>
		</div>
	</form>
	<div class="ni_g_cont clearfix mgt20">
		<div class="ni_g_kz_lr clearfix mglr40">
			<div class="fl ni_screen">
				<div class="ni_img">
					<img src="${imgPath}" alt="" />
				</div>
				<div class="ni_btn mgt15">
					<a id="shutdown" class="ni_i01 <#if deviceInfo.offStatus == '0'>ni_no_at</#if>" href="javascript:void(0)"><span>关机</span></a>
					<a id="restart" class="ni_i02 <#if deviceInfo.offStatus == '0'>ni_no_at</#if>" href="javascript:void(0)"><span>重启</span></a>
					<a id="lockScreen" class="ni_i03 <#if deviceInfo.offStatus == '0'>ni_no_at</#if>" href="javascript:void(0)"><span>锁屏</span></a>
					<a class="ni_i06 ni_last ni_fun_pop_wzgb <#if deviceInfo.offStatus == '0'>ni_no_at</#if>" href="javascript:;"><span>文字信息广播</span></a>
				</div>
			</div>
			<div class="fr ni_connect pdb10">
				<div class="ni_p mgt30">
					<span class="ni_n">教室: 
					<#if deviceInfo.classRoomName??>
					<input type="text" class="inp ni_un" value="${deviceInfo.classRoomName}" name="classRoomName" disabled="disabled" /> 
					<span class="ni_g_c_38adff ni_fun_undisabled">修改</span><span id="gou" class="mglr40"></span>
					</#if>
					</span>
				</div>
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">设备名称：</span>
						<span><#if deviceInfo.name??>${deviceInfo.name}</#if></span>
					</div>
					<div class="fr">
						
					</div>
				</div>
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">系统版本：</span>
						<span><#if deviceInfo.systemVersion??>${deviceInfo.systemVersion}</#if></span>
					</div>
					<div class="fr">
						<span class="ni_n">内 存：</span>
						<span><#if deviceInfo.storage??>${deviceInfo.storage}</#if></span>
					</div>
				</div>
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">存 储：</span>
						<span><#if deviceInfo.diskSize??>${deviceInfo.diskSize}</#if></span>
					</div>
					<div class="fr">
						<span class="ni_n">分辨率：</span>
						<span><#if deviceInfo.resolution??>${deviceInfo.resolution}</#if></span>
					</div>
				</div>
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">CPU信息：</span>
						<span><#if deviceInfo.cpu??>${deviceInfo.cpu}</#if></span>
					</div>
				</div>
				<div class="ni_p clearfix">
					<div class="fl">
						<span class="ni_n">mac地址：</span>
						<span><#if deviceInfo.mac??>${formatMac(deviceInfo.mac)}</#if></span>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<!--设备详细 E-->

<!--设备列表 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto pdb30 pdt30">
	<div class="ni_g_table01 mglr40 mgt10 chklist2 ni_g_dev_list_tb ">
		<table class="t_c">
			<thead>
				<tr>
					<th width="10%">星期</th>
					<th width="10%">日期</th>
					<th width="10%">学科</th>
					<th width="15%">授课教师</th>
					<th width="15%">有效时长</th>
					<th width="40%">截图</th>
				</tr>	
			</thead>
			<tbody>
				<#list pageInfo.list as deviceInfo>
					<tr>
						<td>
							<#if deviceInfo.week??>
								${deviceInfo.week}
							</#if>
						</td>
						<td>
							<#if deviceInfo.hdkt_login_time??>
								${deviceInfo.hdkt_login_time?date}
							</#if>
						</td>
						<td>
							<#if deviceInfo.subject_name??>
								${deviceInfo.subject_name}
							</#if>
						</td>
						
						<td>
							<#if deviceInfo.user_name??>
								${deviceInfo.user_name}
							</#if>
						</td>
						
						<td>
						
							<#if deviceInfo.use_taking??>
								${formatDuring(deviceInfo.use_taking)}
							</#if>
						</td>
						
						<td class="ni_fun_tab_img ni_g_tab_img" data-img=
							<#if deviceInfo.imgs??>
								"${deviceInfo.imgs}"
							</#if>
						>
							<#if deviceInfo.imgs??>
								<#assign imgList=deviceInfo.imgs?split(",") />
								共${imgList?size}张
							</#if>
						
						</td>
						
					</tr>
				</#list>
			</tbody>
		</table>
	</div>
		<div class="turnPage t_c mgt20">
			<#include "../_page.ftl">
        </div>
</div>

<div class="ni_g_pop_txt_udp dis_none pdb20">
	<div class="ni_tit pdtb5">输入广播消息</div>
	<textarea class="inp" name="sendMessage" id="sendMessage" style="width:80%;height:120px;margin:0 auto;display:block;"></textarea>
	<div class="ni_d_lr radiolist mgt15">
		
	</div>
	<div class="ni_d_lr radiolist mgt15">
		<span>连续时间：</span>
		<input type="text" class="inp t_c" id="delayTime" name="delayTime" style="width:40px;ver"
		onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
		
		 />
		秒
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


<@layout.scripts>
<script type="text/javascript" src="../../js/fun.js"></script>
<script type="text/javascript" src="../../js/school/deviceDetail.js"></script>
</@layout.scripts>