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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Mer</title>
</head>

<jsp:include page="/pages/base/base.jsp"></jsp:include>

<body>
	<div class="container-fluid">
		<div class="row" style="padding-left: 180px; position: relative;">
			<div style="width:150px; position: absolute; left: 0; top: 0;">
				<ul class="nav nav-pills nav-stacked text-center">
					<li><a href="<%=contextPath%>/healthService/record?memberId=${memberId }" ><span>健康档案</span></a></li>
					<li><a href="<%=contextPath%>/healthService/data?memberId=${memberId }" ><span>健康数据</span></a></li>
					<li class="active"><a href="<%=contextPath%>/healthService/mer?memberId=${memberId }" ><span>体检报告</span></a></li>
					<li><a href="<%=contextPath%>/healthService/proposal?memberId=${memberId }" ><span>医师建议</span></a></li>
				</ul>
			</div>
			<div class="col-sm-12">
				<!-- tab strat -->
				<ul class="nav nav-tabs">
					<li><a href="<%=contextPath%>/healthService/record?memberId=${memberId }">健康档案</a></li>
					<li><a href="<%=contextPath%>/healthService/data?memberId=${memberId }">健康数据</a></li>
					<li class="active"><a href="<%=contextPath%>/healthService/mer?memberId=${memberId }">体检报告</a></li>
					<li><a href="<%=contextPath%>/healthService/proposal?memberId=${memberId }">医师建议</a></li>
				</ul>
				<!-- tab end -->
				
				<div class="col-md-12" style="padding-right: 0px;">
					<ol class="breadcrumb" style="margin-top: 15px;">
						<form id="merForm" action="mer"  class="form-inline">
							<input type="hidden" id="memberId" name="memberId" value="${memberId }">
							<div class="form-group">
								<label for="merId">体检报告编号</label>&nbsp; 
								<input type="text" class="form-control" style="width: 180px;" id="merId" name="merId" value="${merId }" placeholder="请输入体检报告编号">
							</div>
							<div class="form-group">
								&nbsp;&nbsp;
								<label for="merTime" class="control-label">体检时间</label>&nbsp;&nbsp;&nbsp; 
								<div class="input-group date form_date" style="width: 196px;" data-date-format="yyyy-mm-dd" data-link-field="merTime" data-link-format="yyyy-mm-dd">
									<input class="form-control" id="input_time" name="input_time" size="9" type="text" value="" readonly>
									<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> 
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="merTime" name="merTime" value="${merTime }" /><br />
							</div>
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="submit" class="btn btn-default"> 查询  </button>	&nbsp;&nbsp;&nbsp;&nbsp;						
							<button type="button" class="btn btn-info" id="addMer">录入体检报告数据</button>
						</form>
					</ol>
					
				</div>
				
				<div class="col-md-12" style="margin-top: -10px;">
					<table id="dataTable" class="table table-striped table-hover table-bordered mytable">
						<thead>
							<tr>
								<th>序号</th>
								<th>体检会员</th>
								<th>体检报告编号</th>
								<th>体检时间</th>
								<th>详情</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${merList }" var="mer" varStatus="vs">
							<tr>
								<td width="">${vs.count }</td>
								<td width="">${mer.member.realName }</td>
								<td width="">${mer.merId }</td>
								<td width="">${mer.merTime }</td>
								<td width="">
									<a href="<%=contextPath%>/healthService/merDetail?merId=${mer.merId }&memberId=${memberId }">查看</a>
									<a href="<%=contextPath%>/healthService/toAddMerBmi?merId=${mer.merId }&memberId=${memberId }">编辑</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>

				</div>
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
		//pickerPosition: "bottom-left",
		forceParse: 0
    });
	
	$("#input_time").val('${merTime }');
	
	$("#addMer").click(function(){
		window.location.href = "<%=contextPath%>/healthService/toAddMerBmi?memberId=${memberId }";
		  
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