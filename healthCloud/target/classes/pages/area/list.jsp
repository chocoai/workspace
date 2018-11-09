<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%String contextPath = request.getContextPath();%>
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
					<shiro:hasPermission name="system:role:list">
						<li><a href="<%=contextPath%>/role/list" target="_self"><span>角色管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:resource:list">
						<li><a href="<%=contextPath%>/res/list" target="_self"><span>权限资源管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:dict:list">
						<li><a href="<%=contextPath%>/dict/list" target="_self"><span>数据字典管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="system:area:list">
						<li class="active"><a href="<%=contextPath%>/area/list" target="_self"><span>区域管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="app:version:list">
						<li><a href="<%=contextPath%>/version/list" target="_self"><span>版本管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			<ol class="breadcrumb">
				<li><a href="#">系统管理</a></li>
				<li><a href="#">区域管理</a></li>
			</ol>

			<div class="modal fade" id="addArea" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" style="width: 700px;" role="document">
					<div class="modal-content "></div>
				</div>
			</div>

			<div class="modal fade" id="updateArea" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" style="width: 700px;" role="document">
					<div class="modal-content "></div>
				</div>
			</div>

			<div class="col-md-2" style="vertical-align: top; padding-left: 0px; padding-right: 0px;">
				<ul id="areaTree" class="ztree" style="border: #ddd 1px solid; margin: 2px 5px 0 0; overflow-x: auto;"></ul>
			</div>

			<div class="col-md-10" style="padding-right: 0px;">
				<div id="areaTable">
					<jsp:include page="area_list.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	var parentAreaId = ${parentAreaId};

	function toDelete(areaId) {
		//询问框
		layer.confirm('您确认删除此区域吗？删除后与此区域相关的数据可能异常！', {
		  btn: ['确认','取消'] //按钮
		}, function(){
			window.location.href = "delete?areaId=" + areaId;
		}, function(){
		  return;
		});
	}

	$("#areaTree").height(window.innerHeight - 80);

	var zTreeObj;
	var setting = {
		async : {
			autoParam : [],
			contentType : "application/json",
			dataType : "json",
			enable : true,
			url : "getAreaTree",
			type : "post"
		},

		callback : {
			onAsyncSuccess : onAsyncSuccess,
			onClick : zTreeOnClick
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
		}

	};

	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#areaTree"), setting);
	});

	//数加载完成回调,展开根节点
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		var zTree = $.fn.zTree.getZTreeObj("areaTree");
		var node = zTree.getNodeByParam('id', parentAreaId);
		zTree.expandNode(node);
		zTree.selectNode(node);
	}

	function zTreeOnClick(event, treeId, treeNode) {
		$.ajax({
			async : true,
			cache : false,
			contentType : false,
			dataType : "html",
			processData : false,
			type : "get",
			url : "getChildArea?parentId=" + treeNode.id,
			success : function(data) {
				$("#areaTable").html(data);
			},
		});
	};

	$("#updateArea").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});

	$("#addArea").on("hidden.bs.modal", function() {
		$(this).removeData("bs.modal");
	});
</script>
</body>
</html>
