<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header" style="background: #339966;">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title color-white"
		style="font-weight: bold; color: #ffffff; font-size: 18px; line-height: 27px; padding-top: 0px;">权限设置</h4>
</div>

<div class="modal-body">
	<form id="authorizationForm" action="authorization" method="post">
		<input type="hidden" id="roleId" name="roleId" value="${roleId }">
		<input type="hidden" id="resIds" name="resIds" value="">
		<ul id="resTree" class="ztree"></ul>
	</form>
</div>

<div class="modal-footer" style="background-color: #EEEEEE">
	<button type="button" id="submitBtn" class="btn btn-primary">确定</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>

<script type="text/javascript">
	var zTreeObj;
	var zTreeNodes = ${resTree};
	console.info(zTreeNodes);
	var setting = {
		
		check: {
			enable: true,
			autoCheckTrigger: false,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "ps" }
		},
		
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
			selectedMulti : true
		}

	};

	$(document).ready(function() {
		zTreeObj = $.fn.zTree.init($("#resTree"), setting, zTreeNodes);
		zTreeObj.expandAll(true);
		
	});

	function zTreeOnClick(event, treeId, treeNode) {

	};
	
	function zTreeOnDblClick(event, treeId, treeNode) {
		
	};


	$(function() {
		$("#submitBtn").click(function() {
			var checkedNodes = zTreeObj.getCheckedNodes(true);
			var resIds = new Array(checkedNodes.length);
			for (var i = 0; i < checkedNodes.length; i++) {
				resIds[i] = checkedNodes[i].id;
			}
			$('#resIds').val(resIds);
			$('#authorizationForm').submit();
		});
	});
</script>