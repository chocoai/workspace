<div class="w1200 ni_g_bg_fff ni_g_tj ni_mglr_auto mgt30">
	<ul class="ni_ul clearfix">
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i01">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">设备总数</div>
				<div class="ni_num mgt10">${deviceTotal}</div>
			</div>
		</li>
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i02">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">开机数</div>
				<div class="ni_num mgt10">${offTotal}</div>
			</div>
		</li>
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i03">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">昨日开机率</div>
				<div class="ni_num mgt10">${dayOffRate}%</div>
			</div>
		</li>
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i04">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">近30天开机率</div>
				<div class="ni_num mgt10">${thridDayOffRate}%</div>
			</div>
		</li>
	</ul>
</div>
<!--统计 E-->
<!--人数，开机数，应用统计 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_tj_3h1 ni_g_tab pdb30">
	<div class="ni_g_tit ni_g_tab_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">累计使用人数</a>
			<a class="pdlr5" href="javascript:;">设备开机数</a>
			<a class="pdlr5" href="javascript:;">应用统计</a>
		</div>
	</div>
	<div class="ni_g_cont ni_g_tab_cont">
		<div class="ni_tab hover">
			<div class="ni_g_echarts_tips clearfix">
				<span class="fl">单位：人</span>
				<span class="fr">教师总人数：<em id="teacherTotal">${teacherNum}</em>人</span>
			</div>
			<div class="ni_g_echarts ni_g_echarts1" id="ni_g_echarts1" style="height:300px;width:1200px"></div>
		</div>
		<div class="ni_tab">
			<div class="ni_g_echarts_tips clearfix">
				<span class="fl">单位：台</span>
				<span class="fr">设备总台数：<em id="deviceOffTotal">${deviceTotal}</em>台</span>
			</div>
			<div class="ni_g_echarts ni_g_echarts2" id="ni_g_echarts2" style="height:300px;width:1200px"></div>
		</div>
		<div class="ni_tab">
			<div class="ni_g_echarts_tips clearfix">
				<span class="fl">单位：次</span>
				<span class="fr">应用统计总个数：<em id="appTotal"></em>个</span>
			</div>
			<div class="ni_g_echarts ni_g_echarts3" id="ni_g_echarts3" style="height:300px;width:1200px"></div>
		</div>
		
	</div>
</div>
<!--人数，开机数，应用统计 E-->
<!--使用时长统计 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_tj_time pdb30">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">使用时长统计</a>
		</div>
		<div class="fr ni_g_time_area duk">
			<a class="hover" href="javascript:;" id="deviceUseTaking1">昨日</a>
			<a href="javascript:;" id="deviceUseTaking2">最近一周</a>
			<a href="javascript:;" id="deviceUseTaking3">最近一月</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20 pdt20">
		<div class="fl ni_g_echarts ni_g_echarts4 mgl30" style="width:530px;height: 330px;"></div>
		<div class="fr ni_g_table01 mgr30 pdr15" style="width:574px;">
			<table class="t_c">
				<tr>
					<th>使用时长</th>
					<th>使用次数</th>
					<th>时间占比</th>
				</tr>
				 <tbody class="deviceUseTakingCycle">
				 
				 </tbody>

			</table>
		</div>
	</div>
</div>
<!--使用时长统计 E-->
<!--教师使用排名 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_pm_tear pdb30">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">教师使用排名</a>
		</div>
		<div class="fr ni_g_time_area teacherUseTaking">
			<a class="hover" href="javascript:;" id="ni_g_gime_area1">昨日</a>
			<a href="javascript:;" id="ni_g_gime_area2">最近一周</a>
			<a href="javascript:;" id="ni_g_gime_area3">最近一月</a>
		</div>
	</div>   
	<div class="ni_g_cont clearfix mgt20 pdt20">
		<ul class="ni_ul mglr20 ni_g_pm_list01">
			<li class="ni_li">
				<div class="ni_g_pm_tit ni_i01 mgl10" >
					前五名排行
				</div>
				<dl class="ni_dl mgt25" id="top5">
					
				</dl>
			</li>
			<li class="ni_li ni_li02">
				<div class="ni_g_pm_tit ni_i02 mgl10" >
					上升最快排名
				</div>
				<dl class="ni_dl mgt25" id="riseTop5">
				</dl>
			</li>
			<li class="ni_li ni_li02">
				<div class="ni_g_pm_tit ni_i03 mgl10" >
					下降最快排名
				</div>
				<dl class="ni_dl mgt25" id="fallTop5">
				</dl>
			</li>
		</ul>
	</div>
</div>
<!--教师使用排名 E-->
<!--学科排名 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_pm_xk pdb30">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">学科</a>
		</div>
		<div class="fr ni_g_time_area useTaking">
			<a class="hover" href="javascript:;" id="useTaking1">昨日</a>
			<a href="javascript:;" id="useTaking2">最近一周</a>
			<a href="javascript:;" id="useTaking3">最近一月</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20 pdt20">
		<div class="ni_g_echarts ni_g_echarts5 mglr20" id="ni_g_echarts5" style="height:370px;"></div>
		<div class="ni_g_table01 mglr20 mgt30">
			<table class="t_c">
				<tr>
					<th>使用时长</th>
					<th>使用总时长</th>
					<th>使用次数</th>
					<th>占比</th>
					<th>累计使用人数</th>
				</tr>
			<tbody id="subjectCount">
				
			</tbody>
			
			</table>
		</div>
	</div>
</div>

<!--应用使用情况 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_app_user ">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">应用使用情况</a>
		</div>
		<div class="fr ni_g_time_area useQK">
			<a class="hover" href="javascript:;" id="useQK1">昨日</a>
			<a href="javascript:;" id="useQK2">最近一周</a>
			<a href="javascript:;" id="useQK3">最近一月</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20 pdt20">
		<ul class="ni_ul mglr20 ni_g_pm_list01">
			<li class="ni_li ni_li01 mgl30">
				<div class="ni_g_pm_tit ni_i01 mgl10">
					前五名排行
				</div>
				<dl class="ni_dl mgt25 topApp">
					
					
				</dl>
			</li>
			<li class="ni_li ni_li02">
				<div class="ni_g_pm_tit ni_i04 mgl10">
					其他应用
				</div>
				<div class="ni_g_black_a mgt25 qtApp">

				</div>
			</li>
		</ul>
	</div>
</div>

<@layout.scripts>
  <script type="text/javascript" src="../../js/school/index.js"></script>
</@layout.scripts>


