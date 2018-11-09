<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计管理页面</title>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/behavior/jdjsdList" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10"><span>用户名：</span><input name="user_name" value="${user_name }" type="text" style="width:120px;" class="inp focus" placeholder="请输入用户名"/></p>
        <p class="fl mgr10"><span>学段：</span><input name="period_name" value="${period_name }" type="text" style="width:120px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>学校：</span>
        	<input name="org_name" value="${org_name }" type="text" style="width:150px;" class="inp focus"/>
        </p>
        <p class="fl mgr10"><span>用户类型：</span>
        	<select name="user_type" class="sel" style="width:150px;">
        		<option value="" <c:if test="${empty user_type}">selected="selected"</c:if>>全部</option>
	        	<option value="0" <c:if test="${user_type=='0'}">selected="selected"</c:if>>学生</option>
	        	<option value="1" <c:if test="${user_type=='1'}">selected="selected"</c:if>>老师</option>
	        	<option value="2" <c:if test="${user_type=='2'}">selected="selected"</c:if>>学校管理员</option>
	        	<option value="3" <c:if test="${user_type=='3'}">selected="selected"</c:if>>机构管理员</option>
	        	<option value="4" <c:if test="${user_type=='4'}">selected="selected"</c:if>>超级管理员</option>
	        	<option value="5" <c:if test="${user_type=='5'}">selected="selected"</c:if>>家长</option>
        	</select>
        </p>
        <p class="fl mgr10">
        	<span>操作系统：</span>
        	<input name="system_info" value="${system_info }" type="text" style="width:150px;" class="inp focus"/>
        </p>
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>移动教师端统计列表</strong></div>

		<div class="clearfix mgtb10">
		</div>
		
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%">序号</th>
				<th width="10%">用户名</th>
				<th width="10%">姓名</th>
				<th width="10%">学段</th>
				<th width="10%">学校</th>
				<th width="10%">用户类型</th>
				<th width="10%">操作系统</th>
				<th width="13%">开始使用时间</th>
				<th width="13%">结束使用时间</th>
				<th width="10%">使用时长</th>
		    </tr>
		    <c:if test="${fn:length(list) < 1}">
		    	<tr>
		    		<td colspan="10" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td>${status.count }</td>
					<td>${obj.USER_NAME }</td>
					<td>${obj.REAL_NAME }</td>
					<td>${obj.PERIOD_NAME }</td>
					<td>${obj.ORG_NAME }</td>
					<td>
						<c:if test="${obj.USER_TYPE=='0'}">学生</c:if>
	        			<c:if test="${obj.USER_TYPE=='1'}">老师</c:if>
	        			<c:if test="${obj.USER_TYPE=='2'}">学校管理员</c:if>
	        			<c:if test="${obj.USER_TYPE=='3'}">机构管理员</c:if>
	        			<c:if test="${obj.USER_TYPE=='4'}">超级管理员</c:if>
	        			<c:if test="${obj.USER_TYPE=='5'}">家长</c:if>
					</td>
					<td>${obj.SYSTEM_INFO }</td>
					<td><fmt:formatDate value="${obj.CREATE_TIME }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${obj.UPDATE_TIME }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<script type="text/javascript">
							var timeCount = '<c:out value="${obj.TIME_COUNT}"/>';
							var hour = parseInt(timeCount/3600)+'';
							var minute = parseInt(timeCount%3600/60)+'';
							var seconds = parseInt(timeCount%60)+'';
							if(hour.length==1){
								hour = '0'+hour;
							}
							if(minute.length==1){
								minute = '0'+minute;						
							}
							if(seconds.length==1){
								seconds = '0'+seconds;
							}
							document.write(hour+':'+minute+':'+seconds);
						</script>
					</td>
			    </tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${fn:length(list) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/behavior/jdjsdList"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<script>
//tab
$("div[name='slide']").tabControl("*[name='slideTag'] a" , "*[name='slideCont']" , '.more');

function search(){
	$("#search_type").val(0);
	$("#pageForm").submit();
}
</script>
</body>
</html>