<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<li><a href="<%=contextPath%>/device/list" target="_self"><span>终端列表</span></a></li>
					<li><a href="<%=contextPath%>/devicesetting" target="_self"><span>终端设置</span></a></li>
					<li class="active"><a href="<%=contextPath%>/deviceVideo/list" target="_self"><span>视频设备管理</span></a></li>
					<li><a href="<%=contextPath%>/device/videoControl" target="_self"><span>视频监控</span></a></li>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" style="width:450px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>

			<div class="col-md-12" style="padding-right: 0px;padding-top: 10px;">
				<a class="btn btn-info btn-sm" href="toAdd">+ 新增设备</a>&nbsp;&nbsp;
				<button class="btn btn-info btn-sm" id="batchDelete">- 删除设备</button>&nbsp;&nbsp;
				
				<ol class="breadcrumb" style="margin-top: 10px;">
					<form id="videoForm" action="list"  class="form-inline">
						<div class="form-group">
							<label for="">所属机构</label>&nbsp; 
							<div class="input-group">
								<input type="text" class="form-control" id="orgName" value="${org.orgName }" placeholder="请选择所属机构" style="width: 150px;" readonly="readonly">
								<input type="hidden" name="orgId" id="orgId" value="${org.orgId }">
								<span class="input-group-btn">
				  			        <button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal" href="<%=contextPath%>/org/showOrgTree?orgId=${org.orgId }" data-target="#organizationTree"><i class="fa fa-search"></i></button>
				  			    </span>
			  			    </div>
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">设备名称</label>&nbsp; 
							<input type="text" class="form-control" style="width: 150px;" id="deviceName" name="deviceName" value="${deviceName }" placeholder="请输入设备名称">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">设备型号</label>&nbsp; 
							<input type="text" class="form-control" style="width: 150px;" id="deviceModel" name="deviceModel" value="${deviceModel }" placeholder="请输入设备型号">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">设备状态</label>&nbsp;
							<select id="status" name="status" class="form-control">
							  	<option value="">全部</option>
							  	<option value="1">启用</option>
							  	<option value="2">禁用</option>
							  	<option value="3">损坏</option>
							</select>
						</div>
						&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary"> 查询  </button>							
					</form>
				</ol>
			</div>

			<div class="col-md-12" style="padding-right: 0px;margin-top: -20px;">
				<table id="dataTable" style="table-layout: fixed;text-align: center;" class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th width="6%"><input type="checkbox" id="checkAll"> 选择</th>
							<th>设备id</th>
							<th width="10%">设备名称</th>
							<th width="12%">所属机构</th>
							<th width="10%">设备型号</th>
							<th width="12%">设备厂商</th>
							<th>安装地址</th>
							<th width="8%">状态</th>
							<th width="14%">录入时间</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="item" varStatus="status">
							<tr>
								<td style="text-align: center;">
									<input type="checkbox" name="check_device" value="${item.cid}">
								</td>
								<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" title="${item.deviceId }">${item.deviceId }</td>
								<td>${item.deviceName }</td>
								<td>${item.orgName }</td>
								<td>${item.deviceModel }</td>
								<td>${item.deviceVendor }</td>
								<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" title="${item.address }">${item.address }</td>
								<td>${item.statusView }</td>
								<td>${item.createTime }</td>
								<td>
									<a type="button" class="btn btn-primary btn-xs" href="toAdd?cid=${item.cid}">编辑</a>
									<button type="button" class="btn btn-warning btn-xs" onclick="del(${item.cid})">删除</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">

	$(function(){
		$("#status").val("${status}");
		if("${state}" == "SUCCESS"){
			layer.msg('保存成功', { anim: 6 });
		}else if("${state}" == "FALSE"){
			layer.msg('保存失败', { anim: 6 });
		}else if("${state}" == "SAME"){
			layer.msg('保存失败,数据重复', { anim: 6 });
		}
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
			"aaSorting" : [ [7, "asc" ] ], //默认的排序方式，第2列，升序排列  
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
	
	//全选、取消全选的事件 
	$('#checkAll').click(function() {
		if ($("#checkAll").prop("checked")) {
			$(':checkbox').prop("checked", true);
		} else {
			$(':checkbox').prop("checked", false);
		}
	});
	
	//子复选框的事件
	$(":checkbox").click(function() {
		if (!$("#check_device").checked) {
				$("#checkAll").prop("checked",false);
		}
		var ckbLength = $("input[type='checkbox'][name='check_device']").length;
		var checkedLengtn = $("input[type='checkbox'][name='check_device']:checked").length;
		if (checkedLengtn == ckbLength) {
			$("#checkAll").prop("checked",true);
		}
	});
	
	//批量删除
	$("#batchDelete").click(function(){
		var cids = new Array();
		$("input[type='checkbox'][name='check_device']:checked").each(function(i) {
			cids[i] = $(this).val();
		});
		if(cids.length == 0){
			layer.msg('请先选取要删除的终端设备', {anim: 6});
			return;
		}
		delCom(cids);
	});
	
	//单个删除
	function del(cid){
		var cids = new Array();
		cids[0] = cid;
		delCom(cids);
	}
	
	//删除公共方法
	function delCom(cids){
		layer.confirm("确认要删除设备？", {btn: ["确认","取消"]}, function(){
			$.ajax({
				cache : true,
				type : "post",
				url : "batchDelete",
				async : false,
				data : {
					"cids" : cids
				},
				traditional : true,
				success : function(data) {
					if(data.state == "SUCCESS"){
						layer.msg('删除成功', { anim: 6 });
					}else{
						layer.msg('删除失败', { anim: 6 });
					}
					$("#videoForm").submit();
				},
				error : function() {
					layer.msg('删除失败', { anim: 6 });
				}
			});
		}, function(){
			return;
		});
	}
	
</script>
</body>
</html>
