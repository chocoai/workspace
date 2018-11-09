<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学校统计</title>
</head>
<body>


<div class="mg15 txq_main">

	<form id="pageForm"  name="pageForm" action="${ctx}/manage/installLog/list" method="post">
		<div class="clearfix">
			<p class="fl mgr10"><span>区域：</span><input id="area" name="area" value="${area }" type="text" style="width:150px;" class="inp focus"/></p>	
			<p class="fl mgr10"><span>学校：</span><input id="school" name="school" value="${school }" type="text" style="width:150px;" class="inp focus"/></p>
	        <p class="fl mgr10"><span>姓名：</span><input id="userName" name="userName" value="${userName }" type="text" style="width:150px;" class="inp focus"/></p>
	    	<p class="fl mgr10">
	        	<span>时间：</span> 
	            <input type="text" name="startTime" value="${startTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" class="Wdate">
	            <span>至</span> 
	            <input type="text"  name="endTime" value="${endTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate">
        	</p>
	    	<div class="mgtb10">
	    		<div class="clearfix mgtb10">
					<input type="button" class="btn_blue" value="查询" onclick="search()"/>
					<input type="button" class="btn_blue" value="导出excel" onclick="exportExcel()"/>
				</div>
	    	</div>
        </div>
        
        <div class="mgtb10">
			<div class="base_title"><strong>运营人员互动课堂安装统计</strong></div>
	
			<div class="clearfix mgtb10">
				
			</div>
	       		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
	        	<tr>
					<th width="10%">区域</th>
					<th width="10%">学校</th>
					<th width="10%">班级</th>
					<th width="10%">cpu</th>
					<th width="10%">内存</th>
					<th width="8%">硬盘</th>
					<th width="8%">MAC</th>
					<th width="6%">版本</th>
					<th width="6%">人员</th>
					<th width="6%">时间</th>
			    </tr>
			    
			     <c:if test="${fn:length(installLogList) < 1}">
			    	<tr>
			    		<td colspan="10" style="color: red">暂无数据</td>
			    	</tr>
			    </c:if>
			    
			    <c:forEach items="${installLogList}" var="installLog" varStatus="status">
			    	<tr>
			    		<td>${installLog.area}</td>
			    		<td>${installLog.school}</td>
			    		<td>${installLog.className}</td>
			    		<td>${installLog.cpu}</td>
			    		<td>${installLog.memery}</td>
			    		<td>${installLog.disk}</td>
			    		<td>${installLog.mac}</td>
			    		<td>${installLog.version}</td>
			    		<td>${installLog.userName}</td>
			    		<td><fmt:formatDate value="${installLog.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
			    	</tr>
			    </c:forEach>
	        </table>
       </div>
        <div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${fn:length(installLogList) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/installLog/list"></newTag:page>
		</c:if>
		</div>
	</form>
</div>

<script>
function exportExcel(){
	var area=$('#area').val();
	var school=$('#school').val();
	var userName=$('#userName').val();
	var startTime = $('#d4311').val();
	var endTime = $('#d4312').val();
	window.location.href='${ctx}/manage/installLog/export?area=' + area+'&school='+school+ '&userName='+userName+'&startTime='+startTime+'&endTime='+endTime;
}
function search(){
	$("#pageForm").submit();
}

</script>
</body>
</html>