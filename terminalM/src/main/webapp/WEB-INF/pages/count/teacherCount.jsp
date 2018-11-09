<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
		<div class="w1200 ni_g_bg_fff mgt20 ni_mglr_auto ni_g_app_user ">
			<div class="ni_g_tit mglr20 pdlr10">
				<div class="wql_g_nav fl mgt10">
					<div class="wql_nav01">
						<ul class="clearfix">
							<li class="wql_li"><a href="list">数据总览</a></li>
							<li class="wql_li on"><span class="wql_bl"></span><a href="teacherUseCount">教师使用统计</a></li>
							<li class="wql_li"><span class="wql_bl"></span><a href="deviceUseCount">设备使用统计</a></li>

						</ul>
					</div>
				</div>
			</div>
		</div>

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
							<c:forEach items="${teachers}" var="teacher" varStatus="status">
								<tr>
									<td>${status.index+1}</td>
									<td>${teacher.userName}</td>
									<td>${teacher.subjectId}</td>
									<td>${status.index+1}</td>
									<td>${teacher.createTimeStr}</td>
									<td>${teacher.userCount}</td>
									<td>查看</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="mgt40">
						<p class="site_syspage t_c">
						<form method="post" id="pageForm">
							<input type="hidden" name="startTime" id="startTime">
							<input type="hidden" name="endTime" id="endTime">
							<span class="wql_paginal">每页<em>10</em>条</span>
							<span class="wql_allpage">共<em>${pages }</em>页</span>
							<span class="wql_paginal">去第</span>
							<input type="number" value="${pageNum }" id="vpageNum" min="1" max="${pages }" style="height: 20px; width: 40px; border-radius: 6px; padding: 0 10px; line-height: 34px">
							<span style="margin-left: 0px;">页</span><input type="button" value="Go" onclick="vgetPages()" style="margin-right: 10px; border: 1px solid #e3e3e3; padding: 0 10px; line-height: 20px; background: #fff; cursor: pointer;">
							<a href="#" class="prev" onclick="vprePage()"></a>
							<c:forEach var="s" begin="1" end="${pages }">
								<a <c:if test="${s==pageNum}">class="on"</c:if> href="#" onclick="vgetPage('${s}')">${s}</a>
							</c:forEach>
							<input type="hidden" name="pageValue" value="${pageNum }">
							<a href="#" class="next" onclick="vnextPage()"></a>
							<span class="all">第${pageNum } 页</span>
						</form>
						</p>
					</div>
				</div>
			</div>
		</div>
		<!--教师明细数据 E-->

		<div class="ni_g_tit mglr20 pdlr10">
			时间：
			<input type="text" name="startTime"  value="${startTime}" class="Wdate inp mgl10"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" >
			<span>至</span>
			<input type="text" name="endTime"  value="${endTime}"   onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate inp mgl10">

			<a href="javascript:void(0)" class="ni_g_btn mglr10" id="ni_g_btn" >查询</a>
			<a href="javascript:void(0)" class="ni_g_btn mglr10" id="ni_g_export">导出数据</a>
		</div>
<script type="text/javascript" src="http://116.211.105.43:15022/ecspage/js/echarts.common.min.js"></script>
<script type="text/javascript" src="${ctx}/js/MyPageJs/page.js"></script>
<script type="text/javascript">
var startTime ="${startTime}";
var endTime = "${endTime}";

var totalPage = "${pages }";
//某一页
function vgetPages(){
    var pageNum = $('#vpageNum').val();
    if(pageNum==null || pageNum==""){
        return alert("请输入");
    }
    if(pageNum>totalPage){
        return alert("请输入比"+totalPage+"小的值");
    }
    $('input[name="pageValue"]').val(pageNum);
    $("#pageForm").submit()
}
//某一页
function vgetPage(pageNum){
    $('input[name="pageValue"]').val(pageNum);
    $("#pageForm").submit()
}
//下一页
function vnextPage(){
    var pageNum = $('#vpageNum').val();
    if(pageNum>=totalPage){
        $(this).parent().addClass("disabled")
        return alert("已经是最后一页了");
    }else{
        $('input[name="pageValue"]').val(++pageNum);
        $("#pageForm").submit()
    }
}
//上一页
function vprePage(){
    var pageNum = $('#vpageNum').val();
    if(pageNum<=1){
        $(this).parent().addClass("disabled")
        return alert("已经是首页了");
    }else{
        $('input[name="pageValue"]').val(--pageNum);
        $("#pageForm").submit()
    }
}

$("#ni_g_btn").click(function () {
	startTime = $("#d4311").val();
    endTime = $("#d4312").val();
    $("#startTime").val(startTime);
    $("#endTime").val(endTime);
    $("#pageForm").submit()
});

$("#ni_g_export").click(function () {
    if(confirm("是否导出?")){
        if(undefined ==endTime || undefined==startTime){
            return window.location.href="teacherExcel";
        }
        return window.location.href="teacherExcel?endTime="+endTime+"&startTime="+startTime;
	}
});
</script>