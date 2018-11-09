<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<% String contextPath = request.getContextPath(); %>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:member:list">
						<li><a href="<%=contextPath%>/member/list?status=0" target="_self"><span>会员列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:member:disable:list">
						<li><a href="<%=contextPath%>/member/list?status=1" target="_self"><span>禁用会员列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:member:unbind:list">
						<li class="active"><a href="<%=contextPath%>/member/unbandList" target="_self"><span>未绑定会员列表</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
		
			<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" style="width: 450px;" role="document">
					<div class="modal-content"></div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<form id="subForm" class="form-inline">
				<ol id="subDiv" class="breadcrumb">
					<div class="form-group">
						<label>姓名：</label>&nbsp; 
						<input type="text" class="form-control" style="width: 130px;" id="realName" name="realName" value="${realName }" placeholder="请输入姓名">
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label>身份证号：</label>&nbsp; 
						<input type="text" class="form-control" style="width: 180px;" id="identityCard" name="identityCard" value="${identityCard }" placeholder="请输入身份证号" >
					</div>
					&nbsp;&nbsp;
					<div class="form-group">
						<label>手机号码：</label>&nbsp; 
						<input type="text" class="form-control" style="width: 150px;" id="phoneNo" name="phoneNo" value="${phoneNo }" placeholder="请输入电话号码" >
					</div>
					&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary" >查询</button>
				</ol>
				<div class="table-responsive mar-t10" style="padding-right: 0px;margin-top: -10px;">
					<table id="table" class="table table-bordered table-striped" style="text-align: center;">
						<thead>
							<tr>
								<th>序号</th>
								<th>姓名</th>
								<th>性别</th>
								<th>身份证号</th>
								<th>手机号码</th>
								<th>会员信息</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="member" items="${member_list}" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${member.realName }</td>
									<td>
										<c:choose>
											<c:when test="${member.gender ==1 }">男</c:when>
											<c:otherwise>女</c:otherwise>
										</c:choose>
									</td>
									<td>${member.identityCard }</td>
									<td>${member.phoneNo }</td>
									<td><a href="memberInformation?memberId=${member.memberId }">会员详情</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#table").dataTable({
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
</html>