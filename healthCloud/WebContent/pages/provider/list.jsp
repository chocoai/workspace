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
	<div class="row-fluid">
		<div class="col-md-12">

			<div class="col-md-2" style="vertical-align: top; padding-left: 0px; padding-right: 0px;">
				<ul id="orgTree" class="ztree" style="border: #ddd 1px solid; margin: 10px 5px 0 0; overflow-x: auto;"></ul>
			</div>

			<div class="col-md-10" style="padding-right: 0px;margin-top: 10px;">
				<div id="providerTable">
					<jsp:include page="provider_list.jsp"></jsp:include>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		var orgId = ${orgId};
		
		$("#orgTree").height(window.innerHeight - 30);

		var zTreeObj;
		var setting = {
			async : {
				autoParam : [],
				contentType : "application/json",
				dataType : "json",
				enable : true,
				url : "<%=contextPath%>/org/getOrgTree",
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
			zTreeObj = $.fn.zTree.init($("#orgTree"), setting);
		});

		//数加载完成回调,展开根节点
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			var zTree = $.fn.zTree.getZTreeObj("orgTree");
			var node = zTree.getNodeByParam('id', orgId);
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
				url : "<%=contextPath%>/provider/list?orgId=" + treeNode.id,
				success : function(data) {
					$("#providerTable").html(data);
				},
			});
		};
	</script>
</body>
</html>
