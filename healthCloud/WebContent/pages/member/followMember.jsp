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
<body onload="show('table')">
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:member:list">
						<li class="active"><a href="<%=contextPath%>/member/list?status=0" target="_self"><span>会员列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:member:disable:list">
						<li><a href="<%=contextPath%>/member/list?status=1" target="_self"><span>禁用会员列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:member:unbind:list">
						<li><a href="<%=contextPath%>/member/unbandList" target="_self"><span>未绑定会员列表</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<ul class="nav nav-tabs">
				<li>
					<a href="deviceList?memberId=${member.memberId }">【${member.realName }】的终端设备列表</a>
				</li>
				<li>
					<a href="org_relation?memberId=${member.memberId }">为【${member.realName }】服务的人员</a>
				</li>
				<li class="active">
					<a href="followMember?memberId=${member.memberId }">关注【${member.realName }】的会员</a>
				</li>
				<li>
					<a href="memberFollow?memberId=${member.memberId }">【${member.realName }】关注的会员</a>
				</li>
			</ul>
			<form id="subForm" class="form-search form-inline">
				<input type="hidden" name="memberId" value="${member.memberId }">
				<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;margin-top: -10px;">
					<table id="dataTable" class="table table-striped table-hover table-bordered" style="text-align: center;">
						<thead>
							<tr>
								<th>序号</th>
								<th>所属机构</th>
								<th>姓名</th>
								<th>性别</th>
								<th>联系电话</th>
								<th>关联关系</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="follower" items="${followMember}" varStatus="status">
								<tr>
									<td>${status.count }</td>
									<td>${follower.follow.org.orgName }</td>
									<td>${follower.follow.realName }</td>
									<td>${follower.follow.gender==0?"女":"男" }</td>
									<td>${follower.follow.phoneNo }</td>
									<td>${follower.relationDesc }</td>
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
</html>