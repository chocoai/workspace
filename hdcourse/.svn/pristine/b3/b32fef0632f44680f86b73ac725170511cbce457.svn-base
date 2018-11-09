<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<%@taglib uri="/WEB-INF/tld/pageTag.tld" prefix="newTag"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo列表页</title>
<link href="${ctx}/css/wdstyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/jquery.js"></script> 
</head>
<body>
<h1>demo列表页</h1>
<table style="border: 1px solid;" width="800px">
	<tr><td>创建者</td><td>标题</td><td>创建时间</td><td>操作</td></tr>
	<c:forEach items="${demoList}" var="demo" varStatus="status">
	<tr>
		<td>${demo.createrName }</td>
		<td>${demo.title }</td>
		<td><fmt:formatDate value="${demo.createrTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>-</td>
	</tr>
	</c:forEach>
</table>
        <!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
		<form action="" name="pageForm" method="post">
			<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
			currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
			action="${ctx}/demo/demoListPage"></newTag:page>
		</form>
		</c:if>
</body>
</html>
