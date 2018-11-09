<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<%String contextPath = request.getContextPath();%>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<style type="text/css">
#treeDemoList.ztree {
	max-height: 200px;
	border: 1px solid #617775;
	background: #f0f6e4;
	overflow-y: scroll;
	overflow-x: auto;
}
</style>
</head>
<body>
<%@include file="modal.jsp"%>
<div class="container-fluid" style="padding-left: 0px;padding-right: 0px;">
	<div class="row-fluid" >
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:user:employee:list">
						<li><a href="<%=contextPath%>/employee/list" target="_self"><span>员工管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:user:doctor:list">
						<li class="active"><a href="<%=contextPath%>/doctor/list" target="_self"><span>医师管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:user:disable:list">
						<li><a href="<%=contextPath%>/employee/list?status=1" target="_self"><span>禁用列表</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px;padding-right: 0px;">
			<ul class="nav nav-tabs">
				<li class="active"><a href="list">医师列表</a></li>
				<li><a href="edit">添加医师</a></li>
			</ul>
			
			<div class="modal fade bs-example-modal-sm" id="management" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
				<div class="modal-dialog" style="width: 750px;" role="document">
					<div class="modal-content"></div>
				</div>
			</div>
			<div class="col-md-12" id="modalBodyList" style="padding-right: 0px; padding-left: 0px;">
				<ol id="subDiv" class="breadcrumb">
					<form id="subForm" class="form-inline">
						<div id="menuContentList" class="menuContent" style="display: none; position: absolute; z-index: 999;">
							<ul id="treeDemoList" class="ztree" style="margin-top: 0; width: 200px;"></ul>
						</div>
						<div class="form-group">
							<label for="">所属机构</label>&nbsp;
							<input id="citySelList" type="text" class="form-control" readonly onclick="showMenuList(); return false;" style="width: 200px;" value="${org.orgName }"> 
							<input type="hidden" id="orgList" name="orgId" value="${org.orgId }">
							<!-- <input type="hidden" name="status" id="status" value="0"> -->
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">账号：</label>&nbsp; 
							<input type="text" class="form-control" style="width: 130px;" id="userCode" name="userCode" value="${userCode }" placeholder="请输入账号">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">姓名：</label>&nbsp; 
							<input type="text" class="form-control" style="width: 130px;" id="realName" name="realName" value="${realName }" placeholder="请输入姓名">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">职位：</label>&nbsp; 
							<form:select id="specialty" path="doc.specialty" items="${applicationScope.doc_specialty__}" itemLabel="value" itemValue="key" cssStyle="" class="form-control"/>
						</div>
						&nbsp;&nbsp;
						<button type="button" class="btn btn-primary" onclick="sub(1,1)">查找</button>
						&nbsp;&nbsp;
						<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">批量导入</button>
						&nbsp;&nbsp;
						<button type="button" class="btn btn-info" onclick="exportExcel('subForm','export')">批量导出</button>
					</form>
				</ol>
			</div>
			<div class="col-md-12" style="padding-left: 0px; padding-right: 0px; margin-top: -10px;">
				<table id="dataTable" class="table table-striped table-hover table-bordered" style="text-align: center;" width="100%"></table>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">

	$(function(){
		$("#specialty").val("${specialty}");
		if("${result}"!=null&&"${result}"!=""){
			layer.msg("${result}");
		}
	});
	
	var allColumns = [{
		"title" : "序号",
		"data" : "",
		"render" : function(data, type, row, meta) {
			return meta.row+1;
		}
	},{
		"title" : "所属机构",
		"data" : "orgName",
		"render" : function(data, type, row, meta) {
			if(row && row.org &&row.org.orgName){
				return row.org.orgName;
			}
			return "";
		}
	},{
		"title" : "账号",
		"data" : "userCode",
		"render" : function(data, type, row, meta) {
			if(row && row.user && row.user.userCode){
				return row.user.userCode;
			}
			return "";
		}
	},{
		"title" : "姓名",
		"data" : "realName",
		"render" : function(data, type, row, meta) {
			if(row && row.realName){
				return row.realName;
			}
			return "";
		}
	},{
		"title" : "职位",
		"data" : "specialtyView",
		"render" : function(data, type, row, meta) {
			if(row && row.specialtyView){
				return row.specialtyView;
			}
			return "";
		}
	},{
		"title" : "手机",
		"data" : "phoneNo",
		"render" : function(data, type, row, meta) {
			if(row && row.phoneNo){
				return row.phoneNo;
			}
			return "";
		}
	},{
		"title" : "邮箱",
		"data" : "email",
		"render" : function(data, type, row, meta) {
			if(row && row.email){
				return row.email;
			}
			return "";
		}
	},{
		"title" : "管理用户数",
		"data" : "phoneNum",
		"render" : function(data, type, row, meta) {
			if(row && row.memberSize && row.memberSize > 0){
				return row.memberSize;
			}
			return "0";
		}
	},{
		"title" : "操作",
		"data" : "",
		"render" : function(data, type, row, meta) {
			var htmlStr1 = "<a class='btn btn-info btn-xs' data-toggle='modal' data-target='#management' href='management?docId="+row.docId+"'>分配会员</a>";
			var htmlStr2 = "<a class='btn btn-info btn-xs' href='edit?docId="+row.docId+"'>编辑</a>";
			var htmlStr3 = "<a class='btn btn-warning btn-xs' href='javascript:;' onclick='resetPwd("+row.docId+")'>重置密码</a>";
			var htmlStr4 = "<a class='btn btn-danger btn-xs' href='javascript:;' onclick='disabled("+row.docId+")'>禁用</a>";
			return htmlStr1 + "&nbsp;" + htmlStr2 + "&nbsp;" + htmlStr3 + "&nbsp;" + htmlStr4;
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
					"orgId" : $("#orgList").val(),
					"status" : $("#status").val(),
					"userCode" : $("#userCode").val(),
					"realName" : $("#realName").val(),
					"specialty" : $("#specialty").val()
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
				"targets" : [ 0, 8 ]//指定的列
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

	function resetPwd(docId){
		layer.msg('该操作将重置医师的密码为初始值，是否继续?', {time: 0, btn: ['继续', '点错了'], yes: function(index){
			layer.close(index);
          	$.ajax({
          		type: "post",
              	url: "resetPwd",
              	data: {docId:docId},
              	dataType: "json",
              	success: function(data){
                  	layer.msg(data);
                  	setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                  		$("#subForm").submit();
                  	},1000);
              	},
                error:function (XMLHttpRequest, textStatus, errorThrown){
                	layer.msg("请求失败!", {icon: 5});
            	}
          	});
     	}});
	}
	function disabled(docId){
		layer.msg('禁用后会将所有已分配的会员解除，是否继续?', {time: 0, btn: ['继续', '点错了'], yes: function(index){
	    	layer.close(index);
	    	$.ajax({
		    	type: "post",
                url: "disabled",
                data: {docId:docId},
                dataType: "json",
                success: function(data){
   		            layer.msg(data);
                    setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                    	$("#subForm").submit();
                    },1000);
               	},
                error:function (XMLHttpRequest, textStatus, errorThrown){
                	layer.msg("请求失败!", {icon: 5});
                }
            });
	  	}});
	}
	
	//显示机构树
	var zTreeObjList;
	var settingList = {
		async : {
			autoParam : [],
			contentType : "application/json",
			dataType : "json",
			enable : true,
			url : "/healthCloud/org/getOrgTree",
			type : "post"
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : null
			}
		},
		view : {
			dblClickExpand : true,
			showLine : true,
			selectedMulti : false
		},
		callback : {
			onNodeCreated : onNodeCreatedList,
			onClick : onClickList
		}
	};
	
	$(document).ready(function() {
		zTreeObjList = $.fn.zTree.init($("#treeDemoList"), settingList);
	});
	
	function showMenuList() {
		var cityObj = $("#citySelList");
		var modalOffset = $("#modalBodyList").offset();
		var cityOffset = $("#citySelList").offset();
		var left = cityOffset.left - modalOffset.left;
		var top = cityOffset.top - modalOffset.top;
		$("#menuContentList").css({
			left : left + "px",
			top : top + cityObj.outerHeight() + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onBodyDownList);
	}
	function hideMenuList() {
		$("#menuContentList").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDownList);
	}
	function onClickList(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemoList"), nodes = zTree.getSelectedNodes(), v = "", orgId = "";
		nodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
			orgId = nodes[i].id;
		}
		if (v.length > 0){
			v = v.substring(0, v.length - 1);
		}
		var cityObj = $("#citySelList");
		cityObj.attr("value", v);
		$("#orgList").attr("value", orgId);
		hideMenuList();
	}
	function onNodeCreatedList(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var nodes = zTree.getNodes();
		nodes = zTree.getNodeByParam("id", $("#orgList").val() , null);
		zTree.selectNode(nodes, false , false);
		zTree.expandNode(nodes, true, false, false);
		if ($("#citySelList").val() == "") {
			onClickList(e, treeId, treeNode);
		}
	}
	function onBodyDownList(event) {
		if (!(event.target.id == "citySelList"
				|| event.target.id == "menuContentList" || $(event.target)
				.parents("#menuContentList").length > 0)) {
			hideMenuList();
		}
	}
	
	$("#management").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});
</script>
</html>