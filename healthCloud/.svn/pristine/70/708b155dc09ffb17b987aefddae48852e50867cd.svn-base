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
					<shiro:hasPermission name="cms:content:list">
						<li class="active"><a href="<%=contextPath%>/content/list" target="_self"><span>内容管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="cms:channel:list">
						<li><a href="<%=contextPath%>/channel/list" target="_self"><span>栏目管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<ol class="breadcrumb">
				<li><a href="#">内容管理</a></li>
				<li><a href="#">内容管理</a></li>
			</ol>
			
			<div class="col-md-2" style="vertical-align: top; padding-left: 0px; padding-right: 0px;">
				<ul id="channelTree" class="ztree" style="border: #ddd 1px solid; margin: 2px 5px 0 0; overflow-x: auto;"></ul>
			</div>

			<div class="col-md-10" style="padding-right: 0px;">
				<div id="contentTable">
					<jsp:include page="content_list.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	var channelId = ${channelId};
	$("#channelTree").height(window.innerHeight - 80);
	var zTreeObj;
	var setting = {
		async : {
			autoParam : [],
			contentType : "application/json",
			dataType : "json",
			enable : true,
			url : "<%=contextPath%>/channel/getChannelTree",
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
		zTreeObj = $.fn.zTree.init($("#channelTree"), setting);
	});

	//数加载完成回调,展开根节点
	function onAsyncSuccess(event, treeId, treeNode, msg) {
		var zTree = $.fn.zTree.getZTreeObj("channelTree");
		var node = zTree.getNodeByParam('id', channelId);
		zTree.expandNode(node);
		zTree.selectNode(node);
		zTreeOnClick(event, treeId, node);
	}

	function zTreeOnClick(event, treeId, treeNode) {
		$.ajax({
			async : true,
			cache : false,
			contentType : false,
			dataType : "html",
			processData : false,
			type : "get",
			url : "getContentList?channelId=" + treeNode.id,
			success : function(data) {
				$("#contentTable").html(data);
				channelId = treeNode.id;
			},
		});
	};
	
	function getArchived(){
		$.ajax({
			async : true,
			cache : false,
			contentType : false,
			dataType : "html",
			processData : false,
			type : "get",
			url : "getArchived?channelId=" + channelId,
			success : function(data) {
				$("#contentTable").html(data);
			},
		});
	}
	
	function reloadTable(){
		window.location.href = "list?channelId=" + channelId;
	}
	
	function viewDetail(contentId){
		window.location.href = "viewDetail?contentId=" + contentId;
	}

</script>
</body>
</html>
