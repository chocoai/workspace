<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>查询统计管理</li>
	<li class="active">奖惩信息统计查询</li>
</ol>
<!-- 当前位置 end -->

<!-- 中间内容 start -->
<div class="content">
	<!-- 查询表单 start -->
	<div class="lookfor">
		<div class="row">
			<div class="col-md-12">
				<div class="form-inline">
					<div class="form-group">
						<label for="projectCid">项目名称</label> <input type="text"
							class="form-control" name="projectName" placeholder="输入项目名称" id="projectNameId"> 
					</div>
					<div class="form-group">
						<label for="unitName">参赛单位</label> <input type="text"
							class="form-control" name="unitName" placeholder="输入单位名称" id="unitNameId">
					</div>
					<button id="queryBtn" class="btn btn-primary pull-right">
						<span class="icon-search"></span>&nbsp;&nbsp;查询
					</button>
					<a id="refreshTable" href="javascript:"></a>
				</div>
			</div>
		</div>
	</div>
	<!-- 查询表单 end -->

	<!-- 表格 start -->
	<div class="mytable">
		<div class="table-responsive">
			<table class="table table-hover table-bordered table-striped">
				<thead>
					<tr>
						<th><input id="chooseAll" type="checkbox"></th>
						<th>项目名称</th>
						<th>单位名称</th>
						<th>奖惩类型</th>
						<th>奖牌类型</th>
						<th>奖牌数量</th>
						<th>积分数</th>
						<th>原因</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody id="list">
				</tbody>
				<tfoot>
					<tr id="noDataPage" class="hidden">
						<td colspan="9" align="center">暂无数据</td>
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
<script src="${contextPath}/static/localjs/pubRewInfo/index.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%>