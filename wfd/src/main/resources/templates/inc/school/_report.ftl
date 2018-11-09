<!--头部 E-->
<!--统计 S-->


<div class="w1200 ni_g_bg_fff ni_g_tj ni_mglr_auto mgt30 ni_g_tjbb">

	<form id="pageForm"  name="pageForm" action="report.html" method="post">
	
	<input type="hidden" name="pageNum" id="pageNum">
    <input type="hidden" name="pageSize" id="pageSize">
	
	<div class="ni_g_tit mglr20 pdlr10">
			时间： 
            <input type="text" name="startTime"  value="${startTime }" class="Wdate inp mgl10"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" >
            <span>至</span> 
            <input type="text" name="endTime"  value="${endTime }"   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate inp mgl10">
			
			<a href="javascript:void(0)" class="ni_g_btn mglr10" id="ni_g_btn">查询</a>
			<a href="javascript:void(0)" class="ni_g_btn mglr10" id="ni_g_export">导出数据</a>
	</div>
	</form>
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
				<div class="ni_txt mgt20">总开机数</div>
				<div class="ni_num mgt10">${offDeviceTotal}</div>
			</div>
		</li>
		<li class="ni_li">
			<div class="ni_g_tj_lst t_c ni_i05">
				<div class="ni_img ni_mglr_auto"></div>
				<div class="ni_txt mgt20">累计使用人数</div>
				<div class="ni_num mgt10">${useTotal}</div>
			</div>
		</li>
	</ul>
</div>
<!--统计 E-->

<!--教师明细数据 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto pdb30">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">教师明细数据</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20">
		<div class="ni_g_table01 mglr40">
			<table class="t_c">
				<thead>
					<tr>
						<th>编号</th>
						<th>教师</th>
						<th>学科</th>
						<th>学校</th>
						<th>使用总时长</th>
						<th>使用总次数</th>
						<th>明细数据</th>
					</tr>
				</thead>
				<tbody id="teacherUseLog">
					
					
				</tbody>
			</table>
			<div class="turnPage t_c mgt20" id="teacherUseLogPage">
           
        	</div>
		</div>
	</div>
</div>
<!--教师明细数据 E-->

<!--设备使用统计 S-->
<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto pdb30">
	<div class="ni_g_tit mglr20 pdlr10">
		<div class="fl ni_tit">
			<a class="pdlr5 hover" href="javascript:;">设备使用统计</a>
		</div>
	</div>
	<div class="ni_g_cont clearfix mgt20">
		<div class="ni_g_table01 mglr40">
			<table class="t_c">
				<thead>
					<tr>
						<th>编号</th>
						<th>设备名称</th>
						<th>教室</th>
						<th>使用总次数</th>
						<th>使用总时长</th>
						<th>使用最多的老师</th>
						<th>次数</th>
						<th>数据明细</th>
					</tr>
				</thead>
				<tbody id="deviceUseCount">
					
					
				</tbody>
			</table>
			<div class="turnPage t_c mgt20" id="deviceUseCountPage">
            
            </div>
        </div>
		</div>
	</div>
</div>
<!--设备使用统计 E-->
<@layout.scripts>
<script type="text/javascript" src="../../js/page.js"></script>
<script type="text/javascript" src="../../js/school/report.js"></script>
</@layout.scripts>
