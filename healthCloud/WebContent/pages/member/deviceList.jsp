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
				<li class="active">
					<a href="deviceList?memberId=${member.memberId }">【${member.realName }】的终端设备列表</a>
				</li>
				<li>
					<a href="org_relation?memberId=${member.memberId }">为【${member.realName }】服务的人员</a>
				</li>
				<li>
					<a href="followMember?memberId=${member.memberId }">关注【${member.realName }】的会员</a>
				</li>
				<li>
					<a href="memberFollow?memberId=${member.memberId }">【${member.realName }】关注的会员</a>
				</li>
			</ul>
			<form id="subForm" class="form-search form-inline">
				<input type="hidden" id="memberId" name="memberId" value="${member.memberId }">
				<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;margin-top: -10px;">
					<table id="dataTable" class="table table-striped table-hover table-bordered" style="text-align: center;" width="100%"></table>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	var allColumns = [{
		"title" : "序号",
		"data" : "",
		"render" : function(data, type, row, meta) {
			return meta.row+1;
		}
	},{
		"title" : "IMEI码",
		"data" : "imei",
		"render" : function(data, type, row, meta) {
			if(row && row.imei){
				return row.imei;
			}
			return "";
		}
	},{
		"title" : "设备类型",
		"data" : "deviceType",
		"render" : function(data, type, row, meta) {
			if(row && row.deviceType){
				return row.deviceType;
			}
			return "";
		}
	},{
		"title" : "设备SIM卡",
		"data" : "sim",
		"render" : function(data, type, row, meta) {
			if(row && row.sim){
				return row.sim;
			}
			return "";
		}
	},{
		"title" : "绑定时间",
		"data" : "bindTime",
		"render" : function(data, type, row, meta) {
			if(row && row.bindTime){
				return row.bindTime;
			}
			return "";
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
					"memberId" : $("#memberId").val()
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
				"targets" : [ 0, 4 ]//指定的列
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
	
</script>
</html>