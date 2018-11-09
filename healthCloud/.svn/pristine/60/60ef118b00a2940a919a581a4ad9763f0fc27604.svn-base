<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%String contextPath = request.getContextPath();%>

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
			<ol class="breadcrumb">
				<li><a href="#" target="_self">内容管理</a></li>
			</ol>

			<div class="modal fade" id="addChannel" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content "></div>
				</div>
			</div>

			<div class="modal fade" id="updateChannel" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content "></div>
				</div>
			</div>

			<div class="col-md-2"
				style="vertical-align: top; padding-left: 0px; padding-right: 0px;">
				<ul id="channelTree" class="ztree"
					style="border: #ddd 1px solid; margin: 2px 5px 0 0; overflow-x: auto;"></ul>
			</div>

			<div class="col-md-10" style="padding-right: 0px;">
				<div id="channelTable">
					<jsp:include page="channel_list.jsp"></jsp:include>
				</div>
			</div>
			
			<div class="modal" id="alterBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">操作提示</h4>
						</div>
						<div class="modal-body">
							<span>请先删除此机构下的子机构、员工、医师、设备等数据，您确认删除此机构吗？</span>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" onclick="toDelete()">确认</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<script type="text/javascript">
		var channelId;
		var parentId = ${parentId};
		
		$('#alterBox').on( 'shown.bs.modal',
				function(e) {
					// 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
					$(this).css('display', 'block');
					var modalHeight = $(window).height() / 2
							- $('#alterBox .modal-dialog').height() / 2;
					$(this).find('.modal-dialog').css({
						'margin-top' : modalHeight
					});
				});

		function showAlert(id) {
			$('#alterBox').modal('show');
			channelId = id;
		}

		function toDelete() {
			window.location.href = "delete?channelId=" + channelId;
		}

		$("#channelTree").height(window.innerHeight - 100);

		var zTreeObj;
		var setting = {
			async : {
				autoParam : [],
				contentType : "application/json",
				dataType : "json",
				enable : true,
				url : "getChannelTree",
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
			var node = zTree.getNodeByParam('id', parentId);
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
				url : "getChildChannel?parentId=" + treeNode.id,
				success : function(data) {
					$("#channelTable").html(data);
				},
			});
		};

		$("#updateChannel").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});

		$("#addChannel").on("hidden.bs.modal", function() {
			$(this).removeData("bs.modal");
		});
	</script>
</body>
</html>
