<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>系统配置管理</li>
	<li class="active">系统用户管理</li>
</ol>
<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="row">
		<div class="col-md-12">
			<a class="btn btn-success difbtn" href="updateInfo"  role="button">
				<i class="icon-plus"></i> &nbsp;新增
			</a> 
			<a id="updateInfo" class="btn btn-info difbtn" href="javascript:;" role="button"> 
				<i class="icon-edit"></i> &nbsp;编辑
			</a> 
			<a id="deleteBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
				<i class="icon-minus"></i> &nbsp;删除
			</a> 
		</div>
	</div>
	<!-- 查询表单 strat -->
	<div class="lookfor">
		<div class="row">
			<div class="col-md-12">
				<div class="form-inline">
					<div class="form-group">
						<label for="sjflmc">用户姓名/简称</label> 
						<input id="findContent" type="text" class="form-control" placeholder="输入姓名/简称搜索" id="sjflmc">
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
						<th>姓名</th>
                        <th>账号</th>
                        <th>昵称</th>
                        <th>角色</th>
                        <th>状态</th>
                        <th>备注</th>
                        <th>操作</th>
					</tr>
				</thead>
				<tbody id ="list">
				</tbody>
				<tfoot>
					<tr id="noDataPage" class="hidden">
						<td colspan="8" align="center">暂无数据</td>
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

<script src="${contextPath}/static/localjs/stsyem/indexUser.js"></script>
<script src="${contextPath}/static/localjs/common.js"></script> 

