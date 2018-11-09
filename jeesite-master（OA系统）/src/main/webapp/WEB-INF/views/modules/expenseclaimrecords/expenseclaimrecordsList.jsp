<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/expenseclaimrecords/expenseclaimrecords/">报销列表</a></li>
		<shiro:hasPermission name="expenseclaimrecords:expenseclaimrecords:edit"><li><a href="${ctx}/expenseclaimrecords/expenseclaimrecords/form">报销添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="expenseclaimrecords" action="${ctx}/expenseclaimrecords/expenseclaimrecords/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>工程名：</label>
				<form:input path="projectName" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>区域名：</label>
				<sys:treeselect id="area" name="area.id" value="${expenseclaimrecords.area.id}" labelName="area.name" labelValue="${expenseclaimrecords.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>多选：</label>
				<form:select path="treeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('color')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>时间：</label>
				<input name="date" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${expenseclaimrecords.date}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工程名</th>
				<th>区域名</th>
				<th>多选</th>
				<th>时间</th>
				<th>金额</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="expenseclaimrecords:expenseclaimrecords:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="expenseclaimrecords">
			<tr>
				<td><a href="${ctx}/expenseclaimrecords/expenseclaimrecords/form?id=${expenseclaimrecords.id}">
					${expenseclaimrecords.projectName}
				</a></td>
				<td>
					${expenseclaimrecords.area.name}
				</td>
				<td>
					${fns:getDictLabel(expenseclaimrecords.treeId, 'color', '')}
				</td>
				<td>
					<fmt:formatDate value="${expenseclaimrecords.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${expenseclaimrecords.total}
				</td>
				<td>
					<fmt:formatDate value="${expenseclaimrecords.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${expenseclaimrecords.remarks}
				</td>
				<shiro:hasPermission name="expenseclaimrecords:expenseclaimrecords:edit"><td>
    				<a href="${ctx}/expenseclaimrecords/expenseclaimrecords/form?id=${expenseclaimrecords.id}">修改</a>
					<a href="${ctx}/expenseclaimrecords/expenseclaimrecords/delete?id=${expenseclaimrecords.id}" onclick="return confirmx('确认要删除该报销吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>