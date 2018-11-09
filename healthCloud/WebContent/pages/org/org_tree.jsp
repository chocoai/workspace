<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header" style="background: #339966;">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title color-white" style="font-weight: bold; color: #ffffff; font-size: 18px; line-height: 27px; padding-top: 0px;">选择机构</h4>
</div>

<div class="modal-body">
	<ul id="orgTree" class="ztree"></ul>
</div>

<div class="modal-footer" style="background-color: #EEEEEE">
	<button type="button" id="submitBtn" class="btn btn-primary">确定</button>
	<!-- <button type="button" id="clearBtn" class="btn btn-default">清除</button> -->
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>

<script type="text/javascript">
	var zTreeObj;
	var zTreeNodes = ${orgTree};
	var orgId = ${orgId};
	
	var setting = {
		callback : {
			onClick : zTreeOnClick,
			onDblClick: zTreeOnDblClick
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
		zTreeObj = $.fn.zTree.init($("#orgTree"), setting, zTreeNodes);
		var node = zTreeObj.getNodeByParam('id', orgId);
		zTreeObj.expandNode(node);
		zTreeObj.selectNode(node);
	});

	function zTreeOnClick(event, treeId, treeNode) {

	};
	
	function zTreeOnDblClick(event, treeId, treeNode) {
		$("#submitBtn").click();
	};

	$(function() {
		$("#submitBtn").click(
			function() {
				var zTreeObj = $.fn.zTree.getZTreeObj("orgTree");
				var selectedNodes = zTreeObj.getSelectedNodes();
				$("#orgName").val(selectedNodes[0].name);
				$("#orgId").val(selectedNodes[0].id);
				$("#organizationName").val(selectedNodes[0].name);
				$("#organizationId").val(selectedNodes[0].id);
				$("#orgBtn").attr( "href", "/healthCloud/org/showOrgTree?orgId=" + selectedNodes[0].id);
				$('#organizationTree').modal('hide');
			}
		);
	});
</script>