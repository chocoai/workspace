<!--设备列表 S-->
<div class="w1200 ni_g_bg_fff mgt30 ni_mglr_auto pdb30 ">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">设备列表</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20 ni_g_dev_list">
		<div class="ni_l_tb clearfix mglr40">
			<div class="fl ni_g_tab_lt">
				<a <#if searchType == '3' > class="hover" </#if> href="device.html?searchType=3">开机 (${offDeviceSize})</a>
				<a <#if searchType == '2' > class="hover" </#if> href="device.html?searchType=2">关机 (${showDownDeviceSize})</a>
				<a <#if searchType == '1' > class="hover" </#if>  href="device.html?searchType=1">全部 (${allDeviceSize})</a>
			</div>
			<div class="fr ni_g_lt_btn">
				<a class="ni_fun_pop_zdjk" href="javascript:;">终端监控</a>
				<a class="ni_fun_pop_wzgb" href="javascript:;">文字信息广播</a>
			</div>
		</div>
		<div class="ni_g_table01 mglr40 mgt30 chklist2 ni_g_dev_table">
			<table class="t_c">
				<tbody><tr>
					<th><label class="chkAll"><input type="checkbox" name="chkAll"/></label></th>
					<th>设备名称</th>
					<th>教室</th>
					<th>最后开机时间</th>
					<th>开机状态</th>
					<th>操作</th>
					<th>设备详情</th>
				</tr>
				
			<#list pageInfo.list as deviceInfo>
				<tr>
					<td><label class=""><input type="checkbox" value='${deviceInfo.deviceCode}' name="chkItm"/></label></td>
					<td>
						<#if deviceInfo.name??>
							${deviceInfo.name}
						</#if>
					</td>
					<td>
						<#if deviceInfo.classRoomName??>
							${deviceInfo.classRoomName}
						</#if>
					</td>
					<td>
						<#if deviceInfo.lastLoginTime??>
							${deviceInfo.lastLoginTime?datetime}
						</#if>
					</td>
					
					<#if deviceInfo.offStatus=='1'><td class="ni_on">开机</td></#if>
					<#if deviceInfo.offStatus=='0'><td class="ni_off">关机</td></#if>
					
					<td>
						<a href="javascript:void(0)" onclick="appMonitor('${deviceInfo.deviceCode}')">应用监控</a>
						<#if deviceInfo.offStatus=='1' ><a href="javascript:void(0)" onclick="screenMonitor('${deviceInfo.deviceCode}','${deviceInfo.name}','${deviceInfo.classRoomName}')">屏幕监控</a></#if>
					</td>
					<td>
						<a href="deviceDetail.html?deviceCode=${deviceInfo.deviceCode}">查看</a>
					</td>
				</tr>
			</#list>
			</tbody></table>
		</div>
		<div class="turnPage t_c mgt20">
			
        </div>

	</div>
</div>


<!--终端管控弹框 S-->
<div class="ni_g_pop_zdgk dis_none">
	<div class="clearfix">
		<div class="fl ni_lb">
			<span class="hover">电源管理</span>
		</div>
		<div class="fl mgt30 mgl30">
			<div class="ni_tab">
				<div class="ni_cont">
					<div class="ni_d1">
						<a class="ni_off mgr20" href="javascript:void(0)" id="shutdown"><span>关机</span></a>
						<a class="ni_res " href="javascript:void(0)" id="restsrt"><span>重启</span></a>
					</div>
					<div class="ni_d_lr clearfix mgt20">
						
					</div>
					<div class="ni_d_lr clearfix mgt20 mgb30">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--终端管控弹框 E-->
<!--文本消息广播 S-->
<div class="ni_g_pop_txt_udp dis_none pdb20">
	<div class="ni_tit pdtb5">输入广播消息</div>
	<textarea class="inp" style="width:80%;height:120px;margin:0 auto;display:block;" name="sendMessage" id="sendMessage"></textarea>
	
	<div class="ni_d_lr radiolist mgt15">
		<span>连续播放次数：</span>
		<span>连续时间：</span>
							<input type="text" class="inp t_c" id="delayTime" name="delayTime" style="width:40px;ver" 
							onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
							/>
							秒
	</div>
</div>
<!--文本消息广播 E-->


<!--应用监控 S-->
<div class="ni_g_pop_app_jk dis_none" style="max-height:500px;overflow-y:auto;">
	<div class="ni_g_pm_tit ni_i04 mgl10">
		前五名应用
	</div>
	<div class="ni_g_black_a mgt25 clearfix">
	</div>
	
	

</div>
<!--应用监控 E-->


<!--屏幕监控 S-->
<div class="ni_g_pop_screenMonitor_udp dis_none">
	
</div>
<!-屏幕监控 E-->


<!--设备列表 E-->
<@layout.scripts>
<script type="text/javascript" src="../../js/fun.js"></script>
  <script type="text/javascript" src="../../js/school/device.js"></script>
</@layout.scripts>