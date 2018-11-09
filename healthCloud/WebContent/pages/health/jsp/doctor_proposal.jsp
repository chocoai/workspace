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
<title>doctor Proposal</title>
</head>

<jsp:include page="/pages/base/base.jsp"></jsp:include>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2">
				<ul class="nav nav-pills nav-stacked text-center">
					<li><a href="<%=contextPath%>/healthService/record?memberId=${memberId }" ><span>健康档案</span></a></li>
					<li><a href="<%=contextPath%>/healthService/data?memberId=${memberId }" ><span>健康数据</span></a></li>
					<li><a href="<%=contextPath%>/healthService/mer?memberId=${memberId }" ><span>体检报告</span></a></li>
					<li class="active"><a href="<%=contextPath%>/healthService/proposal?memberId=${memberId }" ><span>医师建议</span></a></li>
				</ul>
			</div>
			
			<div class="modal fade" id="viewProposal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content "></div>
				</div>
			</div>

			<div class="col-sm-10">
				<!-- tab strat -->
				<ul class="nav nav-tabs">
					<li><a href="<%=contextPath%>/healthService/record?memberId=${memberId }">健康档案</a></li>
					<li><a href="<%=contextPath%>/healthService/data?memberId=${memberId }">健康数据</a></li>
					<li><a href="<%=contextPath%>/healthService/mer?memberId=${memberId }">体检报告</a></li>
					<li class="active"><a href="<%=contextPath%>/healthService/proposal?memberId=${memberId }">医师建议</a></li>
				</ul>
				<!-- tab end -->

				<div class="col-md-12" style="margin-top: 5px;">
					<table id="dataTable" class="table table-striped table-hover table-bordered mytable">
						<thead>
							<tr>
								<th>序号</th>
								<th>医师</th>
								<th>会员</th>
								<th>时间</th>
								<th>详情</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${proposals }" var="proposal" varStatus="vs">
								<tr>
									<td>${vs.count }</td>
									<td>${proposal.doctor.realName }</td>
									<td>${proposal.member.realName }</td>
									<td>${proposal.proposalTime }</td>
									<td><button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#viewProposal" href="<%=contextPath%>/healthService/proposalDetail?cid=${proposal.cid}">查看</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	
	<script type="text/javascript">	
	
	$("#viewProposal").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
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