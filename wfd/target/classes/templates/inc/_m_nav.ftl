 <#assign base = request.contextPath />
 <#assign formatDuring = "com.whty.ecspage.common.utils.freemarker.FormatDuring"?new()/>
 <#assign formatMac = "com.whty.ecspage.common.utils.freemarker.FormatMacMethod"?new()/>
  <#assign formatAppName = "com.whty.ecspage.common.utils.freemarker.AppNameMethod"?new()/>
<div class="ni_g_header">
	<div class="w1200 clearfix ni_mglr_auto">
		<div class="fl logo">
			<img src="${base}/images/logo.png" class="pdtb25"  />
		</div>
		
		<#if state_nav_type=='school'>
			<ul class="ni_menu fl clearfix mgt20 mgl30">
				<li class="fl"><a class="<#if state_nav_list=='1'>hover</#if>" href="index.html">首页</a></li>
				<li class="fl"><a class="<#if state_nav_list=='2'>hover</#if>" href="device.html">设备监管</a></li>
				<li class="fl"><a class="<#if state_nav_list=='3'>hover</#if>" href="report.html">统计报表</a></li>
			</ul>
		</#if>
		
		<#if state_nav_type=='leader'>
			<ul class="ni_menu fl clearfix mgt20 mgl30">
				<li class="fl"><a class="hover" href="index.html">首页</a></li>
				<li class="fl"><a href="device.html">设备监管</a></li>
				<li class="fl"><a href="report.html">统计报表</a></li>
			</ul>
		</#if>
		
		
		<div class="fr mgt25">
			<div class="fl clearfix">
				<a href=""  title="${Session.sessionUser.orgName}">
					<div class="ni_img_hd fl"><img src="${Session.sessionUser.userPic}" alt="" /></div>
					<div class="ni_txt_hd fl mgl10">${Session.sessionUser.userName}</div>
				</a>
			</div>
			<div class="fl mgl30 pdl30">
				<a class="ni_unlogin" href="${base}/user/logout">退出</a>
			</div>
		</div>
	</div>
</div>