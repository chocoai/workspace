<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String contextPath = request.getContextPath();
%>
<!doctype html>
<html lang="zx-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">


<style>
* {
	margin: 0;
	padding: 0;
}

a,a:hover,a:focus {
	text-decoration: none;
}

.left {
	float: left;
	width: 60%;
	height: 100%;
	padding: 15px 15px 60px 15px;
	position: absolute;
}

.right {
	float: right;
	width: 40%;
	padding: 15px 10px 15px 0;
}

.video {
	float: left;
	width: 100%;
	background-color: #eee;
	height: 100%;
}

.devices-btns {
	float: left;
	width: 100%;
	margin-top: 15px;
}

.sevice-search {
	border-bottom: 1px solid #ddd;
	padding: 15px;
}

.table-sevices tbody tr.selected {
	background-color: #f4f6fb;
}

.device-page {
	margin-top: 0;
	margin-bottom: 0;
}

.btn-search {
	background-color: #eee;
}
</style>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/bootstrap-theme.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/dataTables/css/dataTables.bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/zTree/css/zTreeStyle/zTreeStyle.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/font-awesome/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/layer/css/layer.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/health/healthManage.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/health/font-awesome.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">
<script src="<%=contextPath%>/res/js/jquery-3.2.1.min.js"></script>
<script src="<%=contextPath%>/res/js/bootstrap.min.js"></script>
<script src="<%=contextPath%>/res/plugins/dataTables/js/jquery.dataTables.min.js"></script>
<script src="<%=contextPath%>/res/plugins/dataTables/js/dataTables.bootstrap.min.js"></script>
<script src="<%=contextPath%>/res/plugins/zTree/js/jquery.ztree.core.min.js"></script>
<script src="<%=contextPath%>/res/plugins/zTree/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/js/videocontrol/GisParam.js"></script>
</head>
<body>
	<div class="right">
		<div class="panel panel-default">
			<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" style="width:320px;margin-right:15px;" role="document">
					<div class="modal-content"></div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<div class="panel-heading">视频设备列表</div>
			<div class="col-md-12" style="padding-right: 15px;padding-top: 10px;">
				<form class="form-inline" action="<%=contextPath%>/device/videoList">
					<div class="form-group" style="display:inline-block;">
						<div class="input-group" style="display:inline-block;">
							<input type="text" style="display:inline-block; width:130px;" class="form-control" id="orgName" value="${org.orgName }" placeholder="请选择所属机构" style="dispaly:inline-block"
								readonly="readonly"> 
								<input type="hidden" name="orgId" id="orgId" value="${org.orgId }"> 
								<span class="input-group-btn" style="display:inline-block; width:auto;">
								<button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal" href="<%=contextPath%>/org/showOrgTree?orgId=${org.orgId }" data-target="#organizationTree">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</div>
					&nbsp;&nbsp;
					<div class="form-group" style="display:inline-block;">
						<input type="text" style="display:inline-block; width:130px;" class="form-control" id="deviceName" name="deviceName" value="${deviceName }" placeholder="输入设备名称">
					</div>
					&nbsp;&nbsp;
					<button type="submit" class="btn btn-primary">查询</button>
				</form>
			</div>
			<table id="dataTable" class="table table-sevices  table-hover table-bordered">
				<thead>
					<tr>
						<th>设备名称</th>
						<th>安装地址</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="item" varStatus="status">
						<tr id="tr_${item.deviceId }">
							<td>${item.deviceName }</td>
							<td>${item.address }</td>
							<td><a class="play" id="dev_${item.deviceId }" onclick="addVideo('${item.deviceId }',this);">播放</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$('#dataTable').dataTable({
				"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
				"searching" : false, // 关闭Datatables的搜索功能
				"aLengthMenu" : [ 10, 20, 40 ], //更改显示记录数选项
				"lengthChange" : false,//禁用调整显示记录数选项
				"iDisplayLength" : 10, //默认显示的记录数
				"bAutoWidth" : true, //是否自适应宽度
				"bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
				"bPaginate" : true, //是否显示（应用）分页器  
				"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
				"sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
				"bSort" : true, //是否启动各个字段的排序功能  
				"aaSorting" : [ [ 2, "desc" ] ], //默认的排序方式，第2列，升序排列  
				columnDefs : [ {
					orderable : false,//禁用排序
					targets : [ 0 ]
				//指定的列
				} ],
				"bFilter" : true, //是否启动过滤、搜索功能  
				"oLanguage" : { //国际化配置  
					"sProcessing" : "正在获取数据，请稍后...",
					"sLengthMenu" : "显示 _MENU_ 条",
					"sZeroRecords" : "没有您要搜索的内容",
					"sInfo" : "从 _START_ 到  _END_ 条记录 总记录 _TOTAL_ 条",
					"sInfoEmpty" : "记录数为0",
					"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
					"sInfoPostFix" : "",
					"sSearch" : "搜索&nbsp;&nbsp;",
					"sUrl" : "",
					"oPaginate" : {
						"sFirst" : "",
						"sPrevious" : "上一页",
						"sNext" : "下一页",
						"sLast" : ""
					}
				}
			});
		});

		function addVideo(deviceId, o) {
			var tr = $("#tr_" + deviceId);
			if (o.innerHTML == "播放") {
				//获取接口需设置参数
				var ret = parent.startVideo(deviceId);
				if (ret != 0) {
					layer.msg("播放失败");
				} else {
					o.innerHTML = "停止";
					if (tr.hasClass("selected")) {
						tr.removeClass('selected');
					} else {
						tr.addClass('selected');
					}
				}
			} else {
				var ret = parent.stopVideoFromList(deviceId);
				if (ret != 0) {
					layer.msg("停止失败");
				} else {
					o.innerHTML = "播放";
					if (tr.hasClass("selected")) {
						tr.removeClass('selected');
					} else {
						tr.addClass('selected');
					}
				}
			}
		}
	</script>
</body>
</html>