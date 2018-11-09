<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/head.jsp"%>
<!-- 当前位置 strat -->
<ol class="breadcrumb location">
	<span class="icon-home">&nbsp;&nbsp;</span>
	<li class="noline">首页</li>
	<li class="active">修改密码</li>
</ol>
<!-- 当前位置 end -->

<!-- 中间内容 strat -->
<div class="content">
	<div class="panel panel-default form-header">
		<div class="panel-heading">
			<h3 class="panel-title">修改密码</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<form class="form-horizontal">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-md-2 control-label" for="xzydyName1">初始密码</label>
							<div class="col-md-8">
								<input type="password" class="form-control" placeholder="请输入初始密码" id="pwd">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-md-2 control-label">新密码</label> 
							<div class="col-md-8">
								<input type="password" class="form-control" placeholder="请输入新密码" id="pwd1">
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-md-2 control-label" for="recordHolder">确认密码</label>
							<div class="col-md-8">
								<input type="password" class="form-control" placeholder="请再次输入新密码" id="pwd2">
							</div>
						</div>
					</div>

					<div class="col-md-9 col-md-offset-1">
						<button type="button" id="submitBtn" class="btn btn-success myformbtn pull-right">保存</button>
					</div>
				</form>

			</div>
		</div>
	</div>
</div>
<!-- 中间内容 end -->
<script src="${contextPath}/static/localjs/user/indexPwd.js"></script>
<script src="${contextPath}/static/localjs/user/MD5.js"></script>