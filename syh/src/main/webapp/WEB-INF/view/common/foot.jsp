<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="${contextPath}/static/localjs/common.js"></script> 
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">选择比赛项目</h4>
			</div>
			<div class="modal-body">
				<div id="menuContent" class="menuContent"
					style="width: 95%; border: 1px solid rgb(170, 170, 170); z-index: 10;">
					<ul id="treeDemo" class="ztree"
						style="margin-top: 0; width: 100%; height: auto;"></ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeProject" class="btn btn-default"
					data-dismiss="modal">关闭</button>
				<button type="button" id="selectProject" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="classModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">选择项目分类</h4>
			</div>
			<div class="modal-body">
				<div id="menuContent" class="menuContent"
					style="width: 95%; border: 1px solid rgb(170, 170, 170); z-index: 10;">
					<ul id="tree" class="ztree"
						style="margin-top: 0; width: 100%; height: auto;"></ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeClass" class="btn btn-default"
					data-dismiss="modal">关闭</button>
				<button type="button" id="selectClass" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
 
<!-- 权限模态框（Modal） -->
<div class="modal fade" id="roleResModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">选择权限资源</h4>
			</div>
			<div class="modal-body">
				<div id="menuContent" class="menuContent"
					style="width: 95%; border: 1px solid rgb(170, 170, 170); z-index: 10;">
					<ul id="roleResTree" class="ztree"
						style="margin-top: 0; width: 100%; height: auto;"></ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeRoleResModal" class="btn btn-default"
					data-dismiss="modal">关闭</button>
				<button type="button" id="submitRoleResModal" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<!-- 部门模态框（Modal） -->
<div class="modal fade" id="deptModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">选择上级部门</h4>
			</div>
			<div class="modal-body">
				<div id="menuContent" class="menuContent"
					style="width: 95%; border: 1px solid rgb(170, 170, 170); z-index: 10;">
					<ul id="deptTree" class="ztree"
						style="margin-top: 0; width: 100%; height: auto;"></ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeDeptModal" class="btn btn-default"
					data-dismiss="modal">关闭</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<!-- 分类模态框--选择最下级分类（Modal） -->
<div class="modal fade" id="myNodeModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">选择比赛项目</h4>
			</div>
			<div class="modal-body">
				<div id="menuContent" class="menuContent"
					style="width: 95%; border: 1px solid rgb(170, 170, 170); z-index: 10;">
					<ul id="nodeTree" class="ztree"
						style="margin-top: 0; width: 100%; height: auto;"></ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeNOdeClass" class="btn btn-default"
					data-dismiss="modal">关闭</button>
				<button type="button" id="selectNOdeClass" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>