<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<jsp:include page="/pages/base/base.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;">
				<ul class="nav nav-tabs">
					<li><a href="<%=contextPath%>/alarm/toList">告警列表</a></li>
					<li role="presentation" class="active"><a href="<%=contextPath%>/callRecord/list">呼叫列表</a></li>
				</ul>
				<ol class="breadcrumb" style="margin-top: 15px;">
					<form id="callForm" action="list" class="form-inline">
						<div class="form-group">
							<label for="">呼叫人姓名</label>&nbsp; 
							<input type="text" class="form-control" style="width: 160px;" id="callName" name="callName" value="${callName }" placeholder="请输入呼叫人姓名">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">呼叫电话号</label>&nbsp; 
							<input type="text" class="form-control" style="width: 160px;" id="phoneNo" name="phoneNo" value="${phoneNo }" placeholder="请输入呼叫电话号">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">呼叫类型</label>&nbsp;
							<select id="callType" name="callType" class="form-control">
								<option value="">全部</option>
								<option value="1">呼入</option>
								<option value="2">呼出</option>
							</select>
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="alarmTime" class="control-label">呼叫时间</label>&nbsp;
							<div class="input-group date form_date" style="width: 196px;" data-date-format="yyyy-mm-dd" data-link-field="alarmTime" data-link-format="yyyy-mm-dd">
								<input class="form-control" id="createTime" name="createTime" size="9" type="text" value="" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="hidden" id="create_time" name="create_time" value="${createTime }" /><br />
						</div>
						
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-info" id="queryBtn">查询</button>
					</form>
				</ol>
			</div>

			<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;margin-top: -10px;">
				<table id="dataTable" class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>呼叫人姓名</th>
							<th>呼叫人类型</th>
							<th>呼叫电话号</th>
							<th>呼叫类型</th>
							<th>呼叫时间</th>
							<th>是否接听</th>
							<th>接听时间</th>
							<th>通话时长</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="call" varStatus="status">
							<tr>
								<td>${status.index + 1 }</td>
								<td>${call.callName }</td>
								<td>${call.typeView }</td>
								<td>${call.phoneNo }</td>
								<td>${call.callInOutView }</td>
								<td>${call.createTime }</td>
								<td>${call.isAnswerView }</td>
								<td>${call.callTime }</td>
								<td>${call.callDuration }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script type="text/javascript">
			
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			format: 'yyyy-mm-dd',
			startView: 2,
			minView: 0,
			maxView: 4,
			minView: 'month',
			forceParse: 0
	    });
		
		$("#callType").val('${callType}');
		$("#createTime").val('${createTime}');
		
		$("#queryBtn").click(function(){
			var url = "<%=contextPath%>/callRecord/list";
		  	url += "?callName=" + $('#callName').val();
		  	url += "&phoneNo=" + $('#phoneNo').val();
		  	url += "&callType=" + $('#callType').val();
		  	url += "&createTime=" + $('#createTime').val();
		  	url += "&isRead=" + $('#isRead').val();
		  	window.location.href = url;
		});
		
		$(document).ready(function() {
			$('#dataTable').dataTable({
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
				"aaSorting" : [ [5, "desc" ] ], //默认的排序方式，第2列，升序排列  
				columnDefs:[{
					 orderable:false,//禁用排序
					 targets:[0,7]   //指定的列
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
