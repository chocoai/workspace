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

		<div class="modal fade" id="viewAlarmDetail" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content "></div>
			</div>
		</div>

		<div class="modal fade" id="viewSOSLinkMan" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content "></div>
			</div>
		</div>
		
		<div class="modal fade" id="viewPushRecord" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content "></div>
			</div>
		</div>

		<div class="row-fluid">
			<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;">
				<ul class="nav nav-tabs">
					<li role="presentation" class="active"><a href="<%=contextPath%>/alarm/toList">告警列表</a></li>
					<li><a href="<%=contextPath%>/callRecord/list">呼叫列表</a></li>
				</ul>
				<ol class="breadcrumb" style="margin-top: 10px;">
					<form id="deviceForm" action="list" class="form-inline">
						<div class="form-group">
							<label for="">会员姓名</label>&nbsp; 
							<input type="text" class="form-control" style="width: 160px;" id="memberName" name="memberName" value="${memberName}" placeholder="请输入会员姓名">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">IMEI号</label>&nbsp; 
							<input type="text" class="form-control" style="width: 160px;" id="imei" name="imei" value="${imei }" placeholder="请输入IMEI号">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">告警类型</label>&nbsp; 
							<form:select id="alarmType" path="alarmType" name="alarmType" items="${applicationScope.alarm_type__}" itemLabel="value" itemValue="key" cssStyle="" class="form-control"/>
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="alarmTime" class="control-label">告警时间</label>&nbsp;
							<div class="input-group date form_date" style="width: 196px;" data-date-format="yyyy-mm-dd" data-link-field="alarmTime" data-link-format="yyyy-mm-dd">
								<input class="form-control" id="input_time" name="input_time" size="9" type="text" value="" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="hidden" id="alarmTime" name="alarmTime" value="${alarmTime }" /><br />
						</div>
						&nbsp;&nbsp;		
						<div class="form-group">
							<label for="">是否已读</label>&nbsp; 
							<form:select id="isRead" path="isRead" name="isRead" items="${applicationScope.is_read__}" itemLabel="value" itemValue="key" cssStyle="" class="form-control"/>
						</div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-info" id="queryBtn">查询</button>&nbsp;&nbsp;
						<button type="reset" class="btn btn-default" id="resetBtn">重置</button>								
					</form>
				</ol>
			</div>

			<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;margin-top: -20px;">
				<table id="dataTable" class="table table-striped table-hover table-bordered" width="100%"></table>
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
		
		$("#input_time").val('${alarmTime}');
		$("#alarmType").val('${alarmType}');
		$("#isRead").val('${isRead}');
		
		$("#viewAlarmDetail").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});

		$("#viewSOSLinkMan").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});

		$("#viewPushRecord").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});

		var allColumns = [
				{
					"title" : "告警ID",
					"data" : "alarmId",
					"render" : function(data, type, row, meta) {
						if(row && row.alarmId){
							return row.alarmId;
						}
						return "";
					}
				},
				{
					"title" : "会员姓名",
					"data" : "realName",
					"render" : function(data, type, row, meta) {
						if(row && row.realName){
							return row.realName;
						}
						return "";
					}
				},
				{
					"title" : "告警类型",
					"data" : "alarmTypeView",
					"render" : function(data, type, row, meta) {
						if(row && row.alarmTypeView){
							return row.alarmTypeView;
						}
						return "";
					}
				},
				{
					"title" : "是否已读",
					"data" : "isReadView",
					"render" : function(data, type, row, meta) {
						if(row && row.isReadView){
							return row.isReadView;
						}
						return "";
					}
				},
				{
					"title" : "告警时间",
					"data" : "alarmTime",
					"render" : function(data, type, row, meta) {
						if(row && row.alarmTime){
							return row.alarmTime;
						}
						return "";
					}
				},
				{
					"title" : "设备IMEI",
					"data" : "imei",
					"render" : function(data, type, row, meta) {
						if(row && row.imei){
							return row.imei;
						}
						return "";
					}
				},
				{
					"title" : "联系电话",
					"data" : "phoneNum",
					"render" : function(data, type, row, meta) {
						if(row && row.phoneNum){
							return row.phoneNum;
						}
						return "";
					}
				},
				{
					"title" : "操作",
					"data" : "",
					"render" : function(data, type, row, meta) {
						var htmlStr1 = '<button type="button" class="btn btn-primary btn-xs" data-toggle="modal" href="viewAlarmDetail?alarmId=' + row.alarmId + '" data-target="#viewAlarmDetail">查看</button>';
						var htmlStr2 = '<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" href="viewSOSLinkMan?imei=' + row.imei + '" data-target="#viewSOSLinkMan">紧急联系人</button>';
						return htmlStr1 + "&nbsp;" + htmlStr2;
					}
				} ]

		$(document).ready(function() {
			$('#dataTable').dataTable({
				// DataTables - Features
				"autoWidth" : true, // 控制Datatables是否自适应宽度,默认值true
				"deferRender" : false, // 控制表格的延迟渲染，可以提高初始化的速度。默认值false
				"info" : true, // 控制是否显示表格左下角的信息,默认值true
				"lengthChange" : false, // 是否允许最终用户改变表格每页显示的记录数，默认值true
				"ordering" : false, // 是否允许Datatables开启排序,默认值true
				"paging" : true, // 是否允许表格分页，默认true
				"processing" : true, // 是否显示处理状态(排序的时候，数据很多耗费时间长的话，也会显示这个)默认值false
				"scrollX" : false, // 是否允许水平滚动，默认值false
				//"scrollY": "200px", // 控制表格的垂直滚动。 Vertical scrolling 强制DataTable为指定的高度，并且会允许任何超出当前视口的数据进行滚动
				"scrollCollapse" : false, //当显示更少的记录时，是否允许表格减少高度, 默认值false
				"searching" : false, // 此选项用来开启、关闭Datatables的搜索功能,默认值true
				"serverSide" : true, // 是否开启服务器模式,默认值false 
				"stateSave" : false, // 保存状态 - 在页面重新加载的时候恢复状态（页码等内容）,默认值false

				// DataTables - Data
				"ajax" : {
					"url" : "<%=contextPath%>/alarm/list",
					"type" : "POST",
					"data" : {
						"memberName" : $('#memberName').val(),
						"imei" : $('#imei').val(),
						"alarmType" : $('#alarmType').val(),
						"alarmTime" : $('#alarmTime').val(),
						"isRead" : $('#isRead').val()
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
				"order" : [ [ 4, "desc" ] ], //表格在初始化的时候的排序，第2列，升序排列  
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
					"targets" : [ 0, 7 ] //指定的列
				} ],
				"columns" : allColumns,

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
		
		
		$("#memberName,#imei").keydown(function(event){
			if(event.keyCode==13){
				$("#queryBtn").click();
			}
		});

		$("#alarmType,#alarmTime,#isRead").change(function() {
			$("#queryBtn").click();
		});


		$("#queryBtn").click(function() {
			var param = {
				"memberName" : $('#memberName').val(),
				"imei" : $('#imei').val(),
				"alarmType" : $('#alarmType').val(),
				"alarmTime" : $('#alarmTime').val(),
				"isRead" : $('#isRead').val()
			}
			var oTable = $('#dataTable').DataTable();
			oTable.settings()[0].ajax.data = param;
		    oTable.ajax.reload();
		});
		
		$("#resetBtn").click(function() {
			$('#memberName').val("");
			$('#imei').val("");
			$('#alarmType').val("");
			$('#alarmTime').val("");
			$('#isRead').val("");
		});
		
		
	</script>
</body>
</html>
