<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 start -->

<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>查询统计管理</li>
	<li class="active">成绩公共查询</li>
</ol>

<!-- 当前位置 end -->

<!-- 中间内容 start -->
<div class="content">
	<!-- 查询表单 start -->
	<div class="lookfor nomar-t20">
		<div class="row">
			<div class="col-md-12">
				<div class="form-inline">
					<div class="form-group">
						<label >项目名称</label> <input type="text"
							class="form-control" placeholder="请输入项目名称" id="projectName">
							<input type="hidden" id="projectCid"/>
					</div>
					<div class="form-group">
                        <label for="csdwmc">参赛单位名称</label>
                        <input type="text" class="form-control" placeholder="输入单位名称（可选）" id="queryUnitName">
                    </div>
                    <div class="form-group">
                        <label for="ydyxm">参赛人/队</label>
                        <input type="text" class="form-control" placeholder="输入参赛人/队名称（可选）" id="queryAthleteName">
                    </div>
					<button id="queryBtn" type="submit" class="btn btn-primary pull-right">
						<span class="icon-search"></span>&nbsp;&nbsp;查询
					</button>
					<input type="hidden" id="refreshTable"/>
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
						<th>项目信息</th>
						<th>赛制类别</th>
						<th>名称</th>
						<th>参赛单位</th>
						<th>成绩</th>
						<th>名次</th>
						<th>积分</th>
						<th>奖牌</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="list">
				</tbody>
				<tfoot>
					<tr id="noDataPage" <c:if test="${list.size()>0}">class="hidden"</c:if>>
						<td colspan="9" align="center">暂无数据</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- 分页 start -->
		<%@include file="/WEB-INF/view/common/pageInfo.jsp"%>
		<!-- 分页 end -->
	</div>
	<!-- 表格 end -->
</div>
<!-- 中间内容 end -->

<%@include file="/WEB-INF/view/scoreManager/viewScoresModal.jsp"%>
<script src="${contextPath}/static/localjs/scoreManager/scoreQuery/index.js"></script>
<%@include file="/WEB-INF/view/common/foot.jsp"%>

