<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12">
				<!-- tab strat -->
				<ul class="nav nav-tabs">
					<li class="active"><a href="list">当天最新健康数据</a></li>
					<li><a href="amlist">健康预警</a></li>
				</ul>
				<!-- tab end -->

				<!-- 查询表单 strat -->
				<div class="lookfor mar-t10">
					<form id="subForm" class="form-inline" name="form" method="get">
						<div class="form-group">
							<label for="">所属机构</label>&nbsp;
							<div class="input-group">
								<input type="text" class="form-control" id="orgName" placeholder="请选择所属机构" readonly="readonly" value=${org.orgName }>
								<input type="hidden" name="orgId" id="orgId" value="${org.orgId }"> 
								<span class="input-group-btn">
									<button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal" href="${pageContext.request.contextPath }/org/showOrgTree?orgId=${org.orgId }" data-target="#organizationTree" style="height: 34px;">
										<i class="fa fa-search"></i>
									</button>
								</span>
							</div>
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<div class="form-group">
							<label for="name">姓名：</label> 
							<input type="text" class="form-control" name="realName" value="${realName }" placeholder="请输入姓名" />
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<div class="form-group">
							<label for="idCard">身份证号：</label> 
							<input type="text" class="form-control" id="idCard" name="idCard" value="${idCard }" placeholder="请输入身份证号码" />
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary" onclick="sub()">查询</button>
					</form>
				</div>
				<!-- 查询表单 end -->

				<div class="table-responsive mar-t10">
					<!-- table strat -->
					<table id="dataTable" class="table table-bordered table-striped" width="100%" style="text-align: center;">
						<thead>
							<tr>
								<td><strong>姓名</strong></td>
								<td><strong>心率(bpm)</strong></td>
								<td><strong>步数</strong></td>
								<td><strong>深睡(h)</strong></td>
								<td><strong>血压(sbp/dbp)</strong></td>
								<td><strong>血糖</strong></td>
								<td><strong>身份证号</strong></td>
								<td><strong>详情</strong></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${items }" var="item" varStatus="vs">
								<tr>
									<td width="10%">${item.member.realName }</td>
									<td width="10%">${item.pulse.pulse }</td>
									<td width="8%">${item.step.stepCount }</td>
									<td width="10%">${item.sleep.deepSleepDuration }</td>
									<td width="12%">${item.pressure.dbp }/${item.pressure.sbp }</td>
									<td width="8%"><a href="${pageContext.request.contextPath }/healthService/bloodGlucose?memberId=${item.member.memberId }">查看</a></td>
									<td width="15%">${item.member.identityCard }</td>
									<td width="25%">
										<a href="${pageContext.request.contextPath }/healthService/record?memberId=${item.member.memberId }">健康档案</a>
										<a href="${pageContext.request.contextPath }/healthService/data?memberId=${item.member.memberId }">健康数据</a>
										<a href="${pageContext.request.contextPath }/healthService/mer?memberId=${item.member.memberId }">体检报告</a>
										<a href="${pageContext.request.contextPath }/healthService/proposal?memberId=${item.member.memberId }">医师建议</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
		<div class="modal-dialog" style="width: 450px;" role="document">
			<div class="modal-content"></div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
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
				"aaSorting" : [ [0, "desc" ] ], //默认的排序方式，第2列，升序排列  
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