<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header" style="background: #339966;">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title color-white"
		style="font-weight: bold; color: #ffffff; font-size: 18px; line-height: 27px; padding-top: 0px;">选择区域</h4>
</div>

<div class="modal-body">
	<ul id="zTree" class="ztree"></ul>
</div>

<div class="modal-footer" style="background-color: #EEEEEE">
	<button type="button" id="submitBtn" class="btn btn-primary">确定</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>

<script type="text/javascript">
	var zTreeObj;
	var zTreeNodes = ${areaTree};
	var areaId = ${areaId};
	
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
		zTreeObj = $.fn.zTree.init($("#zTree"), setting, zTreeNodes);
		var node = zTreeObj.getNodeByParam('id', areaId);
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
				var zTreeObj = $.fn.zTree.getZTreeObj("zTree");
				var selectedNodes = zTreeObj.getSelectedNodes();
				$("#area").val(selectedNodes[0].name);
				$("#areaId").val(selectedNodes[0].id);
				$("#areaBtn").attr( "href", "/healthCloud/area/showAreaTree?areaId=" + selectedNodes[0].id);
				$('#areaTree').modal('hide');
			}
		);
	});
</script>