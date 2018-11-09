<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li class="active">权限资源管理</li>
</ol>
<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="row">
		<!-- 左侧列表组 strat -->
		<div class="col-md-3">
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<i class="icon-th-large"></i> &nbsp;权限资源列表
				</div>
				<!-- List group -->
				<div id="menuContent" class="menuContent" >
					<ul id="resTree" class="ztree"></ul>
				</div>
			</div>
		</div>
		<!-- 左侧列表组 end -->

		<!-- 右侧列表组内容 strat -->
		<div class="col-md-9">
			<div class="row">
				<div class="col-md-12">
					<a class="btn btn-success difbtn" href="javascript:;" role="button" id="save">
						<i class="icon-plus"></i> &nbsp;新增
					</a> 
					<a id="updateInfo" class="btn btn-info difbtn" href="javascript:;" role="button"> 
						<i class="icon-edit"></i> &nbsp;编辑
					</a> 
					<a id="deleteBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
						<i class="icon-minus"></i> &nbsp;删除
					</a> 
					<a id="queryBtn" href="javascript:"></a>
					<a id="refreshTable" href="javascript:"></a>
				</div>
			</div>

			<!-- 表格 strat -->
			<div class="col-md-12 nopad-lr">
				<div class="mytable">
					<div class="panel panel-default nomar-b">
						<div class="panel-heading text-center font-wb" id="pareName">权限资源列表</div>
						<div class="table-responsive">
							<input type="hidden" id="pareId" value="${pCid}">
							<input type="hidden" id="resName">
							<table class="table table-hover table-bordered table-striped">
								<thead>
									<tr>
										<th><input type="checkbox" id ="chooseAll"></th>
										<th>资源名</th>
										<th>资源路径</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="list">
								</tbody>
								<tfoot>
									<tr id="noDataPage" class="hidden">
										<td colspan="4" align="center">暂无数据</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
					<!-- 分页 strat -->
					<div id="pageID"></div>
					<!-- 分页 end -->
				</div>
			</div>
			<!-- 表格 end -->
		</div>
		<!-- 右侧列表组内容 end -->
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/stsyem/indexRes.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 