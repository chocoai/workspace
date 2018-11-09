<%@page import="com.yhcrt.healthcloud.health.entity.HdBloodGlucose"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Blood Glucose</title>
</head>

<jsp:include page="/pages/base/base.jsp"></jsp:include>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<!-- tab strat
				<ul class="nav nav-tabs">
					<li><a
						href="${pageContext.request.contextPath }/healthService/record?memberId=${memberId }">健康档案</a></li>
					<li class="active"><a
						href="${pageContext.request.contextPath }/healthService/data?memberId=${memberId }">健康数据</a></li>
					<li><a
						href="${pageContext.request.contextPath }/healthService/mer?memberId=${memberId }">体检报告</a></li>
					<li><a
						href="${pageContext.request.contextPath }/healthService/proposal?memberId=${memberId }">医师建议</a></li>
				</ul> -->
				<!-- tab end -->

				<div class="mar-t10">
					<!-- 医师建议表格 strat -->
					<table id="dataTable" class="table table-bordered table-striped mytable">
						<thead>
							<tr>
								<th>序号</th>
								<th>记录时间</th>
								<th>数据来源(imei)</th>
								<th>血糖值(mmol/L)</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items }" var="item" varStatus="vs">
								<tr>
									<td width="10%">${vs.count }</td>
									<td width="30%">${item.glucose.dataDate }</td>
									<td width="30%">${item.glucose.imei }</td>
									<td width="30%">${item.glucose.bgValue }/${item.type }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 医师建议表格 end -->
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#dataTable").dataTable({
				"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
				"searching": false, // 关闭Datatables的搜索功能
				"aLengthMenu" : [ 10, 20, 40 ], //更改显示记录数选项
				"lengthChange": false,//禁用调整显示记录数选项
				"iDisplayLength" : 10, //默认显示的记录数
				"bAutoWidth" : true, //是否自适应宽度
				"bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
				"bPaginate" : true, //是否显示（应用）分页器  
				"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
				"sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
				"bSort" : true, //是否启动各个字段的排序功能  
				"aaSorting" : [ [0, "asc" ] ], //默认的排序方式，第2列，升序排列  
				columnDefs:[{
					 orderable:false,//禁用排序
					 targets:[0]   //指定的列
				 }],
				"bFilter" : true, //是否启动过滤、搜索功能  
				"oLanguage" : { //国际化配置  
					"sProcessing" : "正在获取数据，请稍后...",
					"sLengthMenu" : "显示 _MENU_ 条",
					"sZeroRecords" : "没有您要搜索的内容",
					"sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
					"sInfoEmpty" : "记录数为0",
					"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
					"sInfoPostFix" : "",
					"sSearch" : "搜索&nbsp;&nbsp;",
					"sUrl" : "",
					"oPaginate" : {
						"sFirst" : "第一页",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : "最后一页"
					}
				}
	
			});
		});
	</script>

</body>
</html>