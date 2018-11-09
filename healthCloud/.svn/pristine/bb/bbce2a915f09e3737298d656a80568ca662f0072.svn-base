<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:device:list">
						<li class="active"><a href="<%=contextPath%>/device/list" target="_self"><span>终端列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:setting">
						<li><a href="<%=contextPath%>/devicesetting" target="_self"><span>终端设置</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:video:list">
						<li><a href="<%=contextPath%>/deviceVideo/list" target="_self"><span>视频设备管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:video:monitor">
						<li><a href="<%=contextPath%>/device/videoControl" target="_self"><span>视频监控</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<div class="modal fade bs-example-modal-sm" id="organizationTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" style="width:450px;" role="document">
					<div class="modal-content"></div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->

			<div class="col-md-12" style="padding-left: 0px;">
				<a class="btn btn-info btn-sm" href="toAdd">+ 新增设备</a>&nbsp;&nbsp;
				<button class="btn btn-info btn-sm" id="batchDelete">- 删除设备</button>&nbsp;&nbsp;
				<a class="btn btn-info btn-sm" href="toBatchImport">批量导入</a>&nbsp;&nbsp;
				<button class="btn btn-info btn-sm" id="batchExport" >批量导出</button>&nbsp;&nbsp;

				<ol class="breadcrumb" style="margin-top: 10px;">
					<form id="deviceForm" action="list"  class="form-inline">
						<div class="form-group">
							<label for="">所属机构</label>&nbsp; 
							<div class="input-group">
								<input type="text" class="form-control" id="orgName" value="${org.orgName }" placeholder="请选择所属机构" style="width: 120px;" readonly="readonly">
								<input type="hidden" name="orgId" id="orgId" value="${org.orgId }">
								<span class="input-group-btn">
				  			        <button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal" href="<%=contextPath%>/org/showOrgTree?orgId=${org.orgId }" data-target="#organizationTree"><i class="fa fa-search"></i></button>
				  			    </span>
			  			    </div>
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">IMEI号</label>&nbsp; 
							<input type="text" class="form-control" style="width: 120px;" id="imei" name="imei" value="${imei }" placeholder="请输入IMEI号">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">设备型号</label>&nbsp; 
							<input type="text" class="form-control" style="width: 120px;" id="deviceType" name="deviceType" value="${deviceType }" placeholder="请输入设备型号">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">是否绑定</label>&nbsp;
							<select id="isUse" name="isUse" class="form-control" style="width: 80px;">
							  <option value="">全部</option>
							  <option value="1">已绑定</option>
							  <option value="0">未绑定</option>
							</select>
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">是否启用</label>&nbsp;
							<select id="startStatus" name="startStatus" class="form-control" style="width: 80px;">
							  <option value="">全部</option>
							  <option value="1">已启用</option>
							  <option value="0">未启用</option>
							</select>
						</div>
						&nbsp;&nbsp;
						<button type="button" class="btn btn-primary" id="queryBtn">查询</button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" id="resetBtn">重置</button>							
					</form>
				</ol>
			</div>

			<div class="col-md-12" style="padding-left: 0px; margin-top: -20px;">
				<table id="dataTable" class="table table-striped table-hover table-bordered" style="text-align: center;" width="100%">
					<thead>
						<tr>
							<th style="text-align: center;"><input type="checkbox" id="checkAll"> 选择</th>
							<th style="text-align: center;">设备名称</th>
							<th style="text-align: center;">所属机构</th>
							<th style="text-align: center;width:8%;">IMEI号</th>
							<th style="text-align: center;width:7%;">SIM卡号</th>
							<th style="text-align: center;">设备型号</th>
							<th style="text-align: center;width:7%;">是否绑定</th>
							<th style="text-align: center;width:7%;">是否启用</th>
							<th style="text-align: center;width:14%;">启用时间</th>
							<th style="text-align: center;width:13%;">操作</th>
						</tr>
					</thead>
				</table>
				<ol class="breadcrumb" style="margin-top: 10px;">
					<div class="form-inline">
						<div class="form-group">
							<label for="">选择机构</label>&nbsp; 
							<div class="input-group">
								<input type="text" class="form-control" id="organizationName" value="${org.orgName }" placeholder="请选择所属机构" readonly="readonly">
								<input type="hidden" name="organizationId" id="organizationId" value="${org.orgId }">
								<span class="input-group-btn">
				  			        <button class="btn btn-default" id="orgBtn" type="button" data-toggle="modal" href="/healthCloud/org/showOrgTree?orgId=${org.orgId }" data-target="#organizationTree"><i class="fa fa-search"></i></button>
				  			    </span>
			  			    </div>
						</div>
						&nbsp;&nbsp;
						<button type="submit" id="assignDevice" class="btn btn-info"> 分配已选终端设备到此机构 </button>							
					</div>
				</ol>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	$(function(){
		$("#startStatus").val("${startStatus}");
		$("#isUse").val("${isUse}");
		if("${state}"=="SUCCESS"){
	        layer.msg("保存成功");
	    }else if("${state}"=="FALSE"){
            layer.msg("保存失败");
		}
	});
	
	function toDelete(deviceId) {
		//询问框
		layer.confirm('您确认删除此终端设备吗？', {
		  btn: ['确认','取消'] //按钮
		}, function(){
			window.location.href = "delete?deviceId=" + deviceId;
		}, function(){
		  return;
		});
	}


	$("#updateDict").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});

	$("#addDict").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});
	
	
	var allColumns = [{
		/* "title" : "选择", */
		"data" : "",
		"render" : function(data, type, row, meta) {
			return "<input type='checkbox' id='check_device' name='check_device' value="+row.deviceId+">";
		}
	},{
		/* "title" : "设备名称", */
		"data" : "deviceName",
		"render" : function(data, type, row, meta) {
			if(row && row.deviceName){
				return row.deviceName;
			}
			return "";
		}
	},{
		/* "title" : "所属机构", */
		"data" : "orgName",
		"render" : function(data, type, row, meta) {
			if(row && row.org &&row.org.orgName){
				return row.org.orgName;
			}
			return "";
		}
	},{
		/* "title" : "IMEI号", */
		"data" : "imei",
		"render" : function(data, type, row, meta) {
			if(row && row.imei){
				return row.imei;
			}
			return "";
		}
	},{
		/* "title" : "SIM卡号", */
		"data" : "simView",
		"render" : function(data, type, row, meta) {
			if(row && row.simView){
				return row.simView;
			}
			return "";
		}
	},{
		/* "title" : "设备型号", */
		"data" : "deviceType",
		"render" : function(data, type, row, meta) {
			if(row && row.deviceType){
				return row.deviceType;
			}
			return "";
		}
	},{
		/* "title" : "是否绑定", */
		"data" : "isUseView",
		"render" : function(data, type, row, meta) {
			if(row && row.isUseView){
				return row.isUseView;
			}
			return "";
		}
	},{
		/* "title" : "是否启用", */
		"data" : "startStatusView",
		"render" : function(data, type, row, meta) {
			if(row && row.startStatusView){
				return row.startStatusView;
			}
			return "";
		}
	},{
		/* "title" : "启用时间", */
		"data" : "firstStartTime",
		"render" : function(data, type, row, meta) {
			if(row && row.firstStartTime){
				return row.firstStartTime;
			}
			return "";
		}
	},{
		/* "title" : "操作", */
		"data" : "",
		"render" : function(data, type, row, meta) {
			var htmlStr1 = "<a type='button' class='btn btn-primary btn-xs' href='toAdd?deviceId="+row.deviceId+"'>编辑</a>";
			var htmlStr2 = "<button type='button' class='btn btn-warning btn-xs' onclick='toDelete("+row.deviceId+")'>删除</button>";
			var htmlStr3 = "<a type='button' class='btn btn-info btn-xs' href='<%=contextPath%>/devicesetting?imei="+row.imei+"'>设置</a>";
			return htmlStr1 + "&nbsp;" + htmlStr2 + "&nbsp;" + htmlStr3;
		}
	  	
	}];
	  		
	$(document).ready(function() {
		$("#dataTable").dataTable({
			// DataTables - Features
			"autoWidth" : true, // 控制Datatables是否自适应宽度,默认值true
			"deferRender" : false, // 控制表格的延迟渲染，可以提高初始化的速度。默认值false
			"info" : true, // 控制是否显示表格左下角的信息,默认值true
			"lengthChange" : false, // 是否允许最终用户改变表格每页显示的记录数，默认值true
			"ordering" : false, // 是否允许Datatables开启排序,默认值true
			"paging" : true, // 是否允许表格分页，默认true
			"processing" : true, // 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)默认值false
			"scrollX" : false, // 是否允许水平滚动，默认值false
			"scrollCollapse" : false, //当显示更少的记录时，是否允许表格减少高度, 默认值false
			"searching" : false, // 此选项用来开启、关闭Datatables的搜索功能,默认值true
			"serverSide" : true, // 是否开启服务器模式,默认值false 
			"stateSave" : false, // 保存状态 - 在页面重新加载的时候恢复状态（页码等内容）,默认值false
			// DataTables - Data
			"ajax" : {
				"url" : "pageList",
				"type" : "POST",
				"data" : {
					"orgId" : $("#orgId").val(),
					"imei" : $("#imei").val(),
					"startStatus" : $("#startStatus").val(),
					"isUse" : $("#isUse").val(),
					"deviceType" : $("#deviceType").val()
				},
				"dataSrc" : "data"
			},
			// DataTables - Callbacks
			// DataTables - Options
			"destroy" : false, //销毁已经存在的Datatables实例并替换新的选项默认值false
			"displayStart" : 0, //初始化显示的时候从第几条数据开始显示(一开始显示第几页)
			"lengthMenu" : [ 10, 20, 40 ], // 定义在每页显示记录数的select中显示的选项
			"orderClasses" : true, //高亮显示表格中排序的列,默认值： true
			"orderMulti" : true, // 多列排序控制,默认值： true,用户按住shift点击表头，多列排序
			"order" : [ [ 1, "asc" ] ], //表格在初始化的时候的排序，第2列，升序排列  
			"pageLength" : 10, // 改变初始化页长度（每页多少条数据）,默认值:10
			"pagingType" : "full_numbers", // 分页按钮显示选项,full_numbers 首页，尾页，上一页和下一页四个按钮,加上数字按钮
			"renderer" : "bootstrap", // 显示组件渲染器类型
			"search" : {
				"caseInsensitive" : false, //在搜索或者过滤时，是否大小写敏感,默认值true
				"regex" : false, // 允许或者禁止对在搜索字符串中出现的正则表达式字符强制编码, 默认值false
				"smart" : true, // 允许或者禁止DataTables的只能过滤（搜索）功能, 默认值true
			},
			"columnDefs" : [ {
				"orderable" : false,//禁用排序
				"targets" : [ 0, 9 ]//指定的列
			} ],
			"columns" : allColumns,
			"fnDrawCallback": function (oSettings) {
				$("#checkAll").prop("checked",false);
	        },
			"language" : { //国际化配置  
				"processing" : "正在获取数据，请稍后...",
				"lengthMenu" : "显示 _MENU_ 条",
				"zeroRecords" : "没有您要搜索的内容",
				"info" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
				"infoEmpty" : "记录数为0",
				"infoFiltered" : "(全部记录数 _MAX_ 条)",
				"loadingRecords" : "数据正在加载中，请稍后...",
				"emptyTable" : "没有搜索到相关内容",
				"search" : "搜索&nbsp;&nbsp;",
				"url" : "",
				"paginate" : {
					"first" : "第一页",
					"previous" : "上一页",
					"next" : "下一页",
					"last" : "最后一页"
				},
				"aria" : {
					"sortAscending" : ": 以升序排列此列",
					"sortDescending" : ": 以降序排列此列"
				}
			}
		});
	});
	
	//全选、取消全选的事件 
	$("#checkAll").click(function() {
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
	
	$("#batchDelete").click(function(){
		var deviceIds = new Array();
		$("input[type='checkbox'][name='check_device']:checked").each(function(i) {
			deviceIds[i] = $(this).val();
		});
		if(deviceIds.length == 0){
			layer.msg("请先选取要删除的终端设备", { anim: 6 });
			return;
		}
		
		layer.confirm('您确认要删除这些终端设备吗？', {  btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					cache : true,
					type : "post",
					url : 'batchDelete',
					async : false,
					data : {
						"deviceIds" : deviceIds
					},
					traditional : true,
					success : function(data) {
						layer.msg('删除成功', { anim: 6 });
						$("#deviceForm").submit();
					},
					error : function() {
						layer.msg('删除失败', { anim: 6 });
					}
				});
			}, function(){
				return;
			});
	});
	
	
	$("#assignDevice").click(function(){
		var deviceIds = new Array();
		$("input[type='checkbox'][name='check_device']:checked").each(function(i) {
			deviceIds[i] = $(this).val();
		});
		if(deviceIds.length == 0){
			layer.msg('请先选取要分配的终端设备', {
				  anim: 6
				});
			
			return;
		}
		
		$.ajax({
			cache : true,
			type : "post",
			url : 'assignDevice',
			async : false,
			data : {
				"deviceIds" : deviceIds,
				"organizationId" : $("#organizationId").val()
			},
			traditional : true,
			success : function(data) {
				layer.msg('分配成功', { anim: 6 });
				window.location.reload();
			},
			error : function() {
				layer.msg('分配失败', { anim: 6 });
			}
		});
	});
	
	//批量导出
	$("#batchExport").click(function() {
		var url = "<%=contextPath%>/device/exportToExcel";
		url += "?orgId=" + $('#orgId').val();
		url += "&imei=" + $('#imei').val();
		url += "&isUse=" + $('#isUse').val();
		url += "&deviceType=" + encodeURIComponent(encodeURIComponent($('#deviceType').val()));
		window.location.href = url;
	});
	
	
	$("#deviceType,#imei").keydown(function(event){
		if(event.keyCode==13){
			$("#queryBtn").click();
		}
	});

	$("#isUse,#startStatus").change(function() {
		$("#queryBtn").click();
	});
	
	$("#queryBtn").click(function() {
		var param = {
			"orgId" : $("#orgId").val(),
			"imei" : $("#imei").val(),
			"startStatus" : $("#startStatus").val(),
			"isUse" : $("#isUse").val(),
			"deviceType" : $("#deviceType").val()
		};
		var oTable = $('#dataTable').DataTable();
		oTable.settings()[0].ajax.data = param;
	    oTable.ajax.reload();
	});
	
	$("#resetBtn").click(function() {
		$('#imei').val("");
		$('#deviceType').val("");
		$('#isUse').val("");
		$('#startStatus').val("");
	});
	
</script>
</body>
</html>
