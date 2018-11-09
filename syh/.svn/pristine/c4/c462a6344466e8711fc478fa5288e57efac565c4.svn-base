<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li><a href="${contextPath}/System/sysRes">权限资源管理</a></li>
	<li class="active">权限资源信息</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default div-header">
		<div class="panel-heading">
				<h3 class="panel-title">权限资源信息</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="form-horizontal">
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName1">上级资源</label>
							<input type="hidden" value="addInfoRes" id="addInfoRes">
							<input type="hidden" value="${info.cid}" id="cid">
							<input type="hidden" value="${info.pareId}" id="pareId">
							<input type="hidden" value="${info.state}" id="state">
							<div class="col-md-8">
								<input type="text" class="form-control" id="pareName" value="${info.pareSysRes.resName}" placeholder="请选择上级资源">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName2">资源名称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="resName" value="${info.resName}"
									placeholder="请输入资源名称">
							</div>
						</div>
					</div>
					<div class="col-md-5 col-md-offset-1">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName3">资源地址</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="resUrl" value="${info.resUrl}"
									placeholder="请输入资源地址">
							</div>
						</div>
					</div>
					<div class="col-md-5">
						<div class="form-group">
							<label class="col-md-4 control-label" for="xzdwName4">排序号</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="orderNum" value="${info.orderNum}"
									placeholder="请输入排序号">
							</div>
						</div>
					</div>
					<div class="col-md-10 col-md-offset-1">
                       <div class="form-group">
                           <label class="col-md-2 control-label" for="xzInputName13">备注</label>
                           <div class="col-md-10">
                               <textarea class="form-control" rows="3" id="backup">${info.backup}</textarea>
                           </div>
                       </div>
                     </div>
					<div class="col-md-10 col-md-offset-1">
							<button type="button" id="cancleBtnTo" class="btn btn-warning myformbtn pull-right">取消</button>
							<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<!-- 中间内容 end -->

<!-- 权限模态框（Modal） -->
<div class="modal fade" id="resModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">选择上级资源</h4>
			</div>
			<div class="modal-body">
				<div id="menuContent" class="menuContent"
					style="width: 95%; border: 1px solid rgb(170, 170, 170); z-index: 10;">
					<ul id="resTree" class="ztree"
						style="margin-top: 0; width: 100%; height: auto;"></ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="closeResModal" class="btn btn-default"
					data-dismiss="modal">关闭</button>
				<button type="button" id="submitResModal" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<script src="${contextPath}/static/localjs/stsyem/addInfoRes.js"></script>