<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>区域统计</title>
</head>
<body>


<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/userCount/list" method="post">
	<div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10">
        	<span>日期：</span> 
            <input type="text" name="startTime"  value="${startTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" class="Wdate">
            <span>至</span> 
            <input type="text" name="endTime"  value="${endTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate">
        </p>
        <p class="fl">
        <input type="button" onclick="search()" class="btn_blue" value="查询"/>
        <input type="button" onclick="exportExcel()" class="btn_blue" value="导出"/>
        </p>
    </div>
	
	
		<div class="clearfix">
	        
        </div>
        
        <div class="mgtb10">
			<div class="base_title"><strong>家校帮-移动讲台统计</strong></div>
			<div class="clearfix mgtb10">
			
			</div>
	       		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
	        	<tr>
					<th width="10%">日期</th>
					<th width="10%">登录用户数</th>
					<th width="10%">新增登录用户数</th>
			    </tr>
			    
			     <c:if test="${fn:length(dataList) < 1}">
			    	<tr>
			    		<td colspan="10" style="color: red">暂无数据</td>
			    	</tr>
			    </c:if>
	
			    <c:forEach items="${dataList}" var="data" varStatus="status">
			    	<tr>
			    		<td>${data.useTime}</td>
			    		<td>${data.userCount}</td>
			    		<td>${data.newUserCount}</td>
			    	</tr>
			    </c:forEach>
	        </table>
       </div>
        <div class="base_page clearfix">
		<!-- 分页 -->
		
		</div>
	
	</form>

</div>

<script>

function exportExcel(){
	var startTime = $("input[name='startTime']").val();
	var endTime = $("input[name='endTime']").val();
	
	window.location.href='${ctx}/manage/userCount/exportExcel?startTime='+startTime+'&endTime='+endTime;
}

function search(){
	//$("#search_type").val(0);
	$("#pageForm").submit();
}


$(function(){
	
});
</script>
</body>
</html>