<#assign base=request.contextPath />
<form id="pageForm"  name="pageForm" action="${base}/page/school/pageTest.html" method="post">
	<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}">
    	<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
</form>

<table>
	<tr>
		<td>设备编号</td>
	</tr>
	
	
<#list list as bean>
	<tr>
		<td>${bean.deviceCode}</td>
	</tr>
</#list>
<table>
<#include "../_page.ftl">