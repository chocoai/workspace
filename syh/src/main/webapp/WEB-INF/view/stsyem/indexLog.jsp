<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li>首页</li>
	<li>系统配置管理</li>
	<li class="active">系统日志管理</li>
</ol>
<!-- 中间内容 strat -->
<div class="content">
	<!-- 查询表单 strat -->
	<div class="lookfor" style="margin-top: 0px;">
		<div class="row">
			<div class="col-md-12">
				<div class="form-inline">
					<div class="form-group mar-r15">
			            <label>日志类型</label>
			            <select class="form-control" id="opeartionType">
			                <option value="0" selected="true">登录日志</option>
			                <option value="1">操作日志</option>
			            </select>
			        </div>
					<div class="form-group">
						<label for="sjflmc">操作用户</label> 
						<input id="opeartionUser" type="text" class="form-control" placeholder="输入操作用户">
					</div>
					<div class="form-group">
						<label for="sjflmc">操作时间</label> 
						<input id="timeStart" type="text" class="form-control" placeholder="输入开始时间">至
						<input id="timeEnd" type="text" class="form-control" placeholder="输入结束时间">
					</div>
					<button id="queryBtn" type="submit" class="btn btn-primary pull-right">
						<span class="icon-search"></span>&nbsp;&nbsp;查询
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 查询表单 end -->

	<!-- 表格 strat -->
	<div class="mytable">
		<div class="table-responsive">
		     <div class="jqGrid_wrapper">
                   <table id="table_dict"></table>
                   <div id="pager_list"></div>
             </div>
		</div>
	</div>
	<!-- 表格 end -->

</div>
<!-- 中间内容 end -->

<!-- 底部固定 strat -->
<div class="col-md-12 col-sm-12 col-xs-12 mybottom-box">
    <div style="padding: 0 20px;">
		<div class="form-inline">
		<div class="form-group mar-r15">
			<label>删除类型</label>
	        <select class="form-control" id="deleteType">
	             <option value="0" selected="true">选中删除</option>
	             <option value="1">条件删除</option>
	         </select>
    	</div>
         <a id="deleteBtn" class="btn btn-warning difbtn" href="javascript:;" role="button"> 
         	<i class="icon-minus"></i>&nbsp;删除
		 </a> 
    </div>
    </div>
</div>
<!-- 底部固定 end -->
<script src="${contextPath}/static/localjs/stsyem/indexLog.js"></script>
<script src="${contextPath}/static/localjs/commonFunction.js"></script>
<!-- jqGrid -->
<link href="${contextPath}/static/jqGrid/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${contextPath}/static/jqGrid/css/ui.jqgridffe4.css?0820" rel="stylesheet">

<script src="${contextPath}/static/jqGrid/js/jquery.peity.min.js"></script>
<script src="${contextPath}/static/jqGrid/js/grid.locale-cnffe4.js?0820"></script>
<script src="${contextPath}/static/jqGrid/js/jquery.jqGrid.minffe4.js?0820"></script>
<script src="${contextPath}/static/jqGrid/js/content.min.js?v=1.0.0"></script> 
<script src="${contextPath}/static/jqGrid/js/jquery.ui.resizable.js"></script> 
