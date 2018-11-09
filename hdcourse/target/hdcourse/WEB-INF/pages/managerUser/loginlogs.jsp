<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@taglib uri="/WEB-INF/tld/pageTag.tld" prefix="newTag"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录日志列表页</title>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/loginlogs/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
    <div class="mgtb10 txq_txtbox clearfix">
       <%--  <p class="fl mgr10"><span>用户类型：</span><select id="log_type" name="log_type" value="${log_type}" class="sel" style="width:150px;"><option value="">请选择类型</option></select></p> --%>
        <p class="fl mgr10"><span>学校：</span>
       <%--  <select id="school" name="school" value="${school}" class="sel" style="width:150px;"><option value="">请选择学校</option></select></p> --%>
        <input class="sel" type="text" id="school" name="school"  value="${org_name}">
          <p class="fl mgr10"><span>用户名：</span><input class="sel" type="text" id="user_name" name="user_name" value="${user_name}"></p>
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询" /></p>
    </div>
    
    <div class="mgtb10">
		<div class="base_title"><strong>登录日志列表</strong></div>
		<table class="small_space data_list txq_ckall" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%">序号</th>
				<th width="8%">用户名</th>
				<th width="8%">姓名</th>
				<th width="8%">类型</th>
				<th width="18%">学校</th>
				<th width="12%">IP地址</th>
				<th width="15%">登录时间</th>
				<th width="15%">登录次数</th>
		    </tr>
		      <c:if test="${fn:length(loginlogslist)<1}">
		    	<tr>
		    		<td colspan="8" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:forEach items="${loginlogslist}" var="log" varStatus="status">
			    <tr>
					<td>${status.index+1}</td>
					<td>${log.user_name}</td>
					<td>${log.real_name}</td>
					<td>
					<c:if test="${log.user_type eq 0}">学生</c:if>
					<c:if test="${log.user_type eq 1}">老师</c:if>
					<c:if test="${log.user_type eq 2}">学校管理员</c:if>
					<c:if test="${log.user_type eq 3}">机构管理员</c:if>
					<c:if test="${log.user_type eq 4}">超级管理员</c:if>
					<c:if test="${log.user_type eq 5}">家长</c:if>
					</td>
					<td>${log.org_name}</td>
					<td>${log.login_ip}</td>
					<td><fmt:formatDate value="${log.create_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${log.login_count}</td>
			    </tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		 <!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/loginlogs/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>
<script type="text/javascript">
function search(){
	$("#search_type").val(0);
	$("#pageForm").submit();
}
</script>
</body>
</html>