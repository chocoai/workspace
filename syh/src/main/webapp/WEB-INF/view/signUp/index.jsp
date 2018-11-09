<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>报名信息管理</li>
	<li class="active">参赛单位信息管理</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="row">
		<div class="col-md-12">
			<shiro:hasPermission name="/unitInfo/updateInfo">
				<a class="btn btn-success difbtn" href="updateInfo" target="_self" target="_blank" role="button">
					<i class="icon-plus"></i> &nbsp;新增
				</a> 
				<a id="updateInfo" class="btn btn-info difbtn" href="javascript:;" role="button"> 
					<i class="icon-edit"></i> &nbsp;编辑
				</a> 
			</shiro:hasPermission>
			<shiro:hasPermission name="/unitInfo/deleteInfo">
				<a id="deleteBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
					<i class="icon-minus"></i> &nbsp;删除
				</a> 
			</shiro:hasPermission>
		</div>
	</div>
	<!-- 查询表单 strat -->
	<div class="lookfor">
		<div class="row">
			<div class="col-md-12">
				<div class="form-inline">
					<div class="form-group">
						<label for="flmc">单位名称/简称</label> 
						<input type="text"  id="findContent" class="form-control" placeholder="请输入单位名称/简称">
					</div>
					<button id="queryBtn" type="submit" class="btn btn-primary">
						<span class="icon-search"></span>&nbsp;&nbsp;查询
					</button>
					<a id="refreshTable" href="javascript:"></a>
				</div>
			</div>
		</div>
	</div>
	<!-- 查询表单 end -->

	<!-- 表格 strat -->
	<div class="mytable">
		<div class="table-responsive">
			<table class="table table-hover table-bordered table-striped">
				<thead>
					<tr>
						<th><input type="checkbox" id ="chooseAll"></th>
						<th>单位名称</th>
						<th>单位简称</th>
						<th>运动员数量</th>
						<th>联系人</th>
						<th>联系电话</th>
					</tr>
				</thead>
				<tbody id ="list">
				</tbody>
				<tfoot>
						<tr id="noDataPage" class="hidden" >
							<td colspan="6" align="center">暂无数据</td>
						</tr>
				</tfoot>
			</table>
		</div>
		<!-- 分页 start -->
		<div id="pageID"></div>
		<!-- 分页 end -->
	</div>
	<!-- 表格 end -->
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/signUp/index.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 

